<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//마이페이지 클릭시 MemberInfoForm.do로 이동
function goMyInfoForm() {
	location.href = "MemberInfoForm.do";
}
</script>
</head>
<body>
<h2>회원 정보 수정이 완료되었습니다.</h2>
<input type="button" onclick="goMyInfoForm()" value="마이페이지" class="btn btn-default">
</body>
</html>