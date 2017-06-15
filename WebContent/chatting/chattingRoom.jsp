<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@page import="java.net.URLDecoder"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link href="../boot/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="../boot/js/bootstrap.min.js"></script>
<title>채팅방</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
</head>

<body onbeforeunload="closePage()">
	<div>
		<table class="table">
			<%
				String rtitle = request.getParameter("title");
				String title = URLDecoder.decode(rtitle, "UTF-8");
			%>
			<tr>
				<span><td><input type="text" id="title"
						value="<%=title%>" readonly="readonly"></td></span>
			</tr>

			</div>
			<div>
				<tr>
					<td><span><input type="text" id="nationality"
							value="${param.nationality}" readonly="readonly"></span> <span><input
							type="text" id="language" value="${param.language}"
							readonly="readonly"></span> <span><input type="text"
							id="studytime" value="${param.studytime}" readonly="readonly">분</span></td>
				</tr>
				<tr>
					<td><span><input type="text" id="studypoint"
							value="${param.studypoint}" readonly="readonly"></span></td>
				</tr>

			</div>

			<tr>
				<td><textarea id="messageWindow" rows="30" cols="55"
						readonly="readonly"></textarea></td>
			</tr>
			<tr>
				<td><input id="inputMessage" type="text" /> <input
					type="submit" value="send" onclick="send()" /></td>
			</tr>

			<div>
				<tr>
					<td><span id="countTimeMinute"></span>분 <span
						id="acountTimeSecond"></span>초 <input type="button" id="start"
						class="btnOk btn btn-default" value="커뮤니케이션"> <input
						type="button" id="end" class="btnOk btn btn-default"
						value="커뮤니케이션 종료"></td>
				</tr>
			</div>
		</table>
	</div>
	<input type="hidden" id="nickName" value="${sessionScope.nickName }">
	<script type="text/javascript">
		var temp = location.href.split("?");
		var data = temp[1].split("&");
		var sentence = data[0].split("=");
		var roomNum = sentence[1];

		var closePage = function() {

			finalsend();

			$
					.ajax({

						url : "../ChattingServlet?command=closechat&roomNum="
								+ roomNum,
						type : "GET",
						success : function(data) {

						}

					});

		}

		//닉네임 작업해야함.
		var nickName = $("#nickName").val();
		var textarea = document.getElementById("messageWindow");
		var webSocket = new WebSocket(
				'ws://192.168.0.25:8080/Unlimited1stProject/broadcasting');
		var inputMessage = document.getElementById('inputMessage');

		webSocket.onerror = function(event) {
			onError(event)
		};
		webSocket.onopen = function(event) {
			onOpen(event)
		};
		webSocket.onmessage = function(event) {
			onMessage(event)
		};

		var timer;
		var minute = 1;/* $("#studytime").val() * 1 */
		var second = 0;
		var divinedstudypoint = Math.round($("#studypoint").val() / 10);

		function onMessage(event) {

			if (event.data == "&") {

				textarea.value += "커뮤니케이션을 시작합니다." + "\n";

				timer = setInterval(function() {

					$("#countTimeMinute").html(minute);
					$("#acountTimeSecond").html(second);

					second++;

					if (second == 60) {

						minute++;
						second = 0;

					}
				}, 1000);

			} else if (event.data == "*") {

				clearInterval(timer);

				textarea.value += "커뮤니케이션을 종료합니다.\n";
				textarea.value += "커뮤니케이션의 대가로 " + minute * divinedstudypoint
						+ "포인트가 거래되었습니다." + "\n";
				/* $("#countTimeMinute").html(timerInfo.minute);
				$("#acountTimeSecond").html(timerInfo.second); */

			} else {

				textarea.value += event.data + "\n";
			}
		}

		function onOpen(event) {
			textarea.value += "연결 성공\n";
			$(document).ready(function() {
				firstsend(roomNum)
			});

		}
		function onError(event) {
			alert(event.data);
		}

		function send() {
			textarea.value += nickName + " : " + inputMessage.value + "\n";
			var message = roomNum + "%" + nickName + " : " + inputMessage.value;
			webSocket.send(message);
			inputMessage.value = "";
		}

		function firstsend() {
			var message = "@" + roomNum + "%" + nickName;
			webSocket.send(message);

		}

		function finalsend() {
			var message = "^" + roomNum + "%" + nickName;
			webSocket.send(message);

		}

		$("#start").click(function() {
			$.ajax({

				url : "../ChattingServlet",
				type : "POST",
				data : {
					command : "chathistory",
					time : "start",
					roomNum : roomNum
				},
				success : function(data) {

					var result = data.alreadyIn;

					//이미 start키가 눌러진 경우
					if (result == true) {

						alert("이미 커뮤니케이션 시작버튼을 눌렀습니다.");

					}
				}
			});
		});

		$("#end").click(function() {

			var dealing_point = minute * divinedstudypoint;

			$.ajax({

				url : "../ChattingServlet",
				type : "POST",
				data : {
					command : "chathistory",
					time : "end",
					roomNum : roomNum,
					dealing_point : dealing_point
				},
				success : function(data) {

					var result = data.alreadyIn;

					//이미 end키가 눌러진 경우
					if (result == true) {

						alert("이미 커뮤니케이션 종료버튼을 눌렀습니다.");

					}
				}
			});
		});

		$(document).ready(function() {
			$("input[id=inputMessage]").keydown(function(key) {

				if (key.keyCode == 13) {//키가 13이면 실행 (엔터는 13)
					send();
				}

			});

		});
	</script>
</body>

</html>