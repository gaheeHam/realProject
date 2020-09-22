<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
header {
	/* background-color: gray; */
	
}

aside {
	/* background-color: blue; */
	width: 300px;
	float: left;
}

section {
	/* background-color: pink; */
	width: 1000px;
	float: left;
}
</style>
<body>
    <input type="hidden" name = "${_csrf.parameterName}" value="${_csrf.token}" />
	<header>
		<%-- <%@ include file="h2k5every_header.jsp"%><!-- 정적인 방식 --> --%>
		<jsp:include page="h2k5every_header.jsp" /><!-- 동적인 방식 -->
	</header>
	<aside>
		<jsp:include page="h2k5every_aside.jsp" />
	</aside>
	<section id="section">
		<jsp:include page="goAdminLoginPage.jsp" />
	</section>
	<footer>
		<jsp:include page="h2k5every_footer.jsp" />
	</footer>
</body>
</html>