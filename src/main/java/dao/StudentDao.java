package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDao {
	private static StudentDao instance;
	
	public StudentDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static StudentDao getInstance() {
		if (instance == null) {
			instance = new StudentDao();
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
	public int check(String stu_id, String password) throws SQLException {
		int result = 0;
		String sql = "select password from student where stu_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("Dao check sql -> " + sql);
		System.out.println("Dao check session stu_id -> " + stu_id);
		System.out.println("Dao check session pw -> " + password);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stu_id);
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
	public Student userInfo(String stu_id) throws SQLException {
		Student student = new Student();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select name, studno from student where stu_id=?";
		System.out.println("Dao userInfo sql -> " + sql);
		System.out.println("Dao userInfo stu_id -> " + stu_id);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stu_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				student.setName(rs.getString("name"));
				student.setStudno(rs.getInt("studno"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return student;
	}
	// 회원정보 수정 Step1 : 회원 정보 가져오기
	public Student selectUp(String stu_id) throws SQLException {
		Student student = new Student();
		String sql = "select * from student where stu_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stu_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				student.setStu_id(rs.getString("stu_id"));
				student.setName(rs.getString("name"));
				student.setPhone(rs.getString("phone"));
				student.setBirth(rs.getString("birth"));
				student.setGender(rs.getInt("gender"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return student;
		
	}
	// 회원정보 수정 Step2 : 회원 정보 수정하기
	public int update(Student student) throws SQLException {
		int result = 0;
		String sql = "update student set name=?, phone=?, birth=?, gender=? where stu_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println("Dao sql -> " + sql);
		System.out.println("Dao student -> " + student);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getPhone());
			pstmt.setString(3, student.getBirth());
			pstmt.setInt(4, student.getGender());
			pstmt.setString(5, student.getStu_id());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	// 비밀번호 변경
	public int updatePw(String stu_id, String newPw) throws SQLException{
		int updatePwResult = 0;
		Connection conn = null;
		String sql = "update student set password=? where stu_id=?";
		PreparedStatement pstmt = null;
		
		System.out.println("updatePw sql-> " + sql);
		System.out.println("updatePw stu_id -> " + stu_id);
		System.out.println("updatePw newPw password -> " + newPw);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, stu_id);
			updatePwResult = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt !=null) pstmt.close();
			if(conn !=null) conn.close();
		}
		return updatePwResult;
	}	
	// 회원탈퇴
	public int delete(String stu_id) throws SQLException {
		int result = 0;
		String sql = "delete from student where stu_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stu_id);
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
	public int select(String stu_id) throws SQLException{
		int result= 0;
		Connection conn=null;
		String sql="select * from student where stu_id=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, stu_id);
			rs=pstmt.executeQuery();
			if(rs.next()) result=1;
			else result=0;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs !=null) rs.close();
			if(conn !=null) conn.close();
			if(pstmt !=null) pstmt.close();
		}
		return result;
	} 
	// 회원가입 데이터 DB 넣기
	public int insert(Student student) throws SQLException{
		int result=0;
		Connection conn=null;
		String sql="insert into student values(studno_seq.nextval,?,?,?,?,?,?,'1')";
		PreparedStatement pstmt=null;
		
		System.out.println("Dao check sql-> "+sql);
		System.out.println("Dao check id-> "+student.getStu_id());
		System.out.println("Dao check sql-> "+student.getPassword());
		
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,student.getStu_id());
			pstmt.setString(2,student.getPassword());
			pstmt.setString(3, student.getName());
			pstmt.setInt(4, student.getGender());
			pstmt.setString(5, student.getPhone());
			pstmt.setString(6, student.getBirth());
			
			result=pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt !=null) pstmt.close();
			if(conn !=null) conn.close();
		}
		return result;
	} 
}
