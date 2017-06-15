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

public class SearchListService implements ChattingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, ChatRoomDTO> chatRooms = Broadsocket.getInstance();
		ArrayList<ChatRoomDTO> chatRoomsList = new ArrayList<ChatRoomDTO>(chatRooms.values());

		String language = request.getParameter("language");
		String nationality = request.getParameter("nationality");
		String studytime = request.getParameter("studytime");
		String studypoint = request.getParameter("studypoint");

		ArrayList<ChatRoomDTO> searchResult = new ArrayList<ChatRoomDTO>();

		for (int i = 0; i < chatRoomsList.size(); i++) {

			if (language.equals(chatRoomsList.get(i).getLanguage())
					&& nationality.equals(chatRoomsList.get(i).getNationality())
					&& studytime.equals(chatRoomsList.get(i).getStudytime())) {

				if (studypoint.equals("1000") && Integer.parseInt(chatRoomsList.get(i).getStudypoint()) <= 1000) {

					searchResult.add(chatRoomsList.get(i));

				} else if (studypoint.equals("2000") && Integer.parseInt(chatRoomsList.get(i).getStudypoint()) <= 2000
						&& Integer.parseInt(chatRoomsList.get(i).getStudypoint()) > 1000) {

					searchResult.add(chatRoomsList.get(i));

				} else if (studypoint.equals("3000") && Integer.parseInt(chatRoomsList.get(i).getStudypoint()) <= 3000
						&& Integer.parseInt(chatRoomsList.get(i).getStudypoint()) > 2000) {

					searchResult.add(chatRoomsList.get(i));

				} else if (studypoint.equals("4000") && Integer.parseInt(chatRoomsList.get(i).getStudypoint()) > 3000) {

					searchResult.add(chatRoomsList.get(i));

				}
			}
		}

		request.setAttribute("chatRoomsList", searchResult);
		response.setContentType("application/x-json; charset=UTF-8");

		RequestDispatcher rd = request.getRequestDispatcher("chatting/chattingRoomList.jsp");
		rd.forward(request, response);
	}

}
