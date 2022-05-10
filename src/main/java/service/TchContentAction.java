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


public class TchContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("TchContentAction Start...");
		
		int classno = Integer.parseInt(request.getParameter("classno"));
		String pageNum = request.getParameter("pageNum");
		System.out.println("classno->"+classno);
//		System.out.println("pageNum->"+pageNum);
		
		try {
			
			ClasseDao tch = ClasseDao.getInstance();
			LocDao ld = LocDao.getInstance();
			
			Classe tchclass;
			tchclass = tch.tchClasseSelect(classno);
			System.out.println("classSelect 완료");
			List<Loc> s_locList = ld.getLocList(classno);
			System.out.println("getLocList 완료");
			
			
			String context = request.getContextPath();
			String filename = tchclass.getMain_img();
			System.out.println("file_path->"+context+"/"+filename);
			
			request.setAttribute("context", context);
			request.setAttribute("classno", classno);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("tchclass", tchclass);
			request.setAttribute("s_locList", s_locList);
			request.setAttribute("menu_num", "1");
			
			System.out.println("s_locList size->"+s_locList.size());
			System.out.println("------------------------");
			for(Loc loc : s_locList) {
				System.out.println(loc.getLoc_name());
				System.out.println(loc.getUpper_locname());
			}
			System.out.println("------------------------");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return "tchContent.jsp";
		
	}

}
