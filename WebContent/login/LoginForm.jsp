<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 발생오류 : The absolute uri: http://java.sun.com/jsp/jstl/core cannot be resolved in either web.xml or the jar files deployed with this application -->
<!-- 원인 :  라이브러리를 등록하지 않음 -->
<!-- 해결방안 : jstl 라이브러리 등록해줌 -->

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min
.css">
<script type="text/javascript">
	//회원 가입 클릭시 MemberRegist로 이동
	function goRegistForm() {
		location.href = "MemberRegist.do";
	}
	//ID/PW 찾기 클릭시 IdPwFindService로 이동
	function goIdPwFindForm() {
		location.href = "IdPwFind.do";
	}
</script>
<style type="text/css">
.a{
margin-top: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<form action="./LoginService.do" method="post">
			<h2>로그인</h2>
			<div>
				아이디 <input type="text" id="id" name="id" required
					placeholder="아이디 입력" class="form-control" >
			</div>
			<div>
				비밀번호 <input type="password" id="password" name="password" required
					placeholder="패스워드 입력" class="form-control">
				<div>
					<input type="submit" value="로그인" class="btn-primary a"><br />

					<input type="button" value="회원 가입" onclick="goRegistForm()"
						class="btn btn-default"/> <input type="button" value="ID/PW찾기"
						onclick="goIdPwFindForm()" class="btn btn-default" />
		</form>
	</div>

</body>
</html>

