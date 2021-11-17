package cn.vsgames.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.vsgames.bbs.constant.Constant;
import cn.vsgames.bbs.domain.Donate;
import cn.vsgames.bbs.domain.Gw2Area;
import cn.vsgames.bbs.domain.Gw2Homeland;
import cn.vsgames.bbs.domain.LoginUser;
import cn.vsgames.bbs.domain.Message;
import cn.vsgames.bbs.domain.Reply;
import cn.vsgames.bbs.domain.Section;
import cn.vsgames.bbs.domain.StatisticalData;
import cn.vsgames.bbs.domain.Topic;
import cn.vsgames.bbs.domain.Type;
import cn.vsgames.bbs.domain.User;
import cn.vsgames.bbs.service.DonateService;
import cn.vsgames.bbs.service.MessageService;
import cn.vsgames.bbs.service.StatisticalService;
import cn.vsgames.bbs.service.TopicService;
import cn.vsgames.bbs.service.UserService;
import cn.vsgames.bbs.util.PagerUtil;

@Controller
public class MainController {
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StatisticalService statisticalService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private DonateService donateService;
	
	@RequestMapping(value = "/topic/{topicId}", method = RequestMethod.GET)
	public ModelAndView topic(@PathVariable Integer topicId, 
			@RequestParam(value = "p", required = false, defaultValue = "1") int p) {
		ModelAndView modelAndView = new ModelAndView("topic");
		Topic topic = topicService.getTopicById(topicId);
		int pageCount = topicService.getReplyPageCount(topicId);
		if(p < 1) {
			p = 1;
		} else if(p > pageCount) {
			p = pageCount;
		}
		List<Integer> pageList = PagerUtil.generatePageList(p, pageCount);
		List<Reply> replyList = topicService.getReplyList(topicId, p);
		if(topic == null) {
			modelAndView.setViewName("redirect:/404");
		} else {
			List<Section> sectionList = topicService.getSectionList();
			modelAndView.addObject("sectionList", sectionList);
			modelAndView.addObject("topic", topic);
			modelAndView.addObject("replyList", replyList);
			modelAndView.addObject("pageList", pageList);
			modelAndView.addObject("p", p);
			modelAndView.addObject("pageCount", pageCount);
			generateModel(modelAndView);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value = "p", required = false, defaultValue = "1") int p) {
		ModelAndView modelAndView = new ModelAndView("list");
		int pageCount = topicService.getTopicPageCount(null, null);
		if(p < 1) {
			p = 1;
		} else if(p > pageCount) {
			p = pageCount;
		}
		List<Integer> pageList = PagerUtil.generatePageList(p, pageCount);
		List<Topic> topicList = topicService.getTopicList(null, null, p);
		List<Section> sectionList = topicService.getSectionList();
		modelAndView.addObject("sectionList", sectionList);
		modelAndView.addObject("topicList", topicList);
		modelAndView.addObject("pageList", pageList);
		modelAndView.addObject("p", p);
		modelAndView.addObject("pageCount", pageCount);
		generateModel(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(String key, @RequestParam(value = "p", required = false, defaultValue = "1") int p) {
		ModelAndView modelAndView = new ModelAndView("list");
		int pageCount = topicService.getTopicPageCount(null, key);
		if(p < 1) {
			p = 1;
		} else if(p > pageCount) {
			p = pageCount;
		}
		List<Integer> pageList = PagerUtil.generatePageList(p, pageCount);
		List<Topic> topicList = topicService.getTopicList(null, key, p);
		modelAndView.addObject("topicList", topicList);
		modelAndView.addObject("pageList", pageList);
		modelAndView.addObject("p", p);
		modelAndView.addObject("pageCount", pageCount);
		generateModel(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/section/{sectionId}", method = RequestMethod.GET)
	public ModelAndView section(@PathVariable Integer sectionId, 
			@RequestParam(value = "p", required = false, defaultValue = "1") int p) {
		ModelAndView modelAndView = new ModelAndView("list");
		int pageCount = topicService.getTopicPageCount(sectionId, null);
		if(p < 1) {
			p = 1;
		} else if(p > pageCount) {
			p = pageCount;
		}
		List<Integer> pageList = PagerUtil.generatePageList(p, pageCount);
		List<Topic> topicList = topicService.getTopicList(sectionId, null, p);
		List<Section> sectionList = topicService.getSectionList();
		String sectionName = null;
		for(Section section : sectionList) {
			if(sectionId == section.getId()) {
				sectionName = section.getName();
				break;
			}
		}
		modelAndView.addObject("sectionList", sectionList);
		modelAndView.addObject("sectionId", sectionId);
		modelAndView.addObject("sectionName", sectionName);
		modelAndView.addObject("topicList", topicList);
		modelAndView.addObject("pageList", pageList);
		modelAndView.addObject("p", p);
		modelAndView.addObject("pageCount", pageCount);
		generateModel(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public ModelAndView user(@PathVariable Integer userId) {
		ModelAndView modelAndView = new ModelAndView("user");
		User user = userService.getUserById(userId);
		if(user == null) {
			modelAndView.setViewName("redirect:/404");
		} else {
			List<Topic> topicList = topicService.getUserNewTopic(userId);
			List<Reply> replyList = topicService.getUserNewReply(userId);
			modelAndView.addObject("user", user);
			modelAndView.addObject("topicList", topicList);
			modelAndView.addObject("replyList", replyList);
			generateModel(modelAndView);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/donate", method = RequestMethod.GET)
	public ModelAndView donate() {
		ModelAndView modelAndView = new ModelAndView("donate");
		List<Donate> list = donateService.getDonateList();
		modelAndView.addObject("donateList", list);
		generateModel(modelAndView);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/my/avatar", method = RequestMethod.GET)
	public ModelAndView myAvatar(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("myavatar");
		LoginUser loginUser = (LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY);
		User user = userService.getUserById(loginUser.getId());
		if(user == null) {
			modelAndView.setViewName("redirect:/404");
		} else {
			modelAndView.addObject("user", user);
			generateModel(modelAndView);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/my/gw2info", method = RequestMethod.GET)
	public ModelAndView myGw2Info(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("mygw2info");
		LoginUser loginUser = (LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY);
		User user = userService.getUserById(loginUser.getId());
		if(user == null) {
			modelAndView.setViewName("redirect:/404");
		} else {
			List<Gw2Area> gw2AreaList = userService.getGw2AreaList();
			List<Gw2Homeland> gw2HomelandList = userService.getGw2HomelandList();
			modelAndView.addObject("user", user);
			modelAndView.addObject("gw2AreaList", gw2AreaList);
			modelAndView.addObject("gw2HomelandList", gw2HomelandList);
			generateModel(modelAndView);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		generateModel(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("register");
		generateModel(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newPost() {
		ModelAndView modelAndView = new ModelAndView("new");
		List<Section> sectionList = topicService.getSectionList();
		List<Type> typeList = topicService.getTypeList();
		modelAndView.addObject("sectionList", sectionList);
		modelAndView.addObject("typeList", typeList);
		generateModel(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/forget", method = RequestMethod.GET)
	public ModelAndView forget() {
		ModelAndView modelAndView = new ModelAndView("forget");
		generateModel(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/my/send", method = RequestMethod.GET)
	public ModelAndView send() {
		ModelAndView modelAndView = new ModelAndView("send");
		generateModel(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/resetpwd", method = RequestMethod.GET)
	public ModelAndView resetpwd(String key) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String email = userService.getEmailByResetPwdKey(key);
		if(email != null) {
			modelAndView.setViewName("resetpwd");
		} else {
			modelAndView.setViewName("resetpwderror");
		}
		
		generateModel(modelAndView);
		return modelAndView;
	}
	

	@RequestMapping(value = "/my/message", method = RequestMethod.GET)
	public ModelAndView message(HttpSession session, @RequestParam(value = "p", required = false, defaultValue = "1") int p) {
		
		int userId = ((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId();
		
		ModelAndView modelAndView = new ModelAndView("message");
		int pageCount = messageService.getMessagePageCount(userId);
		if(p < 1) {
			p = 1;
		} else if(p > pageCount) {
			p = pageCount;
		}
		List<Message> messageList = messageService.getMessageList(userId, p);
		modelAndView.addObject("messageList", messageList);
		modelAndView.addObject("p", p);
		modelAndView.addObject("pageCount", pageCount);
		generateModel(modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = "/my/editReply", method = RequestMethod.GET)
	public ModelAndView editReply(HttpSession session, int id) {
		ModelAndView modelAndView = new ModelAndView();
		int userId = ((LoginUser) session.getAttribute(Constant.LOGIN_USER_SESSION_KEY)).getId();
		Reply reply = topicService.getReplyById(id);
		if(reply != null && reply.getUserId() == userId) {
			modelAndView.setViewName("editReply");
			modelAndView.addObject("replyContent", reply.getContent());
			generateModel(modelAndView);
		} else {
			modelAndView.setViewName("redirect:/404");
		}
		
		return modelAndView;
	}
	
	private void generateModel(ModelAndView modelAndView) {
		/*List<Topic> hotTopicList = topicService.getHotTopicList();
		modelAndView.addObject("hotTopicList", hotTopicList);*/
		StatisticalData statisticalData = statisticalService.getStatisticalData();
		modelAndView.addObject("statisticalData", statisticalData);
	}
}