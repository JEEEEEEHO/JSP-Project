package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.StuReplyBoardDao;

public class StuBoaReplyDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	      String stu_id = (String) session.getAttribute("stu_id");
	      System.out.println("StuMpyListAction session stu_id -> " + stu_id);
		
		
		try {
			//댓글의 정보들임 
			
			String pageNum=request.getParameter("pageNum");
			System.out.println("pageNum->"+pageNum);
			
			int boardno=Integer.parseInt(request.getParameter("boardno"));
			System.out.println("boardno->"+boardno);
			
			int ref=Integer.parseInt(request.getParameter("ref"));
			System.out.println("ref->"+ref);
			
			StuReplyBoardDao bd=StuReplyBoardDao.getInstance();
			
			int result=bd.delete(boardno); // 회원 체크 
			System.out.println("result=>"+result);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("boardno", boardno);
			request.setAttribute("ref", ref);
			request.setAttribute("result", result); // 글쓴이 체크
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "stuBoaReplyDeletePro.jsp";
	}
}


