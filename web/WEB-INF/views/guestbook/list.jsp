<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String newLine = "\n";
pageContext.setAttribute( "newLine", newLine );
%>
<%@page import="java.time.LocalDateTime"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.servletContext.contextPath }/guestbook/add" method="post">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea cols="50" name="message"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
				<c:set var='length' value='${fn:length(guestbooks)}'/>
				<c:forEach var='guestbook'  items='${ guestbooks }' begin='0' end='${length}' step='1' varStatus='status' >
					<li>
						<table>
							<tr>
								<!-- 글번호  -->
								<td>[${length - status.index }]</td>
								<!-- 작성자명  -->
								<td>${guestbook.name }</td>
								<!-- 작성 시간  -->
								<td>${guestbook.regDate }</td>
								
								<td><a href="${pageContext.servletContext.contextPath }/guestbook/delete/${ guestbook.no }">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
									${fn:replace(guestbook.message, newLine, "<br/>") }
								</td>
							</tr>
						</table>
						<br>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value='guestbook'></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>