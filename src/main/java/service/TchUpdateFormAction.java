package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Teacher;
import dao.TeacherDao;

public class TchUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("TchUpdateFormAction start...");
		
		HttpSession session = request.getSession();
		String tch_id = (String) session.getAttribute("tch_id");
		System.out.println("TchUpdateFormAction session tch_id -> " + tch_id);
		
		if (tch_id != null) {
			// 세션을 불러서 교사 아이디가 있는 경우 개인정보 수정 페이지로 이동
			// 아이디, 이름, 전화번호, 생년월일, 성별, 이력서파일 데이터 가지고 와서 넘겨줘야 함
			try {
				TeacherDao tchDao = TeacherDao.getInstance();
				Teacher teacher = tchDao.selectUp(tch_id);
				request.setAttribute("teacher", teacher);
				System.out.println("TchUpdateFormAction teacher -> " + teacher);
				
				String context = request.getContextPath();
				request.setAttribute("context", context);
				System.out.println("TchUpdateFormAction context -> " + context);
				
				String menu_num = request.getParameter("menu_num");
				request.setAttribute("menu_num", menu_num);
				
				
			} catch (SQLException e) {
				System.out.println("TchUpdateFormAction SQLException -> " + e.getMessage());
			}
			return "tchUpdateForm.jsp";
		} else {
			// 세션에 아이디가 없으면 로그인 페이지로 이동
			return "tchLoginForm.do";
		}
	}

}
