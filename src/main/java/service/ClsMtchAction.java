package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.Classe;
import dao.ClsMtchDao;
import dao.Loc;
import dao.StuReplyBoardDao;

public class ClsMtchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	      String stu_id = (String) session.getAttribute("stu_id");
	      System.out.println("StuMpyListAction session stu_id -> " + stu_id);
	      
	  		ClsMtchDao cd=ClsMtchDao.getInstance();
	  		
	  		try {
	  			int classno=Integer.parseInt(request.getParameter("classno"));
	  			System.out.println("classno->"+classno);
	  			
	  			Classe classe=cd.clsdetail(classno);
				System.out.println("classe=>"+classe);
				// 수업 내용에 대한 객체 
				
				List<Loc> list=cd.ploc(classno);
				System.out.println("list=>"+list);
				// 수업가능지역 받기 
				
				String resume=cd.tresume(classno);
				System.out.println("resume=>"+resume);
				// 이력서 다운 
				
				request.setAttribute("classno", classno);
				request.setAttribute("classe", classe);
				// 클래스 전체 내용 객체
				request.setAttribute("list", list);
				// 수업가능한 지역 리스트로 받음 
				request.setAttribute("resume", resume);
				request.setAttribute("stu_id", stu_id);
	  			
	  			} catch (Exception e) {
	  				System.out.println(e.getMessage());
	  				
	  			} return "clsMtch.jsp";
	  			
			}
		}
