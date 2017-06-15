package min.unlimited.chatting.dto;

import java.util.Set;

import javax.websocket.Session;

public class ChatRoomDTO {

	private String mentorID;
	private String menteeID;
	private String mentorNick;
	private String menteeNick;
	private String chatRoomNum;
	private String roomTitle;
	private String nationality;
	private String language;
	private String studytime;
	private String studypoint;
	private Set<Session> clients;
	
	public ChatRoomDTO() {
		super();
	}

	public ChatRoomDTO(String mentorID, String mentorNick, String chatRoomNum, String roomTitle, String nationality, String language, String studytime,
			String studypoint) {
		super();
		this.mentorID = mentorID;
		this.mentorNick = mentorNick;
		this.chatRoomNum = chatRoomNum;
		this.roomTitle = roomTitle;
		this.nationality = nationality;
		this.language = language;
		this.studytime = studytime;
		this.studypoint = studypoint;
	}

	public String getMentorID() {
		return mentorID;
	}

	public void setMentorID(String mentorID) {
		this.mentorID = mentorID;
	}

	public String getMenteeID() {
		return menteeID;
	}

	public void setMenteeID(String menteeID) {
		this.menteeID = menteeID;
	}

	public String getMentorNick() {
		return mentorNick;
	}

	public void setMentorNick(String mentorNick) {
		this.mentorNick = mentorNick;
	}

	public String getMenteeNick() {
		return menteeNick;
	}

	public void setMenteeNick(String menteeNick) {
		this.menteeNick = menteeNick;
	}

	public String getChatRoomNum() {
		return chatRoomNum;
	}

	public void setChatRoomNum(String chatRoomNum) {
		this.chatRoomNum = chatRoomNum;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getStudytime() {
		return studytime;
	}

	public void setStudytime(String studytime) {
		this.studytime = studytime;
	}

	public String getStudypoint() {
		return studypoint;
	}

	public void setStudypoint(String studypoint) {
		this.studypoint = studypoint;
	}

	public Set<Session> getClients() {
		return clients;
	}

	public void setClients(Set<Session> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "ChatRoomDTO [chatRoomNum=" + chatRoomNum + ", roomTitle=" + roomTitle + ", nationality=" + nationality
				+ ", language=" + language + ", studytime=" + studytime + ", studypoint=" + studypoint + ", clients="
				+ clients + "]";
	}	
		
	
	
}
