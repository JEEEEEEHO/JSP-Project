package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Student;
import dao.StudentDao;
import dao.Teacher;
import dao.TeacherDao;

public class TchUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("TchUpdateProAction start...");
		
		HttpSession session = request.getSession();
		String tch_id = (String) session.getAttribute("tch_id");
		System.out.println("TchUpdateProAction tch_id -> " + tch_id);
		
		try {
			request.setCharacterEncoding("utf-8");
			Teacher teacher = new Teacher();
			
			teacher.setTch_id(request.getParameter("tch_id"));
			teacher.setName(request.getParameter("name"));
			teacher.setPhone(request.getParameter("phone"));
			teacher.setBirth(request.getParameter("birth"));
			teacher.setGender(Integer.parseInt(request.getParameter("gender")));
			teacher.setResume(request.getParameter("resume"));
			
			TeacherDao tchDao = TeacherDao.getInstance();
			int result = tchDao.update(teacher);
			System.out.println("TchUpdateProAction update result -> " + result);
			// 확인
			System.out.println("TchUpdateProAction id -> " + teacher.getTch_id());
			System.out.println("TchUpdateProAction name -> " + teacher.getName());
			System.out.println("TchUpdateProAction phone -> " + teacher.getPhone());
			System.out.println("TchUpdateProAction birth -> " + teacher.getBirth());
			System.out.println("TchUpdateProAction gender -> " + teacher.getGender());
			System.out.println("TchUpdateProAction resume -> " + teacher.getResume());
			
			// request 객체에 result 담아주기 -> stuUpdatePro.jsp에 값 전달
			request.setAttribute("result", result);
			
		} catch (SQLException e) {
			System.out.println("StuUpdateProAction SQLException -> " + e.getMessage());
		}
		return "tchUpdatePro.jsp";
	}

}
