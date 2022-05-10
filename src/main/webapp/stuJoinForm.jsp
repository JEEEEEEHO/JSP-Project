<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>회원가입 | CLASS 33</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link rel="stylesheet" type="text/css" href="css/joinMain.css">
	
	<script type="text/javascript"> 
	function chk(){
		
		if(frm.password.value !=frm.password2.value){
			alert("암호가 다릅니다");
			frm.passwd.focus();
			return false;
		}	
		return true;
	}
	function winop(){
		if(!frm.stu_id.value){
			alert("id를 입력하고 사용하세요")
			frm.stu_id.focus();
			return false;
		}
		window.open("stuIdCheck.do?stu_id=" +frm.stu_id.value, "","width=300 height=300");
		
          }    

	
</script> 
</head>
<body>
	<div id="wrap">
		<div id="margin_wrap">
		    <header>
		        <div id="logo">
		            <a href="main.do"><img src="images/logo.svg" class="logo"></a>
		        </div>
		        <div id="search_box">
		            <form>
		                <fieldset>
		                    <legend class="visually-hidden">검색</legend>
		                    <img alt="검색아이콘" src="images/icons/front_search.svg">
		                    <input type="text" maxlength="340" tabindex="1" placeholder="찾으시는 클래스가 있나요?"/>
		                </fieldset>
		            </form>
		        </div>
		        
	            <nav>
	                <div id="nav_items1">
	                    <ul class="clearfix">
	                        <li><a href="clslist.do">클래스</a></li>
	                        <li><a href="comboard.do">공지사항</a></li>
	                        <li><a href="stumypage.do">마이클래스</a></li>
	                        <c:if test="${stu_id == null}">
		                        <li><a href="stuJoinForm.do">회원가입</a></li>
		                        <li><a href="stuLoginForm.do">로그인</a></li>
	                        </c:if>
	                        <c:if test="${stu_id != null}">
		                        <li class="userName">${name} 님</li>
		                        <li><a href="stuLogoutPro.do">로그아웃</a></li>
	                        </c:if>
	                    </ul>
	                </div>
	                
	            </nav>
		        
		    </header>
		    
		    <div id="gnb">
	            <ul class="clearfix">
	                <li><a href="#"><h2>고1<h2></h2></a></li>
	                <li><a href="#"><h2>고2<h2></a></li>
	                <li><a href="#"><h2>고3<h2></a></li>
	            </ul>
		    </div>
		    
		    <hr>	
		    <!-- ------------------------header 끝 --------------------------- -->
		    
		        	
<!-- ------------------------------------------------------	Main 시작 ---------------------------------------------------------->
	
	
	<main>
		<form action="stuJoinForm2.do" name="frm" onsubmit="return chk()">
		<div class="input_area">
				<h2 class="main-h1">회원가입</h2>
				<div class="join_sty">
				<input class="input" type="text" name="stu_id" maxlength="20" placeholder="아이디" required="required" title="20글자내외로 입력하시오" >
				</div>
				<input class="join_sty2" type="button" value="중복확인" onclick="winop()">
				<input type="hidden" name="idDuplication" value="idUncheck" >

				<input class="input" type="password" name="password" placeholder="비밀번호" required="required">
				<input class="input" type="password" name="password2" placeholder="비밀번호 확인" required="required">
		<input class="button_area" type="submit" value="회원가입"	>
		<div class="logingo">
		    <p>이미 회원이신가요? <a href="stuLoginForm.do">로그인하기</a></p>
		</div>
	</div>		
	</form>
	</main>
	<!-- ------------------------------------------------------	Main 끝 ---------------------------------------------------------->		    
		    
		    <footer>
		    	<div id="footerContent">
		    		CLASS 33(주)<br>
		    		대표자 : 이대 3조<br>
		    		사업자등록번호 : 333-33-33333<br>
		    		주소 : 서울특별시 마포구 대흥동 12-20 301호<br>
		    		E-Mail : class33@class33.co.kr<br>
		    		Copyrights © Class 33.<br>
		    	</div>
		    	
		    	<div id="btnTeacherLogin">
		    		<a href="#">교사 센터 바로가기</a>
		    		<img src="images/icons/front_chevron-right.svg">
		    	</div>
		    
		    </footer>
    	</div>
    	<!-- margin_wrap 끝 -->
    </div>
    <!-- wrap 끝 -->
</body>
</html>