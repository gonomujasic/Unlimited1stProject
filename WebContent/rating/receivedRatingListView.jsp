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

			<div data-theme="a" data-form="ui-body-a"
				class="ui-body ui-body-a ui-corner-all">
				<p>현재 나의 평점</p>
				<table>
					<tr>
						<th>속도</th>
						<th>친절</th>
						<th>발음</th>
						<th>내용</th>
						<th>재미</th>
						<th>종합</th>
					</tr>
					<tr>
						<td>${individualRating[0]}</td>
						<td>${individualRating[1]}</td>
						<td>${individualRating[2]}</td>
						<td>${individualRating[3]}</td>
						<td>${individualRating[4]}</td>
						<td>${individualRating[5]}</td>
					</tr>
				</table>

			</div>

			<c:forEach var="rating" items="${receivedList }">
				<ul data-role="listview" data-inset="true">
					<li data-role="list-divider" data-theme="a" data-swatch="a"
						data-form="ui-bar-a">${rating.menteeID}</li>
					<li data-form="ui-body-a" data-swatch="a" data-theme="a">
					<textarea>${rating.reviewText }</textarea>
						<table>
							<tr>
								<th>속도</th>
								<th>친절</th>
								<th>발음</th>
								<th>내용</th>
								<th>재미</th>
								<th>종합</th>
							</tr>
							<tr>
								<td>${rating.talkingSpeed }</td>
								<td>${rating.friendliness }</td>
								<td>${rating.pronunciation }</td>
								<td>${rating.worthyOfContent }</td>
								<td>${rating.pleasure }</td>							
								<td>${(rating.talkingSpeed+rating.friendliness+rating.pronunciation+rating.worthyOfContent+rating.pleasure)/5 }</td>							
								</tr>
						</table></li>
				</ul>
			</c:forEach>
			<button data-theme="a" data-form="ui-btn-up-a">확인</button>
		</div>
	</div>
	<!--end_swatches-->

</body>
</html>