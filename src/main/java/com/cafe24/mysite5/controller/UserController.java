package com.cafe24.mysite5.controller;

import com.cafe24.mysite5.domain.User;
import com.cafe24.mysite5.security.Auth;
import com.cafe24.mysite5.security.AuthUser;
import com.cafe24.mysite5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping( "/user" )
public class UserController{

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join( @ModelAttribute("user") User user ){
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join( @ModelAttribute("user") @Valid User user,
	                    BindingResult bindingResult ){
		if ( bindingResult.hasErrors() ) {
			return "user/join";
		}
		Boolean result = userService.join( user );
		return result == true ? "redirect:/" : "redirect:/user/join";
	}

	//회원가입 성공
	@RequestMapping("/joinsuccess")
	public String joinsucces( ) {
		return "user/joinsuccess";
	}

	//로그인
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@ModelAttribute("user") User user) {
		return "user/login";
	}

	//interceptor에서 @Auth 어노테이션에 대한 해석이 필요.
	@Auth
	@RequestMapping( value = "/modify", method = RequestMethod.GET )
	public String modify(){
		return "user/modify";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify( @AuthUser User authUser, @ModelAttribute User user) {
		if (userService.modify( user )) {
			authUser.setName( user.getName() );
		}
		return "redirect:/user/modify";
	}

}
