package min.unlimited.chatting.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.simple.JSONObject;

import min.unlimited.chatting.controller.Broadsocket;
import min.unlimited.chatting.dto.ChatRoomDTO;

public class EnterChattingService implements ChattingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String menteeID = (String) session.getAttribute("id");
		String menteeNick = (String) session.getAttribute("nickName");
		String chatRoomNum = request.getParameter("chatRoomNum");
		Map<String, ChatRoomDTO> chatRoom = Broadsocket.getInstance();
		String answer;

		if (chatRoom.get(chatRoomNum).getClients().size() == 2) {
			// 방에 두명이 들어 있을 때
			answer = "!";

		} else if (menteeID == null) {
			// 회원이 아닐 때
			answer = "@";

		} else if (chatRoom.get(chatRoomNum).getMentorID() == menteeID
				|| chatRoom.get(chatRoomNum).getMenteeID() == menteeID) {
			//자기가 방에 들어가 있는 경우
			answer = "#";
		} else {

			//방에 입장할 경우
			chatRoom.get(chatRoomNum).setMenteeNick(menteeNick);
			chatRoom.get(chatRoomNum).setMenteeID(menteeID);
			
			answer = "$";
		}
		JSONObject jobj = new JSONObject();
		jobj.put("answer", answer);
		String jsonString = jobj.toJSONString();
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jsonString);
	}
}
