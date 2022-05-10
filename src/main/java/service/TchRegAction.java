package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClasseDao;
import dao.LocMaster;
import dao.LocMasterDao;
import dao.Subject;
import dao.SubjectDao;

public class TchRegAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("TchRegAction Start...");
		request.setCharacterEncoding("utf-8");
		
		
		
		try {
			
			LocMasterDao lmd = LocMasterDao.getInstance();
			SubjectDao sd = SubjectDao.getInstance();
			
			List<LocMaster> b_loc_list = lmd.select_b();
			List<LocMaster> s_loc_list100 = lmd.select_s(100);
			List<LocMaster> s_loc_list200 = lmd.select_s(200);
			
			List<Subject> b_subj_list = sd.select_b();
			List<Subject> s_subj_list400 = sd.select_s(400);
			List<Subject> s_subj_list500 = sd.select_s(500);
			
			request.setAttribute("b_subj_list", b_subj_list);
			request.setAttribute("s_subj_list400", s_subj_list400);
			request.setAttribute("s_subj_list500", s_subj_list500);
			request.setAttribute("b_loc_list", b_loc_list);
			request.setAttribute("s_loc_list100", s_loc_list100);
			request.setAttribute("s_loc_list200", s_loc_list200);
			request.setAttribute("menu_num", "1");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return "tchReg.jsp";
	}

}
