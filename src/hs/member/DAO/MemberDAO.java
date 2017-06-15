package hs.member.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import hs.login.model.LoginDTO;
import hs.member.model.MemberDTO;
import min.unlimited.util.DBManager;

public class MemberDAO {
	

	// 개인정보 변경 페이지로 가기전 비밀번호 확인
	public MemberDTO chkPw(MemberDTO memberDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			
			
			connection = DBManager.getConnection();
			String sql = "select id, password from member ";
			sql += " where id = ? and password = ? ";
			// System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getId());
			// System.out.println("chkPw 아이디 : " + memberDTO.getId());
			preparedStatement.setString(2, memberDTO.getPassword());
			// System.out.println("chkPw 비밀번호 : " + memberDTO.getPassword());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				String id = resultSet.getString("id");
				String password = resultSet.getString("password");
				// System.out.println("chkPw id:" + id + ",password:"
				// +password);
				memberDTO = new MemberDTO();
				memberDTO.setId(id);
				memberDTO.setPassword(password);
				// System.out.println("비밀번호 일치");

			} else {
				// System.out.println("불일치");
				return null;
			}
			return memberDTO;

		} catch (Exception e) {
			System.out.println("아이디가 존재하지 않습니다 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return memberDTO;
	}// end of chkPw

	// 기존에 입력된 회원 정보를 불러들임
	public MemberDTO memberDetailInfo(MemberDTO memberDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			connection = DBManager.getConnection();
			String sql = "select * from member where id= ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getId());
			// System.out.println(memberDTO.getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setName(resultSet.getString("name"));
				memberDTO.setNickName(resultSet.getString("nickName"));
				memberDTO.setBirth(resultSet.getString("birth"));
				memberDTO.setHp(resultSet.getString("hp"));
				memberDTO.setEmail(resultSet.getString("email"));
				memberDTO.setGender(resultSet.getString("gender"));
				memberDTO.setProfilePicture(resultSet.getString("profilePicture"));
				memberDTO.setIntroduceMySelf(resultSet.getString("introduceMySelf"));
				// System.out.println(" memberDetailInfo 의"+memberDTO);
			}
			return memberDTO;
		} catch (Exception e) {
			System.out.println("회원 기본 정보 보기 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}// end of memberDetailInfo

	
	
	
	public MemberDTO memberDetailInfo2(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			connection = DBManager.getConnection();
			String sql = "select * from member where id= ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			// System.out.println(memberDTO.getId());
			resultSet = preparedStatement.executeQuery();
			MemberDTO memberDTO = new MemberDTO();
			if (resultSet.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setId(id);
				memberDTO.setPoint(resultSet.getInt("point"));
				memberDTO.setName(resultSet.getString("name"));
				memberDTO.setNickName(resultSet.getString("nickName"));
				memberDTO.setBirth(resultSet.getString("birth"));
				memberDTO.setHp(resultSet.getString("hp"));
				memberDTO.setEmail(resultSet.getString("email"));
				memberDTO.setGender(resultSet.getString("gender"));
				memberDTO.setProfilePicture(resultSet.getString("profilePicture"));
				memberDTO.setIntroduceMySelf(resultSet.getString("introduceMySelf"));
				// System.out.println(" memberDetailInfo 의"+memberDTO);
			}
			return memberDTO;
		} catch (Exception e) {
			System.out.println("회원 기본 정보 보기 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}// end of memberDetailInfo
	
	
	
	
	// 회원 정보 수정
	// 닉네임은 null값 나옴...
	public boolean memberInfoModify(MemberDTO memberDTO) {
		String fileName = memberDTO.getTempFile();
		String realFolder = "";
		realFolder += fileName;
		File file = new File(realFolder);
		if (memberDTO.getProfilePicture() == null) {
			memberDTO.setProfilePicture(fileName);
		} else {
			if (file.exists()) {
				file.delete();
			}
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBManager.getConnection();
			String sql = "update member set name=?, nickName=?, birth=?, hp=?, email=?, "
					+ "gender=?, profilePicture=?, introduceMySelf=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getName());
			preparedStatement.setString(2, memberDTO.getNickName());
			System.out.println("닉네임 왜 널??" + memberDTO.getNickName());
			preparedStatement.setString(3, memberDTO.getBirth());
			preparedStatement.setString(4, memberDTO.getHp());
			preparedStatement.setString(5, memberDTO.getEmail());
			preparedStatement.setString(6, memberDTO.getGender());
			preparedStatement.setString(7, memberDTO.getProfilePicture());
			preparedStatement.setString(8, memberDTO.getIntroduceMySelf());
			preparedStatement.setString(9, memberDTO.getId());
			System.out.println("memberInfoModify : " + memberDTO);
			// System.out.println(memberDTO.getId());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("회원 정보 수정 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return false;

	}// end of memberInfoModify

	// 비밀번호 변경하기
	public boolean pwChange(MemberDTO memberDTO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = DBManager.getConnection();
			String sql = "update member set password=? where id=?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, memberDTO.getPassword());
			preparedStatement.setString(2, memberDTO.getId());

			// System.out.println("MemberDAO의 변경된 password : " +
			// memberDTO.getPassword());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("비밀번호 변경 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return false;

	}// end of pwChange

	// 멘토 정보 변경할 경우 등급번호 변경
	public boolean updateGradeNumber(MemberDTO memberDTO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			
			connection = DBManager.getConnection();
			String sql = " update member set grade_number=? where id=? ";
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, 2);
			preparedStatement.setString(2, memberDTO.getId());
			// System.out.println(memberDTO.getId());

			preparedStatement.executeUpdate();
			//System.out.println("MemberDAO의 updateGradeNumber __ " + memberDTO);
			return true;
		} catch (Exception e) {
			System.out.println("회원 등급 수정 실패 : " + e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;

	}// updateGrageNumber

	/*
	 * 회원 탈퇴기능 구현 발생오류:회원 탈퇴 실패 : java.lang.NullPointerException 해결방안:
	 * memberInfoDelete(String id)->memberInfoDelete(MemberDTO memberDTO) 상세 내용
	 * : MemberDeleteService에서 세션에 있는 아이디 값을 가져와서 memberDTO 에 넣고, 그 아이디 값으로
	 * SQL에서 찾아야 하므로 memberDTO로 바꿔주었음.
	 */
	public boolean memberInfoDelete(MemberDTO memberDTO) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			
			connection = DBManager.getConnection();
			String sql = "delete from member where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getId());
			result = preparedStatement.executeUpdate();
			// System.out.println("delete dao에서 id" + memberDTO);
			if (result == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("회원 탈퇴 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return false;
	}// end of memberInfoDelete

}
