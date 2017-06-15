package min.unlimited.chatting.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import min.unlimited.chatting.controller.Broadsocket;
import min.unlimited.chatting.dto.ChatRoomDTO;

public class NewChattingService implements ChattingService{

	static int roomNum = 0;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		//세션 있다치고
		String mentorID = (String) session.getAttribute("id");
		String mentorNick = (String) session.getAttribute("nickName"); 
		String title= request.getParameter("title");
		String nationality= request.getParameter("nationality");
		String language= request.getParameter("language");
		String studytime= request.getParameter("studytime");
		String studypoint= request.getParameter("studypoint");

		//방번호 만들고
		roomNum++;
		
		String chatRoomNum = Integer.toString(roomNum);
		
		//dto에 방정보 입력하고 
		Map<String, ChatRoomDTO> chatRoom = Broadsocket.getInstance();
		ChatRoomDTO cDTO = new ChatRoomDTO(mentorID, mentorNick,chatRoomNum,title,nationality,language,studytime,studypoint);
		//맵에 방 넣고
		chatRoom.put(chatRoomNum, cDTO);
		//방정보에 클라이언트 세션 담을 셋을 만들어 준다. 
		Broadsocket.getInstance().get(chatRoomNum).setClients(Collections.synchronizedSet(new HashSet<Session>()));
		response.setCharacterEncoding("UTF-8");
		//URLEncoder.encode(text, "UTF-8") ;

		//방번호 채팅창에 보내고
		response.sendRedirect("chatting/chattingRoom.jsp?roomNum="+roomNum+"&mentorID="+URLEncoder.encode(mentorID, "UTF-8")+"&mentorNick="+URLEncoder.encode(mentorNick, "UTF-8")+"&title="
		+URLEncoder.encode(title, "UTF-8")+"&nationality="+URLEncoder.encode(nationality, "UTF-8")+"&language="+URLEncoder.encode(language, "UTF-8")+"&studytime="+URLEncoder.encode(studytime, "UTF-8")+"&studypoint="+URLEncoder.encode(studypoint, "UTF-8"));
		
	}

}
