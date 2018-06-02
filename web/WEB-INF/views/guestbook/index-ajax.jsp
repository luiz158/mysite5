<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<%
pageContext.setAttribute( "newLine", "\n" );
%>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
//jQuery plugin 만들기
//(function(){})은 여러개 있어도 상관없다.
(function ( $ ) {
	$.fn.hello = function () {
	    var $element = $( this );
		//hello라는 함수를 다 쓸 수 있게 된다
		//element의 특성을 비교하고 에러처리까지 할 필요 있다.
		//ui에 맞게 추가, 덮어쓰기 등을 한다.
		console.log( $element.attr( "id" ) + " :hello~ " );
    }
})( jQuery )

//var startNo = 0; -> 전역 변수 자제. <li> 태그에서 가져온다.
var isEnd = false;

//EJS 템플릿 가져오기
var ejsListItem = new EJS({
   url: "${pageContext.request.contextPath }/assets/js/ejs/template/listitem.ejs" //템플릿 url
});

var fetchList = function () {
    //마지막 자료면 버튼 클릭해도 통신 안함
    if( isEnd == true ){
        return;
    }

    //last()에서 배열이 비어있을 순 있어도 null은 될 수 없다.
    //data()에서 null이 나올 수 있다.
    var startNo = $( "#list-guestbook li" ).last().data( "no" ) || 0;
    $.ajax({
        url: "/mysite5/api/guestbook/list?no=" + startNo,
        type: "GET",
        dataType: "JSON",
        success: function (response) {
            //성공 유무
            if( response.result != "success" ){
                console.error( response.message );
                return;
            }

            //끝 감지
            if( response.data.length < 5 ){
                isEnd = true;
                //화면에 직접 넣을 수 없는 속성 disalbe
                $("#btn-fetch").prop( "disabled", true );
                return;
            }

            //render
            $.each( response.data, function( index, vo ){
                render( false, vo );
            } );



            //임시 처리
            var length = response.data.length;
            if( length > 0 ) {
                startNo = response.data[length - 1].no;
            }

        },
        error: function (xhr, e) {

        }
    });
}

var messageBox = function( title, message, callback ){
	$( "#dialog-message" ).attr( "title", title );
	$( "#dialog-message p" ).text( message );

	//다이얼로그 띄우기
	$( "#dialog-message" ).dialog({
		modal: true,
		buttons: {
			"확인": function() {
				$( this ).dialog( "close" );
			}
		},
		close: callback || function(){ /* callback이 null인 경우 */ }
	});
}

var render = function( mode, vo ){
    /* ejs 파일 안에서 EL이 안 먹히므로 객체를 생성하여 전달한다. */
    var o = {
        contextPath: "${pageContext.request.contextPath }",
		data: vo
	};
	//템플릿
	//vo의 필드를 그대로 꺼내서 쓴다. vo.no 필요없이 no만 적어도 된다.
	var html = ejsListItem.render( o );

	if( mode == true ){
		$("#list-guestbook").prepend( html );
	} else {
		$("#list-guestbook").append( html );
	}

	//다음 코드는 위의 if/else 코드와 같다.
	//연관 배열. 함수 속성은 연관배열 사용 가능. 
	//배열로 메소드 이름 부를 수 있다.
	//$("#list-guestbook")[ mode ? "prepend" : "append" ](html);
}


$(function () {
	//삭제시 비밀번호 입력 모달 다이얼로그
	var $deleteDialog = $( "#dialog-delete-form" ).dialog({
		autoOpen: false,
		height: 150,
		width: 350,
		modal: true,
		buttons: {
			"삭제": function(){
				//여기서 ajax 통신
				var $password = $("#password-delete").val();
				var $no = $("#hidden-no").val();

				//ajax 통신
				$.ajax({
					url: "/mysite5/api/guestbook/delete",
					type: "POST",
					data: "no=" + $no + "&password=" + $password,
					dataType: "JSON",
					success: function( response ){
						if( response.result == "fail" ){
							console.log( reponse.message );
							return;
						}

						if( response.data == -1 ){
							$(".validateTips.normal").hide();
							$(".validateTips.error").show();	
							$("#password-delete").val("");				
							return;
						}

						$("#list-guestbook li[data-no=" + response.data + "]").remove();
						$deleteDialog.dialog( "close" );
					}

				});
									
			},
			"취소": function() {
				$deleteDialog.dialog( "close" );
			}
		},
		close: function() {
			//close 후처리
			//비밀번호 지우기
			$("#password-delete").val("");
			$("#hidden-no").val("");
			$(".hidelidateTips.normal").show();
			$(".validateTips.error").hide();	
		}
	});

	
	//Live Event Listener
	//document에 걸어놓는다
	//click이 되면, 가만히 뒀다가  나중에 생길 html 태그를 찾아서 함수를 실행
	$(document).on( "click", "#list-guestbook li a", function( event ){

		//앵커 태그의 기본 동작 = hyper link로 이동. 이 기본 동작을 막는다
		event.preventDefault();
		
		var $no = $(this).data( "no" );
		console.log( $no );
		$("#hidden-no").val( $no );
		$deleteDialog.dialog( "open" );;
	} );

	
	//데이터를 바로 보낼 경우
	/* $("#add-form").submit( function( event ){
		event.preventDefault();
		//serialize -> uri encoding이 되어서 나온다. jQuery에서 제공하는 함수
		var $queryString = $(this).serialize();
		console.log( $queryString );
		$.ajax({
			url: "/mysite5/api/guestbook/insert",
			type: "POST",
			dataType: "JSON",
			data: $queryString,
			success: function( response ){
				render( true, response.data );
				//this를 쓰기 힘들다. this를 사용할 경우 어디에 데이터가 붙을지 모른다.
				//$("#add-form").get(0)
				$("#add-form")[0].reset();
			}
		});
	}); */

	//데이터를 JSON으로 보낼 경우
	$("#add-form").submit( function( event ){
		//form이 submit되는 동작을 막는다. 
		event.preventDefault();
		
		var data = {};

		//JSON으로 바꾸기
		//Jackson이 해주는 것의 반대로 
		$.each( $(this).serializeArray(), function( index, o ){
				data[ o.name ] = o.value;
		});
		
		//form의 validator가 따로 있다. 찾아서 테스트.
		if( data["name"] == '' ){
			//form 검증
			messageBox( "메시지 등록", "이름이 비어 있습니다.", function(){ $("#input-name").focus(); } );
			return;
		}

		if( data["password"] == '' ){
			messageBox( "메시지 등록", "비밀번호가 비어 있습니다.", function(){ $("#input-password").focus(); } );
			//alert( "비밀번호가 비어 있습니다." );
			return;
		}

		if( data["message"] == '' ){
			messageBox( "메시지 등록", "내용이 비어 있습니다.", function(){ $("#tx-content").focus(); } );
			//alert( "내용이 비어 있습니다." );
			return;
		}
		
		console.log( JSON.stringify( data ) );

		//네트워크를 돌아다니는 것은 JSON String
		//data를 JSON String으로 변환. javascript에서 지원하는 함수.
		//주고받는 데이터의 형식이 같으므로 이 방식 추천
		var $queryString = $(this).serialize();
		console.log( $queryString );
		$.ajax({
			url: "/mysite5/api/guestbook/insert",
			type: "POST",
			dataType: "JSON", //내가 받는 타입
			contentType: "application/json", //내가 보내는 타입
			data: JSON.stringify( data ),
			success: function( response ){
				console.log( "response: " + response );
				render( true, response.data );
				//this를 쓰기 힘들다. this를 사용할 경우 어디에 데이터가 붙을지 모른다.
				//$("#add-form").get(0)
				$("#add-form")[0].reset();
			}
		});
	});

		
	$("#btn-fetch").click(function () {
        fetchList();
    });

	//스크롤 위치 구하기
	$(window).scroll( function () {
		var $window = $(this);
		//스크롤 위치
		var scrollTop = $window.scrollTop();
		//화면에 보이는 높이
		var windowHeight = $window.height();
		var documentHeight = $( document ).height();

		//console.log( scrollTop + " : " + windowHeight + " : " + documentHeight );
		//scrollbar의 thumb가 바닥 전 30px까지 도달
		if( scrollTop + windowHeight + 30 > documentHeight ){
            fetchList();
		}
    });

	//임의의 플러그인 테스트
	//$( "#container" ).hello();


	var test = function () {
		var list = [];
		$.ajax({
			url: "",
			data: "",
			async: false,
			success: function ( response ) {
				list.push( response.item );
            }
		});

		return list;
    }

});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form">
					<input type="text" name="name" id="input-name" placeholder="이름">
					<input type="password" name="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" name="message" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
					<c:forEach var='guestbook' items="${guestbooks}" begin='0' step="1" varStatus="status">
						<li data-no='${guestbook.no }'>
							<strong class="guest-name">${guestbook.name }</strong>
							<p>
								${fn:replace(guestbook.message, newLine, "<br>") }
							</p>
							<strong>
								<img alt="삭제" src="${pageContext.request.contextPath }/assets/images/user.png"> 
							</strong> <!-- <light>? -->
							<!-- <a href='' data-no=''>삭제</a>  -->
							<a href='' data-no='${guestbook.no }'>
								<img alt="삭제" src="${pageContext.request.contextPath }/assets/images/delete.png"> 
							</a>
						</li>
					</c:forEach>
				</ul>
				<button id="btn-fetch">가져오기</button>
			</div>
			
			<!-- 삭제 dialog form -->
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
  					<!-- 의미적으로 form이라는 틀 안에 저장. -->
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>

			<!-- validation dialog form -->
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" >
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
	</div>
</body>
</html>