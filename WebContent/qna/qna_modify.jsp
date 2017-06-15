<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Qna게시판</title>
<!-- 디바이스에 따라서 보기 방식을 지정한다. -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet" />
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function removeSpace() {
		var form = document.modifyform;
		// 	form.id.value = form.id.value.replace(/^\s*|\s*$/g,"");
		// 	form.qna_pass.value = form.qna_pass.value.replace(/^\s*|\s*$/g,"");
		form.qna_title.value = form.qna_title.value.replace(/^\s/gm, '');
		form.qna_contents.value = form.qna_contents.value.replace(/^\s/gm, '');
	}
</script>
</head>
<body>
<div class="container">
	<div id="contentsArea">
		<section id="titlename">
			<table class="table table-bordered">
				<h1>Qna게시판 수정</h1>
				<p class="formSign">
					<strong class="require">필수</strong>는 반드시 입력해야 하는 항목입니다.
				</p>
				<form action="./QnaModifyDetail.qna" method="post" id="joinForm"
					name="modifyform" enctype="multipart/form-data">
					<fieldset>
						<input type="hidden" name="qna_number"
							value="<c:out value="${qnaDTO.qna_number}"/>" />
						<legend>Qna게시판 수정</legend>
						<p>
							<label for="id">이름</label> <input type="text" id="id" name="id"
								value="<c:out value="${qnaDTO.id}"/>" />
						</p>
						<p>
							<label for="qna_title">제목</label> <input type="text"
								id="qna_title" name="qna_title"
								value="<c:out value="${qnaDTO.qna_title}"/>" />
						</p>
						<p>
							<label for="qna_contents">내용</label>
							<textarea name="qna_contents" cols="74" rows="15"><c:out
									value="${qnaDTO.qna_contents}" />
</textarea>
						</p>
						<c:if test="${empty qnaDTO.attached_file}">
							<p>
								<label for="attached_file">파일 첨부</label><br />
								<c:out value="${qnaDTO.attached_file}" />
								&nbsp;&nbsp;&nbsp; <a
									href="./qnaUpload/<c:out value="${qnaDTO.attached_file}"/>">파일
									내용보기</a> <input type="hidden" name="old_file"
									value="<c:out value="${qnaDTO.attached_file}"/>" />
							</p>
						</c:if>
						<p>
							<label for="old_file">파일 수정</label> <input type="file"
								id="old_file" name="old_file">
						</p>
						<p>
							<label for="qna_pass">비밀번호<strong class="require">필수</strong></label>
							<input type="password" id="qna_pass" name="qna_pass"
								required="required" placeholder="비밀번호 입력" size="12">
						</p>
						<div class="btnJoinArea">
							<button type="submit"class="btnOk btn btn-default">수정</button>
							<button type="reset" class="btnCancel btn btn-default">취소</button>
							<button type="button" value="button"
								onclick="location.href='./QnaList.qna'" class="btnOk btn btn-default">
								목록</button>
						</div>
					</fieldset>
				</form>
				</table>
		</section>
	</div>
	</div>
</body>
</html>