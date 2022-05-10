package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Student;
import dao.StudentDao;

public class StuUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("StuUpdateProAction start...");
		
		HttpSession session = request.getSession();
		String stu_id = (String) session.getAttribute("stu_id");
		System.out.println("StuUpdateProAction stu_id -> " + stu_id);
		
		try {
			request.setCharacterEncoding("utf-8");
			Student student = new Student();
			
			student.setStu_id(request.getParameter("stu_id"));
			student.setName(request.getParameter("name"));
			student.setPhone(request.getParameter("phone"));
			student.setBirth(request.getParameter("birth"));
			student.setGender(Integer.parseInt(request.getParameter("gender")));
			
			StudentDao stuDao = StudentDao.getInstance();
			int result = stuDao.update(student);
			System.out.println("StuUpdateProAction update result -> " + result);
			// 확인
			System.out.println("StuUpdateProAction stu_id -> " + student.getStu_id());
			System.out.println("StuUpdateProAction name -> " + student.getName());
			System.out.println("StuUpdateProAction phone -> " + student.getPhone());
			System.out.println("StuUpdateProAction birth -> " + student.getBirth());
			System.out.println("StuUpdateProAction gender -> " + student.getGender());
			
			// request 객체에 result 담아주기 -> stuUpdatePro.jsp에 값 전달
			request.setAttribute("result", result);
			
		} catch (SQLException e) {
			System.out.println("StuUpdateProAction SQLException -> " + e.getMessage());
		}
		return "stuUpdatePro.jsp";
	}

}
