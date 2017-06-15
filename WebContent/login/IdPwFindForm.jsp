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

	
</script>
</head>
<body>
	<fieldset>

		<form action="./IdFindService.do" method="post">
			<table class="table">
				<h2>아이디 찾기</h2>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" required></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email"></td>

				</tr>
				<td><input type="submit" value="ID찾기" class="btn btn-default"></td>
			</table>
		</form>
	</fieldset>

	<fieldset>
		<form action="./PwFindService.do" method="post" >
			<table class="table">
				<h2>비밀번호 찾기</h2>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" required></td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" required></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" id="email" >
				</tr>
				<td><input type="submit" value="비밀번호 찾기" class="btn btn-default"></td>
			</table>
		</form>
	</fieldset>
</body>
</html>

	