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
	//이전클릭시 MentorMainForm.do로 이동
function goPrevious() {
	history.back();
}

	function checkValue() {
		var form = document.userInfo;
		var check = /^[a-zA-Z가-힣]{1,20}$/;

		if (!form.nation.value) {
			alert("국가를 입력하세요.");
			return false;
		}
		if (check.test(form.nation.value) == false) {
			alert("국가는 한글 및 영문으로만 입력해주세요.");
			return false;
		}
		if (!form.edu_language1.value) {
			alert("최소 1개의 언어를 입력하세요");
			return false;
		}
		if (check.test(form.edu_language1.value) == false) {
			alert("언어는 한글 및 영문으로만 입력해주세요.");
			return false;
		}
		
	}
</script>
<style type="text/css">
.strong {
	color: red;
}

.header {
	text-align: center;
}
</style>
</head>
<body>
<body>
	<div>
		<form action="./MentorModifyService.do" method="post" name="userInfo"
			onsubmit="return checkValue()">
			<table class="table">
				<h2 class="header">멘토 정보 수정</h2>
				<h4 class="header">
					기본 정보는 <strong class="strong">개인정보수정</strong>에서 변경해주세요
				</h4>
				<tr>
					<td>아이디</td>
					<td><c:out value="${sessionScope.id}" /></td>

				</tr>


				<tr>
					<td>국가 <strong class="strong">필수</strong></td>
					<td><input type="text" id="nation" name="nation"
						value="<c:out value="${mentorDTO.nation}"/>" /></td>
				</tr>
				<tr>
					<td>학습 언어 1 <strong class="strong">필수</strong></td>
					<td><input type="text" id="edu_language1" name="edu_language1"
						value="<c:out value="${mentorDTO.edu_language1}"/>" /></td>
				</tr>
				<tr>
					<td>학습 언어 2</td>
					<td><input type="text" id="edu_language2" name="edu_language2"
						value="<c:out value="${mentorDTO.edu_language2}"/>" /></td>
				</tr>

				<tr>
					<td>학습 언어 3</td>
					<td><input type="text" id="edu_language3" name="edu_language3"
						value="<c:out value="${mentorDTO.edu_language3}"/>" /></td>
				</tr>

			</table>
			<input type="submit" value="확인" class="btn btn-default"> <input type="button"
				value="이전" onclick="goPrevious()" class="btn btn-default">
		</form>

	</div>
</body>
</body>
</html>