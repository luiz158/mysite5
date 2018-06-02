package com.cafe24.mysite5.repository;

import com.cafe24.mysite5.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository< Board, Long >{

	@Query( "SELECT b FROM Board b WHERE b.no > :startNo AND b.deletion = 'N'" )
	Page< Board > findAllByStartNo( @Param( "startNo" ) Long startNo, Pageable pageable );
}
