package com.cafe24.mysite5.security;

import com.cafe24.mysite5.dto.MyPrincipal;
import com.cafe24.mysite5.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	private final Logger logger = LoggerFactory.getLogger( CustomAuthenticationProvider.class );

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate( Authentication authentication ) throws AuthenticationException{
		/* 사용자 입력 정보 -> 아이디 및 비번으로 만들기 */
		UsernamePasswordAuthenticationToken upaToken = (UsernamePasswordAuthenticationToken) authentication;

		logger.info( "upaToken.getName(): " + upaToken.getName() );
		logger.info( "upaToken.getCredentials(): " + upaToken.getCredentials() );
		logger.info( "upaToken.getPrincipal(): " + upaToken.getPrincipal() );
		MyPrincipal target = ( MyPrincipal ) userDetailsService.loadUserByUsername( upaToken.getName() );
		logger.info( target.toString() );
		logger.info( target.getUsername() );
		if ( target == null ) {
			logger.info( "이름에 해당하는 유저 정보 없음" );
		}
		if ( upaToken == null ) {
			logger.info( "upaToken == null" );

		}
		logger.info( "encodedPwd: " + passwordEncoder.encode( upaToken.getCredentials().toString() ));

//		if ( !BCrypt.checkpw( upaToken.getCredentials().toString(), target.getPassword() ) ) {
		if ( !passwordEncoder.matches( upaToken.getCredentials().toString(), target.getPassword() ) ) {

			throw new BadCredentialsException( "비밀번호가 들립니다." );
		} else {
			logger.info( "비밀번호가 같습니다." );
		}

		return new UsernamePasswordAuthenticationToken( target, null, null );
	}

	@Override
	public boolean supports( Class< ? > authentication ){
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom( authentication );
	}
}
