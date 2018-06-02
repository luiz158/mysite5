package com.cafe24.mysite5.service;

import com.cafe24.mysite5.domain.Guestbook;
import com.cafe24.mysite5.repository.GuestbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional //Service Layer에 하는 것이 바람직하다
public class GuestbookService{

	@Autowired
	private GuestbookRepository guestbookRepository;

	public List< Guestbook > getMessageList(){
		return guestbookRepository.findAllByOrderByRegDateDesc();
	}

	public List< Guestbook > getMessageList2(){
		return guestbookRepository.findAll( new Sort( Sort.Direction.DESC, "regDate" ) );
	}


	public void writeMessage( Guestbook guestbook ){
		/* Repository가 아닌 Service에서 시간 설정 */
		guestbook.setRegDate( new Date() );
		guestbookRepository.save( guestbook );
	}

	public void deleteMessage( Guestbook guestbook ){
		guestbookRepository.delete( guestbook );
	}


	public List< Guestbook > getMessageList( Long startNo ){
		PageRequest pageRequest = new PageRequest( 0, 5, new Sort( Sort.Direction.DESC, "regDate" ) ); //조회 시작 위치, 사이즈, 소팅
		Page< Guestbook > pages = guestbookRepository.findAllByStartNo( startNo, pageRequest );
		List< Guestbook > list = pages.getContent();
		//int totalPage = pages.getTotalPages();
		//boolean hasNext = pages.hasNext();
		return list;
	}


	public Guestbook addGuestbookPostUsingAjax( Guestbook guestbook ){
		guestbook.setRegDate( new Date() );
		return guestbookRepository.save( guestbook );
	}

	public boolean deleteGuestbookPost( Guestbook guestbook ){
		guestbookRepository.delete( guestbook );
		return !guestbookRepository.exists( guestbook.getNo() );
	}
}
