package jk.qna.model;

import java.sql.Date;

public class QnaDTO {
private int qna_number;
private String id;
private String qna_pass;
private String qna_title;
private String qna_contents;
private String attached_file;
private String old_file;
private int answer_num;
private int answer_lev;
private int answer_seq;
private int qna_view_number;
private Date qna_date;

public int getQna_number() {
	return qna_number;
}
public void setQna_number(int qna_number) {
	this.qna_number = qna_number;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getQna_pass() {
	return qna_pass;
}
public void setQna_pass(String qna_pass) {
	this.qna_pass = qna_pass;
}
public String getQna_title() {
	return qna_title;
}
public void setQna_title(String qna_title) {
	this.qna_title = qna_title;
}
public String getQna_contents() {
	return qna_contents;
}
public void setQna_contents(String qna_contents) {
	this.qna_contents = qna_contents;
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
public int getQna_view_number() {
	return qna_view_number;
}
public void setQna_view_number(int qna_view_number) {
	this.qna_view_number = qna_view_number;
}
public Date getQna_date() {
	return qna_date;
}
public void setQna_date(Date qna_date) {
	this.qna_date = qna_date;
}
@Override
public String toString() {
	return "QnaDTO [qna_number=" + qna_number + ", id=" + id + ", qna_pass=" + qna_pass + ", qna_title=" + qna_title
			+ ", qna_contents=" + qna_contents + ", attached_file=" + attached_file + ", old_file=" + old_file
			+ ", answer_num=" + answer_num + ", answer_lev=" + answer_lev + ", answer_seq=" + answer_seq
			+ ", qna_view_number=" + qna_view_number + ", qna_date=" + qna_date + "]";
}
}