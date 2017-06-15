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
	//약관 동의 여부 확인
	function checkValue() {
		var form = document.userInfo;

		if (!form.agree[0].checked) {
			alert("약관에 동의하셔야 탈퇴가 진행됩니다.");
			return false;
		}
	}

	//취소 버튼 클릭시 메인화면으로 이동
	function goMainForm() {
		location.href = "MainForm.do";
	}
</script>
<style type="text/css">
.strong {
	color: red;
}

.a {
	margin-top: 10px;
}
</style>
</head>
<body>
	<h2>회원 탈퇴</h2>
	<form action="./MemberDeleteService.do" method="post"
		onsubmit="return checkValue()" name="userInfo">
		<table class="table">
			<tr>
				<td class="info"><strong>사용하고 계신 아이디 <strong
						class="strong">"<c:out value="${loginDTO.id}" />"
					</strong>는 탈퇴할 경우 재사용 및 복구가 불가능합니다.
				</strong></td>
			</tr>
			<tr>
				<td class="info"><strong>탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두
						삭제됩니다. </strong></td>
			</tr>
			<tr>
				<td>삭제될내용들 ~~~~~</td>
			</tr>
			<tr>
				<td class="info"><strong>탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로
						남아 있습니다.. </strong></td>
			</tr>
			<tr>
				<td>예를들어 Q&A</td>
			</tr>
			<tr>
				<td class="info"><strong>멘토 회원일 경우 멘토 정보와 일반 회원 탈퇴가
						동시에 이루어집니다. </strong></td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td><input type="radio" value="동의" name="agree">안내 사항을
					모두 확인하였으며, 이에 동의합니다.</td>
			</tr>
		</table>
		<input type="submit" value="확인" class="btn btn-default" > <input
			type="button" value="취소" onclick="goMainForm()" class="btn btn-default">
	</form>

</body>
</html>