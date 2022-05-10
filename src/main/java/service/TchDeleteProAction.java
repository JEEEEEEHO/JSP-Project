
package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import dao.TeacherDao;

public class TchDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TchDeleteProAction start...");
		
		HttpSession session = request.getSession();
		String tch_id = (String) session.getAttribute("tch_id");
		System.out.println("TchDeleteProAction stu_id -> " + tch_id);
		
		try {
			TeacherDao tchDao = TeacherDao.getInstance();
			int result = tchDao.delete(tch_id);
			System.out.println("TchDeleteProAction result -> " + result);
			if (result == 1) {
				session.invalidate();
			}
			request.setAttribute("result", result);
		} catch (SQLException e) {
			System.out.println("TchDeleteProAction SQLException -> " + e.getMessage());
		}
		return "tchDeletePro.jsp";
	}

}
