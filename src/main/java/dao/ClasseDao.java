package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ClasseDao {
	
	private static ClasseDao instance;
	
	private ClasseDao() {}
	
	public static ClasseDao getInstance() {
		if(instance == null) {
			instance = new ClasseDao();
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
	

	
	public int getTotalCnt(String grade, String subjectno, String locno) throws SQLException {
		int totCnt = 0;
		System.out.println("grade->"+grade);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select count(*) "
				+ "from ("
				+ "select distinct c.* "
				+ "from classe c, loc l "
				+ "where c.status=0 "
				+ "and c.classno = l.classno "
				+ "and c.subjectno like ? "
				+ "and l.locno like ?)";
		
		String sql2 = "select count(*) "
				+ "from ("
				+ "select distinct c.* "
				+ "from classe c, loc l "
				+ "where (c.grade=? or c.grade=0) "
				+ "and c.status=0 "
				+ "and c.classno = l.classno "
				+ "and c.subjectno like ? "
				+ "and l.locno like ?)";
		
		try {
			conn = getConnection();
			
			if(grade.equals("0")) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, subjectno);
				pstmt.setString(2, locno);
			} else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, grade);
				pstmt.setString(2, subjectno);
				pstmt.setString(3, locno);
			};
			
			rs = pstmt.executeQuery();
			if(rs.next())	totCnt = rs.getInt(1);
			else 			totCnt = 0;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return totCnt;
	}
	
	
	
	
	public List<Classe> classList(String grade, String subjectno, String locno) throws SQLException {
		List<Classe> list = new ArrayList<Classe>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Classe cls = null;
		
		System.out.println("grade--->"+grade);
		

		String sql = "select distinct c.* "
				+ "from classe c, loc l "
				+ "where (c.classno = l.classno) and (c.subjectno like ?) and (l.locno like ?) and (c.status=0)";
		
		
		String sql2 = "select distinct c.* "
				+ "from classe c, loc l "
				+ "where (c.classno = l.classno) and (c.grade=? or c.grade=0) and (c.subjectno like ?) and (l.locno like ?) and (c.status=0)";
		
		try {
			conn = getConnection();
			
			if(grade.equals("0")) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, subjectno);
				pstmt.setString(2, locno);
			} else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, grade);
				pstmt.setString(2, subjectno);
				pstmt.setString(3, locno);
			}
			
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
	
	
	public int getTchClasseCount(int tchno) throws SQLException {
		int tot = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//classe 에있는 갯수를 가져온다
		String sql="select count(*) from classe where tchno=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tchno);
			rs = pstmt.executeQuery();
			if (rs.next()) tot = rs.getInt(1);
			
		}  catch(Exception e) { System.out.println(e.getMessage());
		}  finally {
			if(rs !=null) 	rs.close();
			if(pstmt !=null) pstmt.close();
			if(conn !=null) conn.close();
		}
		return tot;
	}
	
	
//----------------리스트 갯수 나열하기	
	public void readCount(int classno) throws SQLException {
	Connection conn = null;	
	PreparedStatement pstmt= null; 
	String sql="update tchclass set readcount=readcount+1 where classno=?";
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, classno);			
		pstmt.executeUpdate();
	} catch(Exception e) {	System.out.println(e.getMessage()); 
	} finally {
		if (pstmt != null) pstmt.close();
		if (conn !=null) conn.close();
	}
}
// ------------------------------- 리스트 값 나열하기	
	public List<Classe> tchClassList(int startRow, int endrow, int tchno) throws SQLException{
		List<Classe> list = new ArrayList<Classe>();
		
		Connection conn =null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql= "select classno, class_name, subject_name, grade, status "
				+ "from (select rownum rn, a.* "
				+ "from (select c.classno, c.class_name, s.subject_name, c.grade, c.status "
				+ "from classe c, subject s "
				+ "where c.subjectno = s.subjectno "
				+ "and c.tchno=?) a) "
				+ "where rn between ? and ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tchno);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Classe tchclass = new Classe();
				tchclass.setClassno(rs.getInt("classno"));
				tchclass.setClass_name(rs.getString("class_name"));
				tchclass.setSubject_name(rs.getString("subject_name"));
				tchclass.setGrade(rs.getInt("grade"));
				tchclass.setStatus(rs.getInt("status"));
				list.add(tchclass);
			}
		} catch(Exception e) {System.out.println(e.getMessage());
		
		}finally {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(conn !=null) conn.close();
		}
		
		return list;
	}
	
//---------------------클래스 등록
	public int insert(Classe tchclass) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		int result = 0;		    
		ResultSet rs = null;
		
		String sql="insert into classe values(class_seq.nextval,?,?,?,?,?,?,?,?,sysdate,?,0,'',?,?,'')";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tchclass.getClass_name());
			pstmt.setInt(2, tchclass.getGrade());
			pstmt.setString(3, tchclass.getKeyword());
			pstmt.setString(4, tchclass.getLec_time());
			pstmt.setString(5, tchclass.getLec_duration());
			pstmt.setString(6, tchclass.getTeacher_intro());
			pstmt.setString(7, tchclass.getLec_intro());
			pstmt.setString(8, tchclass.getCurriculum());
			pstmt.setString(9, tchclass.getMain_img());
			pstmt.setInt(10, tchclass.getTchno());
			pstmt.setInt(11, tchclass.getSubjectno());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		
		
		return result;
	}

//-----------------리스트 업데이트
	public int update(Classe tchclass) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		int result = 0;			
		String sql="update classe set class_name=?, grade=?, subjectno=?, keyword=?,"
				+ "lec_time=?, lec_duration=?, teacher_intro=?, lec_intro=?, curriculum=?, main_img=? "
				+ "where classno=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, tchclass.getClass_name());
			pstmt.setInt(2, tchclass.getGrade());
			pstmt.setInt(3, tchclass.getSubjectno());
			pstmt.setString(4, tchclass.getKeyword());
			pstmt.setString(5, tchclass.getLec_time());
			pstmt.setString(6, tchclass.getLec_duration());
			pstmt.setString(7, tchclass.getTeacher_intro());
			pstmt.setString(8, tchclass.getLec_intro());
			pstmt.setString(9, tchclass.getCurriculum());
			pstmt.setString(10, tchclass.getMain_img());
			pstmt.setInt(11, tchclass.getClassno());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
		}
// -----------------------수정,삭제 선택	
	public Classe tchClasseSelect(int classno) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;
		String sql = "select c.*, s.subject_name "
				+ "from classe c, subject s "
				+ "where c.subjectno = s.subjectno "
				+ "and c.classno=?";
		Classe tchclass = new Classe();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tchclass.setClassno(rs.getInt("classno"));
				tchclass.setClass_name(rs.getString("class_name"));
				tchclass.setGrade(rs.getInt("grade"));
				tchclass.setStatus(rs.getInt("status"));
				tchclass.setKeyword(rs.getString("keyword"));
				tchclass.setLec_time(rs.getString("lec_time"));
				tchclass.setLec_duration(rs.getString("lec_duration"));
				tchclass.setTeacher_intro(rs.getString("teacher_intro"));
				tchclass.setLec_intro(rs.getString("lec_intro"));
				tchclass.setCurriculum(rs.getString("curriculum"));
				tchclass.setMat_date(rs.getDate("reg_date"));
				tchclass.setMain_img(rs.getString("main_img"));
				tchclass.setTchno(rs.getInt("tchno"));
				tchclass.setStudno(rs.getInt("studno"));
				tchclass.setSubjectno(rs.getInt("subjectno"));
				tchclass.setSubject_name(rs.getString("subject_name"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return tchclass;
	}
//---------------------삭제선택 및 완료
	public int tchClassedelete(int classno) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		int result = 0;		    

		String sql="delete from CLASSE where classno=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classno);
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}

	public int getClassno(int tchno) throws SQLException {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select max(classno) "
				+ "from classe "
				+ "where tchno=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tchno);
			
			rs = pstmt.executeQuery();
			if(rs.next())	result = rs.getInt(1);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		
		return result;
	}


	
	
	
}
