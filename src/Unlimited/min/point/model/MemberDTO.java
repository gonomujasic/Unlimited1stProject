package Unlimited.min.point.model;

public class MemberDTO {
String id;
int point;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getPoint() {
	return point;
}
public void setPoint(int point) {
	this.point = point;
}
@Override
public String toString() {
	return "MemberDTO [id=" + id + ", point=" + point + "]";
}


}
