<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>


</head>
<body>
	<div class="container">
	<p class="h1">공지사항</p>
	
		<section class="search">
		<form name="search" action="./NoticeSearchListService.notice" method="post">
			<fieldset>
				<legend>공지 검색</legend>
				<label for="keyword"></label> 
				<select name="keyfield" class="b_search">
					<option value="all" selected="selected">전체 검색</option>
					<option value="subject" <c:if test="${'subject' == keyfield}">selected="selected"</c:if>>제목</option>
					<option value="name" <c:if test="${'name' == keyfield}">selected="selected"</c:if>> 글쓴이</option>
					<option value="content" <c:if test="${'content' == keyfield}">selected="selected"</c:if>>내용</option>
					</select> 
				<input type="search" id="keyword" name="keyword" required="required" placeholder="검색어 입력">
				<button type="submit">검색</button>
			</fieldset>
		</form>
	</section>
	<br>
	<br>
	<br>

	<p>
		전체글: &nbsp;
		<c:out value="${listcount}" />
	</p>
	<c:choose>
		<c:when test="${listcount == 0}">
		등록된 글이 없습니다.
		</c:when>
	<c:otherwise>
		<table class="table table-hover text-center">
				<thead>
					<tr>
						<th class="text-center">번호</th>
						<th class="text-center">제목</th>
						<th class="text-center">글쓴이</th>
						<th class="text-center">등록일</th>
						<th class="text-center">조회수</th>
					</tr>
				</thead>
				<c:forEach var="notice" items="${noticeList}">
					<tr>
						<td class="text-center">${notice.no}</td>
						<td class="text-center"><a href="NoticeDetailService.notice?no=${notice.no}">${notice.title}</a></td>
						<td class="text-center">${notice.writer}</td>
						<td class="text-center">${notice.write_date}</td>
						<td class="text-center">${notice.read_count}</td>
					</tr>

				</c:forEach>
				<tr align ="center">
				<td colspan="5">
				<c:choose>
						<c:when test="${page <= 1}"> [이전]&nbsp; </c:when>
						<c:otherwise>
							<a href="./NoticeListService.notice?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp;
						</c:otherwise>
				</c:choose> 
				<c:forEach var="start" begin="${startpage}" end="${endpage}" step="1">
					<c:choose>
						<c:when test="${start eq page}"> [<c:out value="${start}" />] </c:when>
						<c:otherwise>
						<a href="./NoticeListService.notice?page=<c:out value="${start}" />">[<c:out value="${start}" />] </a>&nbsp;
						</c:otherwise>
						</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${page >= maxpage}">[다음] </c:when>
					<c:otherwise>
						<a href="./NoticeListService.notice?page=<c:out value="${page+1}" />">[다음]</a>
					</c:otherwise>
				</c:choose>
				</td>
				</tr>
				
			</table>
				
		</c:otherwise>
	
	</c:choose>

	<c:if test="${id =='admin'}">
<p class = "text-right"><button onclick='location.href="./NoticeAddForm.notice"' class="btn btn-default">공지작성</button></p>
</c:if>
</div>
</body>
</html>
