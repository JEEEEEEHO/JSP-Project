package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.StuReplyBoardDao;

public class StuBoaReplyUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
	      String stu_id = (String) session.getAttribute("stu_id");
	      System.out.println("StuMpyListAction session stu_id -> " + stu_id);
		
		try {
			// 댓글의 전체적인 정보와 변경된 내용 
			String pageNum=request.getParameter("pageNum");
			System.out.println("pageNum->"+pageNum);
			
			int boardno=Integer.parseInt(request.getParameter("boardno"));
			System.out.println("boardno=>"+request.getParameter("boardno"));
			
			int ref=Integer.parseInt(request.getParameter("ref"));
			System.out.println("ref=>"+request.getParameter("ref"));
			
			StuReplyBoardDao bd=StuReplyBoardDao.getInstance();
			
			String content=request.getParameter("content");

			
			int result=bd.replyupdatesuc(content, boardno); // 댓글의 정보인것 
			System.out.println("result=>"+result);

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("boardno", boardno);
			request.setAttribute("ref", ref);
			request.setAttribute("result", result);

		} catch (Exception e) {
			System.out.println("Sql=>"+e.getMessage());
		}
		return "stuBoaReplyUpdatePro.jsp";
	}
}
