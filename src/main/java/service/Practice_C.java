package service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Classe;
import dao.ClasseDao;
import dao.LocDao;

public class Practice_C implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//1. 등록
		ClasseDao cd=ClasseDao.getInstance();
		LocDao ld=LocDao.getInstance();
		
		String filename="";
		String uploadFilename="";
		
		int maxSize=5*1024*1024;
		String fileSave="/fileSave/main_imgs";
		// 이 경로에 파일을 저장 
		
		String realPath=request.getSession().getServletContext().getRealPath(fileSave);
		MultipartRequest multi=new MultipartRequest(request, realPath, maxSize, "utf-8",new DefaultFileRenamePolicy());
		Enumeration en=multi.getFileNames();
		
		while(en.hasMoreElements()) {
			String filename1=(String)en.nextElement();
			filename=multi.getFilesystemName(filename1);
			String original=multi.getOriginalFileName(filename1);
			String type=multi.getContentType(filename1);
			File file=multi.getFile(filename1);
		}
		
		// 등록이기 때문에 객체에 담아줘야함 
		// dto 2개 이용 1. 클래스(클래스정보+과목) 2. 지역 (지역정보)
		Classe tchclass=new Classe();
		tchclass.setMain_img("fileSave\\main_imgs\\"+filename);
		tchclass.setClass_name(multi.getParameter("class_name")); //enctype="multipart/form-data" 
		
		//subject는 조건이 여러개였으므로 여러값이 들어왔음. 조건으로 가려내야함
		int b_subject=Integer.parseInt(multi.getParameter("b_subject")); //대분류 100,200...
		int s_subject400=Integer.parseInt(multi.getParameter("s_subject400")); //사탐 과목이라면 401...
		int s_subject500=Integer.parseInt(multi.getParameter("s_subject500")); //사탐 과목이라면 401...
		int subjectno=0;
		
		//조건
		if(b_subject==100 || b_subject==200 || b_subject==300) {
			subjectno=b_subject;  // 국영수
		} else if(b_subject==400) {
			subjectno=s_subject400; // 사탐인 경우 
		} else {
			subjectno=s_subject500; // 과탐인 경우 
		}
		tchclass.setSubjectno(subjectno);
		int tchno=1;
		tchclass.setTchno(tchno);
		
		// 모두 다 담은 클래스의 객체를 dao 와 연결 
		try {
			int result=cd.insert(tchclass);
			
			// 지역을 넣어줌 , 여긴 다른 dao와 dto 를 이용해야함 
			if(result>0) {
				int classno=cd.getClassno(tchno);
				// 선생님이 등록한 class 중 가장 큰 classno 찾기 = 신규 클래스 등록 (tchno를 이용해서 선생님 classno의 최대값을 찾는 쿼리를 이용) 
				
				String b_loc=multi.getParameter("b_loc");
				String s_loc100=multi.getParameter("s_loc100");
				String s_loc200=multi.getParameter("s_loc200");
				
				String b_loc2=multi.getParameter("b_loc2");
				String s_loc100_2=multi.getParameter("s_loc100_2");
				String s_loc200_2=multi.getParameter("s_loc200_2");
				
				String b_loc3=multi.getParameter("b_loc3");
				String s_loc100_3=multi.getParameter("s_loc100_3");
				String s_loc200_3=multi.getParameter("s_loc200_3");
				
				String str_locno=null; // locno가 여러개이기 때문에 우선은 변수 선언 
				String str_locno2=null;
				String str_locno3=null;
				
				if(b_loc.equals("100")) { // (대분류) 서울시 선택의 경우 
					str_locno=s_loc100;
				} else if (b_loc.equals("200")) { // (대분류) 경기도 선택 경우 
					str_locno=s_loc200;
				}
				int resultloc=0;
				resultloc=ld.insert(classno, str_locno); // 첫번째 담기 
				
				
				if(b_loc2.equals("100")) { // (대분류) 서울시 선택의 경우 
					str_locno=s_loc100_2;
				} else if (b_loc.equals("200")) { // (대분류) 경기도 선택 경우 
					str_locno=s_loc200_2;
				}
				resultloc+=ld.insert(classno, str_locno); // 두번째 담기(+=) 
				 
				
				if(b_loc3.equals("100")) { // (대분류) 서울시 선택의 경우 
					str_locno=s_loc100_3;
				} else if (b_loc.equals("200")) { // (대분류) 경기도 선택 경우 
					str_locno=s_loc200_3;
				}
				resultloc+=ld.insert(classno, str_locno); // 세번째 담기(+=)
				
			}
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
//2. 수정
	

}
