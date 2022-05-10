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

public class SubjectDao {
	
	private static SubjectDao instance;
	
	private SubjectDao() {}
	
	public static SubjectDao getInstance() {
		if(instance == null) {
			instance = new SubjectDao();
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
	
	public List<Subject> select_b() throws SQLException {
		List<Subject> list = new ArrayList<Subject>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Subject subject = null;
		
		String sql = "select * from subject where b_subjectno is null";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				subject = new Subject();
				subject.setSubjectno(rs.getInt("subjectno"));
				subject.setSubject_name(rs.getString("subject_name"));
				subject.setB_subjectno(rs.getInt("b_subjectno"));
				
				list.add(subject);
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
	
	public List<Subject> select_s(int b_subjectno) throws SQLException {
		List<Subject> list = new ArrayList<Subject>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Subject subject = null;
		
		String sql = "select * from subject where b_subjectno=?";
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_subjectno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				subject = new Subject();
				subject.setSubjectno(rs.getInt("subjectno"));
				subject.setSubject_name(rs.getString("subject_name"));
				subject.setB_subjectno(rs.getInt("b_subjectno"));
				
				list.add(subject);
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
