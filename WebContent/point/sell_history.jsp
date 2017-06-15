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
<caption> 판매 내역 </caption>

<tr>
<td>판매번호</td>
<td>판매자</td>
<td>구매자</td>
<td>거래포인트</td>
<td>거래전 포인트</td>
<td>거래후 포인트</td>
<td>거래일</td>
</tr>
<c:forEach var="sellHistory" items="${sellHistoryList}">
<tr>
<%-- <c:forEach var="sellHistoryDTO" items="${sellHistory}"> --%>

<td>${sellHistory["no"]}</td>
<td>${sellHistory["seller"]}</td>
<td>${sellHistory["buyer"]}</td>
<td>${sellHistory["dealing_point"] }</td>
<td>${sellHistory["point_before"]}</td>
<td>${sellHistory["point_after"]}</td>
<td>${sellHistory["dealing_date"]}</td>

<%-- </c:forEach> --%>
</tr>
</c:forEach>
</table>

</body>
</html>