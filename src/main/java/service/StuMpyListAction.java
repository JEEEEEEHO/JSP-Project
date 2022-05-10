package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.Classe;
import dao.ClsMtchDao;

public class StuMpyListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	      String stu_id = (String) session.getAttribute("stu_id");
	      System.out.println("StuMpyListAction session stu_id -> " + stu_id);
	      
	      if(stu_id != null) {
	         

	  		ClsMtchDao cd=ClsMtchDao.getInstance();
	  		
	  		try {
	  			String pageNum=request.getParameter("pageNum");
	  			if(pageNum==null||pageNum.equals("")) {
	  				pageNum="1";
	  			}
	  			System.out.println("pageNum=>"+pageNum);
	  			
	  			int studno=(int) session.getAttribute("studno");
	  			System.out.println("studno=>"+studno);
	  			
	  			int result=cd.listcheck(studno);
	  			// 회원에서도 수강내역 존재/ 미존재를 구별해내는 메소드 
	  			System.out.println("result=>"+result);

	  			List<Classe> list=cd.listshow(studno);
	  			System.out.println("list=>"+list);
	  			// 클래스 dto 사용 예정이므로 studno를 이용하였음 
	  			
	  			request.setAttribute("pageNum", pageNum);
	  			request.setAttribute("result", result);
	  			request.setAttribute("list", list);
	  			
	  			
	  			} catch (Exception e) {
	  				System.out.println(e.getMessage());
	  				
	  			} 
	  		
	  		// 세션을 불러서 학생 아이디 있는 경우 마이페이지로 이동
	  		//return "stuMpyList.jsp";
	  		return "stuMpyList.jsp";
	  		} else {
	         // 세션에 아이디가 없으면 로그인 페이지로 이동
	         return "stuLoginForm.do";
	      }
		}
	}


	
