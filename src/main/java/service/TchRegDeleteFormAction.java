package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Classe;
import dao.ClasseDao;


public class TchRegDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			int classno = Integer.parseInt(request.getParameter("classno"));
			String pageNum = request.getParameter("pageNum");
			
		ClasseDao tch = ClasseDao.getInstance();
		
		Classe tchclass;
		tchclass = tch.tchClasseSelect(classno);
		request.setAttribute("classno", classno);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("tchclass", tchclass);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return "tchRegDeleteForm.jsp";
	}

}
