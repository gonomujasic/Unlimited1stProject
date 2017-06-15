package hs.studypage.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import hs.member.model.MemberDTO;
import hs.studypage.model.StudyPageDTO;
import min.unlimited.util.DBManager;

public class StudyPageDAO {
	

	// 지난 강의 내역을 불러들임
	public List<StudyPageDTO> historyInfo(StudyPageDTO studyPageDTO) {
		List<StudyPageDTO> list = new ArrayList<StudyPageDTO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from lecture_history where mentor_id=? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, studyPageDTO.getMentor_id());
			// System.out.println("studypageDAO에서 멘토아이디값" +
			// studyPageDTO.getMentor_id());
			resultSet = preparedStatement.executeQuery();
			// System.out.println(resultSet);
			while (resultSet.next()) {
				studyPageDTO = new StudyPageDTO();
				studyPageDTO.setChat_index(resultSet.getInt("chat_index"));
				studyPageDTO.setStart_time(resultSet.getString("start_time"));
				studyPageDTO.setEnd_time(resultSet.getString("end_time"));
				studyPageDTO.setEdu_lang(resultSet.getString("edu_lang"));
				studyPageDTO.setChat_title(resultSet.getString("chat_title"));
				studyPageDTO.setId(resultSet.getString("id"));
				studyPageDTO.setMentor_id(resultSet.getString("mentor_id"));
				System.out.println("studyPageDAO의 historyInfo___" + studyPageDTO);
				 list.add(studyPageDTO);
			}
			System.out.println(list);
			return list;

		} catch (Exception e) {
			System.out.println("지난 강의 내역 불러오기 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}// end of memberDetailInfo
	
	// 지난 수강 내역을 불러들임
		public List<StudyPageDTO> historyStudyInfo(StudyPageDTO studyPageDTO) {
			List<StudyPageDTO> list = new ArrayList<StudyPageDTO>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DBManager.getConnection();
				String sql = "select * from lecture_history where id=? ";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, studyPageDTO.getId());
				// System.out.println("studypageDAO에서 멘토아이디값" +
				// studyPageDTO.getMentor_id());
				resultSet = preparedStatement.executeQuery();
				// System.out.println(resultSet);
				while (resultSet.next()) {
					studyPageDTO = new StudyPageDTO();
					studyPageDTO.setChat_index(resultSet.getInt("chat_index"));
					studyPageDTO.setStart_time(resultSet.getString("start_time"));
					studyPageDTO.setEnd_time(resultSet.getString("end_time"));
					studyPageDTO.setEdu_lang(resultSet.getString("edu_lang"));
					studyPageDTO.setChat_title(resultSet.getString("chat_title"));
					studyPageDTO.setId(resultSet.getString("id"));
					studyPageDTO.setMentor_id(resultSet.getString("mentor_id"));
					System.out.println("studyPageDAO의 historyStudyInfo___" + studyPageDTO);
					 list.add(studyPageDTO);
				}
				System.out.println(list);
				return list;

			} catch (Exception e) {
				System.out.println("지난 강의 내역 불러오기 실패 : " + e);
			} finally {
				DBManager.close(connection, preparedStatement, resultSet);
			}
			return null;
		}// end of memberDetailInfo
}
