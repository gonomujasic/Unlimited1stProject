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

function checkValue() {
	//alert("checkValue");
	var form = document.userInfo;

	//이름 및 닉네임 유효성검사 : 한글 및 영문으로 최소2글자 작성
	var name_check = /^[a-zA-Z가-힣]{2,10}$/;
	//전화번호는-를 포함
	var hp_check =/^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})$/;
	//이메일 유효성검사
	var email_check = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
	if (!form.name.value) {
		alert("이름을 입력하세요.");
		return false;
	}
	if (name_check.test(form.name.value) == false) {
		alert("이름은 한글 및 영문으로만 입력해주세요.");
		return false;
	}
	if (!form.nickName.value) {
		alert("닉네임을 입력하세요.");
		return false;
	}

	if (!form.birth.value) {
		alert("생년월일을 입력하세요.");
		return false;
	}

	if (isNaN(form.birth.value)) {
		alert("생년월일은 숫자만 입력가능합니다.");
		return false;
	}
	if (!form.hp.value) {
		alert("핸드폰 번호를 입력하세요.");
		return false;
	}
	if (hp_check.test(form.hp.value) == false) {
		alert("핸드폰 입력 형식에 어긋납니다.  ");
		return false;
	}
	if (!form.email.value) {
		alert("이메일을 입력하세요.");
		return false;
	}
	if (email_check.test(form.email.value) == false) {
		alert("이메일 형식에 어긋납니다.  ");
		return false;
	}

	

}


	//회원탈퇴  클릭시 MemberDeleteForm.do로 이동
	function goMemberDeleteForm() {
		location.href = "MemberDeleteForm.do";
	}
	//취소 클릭시 마이페이지로 이동
	function goMyInfoForm() {
		location.href = "MemberInfoForm.do";
	}
	//비밀번호 변경 클릭시 변경 창으로 이동
	function openPwChange() {
		window.name = "parentForm";
		window.open("./ChangePwForm.do", "ChangeForm",
				"width=500, height=300, resizable = no, scrollbars = no");

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
<body>
	<div>
		<form action="./MemberModifyService.do" method="post"
			enctype="multipart/form-data" name="userInfo" onsubmit="return checkValue()">
			<table class="table">
				<h2 class="header">개인정보 수정</h2>
				<p>
				<h4>
					*<strong class="strong"> 필수</strong><strong>는 반드시 입력해야
						합니다.</strong>
				</h4>
				</p>
				<tr>
					<td>아이디</td>
					<td><c:out value="${sessionScope.id}" /></td>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="button" name="changePw" value="비밀번호 변경"
						onclick="openPwChange()" class="btn btn-default"></td>
				</tr>

				<tr>
					<td>이름 <strong class="strong">필수</strong></td>
					<td><input type="text" id="name" name="name"
						value="<c:out value="${memberDTO.name}"/>" /></td>
				</tr>
				<tr>
					<td>닉네임 <strong class="strong">필수</strong></td>
					<td><input type="text" id="nickName" name="nickName"
						value="<c:out value="${memberDTO.nickName}"/>" /></td>
				</tr>
				<tr>
					<td>생년월일 <strong class="strong">필수</strong></td>
					<td><input type="text" id="birth" name="birth"
						placeholder="1990-01-01"
						value="<c:out value="${memberDTO.birth}"/>" /></td>
				</tr>
				<tr>
					<td>핸드폰 번호 <strong class="strong">필수</strong></td>
					<td><input type="text" id="hp" name="hp"
						placeholder="010-1234-1234"
						value="<c:out value="${memberDTO.hp}"/>" /></td>
				</tr>
				<tr>

					<td>이메일 <strong class="strong">필수</strong></td>
					<td><input name="email" id="email" type="email"
						value="<c:out value="${memberDTO.email}"/>" />
				</tr>
				<!-- 				<tr> -->
				<!-- 					<td>성별</td> -->
				<%-- 					<td><c:choose> --%>
				<%-- 							<c:when test="{memberDTO.gender='m'}"> --%>
				<!-- 									남자<input type="radio" id="gender" name="gender" value="m"> -->

				<%-- 							</c:when> --%>
				<%-- 							<c:otherwise> --%>
				<!-- 					여자<input type="radio" id="gender" name="gender" value="f"> -->
				<%-- 							</c:otherwise> --%>
				<%-- 						</c:choose></td> --%>
				<!-- 					</td> -->
				<!-- 				</tr> -->

				<c:if test="${empty memberDTO.profilePicture}">
					<tr>
						<td>프로필 사진</td>
						<td><c:out value="${memberDTO.profilePicture}" />
							&nbsp;&nbsp;&nbsp; <a
							href="./memberPicture/<c:out value="${memberDTO.profilePicture}"/>">프로필
								사진 보기</a> <input type="hidden" name="tempFile"
							value="<c:out value="${memberDTO.profilePicture}"/>"></td>
					</tr>
				</c:if>
				<tr>
					<td>프로필 사진 수정</td>
					<td><input type="file" id="tempFile" name="tempFile"></td>
				</tr>
				<tr>

					<td>자기 소개</td>
					<td><input type="text" name="introduceMySelf"
						placeholder="간단한 자기소개를 작성해주세요"
						value="<c:out value="${memberDTO.introduceMySelf}"/>" /></td>
				</tr>
			</table>
			<input type="submit" value="확인" class="btn btn-default" > <input type="button"
				value="취소" onclick="goMyInfoForm()" class="btn btn-default">
		</form>
		<input type="submit" value="회원탈퇴" onclick="goMemberDeleteForm()" class="btn btn-default">
	</div>
</body>
</body>
</html>