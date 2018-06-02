package com.cafe24.mysite5.controller.api;


import com.cafe24.mysite5.dto.JSONResult;
import com.cafe24.mysite5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//API 패키지에 있으므로 굳이 API를 붙이지 않아도 된다
@Controller("userAPIController")
@RequestMapping("/api/user")
public class UserController {
	
	//dao 통해 DB에서 체크
	//싱글톤으로 생성되어 주입.
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail( @RequestParam(value="email",
												required=true, 
												defaultValue="") String email ) {
		//이 중간에서 익셉션 발생시 GlobalException으로 전달된다.
		//api의 exception의 경우에는 분리를 해야 한다.
		//request에서 JSON 요청인지 WEB 요청인지 구별해서 accept 필드에서 받을 수 있는 값을 설정. 
		boolean result = userService.isExistEmail( email );
		//JSON 통신을 할 때는 반드시 브라우저상에서 JSON 문자열이 제대로 출력되는지 확인하고 자바스크립트에서 사용해야 한다.
		return JSONResult.success( result ? "not exist" : "exist" );
	}
	
}
