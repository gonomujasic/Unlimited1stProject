<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#refundbtn").click(function() {
			var pointReg = /[0-9]$/;
			if (!pointReg.test($("#dealing_point").val())) {
				alert("환전 요청 포인트에 숫자만 입력해주세요.")
				return false;
			}
			if ($("#dealing_point").val() > $("#limit").val()) {
				alert("가지고 있는 포인트보다 환전 요청금액이 많습니다.")
				return false;
			}
// 			return false;
		})
	})
	/* 이거 왜 그냥 되버리냐? 금액 크게 해도? 여기부터 내일해라 */
</script>
</head>
<body style="background-color:transparent">


<div class="container">
<br>
<br>
<fieldset>
<legend>환전신청</legend>
	<form action="./PointRefundRequestService.Point">
		<label>환전가능 금액 :</label> <input type="text" id="limit" value="${point}"
			readonly="readonly"><br> <label for="dealing_point">환전요청
			금액 : </label> <input type="text" id="dealing_point" name="dealing_point"
			required="required"> <br> <label for="refund_bank">
			환전 은행 : </label> <select name="refund_bank">
			<option value="shinhan">신한</option>
			<option value="nonghyup">농협</option>
			<option value="kookmin">국민</option>
		</select> <br> <label for="refund_accountant">환전 계좌번호 : </label> <input
			name="refund_accountant" required="required">
			<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="btn btn-default" type="submit" id="refundbtn">환전신청</button>
		<button class="btn btn-default" type="button" onclick="location.href='./PointMain.Point'">환전취소</button>
	</form>
</fieldset>
</div>

</body>
</html>