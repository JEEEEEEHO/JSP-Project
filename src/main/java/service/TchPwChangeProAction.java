package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TeacherDao;

public class TchPwChangeProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		String tch_id=(String)session.getAttribute("tch_id");
		String password=(String)session.getAttribute("password"); 
		
		System.out.println("TchPwChangeProAction session tch_id->" +tch_id);
		System.out.println("TchPwChangeProAction session password->" +password);
		
		String oldPw = request.getParameter("oldPw");
		String newPw = request.getParameter("newPw");
		String newPwCheck =request.getParameter("newPwCheck");
		
		System.out.println("TchPwChangeProAction oldPw->" +oldPw);
		System.out.println("TchPwChangeProAction newPw->" +newPw);
		System.out.println("TchPwChangeProAction newCheckPw->" +newPwCheck);
		
		try {
			
		TeacherDao tchDao= TeacherDao.getInstance();
		int result =tchDao.check(tch_id,oldPw);
		if(result ==1) {
			int updatePwResult = tchDao.updatePw(tch_id,newPw);
		}
		System.out.println("TchPwChangeProAction check result->" +result);
		request.setAttribute("result", result);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "tchPwChangePro.jsp";
	}

}
