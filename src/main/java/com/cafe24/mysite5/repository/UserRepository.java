package com.cafe24.mysite5.repository;

import com.cafe24.mysite5.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository< User, Long >{

	/* 이름에 민감하므로 오타가 없어야 한다. */
	User findByEmailAndPassword( String email, String password );

	/* get은 프록시를 조회 */
	//User getByEmail( String email );

	User findByEmail( String email );

	/*벌크성 UPDATE, DELETE 쿼리 */
	//@Modifying
	//@Query( value = "UPDATE User u SET u.name = :name WHERE u.no = :no", nativeQuery = false ) //nativeQuery = false 생략 가능
	//Integer update( @Param( "name" ) String name, @Param( "no" ) Long no );

	/* bean으로 만들어서 수정 */
	@Modifying
	@Query( value = "UPDATE User u SET u.name = :#{#user.name}, u.gender = :#{#user.gender} WHERE u.no = :#{#user.no}", nativeQuery = false )
	int update( @Param( "user" ) User user );

}
