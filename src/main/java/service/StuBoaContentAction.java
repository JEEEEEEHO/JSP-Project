package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.ClsMtchDao;
import dao.StuReplyBoardDao;

public class StuBoaContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 원글에 대한 정보 

		HttpSession session = request.getSession();
	      String stu_id = (String) session.getAttribute("stu_id");
	      System.out.println("StuMpyListAction session stu_id -> " + stu_id);
	      
	      if(stu_id != null) {
	  		
	  		try {
	  			String pageNum=request.getParameter("pageNum");
	  			System.out.println("pageNum->"+pageNum);
	  			
	  			int boardno=Integer.parseInt(request.getParameter("boardno"));
	  			System.out.println("boardno->"+boardno);
	  			
	  			int ref=Integer.parseInt(request.getParameter("ref"));
	  			System.out.println("ref->"+ref);

	  			StuReplyBoardDao bd=StuReplyBoardDao.getInstance();
	  			
  				bd.readCount(boardno);
  				Board board=bd.contentselect(ref,0);
  				// 원글 선생님 이름 포함 (1개만 보여주면됨, ref=boardno랑 같기 때문에 이걸로 찾아냄)
  				List<Board> list =bd.replylist(ref);
  				// 댓글 정보 boardno는 sequence 로 계속 변경되기 때문에 ref 로 원글에 대한 댓글을 불러와야함 
	  				
  				request.setAttribute("pageNum", pageNum); // 1
  				request.setAttribute("boardno", boardno); //데이터 값
  				request.setAttribute("ref", ref); //boardno
  				request.setAttribute("board", board); // 원글 1개 상세 
  				request.setAttribute("list", list); // 원글의 댓글 여러개  
	  			
	  			} catch (Exception e) {
	  				System.out.println(e.getMessage());
	  				
	  			} return "stuBoaReplyContent.jsp";
	  			
	      		}else {
		         // 세션에 아이디가 없으면 로그인 페이지로 이동
		         return "stuLoginForm.do";
	      		}
			}
		}

