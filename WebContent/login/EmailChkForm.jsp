
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 20170612정해선 -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min
.css">
<script type="text/javascript">
	function NumChk() {
		var form = document.emailChk;

		if (!form.inputNum.value) {
			alert("인증번호를 입력하세용");
			return false;
		}

		if (form.inputNum.value != form.num.value) {
			alert("인증번호가 일치하지 않습니다. 다시 입력 해주세요");
			form.inputNum.value = "";
			history.go(-1);

			return false;
		}
		if (form.inputNum.value == form.num.value) {
			alert("인증완료");
			//	document.form.action="location.href='./inputNewPw.do'";
			document.emailChk.submit();
			//self.close();
		}
	}
</script>
<style type="text/css">
.posi {
	text-align: center;
}

.a {
	margin-left: 30px;
}
</style>
</head>

<body>
	<form action="./inputNewPw.do" method="post" class="posi"
		name="emailChk">
		<table>
			<h2>이메일 인증하기</h2>
			<h1></h1>
			<h4>입력하신 메일 주소로 인증번호가 발송 되었습니다.</h4>
			<h4>인증번호 6자리를 입력해주세요</h4>
			<tr>
				<td>인증번호</td>
				<td><input type="text" name="inputNum" id="inputNum"></td>
				<td><input type="submit" value="인증하기" onclick="NumChk()"
					class="btn btn-default"></td>
			</tr>
			<tr>
				<td><input type="hidden" id="num" name="num"
					value="${sessionScope.num}" /></td>
			</tr>

		</table>
	</form>

</body>
</html>