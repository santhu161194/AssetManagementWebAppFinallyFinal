package org.medplus.assetmanagementcore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!request.getRequestURI().contains("/login")) {
		String userInfo = (String)request.getSession()
					.getAttribute("username");
			if (userInfo == null) {
				response.sendRedirect("login");
				return false;
			}
		}
		return true;
	}

}