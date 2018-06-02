package com.cafe24.mysite5.service;

import com.cafe24.mysite5.domain.Board;
import com.cafe24.mysite5.enumeration.Deletion;
import com.cafe24.mysite5.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService{
	private final Logger logger = LoggerFactory.getLogger( BoardService.class );

	@Autowired
	private BoardRepository boardRepository;


	public Page<Board> getBoardList( Long pageNo, Boolean flag ){
		int page = (int) (pageNo - 1);
		logger.info( "page: " + page );
		PageRequest pageRequest = new PageRequest(  page, 10, new Sort( Sort.Direction.DESC, "regTime" ) );
		Page< Board > pages = boardRepository.findAllByStartNo( pageNo, pageRequest );
		logger.info( "boards size: " + pages.getContent().size() );

		boardRepository.getOne( pageNo );

		return pages;

	}

	public Map< String, Object > getBoardDetail( String boardNo ){
		return null;
	}
}
