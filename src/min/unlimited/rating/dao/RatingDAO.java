package min.unlimited.rating.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import min.unlimited.rating.dto.RatingVO;
import min.unlimited.util.DBManager;

public class RatingDAO {

	private static class RatingDAOSingleton{
		
		private static final RatingDAO INSTANCE = new RatingDAO();
		
	}
	
	public static RatingDAO getInstance(){
		return RatingDAOSingleton.INSTANCE;
	}
	
	public void doRating(RatingVO ratingVO){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int reviewNumber = reviewNumber();
		
		String sql = "insert into RATING_HISTORY "
				+ "(REVIEW_NUMBER, REVIEW_TEXT, TALKING_SPEED, FRIENDLINESS, PRONUNCIATION, WORTHY_OF_CONTENT, PLEASURE, MENTOR_ID, ID) ";
		sql += "values(?,?,?,?,?,?,?,?,?)";
		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNumber);
			pstmt.setString(2, ratingVO.getReviewText());
			pstmt.setInt(3, ratingVO.getTalkingSpeed());
			pstmt.setInt(4, ratingVO.getFriendliness());
			pstmt.setInt(5, ratingVO.getPronunciation());
			pstmt.setInt(6, ratingVO.getWorthyOfContent());
			pstmt.setInt(7, ratingVO.getPleasure());
			pstmt.setString(8, ratingVO.getMentorID());
			pstmt.setString(9, ratingVO.getMenteeID());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		
	}
	
	public ArrayList<RatingVO> receivedRatingList(String mentorID){
		
		ArrayList<RatingVO> receivedList = new ArrayList<RatingVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from RATING_HISTORY when mentor_id = ?";
		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mentorID);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				RatingVO rVO = new RatingVO();
				rVO.setMenteeID(rs.getString("id"));
				rVO.setReviewText(rs.getString("review_text"));
				rVO.setReviewDate(rs.getString("review_date"));
				rVO.setTalkingSpeed(rs.getInt("talking_speed"));
				rVO.setFriendliness(rs.getInt("friendliness"));
				rVO.setPronunciation(rs.getInt("pronunciation"));
				rVO.setWorthyOfContent(rs.getInt("worthy_of_content"));
				rVO.setPleasure(rs.getInt("pleasure"));
				
				receivedList.add(rVO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return receivedList;
		
	}
	
	public ArrayList<RatingVO> givenRatingList(String menteeID){
		
		ArrayList<RatingVO> givenList = new ArrayList<RatingVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from RATING_HISTORY when mentor_id = ?";
		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menteeID);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				RatingVO rVO = new RatingVO();
				rVO.setMenteeID(rs.getString("id"));
				rVO.setReviewText(rs.getString("review_text"));
				rVO.setReviewDate(rs.getString("review_date"));
				rVO.setTalkingSpeed(rs.getInt("talking_speed"));
				rVO.setFriendliness(rs.getInt("friendliness"));
				rVO.setPronunciation(rs.getInt("pronunciation"));
				rVO.setWorthyOfContent(rs.getInt("worthy_of_content"));
				rVO.setPleasure(rs.getInt("pleasure"));
				
				givenList.add(rVO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return givenList;
		
	}
	
	public void deleteRating(int reviewNumber){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from RATING_HISTORY where REVIEW_NUMBER = ?";
		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNumber);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	public int reviewNumber(){
		
		int reviewNumber =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select MAX(REVIEW_NUMBER) from RATING_HISTORY";
		
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				reviewNumber = rs.getInt(1);
				
			};
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			DBManager.close(conn, pstmt, rs);
			
		}
		
		return reviewNumber;
	}
	
	
	
}
