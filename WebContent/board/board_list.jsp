<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공부 게시판</title>
<!-- css 설정 -->
<link href="./boot/css/bootstrap.min.css"  rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>

<link href="../boot/css/bootstrap.min.css"  rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="../boot/js/bootstrap.min.js"></script>
<!-- <link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon"> -->
<!-- <link rel="stylesheet" type="text/css" href="./css/jboard.css"> -->
</head>
<body>
<div class="container">
<div id="contentsArea">
<section id="titlename" class="qnaBoard">
<h1>공부 게시판</h1>
<div id="infoArea">
<section class="search">
<form name="search" action="./BoardSearchList.study" method="post">
<fieldset>
<legend>검색</legend>
<label for="keyword"></label>
<select name="keyfield" class="b_search">
<option value="all" selected="selected">전체 검색</option>
<option value="study_post_title"<c:if test="${'study_post_title' == keyfield}">selected="selected"</c:if>>제목</option>
<option value="id"<c:if test="${'id' == keyfield}">selected="selected"</c:if>>글쓴이</option>
<option value="study_post_content"<c:if test="${'study_post_contents' == keyfield}">selected="selected"</c:if>>내용</option>
</select>
<input type="search" id="keyword" name="keyword" required="required" placeholder="검색어 입력">
<button type="submit">검색</button>
</fieldset>
</form>
</section>
</div>
<p class="allPost">
전체 글 : &nbsp; <strong><c:out value="${listcount}"/> </strong>개
</p>
<table class="table">
<caption>공부 게시판 리스트</caption>
<c:choose>
<c:when test="${listcount>0}">
<thead>
<tr>
<th scope="col" class="bbsNumber">번호</th>
<th scope="col" class="bbsTitle">제목</th>
<th scope="col" class="bbsAuthor">글쓴이</th>
<th scope="col" class="bbsDate">등록일</th>
<th scope="col" class="bbsHit">조회수</th>
</tr>
</thead>
<c:forEach var="board" items="${boardList}" varStatus="status">
<tbody>
<tr>
<td><c:out value="${board.study_post_index}"/></td>
<td>
<c:if test="${!empty board.answer_lev}">
<c:forEach var="j" begin="0" end="${board.answer_lev*2}" step="1">&nbsp;
</c:forEach>
</c:if>
<a href="./BoardDetail.study?study_post_index=<c:out value="${board.study_post_index}"/>">
<c:out value="${board.study_post_title}"/>
</a>
</td>
<td><c:out value="${board.id}"/></td>
<td><c:out value="${board.study_post_date}"/></td>
<td><c:out value="${board.study_post_number}"/></td>
</tr>
</tbody>
</c:forEach>
</c:when>
</c:choose>
</table>
<div align="center">
<table id="boardTableNe" class="boardTableNe">
<tbody>
<c:if test="${searchlistcount==0}">
<tr>
<td colspan="4"></td>
<td>등록된 글이 없습니다.</td>
</tr>
</c:if>
<tr>
<td colspan="5">
<c:choose>
<c:when test="${page <= 1}">[이전]&nbsp;</c:when>
<c:otherwise>
<a href="../BoardList.study?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp;
</c:otherwise>
</c:choose>
<c:forEach var="start" begin="${startpage}" end="${endpage}" step="1">
<c:choose>
<c:when test="${start eq page}">[<c:out value="${start}"/>]</c:when>
<c:otherwise>
<a href="../BoardList.study?page=<c:out value="${start}"/>">[<c:out value="${start}"/>]</a>&nbsp;
</c:otherwise>
</c:choose>
</c:forEach>
<c:choose>
<c:when test="${page >= maxpage}">[다음]</c:when>
<c:otherwise>
<a href="../BoardList.study?page=<c:out value="${page+1}"/>">[다음]</a>
</c:otherwise>
</c:choose>
</td>
</tr>
</tbody>
</table>
<div class="btnJoinArea">
<button type="button" value="button" onclick="location.href='./BoardWrite.study'" class="btnOk btn btn-default">
글쓰기
</button>
</div>
</div>
</section>
</div>
</div>
</body>
</html>