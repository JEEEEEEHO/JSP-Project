package service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Teacher;
import dao.TeacherDao;

public class TchJoinPro2Action implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("TchjoinPro2Action Start...");
		
		try {
		request.setCharacterEncoding("utf-8");
		String filename="";
		int maxSize = 5 * 1024 * 1024;
		String fileSave = "/fileSave/profile"; // /js/pdfjs/web 아래에서 저장 
		String realPath = request.getSession().getServletContext().getRealPath(fileSave);
		System.out.println("realPath->" +realPath);
		
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		Enumeration en = multi.getFileNames();
		
		while(en.hasMoreElements()) {
			String filename1 =(String)en.nextElement(); 
			filename = multi.getFilesystemName(filename1);
			String original = multi.getOriginalFileName(filename1);
			String type= multi.getContentType(filename1);
			
			File file =multi.getFile(filename1);
			
			System.out.println("realPath->" +realPath);
			System.out.println("파라미터 이름->" +filename1);
			System.out.println("실제 파일 이름->" +original);
			System.out.println("저장된 파일 이름->" +filename);
			System.out.println("파일 타입->" +type);
			
			if(file !=null) {
				System.out.println("크기->" +file.length());
			}
		}
		
		Teacher teacher = new Teacher();
		
		teacher.setTch_id(multi.getParameter("tch_id")); //멀티로 값가져오기
		teacher.setPassword(multi.getParameter("password"));
		teacher.setResume(filename);
		teacher.setGender(Integer.parseInt(multi.getParameter("gender")));
		teacher.setPhone(multi.getParameter("phone"));
		teacher.setBirth(multi.getParameter("birth"));
		teacher.setName(multi.getParameter("name"));
		
		TeacherDao tchDao = TeacherDao.getInstance();
		int result = tchDao.insert(teacher);
		
		request.setAttribute("tch_id", teacher.getTch_id());
		request.setAttribute("password", teacher.getPassword());
		request.setAttribute("result", result);
		request.setAttribute("filename",filename); // fileSave 없애기
		
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "tchJoinPro2.jsp";
	}

	}

