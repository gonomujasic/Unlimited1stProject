package min.unlimited.chatting.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.simple.JSONObject;

import Unlimited.min.point.dao.PointDAO;
import hs.member.DAO.MemberDAO;
import hs.member.model.MemberDTO;
import min.unlimited.chatting.controller.Broadsocket;
import min.unlimited.chatting.dao.ChattingDAO;
import min.unlimited.chatting.dto.ChatRoomDTO;

public class ChatHistoryService implements ChattingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String time = request.getParameter("time");
		String roomNum = request.getParameter("roomNum");

		ChattingDAO chattingDAO = ChattingDAO.getInstance();
		Map<String, ChatRoomDTO> chatRoomsList = Broadsocket.getInstance();
		String mentorID = chatRoomsList.get(roomNum).getMentorID();
		String menteeID = chatRoomsList.get(roomNum).getMenteeID();
		String roomTitle = chatRoomsList.get(roomNum).getRoomTitle();
		String eduLang = chatRoomsList.get(roomNum).getLanguage();
		boolean alreadyIn = false;
		JSONObject jobj = new JSONObject();

		if (time.equals("start")) {// 강의시작 버튼 눌렀을 때

			alreadyIn = chattingDAO.chatHistoryStart(roomNum, mentorID, menteeID, roomTitle, eduLang);

			if (alreadyIn == false) {

				Iterator<Session> iter = chatRoomsList.get(roomNum).getClients().iterator();
				String message = "&" + roomNum;

				while (iter.hasNext()) {

					Session session = iter.next();
					Broadsocket.getbsInstance().onMessage(message, session);

				}
			}

			jobj.put("alreadyIn", alreadyIn);
			String jsonString = jobj.toJSONString();
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(jsonString);

		} else {// 강의종료 버튼 눌렀을 때

			String dealing_point = request.getParameter("dealing_point");
			try {
				HttpSession session = request.getSession();
				int point = (int) session.getAttribute("point");
				String id = (String) session.getAttribute("id");
				pointExchange(mentorID, menteeID, dealing_point);
				int resultPoint = 0;
				
				if(id.equals(mentorID)){//내가 멘토인 경우
					
					resultPoint = point + Integer.parseInt(dealing_point); 
					session.setAttribute("point", resultPoint);
					
				} else if(id.equals(menteeID)){
					
					resultPoint = point - Integer.parseInt(dealing_point); 
					session.setAttribute("point", resultPoint);
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			alreadyIn = chattingDAO.chatHistoryEnd(roomNum);

			if (alreadyIn == false) {

				Iterator<Session> iter = chatRoomsList.get(roomNum).getClients().iterator();
				String message = "*" + roomNum;

				while (iter.hasNext()) {

					Session session = iter.next();
					Broadsocket.getbsInstance().onMessage(message, session);

				}
			}

			jobj.put("alreadyIn", alreadyIn);
			String jsonString = jobj.toJSONString();
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(jsonString);
		}

	}

	public void pointExchange(String mentorID, String menteeID, String point) throws Exception {
		// TODO Auto-generated method stub

		String buyer = menteeID;
		String seller = mentorID;

		int dealing_point = Integer.parseInt(point);
		PointDAO pointDAO = new PointDAO();
		MemberDAO memberDAO = new MemberDAO();

		MemberDTO sellerDTO = memberDAO.memberDetailInfo2(seller);
		// System.out.println(sellerDTO);
		MemberDTO buyerDTO = memberDAO.memberDetailInfo2(buyer);
		// System.out.println(buyerDTO);
		pointDAO.insertSellHistory(dealing_point, buyerDTO, sellerDTO);
		pointDAO.insertBuyHistory(-(double) dealing_point, buyerDTO, sellerDTO);
		pointDAO.chargeMbPt(dealing_point, sellerDTO.getId(), sellerDTO.getPoint());
		pointDAO.chargeMbPt(-(double) dealing_point, buyerDTO.getId(), buyerDTO.getPoint());
		
	}

}
