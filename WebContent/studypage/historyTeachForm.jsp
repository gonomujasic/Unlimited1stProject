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
function goPrevious() {
	history.back();
}
</script>
<style type="text/css">

</style>
</head>
<body>
<body>
	<div>

		<table class="table">
			<h2 class="header">지난 강의 내역</h2>

			<th>방 번호</th>
			<th>시작 시간</th>
			<th>종료 시간</th>
			<th>학습 언어</th>
			<th>방 제목</th>
			<th>멘티 아이디</th>
			<th>멘토 아이디</th>
			<c:forEach var="list" items="${studyPageList}" >
				<tr>
					<td><c:out value="${list.chat_index}" /></td>
					<td><c:out value="${list.start_time}" /></td>
					<td><c:out value="${list.end_time}" /></td>
					<td><c:out value="${list.edu_lang}" /></td>
					<td><c:out value="${list.chat_title}" /></td>
					<td><c:out value="${list.id}" /></td>
					<td><c:out value="${sessionScope.id}" /></td>
				</tr>
			</c:forEach>

		</table>
				<input type="button" value="이전" onclick="goPrevious()" class="btn btn-default">
</body>
</body>
</html>