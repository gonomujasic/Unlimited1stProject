package min.unlimited.chatting.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import min.unlimited.chatting.dto.ChatRoomDTO;
import min.unlimited.util.DBManager;

public class ChattingDAO {

	private static class CDAOSingleton {

		private static final ChattingDAO INSTANCE = new ChattingDAO();

	}

	public static ChattingDAO getInstance() {

		return CDAOSingleton.INSTANCE;

	}

	public ChatRoomDTO getInfo(String mentorID) {

		ChatRoomDTO crDTO = new ChatRoomDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select NATION, EDU_LANGUAGE1 from MENTOR where id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mentorID);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				crDTO.setNationality(rs.getString("NATION"));
				crDTO.setLanguage(rs.getString("EDU_LANGUAGE1"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBManager.close(conn, pstmt, rs);

		return crDTO;

	}

	public boolean checkStart(String roomNum) {

		boolean alreadyIn = false;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from LECTURE_HISTORY WHERE CHAT_INDEX = " + roomNum;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// ResultSet이 비어있는지 확인하려면 getter로 값을 하나 받고 다음에 wasNull로 그 값이
				// null인지 확인해야.
				int var = rs.getInt("chat_index");
				if (!rs.wasNull()) {
					alreadyIn = true;

				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return alreadyIn;
	}

	public boolean chatHistoryStart(String roomNum, String mentorID, String menteeID, String roomTitle,
			String eduLang) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean alreadyIn = checkStart(roomNum);

		if (alreadyIn == false) {

			String sql = "insert into LECTURE_HISTORY (CHAT_INDEX, START_TIME, EDU_LANG, CHAT_TITLE, ID, MENTOR_ID)"
					+ " values(?,SYSDATE,?,?,?,?)";

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
  
				pstmt.setInt(1, Integer.parseInt((roomNum)));
				pstmt.setString(2, eduLang);
				pstmt.setString(3, roomTitle);
				pstmt.setString(4, menteeID);
				pstmt.setString(5, mentorID);
				pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
		return alreadyIn;
	}

	public boolean checkEnd(String roomNum) {

		boolean alreadyIn = false;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select END_TIME from LECTURE_HISTORY WHERE CHAT_INDEX = " + roomNum;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Date date = rs.getDate("end_time");

				if (!rs.wasNull()) {

					alreadyIn = true;

				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return alreadyIn;
	}

	public boolean chatHistoryEnd(String roomNum) {
		boolean alreadyIn = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		alreadyIn = checkEnd(roomNum);

		if (alreadyIn == false) {
			String sql = "update LECTURE_HISTORY set END_TIME =SYSDATE where CHAT_INDEX = ?";

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(roomNum));
				pstmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
		return alreadyIn;
	}

}
