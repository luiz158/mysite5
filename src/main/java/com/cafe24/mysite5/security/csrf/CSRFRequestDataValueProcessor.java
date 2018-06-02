package com.cafe24.mysite5.security.csrf;

import org.springframework.web.servlet.support.RequestDataValueProcessor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CSRFRequestDataValueProcessor implements RequestDataValueProcessor{

	/* response의 hidden 값으로 CSRF 토큰을 전달*/
	@Override
	public Map<String, String> getExtraHiddenFields( HttpServletRequest request) {
		Map<String,String> hiddenFields = new HashMap<>();
		hiddenFields.put(CSRFTokenManager.CSRF_PARAM_NAME,
		                 CSRFTokenManager.getTokenForSession(request.getSession()));
		System.out.println("in CSRFRequestDataValueProcessor CSRF_PARAM_NAME: " + CSRFTokenManager.CSRF_PARAM_NAME);
		System.out.println("CSRFRequestDataValueProcessor");
		return hiddenFields;
	}

	@Override
	public String processAction( HttpServletRequest arg0, String arg1, String httpMethod ){
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public String processFormFieldValue(HttpServletRequest arg0, String arg1,
	                                    String arg2, String arg3) {
		// TODO Auto-generated method stub
		return arg2;
	}

	@Override
	public String processUrl(HttpServletRequest arg0, String arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}
}
