<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공부 게시판</title>
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
			<table class="table table-bordered">
				<h1>공부 게시판 수정</h1>

				<p class="formSign">
					<strong class="require">필수</strong>는 반드시 입력해야 하는 항목입니다.
				</p>
				<form action="./BoardModifyDetail.study" method="post" id="joinForm"
					name="modifyform" enctype="multipart/form-data">
					<fieldset>
						<input type="hidden" name="study_post_index"
							value="<c:out value="${boardDTO.study_post_index}"/>" />
						<legend>공부 게시판 수정</legend>
						<p>
							<label for="id">이름</label> <input type="text" id="id" name="id"
								value="<c:out value="${boardDTO.id}"/>" />
						</p>
						<p>
							<label for="study_post_title">제목</label> <input type="text"
								id="study_post_title" name="study_post_title"
								value="<c:out value="${boardDTO.study_post_title}"/>" />
						</p>
						<p>
							<label for="study_post_contents">내용</label>
							<textarea name="study_post_contents" cols="74" rows="15"><c:out
									value="${boardDTO.study_post_contents}" />
</textarea>
						</p>
						<c:if test="${empty boardDTO.attached_file}">
							<p>
								<label for="attached_file">파일 첨부</label><br />
								<c:out value="${boardDTO.attached_file}" />
								&nbsp;&nbsp;&nbsp; <a
									href="./boardUpload/<c:out value="${boardDTO.attached_file}"/>">파일
									내용보기</a> <input type="hidden" name="old_file"
									value="<c:out value="${boardDTO.attached_file}"/>" />
							</p>
						</c:if>
						<p>
							<label for="old_file">파일 수정</label> <input type="file"
								id="old_file" name="old_file">
						</p>
						<p>
							<label for="password">비밀번호<strong class="require">필수</strong></label>
							<input type="password" id="password" name="password"
								required="required" placeholder="비밀번호 입력" size="12">
						</p>
						<div class="btnJoinArea">
							<button type="submit" class="btnOk btn btn-default">수정</button>
							<button type="reset" class="btnCancel btn btn-default">취소</button>
							<button type="button" value="button"
								onclick="location.href='./BoardList.study'"
								class="btnOk btn btn-default">목록</button>
						</div>
					</fieldset>
				</form>
			</table>
		</section>
	</div>
	</div>
</body>
</html>