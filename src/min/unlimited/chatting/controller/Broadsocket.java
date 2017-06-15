package min.unlimited.chatting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import min.unlimited.chatting.dto.ChatRoomDTO;

@ServerEndpoint("/broadcasting") // 클라이언트에서 접속하는 서버 주소
public class Broadsocket {

	// set은 중복없이 담는 컬렉션. hashset은 순서 없이 담는 형태.

	private static class chatRoomsSingleton {
		private static Map<String, ChatRoomDTO> chatRooms = new HashMap<String, ChatRoomDTO>();
		private static Broadsocket bs = new Broadsocket();
	}

	public static Map<String, ChatRoomDTO> getInstance() {

		return chatRoomsSingleton.chatRooms;

	}

	public static Broadsocket getbsInstance() {
		return chatRoomsSingleton.bs;
	}

	@OnMessage // 메시지가 도착했을 경우 처리
	public void onMessage(String message, Session session) throws IOException {

		if (message.substring(0, 1).equals("@")) {// 첫접속.
			int index = message.indexOf("%");
			String roomNum = message.substring(1, index);
			String nickName = message.substring(index + 1);
			String entranceMessage = nickName + "님이 입장하셨습니다.";
			// 채팅 방에 들어가게 만듬.
			Set<Session> clients = chatRoomsSingleton.chatRooms.get(roomNum).getClients();
			clients.add(session);

			for (Session client : clients) {
				client.getBasicRemote().sendText(entranceMessage);
			}

		} else if (message.substring(0, 1).equals("^")) {// 채팅 아웃
			int index = message.indexOf("%");
			String roomNum = message.substring(1, index);
			String nickName = message.substring(index + 1);
			String exitMessage = nickName + "님이 퇴장하셨습니다.";

			Set<Session> clients = chatRoomsSingleton.chatRooms.get(roomNum).getClients();
			clients.remove(session);

			for (Session client : clients) {
				client.getBasicRemote().sendText(exitMessage);
			}
			if (clients.isEmpty()) {
				chatRoomsSingleton.chatRooms.remove(roomNum);
			}

		} else if (message.substring(0, 1).equals("&")) {// 커뮤니케이션 시작

			String roomNum = message.substring(1);
			String signMessage = "&";
			Set<Session> clients = chatRoomsSingleton.chatRooms.get(roomNum).getClients();

			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(signMessage);
				}
			} 
		} else if (message.substring(0, 1).equals("*")) {// 커뮤니케이션 종료

			String roomNum = message.substring(1);
			String signMessage = "*";
			Set<Session> clients = chatRoomsSingleton.chatRooms.get(roomNum).getClients();

			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(signMessage);
				}
			}
		} else {// 대화

			int idx = message.indexOf("%");
			String roomNum = message.substring(0, idx);
			String realMessage = message.substring(idx + 1);
			Set<Session> clients = getInstance().get(roomNum).getClients();
 
			synchronized (clients) {

				// Iterate over the connected sessions
				// and broadcast the received message
				for (Session client : clients) {
					if (!client.equals(session)) {
						client.getBasicRemote().sendText(realMessage);
					}
				}
			}
		}

	}

	@OnOpen // 클라이언트가 접속할 때
	public void onOpen(Session session) {
		// Add session to the connected sessions set
		/*
		 * System.out.println(session.getId()); clients.add(session);
		 */
	}

	@OnClose // 클라이언트가 접속 끊을 때
	public void onClose(Session session) {

	}

}