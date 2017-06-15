package yeon.board.model;

public class BoardDTO {
private int study_post_index;
private String id;
private String password;
private String study_post_title;
private String study_post_contents;
private String attached_file;
private String old_file;
private int answer_num;
private int answer_lev;
private int answer_seq;
private int study_post_number;
private String study_post_date;
public int getstudy_post_index() {
	return study_post_index;
}
public void setstudy_post_index(int study_post_index) {
	this.study_post_index = study_post_index;
}
public String getid() {
	return id;
}
public void setid(String id) {
	this.id = id;
}
public String getpassword() {
	return password;
}
public void setpassword(String password) {
	this.password = password;
}
public String getstudy_post_title() {
	return study_post_title;
}
public void setstudy_post_title(String study_post_title) {
	this.study_post_title = study_post_title;
}
public String getstudy_post_contents() {
	return study_post_contents;
}
public void setstudy_post_contents(String study_post_contents) {
	this.study_post_contents = study_post_contents;
}
public String getAttached_file() {
	return attached_file;
}
public void setAttached_file(String attached_file) {
	this.attached_file = attached_file;
}
public String getOld_file() {
	return old_file;
}
public void setOld_file(String old_file) {
	this.old_file = old_file;
}
public int getAnswer_num() {
	return answer_num;
}
public void setAnswer_num(int answer_num) {
	this.answer_num = answer_num;
}
public int getAnswer_lev() {
	return answer_lev;
}
public void setAnswer_lev(int answer_lev) {
	this.answer_lev = answer_lev;
}
public int getAnswer_seq() {
	return answer_seq;
}
public void setAnswer_seq(int answer_seq) {
	this.answer_seq = answer_seq;
}
public int getstudy_post_number() {
	return study_post_number;
}
public void setstudy_post_number(int study_post_number) {
	this.study_post_number = study_post_number;
}
public String getstudy_post_date() {
	return study_post_date;
}
public void setstudy_post_date(String study_post_date) {
	this.study_post_date = study_post_date;
}

@Override
public String toString() {
	return "BoardDTO [study_post_index=" + study_post_index + ", id=" + id + ", password=" + password + ", study_post_title="
			+ study_post_title + ", study_post_contents=" + study_post_contents + ", attached_file=" + attached_file
			+ ", old_file=" + old_file + ", answer_num=" + answer_num + ", answer_lev=" + answer_lev + ", answer_seq="
			+ answer_seq + ", study_post_number=" + study_post_number + ", study_post_date=" + study_post_date + "]";
}


}