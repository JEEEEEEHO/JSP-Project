package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;


/**
 * Servlet implementation class Controller
 */
//@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//         /list.do=service.ListAction
    private Map<String , Object>   commandMap  = new HashMap<String, Object>();
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void init(ServletConfig config) throws ServletException {
		 // web.xml에서 propertyConfig에 해당하는 init-param 의 값을 읽어옴
	    String props = config.getInitParameter("config");
        // /WEB-INF/command.properties
		System.out.println("String props=> "+ props);  // /ch16/com
	    
		  //명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
	    Properties pr = new Properties();
	    FileInputStream f = null;
	    try {
	    	 //                                                  /WEB-INF/command.properties
	          String configFilePath = config.getServletContext().getRealPath(props);
	  		  System.out.println("String configFilePath=> "+ configFilePath);  // /ch16/com
	          f = new FileInputStream(configFilePath);
	        //command.properties파일의 정보를  Properties객체에 저장
	          pr.load(f);
	     } catch (IOException e) { throw new ServletException(e);
	     } finally {
	          if (f != null) try { f.close(); } catch(IOException ex) {}
	     }
	  //Iterator객체는 Enumeration객체를 확장시킨 개념의 객체
	     Iterator keyIter = pr.keySet().iterator();
	   //객체를 하나씩 꺼내서 그 객체명으로 Properties객체에 저장된 객체에 접근
	     while( keyIter.hasNext() ) {
	          String command = (String)keyIter.next();         // /list.do
	          String className = pr.getProperty(command);      // service.ListAction
	          System.out.println("className=> "+ className);  
	          try {
	        	//  ListAction la = new ListAction();
	               Class commandClass = Class.forName(className);//해당 문자열을 클래스로 만든다.
	               Object commandInstance = commandClass.newInstance();//해당클래스의 객체를 생성
	               commandMap.put(command, commandInstance); // Map객체인 commandMap에 객체 저장
	          } catch (Exception e) {
	               throw new ServletException(e);
	          }
	     }
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestServletPro(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestServletPro(request, response);
	}
	//시용자의 요청을 분석해서 해당 작업을 처리
	protected void requestServletPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
	    CommandProcess com=null;
	    String command = request.getRequestURI();
		System.out.println("1. command=> "+ command);  // /och16/list.do
	    command = command.substring(request.getContextPath().length());
		System.out.println("2. command substring=> "+ command);  // /ch16/list.do
	    try {
	        com = (CommandProcess)commandMap.get(command);  
			System.out.println("command=> "+ command);  // /ch16/list.do
			System.out.println("com=> "+ com);  // /ch16/list.do
	        view = com.requestPro(request, response);
			System.out.println("view=> "+ view);  // /ch16/list.do
		    
		   
		} catch (Exception e) {
			throw new ServletException(e); 
		}
	    
	    if(command.contains("ajaxupdateform")) { // agax string을 가지고 있을 경우 
	    	System.out.println("ajaxupdateform ->" + command);
	    	
	    	String content = (String) request.getAttribute("content");
	    	// service 단에 있는 content 에 대한 정보를 가져오겠다 
	    	PrintWriter pw = response.getWriter();
	    	pw.write(content);
	    	// 그 정보를 다시 jsp successfunction 부분으로 보내겠다  -> stuBoaReplyContent.jsp
	    	pw.flush();
	    }else {
	    	// 일반적인 경우 
	     RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	     dispatcher.forward(request, response);
		 }	
	}
	
	

}
