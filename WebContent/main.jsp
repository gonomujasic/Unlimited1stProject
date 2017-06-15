<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String pagefile = request.getParameter("page");
	if (pagefile == null) {
		pagefile = "maincontent.jsp"; // 첫 번째 메뉴 내용
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>

<script type="text/javascript" charset="utf-8" src="js/jquery.tubular.1.0.js"></script>
<script type="text/javascript">
//$(document).ready(function(){
// 	var options = { videoId: 'iIX9ni6GNBg', start: 3};
// 	$("#wrapper").tubular(options);
// });
</script>




<style type="text/css">
/* 	div.wrapper{ */
/* 		margin: 0px; */
/* 		padding: 0px; */
/* 	} */
/* 	.iframe1{ */
/* 		position: absolute; */
/* 		width: 84.969%; */
/* 		height: 67.5%; */
/* 		right: 0cm; */
/* 		bottom: 0.09cm; */
/* 		z-index: 1; */
/* 	} */
/* 	.bottomView{ */
/* 		position: absolute; */
/* 		width: 100%; */
/* 		height: 7.33cm; */
/* 		border-top: 1px solid #dcdcdc; */
/* 		top: 18.45cm; */
/* 		z-index: 1;  */
/* 		background-color: white; */
/* 	} */
</style>
</head>
<body>

<div id="wrapper">
<!-- <div id="wrapper"></div> -->
	<table class="table" border="1">
		<tr>
			<td width="100%" height="40" colspan="3" class="h1" style="background-color:white"><img
				align="left" style="margin: 10px" width="240px" height="140px"
				src="./img/gift.png">
				<h1 align="center" class="lead" style="margin-top: 70px">
				<img src="giftgift.png">
				</h1></td>
		</tr>
		<tr>
		
			<td width="960" height="43" colspan="3" align="left" style="background-color:white">
			<c:choose>
			<c:when test="${empty id}">
&nbsp;&nbsp;&nbsp; <button class="btn btn-primary btn-sm"onclick="location.href='main.jsp?page=LoginForm.do'">Login</button> | <button class="btn btn-warning btn-sm" onclick="location.href='main.jsp?page=MemberRegist.do'">Join</button>
		</c:when>
		<c:otherwise>
		${id}님이 로그인 되었습니다. <button class="btn btn-warning btn-sm" onclick="location.href='main.jsp?page=./login/LogoutForm.jsp'">Logout</button>
		</c:otherwise>
		</c:choose>
		</td>
		</tr>
		<tr>
			<td width="15%" align="right" valign="top" style="background-color:white">
				<p align="center">
					<br /> <br /> <br /> <br /> <a href="main.jsp" class="h3"
						style="margin-bottom: 20px" id="memberinfo">메인</a>
					
					<c:if test="${!empty id}"><br /> <br /> <br /> <br />
					 <a href="main.jsp?page=MemberInfoForm.do" class="h3" style="margin-bottom: 20px" id="memberinfo">마이페이지</a>
					</c:if>
					<c:if test="${id=='admin'}"><br /> <br /> <br /> <br />
					<a href="main.jsp?page=PointAdmin.Point" class="h3" style="margin-bottom: 20px" id="memberinfo">포인트 관리자<br> 페이지</a>
					</c:if>
						 <br /> <br />
					<br /> <br /> <a href="main.jsp?page=ChattingServlet?command=loadlist" class="h3" style="margin-bottom: 20px"
						id="chat">강의</a> <br /> <br /> <br /> <br />
					<a href="main.jsp?page=/Unlimited1stProject/NoticeListService.notice" class="h3" style="margin-bottom: 20px"> 공지사항</a>
					<br /> <br /> <br /> <br /> 
					<a href="main.jsp?page=BoardList.study" class="h3" style="margin-bottom: 20px" id="studyboard">공부게시판</a>
					<br /> <br /> <br /> <br /> 
					<a href="main.jsp?page=QnaList.qna" class="h3" style="margin-bottom: 20px" id="qna">QnA</a>
				</p>
			</td>
			
			<td width="960" colspan="2" align="center" id="contents">
		
			<iframe frameborder=0 src="<%=pagefile%>" class="iframe1" width="100%" style="height:1000px;"></iframe>
			 	<!-- 이부분 그거 메인 부분 다 날리고 동영상에 홈페이지소개 다른화면에선 동영상 안나오게 -->
			 </td>
		</tr>
		<tr>
		<td colspan="3" background="white">
		<p style="text-align: center"><font color="black">바닥글</font></p>
		</td>
		</tr>
	</table>

<!-- <div class="bottomView"> -->
<!-- <p><font color="black">바닥글</font></p> -->
<!-- </div> -->
	</div>
</body>
</html>