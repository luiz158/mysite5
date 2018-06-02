package com.cafe24.mysite5.service;


import com.cafe24.mysite5.domain.User;
import com.cafe24.mysite5.enumeration.Role;
import com.cafe24.mysite5.enumeration.Withdraw;
import com.cafe24.mysite5.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserService{

	private final Logger logger = LoggerFactory.getLogger( UserService.class );


	@Autowired
	private UserRepository userRepository;

	
	public Boolean join(User user) {
		Boolean result = false;
		user.setRole( Role.USER );
		user.setJoinDate( new Date(  ) );
		user.setWithdraw( Withdraw.N );
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
}
