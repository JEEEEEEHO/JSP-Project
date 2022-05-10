package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDao;

public class TchIdCheckAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String tch_id=request.getParameter("tch_id");
		TeacherDao tchDao= TeacherDao.getInstance();
		
		try {
		int result=tchDao.select(tch_id);
		
		request.setAttribute("tch_id", tch_id);
		request.setAttribute("result", result);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		  return "tchIdCheck.jsp";
		}
	
	}
