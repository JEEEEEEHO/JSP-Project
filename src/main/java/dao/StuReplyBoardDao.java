package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.naming.java.javaURLContextFactory;


public class StuReplyBoardDao {
	private static StuReplyBoardDao instance;
	private StuReplyBoardDao() {}
	public static StuReplyBoardDao getInstance() {
		if(instance==null) {
			instance=new StuReplyBoardDao();
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
	
	
	public int getTotalCnt() throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		
		String sql="SELECT count(*) FROM   teacher t, board b WHERE b.tchno=t.tchno AND b.re_level = 0";

		System.out.println("getTotalCnt sql-->"+sql);
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			//pstmt.setInt(1, startRow);
			//pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
		System.out.println("getTotalCnt result-->"+result);

		return result;
	}
	
	public List<Board> boardlist(int startRow, int endRow) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList<Board>();
	
		
		String sql="SELECT * FROM (SELECT rownum rn,a.*  FROM (SELECT b.* , t.name FROM   teacher t, board b "
				+ "WHERE b.tchno=t.tchno AND b.re_step = 0 order by ref desc,re_step) a) "
				+ "WHERE rn between ? and ?";
		System.out.println("sql 작동중->"+sql);

		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			System.out.println("sql pstmt after ...");
		
			while (rs.next()) {
					Board board=new Board();
					board.setBoardno(rs.getInt("boardno"));
					System.out.println("boardlist boardno->"+rs.getString("boardno"));
					
					board.setTchno(rs.getInt("tchno"));
					System.out.println("boardlist tchno->"+rs.getString("tchno"));
					
					board.setStudno(rs.getInt("studno"));
					System.out.println("boardlist studno->"+rs.getString("studno"));
					
					board.setContent(rs.getString("content"));
					System.out.println("boardlist content->"+rs.getString("content"));
					
					board.setSubject(rs.getString("subject"));
					System.out.println("boardlist subject->"+rs.getString("subject"));

					board.setWrite_date(rs.getDate("write_date"));
					System.out.println("boardlist write_date->"+rs.getString("write_date"));

					board.setReadcount(rs.getInt("readcount"));
					System.out.println("boardlist readcount->"+rs.getString("readcount"));

					board.setRef(rs.getInt("ref"));
					System.out.println("boardlist ref->"+rs.getString("ref"));

					board.setRe_step(rs.getInt("re_step"));
					System.out.println("boardlist re_step->"+rs.getString("re_step"));

					board.setRe_level(rs.getInt("re_level"));
					System.out.println("boardlist re_level->"+rs.getString("re_level"));

					board.setName(rs.getString("name"));
					System.out.println("boardlist name->"+rs.getString("name"));

					list.add(board);
				} 
			
			
		} catch (SQLException e) {
			System.out.println("SQLException->"+e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
		return list;
	} 
	
	
	public void readCount(int boardno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql="UPDATE board SET readcount=readcount+1 WHERE boardno=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardno);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
	}

	public Board contentselect(int ref, int a) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		Board board=new Board();

		String sql="SELECT b.*, t.name FROM board b, teacher t WHERE t.tchno=b.tchno AND ref=? AND re_level=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, a);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				board.setBoardno(rs.getInt("boardno"));
				board.setTchno(rs.getInt("tchno"));
				board.setStudno(rs.getInt("studno"));
				board.setContent(rs.getString("content"));
				board.setSubject(rs.getString("subject"));
				board.setWrite_date(rs.getDate("write_date"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(a);
				board.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return board;
	}
	
	public int check(int studno) throws SQLException { // write rewrite 
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		
		String sql="SELECT * FROM board WHERE studno=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, studno);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=1;
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
	
	
	public int insert(Board board) throws SQLException {
		int boardno=board.getBoardno();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int result=0;
		
		String sql1="UPDATE board SET re_step=re_step+1 WHERE ref=? and re_step>?"; 
		String sql3="INSERT INTO board values(boardno_seq.nextval,?,?,?,?,sysdate,?,?,?,?)";
		System.out.println("sql1=>"+sql1);
		System.out.println("sql3=>"+sql3);
		

		try {
			if(boardno!=0) { 
				// 글이 존재한다면 = 댓글이라면 = 나의 경우에 해당함 
				conn=getConnection();
				pstmt=conn.prepareStatement(sql1);
				System.out.println("pstmt1=>"+pstmt);
				pstmt.setInt(1, board.getRef()); 
				// 보드 넘버랑 같은 것  
				pstmt.setInt(2, board.getRe_step()); 
				// 0 원글에 대한 정보만을 불러오기 때문에 무조건 0보다 큰 값들에 대해서=댓글들에 대해서 ((대댓글 기능 없음) 현재까진...)
				pstmt.executeUpdate();
				pstmt.close();
				board.setRe_step(board.getRe_step()+1); //0->1
				board.setRe_level(board.getRe_level()+1); //0->1
			}
			
			// 원글이라면 
			pstmt=conn.prepareStatement(sql3);
			System.out.println("pstmt2=>"+pstmt);
			pstmt.setInt(1, board.getTchno());
			pstmt.setInt(2, board.getStudno());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getSubject());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, board.getRef()); 
			//넘어오는 것은 원글 클릭시 => 원글의 ref 정보를 가지고 감 (그대로)
			pstmt.setInt(7, board.getRe_step());
			pstmt.setInt(8, board.getRe_level());
			result=pstmt.executeUpdate();
			System.out.println("result1=>"+result);

		} catch (SQLException e) {
			System.out.println("sql=>"+e.getMessage());

		}finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		System.out.println("result2=>"+result);
		return result;
	}
	
	public Board select2(int boardno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board board=new Board();

		String sql="SELECT * FROM board WHERE boardno=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardno);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				board.setBoardno(rs.getInt("boardno"));
				board.setTchno(rs.getInt("tchno"));
				board.setStudno(rs.getInt("studno"));
				board.setContent(rs.getString("content"));
				board.setSubject(rs.getString("subject"));
				board.setWrite_date(rs.getDate("write_date"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return board;
	}
	
	public List<Board> replylist(int ref) throws SQLException{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList<Board>();
		
		String sql="SELECT b.*, s.name FROM board b, student s WHERE b.studno=s.studno AND ref=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				Board board=new Board();
				board.setBoardno(rs.getInt("boardno"));
				board.setTchno(rs.getInt("tchno"));
				board.setStudno(rs.getInt("studno"));
				board.setContent(rs.getString("content"));
				board.setSubject(rs.getString("subject"));
				board.setWrite_date(rs.getDate("write_date"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
				board.setName2(rs.getString("name"));
				list.add(board);
			} 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
		return list;
	}
	
	public Board replyupdate(int boardno) throws SQLException { 
		// 댓글 찾기 및 수정 
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board board=new Board();

		
		String sql="SELECT * FROM board WHERE boardno=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardno);
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				board.setBoardno(boardno);
				board.setTchno(rs.getInt("tchno"));
				board.setStudno(rs.getInt("studno"));
				board.setContent(rs.getString("content"));
				board.setSubject(rs.getString("subject"));
				board.setWrite_date(rs.getDate("write_date"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return board;
	}
	
	public int replyupdatesuc(String content, int boardno) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql="UPDATE board SET content=? WHERE boardno=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, boardno);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return result;
	}
	
	public int delete(int boardno) throws SQLException { 
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0; //비회원임 
		
		String sql="DELETE FROM board WHERE boardno=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardno);
			System.out.println("pstmt=>"+pstmt);
			result=pstmt.executeUpdate();
			System.out.println("result=>"+result);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return result;
	}
}
