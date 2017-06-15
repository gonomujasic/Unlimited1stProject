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
<link href="../boot/css/bootstrap.min.css" rel="stylesheet" />
<!-- jquery.js 설정 -->
<script type="text/javascript" src="../boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="../boot/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
// 		$.ajax({
// 			url : "15165", //채팅리스트유알엘
// 			type : "get",
// 			success : function(result) {
// 				$("#mainchatlist").html(result)
// 			}
// 		});

		$.ajax({
			url : "mainboards.jsp", //
			type : "get",
			success : function(result) {
				$("#mainboards").html(result)
			}
		});
	});
</script>


</head>
<body>
<div class="container">
	<div class="intro">
	<h2 style="text-align:center">강의소개영상</h2>
	<iframe width="750" height="450" src="https://www.youtube.com/embed/dajW9nfXLds?&autoplay=1" frameborder="0" allowfullscreen>
	</iframe>
	</div>
	<hr>
	<br>
	<div id="mainboards"></div>

</div>
</body>
</html>