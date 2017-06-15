<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<!-- jquery.js 설정 -->
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
// 	var d="page=1&no=3" /* ${noticeDTO.no}를 넣고싶은데 어케하지 */
	var d =$("#no").serialize();
// 	alert(d);
	
	$.ajax({
		url:"NoticeCommentListService.notice",
		type:"get",
		data:d,
		success:function(result){
			$("#comments").html(result);
		}
		});
	

	})


	


</script>
</head>
<body>
<div class="container">
	<div id="contentsArea">
		<section id="titlename">
		<table class="table table-bordered">
		<tr>
		<td>
			<p><h1>${noticeDTO.title }</h1>
			<p class="formSign"> 작성자 : ${noticeDTO.writer }</p>
			<div id="joinForm">
				<input type="hidden" name="no" id="no"
					value="<c:out value="${noticeDTO.no}" />">
				<fieldset>
					<legend>공지 내용</legend>
					
<!-- 					<p> -->
<!-- 						<label for="title">제목 </label> <br /> -->
<%-- 						<c:out value="${noticeDTO.title }" /> --%>
<!-- 					</p> -->
					<p> 
<!-- 						<label for="content">내용</label> -->
						 <br />
						<c:out value="${noticeDTO.content}" />
						<br>
					</p>
					<br>
					<br>
					<hr>
					<p>
						<label for="attached_file"> 첨부 이미지: </label><br/>
						<c:choose>
						<c:when test="${!empty noticeDTO.attached_file}">
						
						<img src="./NoticeUpload/${noticeDTO.attached_file}">
						</c:when>
						<c:otherwise>
						첨부된 이미지가 없습니다.
						</c:otherwise>
						</c:choose>
					</p>
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${!empty noticeDTO.attached_file}"> --%>
<!-- 							<p> -->
<!-- 								<label for="attached_file">파일 첨부</label><br /> -->
<%-- 								<c:out value="${noticeDTO.attached_file}" /> --%>
<!-- 								&nbsp;&nbsp;&nbsp; <a -->
<%-- 									href="./NoticeUpload/<c:out value="${noticeDTO.attached_file}"/>">파일 --%>
<!-- 									내용보기</a> <input type="hidden" name="old_file" -->
<%-- 									value="<c:out value="${noticeDTO.attached_file}"/>" /> --%>
<!-- 								&nbsp; <a -->
<%-- 									href="./NoticeDownloadService.notice?attached_file=<c:out value="${noticeDTO.attached_file}" />"> --%>
<!-- 									파일 다운로드 </a> <br /> -->
<!-- 							</p> -->
						
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<!-- 							<p> -->
<!-- 								<label for="old_file">파일 첨부</label><br /> 첨부 파일이 없습니다. -->
<!-- 							</p> -->
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>
					<div class="btnJoinArea">
						<c:if test="${id =='admin' || id==noticeDTO.writer}">
					
						<a href="./NoticeModifyForm.notice?no=<c:out value="${noticeDTO.no}"/>">
							<input type="button" class="btnOk btn btn-default" value="수정">
						</a>
					
						<a href="./NoticeDeleteService.notice?no=<c:out value="${noticeDTO.no}"/>">
							<input type="button" class="btnOk btn btn-default" value="삭제"/>
						</a>
						</c:if>
						<button type="button" value="button" onclick="location.href='./NoticeListService.notice'" class="btnOk btn btn-default"> 목록 </button>
					</div>
				</fieldset>
			</div>
			<br>
			<br>
			<br>
			<br>
			</td>
			</tr>
			<tr>
			<td>
			<div id="comments"></div>
			</td>
			</tr>
			</table>
		</section>
	</div>
</div>
</body>
</html>