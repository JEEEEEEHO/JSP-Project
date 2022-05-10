package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TchJoinForm2Action implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			String tch_id=request.getParameter("tch_id");
			String password=request.getParameter("password");
			
			request.setAttribute("tch_id", tch_id);
			request.setAttribute("password", password);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		
		return "tchJoinForm2.jsp";
	}

}
