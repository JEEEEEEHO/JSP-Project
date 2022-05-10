package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Student;
import dao.StudentDao;

public class StuIdCheckAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
		String stu_id=request.getParameter("stu_id");
		StudentDao stuDao = StudentDao.getInstance();
		System.out.println("stu_id->"+stu_id);
		
		int result= stuDao.select(stu_id);
		
		request.setAttribute("stu_id", stu_id);
		request.setAttribute("result", result);
		
		} catch (Exception e) {
		System.out.println(e.getMessage());	
		}
		return "stuIdCheck.jsp";
	}

}
