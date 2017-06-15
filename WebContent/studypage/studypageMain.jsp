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
	//지난 수강 내역클릭시 로 이동
	function goHistoryForm() {
		location.href = "MemberHistoryDetail.do";
	}
	function goPrevious() {
		history.back();
	}
	
</script>
</head>
<body>
	<div>
		<h2>학습 페이지 메인</h2>
		<input type="button" value="지난 수강 내역" onclick="goHistoryForm()" />
	</div>
		<input type="button" value="이전" onclick="goPrevious()">
</body>
</html>

