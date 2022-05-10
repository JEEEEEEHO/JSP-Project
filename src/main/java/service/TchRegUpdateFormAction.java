package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Classe;
import dao.ClasseDao;
import dao.Loc;
import dao.LocDao;
import dao.LocMaster;
import dao.LocMasterDao;
import dao.Subject;
import dao.SubjectDao;


public class TchRegUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int classno = Integer.parseInt(request.getParameter("classno"));
		String pageNum = request.getParameter("pageNum");
		
		try {
			
			ClasseDao tch = ClasseDao.getInstance();
			LocDao ld = LocDao.getInstance();
			LocMasterDao lmd = LocMasterDao.getInstance();
			SubjectDao sd = SubjectDao.getInstance();
			
			Classe tchclass;
			tchclass = tch.tchClasseSelect(classno);
			// s_locList = 클래스의 지역 리스트
			List<Loc> s_locList = ld.getLocList(classno);
			int s_locList_size = s_locList.size();
			int[] class_bLoc = new int[s_locList_size];
			int i = 0;
			for(Loc loc : s_locList) {
				class_bLoc[i] = loc.getLocno();
				i++;
			}
			
			
			
			
			List<LocMaster> b_loc_list = lmd.select_b();
			List<LocMaster> s_loc_list100 = lmd.select_s(100);
			List<LocMaster> s_loc_list200 = lmd.select_s(200);
			
			
			List<Subject> b_subj_list = sd.select_b();
			List<Subject> s_subj_list400 = sd.select_s(400);
			List<Subject> s_subj_list500 = sd.select_s(500);
			
			int b_tchSubj = (tchclass.getSubjectno() / 100) * 100; 
			// int 로 나누면 401-> 400이 됨, b_tchSubj 는 큰 카테고리 
			
			String context = request.getContextPath();
			String filename = tchclass.getMain_img();
			System.out.println("file_path->"+context+"/"+filename);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("classno", classno);
			request.setAttribute("tchclass", tchclass);
			request.setAttribute("b_subj_list", b_subj_list);
			request.setAttribute("s_subj_list400", s_subj_list400);
			request.setAttribute("s_subj_list500", s_subj_list500);
			request.setAttribute("b_loc_list", b_loc_list);
			request.setAttribute("s_loc_list100", s_loc_list100);
			request.setAttribute("s_loc_list200", s_loc_list200);
			request.setAttribute("s_locList", s_locList);
			request.setAttribute("menu_num", "1");
			request.setAttribute("context", context);
			request.setAttribute("b_tchSubj", b_tchSubj);
			request.setAttribute("s_locList_size", s_locList_size);
			request.setAttribute("class_bLoc", class_bLoc);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return "tchRegUpdateForm.jsp";
	}

}
