package com.cafe24.mysite5.security;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class FormTokenResponseWrapper extends HttpServletResponseWrapper{

	/**
	 * Constructs a response adaptor wrapping the given response.
	 *
	 * @param response
	 * @throws IllegalArgumentException if the response is null
	 */
	public FormTokenResponseWrapper( HttpServletResponse response ){
		super( response );
	}
}
