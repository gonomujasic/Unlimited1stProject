
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
	function checkValue() {

		var form = document.pwInfo;
		var password_check = /^[a-zA-Z0-9]{8,64}$/;

		if (!form.password.value) {
			alert("현재 비밀번호를 입력하세요.");
			return false;
		}
		if (!form.newPassword.value) {
			alert("변경할 비밀번호를 입력하세요.");
			return false;
		}
		if (!form.newPasswordChk.value) {
			alert("비밀번호 확인을 입력하세요.");
			return false;
		}

		if (password_check.test(form.password.value) == false) {
			alert("비밀번호는 영문, 숫자를 이용하여 8자 이상 입력해주세요.");
			return false;
		}

		// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
		if (form.newPassword.value != form.newPasswordChk.value) {
			alert("새로운 비밀번호를 동일하게 입력하세요.");
			return false;
		}

	}
</script>
</head>
<body>
	<form action="./PwChangeService.do" method="post" name="pwInfo"
		onsubmit="return checkValue()">
		<table>
			<h2>비밀번호 변경하기</h2>
			<tr>
				<td>현재 비밀번호</td>
				<br />
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>변경할 비밀번호</td>
				<br />
				<td><input type="password" name="newPassword"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<br />
				<td><input type="password" name="newPasswordChk"></td>

				<td><input type="submit" value="변경하기"
					class="btn btn-default"></td>
			</tr>
		</table>
	</form>
</body>
</html>