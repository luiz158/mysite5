package com.cafe24.mysite5.controller;

import com.cafe24.mysite5.domain.Guestbook;
import com.cafe24.mysite5.service.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping( "/guestbook" )
public class GuestbookController{

	@Autowired
	private GuestbookService guestbookService;


	@RequestMapping( value = { "", "/list" }, method = RequestMethod.GET )
	public String index( Model model ){
		/* Guestbook은 영속화된 객체. Controller부터 JSP까지 영속화된 객체. */
		List< Guestbook > guestbooks = guestbookService.getMessageList();
		model.addAttribute( "guestbooks", guestbooks );

		return "guestbook/list";
	}

	@RequestMapping( "/add" )
	public String add( @ModelAttribute Guestbook guestbook ){
		guestbookService.writeMessage( guestbook );
		return "redirect:/guestbook";
	}

	@RequestMapping( value = "/delete/{no}", method = RequestMethod.GET )
	private String delete( @PathVariable( "no" ) Long no, Model model ){
		model.addAttribute( "no", no );
		return "guestbook/delete";
	}

	@RequestMapping( value = "/delete", method = RequestMethod.POST )
	private String delete( @ModelAttribute Guestbook guestbook ){
		guestbookService.deleteMessage( guestbook );
		return "redirect:/guestbook";
	}

	@RequestMapping("/ajax")
	public String ajax( Model model ) {
		List<Guestbook> guestbooks = guestbookService.getMessageList( 0L );
		model.addAttribute( "guestbooks", guestbooks );
		return "guestbook/index-ajax";
	}

}
