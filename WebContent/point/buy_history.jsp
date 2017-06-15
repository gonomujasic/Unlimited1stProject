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
<caption> 구매 내역 </caption>
<tr>
<td>구매번호</td>
<td>구매자</td>
<td>판매자</td>
<td>거래포인트</td>
<td>거래전 포인트</td>
<td>거래후 포인트</td>
<td>거래일</td>
</tr>
<c:forEach var="buyHistory" items="${buyHistoryList}">
<tr>
<%-- <c:forEach var="sellHistoryDTO" items="${sellHistory}"> --%>

<td>${buyHistory["no"]}</td>
<td>${buyHistory["buyer"]}</td>
<td>${buyHistory["seller"]}</td>
<td>${buyHistory["dealing_point"] }</td>
<td>${buyHistory["point_before"]}</td>
<td>${buyHistory["point_after"]}</td>
<td>${buyHistory["dealing_date"]}</td>

<%-- </c:forEach> --%>
</tr>
</c:forEach>
</table>
</body>
</html>