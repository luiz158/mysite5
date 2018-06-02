<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="navigation">
	<ul>
		<!-- class='selected'로 이동한 페이지가 어디인지 표시 -->
	<c:choose>
		<c:when test='${param.menu == "main" }'>
			<c:choose> 
				<c:when test="${not empty authUser }">
					<li class='selected'><a href="${pageContext.servletContext.contextPath }/main">${authUser.name }</a></li>
				</c:when>
				<c:otherwise>
					<li class='selected'><a href="${pageContext.servletContext.contextPath }/main">MySite</a></li>
				</c:otherwise>
			</c:choose>
				<li><a href="${pageContext.servletContext.contextPath }/guestbook/list">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/board/list?page_no=1&flag=false">게시판</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/gallery">갤러리</a></li>
		</c:when>
		
		<c:when test='${param.menu == "guestbook" }'>
			<c:choose> 
				<c:when test="${not empty authUser }">
					<li><a href="${pageContext.servletContext.contextPath }/main">${authUser.name }</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.servletContext.contextPath }/main">MySite</a></li>
				</c:otherwise>
			</c:choose>
				<li class='selected'><a href="${pageContext.servletContext.contextPath }/guestbook/list">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/board/list?page_no=1&flag=false">게시판</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/gallery">갤러리</a></li>
		</c:when>
		
		<c:when test='${param.menu == "guestbook-ajax" }'>
			<c:choose> 
				<c:when test="${not empty authUser }">
					<li><a href="${pageContext.servletContext.contextPath }/main">${authUser.name }</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.servletContext.contextPath }/main">MySite</a></li>
				</c:otherwise>
			</c:choose>
				<li><a href="${pageContext.servletContext.contextPath }/guestbook/list">방명록</a></li>
				<li class='selected'><a href="${pageContext.servletContext.contextPath }/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/board/list?page_no=1&flag=false">게시판</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/gallery">갤러리</a></li>
		</c:when>
		
		<c:when test='${param.menu == "board" }'>
			<c:choose> 
				<c:when test="${not empty authUser }">
					<li><a href="${pageContext.servletContext.contextPath }/main">${authUser.name }</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.servletContext.contextPath }/main">MySite</a></li>
				</c:otherwise>
			</c:choose>
				<li><a href="${pageContext.servletContext.contextPath }/guestbook/list">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/guestbook/ajax">방명록(ajax)</a></li>
				<li class='selected'><a href="${pageContext.servletContext.contextPath }/board/list?page_no=1&flag=false">게시판</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/gallery">갤러리</a></li>
		</c:when>
		
		<c:when test='${param.menu == "gallery" }'>
			<c:choose> 
				<c:when test="${not empty authUser }">
					<li><a href="${pageContext.servletContext.contextPath }/main">${authUser.name }</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.servletContext.contextPath }/main">MySite</a></li>
				</c:otherwise>
			</c:choose>
				<li><a href="${pageContext.servletContext.contextPath }/guestbook/list">방명록</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/guestbook/ajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/board/list?page_no=1&flag=false">게시판</a></li>
				<li class='selected'><a href="${pageContext.servletContext.contextPath }/gallery">갤러리</a></li>
		</c:when>
		<c:when test='${param.menu == "user-modify"}'>
			<li><a href="${pageContext.servletContext.contextPath }/main">${authUser.name }</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/guestbook/list">방명록</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/guestbook/ajax">방명록(ajax)</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/board/list?page_no=1&flag=false">게시판</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/gallery">갤러리</a></
		</c:when>
	</c:choose>	
	</ul>
</div> 

