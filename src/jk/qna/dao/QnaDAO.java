package jk.qna.dao;

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

import jk.qna.model.QnaDTO;
import min.unlimited.util.DBManager;

public class QnaDAO {

	public int getListCount() {
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select count(*) from qna";
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

	public List<QnaDTO> getQnaList(int page, int limit) {
		List<QnaDTO> list = new ArrayList<QnaDTO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from(select rownum rnum,qna_number,id,qna_title,qna_contents,";
			sql += "attached_file,answer_num,answer_lev,answer_seq,qna_view_number,qna_date";
			sql += " from(select * from qna order by answer_num desc,answer_seq asc))";
			sql += " where rnum>=? and rnum<=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QnaDTO qnaDTO = new QnaDTO();
				qnaDTO.setQna_number(resultSet.getInt("qna_number"));
				qnaDTO.setId(resultSet.getString("id"));
				qnaDTO.setQna_title(resultSet.getString("qna_title"));
				qnaDTO.setQna_contents(resultSet.getString("qna_contents"));
				qnaDTO.setAttached_file(resultSet.getString("attached_file"));
				qnaDTO.setAnswer_num(resultSet.getInt("answer_num"));
				qnaDTO.setAnswer_lev(resultSet.getInt("answer_lev"));
				qnaDTO.setAnswer_seq(resultSet.getInt("answer_seq"));
				qnaDTO.setQna_view_number(resultSet.getInt("qna_view_number"));
				qnaDTO.setQna_date(resultSet.getDate("qna_date"));
				list.add(qnaDTO);
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
	public boolean qnaInsert(QnaDTO qnaDTO) {
		int num = 0;
		String sql = "";
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			sql = "select max(qna_number) from qna";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				num = resultSet.getInt(1) + 1;
			} else {
				num = 1;
			}
			sql = "insert into qna(qna_number,id,qna_pass,qna_title,qna_contents,attached_file,";
			sql += "answer_num,answer_lev,answer_seq,qna_view_number,qna_date)";
			sql += " values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			preparedStatement.setString(2, qnaDTO.getId());
			preparedStatement.setString(3, qnaDTO.getQna_pass());
			preparedStatement.setString(4, qnaDTO.getQna_title());
			preparedStatement.setString(5, qnaDTO.getQna_contents());
			preparedStatement.setString(6, qnaDTO.getAttached_file());
			preparedStatement.setInt(7, num);
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

	public QnaDTO getDetail(int num) {
		QnaDTO qnaDTO = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from qna where qna_number = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				qnaDTO = new QnaDTO();
				qnaDTO.setQna_number(resultSet.getInt("qna_number"));
				qnaDTO.setId(resultSet.getString("id"));
				qnaDTO.setQna_title(resultSet.getString("qna_title"));
				qnaDTO.setQna_contents(resultSet.getString("qna_contents"));
				qnaDTO.setAttached_file(resultSet.getString("attached_file"));
				qnaDTO.setAnswer_num(resultSet.getInt("answer_num"));
				qnaDTO.setAnswer_lev(resultSet.getInt("answer_lev"));
				qnaDTO.setAnswer_seq(resultSet.getInt("answer_seq"));
				qnaDTO.setQna_view_number(resultSet.getInt("qna_view_number"));
				qnaDTO.setQna_date(resultSet.getDate("qna_date"));
			}
			return qnaDTO;
		} catch (Exception e) {
			System.out.println("글 내용 보기 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	public void setQnaViewNumberUpdate(int num) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "update qna set qna_view_number = qna_view_number+1 where qna_number = " + num;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("조회수 업데이트 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
	}

	public boolean qnaModify(QnaDTO qnaDTO) {
		String fileName = qnaDTO.getOld_file();
		String realFolder = "";
		realFolder += fileName;
		File file = new File(realFolder);
		if (qnaDTO.getAttached_file() == null) {
			qnaDTO.setAttached_file(fileName);
		} else {
			if (file.exists()) {
				file.delete();
			}
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "update qna set id=?,qna_title=?,qna_contents=?,attached_file=? where qna_number=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, qnaDTO.getId());
			preparedStatement.setString(2, qnaDTO.getQna_title());
			preparedStatement.setString(3, qnaDTO.getQna_contents());
			preparedStatement.setString(4, qnaDTO.getAttached_file());
			preparedStatement.setInt(5, qnaDTO.getQna_number());
			preparedStatement.executeQuery();
			return true;
		} catch (Exception e) {
			System.out.println("글 수정 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return false;
	}

	public boolean isQnaWriter(int num, String pass) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from qna where qna_number=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if (pass.equals(resultSet.getString("qna_pass"))) {
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
	public int qnaReply(QnaDTO qnaDTO) {
		// System.out.println(qnaDTO); 찍
		String sql = "";
		int num = 0;
		int answer_num = qnaDTO.getAnswer_num();
		int answer_lev = qnaDTO.getAnswer_lev();
		int answer_seq = qnaDTO.getAnswer_seq();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			sql = "select max(qna_number) from qna";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				num = resultSet.getInt(1) + 1;
				// System.out.println(num); //찍
			} else {
				num = 1;
			}
			sql = "update qna set answer_seq=answer_seq+1";
			sql += " where answer_num=? and answer_seq>?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, answer_num);
			preparedStatement.setInt(2, answer_seq);
			preparedStatement.executeUpdate();
			answer_seq = answer_seq + 1;
			answer_lev = answer_lev + 1;
			sql = "insert into qna(qna_number,id,qna_pass,qna_title,qna_contents,attached_file,";
			sql += "answer_num,answer_lev,answer_seq,qna_view_number,qna_date)";
			sql += " values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			preparedStatement.setString(2, qnaDTO.getId());
			preparedStatement.setString(3, qnaDTO.getQna_pass());
			preparedStatement.setString(4, qnaDTO.getQna_title());
			preparedStatement.setString(5, qnaDTO.getQna_contents());
			preparedStatement.setString(6, qnaDTO.getAttached_file());
			preparedStatement.setInt(7, answer_num);
			preparedStatement.setInt(8, answer_lev);
			preparedStatement.setInt(9, answer_seq);
			preparedStatement.setInt(10, 0);
			preparedStatement.executeUpdate();
			return num;
		} catch (Exception e) {
			System.out.println("글 답변 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}

	public List<QnaDTO> getSearchList(String keyword, String keyfield, int page, int limit) {
		String searchCall = "";
		if (!"".equals(keyword)) {
			if ("all".equals(keyfield)) {
				searchCall = "(qna_title like '%' ||'" + keyword + "' || '%') or (id like '%' ||'" + keyword
						+ "' ||'%') or (qna_contents like '%' || '" + keyword + "'||'%')";
			} else if ("qna_title".equals(keyfield)) {
				searchCall = " qna_title like '%' || '" + keyword + "' || '%'";
			} else if ("id".equals(keyfield)) {
				searchCall = " id like '%' || '" + keyword + "'||'%'";
			} else if ("qna_contents".equals(keyfield)) {
				searchCall = " qna_contents like '%' || '" + keyword + "' || '%'";
				// System.out.println(searchCall); 찍
			}
		}
		List<QnaDTO> list = new ArrayList<QnaDTO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from(select rownum rnum,qna_number,id,qna_title,qna_contents,";
			sql += "attached_file,answer_num,answer_lev,answer_seq,qna_view_number,qna_date";
			sql += " from(select * from qna order by answer_num desc, answer_seq asc)";
			sql += " where " + searchCall + ")";
			sql += " where rnum>=? and rnum<=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QnaDTO qnaDTO = new QnaDTO();
				qnaDTO.setQna_number(resultSet.getInt("qna_number"));
				qnaDTO.setId(resultSet.getString("id"));
				qnaDTO.setQna_title(resultSet.getString("qna_title"));
				qnaDTO.setQna_contents(resultSet.getString("qna_contents"));
				qnaDTO.setAttached_file(resultSet.getString("attached_file"));
				qnaDTO.setAnswer_num(resultSet.getInt("answer_num"));
				qnaDTO.setAnswer_lev(resultSet.getInt("answer_lev"));
				qnaDTO.setAnswer_seq(resultSet.getInt("answer_seq"));
				qnaDTO.setQna_view_number(resultSet.getInt("qna_view_number"));
				qnaDTO.setQna_date(resultSet.getDate("qna_date"));
				list.add(qnaDTO);
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
				searchCall = "(qna_title like '%' || '" + keyword + "'|| '%') or (id like '%'||'" + keyword
						+ "'|| '%') or (qna_contents like '%' || '" + keyword + "'|| '%')";
			} else if ("qna_title".equals(keyfield)) {
				searchCall = " qna_title like '%' ||'" + keyword + "' || '%'";
			} else if ("id".equals(keyfield)) {
				searchCall = " id like '%' ||'" + keyword + "'|| '%'";
			} else if ("qna_contents".equals(keyfield)) {
				searchCall = " qna_contents like '%' ||'" + keyword + "'||'%'";
			}
		}
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select count(*) from qna where " + searchCall;
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

	public boolean qnaDelete(int num) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "delete from qna where qna_number=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
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