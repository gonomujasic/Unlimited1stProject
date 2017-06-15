package hs.studypage.model;

public class StudyPageDTO {

	private int chat_index;
	private String start_time;
	private String end_time;
	private String edu_lang;
	private String chat_title;
	private String id;
	private String mentor_id;

	public int getChat_index() {
		return chat_index;
	}

	public void setChat_index(int chat_index) {
		this.chat_index = chat_index;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getEdu_lang() {
		return edu_lang;
	}

	public void setEdu_lang(String edu_lang) {
		this.edu_lang = edu_lang;
	}

	public String getChat_title() {
		return chat_title;
	}

	public void setChat_title(String chat_title) {
		this.chat_title = chat_title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMentor_id() {
		return mentor_id;
	}

	public void setMentor_id(String mentor_id) {
		this.mentor_id = mentor_id;
	}

	@Override
	public String toString() {
		return "StudyPageDTO [chat_index=" + chat_index + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", edu_lang=" + edu_lang + ", chat_title=" + chat_title + ", id=" + id + ", mentor_id=" + mentor_id
				+ "]";
	}

}
