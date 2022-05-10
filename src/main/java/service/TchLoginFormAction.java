package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TchLoginFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("TchLoginFormAction start...");
	      
	      HttpSession session = request.getSession();
	      String tch_id = (String) session.getAttribute("tch_id");
	      if (tch_id != null) {
	         return "tchlist.do";
	      } else {
	         return "tchLoginForm.jsp";
	      }
		
	}

}
