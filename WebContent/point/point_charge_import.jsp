<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>



</head>
<body>
<input id="amount" type="hidden" value="${param.amount}">
<input class="name" id="name" type="hidden" value="${param.name}">
<input id="buyer_tel" type="hidden" value="${param.buyer_tel}">
<input id="pay_method" type="hidden" value="${param.pay_method}">
<form action = "/Unlimited1stProject/PointChargeService.Point?dealing_point=<%=request.getParameter("amount")%>" method = "post" id = "chk"></form>

</body>

<script type="text/javascript">
	$(function() {
// 		alert($("#name").val());
		var IMP = window.IMP;
		IMP.init('imp48313576');
	
		IMP.request_pay({
		    pg : 'payco', // version 1.1.0부터 지원.
		    pay_method : $("#pay_method").val(), //request.getParameter("payMethod") 이전화면에서 페이메서드 라디오로 받기 
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : $("#name").val(),
		    amount : $("#amount").val(),
			m_redirect_url : 'localhost:8080/Unlimited1stProject/PointChargeService.Point?dealing_point='+$("#amount").val()+'&pay_method='+$("#pay_method").val(),
			buyer_tel : '010-6368-6184'					
		}, function(rsp) {
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
// 				msg += '고유ID : ' + rsp.imp_uid;
// 				msg += '상점 거래ID : ' + rsp.merchant_uid;
// 				msg += '결제 금액 : ' + rsp.paid_amount;
// 				msg += '카드 승인번호 : ' + rsp.apply_num;
				alert(msg);
				document.getElementById("chk").submit();
				//location.href="/Unlimited1stProject/PointChargeService.Point?dealing_point="+$("#amount").val();
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			
		});

	})
	
</script>
<%-- <% response.sendRedirect("localhost:8080/Unlimited1stProject/PointChargeService.Point?dealing_point="+request.getParameter("amount")); %> --%>


</html>