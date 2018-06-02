package com.cafe24.mysite5.security;

import com.cafe24.mysite5.domain.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		User authUser = (User) session.getAttribute( "authUser" );
		if( authUser != null ) {
			session.removeAttribute( "authUser" );
			session.invalidate();
		}
		
		response.sendRedirect( request.getContextPath() );
		return false;
	}
	
}
