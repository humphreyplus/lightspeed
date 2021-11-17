package cn.vsgames.bbs.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vsgames.bbs.domain.AjaxResult;
import cn.vsgames.bbs.service.EmailService;
import cn.vsgames.bbs.service.UserService;

@Controller
public class EmailController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@ResponseBody
	@RequestMapping(value="/setPwdEmail", method = RequestMethod.POST)
	public Object setPwdEmail(String email) throws MessagingException {
		AjaxResult ajaxResult = new AjaxResult();
		
		int count = userService.getUserCountByEmail(email);
		if(count == 0) {
			ajaxResult.setStatus(false);
			ajaxResult.setMessage("该邮箱未注册过VSGAMES账号！");
		} else {
			emailService.sendSetPwdEmail(email);
			ajaxResult.setStatus(true);
			ajaxResult.setMessage("重置密码邮件已发送，请登录邮箱完成密码重置！");
		}
		
		return ajaxResult;
	}

}
