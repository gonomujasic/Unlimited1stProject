package Unlimited.min.point.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import hs.member.model.MemberDTO;
import min.unlimited.util.DBManager;
import Unlimited.min.point.model.PointHistoryDTO;

public class PointDAO {

	public MemberDTO login(String id) {
		MemberDTO memberDTO = new MemberDTO();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBManager.getConnection();
			String sql = "select * from member where id = ?";
			// System.out.println("연결되었습니다.");
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				memberDTO.setId(id);
				memberDTO.setPoint(resultSet.getInt("point"));
				return memberDTO;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("로긴 실패 : " + e);
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
		return null;

	}

	public ArrayList<PointHistoryDTO> getMbPointHistory(String id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<PointHistoryDTO> pointHistoryList = new ArrayList<PointHistoryDTO>();
		String sql = null;
		try {
			connection = DBManager.getConnection();
			sql = "(select dealing_point, point_before, point_after, " 
					+ " dealing_date, seller opponent ,why "
					+ " from buyhistory where buyer = ?)";
			
			sql += "union all (select dealing_point, point_before, point_after, " 
					+ " dealing_date, buyer opponent, why"
					+ " from sellhistory where seller = ?)";
			
			sql += "union all (select dealing_point, point_before, point_after,"
					+ " dealing_date, opponent, why from c_rhistory where id = ?) order by dealing_date desc";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PointHistoryDTO pointHistoryDTO = new PointHistoryDTO();
				// pointHistoryDTO.setNo(i);
				pointHistoryDTO.setDealing_point(resultSet.getInt("dealing_point"));
				pointHistoryDTO.setPoint_before(resultSet.getInt("point_before"));
				pointHistoryDTO.setPoint_after(resultSet.getInt("point_after"));
				pointHistoryDTO.setWhy(resultSet.getString("why"));
				pointHistoryDTO.setDealing_date(resultSet.getString("dealing_date"));
				pointHistoryDTO.setOpponent((resultSet.getString("opponent")));
				// 이부분은 판매자입장에서의 테이블을 가지고오는겁니다.
				// System.out.println(pointHistoryDTO);
				pointHistoryList.add(pointHistoryDTO);
				// i++;
			} // 요 세 테이블 합쳐갖고 거래일로 그거 정렬못하나

			return pointHistoryList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
		return null;

	}

	public void updateCRtable(String id, double dealing_point, int point_before, String why) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql=null;
		int no=1;
		try {
			connection = DBManager.getConnection();
			sql ="select max(no) from c_rhistory";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				no = resultSet.getInt(1)+1;
			}
			
			
			sql = "insert into c_rhistory(no, dealing_point, point_before, point_after, dealing_date, id, why) "
					+ "values(?,?,?,?,to_char(sysdate,'YYYY/mm/dd hh24:mi:SS'),?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, no);
			preparedStatement.setDouble(2, dealing_point);
			preparedStatement.setInt(3, point_before);
			preparedStatement.setDouble(4, (int)(point_before + dealing_point));
			preparedStatement.setString(5, id);
			preparedStatement.setString(6, why);
			preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
	}

	public int chargeMbPt(double dealing_point, String id, int point_before) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql=null;
		try {
			connection = DBManager.getConnection();
			sql ="update member set point=point+? where id = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, dealing_point);
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();
			int point_after = ((int)(point_before+dealing_point));
//			System.out.println(memberDTO);
			return point_after;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement);

		}
		return 0;
	}
//	public void test() {
//	Connection connection = null;
//	PreparedStatement preparedStatement = null;
//	ResultSet resultSet = null;
//
//	try {
//		Context context = new InitialContext();
//		DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
//		connection = dataSource.getConnection();
//		String sql = "select deptno from dept where deptno=10 UNION ALL select deptno from dept where deptno=20";
//		while (resultSet.next()) {
//			System.out.println(resultSet.getInt("deptno"));
//		} // 요 세 테이블 합쳐갖고 거래일로 그거 정렬못하나
//
//	} catch (Exception e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	} finally {
//		try {
//			if (resultSet != null) {
//				resultSet.close();
//			}
//			if (preparedStatement != null) {
//				preparedStatement.close();
//			}
//			if (connection != null) {
//				connection.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	public void insertRefundRequest(int dealing_point, String refund_bank, String refund_accountant, String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql=null;
		int request_no = 1;
		try {
			connection = DBManager.getConnection();
			sql ="select max(request_no) from pointrefundrequest";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				request_no = resultSet.getInt(1)+1;
			}
			
			
			
			sql ="insert into pointrefundrequest( request_no, id, "
					+ "dealing_point, done, request_date, refund_bank, refund_accountant)"
					+ " values(?,?,?,?,to_char(sysdate,'YYYY/mm/dd hh24:mi:SS'),?,?)";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, request_no);
			preparedStatement.setString(2, id);
			preparedStatement.setInt(3, dealing_point);
			preparedStatement.setString(4, "N");
			preparedStatement.setString(5, refund_bank);
			preparedStatement.setString(6, refund_accountant);
			
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
	}

	public ArrayList<Map<String, Object>> getSellHistory() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql=null;
		try {
			connection = DBManager.getConnection();
			sql ="select * from sellhistory order by dealing_date desc";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			ArrayList<Map<String, Object>>pointHistoryList = new ArrayList<Map<String, Object>>();
			while(resultSet.next()){
				HashMap<String, Object> pointHistoryDTO = new HashMap<String, Object>();
				
				pointHistoryDTO.put("no",resultSet.getInt("no"));
				pointHistoryDTO.put("dealing_point",resultSet.getInt("dealing_point"));
				pointHistoryDTO.put("point_before",resultSet.getInt("point_before"));
				pointHistoryDTO.put("point_after",resultSet.getInt("point_after"));
				pointHistoryDTO.put("why", resultSet.getString("why"));
				pointHistoryDTO.put("dealing_date",resultSet.getString("dealing_date"));
				pointHistoryDTO.put("buyer",resultSet.getString("buyer"));
				pointHistoryDTO.put("seller",resultSet.getString("seller"));
				// 이부분은 판매자입장에서의 테이블을 가지고오는겁니다.
				// System.out.println(pointHistoryDTO);
				pointHistoryList.add(pointHistoryDTO);
				
			}
		return pointHistoryList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
		
		
		return null;
	}

	public ArrayList<Map<String, Object>> getbuyHistory() {
		// TODO Auto-generated method stub
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				String sql=null;
				try {
					connection = DBManager.getConnection();
					sql ="select * from buyhistory order by dealing_date desc";
					preparedStatement = connection.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					ArrayList<Map<String, Object>>pointHistoryList = new ArrayList<Map<String, Object>>();
					while(resultSet.next()){
						HashMap<String, Object> pointHistoryDTO = new HashMap<String, Object>();
						
						pointHistoryDTO.put("no",resultSet.getInt("no"));
						pointHistoryDTO.put("dealing_point",resultSet.getInt("dealing_point"));
						pointHistoryDTO.put("point_before",resultSet.getInt("point_before"));
						pointHistoryDTO.put("point_after",resultSet.getInt("point_after"));
						pointHistoryDTO.put("why", resultSet.getString("why"));
						pointHistoryDTO.put("dealing_date",resultSet.getString("dealing_date"));
						pointHistoryDTO.put("buyer",resultSet.getString("buyer"));
						pointHistoryDTO.put("seller",resultSet.getString("seller"));
						// System.out.println(pointHistoryDTO);
						pointHistoryList.add(pointHistoryDTO);
						
					}
				return pointHistoryList;
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					DBManager.close(connection, preparedStatement, resultSet);

				}
				
				
				return null;
	}

	public ArrayList<Map<String, Object>> getCrHistory() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql=null;
		try {
			connection = DBManager.getConnection();
			sql ="select * from c_rhistory order by dealing_date desc";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			ArrayList<Map<String, Object>>pointHistoryList = new ArrayList<Map<String, Object>>();
			while(resultSet.next()){
				HashMap<String, Object> pointHistoryDTO = new HashMap<String, Object>();
				
				pointHistoryDTO.put("no",resultSet.getInt("no"));
				pointHistoryDTO.put("dealing_point",resultSet.getInt("dealing_point"));
				pointHistoryDTO.put("point_before",resultSet.getInt("point_before"));
				pointHistoryDTO.put("point_after",resultSet.getInt("point_after"));
				pointHistoryDTO.put("why", resultSet.getString("why"));
				pointHistoryDTO.put("dealing_date",resultSet.getString("dealing_date"));
				pointHistoryDTO.put("id",resultSet.getString("id"));
				System.out.println(pointHistoryDTO);
				pointHistoryList.add(pointHistoryDTO);
				
			}
		return pointHistoryList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
		
		
		return null;
}

	public ArrayList<Map<String, Object>> getRefundRequestList() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql=null;
		try {
			connection = DBManager.getConnection();
			sql ="select * from pointrefundrequest order by request_date desc";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			ArrayList<Map<String, Object>>pointHistoryList = new ArrayList<Map<String, Object>>();
			while(resultSet.next()){
				HashMap<String, Object> pointHistoryDTO = new HashMap<String, Object>();
				
				pointHistoryDTO.put("no",resultSet.getInt("request_no"));
				pointHistoryDTO.put("dealing_point",resultSet.getInt("dealing_point"));
				pointHistoryDTO.put("done", resultSet.getString("done"));
				pointHistoryDTO.put("request_date",resultSet.getString("request_date"));
				pointHistoryDTO.put("refund_bank",resultSet.getString("refund_bank"));
				pointHistoryDTO.put("refund_accountant",resultSet.getString("refund_accountant"));
				pointHistoryDTO.put("id",resultSet.getString("id"));
				System.out.println(pointHistoryDTO);
				pointHistoryList.add(pointHistoryDTO);
				
			}
		return pointHistoryList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
		
		
		return null;
	}

	public void refundDone(int no) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql=null;
		try {
			connection = DBManager.getConnection();
			sql ="update pointrefundrequest set done=? where request_no = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(2, no);
			preparedStatement.setString(1, "Y");
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement);

		}
	}

	public void insertSellHistory(int dealing_point, MemberDTO buyerDTO, MemberDTO sellerDTO) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql=null;
		int no=1;
		String why = "판매";
		try {
			connection = DBManager.getConnection();
			sql ="select max(no) from sellhistory";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				no = resultSet.getInt(1)+1;
			}
			
			
			sql = "insert into sellhistory(no, dealing_point, point_before, point_after, dealing_date, buyer, seller, why) "
					+ "values(?,?,?,?,to_char(sysdate,'YYYY/mm/dd hh24:mi:SS'),?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, no);
			preparedStatement.setDouble(2, dealing_point);
			preparedStatement.setInt(3, sellerDTO.getPoint());
			preparedStatement.setDouble(4, (int)(sellerDTO.getPoint() + dealing_point));
			preparedStatement.setString(5, buyerDTO.getId());
			preparedStatement.setString(6, sellerDTO.getId());
			preparedStatement.setString(7, why);
			preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
		
		
	}
	
	public void insertBuyHistory(double dealing_point, MemberDTO buyerDTO, MemberDTO sellerDTO) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql=null;
		int no=1;
		String why = "구매";
		try {
			connection = DBManager.getConnection();
			sql ="select max(no) from buyhistory";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				no = resultSet.getInt(1)+1;
			}
			
			
			sql = "insert into buyhistory(no, dealing_point, point_before, point_after, dealing_date, buyer, seller, why) "
					+ "values(?,?,?,?,to_char(sysdate,'YYYY/mm/dd hh24:mi:SS'),?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, no);
			preparedStatement.setDouble(2, dealing_point);
			preparedStatement.setInt(3, buyerDTO.getPoint());
			preparedStatement.setDouble(4, (int)(buyerDTO.getPoint() + dealing_point));
			preparedStatement.setString(5, buyerDTO.getId());
			preparedStatement.setString(6, sellerDTO.getId());
			preparedStatement.setString(7, why);
			preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);

		}
		
		
	}

}
