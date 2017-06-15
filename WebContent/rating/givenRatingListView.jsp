<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="user-scalable=no, width=device-width, initial-scale=1" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="mobile-web-app-capable" content="yes" />
<link rel="stylesheet" href="../themes/unlimited.min.css" />
<link rel="stylesheet" href="../themes/jquery.mobile.icons.min.css" />
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.2/jquery.mobile.structure-1.4.2.min.css" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
<title>평점 리스트</title>
</head>
<body>
	<div class="preview ui-shadow swatch">
		<div class="ui-header ui-bar-a" data-swatch="a" data-theme="a"
			data-form="ui-bar-a" data-role="header" role="banner">
			<a
				class="ui-btn-left ui-btn ui-btn-icon-notext ui-icon-home ui-btn-corner-all ui-shadow"
				title=" Home " data-form="ui-icon"> Home </a>
			<h1 class="ui-title" tabindex="0" role="heading" aria-level="1">UNLIMITED</h1>
			<a
				class="ui-btn-right ui-btn ui-btn-icon-notext ui-icon-grid ui-btn-corner-all ui-shadow"
				title=" Navigation " data-form="ui-icon"> Navigation </a>
		</div>

		<div class="ui-content ui-page-theme-a" data-form="ui-page-theme-a"
			data-theme="a" role="main">
			<p style="position: relative; text-align:center">내가 준 평점</p>


<c:forEach var="givenRating" items="givenList">
			<ul data-role="listview" data-inset="true">
				<li data-role="list-divider" data-theme="a" data-swatch="a"
					data-form="ui-bar-a">ID: ${givenRating.mentorID }</li>
				<li data-form="ui-body-a" data-swatch="a" data-theme="a"><textarea>와 정말 좋았어요. 최고예요! 짱짱맨!!</textarea>
					<table style="text-align:center">
						<tr>
							<th>속도</th>
							<th>친절</th>
							<th>발음</th>
							<th>내용</th>
							<th>재미</th>
							<th>종합</th>
						</tr>
						<tr>
							<td>${givenRating.talkingSpeed }</td>
							<td>${givenRating.friendliness }</td>
							<td>${givenRating.pronunciation }</td>
							<td>${givenRating.worthyOfContent }</td>
							<td>${givenRating.pleasure }</td>
							<td>${(givenRating.talkingSpeed+givenRating.friendliness+givenRating.pronunciation+givenRating.worthyOfContent+givenRating.pleasure)/5 }</td>
						</tr>
					</table>
					<a href="RatingServlet?command=delete_rating&reviewNumber=${givenRating.reviewNumber }" style="position: relative; left:85%">삭제하기</a>
					</li>
					
			</ul>
</c:forEach>

			<button data-theme="a" data-form="ui-btn-up-a">확인</button>
		</div>
	</div>
	<!--end_swatches-->

</body>
</html>