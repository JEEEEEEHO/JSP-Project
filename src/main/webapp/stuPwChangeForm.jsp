<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>개인정보 수정 | CLASS 33</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link rel="stylesheet" type="text/css" href="css/myPageSNB.css">
	<link rel="stylesheet" type="text/css" href="css/myPageStuPwChange.css">
	
	<script type="text/javascript">
		 function chk() {
			 if(!frm.oldPw.value){
				 alert("기존 비밀번호를 입력해주세요")
				 frm.oldPw.focus();
				return false;
			 }
	
			if (frm.newPw.value != frm.newPwCheck.value) {
				alert("변경할 비밀번호가 일치하지않습니다.");
				frm.newPwCheck.focus();				
			return false;
			} 
			
			return true;
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
	                        <li><a href="stuMyPage.do">마이페이지</a></li>
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
				<div class="snb">
					<div class="snb_wrapper1">
						<p>마이페이지</p>
					</div>
					<div class="snb_wrapper3"></div>
					<div class="snb_wrapper2">
						<a href="#" class="a">수강신청 내역</a>
					</div>
					<div class="snb_wrapper2">
						<a href="stuUpdateForm.do" class="a">개인정보 수정</a>
					</div>
					<div class="snb_wrapper2">
						<a href="stuPwChangeForm.do" class="a">비밀번호 변경</a>
					</div>
					<div class="snb_wrapper2">
						<a href="stuDeleteForm.do" class="a">회원탈퇴</a>
					</div>
					<div class="snb_wrapper3"></div>
				</div>
	
	
	
				<!-- 개인정보 수정 컨텐츠 영역---------------------------------------------------------------------- -->	
				<section class="section_com"> 
					<h2>비밀번호 변경</h2>
					<form action="stuPwChangePro.do" name="frm" onsubmit="return chk()" method="post">
						<ul class="ul_wrapping">
							<li>
								<label for="password" class="label">기존 비밀번호</label>
								<input class="i-box" type="password" name="oldPw" >
							</li><p>
							<li>
								<label for="password2" class="label">변경할 비밀번호</label>
								<input class="i-box" type="password" name="newPw">
							</li><p>
							<li>
								<label for="password2" class="label">비밀번호 확인</label>
								<input class="i-box" type="password" name="newPwCheck" >
								<p class="pwtext">
							</li><p>
							<li>
								<input class="i-box-reset" type="reset" value="취소">
								<input class="i-box-submit" type="submit" value="수정 완료">
							</li><p>
						</ul>
					</form>
					
				</section>
				<!-- 끝 ------------------------------------------------------------------------------------- -->
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