<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 20170608작성 -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min
.css">
<script type="text/javascript">

//로그인 클릭시 LoginForm.do로 이동
function goLoginForm(){
		location.href = "LoginForm.do";
}

</script>
</head>
<body>

	<h2>
		회원 가입이 완료되었습니다. 
		로그인 해주세요.
	</h2>

	<input type="button" value="로그인" onclick="goLoginForm()" class="btn btn-default"/>
	
</body>
</html>