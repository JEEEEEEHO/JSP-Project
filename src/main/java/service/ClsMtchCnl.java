package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClsMtchDao;

public class ClsMtchCnl implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
	      String stu_id = (String) session.getAttribute("stu_id");
	      System.out.println("StuMpyListAction session stu_id -> " + stu_id);
		
		int classno=Integer.parseInt(request.getParameter("classno"));
		System.out.println("classno=>"+classno);
		// 처음 부분에서 classno 넘어온 것 이용
		ClsMtchDao cd=ClsMtchDao.getInstance();
		
		try {
			int result = cd.mtchCnlCheck(classno);
			System.out.println("result=>"+result);
			// 학생번호와 상태 변경 (수강취소)
			
			request.setAttribute("classno", classno);
			request.setAttribute("result", result);
			request.setAttribute("stu_id", stu_id);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "clsMtchCnl.jsp";
	}
}
