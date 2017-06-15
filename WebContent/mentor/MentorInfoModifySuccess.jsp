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
//멘토관리 클릭시 ~~~~로 이동
function goMentorPageForm() {
	location.href = "MentorInfoExistChkService.do";
}
</script>
</head>
<body>
<h2>멘토 정보 수정이 완료되었습니다.</h2>
<input type="button" onclick="goMentorPageForm()" value="멘토관리" class="btn btn-default">
</body>
</html>