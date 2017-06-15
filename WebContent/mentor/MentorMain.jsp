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
	//멘토정보 수정 클릭시 MemberInfoForm.do로 이동
	function goMentoModifyForm() {
		location.href = "MentorModifyDetailService.do";
	}

	//지난 강의 내역클릭시 로 이동
	function goHistoryTeachForm() {
		location.href = "MentorHistoryTeachDetail.do";
	}
	function goPrevious() {
		history.back();
	}

</script>
</head>
<body>
	<fieldset>
		<ul class="nav nav-tabs">
			<h2>멘토 메인 페이지</h2>

			<li><a href="./MentorModifyDetailService.do">멘토 정보 수정</a></li>
			<li><a href="./MentorHistoryTeachDetail.do">지난 강의 내역</a></li>

		</ul>
	</fieldset>
<input type="button"
				value="이전" onclick="goPrevious()">
	<!-- 	<div> -->
	<!-- 		<h2>멘토 메인 페이지</h2> -->
	<!-- 		<input type="submit" value="멘토 정보 수정" onclick="goMentoModifyForm()"> -->
	<!-- 		<input type="button" value="지난 강의 내역" onclick="goHistoryTeachForm()" /> <input -->
	<!-- 			type="button" value="현재 강의 내역" onclick="goCurrentTeachForm()" /> -->
	<!-- 	</div> -->

</body>
</html>

