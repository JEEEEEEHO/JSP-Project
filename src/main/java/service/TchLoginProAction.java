package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Teacher;
import dao.TeacherDao;

public class TchLoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("TchLoginProAction start...");
		
		String tch_id = request.getParameter("tch_id");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		try {
			TeacherDao tchDao = TeacherDao.getInstance();
			int result = tchDao.check(tch_id, password);
			System.out.println("TchLoginProAction check result -> " + result);
			
			Teacher teacher = tchDao.userInfo(tch_id);
			System.out.println("TchLoginProAction tchno -> " + teacher.getTchno());
			
			// 로그인 성공 시에만 세션에 id, pw, tchno 담아주기
			if (result == 1) {
				session.setAttribute("tch_id", tch_id);
				session.setAttribute("password", password);
				session.setAttribute("tchno", teacher.getTchno());
				session.setAttribute("tchName", teacher.getName());
			}
			// request 객체에 result 담아주기 -> tchLoginPro.jsp에 값 전달
			request.setAttribute("result", result);
			
		} catch (SQLException e) {
			System.out.println("TchLoginProAction SQLException -> " + e.getMessage());
		}
		return "tchLoginPro.jsp";
	}

}
