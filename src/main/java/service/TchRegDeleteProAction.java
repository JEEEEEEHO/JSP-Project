package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClasseDao;


public class TchRegDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("DeleteProAction Start...");
		
		try {
			
			int classno = Integer.parseInt(request.getParameter("classno"));
			String pageNum=request.getParameter("pageNum");
		
			ClasseDao tchclass = ClasseDao.getInstance();
			
			
			/* TchClass tchclass = new TchClass();*/
			
			int result = tchclass.tchClassedelete(classno);
			
			
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("classno", classno);
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return "tchRegDeletePro.jsp";
	}

}
