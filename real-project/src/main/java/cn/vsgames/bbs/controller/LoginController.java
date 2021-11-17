package cn.vsgames.bbs.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.vsgames.bbs.constant.Constant;
import cn.vsgames.bbs.domain.LoginUser;
import cn.vsgames.bbs.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/dologin", method = RequestMethod.POST)
	public String login(String userstr, String password, String saveUser,
			HttpSession session, HttpServletResponse response) {
		
		LoginUser loginUser = userService.login(userstr, password);
		if(loginUser != null) {
			session.setAttribute(Constant.LOGIN_USER_SESSION_KEY, loginUser);
			
			if(saveUser != null && saveUser.equals("saveUser")) {
				String encode = "";
				try {
					encode = URLEncoder.encode(userstr + "|" + password, "UTF-8");
					Cookie cookie = new Cookie("loginuser", encode);
					cookie.setMaxAge(60 * 60 * 24 * 365);
					cookie.setPath("/");
					response.addCookie(cookie);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			
			return "redirect:/";
		} else {
			return "redirect:/login?error=true";
		}
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute(Constant.LOGIN_USER_SESSION_KEY);
		Cookie cookie = new Cookie("loginuser", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/";
	}
	
	@RequestMapping(value="/doregister", method = RequestMethod.POST)
	public String register(String username, String password, String email, HttpSession session) throws UnsupportedEncodingException {
		// 校验
		/*
		if(!username.matches("[A-Za-z0-9\u4E00-\u9FA5]{2,8}") 
				|| !password.matches("[A-Za-z0-9]{6,20}")
				|| email.length() < 5 || email.length() > 50 || email.indexOf("@") < 0) {
			return "redirect:/register";
		}
		*/
		
		// 判断username唯一
		int count = userService.getUserCountByUsername(username);
		if(count > 0) {
			return "redirect:/register?error=userexist";
		}
		
		// 判断email唯一
		count = userService.getUserCountByEmail(email);
		if(count > 0) {
			return "redirect:/register?error=emailexist";
		}
		
		//String username2 = URLEncoder.encode(username, "utf-8");
		int userId = userService.register(username, password, email);
		LoginUser loginUser = userService.getLoginUserById(userId);
		session.setAttribute(Constant.LOGIN_USER_SESSION_KEY, loginUser);
		return "redirect:/";
	}
	
	@RequestMapping(value="/doresetpwd", method = RequestMethod.POST)
	public String doresetpwd(String password, String key) {
		// 校验
		if(!password.matches("[A-Za-z0-9]{6,20}")) {
			return "resetpwderror";
		}
		
		String email = userService.getEmailByResetPwdKey(key);
		if(email == null) {
			return "resetpwderror";
		} else {
			userService.updatePwd(key, email, password);
			return "resetpwdsuccess";
		}
	}
}