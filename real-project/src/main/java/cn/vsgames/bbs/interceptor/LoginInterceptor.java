package cn.vsgames.bbs.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.vsgames.bbs.constant.Constant;
import cn.vsgames.bbs.domain.LoginUser;
import cn.vsgames.bbs.service.UserService;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			LoginUser loginUser = (LoginUser) request.getSession(true).getAttribute(Constant.LOGIN_USER_SESSION_KEY);
			if(loginUser == null) {
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals("loginuser")) {
							String value = cookie.getValue();
							value = URLDecoder.decode(value, "UTF-8");
							int index = value.lastIndexOf("|");
							String user = value.substring(0, index);
							String password = value.substring(index + 1);
							loginUser = userService.login(user, password);
							if(loginUser != null) {
								request.getSession(true).setAttribute(Constant.LOGIN_USER_SESSION_KEY, loginUser);
							}
							
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
