package com.cafe24.mysite5.controller;


import com.cafe24.mysite5.domain.Board;
import com.cafe24.mysite5.domain.Comment;
import com.cafe24.mysite5.domain.User;
import com.cafe24.mysite5.security.Auth;
import com.cafe24.mysite5.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping( "/board" )
public class BoardController{
	private final Logger logger = LoggerFactory.getLogger( BoardController.class );

	@Autowired
	private BoardService boardService;

	//------------------게시판 목록 가져오기----------------
	@RequestMapping( "/list" )
	public String list( @RequestParam( value = "page_no", required = false, defaultValue = "1" ) Long pageNo,
	                    @RequestParam( value = "flag", required = false, defaultValue = "false" ) Boolean flag,
	                    HttpSession session,
	                    Model model ){
		logger.info( "pageNo: " + pageNo );
		Page< Board > pages = boardService.getBoardList( pageNo, flag );
		List< Board > boards = pages.getContent();
		for ( Board board : boards ) {
			logger.info( "board of list in BoardController: " + board );
		}
		model.addAttribute( "pages", pages );
		return "board/list";
	}

	//-------------------글 내용 보기-----------------------------
	@RequestMapping( "/view" )
	public String view( @RequestParam( "board_no" ) String boardNo,
	                    Model model ){
		Map< String, Object > map = boardService.getBoardDetail( boardNo );
		model.addAttribute( "map", map );
		return "board/view";
	}


	//---------------------댓글 쓰기------------------------------
	/*@Auth
	@RequestMapping( value = "/comment", method = RequestMethod.POST )
	public String writeComment( @ModelAttribute Comment vo ){
		service.writeComment( vo );
		return "redirect:/board/view?board_no=" + vo.getBoard().getNo();
	}*/


	//-------------------- 댓글 삭제 ------------------------------
	@Auth
	@RequestMapping( value = "/commentdelete", method = RequestMethod.GET )
	public String deleteComment(){
		return "board/commentdelete";
	}

	/*@Auth
	@RequestMapping( value = "/commentdelete", method = RequestMethod.POST )
	public String deleteComment( String no, String password, String boardNo ){
		service.deleteComment( no, password );
		return "redirect:/board/view?board_no=" + boardNo;
	}*/


	//-------------------글 삭제하기---------------------------------
	@Auth
	@RequestMapping( value = "/delete", method = RequestMethod.GET )
	public String delete(){
		return "/board/delete";
	}

	/*@Auth
	@RequestMapping( value = "/delete", method = RequestMethod.POST )
	public String delete( String no,
	                      String password,
	                      HttpSession session ){
		System.out.println( "in BoardController delete no: " + no + ", password: " + password );
		service.deletePost( no, password );
		return "redirect:/board/list?page_no=" + session.getAttribute( "pageNo" );
	}
*/

	//-------------------- 글 수정하기 --------------------------
	@Auth
	@RequestMapping( value = "/modify", method = RequestMethod.GET )
	public String modify( @RequestParam( "board_no" ) String boardNo,
	                      Model model ){
		Map< String, Object > map = boardService.getBoardDetail( boardNo );
		model.addAttribute( "map", map );
		return "board/modify";
	}

	/*@Auth
	@RequestMapping( value = "/modify", method = RequestMethod.POST )
	public String modify( @ModelAttribute Board vo ){
		vo.setContent( vo.getContent().trim() );
		service.modifyBoard( vo );
		return "redirect:/board/view?board_no=" + vo.getNo();
	}*/


	//--------------------- 글쓰기 ------------------------
	@Auth
	@RequestMapping( value = "/write", method = RequestMethod.GET )
	public String write( HttpSession session ){
		User authUser = ( User ) session.getAttribute( "authUser" );
		if ( authUser == null ) {
			return "redirect:/user/login";
		}
		return "board/write";
	}

	//---------------------답글쓰기--------------------------
	@Auth
	@RequestMapping( "/reply" )
	public String reply( @ModelAttribute( "vo" ) Board vo,
	                     @ModelAttribute( "reply" ) Boolean reply ){
		return "board/write";
	}


	//-----------------글쓰기와 답글쓰기 함께 처리------------------------
	/*@RequestMapping( value = "/write", method = RequestMethod.POST )
	public String write( @RequestParam( value = "reply", required = false, defaultValue = "false" ) Boolean reply,
	                     Board vo,
	                     HttpSession session ){
		//페이지 번호를 세션에 저장하지 말고 그때마다 따라다니게 고쳐야 한다.
		Long pageNo = ( Long ) session.getAttribute( "pageNo" );
		//회원 번호 설정
		User authUser = ( User ) session.getAttribute( "authUser" );
		long userNo = authUser.getNo();
		vo.getUser().setNo( userNo );
		//답글 처리
		if ( reply ) {
			service.writeReply( vo );
			long no = service.getLatestNo();
			return "redirect:/board/view?board_no=" + no;
		}
		service.writeBoard( vo );
		return "redirect:/board/list?pageNo=" + pageNo;
	}*/
}
