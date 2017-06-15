package min.unlimited.chatting.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import min.unlimited.chatting.controller.Broadsocket;
import min.unlimited.chatting.dto.ChatRoomDTO;

public class CloseChatService implements ChattingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String chatRoomNum = request.getParameter("chatRoomNum");
		
		Map<String,ChatRoomDTO> chatRoom = Broadsocket.getInstance();
		if(id.equals(chatRoom.get(chatRoomNum).getMentorID())){
			
			chatRoom.get(chatRoomNum).setMentorNick(null);
			chatRoom.get(chatRoomNum).setMentorID(null);
			
		} else{
			
			chatRoom.get(chatRoomNum).setMenteeNick(null);
			chatRoom.get(chatRoomNum).setMenteeID(null);
			
		}
	}
}
