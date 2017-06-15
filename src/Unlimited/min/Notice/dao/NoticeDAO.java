package Unlimited.min.Notice.dao;

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

import Unlimited.min.Notice.model.NoticeCommentDTO;
import Unlimited.min.Notice.model.NoticeDTO;
import min.unlimited.util.DBManager;

public class NoticeDAO {

	public int boardInsert(NoticeDTO noticeDTO) {
		// TODO Auto-generated method stub

		int no = 0;
		String sql = "";
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			sql = "select max(no) from noticelist";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				no = resultSet.getInt(1) + 1;
			} else {
				no = 1;
			}
			sql = "insert into noticelist (no,writer,title,content,attached_file,"
					+ " read_count, answer_num, answer_lev, answer_seq, write_date) "
					+ " values(?,?,?,?,?,?,0,0,0,to_char(sysdate,'YYYY/mm/dd hh24:mi:SS'))";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, no);
			preparedStatement.setString(2, noticeDTO.getWriter());
			preparedStatement.setString(3, noticeDTO.getTitle());
			preparedStatement.setString(4, noticeDTO.getContent());
			preparedStatement.setString(5, noticeDTO.getAttached_file());
			preparedStatement.setInt(6, 0);
			result = preparedStatement.executeUpdate();
			if (result == 0) {
				return 0;
			}
			return no;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("공지등록실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return no;
	}

	public int getListCount() {
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select count(*) from noticelist";
			System.out.println("연결되었습니다.");
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				i = resultSet.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("글의 개수 구하기 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
		return i;
	}

	public List<?> getBoardList(int page, int limit) {
		// TODO Auto-generated method stub
		List<NoticeDTO> list = new ArrayList<NoticeDTO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from (select rownum rnum, no, writer, title, content,"
					+ " attached_file, answer_num, answer_lev, answer_seq, read_count, write_date" + 
					" from (select * from noticelist order by no desc))"
					+ " where rnum >=? and rnum <=?";     //이거 역순으로 안되냐
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				NoticeDTO noticeDTO = new NoticeDTO();
				noticeDTO.setNo(resultSet.getInt("no"));
				noticeDTO.setWriter(resultSet.getString("writer"));
				noticeDTO.setTitle(resultSet.getString("title"));
				noticeDTO.setContent(resultSet.getString("content"));
				noticeDTO.setAttached_file(resultSet.getString("attached_file"));
				noticeDTO.setRead_count(resultSet.getInt("read_count"));
				noticeDTO.setWrite_date(resultSet.getString("write_date"));
				list.add(noticeDTO);
			}
			return list;
		} catch (Exception e) {
			System.out.println("글 목록 보기 실패 :" + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}

		return null;
	}

	public boolean boardDelete(int no) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "delete from noticelist where no=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, no);
			result = preparedStatement.executeUpdate();
			sql = "delete from noticecomment where no=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, no);
			preparedStatement.executeUpdate();
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

	public void setReadcountUpdate(int no) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			
			connection = DBManager.getConnection();
			String sql = "update noticelist set read_count = read_count+1 where no = " + no;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("조회수 업데이트 실패 : " + e);
		} finally {
			connection = DBManager.getConnection();
			DBManager.close(connection, preparedStatement);
		}

	}

	public NoticeDTO getDetail(int no) {
		NoticeDTO noticeDTO = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from noticelist where no =?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, no);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				noticeDTO = new NoticeDTO();
				noticeDTO.setNo(resultSet.getInt("no"));
				noticeDTO.setWriter(resultSet.getString("writer"));
				noticeDTO.setTitle(resultSet.getString("title"));
				noticeDTO.setContent(resultSet.getString("content"));
				noticeDTO.setOld_file(resultSet.getString("attached_file"));
				noticeDTO.setAttached_file(resultSet.getString("attached_file"));
				noticeDTO.setRead_count(resultSet.getInt("read_count"));
				noticeDTO.setWrite_date(resultSet.getString("write_date"));
			}
			return noticeDTO;
		} catch (Exception e) {
			System.out.println("글내용보기실패: " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	public boolean noticeModify(NoticeDTO noticeDTO) {
		String fileName = noticeDTO.getOld_file();
		String realFolder = "";
		realFolder += fileName;
		File file = new File(realFolder);
		if (noticeDTO.getAttached_file() == null) {
			noticeDTO.setAttached_file(fileName);
		} else {
			if (file.exists()) {
				file.delete();
			}
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "update noticelist set writer=?, title=?, content=?, attached_file=? where no=?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, noticeDTO.getWriter());
			preparedStatement.setString(2, noticeDTO.getTitle());
			preparedStatement.setString(3, noticeDTO.getContent());
			preparedStatement.setString(4, noticeDTO.getAttached_file());
			preparedStatement.setInt(5, noticeDTO.getNo());
			preparedStatement.executeUpdate();
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("글쓰기 수정 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);

		}

		return false;
	}

	public void commentInsert(NoticeCommentDTO noticeCommentDTO) {
		// TODO Auto-generated method stub
		int comment_no = 0;
		String sql = "";
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			sql = "select max(comment_no) from noticecomment";
			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, noticeCommentDTO.getNo());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				comment_no = resultSet.getInt(1) + 1;
			} else {
				comment_no = 1;
			}
			sql = "insert into noticecomment (no, comment_no, writer,content,"
					+ " answer_num, answer_lev, answer_seq, write_date) " 
					+ " values(?,?,?,?,?,?,?,to_char(sysdate,'YYYY/mm/dd hh24:mi:SS'))";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, noticeCommentDTO.getNo());
			preparedStatement.setInt(2, comment_no);
			preparedStatement.setString(3, noticeCommentDTO.getWriter());
			preparedStatement.setString(4, noticeCommentDTO.getContent());
			preparedStatement.setInt(5, 0);
			preparedStatement.setInt(6, 0);
			preparedStatement.setInt(7, 0);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("댓글 등록실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}

	}

	public ArrayList<NoticeCommentDTO> getComment(int no, int page, int limit) {
		// TODO Auto-generated method stub
		ArrayList<NoticeCommentDTO> list = new ArrayList<NoticeCommentDTO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
//			String sql = "select * from (select rownum rnum, comment_no, writer, content, write_date"
//					+ "  from noticecomment where no=? order by no) where rnum >=? and rnum <=?";
//			위에거 rnum으로 댓글 페이지 만드는거 문젠데?
			String sql = "select * from noticecomment where no=?"
					+ " order by comment_no";
			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, startrow);
//			preparedStatement.setInt(2, endrow);
			preparedStatement.setInt(1, no);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				NoticeCommentDTO noticeCommentDTO = new NoticeCommentDTO();
				noticeCommentDTO.setNo(resultSet.getInt("no"));
				noticeCommentDTO.setComment_No(resultSet.getInt("comment_no"));
				noticeCommentDTO.setWriter(resultSet.getString("writer"));
				noticeCommentDTO.setContent(resultSet.getString("content"));
				noticeCommentDTO.setWrite_date(resultSet.getString("write_date"));
				noticeCommentDTO.setAnswer_lev(resultSet.getInt("answer_lev"));
				noticeCommentDTO.setAnswer_seq(resultSet.getInt("answer_seq"));
				noticeCommentDTO.setAnswer_num(resultSet.getInt("answer_num"));
				System.out.println(noticeCommentDTO); //찍
				list.add(noticeCommentDTO);
			}
			return list;
		} catch (Exception e) {
			System.out.println("댓글 목록 보기 실패 :" + e);
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	public int getSearchListCount(String keyword, String keyfield) {
		String searchCall = "";
		if (!"".equals(keyword)) {
			if ("all".equals(keyfield)) {
				searchCall = "(title like '%' || '" + keyword + "' || '%' ) or ( writer like '%' || '" + keyword
						+ "' || '%') or ( content like '%' || '" + keyword + "' || '%')";
			} else if ("subject".equals(keyfield)) {
				searchCall = " title like '%' || '" + keyword + "' || '%'";
			} else if ("name".equals(keyfield)) {
				searchCall = " writer like '%' || '" + keyword + "' || '%'";
			} else if ("content".equals(keyfield)) {
				searchCall = " content like '%' || '" + keyword + "' || '%'";
			}
		}
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select count(*) from noticelist where" + searchCall;
			System.out.println("연결이 되었습니다.");
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				i = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("글의 개수 구하기 실패: " + e);
		} finally {
			connection = DBManager.getConnection();
		}
		return i;
	}

	public List<?> getSearchList(String keyword, String keyfield, int page, int limit) {
		String searchCall = "";
		if (!"".equals(keyword)) {
			if ("all".equals(keyfield)) {
				searchCall = "(title like '%' || '" + keyword + "' || '%' ) or ( writer like '%' || '" + keyword
						+ "' || '%') or ( content like '%' || '" + keyword + "' || '%')";
			} else if ("subject".equals(keyfield)) {
				searchCall = " title like '%' || '" + keyword + "' || '%'";
				System.out.println(searchCall);

			} else if ("name".equals(keyfield)) {
				searchCall = " writer like '%' || '" + keyword + "' || '%'";
				System.out.println(searchCall);
			} else if ("content".equals(keyfield)) {
				searchCall = " content like '%' || '" + keyword + "' || '%'";
			}
		}
		List<NoticeDTO> list = new ArrayList<NoticeDTO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from (select rownum rnum,no,writer,title,content,";
			sql += "attached_file,read_count,write_date";
			sql += " from (select * from noticelist order by no desc) ";
			sql += " where " + searchCall + ")";
			sql += " where rnum>=? and rnum<=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				NoticeDTO noticeDTO = new NoticeDTO();
				noticeDTO.setNo(resultSet.getInt("no"));
				noticeDTO.setWriter(resultSet.getString("writer"));
				noticeDTO.setTitle(resultSet.getString("title"));
				noticeDTO.setContent(resultSet.getString("content"));
				noticeDTO.setAttached_file(resultSet.getString("attached_file"));
				noticeDTO.setRead_count(resultSet.getInt("read_count"));
				noticeDTO.setWrite_date(resultSet.getString("write_date"));
				System.out.println(noticeDTO); //찍
				list.add(noticeDTO);
			}
			return list;
		} catch (Exception e) {
			System.out.println("search 에러" + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	public void commentDelete(int comment_no) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "delete from noticecomment where comment_no=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, comment_no);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println("글 삭제 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		
	}

	public NoticeCommentDTO getCommen(int comment_no) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
//			String sql = "select * from (select rownum rnum, comment_no, writer, content, write_date"
//					+ "  from noticecomment where no=? order by no) where rnum >=? and rnum <=?";
//			위에거 rnum으로 댓글 페이지 만드는거 문젠데?
			String sql = "select * from noticecomment where comment_no=?"
					+ " order by comment_no";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, comment_no);
			resultSet = preparedStatement.executeQuery();
			NoticeCommentDTO noticeCommentDTO = new NoticeCommentDTO();
			while (resultSet.next()) {
				noticeCommentDTO.setNo(resultSet.getInt("no"));
				noticeCommentDTO.setComment_No(resultSet.getInt("comment_no"));
				noticeCommentDTO.setWriter(resultSet.getString("writer"));
				noticeCommentDTO.setContent(resultSet.getString("content"));
				noticeCommentDTO.setWrite_date(resultSet.getString("write_date"));
				noticeCommentDTO.setAnswer_lev(resultSet.getInt("answer_lev"));
				noticeCommentDTO.setAnswer_seq(resultSet.getInt("answer_seq"));
				noticeCommentDTO.setAnswer_num(resultSet.getInt("answer_num"));
				System.out.println(noticeCommentDTO); //찍

			}
			return noticeCommentDTO;
		} catch (Exception e) {
			System.out.println("댓글 수정폼 보기 실패 :" + e);
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	public void commentModify(String content, int comment_no) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "update noticecomment set content=? where comment_no=?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, content);
			preparedStatement.setInt(2, comment_no);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("글쓰기 수정 실패 : " + e);
		} finally {
			connection = DBManager.getConnection();
			DBManager.close(connection, preparedStatement);

		}
		
	}
}