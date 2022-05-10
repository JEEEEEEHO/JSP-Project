package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.StuReplyBoardDao;

public class ComBoaListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	      String stu_id = (String) session.getAttribute("stu_id");
	      System.out.println("StuMpyListAction session stu_id -> " + stu_id);
		
		StuReplyBoardDao bd=StuReplyBoardDao.getInstance();
		try {
			String pageNum=request.getParameter("pageNum");
			if(pageNum==null||pageNum.equals("")) {
				pageNum="1";
			}
			System.out.println("pageNum->"+pageNum);
			int currentPage=Integer.parseInt(pageNum);
			int pageSize=10, blockSize=10;
			int startRow=(currentPage-1)*pageSize+1;
			System.out.println("startRow->"+startRow);
			int endRow=startRow+pageSize-1;
			
			//int totCnt=bd.getTotalCnt(startRow,endRow);
			int totCnt=bd.getTotalCnt();
			int startNum=totCnt-startRow+1;
			System.out.println("startNum->"+startNum);

			
			List<Board> list=bd.boardlist(startRow,endRow );
			System.out.println("list 작동중->"+list);
			
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize+1; // 나누고 int 형을 씌우면서 . 뒤에 값 버림 
			int endPage=startPage+blockSize-1;
			if(endPage>pageCnt)endPage=pageCnt;
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("startRow", startRow);
			request.setAttribute("endRow", endRow);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "comBoaList.jsp";
	}
}
