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
<title>어깨동무 게시판</title>
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
			<h1>공지사항 수정</h1>
		
			<form action="./NoticeModifyService.notice" method="post" id="joinForm"
				name="modifyform" enctype="multipart/form-data">
				<fieldset>
<%-- 				${noticeDTO.no} --%>
					<input type="hidden" name="no"
						value="<c:out value="${noticeDTO.no}"/>" />
					<legend>게시판 수정</legend>
					<p>
						<label for="writer">이름 </label> <input type="text" id="writer"
							name="writer" value="<c:out value ="${noticeDTO.writer}"/>" />
					</p>
					<p>
						<label for="title">제목 </label><input type="text" id="title"
							name="title" value="<c:out value="${noticeDTO.title}"/>">
					</p>
					<p>
						<label for="content">내용</label>
						<textarea name="content" cols="100" rows="30"> <c:out
								value="${noticeDTO.content}" /></textarea>
					</p>
					<c:if test="${empty noticeDTO.attached_file}">
						<p>
							<label for="attached_file">파일첨부</label><br />
							<c:out value="${noticeDTO.attached_file}" />
							&nbsp;&nbsp;&nbsp; 
							<c:if test="${!empty noticeDTO.old_file}">
							<a href="./NoticeUpload/<c:out value="${noticeDTO.attached_file}"/>">파일
								내용보기</a> <input type="hidden" name="old_file"
								value="<c:out value="${noticeDTO.attached_file}"/>" />
								</c:if>
						</p>
					</c:if>
					<p>
						<label for="attached_file">파일 수정</label> <input type="file"
							id="attached_file" name="attached_file">
					</p>
					<hr>
					<div class="btnJoinArea text-center">
						<button type="submit" class="btnOk">수정</button>
						<button type="reset" class="btnCancel">취소</button>
						<button type="button" value="button"
							onclick="location.href='./NoticeListService.notice'" class="btnOk">목록</button>
					</div>
				</fieldset>

			</form>
		</section>
	</div>
</div>

</body>
</html>