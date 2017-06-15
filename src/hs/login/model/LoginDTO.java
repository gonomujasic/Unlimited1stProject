package hs.login.model;

/*
 * 정해선
 * 2017-06-05작성
 * 사용할 변수 선언
 * 값이 제대로 들어갔는지 확인하기 위해 마지막에 tostring()을 사용
 */

public class LoginDTO {
	
	private String id;
	private String password;
	private String name;
	private String nickName;
	private String birth;
	private String hp;
	private String email;
	private String gender;
	private int point;
	private int grade_number;
	private String profilePicture;
	private String introduceMySelf;
	private String tempFile;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getGrade_number() {
		return grade_number;
	}

	public void setGrade_number(int grade_number) {
		this.grade_number = grade_number;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getIntroduceMySelf() {
		return introduceMySelf;
	}

	public void setIntroduceMySelf(String introduceMySelf) {
		this.introduceMySelf = introduceMySelf;
	}
	

	public String getTempFile() {
		return tempFile;
	}

	public void setTempFile(String tempFile) {
		this.tempFile = tempFile;
	}

	@Override
	public String toString() {
		return "LoginDTO [id=" + id + ", password=" + password + ", name=" + name + ", nickName=" + nickName
				+ ", birth=" + birth + ", hp=" + hp + ", email=" + email + ", gender=" + gender + ", point=" + point
				+ ", grade_number=" + grade_number + ", profilePicture=" + profilePicture + ", introduceMySelf="
				+ introduceMySelf + "]";
	}

	

	
}
