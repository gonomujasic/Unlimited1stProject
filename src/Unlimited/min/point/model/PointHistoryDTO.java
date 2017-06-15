package Unlimited.min.point.model;

public class PointHistoryDTO {
	int no;
	int dealing_point;
	int point_before;
	int point_after;
	String dealing_date;
	String why;
	String opponent;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getDealing_point() {
		return dealing_point;
	}
	public void setDealing_point(int dealing_point) {
		this.dealing_point = dealing_point;
	}
	public int getPoint_before() {
		return point_before;
	}
	public void setPoint_before(int point_before) {
		this.point_before = point_before;
	}
	public int getPoint_after() {
		return point_after;
	}
	public void setPoint_after(int point_after) {
		this.point_after = point_after;
	}
	public String getWhy() {
		return why;
	}
	public void setWhy(String why) {
		this.why = why;
	}
	public String getDealing_date() {
		return dealing_date;
	}
	public void setDealing_date(String dealing_date) {
		this.dealing_date = dealing_date;
	}
	public String getOpponent() {
		return opponent;
	}
	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	
	@Override
	public String toString() {
		return "PointHistoryDTO [no=" + no + ", dealing_point=" + dealing_point + ", point_before=" + point_before
				+ ", point_after=" + point_after + ", dealing_date=" + dealing_date + ", why=" + why + ", opponent="
				+ opponent + "]";
	}

	
	
}
