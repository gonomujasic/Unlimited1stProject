<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공부 게시판</title>
<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet" />
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div id="contentsArea">
		<section id="titlename">
			<table class="table table-bordered">
				<tr>
					<td>
						<p>
						<h1>${boardDTO.study_post_title}"</h1>
						</p>
						<p class="formSign">작성자 : ${boardDTO.id}</p>
						<div id="joinForm">
							<input type="hidden" name="no" id="no"
								value="<c:out value="${boardDTO.study_post_index}" />">
							<fieldset>
								<legend>공부 게시판 내용</legend>
								<p>
									<br />
									<c:out value="${boardDTO.study_post_contents}" />
									<br>
								</p>
								<br> <br>
								<hr>
								<p>
									<c:choose>
										<c:when test="${!empty boardDTO.attached_file}">
											<p>
												<label for="attached_file">파일 첨부</label><br />
												<c:out value="${boardDTO.attached_file}" />
												&nbsp;&nbsp;&nbsp; <a
													href="./boardUpload/<c:out value="${boardDTO.attached_file}"/>">파일
													내용보기</a> <input type="hidden" name="old_file"
													value="<c:out value="${boardDTO.attached_file}"/>" />
												&nbsp;&nbsp;&nbsp; <a
													href="./BoardDownload.study?attached_file=<c:out value="${boardDTO.attached_file}"/>">
													파일 다운로드 </a> <br />
											</p>
										</c:when>
										<c:otherwise>
											<p>
												<label for="old_file">파일 첨부</label><br /> 첨부 파일이 없습니다.
											</p>
										</c:otherwise>
									</c:choose>
								</p>

								<div class="btnJoinArea">
									<a
										href="./BoardReplyMove.study?study_post_index=<c:out value="${boardDTO.study_post_index}"/>">
										<button type="button" class="btnOk btn btn-default">답변</button>
									</a> <a
										href="./BoardModify.study?study_post_index=<c:out value="${boardDTO.study_post_index}"/>">
										<button type="button" class="btnOk btn btn-default">수정</button>
									</a> <a
										href="./BoardDelete.study?study_post_index=<c:out value="${boardDTO.study_post_index}"/>">
										<button type="button" class="btnOk btn btn-default">삭제</button>
									</a>
									<button type="button" value="button"
										onclick="location.href='./BoardList.study'"
										class="btnOk btn btn-default">목록</button>
								</div>
					</td>
				</tr>

			</table>
		</section>
	</div>
	</div>
</body>
</html>