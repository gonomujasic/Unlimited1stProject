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
<link href="./boot/css/bootstrap.min.css" rel="stylesheet" />
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#charge").submit(function(event){
		var amountReg = /[0-9]$/;
		var sel_type=null;
		var pm = document.getElementsBYName('pay_method');
		if(!amountReg.test(($("#amount").val()))){
			alert("충전포인트에 숫자만 입력해주세요");
			return false;
		}
	})
})

</script>


</head>
<body style="background-color:transparent">
	<div class="container">
	<br><br>
		<fieldset>
			<legend>포인트충전</legend>
			<form id="charge" action="./PointChargeImport.Point" method="post">
				<input type="hidden" name="name" id="name" class="name"
					value="언어재능 교환 포인트"> <label for="amount">결제포인트 : </label> <input
					type="text" name="amount" id="amount" required="required">
				<%-- <input type="hidden" name="buyer_email" id="buyer_email" value="${memberDTO.email}"> <!-- 그거그거야 --> --%>
				<input type="hidden" name="buyer_tel" id="buyer_tel"
					value="010-6368-6184">
				<!-- 세션에있는 memberDTO에서 email뽑아서쓸꺼야 -->
				<br> <br> <label for="pay_method">결제수단:</label>&nbsp;&nbsp;&nbsp;
				<input type="radio" name="pay_method" id="pay_method" value="card">신용카드
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="pay_method" id="pay_method" value="trans">실시간계좌이체
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="pay_method" id="pay_method" value="vbank">가상계좌
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="pay_method" id="pay_method" value="phone">휴대폰소액결제
				<br> <input class="btn btn-default" type="submit" id="chargebtn" value="충전"
					style="MARGIN: 0px 0px 0px 220px">
				<button class="btn btn-default" type="button" onclick="location.href='./PointMain.Point'">취소</button>

			</form>
		</fieldset>
	</div>

</body>
</html>