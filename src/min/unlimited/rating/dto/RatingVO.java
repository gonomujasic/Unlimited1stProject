package min.unlimited.rating.dto;

public class RatingVO {

	private int reviewNumber;
	private String reviewDate;
	private String mentorID;
	private String menteeID;
	private String reviewText;
	private int talkingSpeed;
	private int friendliness;
	private int pronunciation;
	private int worthyOfContent;
	private int pleasure;

	public RatingVO() {
		super();
	}

	public int getReviewNumber() {
		return reviewNumber;
	}

	public void setReviewNumber(int reviewNumber) {
		this.reviewNumber = reviewNumber;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
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

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public int getTalkingSpeed() {
		return talkingSpeed;
	}

	public void setTalkingSpeed(int talkingSpeed) {
		this.talkingSpeed = talkingSpeed;
	}

	public int getFriendliness() {
		return friendliness;
	}

	public void setFriendliness(int friendliness) {
		this.friendliness = friendliness;
	}

	public int getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(int pronunciation) {
		this.pronunciation = pronunciation;
	}

	public int getWorthyOfContent() {
		return worthyOfContent;
	}

	public void setWorthyOfContent(int worthyOfContent) {
		this.worthyOfContent = worthyOfContent;
	}

	public int getPleasure() {
		return pleasure;
	}

	public void setPleasure(int pleasure) {
		this.pleasure = pleasure;
	}

}
