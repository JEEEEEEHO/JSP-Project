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

public class LocDao {
	
	private static LocDao instance;
	
	private LocDao() {}
	
	public static LocDao getInstance() {
		if(instance == null) {
			instance = new LocDao();
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

	public List<Loc> getLocList(int classno) throws SQLException {
		List<Loc> list = new ArrayList<Loc>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		Loc loc = null;
		int locno = 0;
		
		String sql3 = "select locno "
				+ "from loc "
				+ "where classno=?";
		
		String sql = "select m.locno, m.loc_name, m.upper_locno, n.loc_name "
				+ "from locmaster m, locmaster n "
				+ "where m.upper_locno = n.locno "
				+ "and m.locno=?";
		
		String sql2 = "select * "
				+ "from locmaster "
				+ "where locno=?";
		
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql3);
			pstmt.setInt(1, classno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				locno = rs.getInt(1);
				System.out.println("locno-->"+locno);
				
				if(locno==100 || locno==200) {
					System.out.println("작업1...");
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, locno);
					rs2 = pstmt2.executeQuery();
					
					if(rs2.next()) {
						loc = new Loc();
						loc.setClassno(classno);
						loc.setLocno(rs2.getInt(1));
						loc.setLoc_name(rs2.getString(2));
						list.add(loc);
					}
					
					
				} else {
					System.out.println("작업2...");
					pstmt2 = conn.prepareStatement(sql);
					pstmt2.setInt(1, locno);
					rs2 = pstmt2.executeQuery();
					
					if(rs2.next()) {
						loc = new Loc();
						loc.setClassno(classno);
						loc.setLocno(rs2.getInt(1));
						loc.setLoc_name(rs2.getString(2));
						loc.setUpper_locname(rs2.getString(4));
						
						list.add(loc);
					}
					
				}
				rs2.close();
				pstmt2.close();
			}

			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(rs2 != null) rs2.close();
			if(pstmt != null) pstmt.close();
			if(pstmt2 != null) pstmt2.close();
			if(conn != null) conn.close();
		}
		
		return list;
	}

	public int delete(int classno) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from loc where classno=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return result;
	}

	public int insert(int classno, String str_locno) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into loc values(?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classno);
			pstmt.setString(2, str_locno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return result;
	}
	
	
	
	
}
