package min.unlimited.chatting.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.unlimited.chatting.controller.Broadsocket;
import min.unlimited.chatting.dto.ChatRoomDTO;

public class LoadListService implements ChattingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, ChatRoomDTO> chatRooms = Broadsocket.getInstance();
		ArrayList<ChatRoomDTO> chatRoomsList = new ArrayList<ChatRoomDTO>(chatRooms.values());
		
		//객체를 먼저 json으로 만들고 json리스트에 담는 법.  
		System.out.println(chatRoomsList.toString());
		
	//	ObjectMapper objectMapper = new ObjectMapper();
	//	objectMapper.configure
//		(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
//		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	//	String str = objectMapper.writeValueAsString(chatRoomsList);
		
		//System.out.println(str);
//		ObjectMapper mapper = new ObjectMapper();
//		//mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//		// do various things, perhaps:
//		String someJsonString = mapper.writeValueAsString(chatRoomsList);
//		ChatRoomDTO someClassInstance = mapper.readValue(someJsonString, ChatRoomDTO.class);
		/*Gson gsonObj = new Gson();
		String str = gsonObj.toJson(chatRoomsList);
		System.out.println(str);*/
		/*System.out.println(gsonObj.toJson(chatRoomsList));*/
		request.setAttribute("chatRoomsList", chatRoomsList);
		response.setContentType("application/x-json; charset=UTF-8");
//		response.getWriter().print(str);
		RequestDispatcher rd = request.getRequestDispatcher("chatting/chattingRoomList.jsp");
		rd.forward(request, response);
		
		
	}

}
