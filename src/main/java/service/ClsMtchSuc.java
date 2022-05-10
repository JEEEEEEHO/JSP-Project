package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Classe;
import dao.ClsMtchDao;
import dao.Loc;

public class ClsMtchSuc implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	      String stu_id = (String) session.getAttribute("stu_id");
	      System.out.println("StuMpyListAction session stu_id -> " + stu_id);
	      
	      if(stu_id != null) {
	  		
	  		try {
	  			int classno = 0;
	  			if (request.getParameter("classno") != null) {
	  				classno=Integer.parseInt(request.getParameter("classno"));
	  			}
	  			System.out.println("classno=>"+classno);
	  			// 처음 부분에서 classno 넘어온 것 이용
	  			
	  			int studno=(int) session.getAttribute("studno");
	  	        System.out.println("studno -> " + studno);

	  		
	  			ClsMtchDao cd=ClsMtchDao.getInstance();
	  			
	  			int result = cd.mtchCheck(studno,classno);
	  			System.out.println("result=>"+result);
	  				// 학생번호와 상태 변경 
	  				
	  				request.setAttribute("classno", classno);
	  				request.setAttribute("result", result);
	  				
	  			} catch (SQLException e) {
	  				System.out.println(e.getMessage());
	  				// jsp 단에서 조건 걸어줬음 보여지는 건 수강신청이 가능한 것들 뿐 그냥 되나 안되나 정도만 확인하면됨 
	  			} return "clsMtchSuc.jsp";
	  			
	      		} else {
		         // 세션에 아이디가 없으면 로그인 페이지로 이동
		         return "stuLoginForm.do";
	      		}
			}
		}
