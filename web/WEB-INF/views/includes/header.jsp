<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<div id="header">
	<h1><a href="${pageContext.servletContext.contextPath }/main">MySite</a></h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<!-- GET 방식은 form으로, POST는 실제 Action으로 -->
				<li><a href="${pageContext.servletContext.contextPath }/user/auth">로그인</a></li>
				<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath }/user/modify">회원정보수정</a></li>
				<!-- 세션을 삭제하면 된다. -->
				<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
				<li>${authUser.name }님 안녕하세요 ^^;</li>
			</c:otherwise>
		</c:choose>
		
	</ul>
</div>
