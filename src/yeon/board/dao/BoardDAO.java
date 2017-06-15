package yeon.board.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import min.unlimited.util.DBManager;
import yeon.board.model.BoardDTO;

public class BoardDAO {

	public int getListCount() {
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = DBManager.getConnection();
			String sql = "select count(*) from studyboard";
			System.out.println("연결이 되었습니다.");
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				i = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("글의 개수 구하기 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return i;
	}

	public List<BoardDTO> getBoardList(int page, int limit) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = DBManager.getConnection();
			String sql = "select * from(select rownum rnum,study_post_index,id,study_post_title,study_post_contents,";
			sql += "attached_file,answer_num,answer_lev,answer_seq,study_post_number,study_post_date";
			sql += " from(select * from studyboard order by answer_num desc,answer_seq asc))";
			sql += " where rnum>=? and rnum<=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setstudy_post_index(resultSet.getInt("study_post_index"));
				boardDTO.setid(resultSet.getString("id"));
				boardDTO.setstudy_post_title(resultSet.getString("study_post_title"));
				System.out.println(resultSet.getString("study_post_title"));
				boardDTO.setstudy_post_contents(resultSet.getString("study_post_contents"));
				boardDTO.setAttached_file(resultSet.getString("attached_file"));
				boardDTO.setAnswer_num(resultSet.getInt("answer_num"));
				boardDTO.setAnswer_lev(resultSet.getInt("answer_lev"));
				boardDTO.setAnswer_seq(resultSet.getInt("answer_seq"));
				boardDTO.setstudy_post_number(resultSet.getInt("study_post_number"));
				boardDTO.setstudy_post_date(resultSet.getString("study_post_date"));
				list.add(boardDTO);
			}
			return list;
		} catch (Exception e) {

			System.out.println("글 목록 보기 실패 : " + e);
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	@SuppressWarnings("resource")
	public boolean boardInsert(BoardDTO boardDTO) {
		int study_post_index = 0;
		String sql = "";
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = DBManager.getConnection();
			sql = "select max(study_post_index) from studyboard";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				study_post_index = resultSet.getInt(1) + 1;
			} else {
				study_post_index = 1;
			}
			sql = "insert into studyboard(study_post_index,id,password,study_post_title,study_post_contents,attached_file,";
			sql += "answer_num,answer_lev,answer_seq,study_post_number,study_post_date)";
			sql += " values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, study_post_index);
			preparedStatement.setString(2, boardDTO.getid());
			preparedStatement.setString(3, boardDTO.getpassword());
			preparedStatement.setString(4, boardDTO.getstudy_post_title());
			preparedStatement.setString(5, boardDTO.getstudy_post_contents());
			preparedStatement.setString(6, boardDTO.getAttached_file());
			preparedStatement.setInt(7, study_post_index);
			preparedStatement.setInt(8, 0);
			preparedStatement.setInt(9, 0);
			preparedStatement.setInt(10, 0);
			result = preparedStatement.executeUpdate();
			if (result == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("글 등록 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return false;
	}

	public BoardDTO getDetail(int study_post_index) {
		BoardDTO boardDTO = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			connection = DBManager.getConnection();
			String sql = "select * from studyboard where study_post_index = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, study_post_index);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setstudy_post_index(resultSet.getInt("study_post_index"));
				boardDTO.setid(resultSet.getString("id"));
				boardDTO.setstudy_post_title(resultSet.getString("study_post_title"));
				boardDTO.setstudy_post_contents(resultSet.getString("study_post_contents"));
				boardDTO.setAttached_file(resultSet.getString("attached_file"));
				boardDTO.setAnswer_num(resultSet.getInt("answer_num"));
				boardDTO.setAnswer_lev(resultSet.getInt("answer_lev"));
				boardDTO.setAnswer_seq(resultSet.getInt("answer_seq"));
				boardDTO.setstudy_post_number(resultSet.getInt("study_post_number"));
				boardDTO.setstudy_post_date(resultSet.getString("study_post_date"));
			}
			return boardDTO;
		} catch (Exception e) {
			System.out.println("글 내용 보기 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	public void setReadCountUpdate(int study_post_index) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			
			connection = DBManager.getConnection();
			String sql = "update studyboard set study_post_number = study_post_number+1 where study_post_index = "
					+ study_post_index;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("조회수 업데이트 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
	}

	public boolean boardModify(BoardDTO boardDTO) {
		String fileid = boardDTO.getOld_file();
		String realFolder = "";
		realFolder += fileid;
		File file = new File(realFolder);
		if (boardDTO.getAttached_file() == null) {
			boardDTO.setAttached_file(fileid);
		} else {
			if (file.exists()) {
				file.delete();
			}
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			
			connection = DBManager.getConnection();
			String sql = "update studyboard set id=?, study_post_title=?,study_post_contents=?, attached_file=? where study_post_index=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, boardDTO.getid());
			preparedStatement.setString(2, boardDTO.getstudy_post_title());
			preparedStatement.setString(3, boardDTO.getstudy_post_contents());
			preparedStatement.setString(4, boardDTO.getAttached_file());
			preparedStatement.setInt(5, boardDTO.getstudy_post_index());
			preparedStatement.executeQuery();
			return true;
		} catch (Exception e) {
			System.out.println("글 수정 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return false;
	}

	public boolean isBoardWriter(int study_post_index, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			connection = DBManager.getConnection();
			String sql = "select * from studyboard where study_post_index=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, study_post_index);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if (password.equals(resultSet.getString("password"))) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("글쓴이 확인 실패 : " + e);
		} finally {
		
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return false;
	}

	@SuppressWarnings("resource")
	public int boardReply(BoardDTO boardDTO) {
		String sql = "";
		int study_post_index = 0;
		int answer_num = boardDTO.getAnswer_num();
		int answer_lev = boardDTO.getAnswer_lev();
		int answer_seq = boardDTO.getAnswer_seq();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			connection = DBManager.getConnection();
			sql = "select max(study_post_index) from studyboard";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				study_post_index = resultSet.getInt(1) + 1;
				// System.out.println(study_post_index); //찍
			} else {
				study_post_index = 1;
			}
			sql = "update studyboard set answer_seq=answer_seq+1";
			sql += " where answer_num=? and answer_seq>?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, answer_num);
			preparedStatement.setInt(2, answer_seq);
			preparedStatement.executeUpdate();
			answer_seq = answer_seq + 1;
			answer_lev = answer_lev + 1;
			sql = "insert into studyboard(study_post_index,id,password,study_post_title,study_post_contents,attached_file,";
			sql += "answer_num,answer_lev,answer_seq,study_post_number,study_post_date)";
			sql += " values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, study_post_index);
			preparedStatement.setString(2, boardDTO.getid());
			preparedStatement.setString(3, boardDTO.getpassword());
			preparedStatement.setString(4, boardDTO.getstudy_post_title());
			preparedStatement.setString(5, boardDTO.getstudy_post_contents());
			preparedStatement.setString(6, boardDTO.getAttached_file());
			preparedStatement.setInt(7, answer_num);
			preparedStatement.setInt(8, answer_lev);
			preparedStatement.setInt(9, answer_seq);
			preparedStatement.setInt(10, 0);
			preparedStatement.executeUpdate();
			return study_post_index;
		} catch (Exception e) {
			System.out.println("글 답변 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}

	public List<BoardDTO> getSearchList(String keyword, String Keyfield, int page, int limit) {
		String searchCall = "";
		if (!"".equals(keyword)) {
			if ("all".equals(Keyfield)) {
				searchCall = "(study_post_title like '%' ||'" + keyword + "' || '%') or (id like '%' ||'" + keyword
						+ "' ||'%') or (study_post_contents like '%' || '" + keyword + "'||'%')";
			} else if ("study_post_title".equals(Keyfield)) {
				searchCall = "study_post_title like '%' || '" + keyword + "' || '%'";
			} else if ("id".equals(Keyfield)) {
				searchCall = "id like '%' || '" + keyword + "'||'%'";
			} else if ("study_post_contents".equals(Keyfield)) {
				searchCall = "study_post_contents like '%' || '" + keyword + "'||'%'";
			}
		}
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from(select rownum rnum,study_post_index,id,study_post_title,study_post_contents,";
			sql += "attached_file,answer_num,answer_lev,answer_seq,study_post_number,study_post_date";
			sql += " from(select * from studyboard order by answer_num desc, answer_seq asc)";
			sql += " where " + searchCall + ")";
			sql += " where rnum>=? and rnum<=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setstudy_post_index(resultSet.getInt("study_post_index"));
				boardDTO.setid(resultSet.getString("id"));
				boardDTO.setstudy_post_title(resultSet.getString("study_post_title"));
				boardDTO.setstudy_post_contents(resultSet.getString("study_post_contents"));
				boardDTO.setAttached_file(resultSet.getString("attached_file"));
				boardDTO.setAnswer_num(resultSet.getInt("answer_num"));
				boardDTO.setAnswer_lev(resultSet.getInt("answer_lev"));
				boardDTO.setAnswer_seq(resultSet.getInt("answer_seq"));
				boardDTO.setstudy_post_number(resultSet.getInt("study_post_number"));
				boardDTO.setstudy_post_date(resultSet.getString("study_post_date"));
				list.add(boardDTO);
			}
			return list;
		} catch (Exception e) {
			System.out.println("search 에러" + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	public int getSearchListCount(String keyword, String keyfield) {
		String searchCall = "";
		if (!"".equals(keyword)) {
			if ("all".equals(keyfield)) {
				searchCall = "(study_post_title like '%' || '" + keyword + "'|| '%') or (id like '%'||'" + keyword
						+ "'|| '%') or (study_post_contents like '%' || '" + keyword + "'|| '%')";
			} else if ("study_post_title".equals(keyfield)) {
				searchCall = "study_post_title like '%' ||'" + keyword + "' || '%'";
			} else if ("id".equals(keyfield)) {
				searchCall = "id like '%' ||'" + keyword + "'|| '%'";
			} else if ("study_post_contents".equals(keyfield)) {
				searchCall = " study_post_contents like '%' ||'" + keyword + " '||'%'";
			}
		}
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select count(*) from studyboard where " + searchCall;
			System.out.println("연결이 되었습니다.");
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				i = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("글의 개수 구히가 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return i;
	}

	public boolean boardDelete(int study_post_index) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "delete from studyboard where study_post_index=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, study_post_index);
			result = preparedStatement.executeUpdate();
			if (result == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("글 삭제 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return false;
	}
}