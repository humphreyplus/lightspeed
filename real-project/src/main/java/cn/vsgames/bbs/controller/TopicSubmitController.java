package cn.vsgames.bbs.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vsgames.bbs.constant.Constant;
import cn.vsgames.bbs.domain.AjaxResult;
import cn.vsgames.bbs.domain.LoginUser;
import cn.vsgames.bbs.service.TopicService;

@Controller
public class TopicSubmitController {
	
	@Autowired
	private TopicService topicService;
	
	@ResponseBody
	@RequestMapping(value = "/ajaxreply", method = RequestMethod.POST)
	public Object replySubmit(String html, int topicId, HttpSession session)
			throws PolicyException, ScanException, IOException {
		int userId = ((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId();
		topicService.insertReply(html, userId, topicId);
		AjaxResult result = new AjaxResult();
		result.setStatus(true);
		int pageCount = topicService.getReplyPageCount(topicId);
		result.setMessage(Integer.toString(pageCount));
		return result;
	}
	
	@RequestMapping(value = "/donewtopic", method = RequestMethod.POST)
	public String topicSubmit(String content, String title, int sectionId, String image,
			@RequestParam(value = "topicTypeId", defaultValue = "0") int topicTypeId,
			HttpSession session, HttpServletRequest request) throws PolicyException, ScanException, IOException {
		if(title.length() < 4 || title.length() > 100 || content.length() == 0) {
			return "redirect:/new";
		}
		int userId = ((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId();
		int topicId = topicService.insertTopic(content, title, image, sectionId, topicTypeId, userId);
		return "redirect:/topic/" + topicId;
	}
	
	@RequestMapping(value = "/gotoReply", method = RequestMethod.GET)
	public String gotoReply(int id) {
		int topicId = topicService.getTopicIdByReplyId(id);
		int page = topicService.getReplyPagePosition(id);
		return "redirect:/topic/" + topicId + "?p=" + page + "#gotoReply" + id;
	}
	
	@ResponseBody
	@RequestMapping(value = "/my/deletereply", method = RequestMethod.POST)
	public Object deleteReply(int id, HttpSession session) {
		topicService.deleteReply(id, ((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId());
		AjaxResult result = new AjaxResult();
		result.setStatus(true);
		return result;
	}
	
	@RequestMapping(value = "/my/updatereply", method = RequestMethod.POST)
	public String updateReply(int id, String content, HttpSession session) throws PolicyException, ScanException, IOException {
		topicService.updateReply(id,
				((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId(), content);
		return "redirect:/gotoReply?id=" + id;
	}
	
	@ResponseBody
	@RequestMapping(value = "/my/deletetopic", method = RequestMethod.POST)
	public Object deleteTopic(int id, HttpSession session) {
		topicService.deleteTopic(id, ((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId());
		AjaxResult result = new AjaxResult();
		result.setStatus(true);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/my/updatetopic", method = RequestMethod.POST)
	public AjaxResult updateTopic(int id, String title, HttpSession session) {
		topicService.updateTopic(id,
				((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId(), title);
		AjaxResult result = new AjaxResult();
		result.setStatus(true);
		return result;
	}

}
