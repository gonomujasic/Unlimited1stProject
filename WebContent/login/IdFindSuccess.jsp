<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	function goLoginForm() {
		location.href = "LoginForm.do";
	}
	//비밀번호 찾기 클릭시 LoginForm.do로 이동
	function goPwFindForm() {
		location.href = "IdPwFind.do";
	}
</script>
</head>
<body>

	<h3>
		<c:out value="${loginDTO.name}" />
		님의 아이디는
		<c:out value="${loginDTO.id}" />
		입니다.
	</h3>
	<input type="button" value="로그인" onclick="goLoginForm()" class="btn btn-default">
	<input type="button" value="비밀번호찾기" onclick="goPwFindForm()" class="btn btn-default">

</body>
</html>