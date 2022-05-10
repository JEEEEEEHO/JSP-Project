package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Student;
import dao.StudentDao;

public class StuUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("StuUpdateFormAction start...");
		
		HttpSession session = request.getSession();
		String stu_id = (String) session.getAttribute("stu_id");
		System.out.println("StuUpdateFormAction session stu_id -> " + stu_id);
		
		if(stu_id != null) {
			// 세션을 불러서 학생 아이디 있는 경우 개인정보 수정 페이지로 이동
			// 아이디, 이름, 전화번호, 생년월일, 성별 데이터 가지고 와서 넘겨줘야 함
			try {
				StudentDao stuDao = StudentDao.getInstance();
				Student student = stuDao.selectUp(stu_id);
				request.setAttribute("student", student);
				System.out.println("StuUpdateFormAction student -> " + student);
				
			} catch (SQLException e) {
				System.out.println("StuUpdateFormAction SQLException -> " + e.getMessage());
			}
			return "stuUpdateForm.jsp";
		}
		else {
			// 세션에 아이디가 없으면 로그인 페이지로 이동
			return "stuLoginForm.do";
		}
	}

}
