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

public class ClassSearchDao {
	
	private static ClassSearchDao instance;
	
	private ClassSearchDao() {}
	
	public static ClassSearchDao getInstance() {
		if(instance == null) {
			instance = new ClassSearchDao();
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
	
	public int getTotalCnt(String sword) throws SQLException {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from classe where keyword like ?";
//		String sql = "select count(*) from classe c, teacher t where c.tchno = t.tchno and (c.keyword like ? or t.name like ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+sword+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) totCnt = rs.getInt(1);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
				
		
		return totCnt;
	}
	
	public List<Classe> classList(int startRow, int endRow, String sword) throws SQLException {
		List<Classe> list = new ArrayList<Classe>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Classe cls = null;
		
		String sql = "select * "
				+ "from (select rownum rn, a.* "
				+ "from  (select * from classe where  keyword like ?) a) "
				+ "where rn between ? and ?";
		
//		String sql = "select * "
//				+ "from (select rownum rn, a.* "
//				+ "from  (select * from classe c, teacher t where c.tchno = t.tchno and (keyword like ? or name like ?)) a) "
//				+ "where rn between ? and ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+sword+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cls = new Classe();
				cls.setClassno(rs.getInt("classno"));
				cls.setClass_name(rs.getString("class_name"));
				cls.setGrade(rs.getInt("grade"));
				cls.setKeyword(rs.getString("keyword"));
				cls.setLec_time(rs.getString("lec_time"));
				cls.setLec_duration(rs.getString("lec_duration"));
				cls.setTeacher_intro(rs.getString("teacher_intro"));
				cls.setLec_intro(rs.getString("lec_intro"));
				cls.setCurriculum(rs.getString("curriculum"));
				cls.setReg_date(rs.getDate("mat_date"));
				cls.setMain_img(rs.getString("main_img"));
				cls.setStatus(rs.getInt("status"));
				cls.setMat_date(rs.getDate("mat_date"));
				cls.setTchno(rs.getInt("tchno"));
				cls.setSubjectno(rs.getInt("subjectno"));
				cls.setStudno(rs.getInt("studno"));
				
				list.add(cls);
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
