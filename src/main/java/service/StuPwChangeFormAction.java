package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Student;
import dao.StudentDao;

public class StuPwChangeFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("StuPwChangeFormAction start...");
		
		HttpSession session = request.getSession();
		String stu_id = (String) session.getAttribute("stu_id");
		System.out.println("StuPwChangeFormAction session stu_id -> " + stu_id);
		
		if(stu_id != null) {
			// 세션을 불러서 학생 아이디 있는 경우 비밀번호 변경 페이지로 이동
			return "stuPwChangeForm.jsp";
		} else {
			// 세션에 아이디가 없으면 로그인 페이지로 이동
			return "stuLoginForm.do";
		}
		
	}

}
