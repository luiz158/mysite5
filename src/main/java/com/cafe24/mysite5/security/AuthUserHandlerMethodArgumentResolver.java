package com.cafe24.mysite5.security;

import com.cafe24.mysite5.domain.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		//1. 내가 지원하는 매개변수인지 여부 확인
		//@AuthUser가 붙어 있는지 확인
		AuthUser authUser = parameter.getParameterAnnotation( AuthUser.class );
		//2.  @AuthUser가 안 붙어 있으면 null 반환
		if( authUser == null ) {
			return false;
		}
		
		//3. Type이 UserVo가 아닌 경우
		if( parameter.getParameterType().equals( User.class ) == false ){
			return false;
		}
		
		//4. @AuthUser가 붙어있고 타입이 UserVo 타입인 경우
		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
								  ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest,
								  WebDataBinderFactory binderFactory) throws Exception {
		if( supportsParameter(parameter) == false ) {
			//매개변수가 없다면
			return WebArgumentResolver.UNRESOLVED;
		}
		
		//@AuthUser가 붙어있고 타입이 UserVo 타입인 경우의 처리
		HttpServletRequest request = webRequest.getNativeRequest( HttpServletRequest.class );
		HttpSession session = request.getSession();
		
		if( session == null ) {
			return null;
		}
		return session.getAttribute( "authUser" );
	}

}
