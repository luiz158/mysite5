package com.cafe24.mysite5.security;


import com.cafe24.mysite5.domain.User;
import com.cafe24.mysite5.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@EnableWebSecurity
public class AuthLoginInterceptor extends HandlerInterceptorAdapter{

	/*@Autowired
	private UserService userService;*/
	@Override
	public boolean preHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler) throws Exception {
		
		String email = request.getParameter( "email" );
		String password = request.getParameter( "password" );
		String csrfToken = request.getParameter( "_csrf" );
		//UserService userService = new UserService(); //불가능한 코드. userService 내의 Dao 주입 불가
		
		//Web Application Context는 Root Application Context를 참조하고 있을 것이다.
		User vo = new User();
		vo.setEmail( email );
		vo.setPassword( password );
		
		/* WebApplicationContextUtils는 어디서든지 컨테이너에 접근할 수 있는 static 클래스 */
		ApplicationContext ac
				= WebApplicationContextUtils
				  .getWebApplicationContext( request.getServletContext() );
		/* 타입을 주면 해당 타입으로 리턴하고, id를 주면 Object 타입을 리턴 */
		UserService userService = ac.getBean( UserService.class );
		User authUser = userService.getUserByEmailandPassword(vo);
		
		if( authUser == null ) {
			/* request.getContextPath()는 /mysite5를 반환한다. */
			response.sendRedirect( request.getContextPath() + "/user/login" );
			return false;
		}
		
		//인증이 됐다면 뒤로 넘긴다.
		HttpSession session = request.getSession();
		authUser.setEmail( email );
		session.setAttribute( "authUser", authUser );
		/* 로그인 성공했으므로 세션에 CSRF 토큰값 저장 */
		session.setAttribute( "_csrf", csrfToken );
		response.sendRedirect( request.getContextPath() );
		return false;
		
	}

	
}
