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
<br><br>
<div class="container">
<p class="h3">${id}님의 포인트 이용 내역</p>
<table class="table">
<tr>
<td>거래 전 포인트</td>
<td>거래 포인트</td>
<td>거래 후 포인트</td>
<td>거래내용</td>
<td>상대</td>
<td>거래일</td>

</tr>
<c:forEach var="pointHistoryDTO" items="${pointHistoryList}">

<tr>

<td>${pointHistoryDTO.point_before}</td>
<td>${pointHistoryDTO.dealing_point}</td>
<td>${pointHistoryDTO.point_after}</td>
<td>${pointHistoryDTO.why}</td>
<td>${pointHistoryDTO.opponent}</td>
<td>${pointHistoryDTO.dealing_date}</td>
</tr>

</c:forEach>
</table>
<button class = "btn btn-default" onclick="location.href='./PointMain.Point'">포인트 메인으로</button>

</div>
</body>
</html>