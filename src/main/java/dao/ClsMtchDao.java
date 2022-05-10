package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ClsMtchDao {
	private static ClsMtchDao instance;
	private ClsMtchDao() {}
	public static ClsMtchDao getInstance() {
		if(instance==null) {
			instance=new ClsMtchDao();
		}
		return instance;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	
	
	public int listcheck(int studno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0; // // 회원인데 클래스에 수업 x
		
		String sql="SELECT * FROM classe WHERE studno=?";
		System.out.println("sql listcheck =>"+sql);
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("pstmt listcheck=>"+pstmt);
			pstmt.setInt(1, studno);
			rs=pstmt.executeQuery();
			System.out.println("rs listcheck=>"+rs);
			
			
			if(rs.next()) {
					result=1;
					// 회원인데 클래스에 수업 내역이 있다는 말 
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} 
		return result;
	}
	
	
	
	public List<Classe> listshow (int studno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Classe> list=new ArrayList<Classe>();
		
 
		String sql="SELECT * FROM classe WHERE studno=?";
		// 아이디가 들어갔을 떄, 선생님 이름과 아이디 포함 
		System.out.println("sql listshow =>"+sql);
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("pstmt listshow=>"+pstmt);
			pstmt.setInt(1, studno);
			rs=pstmt.executeQuery();
			System.out.println("rs listshow=>"+rs);
			
			while(rs.next()) {
				Classe class1=new Classe();
				class1.setClassno(rs.getInt(1));
				class1.setClass_name(rs.getString(2));
				class1.setGrade(rs.getInt(3));
				class1.setKeyword(rs.getString(4));
				class1.setLec_time(rs.getString(5));
				class1.setLec_duration(rs.getString(6));
				class1.setTeacher_intro(rs.getString(7));
				class1.setLec_intro(rs.getString(8));
				class1.setCurriculum(rs.getString(9));
				class1.setReg_date(rs.getDate(10));
				class1.setMain_img(rs.getString(11));
				class1.setStatus(rs.getInt(12));
				class1.setMat_date(rs.getDate(13));
				class1.setTchno(rs.getInt(14));
				class1.setSubjectno(rs.getInt(15));
				class1.setStudno(rs.getInt(16));
				list.add(class1);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return list;
	}
	
	
	public Classe clsdetail(int classno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Classe classe=new Classe();
		
		String sql="SELECT c.*, t.name FROM teacher t, classe c WHERE t.tchno=c.tchno AND classno=?";
		System.out.println("sql clsdetail =>"+sql);
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("pstmt clsdetail=>"+pstmt);
			pstmt.setInt(1, classno);
			rs=pstmt.executeQuery();
			System.out.println("rs clsdetail=>"+rs);
			
			if(rs.next()) {
				classe.setClassno(rs.getInt(1));
				classe.setClass_name(rs.getString(2));
				classe.setGrade(rs.getInt(3));
				classe.setKeyword(rs.getString(4));
				classe.setLec_time(rs.getString(5));
				classe.setLec_duration(rs.getString(6));
				classe.setTeacher_intro(rs.getString(7));
				classe.setLec_intro(rs.getString(8));
				classe.setCurriculum(rs.getString(9));
				classe.setReg_date(rs.getDate(10));
				classe.setMain_img(rs.getString(11));
				classe.setStatus(rs.getInt(12));
				classe.setMat_date(rs.getDate(13));
				classe.setTchno(rs.getInt(14));
				classe.setSubjectno(rs.getInt(15));
				classe.setStudno(rs.getInt(16));
				classe.setName(rs.getString(17));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
		return classe;
	}
	public List<Loc> ploc(int classno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Loc> list=new ArrayList<Loc>();
		
		String sql="SELECT lm.loc_name, upper_locnm(lm.upper_locno) upper_locname "
				+ "FROM loc l, locmaster lm WHERE l.locno=lm.locno AND l.classno=?";
		System.out.println("sql ploc =>"+sql);
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("pstmt ploc=>"+pstmt);
			pstmt.setInt(1, classno);
			rs=pstmt.executeQuery();
			System.out.println("rs ploc=>"+rs);
			
			while(rs.next()) {
				Loc loc=new Loc();
				loc.setLoc_name(rs.getString(1));
				loc.setUpper_locname(rs.getString(2));
				list.add(loc);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return list;
	}
	
	
	public int mtchCheck(int studno, int classno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql="UPDATE classe SET studno=?, status=1, mat_date=sysdate WHERE classno=?";
		System.out.println("sql mtchCheck=>"+sql);
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("pstmt=>"+pstmt);
			pstmt.setInt(1, studno);
			pstmt.setInt(2, classno);
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("result mtchCheck=>"+result);
		return result;
	} 
	
	
	
	
	public String tresume(int classno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String resume=null;
		
		String sql="SELECT t.resume FROM teacher t, classe c WHERE t.tchno=c.tchno AND classno=?";
		System.out.println("sql tresume=>"+sql);
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("pstmt tresume=>"+pstmt);
			pstmt.setInt(1, classno);
			rs=pstmt.executeQuery();
			System.out.println("rs tresume=>"+rs);
			if(rs.next()) {
				resume=rs.getString(1);
			}
			
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		System.out.println("rs tresume=>"+resume);
		return resume;
	}
	
	public int mtchCnlCheck(int classno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql="UPDATE classe SET studno=null, status=0, mat_date=null WHERE classno=?";
		System.out.println("sql mtchCheck=>"+sql);
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("pstmt=>"+pstmt);
			pstmt.setInt(1, classno);
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("result mtchCheck=>"+result);
		return result;
	} 
}
