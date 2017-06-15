<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Qna게시판</title>
<!-- 디바이스에 따라서 보기 방식을 지정한다. -->
<meta name="viewport" content="width=device-witdh, initial-scale=1">
<!-- css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<div id="contentsArea">
<section id="titlename" class="qnaBoard">
<h1>Qna게시판 검색 결과</h1>
<p class="allPost">
검색 글 : &nbsp; <strong><c:out value="${searchlistcount}"/></strong>개
</p>
<table class="table table-hover table-striped">
<caption>Qna게시판 검색</caption>
<c:choose>
<c:when test="${searchlistcount>0}">
<thead>
<tr>
<th scope="col" class="bbsNumber">번호</th>
<th scope="col" class="bbsTitle">제목</th>
<th scope="col" class="bbsAuthor">글쓴이</th>
<th scope="col" class="bbsDate">등록일</th>
<th scope="col" class="bbsHit">조회수</th>
</tr>
</thead>
<tbody>
<c:forEach var="search" items="${searchQnalist}">
<tr>
<td><c:out value="${search.qna_number}"/></td>
<td>
<c:if test="${!empty search.answer_lev}">
<c:forEach var="j" begin="0" end="${search.answer_lev*2}" step="1">&nbsp;</c:forEach>
</c:if>
<a href="./QnaDetail.qna?qna_number=<c:out value="${search.qna_number}"/>">
<c:out value="${search.qna_title}"/></a>
</td>
<td><c:out value="${search.id}"/></td>
<td><c:out value="${search.qna_date}"/></td>
<td><c:out value="${search.qna_view_number}"/></td>
</tr>
</c:forEach>
</tbody>
</c:when>
</c:choose>
</table>
<div align="center">
<table id="boardTableNe" class="boardTableNe">
<tbody>
<c:if test="${searchlistcount==0}">
<tr>
<td colspan="4"></td>
<td>검색된 글이 없습니다.</td>
</tr>
</c:if>
<tr>
<td colspan="5">
<c:choose>
<c:when test="${page <= 1}">[이전]&nbsp;</c:when>
<c:otherwise>
<a href="../QnaSearch.qna?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp;
</c:otherwise>
</c:choose>
<c:forEach var="start" begin="${startpage}" end="${endpage}" step="1">
<c:choose>
<c:when test="${start eq page}">[<c:out value="${start}"/>]</c:when>
<c:otherwise>
<a href="../QnaSearch.qna?page=<c:out value="${start}"/>">[<c:out value="${start}"/>]</a>&nbsp;
</c:otherwise>
</c:choose>
</c:forEach>
<c:choose>
<c:when test="${page >= maxpage}">[다음]</c:when>
<c:otherwise>
<a href="../QnaSearch.qna?page=<c:out value="${page+1}"/>">[다음]</a>
</c:otherwise>
</c:choose>
</td>
</tr>
</tbody>
</table>
<div class="btn-group-sm">
<button type="button" value="button" onclick="location.href='./QnaList.qna'" class="btnOk btn btn-default">
 목록
</button>
</div>
</div>
</section>
</div>
</div>
</body>
</html>