package hs.login.DAO;

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

public class LoginDAO {

	@SuppressWarnings("unused")

	/*
	 * 발생오류 : db 연결 실패 해결방안 : Connection 인터페이스 사용 상세내용 : 여기서 connection 을 안써줬더니
	 * 계속 연결에 실패했었다. connection을 초기화 해준 후 연결을 해줬더니 오류가 넘어갔음
	 */

	/*
	 * 20170606 정해선 로그인 처리, 아이디 pw 일치 확인 발생오류 : 아이디가 존재하지 않습니다 :
	 * java.sql.SQLException: 부적합한 열 이름 해결방안 : sql문에 값이 빠져서 넣어주었음 상세내용: sql문에
	 * name 없이 실행해서 발생한 오류
	 */

	public LoginDTO loginProcess(LoginDTO loginDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBManager.getConnection();
			String sql = "select id, password, name, nickName, point, grade_number from member ";
			sql += " where id = ? and password = ? ";
			// System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginDTO.getId());
			// System.out.println("loginDAO의 loginProcess 아이디 : " +
			// loginDTO.getId());
			preparedStatement.setString(2, loginDTO.getPassword());
			// System.out.println("loginDAO의 loginProcess 비밀번호 : " +
			// loginDTO.getPassword());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String id = resultSet.getString("id");
				String password = resultSet.getString("password");
				String name = resultSet.getString("name");
				String nickName = resultSet.getString("nickName");
				int point = resultSet.getInt("point");
				int grade_number = resultSet.getInt("grade_number");
				// System.out.println("LoginProcess id:" + id + ",password:" +
				// password);
				loginDTO = new LoginDTO();
				loginDTO.setId(id);
				loginDTO.setPassword(password);
				loginDTO.setName(name);
				loginDTO.setNickName(nickName);
				loginDTO.setPoint(point);
				loginDTO.setGrade_number(grade_number);
				// System.out.println("loginDAO의 loginProcess:" + loginDTO);
				// System.out.println("로그인 성공");
				// return null;
				return loginDTO;
			}

			return null;

		} catch (Exception e) {
			System.out.println("아이디가 존재하지 않습니다 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return loginDTO;
	}// end of LoginProcess

	// 20170607정해선 작성
	// ID 중복확인 DAO
	public String IdCheckProcess(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String checkId = null; // 가져온 아이디를 넣어주기 위해 null로 초기화
		try {
			connection = DBManager.getConnection();
			String sql = "select id from member ";
			sql += " where id =? ";
			// System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {// 아이디가 존재하면 checkId에 값을 할당
				checkId = resultSet.getString("id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return checkId;
	}// end of IdCheckProcess

	
	/*
	 * id 찾기서비스를 처리
	 * 발생 오류 : java.sql.SQLException: 부적합한 열 이름
	 * 해결 방안 :sql 문 수정
	 * 상세 내용 : sql 문에서 select id from member 로 하면서 id, name, email모두 가져오려 해서 발생한 오류
	 * */
	public LoginDTO idFindProcess(LoginDTO loginDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBManager.getConnection();
			String sql = "select id, name, email from member ";
			sql += " where name= ? and email= ? ";
			// System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginDTO.getName());
			preparedStatement.setString(2, loginDTO.getEmail());
			//System.out.println("loginDAO에서 idFindProcess___" + loginDTO);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				loginDTO = new LoginDTO();
				loginDTO.setId( resultSet.getString("id"));
				loginDTO.setName(resultSet.getString("name"));
				loginDTO.setEmail(resultSet.getString("email"));
				//System.out.println("loginDAO에서 idFindProcess___" + loginDTO);
				return loginDTO;
			}

			return null;

		} catch (Exception e) {
			System.out.println("idFindProcess : 아이디가 존재하지 않습니다 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return loginDTO;
	}// end of idFindProcess

	//비밀번호 찾기-> 입력한 정보에 일치하는 사람 찾기
	public LoginDTO PwFindProcess(LoginDTO loginDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBManager.getConnection();
			String sql = "select id, name from member ";
			sql += " where id= ? and name= ? ";
			// System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginDTO.getId());
			preparedStatement.setString(2, loginDTO.getName());
			//System.out.println("loginDAO에서 PwFindProcess___" + loginDTO);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				loginDTO = new LoginDTO();
				loginDTO.setId( resultSet.getString("id"));
				loginDTO.setName(resultSet.getString("name"));
				//System.out.println("loginDAO에서 PwFindProcess___" + loginDTO);
				return loginDTO;
			}

			return null;

		} catch (Exception e) {
			System.out.println("PwFindProcess : 아이디가 존재하지 않습니다 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return loginDTO;
	}// end of idFindProcess
	
	// 20170608정해선 작성
	// 회원가입 처리 DAO
	public boolean memberRegist(LoginDTO loginDTO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;

		try {

			connection = DBManager.getConnection();
			String sql = " insert into member(id,password,name,nickName,birth,hp,email,gender,profilePicture) ";
			sql += "  values (?,?,?,?,?,?,?,?,?)";
			// System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginDTO.getId());
			// System.out.println(loginDTO.getId());
			preparedStatement.setString(2, loginDTO.getPassword());
			preparedStatement.setString(3, loginDTO.getName());
			preparedStatement.setString(4, loginDTO.getNickName());
			preparedStatement.setString(5, loginDTO.getBirth());
			preparedStatement.setString(6, loginDTO.getHp());
			preparedStatement.setString(7, loginDTO.getEmail());
			preparedStatement.setString(8, loginDTO.getGender());
			preparedStatement.setString(9, loginDTO.getProfilePicture());

			count = preparedStatement.executeUpdate();
			// System.out.println(count);
			if (count == 0) {
				System.out.println("회원가입이 완료되었습니다.");
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("회원가입 등록 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return false;
	}// end of memberRegist

	public LoginDTO setPw(LoginDTO loginDTO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBManager.getConnection();
			String sql = "update member set password=? where id=?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, loginDTO.getPassword());
			preparedStatement.setString(2, loginDTO.getId());

			//System.out.println("MemberDAO의 변경된 password : " + memberDTO.getPassword());
			preparedStatement.executeUpdate();
			return loginDTO;
		} catch (Exception e) {
			System.out.println("비밀번호 변경 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement);
		}
		return loginDTO;

	}// end of setPw

}
