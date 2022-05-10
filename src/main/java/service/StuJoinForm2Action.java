package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Student;
import dao.StudentDao;

public class StuJoinForm2Action implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
		request.setCharacterEncoding("utf-8");
		
		String stu_id =request.getParameter("stu_id");
		String password= request.getParameter("password");
		
		System.out.println("StuJoinForm2Action stu_id"+stu_id);
		System.out.println("StuJoinForm2Action password"+password);
		
		request.setAttribute("stu_id", stu_id);
		request.setAttribute("password", password);
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "stuJoinForm2.jsp";
	}

}
