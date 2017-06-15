package hs.mentor.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import hs.mentor.model.MentorDTO;
import min.unlimited.util.DBManager;

public class MentorDAO {
	

	// 멘토 정보 등록
	public boolean mentorAddInfo(MentorDTO mentorDTO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// ResultSet resultSet = null;
		int count = 0;

		try {

			connection = DBManager.getConnection();
			String sql = " insert into mentor(id,nation,edu_language1,edu_language2,edu_language3 ) ";
			sql += "  values (?,?,?,?,?)";
			// System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mentorDTO.getId());
			// System.out.println("mentorAddInfo:" + mentorDTO.getId());
			preparedStatement.setString(2, mentorDTO.getNation());
			preparedStatement.setString(3, mentorDTO.getEdu_language1());
			preparedStatement.setString(4, mentorDTO.getEdu_language2());
			preparedStatement.setString(5, mentorDTO.getEdu_language3());

			count = preparedStatement.executeUpdate();
			// System.out.println(count);
			if (count == 0) {
				System.out.println("멘토 정보 등록.");
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("멘토 정보 등록 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return false;
	}// end of memberRegist

	// 기존에 입력된 회원 정보를 불러들임
	public MentorDTO mentorDetailInfo(MentorDTO mentorDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from mentor where id= ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mentorDTO.getId());
			//System.out.println(mentorDTO.getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				mentorDTO = new MentorDTO();
				mentorDTO.setNation(resultSet.getString("nation"));
				mentorDTO.setEdu_language1(resultSet.getString("edu_language1"));
				mentorDTO.setEdu_language2(resultSet.getString("edu_language2"));
				mentorDTO.setEdu_language3(resultSet.getString("edu_language3"));

				// System.out.println(" mentorDetailInfo 의" + mentorDTO);

			}
			return mentorDTO;
		} catch (Exception e) {
			System.out.println("멘토 정보 보기 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}// end of mentorDetailInfo

	// 멘토 정보 수정페이지
	public boolean mentorInfoModify(MentorDTO mentorDTO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBManager.getConnection();
			String sql = "update mentor set nation=?, edu_language1=?, edu_language2=?, edu_language3=? where id=?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, mentorDTO.getNation());
			preparedStatement.setString(2, mentorDTO.getEdu_language1());
			preparedStatement.setString(3, mentorDTO.getEdu_language2());
			preparedStatement.setString(4, mentorDTO.getEdu_language3());
			preparedStatement.setString(5, mentorDTO.getId());

			// System.out.println("MentorDAO의 mentorInfoModify : " + mentorDTO);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("회원 정보 수정 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return false;

	}// end of mentorberInfoModify

	// 멘토 정보 삭제
	public boolean mentorInfoDelete(MentorDTO mentorDTO) {
		int result2 = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "delete from mentor where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mentorDTO.getId());
			result2 = preparedStatement.executeUpdate();
			// System.out.println("mentorDAO에서 delete "+mentorDTO);
			if (result2 == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("멘토 정보 삭제 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return false;
	}// end of memberInfoDelete
}
