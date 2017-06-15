
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 20170607 정해선 -->
<!-- 아이디 중복확인 폼 -->
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
	
	//사용하기 클릭시 회원가입 창의 중복체크 상태를 바꿔주기 위한 함수
	function checkValue() {
		//	alert("CheckValue()");
		//idCheck 값을 전달
		opener.document.userInfo.idDuplication.value = "idCheck";
		//회원가입 창의 id 입력란에 사용할 아이디 값 전달
		opener.document.userInfo.id.value = document.getElementById("id").value;
		//값이 전달된 후에 중복체크 창을 닫음
		if (opener != null) {
			opener.chkForm = null;
			self.close();
		}
	}
	
</script>
</head>
<!-- 중복 체크 화면이 열리면 onload이벤트에 의해 pValue()라는 함수가 바로 호출된다 -->
<body >
	<form action="./IdCheckService.do" method="post" >
		<h2>아이디 중복 확인</h2>
<!-- 		넘겨온 url 주소의 값을 바로 셋팅해준다 -->
		<input type="text" name="id" id="id" value="${param.id}"><input type="submit"
			value="중복확인"  class="btn btn-default">
	</form>

<!-- 	중복확인 결과물 -->
		<c:if test="${!empty newId}">
			<span style="color: red;">사용 할 수 없는 아이디 입니다. 다른 아이디를 입력해 주세요</span>
		</c:if>
		<c:if test="${empty newId}">
			<span style="color: blue;">사용 가능한 아이디 입니다.</span>
			<br />
			<input type="submit" onclick="checkValue()" class="btn btn-default" value="사용하기">
		</c:if>
</body>
</html>