package com.cafe24.mysite5.controller.api;


import com.cafe24.mysite5.domain.Guestbook;
import com.cafe24.mysite5.dto.JSONResult;
import com.cafe24.mysite5.service.GuestbookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("guestbookAPIController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	private final Logger logger = LoggerFactory.getLogger( GuestbookController.class );
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list( @RequestParam(value = "", required = true, defaultValue = "0") Long no) {
		System.out.println( "in guestbook ajax no: " + no );
		
		List<Guestbook > list = guestbookService.getMessageList( no );

		return JSONResult.success( list );
	}
	
	
	@ResponseBody
	@RequestMapping("/insert")
	public JSONResult insert( @RequestBody Guestbook guestbook ) {
		//@ModelAttribute와 RequestBody 충돌. 왜?????

		logger.info( "insert in GuestbookController using AJAX: " + guestbook );
		//데이터 삽입하고 no와 regTime이 있는 새로운 vo가 반환되어야 한다.
		Guestbook vo = guestbookService.addGuestbookPostUsingAjax( guestbook );
		return JSONResult.success( vo );
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete( Guestbook vo ) {
		boolean result = guestbookService.deleteGuestbookPost(vo);
		return JSONResult.success( result ? vo.getNo() : -1 );
	}
}
