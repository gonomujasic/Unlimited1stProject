<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
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
<div id="contentsArea">
<section id="titlename">
			<table class="table table-bordered">
<h1>공부 게시판 글 삭제</h1>
<p class="formSign">
<strong class="require">필수</strong>는 반드시 입력해야 하는 항목입니다.
</p>
<form id="joinForm" name="deleteForm" action="./BoardDeleteService.study?study_post_index=${param.study_post_index}" method="post">
<fieldset>
<legend>공부 게시판 글 삭제</legend>
<p>
<label for="password">글 입력 비밀번호<strong class="require">필수</strong></label>
<input  type="password" id="password" name="password" required="required" placeholder="비밀번호 입력" size="12"/>
</p>
<div class="btnJoinArea">
<button type="submit" class="btnOk btn btn-default">삭제</button>
<button type="reset" class="btnCancel btn btn-default">취소</button>
<button type="button" value="button" onclick="location.href='./BoardList.study'" class="btnOk btn btn-default">
목록
</button>
</div>
</fieldset>
</form>
</table>
</section>
</div>
</body>
</html>