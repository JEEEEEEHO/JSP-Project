package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class StuDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("StuDeleteFormAction start...");
		
		HttpSession session = request.getSession();
		String stu_id = (String) session.getAttribute("stu_id");
		System.out.println("StuDeleteFormAction session stu_id -> " + stu_id);
		
		if(stu_id != null) {
			// 세션을 불러서 학생 아이디 있는 경우 회원탈퇴 페이지로 이동
			return "stuDeleteForm.jsp";
		} else {
			// 세션에 아이디가 없으면 로그인 페이지로 이동
			return "stuLoginForm.do";
		}
		
	}

}
