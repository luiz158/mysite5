<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!DOCTYPE html>
<html>
<c:set var='boardVo' value='${map.boardVo }'/>
<c:set var='commentList' value='${map.commentList }'/>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
<link href="${pageContext.servletContext.contextPath }/assets/css/comment.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp">
			<c:param name="menu" value='board' />
		</c:import>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label"></td>
						<td>${boardVo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(boardVo.content, newLine, '<br/>') }</div>
						</td>
					</tr>
				</table>

				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href='${pageContext.servletContext.contextPath }/board/reply?gNo=${boardVo.gNo }&oNo=${boardVo.oNo}&depth=${boardVo.depth}&reply=true'>답글쓰기</a>
					</c:if>

					<a href="${pageContext.servletContext.contextPath }/board/list?page_no=${sessionScope.pageNo }">글목록</a>

					<c:if test="${authUser.no == boardVo.userNo && authUser.name == boardVo.userName }">
						<a href="${pageContext.servletContext.contextPath }/board/modify?board_no=${boardVo.no }">글수정</a>
					</c:if>
				</div>
				
				<div id="container">
					<div id="comment">
						<c:if test="${not empty authUser }">
							<form action="${pageContext.servletContext.contextPath }/board/comment" method="post">
								<input type="hidden" name='boardNo' value='${boardVo.no }'/>
								<input type="hidden" name='usersNo' value='${authUser.no }'/>
								<table>
									<tr>
										<td>이름</td>
										<td>${authUser.name }</td>
									</tr>
									<tr>
										<td colspan=4><textarea name="content" id="content"></textarea></td>
									</tr>
									<tr>
										<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
									</tr>
								</table>
							</form>
						</c:if>
						<c:set var='length' value='${fn:length(commentList) }'/>
						<c:forEach items='${commentList }' begin='0' var='commentVo'
									end='${length}' step='1' varStatus='status'>
							<c:if test="${cmntVo.no != 0}">
							<ul>
								<li>
									<table>
										<tr>
											<td>[${status.index + 1}]</td>
											<td>${commentVo.name }</td>
											<td>${commentVo.regTime }</td>
											<c:if test='${commentVo.deletion != "Y" }'>
												<td><a href="${pageContext.servletContext.contextPath }/board/commentdelete?no=${ commentVo.no }&boardNo=${boardVo.no}">삭제</a></td>
											</c:if>
										</tr>
										<tr>
											<td colspan=4>${fn:replace(commentVo.content, newLine, "<br/>") }
											</td>
										</tr>
									</table> <br>
								</li>
							</ul>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>



		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value='board' />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp">
			<c:param name="menu" value='board' />
		</c:import>
	</div>
</body>
</html>