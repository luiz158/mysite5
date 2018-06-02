<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<div id="content">
			<div id="comment" class="delete-form">
				<form method="post" action="${pageContext.servletContext.contextPath }/board/commentdelete">
					<input type='hidden' name="no" value="${param.no }">
					<input type='hidden' name="boardNo" value="${boardNo }">
					<label>비밀번호</label>
					<ul><li>${cmntNo }</li></ul>
					<input type="password" name="password">
					<input type="submit" value="확인">
				</form>
				<a href="${pageContext.servletContext.contextPath }/board?page_no=${sessionScope.pageNo }">게시판 리스트</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>

</body>
</html>