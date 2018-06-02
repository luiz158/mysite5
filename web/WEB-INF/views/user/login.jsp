<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp">
			<c:param name="menu" value='main' />
		</c:import>
		<div id="content">
			<div id="user">
				<form:form modelAttribute="user" id="login-form" name="loginform" method="post" action="${pageContext.servletContext.contextPath }/user/auth">
					<input type="hidden"
						   name="${_csrf.parameterName}"
						   value="${_csrf.token}" />
					<label class="block-label" for="email">이메일</label>
					<form:input path="email" type="text" value="${email}"/>
					<label class="block-label" >패스워드</label>
					<form:password path="password" value=""/>
					<c:if test='${requestScope.result == "fail" }'>
						<p>
							로그인이 실패 했습니다.
						</p>
					</c:if>
					<input type="submit" value="로그인">
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value='main' />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp">
			<c:param name="menu" value='main' />
		</c:import>
	</div>
</body>
</html>