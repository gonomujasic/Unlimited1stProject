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
	//마이페이지 클릭시 MemberInfoForm.do로 이동
	function goMyInfoForm() {
		location.href = "MemberInfoForm.do";
	}

	//로그아웃 클릭시LogoutForm.do로 이동
	function goLogoutForm() {
		location.href = "LogoutForm.do";
	}
	top.location.href="/Unlimited1stProject/main.jsp"
</script>
</head>
<body>
<!-- 	<div> -->
<!-- 		<h1> -->
<!-- 			환영합니다 -->
<%-- 			<c:out value="${sessionScope.name}"/>님 --%>
<!-- 		</h1> -->
<!-- 		<h2>원하는 메뉴를 선택하세요</h2> -->
<!-- 		<input type="submit" value="마이페이지" onclick="goMyInfoForm()"class="btn btn-default"> <input -->
<!-- 			type="button" value="로그아웃" onclick="goLogoutForm()" class="btn btn-default"/> -->
<!-- 	</div> -->

</body>
</html>

