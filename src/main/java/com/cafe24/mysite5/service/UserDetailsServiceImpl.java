package com.cafe24.mysite5.service;

import com.cafe24.mysite5.domain.User;
import com.cafe24.mysite5.dto.MyPrincipal;
import com.cafe24.mysite5.enumeration.Enabled;
import com.cafe24.mysite5.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private static final Logger logger = LoggerFactory.getLogger( UserDetailsServiceImpl.class );

	@Autowired
	private UserRepository userRepository;
//	private UserService userService;

	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername( String email )
			throws UsernameNotFoundException{
		/*ApplicationContext ac
				= WebApplicationContextUtils
				.getWebApplicationContext( request.getServletContext() );
		*//* 타입을 주면 해당 타입으로 리턴하고, id를 주면 Object 타입을 리턴 *//*
		UserService userService = ac.getBean( UserService.class );*/
		logger.info( "email: " + email );
		/* 해당하는 사용자 가져오기 */
		if ( userRepository == null ) {
			logger.info( "userRepository == null" );
		}
		User target = userRepository.findByEmail( email );
		if ( target == null ) {
			throw new UsernameNotFoundException( "email: " + email );
		}
		logger.info( target.toString() );
		/*UserBuilder userBuilder = null;
		if ( target != null ) {
			target.setPassword( encodedPwd );
			userBuilder = org.springframework.security.core.userdetails.User.withUsername( email );
			userBuilder.password( passwordEncoder.encode( target.getPassword() ) );
			userBuilder.roles( String.valueOf( null ) );
		} else {
			throw new UsernameNotFoundException( "email: " + email );
		}*/

		return new MyPrincipal( target );
	}
}
