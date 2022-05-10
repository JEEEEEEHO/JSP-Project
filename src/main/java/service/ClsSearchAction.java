package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Classe;
import dao.ClassSearchDao;

public class ClsSearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		System.out.println("ClsSearchAction Service Start...");
		ClassSearchDao csd = ClassSearchDao.getInstance();
		String sword = (String)request.getAttribute("sword");
		try {
			int totCnt = csd.getTotalCnt(sword);	//40
			
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null || pageNum.equals("")) {pageNum = "1";}
			int currentPage = Integer.parseInt(pageNum);	//1
			int pageSize = 9, blockSize = 10;
			
			int startRow = (currentPage - 1) * pageSize + 1;	//1
			int endRow = startRow + pageSize - 1;				//9
			
			int startNum = totCnt - startRow + 1;				//40
			
			List<Classe> list = csd.classList(startRow, endRow, sword); 	
			
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);		//5
			int startPage = (int)(currentPage - 1)/blockSize*blockSize + 1;	//1	
			int endPage = startPage + blockSize - 1;		// 10
			
			if(endPage > pageCnt) endPage = pageCnt;
			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			System.out.println("-----------------------------------------------");   
			System.out.println("startNum-->" + startNum);  // /ch16/list.do
			System.out.println("totCnt-->" + totCnt);  // /ch16/list.do
			System.out.println("currentPage-->" + currentPage);  // /ch16/list.do
			System.out.println("blockSize-->" + blockSize);  // /ch16/list.do
			System.out.println("pageSize-->" + pageSize);  // /ch16/list.do
			System.out.println("pageCnt-->" + pageCnt);  // /ch16/list.do
			System.out.println("startPage-->" + startPage);  // /ch16/list.do
			System.out.println("endPage-->" + endPage);  // /ch16/list.do
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		
		
		
		return "clsList.jsp";
	}

}
