<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String pagefile = request.getParameter("page");
	if (pagefile == null) {
		pagefile = "maincontent"; // 첫 번째 메뉴 내용
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- bootstrap css 설정 -->
<link href="../boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="../boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="../boot/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
// 	$("#memberinfo").click(function(){
// 		$.ajax({
// 			url : ,
// 			type : ,
// 			success:function(result){
// 				$("#contents").html(result);
// 			}
// 		})
// });
// $("#chat").click(function(){
// 	$.ajax({
// 		url : ,
// 		type : ,
// 		success:function(result){
// 			$("#contents").html(result);
// 		}
// 	})
// });
	$("#notice").click(function(){
		$.ajax({
			url : "../NoticeListService.notice",
			type : "get",
			success:function(result){
				$("#contents").html(result);
			}
		})
	});
// 	$("#studyboard").click(function(){
// 		$.ajax({
// 			url : ,
// 			type : ,
// 			success:function(result){
// 				$("#contents").html(result);
// 			}
// 		})
// 	});
// 	$("#qna").click(function(){
// 		$.ajax({
// 			url : ,
// 			type : ,
// 			success:function(result){
// 				$("#contents").html(result);
// 			}
// 		})
// 	});
	
	
});
</script>

</head>
<body>
	<table class="table table-responsive" border="1">
		<tr>
			<td width="100%" height="40" colspan="3" class="h1"><img
				align="left" style="margin: 10px" width="240px" height="140px"
				src="../img/gift.png">
				<h1 align="center" class="lead" style="margin-top: 70px">Gift
					gifts</h1></td>
		</tr>
		<tr>
			<td width="960" height="43" colspan="3" align="left"><jsp:include
					page="top.jsp" /></td>
		</tr>
		<tr>
			<td width="15%" align="right" valign="top">
				<p align="center">
					<br /> <br /> <br /> <br /> <a href="#" class="h3"
						style="margin-bottom: 20px" id="memberinfo">회원관리</a> <br /> <br />
					<br /> <br /> <a href="#" class="h3" style="margin-bottom: 20px"
						id="chat">채팅</a> <br /> <br /> <br /> <br />
					<a href="#" class="h3" style="margin-bottom: 20px" id="notice">공지사항</a>
					<br /> <br /> <br /> <br /> <a href="#" class="h3"
						style="margin-bottom: 20px" id="studyboard">공부게시판</a> <br /> <br />
					<br /> <br /> <a href="#" class="h3" style="margin-bottom: 20px"
						id="qna">QnA</a>
				</p>
			</td>
			<td width="960" colspan="2" align="center" id="contents"></td>
		</tr>
		<tr>
			<td width="100%" height="40" colspan="3"
				style="background-color: black;"><jsp:include page="bottom.jsp" />
			</td>
		</tr>
	</table>
</body>
</html>