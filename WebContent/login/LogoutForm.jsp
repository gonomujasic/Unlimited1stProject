<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min
.css">
<script type="text/javascript">
	//로그인 클릭시 LoginForm.do로 이동
// 	function goLoginForm() {
		top.location.href = "../main.jsp";
// 	}
</script>
</head>
<body>
<!-- 	<h2>로그아웃 되었습니다.</h2> -->
<!-- 	<br /> -->
<!-- <input type="button" value="로그인" onclick="goLoginForm()"> -->
</body>
</html>
