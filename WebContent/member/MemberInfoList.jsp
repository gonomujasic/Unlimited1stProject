<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
/* ul li{ */
/* margin-left: 100px; */

/* } */
</style>
<script type="text/javascript">
	//포인트 클릭시 ~~~~로 이동
	//function goPointForm() {
	//	location.href = "MemberInfoForm.do";
	//}
	//학습페이지 클릭시 ~~~~로 이동
	//function goStudyPageForm() {
	//	location.href = "MemberInfoForm.do";
	//	}

	//멘토관리 클릭시 ~~~~로 이동
	//function goMentorPageForm() {
	//	location.href = "MentorInfoExistChkService.do";
	//	}

	//개인정보 관리 클릭시 PersonalForm.do로 이동
	//function goPersonalPageForm() {
	//	location.href = "PersonalChkForm.do";
	//	}
</script>
</head>
<body>
	<div>
		<fieldset>
			<ul class="nav nav-tabs">
				<h2>마이페이지</h2>

				<!-- 				<li class="active"><a href="./#">마이페이지</a></li> -->
				<li><a href="./PointMain.Point">포인트</a></li>
				<li><a href="./StudyPage.do">학습페이지</a></li>
				<li><a href="./MentorInfoExistChkService.do">멘토 관리</a></li>
				<li><a href="./PersonalChkForm.do">개인정보 관리</a></li>

			</ul>
		</fieldset>
		<!-- 		<table border=1px> -->
		<!-- 			<h2>마이페이지</h2> -->
		<!-- 			<tr> -->
		<!-- 				<td>프로필 사진</td> -->
		<!-- 				<td rowspan="6"></td> -->
		<!-- 			</tr> -->
		<!-- 			<tr> -->
		<!-- 				<td><input type="button" name="studyPage" -->
		<!-- 					onclick="goPointForm()" value="포인트"></td> -->
		<!-- 			</tr> -->
		<!-- 			<tr> -->
		<!-- 				<td><input type="button" name="studyPage" -->
		<!-- 					onclick="goStudyPageForm()" value="학습 페이지" class="pagination"></td> -->
		<!-- 			</tr> -->
		<!-- 			<tr> -->
		<!-- 				<td><input type="button" name="mentorPage" -->
		<!-- 					onclick="goMentorPageForm()" value="멘토 관리" class="pagination"></td> -->
		<!-- 			</tr> -->

		<!-- 			<tr> -->
		<!-- 				<td><input type="button" name="personalPage" -->
		<!-- 					onclick="goPersonalPageForm()" value="개인정보 관리" class="pagination"></td> -->
		<!-- 			</tr> -->
		<!-- 		</table> -->
	</div>
</body>
</html>