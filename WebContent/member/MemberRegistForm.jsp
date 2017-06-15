<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 20170606정해선 작성 -->
<!-- 발생오류 :Posted content type isn't multipart/form-data -->
<!-- 해결방안:enctype="multipart/form-data" -->
<!-- 상세내용: form 태그안에 enctype="multipart/form-data"를 추가 -->
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
	function checkValue() {
		//alert("checkValue");
		var form = document.userInfo;
		//아이디: 숫자,영어만 사용하며 4~12자로 작성 
		var id_check = /^[a-zA-Z0-9]{4,12}$/;
		//비밀번호 영어/숫자 최소 8글자
		var password_check = /^[a-zA-Z0-9]{8,64}$/;
		//이름 및 닉네임 유효성검사 : 한글 및 영문으로 최소2글자 작성
		var name_check = /^[a-zA-Z가-힣]{2,10}$/;
		//전화번호는-를 포함
		var hp_check =/^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})$/;
		//이메일 유효성검사
		var email_check = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		

		if (!form.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (id_check.test(form.id.value) == false) {
			alert("아이디에 특수문자 및 한글은 사용할 수 없으며, 4자~12자로 입력해주세요");
			return false;
		}

		if (form.idDuplication.value != "idCheck") {
			alert("아이디 중복체크를 해주세요.");
			return false;
		}

		if (!form.password.value) {

			alert("비밀번호를 입력하세요.");
			return false;
		}

		if (password_check.test(form.password.value) == false) {
			alert("비밀번호는 영문, 숫자를 이용하여 8자 이상 입력해주세요.");
			return false;
		}

		// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
		if (form.password.value != form.passwordcheck.value) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}

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

		if (!form.agree[0].checked) {
			alert("약관에 동의해주세요.");
			return false;
		}

	}

	/*
	 *아이디 중복확인 함수 
	 *아이디 중복 확인여부를 위해 새 창을 띄워 줌.
	 *./IdCheckService.do?id="+$("#id").val() 입력된 id의 값을 url주소로 넘겨준다
	 */
	function openIdChk() {
		window.name = "parentForm";
		window.open("./IdCheckService.do?id=" + $("#id").val(), "chkForm",
				"width=500, height=300, resizable = no, scrollbars = no");

	}
	/*
	 * 아이디 입력창에 값을 입력하면 hidden에 inUncheck로 세팅한다.
	 *중복 체크 후 idCheck로 바꿔주고,만약 다른 아이디를 입력했을 경우 
	 *다시 중복 체크를 하기 위해서이다
	 */
	function inputIdChk() {
		//	alert("onkeydown inputidChk");
		document.userInfo.idDuplication.value = "idUncheck";
	}

	// 취소 버튼 클릭시 첫화면으로 이동
	function goFirstForm() {
		location.href = "LoginForm.do";
	}
</script>
<style type="text/css">
.strong {
	color: red;
}

.header {
	text-align: center;
}
div.mainView{
	margin: 0px;
	padding: 0px;
}
body{
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<body>
	<div class="mainView">
		<form action="./MemberRegistService.do" method="post" name="userInfo"
			onsubmit="return checkValue()" enctype="multipart/form-data">
			<table class="table">
				<h2 class="header">회원 가입</h2>
				<p>
				<h4>
					*<strong class="strong"> 필수</strong><strong>는 반드시 입력해야
						합니다.</strong>
				</h4>
				</p>
				<tr>
					<td>아이디 <strong class="strong">필수</strong></td>
					<td><input type="text" id="id" name="id"
						onkeydown="inputIdChk()" placeholder="중복확인 버튼을 눌러주세요"> <input
						type="button" value="중복확인" onclick="openIdChk()" class="btn btn-default"> <input
						type="hidden" name="idDuplication" value="idUncheck"></td>
				</tr>
				<tr>
					<td>비밀번호 <strong class="strong">필수</strong></td>
					<td><input type="password" id="password" name="password"
						placeholder="8자 이상"></td>
				</tr>
				<tr>
					<td>비밀번호 확인 <strong class="strong">필수</strong></td>
					<td><input type="password" name="passwordcheck"></td>
				</tr>
				<tr>
					<td>이름 <strong class="strong">필수</strong></td>
					<td><input type="text" id="name" name="name"
						placeholder="한글/영문"></td>
				</tr>
				<tr>
					<td>닉네임 <strong class="strong">필수</strong></td>
					<td><input type="text" id="nickName" name="nickName"></td>
				</tr>
				<tr>
					<td>생년월일 <strong class="strong">필수</strong></td>
					<td><input type="text" id="birth" name="birth"
						placeholder="19900101"></td>
				</tr>
				<tr>
					<td>핸드폰 번호 <strong class="strong">필수</strong></td>
					<td><input type="text" id="hp" name="hp"
						placeholder="010-1234-1234"></td>
				</tr>
				<tr>

					<td>이메일 <strong class="strong">필수</strong></td>
					<td><input name="email" id="email" type="text"
						placeholder="example@naver.com"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><input type="radio" id="gender" name="gender" value="m"
						checked class="checkbox-inline">남 <input type="radio"
						id="gender" name="gender" value="f" class="checkbox-inline">여</td>
				</tr>
				<tr>
					<td>프로필 사진</td>
					<td><input type="file" name="profilePicture"
						placeholder="사진 첨부"></td>
				</tr>
				<tr>
					<td colspan="2">이용 약관</td>
				</tr>
				<tr>
					<td colspan="2">이용 약관 내용 블라블ㄹ라</td>
				</tr>
				<tr>
					<td colspan="2"><input type="radio" name="agree" value="동의합니다"
						class="checkbox-inline">동의합니다 <input type="radio"
						name="agree" value="동의하지 않습니다." class="checkbox-inline">동의하지
						않습니다</td>
				</tr>
			</table>
			<input type="submit" value="회원가입" class="btn btn-default"> <input
				type="button" value="취소" onclick="goFirstForm()" class="btn btn-default">
		</form>
	</div>
</body>
</html>