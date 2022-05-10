package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TeacherDao {
	private static TeacherDao instance;
	
	public TeacherDao() {
		// TODO Auto-generated constructor stub
	}
	public static TeacherDao getInstance() {
		if (instance == null) {
			instance = new TeacherDao();
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
	
	// 로그인 시 id, pw 체크
	public int check(String tch_id, String password) throws SQLException {
		Teacher teacher = new Teacher();
		int result = 0;
		String sql = "select password from teacher where tch_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("Dao check sql -> " + sql);
		System.out.println("Dao check session tch_id -> " + tch_id);
		System.out.println("Dao check session pw -> " + password);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tch_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPassword = rs.getString(1);
				if (dbPassword.equals(password)) result = 1;
				else result = 0;
			} else result = -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	// 로그인 시 사용자 정보 가져오기
	public Teacher userInfo(String tch_id) throws SQLException {
		Teacher teacher = new Teacher();
		String sql = "select tchno from teacher where tch_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("Dao userInfo sql -> " + sql);
		System.out.println("Dao userInfo tch_id -> " + tch_id);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tch_id);
			rs = pstmt.executeQuery();
			if (rs.next()) 
				teacher.setTchno(rs.getInt("tchno"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return teacher;
	}
	// 회원정보 수정 Step1 : 회원 정보 가져오기
	public Teacher selectUp(String tch_id) throws SQLException {
		Teacher teacher = new Teacher();
		String sql = "select * from teacher where tch_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("TchDao selectUp sql -> " + sql);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tch_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				teacher.setTch_id(rs.getString("tch_id"));
				teacher.setName(rs.getString("name"));
				teacher.setPhone(rs.getString("phone"));
				teacher.setBirth(rs.getString("birth"));
				teacher.setGender(rs.getInt("gender"));
				teacher.setResume(rs.getString("resume"));
				
				System.out.println("TchDao tch_id" + rs.getString("tch_id"));
				System.out.println("TchDao name" + rs.getString("name"));
				System.out.println("TchDao phone" + rs.getString("phone"));
				System.out.println("TchDao birth" + rs.getString("birth"));
				System.out.println("TchDao gender" + rs.getInt("gender"));
				System.out.println("TchDao resume" + rs.getString("resume"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return teacher;
		
	}	
	// 회원정보 수정 Step2 : 회원 정보 수정하기
	public int update(Teacher teacher) throws SQLException {
		int result = 0;
		String sql = "update teacher set name=?, phone=?, birth=?, gender=?, resume=? where tch_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println("Dao sql -> " + sql);
		System.out.println("Dao teacher -> " + teacher);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getName());
			pstmt.setString(2, teacher.getPhone());
			pstmt.setString(3, teacher.getBirth());
			pstmt.setInt(4, teacher.getGender());
			pstmt.setString(5, teacher.getResume());
			pstmt.setString(6, teacher.getTch_id());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	//비밀번호 변경
	public int updatePw(String tch_id, String newPw) throws SQLException {
		int updatePwResult = 0;
		Connection conn = null;
		String sql = "update teacher set password=? where tch_id=?";
		PreparedStatement pstmt=null;
		
		System.out.println("updatePw SQL -> " + sql);
		System.out.println("updatePw tch_id -> " + tch_id);
		System.out.println("updatePw newPw -> " + newPw);
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, tch_id);
			updatePwResult=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(conn !=null) conn.close();
			if(pstmt !=null) pstmt.close();
		}
		return updatePwResult;
	}
	// 회원탈퇴
	public int delete(String tch_id) throws SQLException {
		int result = 0;
		String sql = "delete from teacher where tch_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tch_id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	// 회원가입 -----------------------------------------------------------------
	// 회원가입 ID 중복체크
	public int select(String tch_id) throws SQLException{
		 int result = 0;
		 Connection conn =null;
		 String sql="select * from teacher where tch_id=?";
		 PreparedStatement pstmt=null;
		 ResultSet rs= null;
		 
		 try {
			conn=getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, tch_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) result=1;
			else result=0; 
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(conn !=null) conn.close();
		}
		 return result;
	}
	// 회원가입 데이터 DB 넣기
	public int insert(Teacher teacher) throws SQLException{
		int result=0;
		Connection conn=null;
		String sql="insert into teacher values(tchno_seq.nextval,?,?,?,?,?,?,1,?)";
		PreparedStatement pstmt=null;
		
		System.out.println("Dao check sql-> "+sql);
		System.out.println("Dao check id-> "+teacher.getTch_id());
		System.out.println("Dao check sql-> "+teacher.getPassword());
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, teacher.getTch_id());
			pstmt.setString(2, teacher.getPassword());
		    pstmt.setString(3, teacher.getResume()); 
			pstmt.setInt(4, teacher.getGender());
			pstmt.setString(5, teacher.getPhone());
			pstmt.setString(6, teacher.getBirth());
			pstmt.setString(7, teacher.getName());
			
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(conn !=null)conn.close();
			if(pstmt !=null) conn.close();
		}
		return result;
	}
	
}
