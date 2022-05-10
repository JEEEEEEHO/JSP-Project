package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LocMasterDao {
	
	private static LocMasterDao instance;
	
	private LocMasterDao() {}
	
	public static LocMasterDao getInstance() {
		if(instance == null) {
			instance = new LocMasterDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	public List<LocMaster> select_b() throws SQLException{
		List<LocMaster> list = new ArrayList<LocMaster>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LocMaster lm = null;
		
		String sql = "select * from locmaster where upper_locno is null";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lm = new LocMaster();
				lm.setLocno(rs.getInt("locno"));
				lm.setLoc_name(rs.getString("loc_name"));
				lm.setUpper_locno(rs.getInt("upper_locno"));
				
				list.add(lm);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return list;
	}
	
	
	public List<LocMaster> select_s(int upper_locno) throws SQLException{
		List<LocMaster> list = new ArrayList<LocMaster>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LocMaster lm = null;
		
		String sql = "select * from locmaster where upper_locno=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, upper_locno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lm = new LocMaster();
				lm.setLocno(rs.getInt("locno"));
				lm.setLoc_name(rs.getString("loc_name"));
				lm.setUpper_locno(rs.getInt("upper_locno"));
				
				list.add(lm);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return list;
	}
	
	
}
