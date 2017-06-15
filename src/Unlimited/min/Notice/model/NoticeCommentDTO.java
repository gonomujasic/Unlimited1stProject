package Unlimited.min.Notice.model;

public class NoticeCommentDTO {
	int comment_no;
	int no; //공지사항 번호
	String writer;
	String content;
	int answer_num;
	int answer_lev;
	int answer_seq;
	String write_date;
	
	
	public int getComment_No() {
		return comment_no;
	}
	public void setComment_No(int commnet_no) {
		this.comment_no = commnet_no;
	}
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	
	@Override
	public String toString() {
		return "NoticeCommentDTO [comment_no=" + comment_no + ", no=" + no + ", writer=" + writer + ", content="
				+ content + ", answer_num=" + answer_num + ", answer_lev=" + answer_lev + ", answer_seq=" + answer_seq
				+ ", write_date=" + write_date + "]";
	}
	
}
