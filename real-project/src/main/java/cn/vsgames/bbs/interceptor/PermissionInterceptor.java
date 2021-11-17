package cn.vsgames.bbs.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.vsgames.bbs.constant.Constant;
import cn.vsgames.bbs.domain.LoginUser;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		LoginUser loginUser = (LoginUser) request.getSession(true).getAttribute(Constant.LOGIN_USER_SESSION_KEY);
		if(loginUser != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
	}

}
