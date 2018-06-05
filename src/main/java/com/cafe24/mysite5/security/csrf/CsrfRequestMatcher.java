package com.cafe24.mysite5.security.csrf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Component
public class CsrfRequestMatcher implements RequestMatcher{
	private Logger logger = LoggerFactory.getLogger( "CsrfRequestMatcher" );
	private Pattern allowedMethods = Pattern.compile( "^(GET|HEAD|TRACE|OPTIONS)$");
//	private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher( "/guestbook*", null);

	private AntPathRequestMatcher[] requestMatchers = {
			new AntPathRequestMatcher( "/guestbook/**" ),
			new AntPathRequestMatcher( "/api/**" ),
			new AntPathRequestMatcher( "/user/auth" )
	};

	public CsrfRequestMatcher(){
		logger.info( "CsrfRequestMatches started..." );
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		logger.info( "CsrfRequestMatcher matches started..." );
		/* GET, HEAD, TRACE, OPTIONS 방식일 경우
		*  false를 return함으로써 CSRF가 적용되지 않아야 함을 알린다*/
		logger.info( request.getMethod() );  // POST, GET ...
		if(allowedMethods.matcher(request.getMethod()).matches()){
			return false;
		}

//		System.out.println(!unprotectedMatcher.matches(request));
//		return !unprotectedMatcher.matches(request);

		for ( AntPathRequestMatcher aprm : requestMatchers ) {
			logger.info( request.toString() );
			//  org.springframework.security.web.context.HttpSessionSecurityContextRepository$Servlet3SaveToSessionRequestWrapper@206c050a

			logger.info( Boolean.toString( aprm.matches( request ) ) );  // false

			if ( aprm.matches( request ) ) {
				return false;
			}
		}

		return true;
	}
}
