<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt' %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<c:set var='boards' value='${pages.content}'/>
<c:set var='totalPageNumber' value='${pages.totalPages }'/>
<c:set var='boardNumber' value='${pages.numberOfElements}'/>
<c:set var='start' value='${pages.number}'/>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp">
			<c:param name="menu" value='board'/>
		</c:import>
		<div id="content">
			<div id="board">
				<!-- 검색 폼 -->
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/search" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>		
					<c:set var='length' value='${fn:length(boards)}'/>
					<c:forEach items='${boards }'
								begin='0' 
								var='board'
								end='${length}' 
								step='1' 
								varStatus='status'>
						<c:set var='i' value='${i+1 }'/>
						<c:set var='depth' value='${20 * board.depth}'/>
						<tr>
							<td>${boardNumber - i }</td>
							<td style='text-align: left; padding-left: ${depth}px' >
								<c:if test="${depth > 0 }">
									<img alt="" src="${pageContext.servletContext.contextPath }/assets/images/reply.png">
								</c:if>
								<c:choose>
									<c:when test='${board.deletion == "Y" }'>
										${board.title }
									</c:when>
									<c:otherwise>
										<a href="${pageContext.servletContext.contextPath }/board/view?board_no=${board.no }">${board.title }</a>
									</c:otherwise>
								</c:choose>
								
							</td>
							<td>${board.userName}</td>
							<td>${board.hits }</td>
							<td>${board.regTime }</td>
							<c:if test="${authUser.no == board.userNo && authUser.name == board.userName }">
								<td>
									<a href="${pageContext.servletContext.contextPath }/board/delete?no=${board.no }" class="del">
										<img alt="" src="${pageContext.servletContext.contextPath }/assets/images/recycle.png">
									</a>
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				
				<!-- 페이징 부분 -->
				<div class="pager">
					<ul>
						<%--<c:forEach var='i' begin='${(start * 5) - 4 }' end='${totalPageNumber }' step='1' varStatus='status'>
							<c:if test="${not flag}">
								<c:if test="${start > 1 && i % 5 == 1}">
									&lt;%&ndash; <c:if test="${start == (totalPageNumber / 10) + 1 }">
										<li><a href="${pageContext.servletContext.contextPath }/board?page_no=${i + 1 }&flag=${flag}">&nbsp;◀◀</a></li>
									</c:if> &ndash;%&gt;
									<li><a href="${pageContext.servletContext.contextPath }/board/list?page_no=${i - 1 }">◀</a></li>
								</c:if>
								
								<c:choose>
									<c:when test="${i == pageNo }">
										<li class='selected'><a href="${pageContext.servletContext.contextPath }/board/list?page_no=${i}">${i}</a></li>
										<c:set var='track_page' value='${i}'/>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.servletContext.contextPath }/board/list?page_no=${i}">${i}</a></li>
									</c:otherwise>
								</c:choose>
								
								<c:if test="${(status.count % 5) == 0 }">
									<c:set var='flag' value='true'/>
									<li><a href="${pageContext.servletContext.contextPath }/board/list?page_no=${i + 1 }&flag=${flag}">▶</a></li>
									&lt;%&ndash; <c:if test="${start == 1}">
										<li><a href="${pageContext.servletContext.contextPath }/board/list?page_no=${i + 1 }&flag=${flag}">&nbsp;▶▶</a></li>
									</c:if> &ndash;%&gt;
								</c:if>
							</c:if>
						</c:forEach>--%>
					</ul>
				</div>				
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value='board'/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp">
			<c:param name="menu" value='board'/>
		</c:import>
	</div>
</body>
</html>