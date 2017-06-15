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
	$(document).ready(function() {
		$("#sellhistory").click(function() {
// 			alert("E");
				$.ajax({
					url : "GetSellHistoryService.Point",
					type : "post",
					data : "d=1",
					success : function(result){
						$("#hiscontainer").html(result);
					}

				});

		});

	$("#buyhistory").click(function() {
		$.ajax({
			url : "GetBuyHistoryService.Point",
			type : "post",
			success : function(result) {
				$("#hiscontainer").html(result);
			}

		});

	});
	$("#crhistory").click(function() {
		$.ajax({
			url : "GetCrHistoryService.Point",
			type : "post",
			success : function(result) {
				$("#hiscontainer").html(result);
			}

		});

	});
});
</script>

</head>
<body  style="background-color:transparent">
<div class="container">
	<h1 class="text-muted">포인트 내역</h1>
	<button class="btn btn-default" type="button" id="sellhistory">판매 내역</button>
	<button class="btn btn-default" type ="button" id="buyhistory">구매 내역</button>
	<button class="btn btn-default" type ="button" id="crhistory">충/환전 내역</button>
	
	<br>
	<br>
	<br>
	<div id="hiscontainer"></div>
	
	<button class="btn btn-default" onclick="location.href='./PointAdmin.Point'">포인트 관리 페이지로</button>
</div>
</body>
</html>