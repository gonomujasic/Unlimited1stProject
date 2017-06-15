<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setCharacterEncoding("UTF-8"); %>
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
<div id ="contentsArea">
<section id="titlename" class="qnaBoard">
<h1>공지사항 검색 결과</h1>
<p class="allPost">
검색결과 : &nbsp; <strong><c:out value="${searchlistcount}"/></strong>개
</p>
<table class="table borderedtable">

<c:choose>
<c:when test="${searchlistcount>0}">
<thead>
<tr>
<th scope="col" class="text-center"> 번호</th>
<th scope="col" class="text-center">제목</th>
<th scope="col" class="text-center">글쓴이</th>
<th scope="col" class="text-center">등록</th>
<th scope="col" class="text-center">조회수</th>
</tr>
</thead>
<tbody>
<c:forEach var="search" items="${searchNoticeList}">
<tr>
<td class="text-center"><c:out value="${search.no}"/></td>
<td class="text-center">
<a href="./NoticeDetailService.notice?no=<c:out value="${search.no}"/>">
<c:out value="${search.title}"/></a>
</td>
<td class="text-center"><c:out value="${search.writer}"/></td>
<td class="text-center"><c:out value="${search.write_date}"/></td>
<td class="text-center"><c:out value="${search.read_count}"/></td>
</tr>
</c:forEach>

</tbody>
</c:when>
</c:choose>
</table>
<div align="center">
<table id="boardTableNe" class="table">
<tbody>
<c:if test="${searchlistcount==0}">
<tr>
<td colspan="4"></td>
<td class="text-center">검색된글이없습니다.</td>
</tr>
</c:if>
<tr>
<td colspan="5">
<c:choose>
<c:when test="${page<=1}"> [이전]&nbsp; </c:when>
<c:otherwise>
<a href="./BoardSearch.notice?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp;
</c:otherwise>
</c:choose>
<c:forEach var="start" begin="${startpage}" end="${endpage}" step="1">
<c:choose>
<c:when test="${start eq page}">[<c:out value="${ start}"/>]</c:when>
<c:otherwise>
<a href="./BoardSearch.notice?page=<c:out value="${start}"/>">[<c:out value="${start}"/>]</a>&nbsp;
</c:otherwise>
</c:choose>
</c:forEach>
<c:choose>
<c:when test="${page>=maxpage}">[다음]</c:when>
<c:otherwise>
<a href="./BoardSearch.notice?page=<c:out value="${page+1}"/>">[다음]</a>
</c:otherwise>
</c:choose>
</td>
</tr>
</tbody>
</table>
<div class="btnJoinAreb">
<p class="text-right">
<button type="button" value="button" onclick="location.href='./NoticeListService.notice'" class="btnOk btn btn-default">
목록
</button>
</p>
</div>
</div>
</section>
</div>
</div>
</body>
</html>