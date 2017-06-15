package hs.mentor.model;

/*
 * 정해선
 * 2017-06-05작성
 * 사용할 변수 선언
 * 값이 제대로 들어갔는지 확인하기 위해 마지막에 tostring()을 사용
 */

public class MentorDTO {
	
	private String id;
	private String nation;
	private String edu_language1;
	private String edu_language2;
	private String edu_language3;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getEdu_language1() {
		return edu_language1;
	}
	public void setEdu_language1(String edu_language1) {
		this.edu_language1 = edu_language1;
	}
	public String getEdu_language2() {
		return edu_language2;
	}
	public void setEdu_language2(String edu_language2) {
		this.edu_language2 = edu_language2;
	}
	public String getEdu_language3() {
		return edu_language3;
	}
	public void setEdu_language3(String edu_language3) {
		this.edu_language3 = edu_language3;
	}

	@Override
	public String toString() {
		return "MentorDTO [id=" + id + ", nation=" + nation + ", edu_language1=" + edu_language1 + ", edu_language2="
				+ edu_language2 + ", edu_language3=" + edu_language3 + "]";
	}
	

}
