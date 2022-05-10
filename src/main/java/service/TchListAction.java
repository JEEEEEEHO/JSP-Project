package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Classe;
import dao.ClasseDao;

public class TchListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("TchListAction start...");
		
		ClasseDao tcd = ClasseDao.getInstance();
		
		HttpSession session = request.getSession();
		String tch_id = (String) session.getAttribute("tch_id");
		System.out.println("TchListAction session tch_id -> " + tch_id);
		
		if (tch_id != null) {
			
			try {
				// 세션을 불러서 교사 아이디가 있는 경우 클래스 관리 페이지로 이동
				String password = (String) session.getAttribute("password");
				int tchno = (int) session.getAttribute("tchno");
				
				int totCnt;
				totCnt = tcd.getTchClasseCount(tchno);
				
				String pageNum = request.getParameter("pageNum");	
				if (pageNum==null || pageNum.equals("")) {	pageNum = "1";	}
				int currentPage = Integer.parseInt(pageNum);	// 1        2
				int pageSize  = 10, blockSize = 10;
				int startRow = (currentPage - 1) * pageSize + 1;  // 1      11
				int endRow   = startRow + pageSize - 1;           // 10     20
				
//					int startNum = totCnt - startRow + 1; // 38 - 11 + 1 28
				
				// TchClass 조회 위에 TchClassDao 보이니? 꼭봐라
				List<Classe> list = tcd.tchClassList(startRow,endRow,tchno); 
				
				int pageCnt = (int)Math.ceil((double)totCnt/pageSize);  // 4
				//                      2 -1  /  10   * 10  + 1         // 2
				int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;  // 1   2
				int endPage = startPage + blockSize -1;	                       // 10  11
				// 공갈 Page 방지   11   >  4
				if (endPage > pageCnt) endPage = pageCnt;	                   // 4
			
				
				request.setAttribute("totCnt", totCnt);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("list", list);
				request.setAttribute("blockSize", blockSize);
				request.setAttribute("pageCnt", pageCnt);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);	
				request.setAttribute("totCnt", totCnt);
				request.setAttribute("menu_num", "1");
				
				System.out.println("list length->"+list.size());
				
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			return "tchList.jsp";
		} else {
			// 세션에 아이디가 없으면 로그인 페이지로 이동
			return "tchLoginForm.do";
		}
			
		
		
	}

}
