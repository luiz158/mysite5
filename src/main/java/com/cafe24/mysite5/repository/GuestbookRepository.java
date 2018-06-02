package com.cafe24.mysite5.repository;

import com.cafe24.mysite5.domain.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestbookRepository extends JpaRepository< Guestbook, Long >{

	List< Guestbook > findAllByOrderByRegDateDesc();

	boolean deleteByNoAndPassword( Long no, String password );

	@Query( "SELECT gb FROM Guestbook gb WHERE gb.no > :startNo" ) //pageable을 넣어주므로 ORDER BY gb.regDate 생략 가능
	Page< Guestbook > findAllByStartNo( @Param( "startNo" ) Long startNo, Pageable pageable );
}
