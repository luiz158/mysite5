package com.cafe24.mysite5.security.csrf;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

final class CSRFInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response,
	                          Object handler ) throws Exception{

		System.out.println( "CSRFInterceptor" );
		if ( !request.getMethod().equalsIgnoreCase( "POST" ) ) {
			// Not a POST - allow the request
			return true;
		} else {

			// This is a POST request - need to check the CSRF token
			String sessionToken = CSRFTokenManager.getTokenForSession( request.getSession() );
			String requestToken = CSRFTokenManager.getTokenFromRequest( request );
			if ( sessionToken.equals( requestToken ) ) {
				/* 요청 토큰과 세션 토큰이 같으면 통과 */
				return true;
			} else {
				response.sendError( HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value" );
				return false;
			}
		}
	}
}