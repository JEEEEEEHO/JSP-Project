package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;

public class StuDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("StuDeleteProAction start...");
		
		HttpSession session = request.getSession();
		String stu_id = (String) session.getAttribute("stu_id");
		System.out.println("StuDeleteProAction stu_id -> " + stu_id);
		
		try {
			StudentDao stuDao = StudentDao.getInstance();
			int result = stuDao.delete(stu_id);
			System.out.println("StuDeleteProAction result -> " + result);
			if (result == 1) {
				session.invalidate();
			}
			request.setAttribute("result", result);
		} catch (SQLException e) {
			System.out.println("StuDeleteProAction SQLException -> " + e.getMessage());
		}
		return "stuDeletePro.jsp";
	}

}
