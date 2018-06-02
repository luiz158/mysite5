package com.cafe24.mysite5.filter;

import com.cafe24.mysite5.security.FormTokenResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FormTokenVerificationFilter implements Filter{


	private static final String[] EXCLUDE_URL_LIST = { "/logout", "/assets", "/errorpages", "/WEB-INF", "/report/download" };


	private static final String PREFIX_NAME = "_";
	private static final String SESSION_TOKEN_NAME = "SESSION_TOKEN_NAME";
	private static final String SESSION_ACTIVE_TOKEN = "SESSION_ACTIVE_TOKEN";

	private static final String REDIRECT_URL = "/potal/auth/auth_form.do?resultMessage=INVALID_TOKEN";

	private Logger logger = LoggerFactory.getLogger( this.getClass() );




	@Autowired
	private CsrfTokenRepository csrfTokenRepository;



	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException{
		if( request instanceof HttpServletRequest ){
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			HttpSession httpSession = httpRequest.getSession();

			boolean excludeState = false;
			String reqUrl = httpRequest.getRequestURL().toString();

			for( String target : EXCLUDE_URL_LIST ){

				if( reqUrl.indexOf( target ) > -1 ){

					excludeState = true;

					break;

				}

			}



			if( excludeState ){

				chain.doFilter( request, response );

				return;

			}

			ServletResponse newResponse = new FormTokenResponseWrapper( httpResponse );
			chain.doFilter( request, newResponse );

			/*if( newResponse instanceof FormTokenResponseWrapper )	{

				String tokenName = PREFIX_NAME + RandomCodeUtils.generate();

				String tokenValue = RandomCodeUtils.generate();

				String encryptedTokenValue = CryptoUtils.encrypt( tokenValue );





				CsrfToken token = (CsrfToken) request.getAttribute( "_csrf");

				// Spring Security will allow the Token to be included in this header name

				// response.setHeader("X-CSRF-HEADER", token.getHeaderName());

				// Spring Security will allow the token to be included in this parameter name

				// response.setHeader("X-CSRF-PARAM", token.getParameterName());

				// this is the value of the token to be included as either a header or an HTTP parameter

				//response.setHeader("X-CSRF-TOKEN", token.getToken());



				//final CsrfToken token = csrfTokenRepository.loadToken(httpRequest);

				String tokenStr = "";

				*//* hidden 태그 추가 *//*
				if(token != null) {
					tokenStr = String.format("<input type=\"hidden\" name=\"%s\" value=\"%s\" />", token.getParameterName(), token.getToken());
				}


				String responseText = newResponse.toString();

				if( responseText != null && httpSession != null ){



					httpSession.removeAttribute( SESSION_ACTIVE_TOKEN );

					httpSession.removeAttribute( SESSION_TOKEN_NAME );


					httpSession.setAttribute( SESSION_TOKEN_NAME, tokenName );

					httpSession.setAttribute( SESSION_ACTIVE_TOKEN, tokenValue );



					logger.debug( "===============================");

					logger.debug( "TOKEN-NAME : {} ", httpRequest.getRequestURI() );

					logger.debug( "TOKEN-NAME : {} ", tokenName );

					logger.debug( "TOKEN-VALUE : {} ", tokenValue );



					String formTokenInput = String.format("<input type=\"hidden\" name=\"%s\" value=\"%s\" />"

							                                      + tokenStr +"</form>", tokenName, encryptedTokenValue );

					responseText = StringUtils.replace( responseText, "</form>", formTokenInput );



					response.getWriter().write( responseText );

				}

			}*/



		}

		else {

			chain.doFilter( request, response );

		}



	}



	@Override

	public void init( FilterConfig arg0 ) throws ServletException {

	}



	@Override

	public void destroy() {

	}



}
