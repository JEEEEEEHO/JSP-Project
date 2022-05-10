package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TchDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("TchDeleteFormAction start...");
		
		HttpSession session = request.getSession();
		String tch_id = (String)session.getAttribute("tch_id");
		System.out.println("TchDeleteFormAction session tch_id -> " + tch_id);
		
		if(tch_id !=null) {
			// 세션을 불러서 교사 아이디 있는 경우 회원탈퇴 페이지로 이동
			request.setAttribute("menu_num", "5");
			return "tchDeleteForm.jsp";
		}else {
			// 세션에 아이디가 없으면 로그인 페이지로 이동
			return "TchLoginForm.do";
		}
		
	}

}
