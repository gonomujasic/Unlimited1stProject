<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min
.css">
<script type="text/javascript">
	//alert("개인정보 변경 페이지");
</script>
</head>
<body>
	<h2>비밀번호 확인</h2>
	<h4>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인합니다.</h4>
	<hr>
	<form action="./PersonalChkPwService.do"method="post">
		<input type="password" name="password" required placeholder="비밀번호 입력">
	
		<input type="submit" value=" 확인" class="btn btn-default">
	</form>
</body>
</html>