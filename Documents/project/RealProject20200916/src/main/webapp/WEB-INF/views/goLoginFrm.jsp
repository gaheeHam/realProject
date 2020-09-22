<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
.menu{
    margin-left: 20px;
    text-align:center;
    width: 200px;
    border: 1px black solid;
    float: left;
}

</style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
<script>
$(document).ready
(function() {
	
	console.log('${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}');
	console.log('${sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities}');
	//var aut =JSON.parse("${sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities}");
	var aut= "${aut}";
	console.log(aut);
	var n = aut.indexOf("ROLE_PROF");
	console.log(n);

	 var id = "${id}";
	 console.log("hansnq3"+id);
	if (n == 1){
		$("#goTeacherPage").append("<p style='margin-top: auto;'><b><a href='/h2k5every/prof/goTeacherLoginFrm' style='color: white; '>강사페이지이동</a></b></p>");
	}
 
	test(id);
});
</script>
	<header>
		<%-- <%@ include file="h2k5every_header.jsp"%><!-- 정적인 방식 --> --%>
		<jsp:include page="h2k5every_loginHeader.jsp" /><!-- 동적인 방식 -->
	</header>
	<aside>
		<jsp:include page="h2k5every_aside.jsp" />
	</aside>
	<section id="section" style="margin-left: 20px;">
			<div><h3><b>추천강의</b></h3></div>
			<div style="width: 600px;height: 600px;  "></div>
			<div id="menu"><h3><b>관심사</b></h3></div>
			
	</section>
	<footer>
		<jsp:include page="h2k5every_footer.jsp" />
	</footer>
		
<script>
function test(id) {
	$.ajax({
		type:'get',
		url:'rest/selectConcern',
		datatype:'json',
		success: function (json) {
			console.log(json);
			console.log(json.length);
		
			for(var i=0; i<json.length; i++){
				$('#menu').append("<div class='menu'><p>"+json[i]+"</p></div>");
			}
			
		},
		error: function (err) {
			console.log(err);
		}
		
	});
} 
</script>
</body>
</html>