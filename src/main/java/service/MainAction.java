package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Classe;
import dao.ClasseDao;
import dao.LocMaster;
import dao.LocMasterDao;
import dao.Subject;
import dao.SubjectDao;


public class MainAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("MainAction start...");
		
		// 세션에 id, pw, name, studno 담아주기
		HttpSession session = request.getSession();
		String stu_id = (String) session.getAttribute("stu_id");
		System.out.println("MainAction stu_id -> " + stu_id);
		String tch_id = (String) session.getAttribute("tch_id");
		System.out.println("MainAction tch_id -> " + tch_id);
		
		
		try {
			// 회원 유형이 학생일 경우에만 세션에 id, pw, name, studno 담아주기
			if (stu_id != null && tch_id == null) {
				String password = (String) session.getAttribute("password");
				String stuName = (String) session.getAttribute("stuName");
				int studno = (int) session.getAttribute("studno");
				System.out.println("MainAction password -> " + password);
				System.out.println("MainAction stuName -> " + stuName);
				System.out.println("MainAction studno -> " + studno);
				
				// request 객체에 id, pw, name 담아주기 -> clsList.jsp에 값 전달
				request.setAttribute("stu_id", stu_id);
				request.setAttribute("password", password);
				request.setAttribute("stuName", stuName);
				session.setAttribute("studno", studno);
				
				
				
			} else if (tch_id != null) 
				// 교사센터에서 학생 서비스 페이지로 이동 시 ' 님 | 로그아웃' --> 선생 회원정보 노출 X
				return "clsList.jsp";		
			
			ClasseDao cd = ClasseDao.getInstance();
			request.setCharacterEncoding("utf-8");
			
			
			String grade = request.getParameter("grade");
			if(grade==null || grade.equals("")) {
				grade="0";
			} 
			
			
			String subjectno = "%";
			String locno = "%";
			
			String b_subject = request.getParameter("b_subject");
			String s_subject400 = request.getParameter("s_subject400");
			String s_subject500 = request.getParameter("s_subject500");
			
			String b_loc = request.getParameter("b_loc");
			String s_loc100 = request.getParameter("s_loc100");
			String s_loc200 = request.getParameter("s_loc200");
			
			// 아무것도 선택이 안됬을 때 
				// 과목
			if(b_subject==null || b_subject.equals("")) {
				b_subject = "%";
			}
			if(s_subject400==null || s_subject400.equals("")) {
				s_subject400 = "4%";
			}
			if(s_subject500==null || s_subject500.equals("")) {
				s_subject500 = "5%";
			}
				// 지역
			if(b_loc==null || b_loc.equals("")) {
				b_loc = "%";
			}
			if(s_loc100==null || s_loc100.equals("")) {
				s_loc100 = "1%";
			}
			if(s_loc200==null || s_loc200.equals("")) {
				s_loc200 = "2%";
			}
			
			
			// 특정 값이 선택이 되었을 때 
				// 과목
			if(b_subject.equals("%") || b_subject.equals("%100") || b_subject.equals("%200") || b_subject.equals("%300")) {
				subjectno = b_subject;
			} else if(b_subject.equals("%400")){
				subjectno = s_subject400;
			} else if(b_subject.equals("%500")){
				subjectno = s_subject500;
			}
				// 지역
			if(b_loc.equals("%")) {
				locno = b_loc;
			} else if(b_loc.equals("%100")) {
				locno = s_loc100;
			} else if(b_loc.equals("%200")){
				locno = s_loc200;
			}
			
			// 그떄의 값이 들어가서 화면에 보여줄 총 클래스의 개수를 셈 
			int totCnt = cd.getTotalCnt(grade, subjectno, locno);
			// 그걸 객체에 담음 
			List<Classe> list = cd.classList(grade, subjectno, locno);
			
			// 과목의 리스트 
			SubjectDao sd = SubjectDao.getInstance();
			List<Subject> b_subj_list = sd.select_b();
			List<Subject> s_subj_list400 = sd.select_s(400);
			List<Subject> s_subj_list500 = sd.select_s(500);
			// 지역의 리스트 
			LocMasterDao ld = LocMasterDao.getInstance();
			List<LocMaster> b_loc_list = ld.select_b();
			List<LocMaster> s_loc_list100 = ld.select_s(100);
			List<LocMaster> s_loc_list200 = ld.select_s(200);
			

			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("list", list);
			request.setAttribute("b_subj_list", b_subj_list);
			request.setAttribute("s_subj_list400", s_subj_list400);
			request.setAttribute("s_subj_list500", s_subj_list500);
			request.setAttribute("b_loc_list", b_loc_list);
			request.setAttribute("s_loc_list100", s_loc_list100);
			request.setAttribute("s_loc_list200", s_loc_list200);
			request.setAttribute("grade", grade);
//			request.setAttribute("b_subject", b_subject);
//			request.setAttribute("s_subject400", s_subject400);
//			request.setAttribute("s_subject500", s_subject500);
//			request.setAttribute("b_loc", b_loc);
//			request.setAttribute("s_loc100", s_loc100);
//			request.setAttribute("s_loc200", s_loc200);
			
			
			System.out.println("-----------------------------------------------");   
			System.out.println("totCnt-->" + totCnt);  
			System.out.println("grade-->"+grade);
			System.out.println("b_subject-->"+b_subject);
			System.out.println("s_subject400-->"+s_subject400);
			System.out.println("s_subject500-->"+s_subject500);
			System.out.println("b_loc-->"+b_loc);
			System.out.println("s_loc100-->"+s_loc100);
			System.out.println("s_loc200-->"+s_loc200);
			System.out.println("subjectno-->"+subjectno);
			System.out.println("locno-->"+locno);
			System.out.println("list.length-->"+list.size());
			System.out.println();
			System.out.println();
			
			
			
			
//			String pageNum = request.getParameter("pageNum");
//			if(pageNum==null || pageNum.equals("")) {pageNum = "1";}
//			int currentPage = Integer.parseInt(pageNum);	//1
//			int pageSize = 10, blockSize = 10;
//			
//			int startRow = (currentPage - 1) * pageSize + 1;	//1
//			int endRow = startRow + pageSize - 1;				//10
//			
//			int startNum = totCnt - startRow + 1;				//40
			
			
//			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);		//5
//			int startPage = (int)(currentPage - 1)/blockSize*blockSize + 1;	//1	
//			int endPage = startPage + blockSize - 1;		// 10
//			
//			
//			if(endPage > pageCnt) endPage = pageCnt
			
			
			
			
//			request.setAttribute("pageNum", pageNum);
//			request.setAttribute("currentPage", currentPage);
//			request.setAttribute("startNum", startNum);
//			request.setAttribute("blockSize", blockSize);
//			request.setAttribute("pageCnt", pageCnt);
//			request.setAttribute("startPage", startPage);
//			request.setAttribute("endPage", endPage);
			
//			System.out.println("startNum-->" + startNum); 
//			System.out.println("currentPage-->" + currentPage);  
//			System.out.println("blockSize-->" + blockSize);  
//			System.out.println("pageSize-->" + pageSize); 
//			System.out.println("pageCnt-->" + pageCnt);  
//			System.out.println("startPage-->" + startPage);  
//			System.out.println("endPage-->" + endPage);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		return "clsList.jsp";
	}

}
