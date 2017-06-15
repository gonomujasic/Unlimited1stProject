<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Qna게시판</title>
				<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>
<link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="../css/jboard.css">
</head>
<body>
<div class="container">

<div id="contentsArea">
<section id="titlename">
			<table class="table table-bordered">
<h1>Qna답변 글 등록</h1>
<p class="formSign">
<strong class="require">필수</strong>는 반드시 입력해야 하는 항목입니다.
</p>
<form action="./QnaReply.qna" method="post" id="joinForm" name="boardform" enctype="multipart/form-data">
<fieldset>
<input type="hidden" name="qna_number" value="<c:out value="${qnaDTO.qna_number}"/>"/>
<input type="hidden" name="answer_num" value="<c:out value="${qnaDTO.answer_num}"/>"/>
<input type="hidden" name="answer_lev" value="<c:out value="${qnaDTO.answer_lev}"/>"/>
<input type="hidden" name="answer_seq" value="<c:out value="${qnaDTO.answer_seq}"/>"/>
<legend>답변 글</legend>
<p>
<label for="id">글쓴이<strong class="require">필수</strong></label>
<input type="text" id="id" name="id" required="required" placeholder="홍길동">
</p>
<p>
<label for="qna_title">제목</label>
<input type="text" id="qna_title" name="qna_title" value="[답변]<c:out value="${qnaDTO.qna_title}"/>">
</p>
<p>
<label for="qna_contents">내용</label>
<textarea name="qna_contents" rows="15" cols="74"></textarea>
</p>
<p>
<label for="attached_file">파일 수정</label>
<input type="file" id="attached_file" name="attached_file"/>
</p>
<p>
<label for="qna_pass">비밀번호<strong class="require">필수</strong></label>
<input type="password" id="qna_pass" name="qna_pass" required="required" placeholder="비밀번호 입력" size="12"/>
</p>
<div class="btnJoinArea">
<button type="submit" class="btnOk btn btn-default">글 등록</button>
<button type="reset" class="btnCancel btn btn-default">취소</button>
<button type="button" value="button" onclick="location.href='./QnaList.qna'" class="btnOk btn btn-default">
목록
</button>
</div>
</fieldset>
</form>
</table>
</section>
</div>
</div>
</body>
</html>