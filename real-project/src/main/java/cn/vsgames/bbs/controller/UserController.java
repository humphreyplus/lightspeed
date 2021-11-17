package cn.vsgames.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiniu.api.auth.AuthException;

import cn.vsgames.bbs.constant.Constant;
import cn.vsgames.bbs.domain.AjaxResult;
import cn.vsgames.bbs.domain.LoginUser;
import cn.vsgames.bbs.domain.Message;
import cn.vsgames.bbs.domain.User;
import cn.vsgames.bbs.init.ApplicationProperties;
import cn.vsgames.bbs.service.MessageService;
import cn.vsgames.bbs.service.QiniuService;
import cn.vsgames.bbs.service.UserService;
import cn.vsgames.bbs.util.ImageUtil;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private QiniuService qiniuService;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@RequestMapping(value = "/my/doSubmitGw2Info", method = RequestMethod.POST)
	public String doSubmitGw2Info(User user, HttpSession session) {
		
		String gameNickname = user.getGameNickname();
		if(gameNickname != null && gameNickname.length() > 0) {
			if(!gameNickname.matches(".*\\.[0-9]{4}")) {
				return "redirect:/my/gw2info";
			}
		}
		
		LoginUser loginUser = (LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY);
		user.setId(loginUser.getId());
		userService.submitGw2Info(user);
		return "redirect:/user/" + loginUser.getId();
	}
	
	@ResponseBody
	@RequestMapping(value = "/my/doSubmitAvatarInfo", method = RequestMethod.POST)
	public Object doSubmitAvatarInfo(String fileName, int x, int y, int h, HttpSession session,
			HttpServletRequest request) throws IOException, JSONException, AuthException {
		
		// 图片裁剪
		String srcFile = applicationProperties.getAvatarUploadTempFolder() + File.separatorChar 
				+ fileName;
		String destFileName = UUID.randomUUID().toString() + ".png";
		String destFile = applicationProperties.getAvatarFolder() + File.separatorChar 
				+ destFileName;
		ImageUtil.avatarCrop(srcFile, destFile, x, y, h);
		
		// 生成HTTP地址存入数据库
		LoginUser loginUser = (LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY);
		String destFileHttpUrl = null;
		if(applicationProperties.getAvatarStorage() == 1) {
			destFileHttpUrl = qiniuService.uploadFile(destFile, applicationProperties.getAvatarQiniuPath()
					+ "/" + destFileName);
			if(new File(destFile).length() > 10240) {
				destFileHttpUrl += "?imageView2/2/w/300/h/300/format/jpg";
			}
		} else {
			destFileHttpUrl = applicationProperties.getAvatarHttpUrl() + "/" + destFileName;
		}
		userService.updateUserAvatar(loginUser.getId(), destFileHttpUrl);
		loginUser.setAvatar(destFileHttpUrl);
		
		// 返回成功
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatus(true);
		ajaxResult.setMessage(request.getContextPath() + "/user/" + loginUser.getId());
		return ajaxResult;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/my/sendMessage", method = RequestMethod.POST)
	public Object sendMessage(String username, String content, HttpSession session) {
		
		AjaxResult ajaxResult = new AjaxResult();
		
		User user = userService.getUserByUsername(username);
		if(user == null) {
			ajaxResult.setStatus(false);
			ajaxResult.setMessage("该用户不存在！");
		} else {
			Message message = new Message();
			message.setContent(content);
			message.setSendUserId(((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId());
			message.setType(1);
			message.setUserId(user.getId());
			messageService.insertMessage(message);
			ajaxResult.setStatus(true);
			ajaxResult.setMessage("消息发送成功！");
		}
		
		return ajaxResult;
	}
	
	@ResponseBody
	@RequestMapping(value = "/my/getMsgCount", method = RequestMethod.GET)
	public Object getMsgCount(HttpSession session) {
		
		AjaxResult ajaxResult = new AjaxResult();

		int count = messageService.getUserUnreadMsgCount(((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId());
		ajaxResult.setStatus(true);
		if(count == 0) {
			ajaxResult.setMessage("");
		} else if(count < 100) {
			ajaxResult.setMessage("(" + String.valueOf(count) + ")");
		} else {
			ajaxResult.setMessage("(N)");
		}
		
		return ajaxResult;
	}
	
	@ResponseBody
	@RequestMapping(value = "/my/readMessage", method = RequestMethod.POST)
	public Object readMessage(HttpSession session, int id) {
		
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatus(true);
		
		messageService.readMessage(((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId(), id);
		
		return ajaxResult;
	}
}