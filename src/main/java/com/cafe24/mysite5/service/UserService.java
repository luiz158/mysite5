package com.cafe24.mysite5.service;


import com.cafe24.mysite5.domain.Role;
import com.cafe24.mysite5.domain.User;
import com.cafe24.mysite5.enumeration.Enabled;
import com.cafe24.mysite5.repository.RoleRepository;
import com.cafe24.mysite5.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService{

	private final Logger logger = LoggerFactory.getLogger( UserService.class );


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public Boolean join(User user) {
		Role role = roleRepository.findByName( "USER" );
		Set< Role > roles = new HashSet<>();
		roles.add( role );
		Boolean result = false;
		user.setJoinDate( new Date(  ) );
		/* 비번 인코딩 */
		user.setPassword( passwordEncoder.encode( user.getPassword() ) );
		user.setRoles( roles );
		user.setEnabled( Enabled.True );
		User newUser = userRepository.save( user );
		if ( newUser != null ) {
			result = true;
		}
		return result;
	}


	//서비스 계층에서는 이름을 확실하게 정의해주는 것이 좋다.
	public User getUserByEmailandPassword(User user) {
		return userRepository.findByEmailAndPassword( user.getEmail(), user.getPassword() );
	}

	public Boolean isExistEmail( String email ){
		//User user = userRepository.getByEmail( email );
		User user = userRepository.findByEmail( email );
		logger.info( "user inExistEmail: " + user );
		return user == null;
	}


	public User getUser( Long no ) {
		return userRepository.getOne( no );
	}

	public boolean modify( User user ){
		/* 1. 영속화 시켜서 값을 바꾸기 */
		User targetUser = userRepository.findOne( user.getNo() );
		targetUser.setEmail( user.getEmail() );

		/* 2. 준영속 후 merge도 가능하다 */

		/* 3. @Query 및 JPQL 사용한 update 메소드 만들기 */
		return userRepository.update( user ) == 1;
	}

	public User getUserByEmail( String email ){
		logger.info( "email: " + email );
		User user = userRepository.findByEmail( email );
		logger.info( user.toString() );
		return userRepository.findByEmail( email );
	}
}
