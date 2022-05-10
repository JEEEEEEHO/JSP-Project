package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Student;
import dao.StudentDao;

public class StuJoinPro2Action implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		try {
			request.setCharacterEncoding("utf-8");
			
			Student student=new Student();
			
			student.setStu_id(request.getParameter("stu_id"));
			student.setPassword(request.getParameter("password"));
			student.setName(request.getParameter("name"));
			student.setGender(Integer.parseInt(request.getParameter("gender")));
			student.setPhone(request.getParameter("phone"));
			student.setBirth(request.getParameter("birth"));
			
			System.out.println("Stu_id->->" +student.getStu_id());
			
		    StudentDao stuDao= StudentDao.getInstance(); 
		    int result = stuDao.insert(student);
		    
			request.setAttribute("id", student.getStu_id());
			request.setAttribute("password", student.getPassword());
			request.setAttribute("result", result);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return "stuJoinPro2.jsp";
	}

}
