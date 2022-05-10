package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Student;
import dao.StudentDao;

public class StuLoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("StuLoginProAction start...");
		
		String stu_id = request.getParameter("stu_id");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		try {
			StudentDao stuDao = StudentDao.getInstance();
			int result = stuDao.check(stu_id, password);
			System.out.println("StuLoginProAction check result -> " + result);
			
			Student student = stuDao.userInfo(stu_id);
			System.out.println("StuLoginProAction name -> " + student.getName());
			System.out.println("StuLoginProAction studno -> " + student.getStudno());
			
			// 로그인 성공 시에만 세션에 id, pw, name, studno, stu_status 담아주기
			if (result == 1) {
				session.setAttribute("stu_id", stu_id);
				session.setAttribute("password", password);
				session.setAttribute("stuName", student.getName());
				session.setAttribute("studno", student.getStudno());
			}
			// request 객체에 result 담아주기 -> stuLoginPro.jsp에 값 전달
			request.setAttribute("result", result);
			
		} catch (SQLException e) {
			System.out.println("StuLoginProAction Exception -> " + e.getMessage());
		}
		return "stuLoginPro.jsp";
	}

}
