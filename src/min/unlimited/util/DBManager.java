package min.unlimited.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBManager {

	public static Connection getConnection(){
		Connection conn = null;
		try {
			
			Context context = new InitialContext();
			BasicDataSource basicDataSource = (BasicDataSource) context.lookup("java:/comp/env/jdbc/OracleDB");
			
			conn = basicDataSource.getConnection();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
		
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
public static void close(Connection conn, PreparedStatement pstmt){
		
		try {
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

public static void close(Connection conn, Statement stmt, ResultSet rs){
	
	try {
		
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
}