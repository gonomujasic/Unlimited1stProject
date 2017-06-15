package Unlimited.min.Notice.model;

public class NoticeDTO {
	
	int no;
	String writer;
	String title;
	String content;
	String attached_file;
	String old_file;
	int read_count;
	String write_date;
	
	
	public String getOld_file() {
		return old_file;
	}
	public void setOld_file(String old_file) {
		this.old_file = old_file;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttached_file() {
		return attached_file;
	}
	public void setAttached_file(String attached_file) {
		this.attached_file = attached_file;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	
	@Override
	public String toString() {
		return "NoticeDTO [no=" + no + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", attached_file=" + attached_file + ", old_file=" + old_file + ", read_count=" + read_count
				+ ", write_date=" + write_date + "]";
	}

}
