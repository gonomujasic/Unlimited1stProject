<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시판</title>
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
<h1>공부 게시판 글쓰기</h1>
<p class="formSign">
<strong class="require">필수</strong>는 반드시 입력해야 하는 항목입니다.
</p>
<form action="./BoardAdd.study" method="post" id="joinForm" name="boardform" enctype="multipart/form-data">
<fieldset>
<legend>공부 게시판 글쓰기</legend>
<p>
<label for="id">글쓴이 <strong class="require">필수</strong></label>
<input type="text" id="id" name="id" required="required" placeholder="홍길동"/>
</p>
<p>
<label for="password">암호<strong class="require">필수</strong></label>
<input type="password" id="password" name="password" required="required" placeholder="영문/숫자 4~8자 이내"/>
</p>
<p>
<label for="study_post_title">제목<strong class="require">필수</strong></label>
<input type="text" id="study_post_title" name="study_post_title" required="required" placeholder="글의 제목을 입력하세요."/>
</p>
<p>
<label for="study_post_contents">내용<strong class="require">필수</strong></label>
<textarea id="study_post_contents" name="study_post_contents" cols="74" rows="15" required="required" placeholder="글의 내용을 입력하세요.">
</textarea>
</p>
<p>
<label for="attached_file">파일첨부</label>
<input type="file" id="attached_file" name="attached_file" placeholder="파일첨부"/>
</p>
<div class="btnJoinArea">
<button type="submit"  class="btnOk  btn btn-default">글 등록</button>
<button type="reset" class="btnCancel btn btn-default">취소</button>
<button type="button" value="button" onclick="location.href='./BoardList.study'" class="btnOk btn btn-default">목록
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