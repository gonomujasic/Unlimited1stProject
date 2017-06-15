package min.unlimited.chatting.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import min.unlimited.chatting.dao.ChattingDAO;
import min.unlimited.chatting.dto.ChatRoomDTO;

public class LoadInfoService implements ChattingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String mentorID = (String) session.getAttribute("id");
System.out.println("세선 들어오나?"+mentorID);		
		ChattingDAO chattingDAO = ChattingDAO.getInstance();
		ChatRoomDTO chatRoomDTO = chattingDAO.getInfo(mentorID);
		
		String nationality = chatRoomDTO.getNationality();
		String language = chatRoomDTO.getLanguage();
		
		JSONObject jobj = new JSONObject();
		jobj.put("nationality",nationality);
		jobj.put("language", language);
		String jsonString = jobj.toJSONString();
		System.out.println(jsonString);
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jsonString);
	}

}
