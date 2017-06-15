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
<title>정민규 게시판</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./css/jboard.css">

<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>
	

</head>
<body>
<div class="container">
	<div id="contentsArea">
		<section id="titlename">
			<h1>공지사항 작성</h1>

			<form action="./NoticeAddService.notice" method="post" id="joinForm"
				name="boardForm" enctype="multipart/form-data">
				<fieldset>
					

						
						 <input type="hidden" id="writer" name="writer" value="${id}" required
						 />
			


					<table class="table">
					<tr>
					<td>
					<p>
						<label for="title">제목</label>
						 <input type="text" id="title" name="title" required
							placeholder="글의 제목을 입력하세요" size="50" />
					</p>
					</td>
					</tr>
					
					<tr>
					<td>
					<p>
						<label for="content">내용
						</label><br>
						<textarea id="content" name="content" required cols="100" rows="30"
							placeholder="글의 내용을 입력하세요"></textarea>
					</p>
					</td>
					</tr>
					
					<tr>
					<td>
					<p>
						<label for="attached_file">이미지 첨부</label> <input type="file"
							id="attached_file" name="attached_file" />
					</p>
					</td>
					</tr>
					<tr>
					<td>
					<div class="btnJoinArea">
						<button type="submit" class="btnOk btn btn-default">글등록</button>
						<button type="reset" class="btnCancel btn btn-default">새로고침</button>
						<button type="button" class="btnOk btn btn-default"
							onclick="location.href='./NoticeListService.notice'" class="btnOk btn btn-default">목록</button>
					</div>
					</td>
					</tr>
					</table>
				</fieldset>
			</form>
		</section>
	</div>
</div>
</body>
</html>