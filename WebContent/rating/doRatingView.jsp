<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
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
<title>평점 매기기</title>
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
				<input type="hidden" name="mentorID" value="">
				<input type="hidden" name="menteeID" value="">
				
				<p>후기 입력</p>
				<textarea data-theme="a" data-form="ui-body-a" name="reviewText"></textarea>

			</div>
			<br>

			<ul data-role="listview" data-inset="true">
				<li data-role="list-divider" data-theme="a" data-swatch="a"
					data-form="ui-bar-a">평점</li>
				<li data-form="ui-body-a" data-swatch="a" data-theme="a">


					<p>말속도</p>
					<div data-role="fieldcontain">
						<input type="range" name="talkingSpeed" value="3" min="1" max="5"
							data-form="ui-body-a" data-theme="a" data-highlight="true" />
					</div>
					<p>친절</p>
					<div data-role="fieldcontain">
						<input type="range" name="friendliness" value="3" min="1" max="5"
							data-form="ui-body-a" data-theme="a" data-highlight="true" />
					</div>
					<p>발음</p>
					<div data-role="fieldcontain">
						<input type="range" name="pronunciation" value="3" min="1" max="5"
							data-form="ui-body-a" data-theme="a" data-highlight="true" />
					</div>
					<p>수업내용</p>
					<div data-role="fieldcontain">
						<input type="range" name="worthyOfContent" value="3" min="1" max="5"
							data-form="ui-body-a" data-theme="a" data-highlight="true" />
					</div>
					<p>재미</p>
					<div data-role="fieldcontain">
						<input type="range" name="pleasure" value="3" min="1" max="5"
							data-form="ui-body-a" data-theme="a" data-highlight="true" />
					</div>
				</li>

			</ul>
			
			<button data-theme="a" data-form="ui-btn-up-a">완료</button>
		</div>
	</div>
	<!--end_swatches-->

</body>
</html>