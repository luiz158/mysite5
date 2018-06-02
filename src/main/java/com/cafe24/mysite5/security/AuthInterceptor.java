package com.cafe24.mysite5.security;

import com.cafe24.mysite5.domain.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response, 
							 Object handler) throws Exception {
		
		//1. handler 종류 구하기
		//default servlet handler도 들어올 수 있기 때문
		//일반적으로 정적인 자원들은 default servlet에서 담당하고, 이를 assets에 모아서 exclusion을 하면 된다
		//하지만 그럼에도 들어올 가능성이 있으므로 처리를 해줘야 한다
		if( ( handler instanceof HandlerMethod ) == false ) {
			//default servlet handler가 가야 할 곳으로 보낸다
			return true;
		}
		
		//2. casting
		//어노테이션 정보 뽑아내기 위해서
		HandlerMethod handlerMethod = (HandlerMethod ) handler;
		
		//3. @Auth 받아오기
		//만약 없다면 null 나올 것이다
		Auth auth = handlerMethod.getMethodAnnotation( Auth.class );
		
		//auth로부터 값을 가져와서 USER, ADMIN 등을 처리할 수 있다.
		
		//4. Method에 @Auth가 없는 경우
		if( auth == null ) {
			//갈 곳으로 보낸다. Controller단으로
			return true;
		}
		
		//5. @Auth가 붙어있는 경우, 인증 여부 체크
		HttpSession session = request.getSession();
		
		//session이 없는 경우
		if( session == null ) {
			response.sendRedirect( request.getContextPath() + "/user/login" );
			return false;
		}
		
		//라이브러리로 만들 경우 더욱 추상화하여 특정 애플리케이션에 한정되지 않도록 만들어야 한다.
		//이 경우에는 UserVo 등으로 캐스팅을 하는 등 mysite3에 의존적이다.
		User authUser = (User) session.getAttribute( "authUser" );
		if( authUser == null ) {
			//인증되지 않은 사용자인 경우
			response.sendRedirect( request.getContextPath() + "/user/login" );
			return false;
		}
		
		//6. 접근 허가
		return true;
	}
	
}
