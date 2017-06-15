<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 20170606정해선 작성 -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min
.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
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
			alert("언어1는 한글 및 영문으로만 입력해주세요.");
			return false;
		}

	}

	// 취소 버튼 클릭시 첫화면으로 이동
	function goPrevious() {
		history.back();
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
	<div>
		<form action="./MentorAddService.do" method="post" name="userInfo"
			onsubmit="return checkValue()">
			<table class="table">
				<h2 class="header">멘토 정보 등록</h2>
				<tr>
					<td>국가 <strong class="strong">필수</strong></td>
					<td><input type="text" id="nation" name="nation"></td>

				</tr>
				<tr>
					<td>학습 언어 1 <strong class="strong">필수</strong></td>
					<td><input type="text" id="edu_language1" name="edu_language1"></td>
				</tr>
				<tr>
					<td>학습 언어 2</td>
					<td><input type="text" name="edu_language2"></td>
				</tr>
				<tr>
					<td>학습 언어 3</td>
					<td><input type="text" name="edu_language3"></td>
				</tr>


			</table>
			<input type="submit" value="멘토 정보 등록"> <input type="button"
				value="이전" onclick="goPrevious()" class="btn btn-default">
		</form>
	</div>
</body>
</html>