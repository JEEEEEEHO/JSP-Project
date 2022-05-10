package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.Student;
import dao.StudentDao;

public class StuPwChangeProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("StuUpdateProAction start..");
		
		// 세션을 통해 아이디, 비밀번호 가져오기
		HttpSession session = request.getSession();
		String password=(String)session.getAttribute("password");
		String stu_id=(String)session.getAttribute("stu_id");
		
		System.out.println("StuUpdateProAction session stu_id-> " +stu_id);
		System.out.println("StuUpdateProAction session password-> " +password);
		
		// form에서 입력한 파라미터 가져오기
		String oldPw = request.getParameter("oldPw"); // 내가 맞는지 확인하기 위한 비밀번호 입력
		String newPw = request.getParameter("newPw"); // 변경할 비밀번호
		String newPwCheck = request.getParameter("newPwCheck"); // 변경할 비밀번호 확인용
		System.out.println("oldPw -> " + oldPw );
		System.out.println("newPw -> " + newPw );
		System.out.println("newPwCheck -> " + newPwCheck );
		
		try {
			StudentDao stuDao=StudentDao.getInstance();
			int result = stuDao.check(stu_id, oldPw); // result == 1일때 비밀번호 변경 가능
			if (result == 1) {
				int updatePwResult = stuDao.updatePw(stu_id, newPw);
			}
			System.out.println("StuPwChangeProAction check result-> " + result);
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
		return "stuPwChangePro.jsp";
	}

}
