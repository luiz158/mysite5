package com.cafe24.mysite5.security.csrf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class CSRFTokenManager{

	/**
	 * The token parameter name
	 */
	static final String CSRF_PARAM_NAME = "CSRFToken";

	/**
	 * The location on the session which stores the token
	 */
	private final static String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getName() + ".tokenval";

	/* HTTPSession의 속성으로 저장된 CSRF 토큰 체크 */
	static String getTokenForSession (HttpSession session) {
		String token = null;
		// I cannot allow more than one token on a session - in the case of two requests trying to
		// init the token concurrently
		/* 하나의 세션에 하나의 CSRF 토큰을 만들어서 사용하기 위해 session에 대해 동기화 설정 */
		synchronized (session) {
			token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
			System.out.println("in getTokenForSession CSRF_TOKEN_FOR_SESSION_ATTR_NAME: " + CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
			System.out.println( "in getTokenForSession token: " + token);
			/* 세션에 CSRF_TOKEN_FOR_SESSION_ATTR_NAME이 없는 경우 토큰을 생성하여 세션에 저장 */
			if ( token == null ) {
				token = UUID.randomUUID().toString();
				System.out.println( "TOKEN: " + token );
				session.setAttribute( CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token );
			}
		}
		return token;
	}

	/**
	 * Extracts the token value from the session
	 * @param request
	 * @return
	 */
	static String getTokenFromRequest(HttpServletRequest request) {
		return request.getParameter(CSRF_PARAM_NAME);
	}

	private CSRFTokenManager() {};
}
