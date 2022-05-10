package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.StuReplyBoardDao;

public class StuBoaReplyWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	      String stu_id = (String) session.getAttribute("stu_id");
	      System.out.println("StuMpyListAction session stu_id -> " + stu_id);
		
		try {
			
			request.setCharacterEncoding("utf-8");
			String pageNum=request.getParameter("pageNum");
			System.out.println("pageNum->"+pageNum);
			
			Board board=new Board();
			
			if(request.getParameter("boardno")!=null) { 
				System.out.println(request.getParameter("boardno"));
				//  이부분만 해당이 됨 댓글이기 때문에 
				board.setBoardno(Integer.parseInt(request.getParameter("boardno")));
				//원글의 boardno
				System.out.println(request.getParameter("boardno"));

				board.setStudno(Integer.parseInt(request.getParameter("studno")));
				System.out.println(request.getParameter("boardno"));

				board.setTchno(Integer.parseInt(request.getParameter("tchno")));
				System.out.println(request.getParameter("tchno"));

				board.setSubject(request.getParameter("subject"));
				System.out.println(request.getParameter("subject"));

				board.setWrite_date(java.sql.Date.valueOf(request.getParameter("write_date")));
				System.out.println(request.getParameter("write_date"));

				board.setReadcount(Integer.parseInt(request.getParameter("readcount")));
				System.out.println(request.getParameter("readcount"));

				board.setRef(Integer.parseInt(request.getParameter("ref"))); 
				System.out.println(request.getParameter("ref"));
				//원글의 boardno 
				
				board.setRe_step(Integer.parseInt(request.getParameter("re_step"))); 
				System.out.println(request.getParameter("re_step"));
				//0 댓글의 댓글은 기능이 달라짐
				//1, 2, 3... 댓글의 re_step이 들어가게 됨 
				
				board.setRe_level(Integer.parseInt(request.getParameter("re_level"))); 
				System.out.println(request.getParameter("re_level"));
				//0
				
				board.setContent(request.getParameter("content"));
				System.out.println(request.getParameter("content"));

				
				StuReplyBoardDao bd=StuReplyBoardDao.getInstance();
				
				// 원글의 boardno ref 명령을 완수하고 돌아올때, 오류날때 필요하므로 
				int boardno=board.getBoardno();
				System.out.println("boardno->"+boardno);

				int ref=board.getRef();
				System.out.println("ref=>"+ref);
				
				int result = bd.insert(board);
				// 원글에 대한 댓글인 경우에
				System.out.println("result2=>"+result);
				// 콘텐츠를 변경해 새롭게 입력한 값 
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("boardno", boardno);
				request.setAttribute("ref", ref);
				request.setAttribute("result", result);
				request.setAttribute("board", board);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "stuBoaReplyWritePro.jsp";
	}

}
