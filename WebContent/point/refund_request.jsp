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

<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>


</head>
<body style="background-color:transparent">
<table style="text-align:center" class="table">
<caption> 환전 요청 내역 </caption>

<tr>
<td>요청 번호</td>
<td>요청자</td>
<td>요청 포인트</td>
<td>요청일</td>
<td>요청 은행</td>
<td>요청 계좌</td>
<td>처리유무</td>
<td>환전완료버튼</td>
</tr>
<c:forEach var="refundRequest" items="${refundRequestList}">
<tr>
<%-- <c:forEach var="sellHistoryDTO" items="${sellHistory}"> --%>

<td>${refundRequest["no"]}</td>
<td>${refundRequest["id"]}</td>
<td>${refundRequest["dealing_point"] }</td>
<td>${refundRequest["request_date"]}</td>
<td>${refundRequest["refund_bank"]}</td>
<td>${refundRequest["refund_accountant"]}</td>
<td>${refundRequest["done"]}</td>
<td><c:if test='${refundRequest["done"] == "N"}'>
<button class ="btn btn-default" onclick='location.href="./PointRefundRequestCheckService.Point?id=${refundRequest["id"]}&dealing_point=${refundRequest["dealing_point"]}&no=${refundRequest["no"]}"'>환전완료</button>
 </c:if> </td>

<%-- </c:forEach> --%>
</tr>
</c:forEach>
</table>
<button class ="btn btn-default" onclick="location.href='./PointAdmin.Point'">포인트 관리 페이지로</button>
</body>
</html>