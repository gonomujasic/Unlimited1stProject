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
<p class="h2 text-muted">포인트 관리자 페이지</p>

<!-- <button onclick="location.href='./'"> 회원 포인트  </button> 관리자 맘대로 포인트 회수하고 주고 하는 기능인데 2차때 하기로-->
<button class="btn btn-default" onclick="location.href='./AdminGetPointHistoryForm.Point'"> 회원 포인트 내역 보기  </button>
<button class="btn btn-default" onclick="location.href='./AdminGetRefundRequestService.Point'"> 환전 요청 리스트  </button>
<!-- <button class="btn btn-default" onclick="location.href='./PointMain.Point'"> 포인트 메인  </button> -->
</body>
</html>