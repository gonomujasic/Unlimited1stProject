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

<script type="text/javascript">



</script>

</head>
<body style="background-color:transparent" >
<div class="container">
<br><br>
<p class="text">${id}님의 포인트 : ${point} point<p>

<p>
<button class="btn btn-default" onclick="location.href='./GetPointHistoryService.Point?id=${memberDTO.id}'" >포인트 내역</button>
<button class="btn btn-default" onclick="location.href='./PointChargeForm.Point'">포인트 충전</button>
<button class="btn btn-default" onclick="location.href='./PointRefundForm.Point'">포인트 환전</button>
</p>


</div>
<div class="content"></div>
</body>
</html>