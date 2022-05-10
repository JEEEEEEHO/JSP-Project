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
	<link rel="stylesheet" type="text/css" href="css/myPageStuUpdate.css">
	
	<script type="text/javascript">
		function chk() {
			if (!frm.phone.value) {
				alert("전화번호를 입력하세요.");
				frm.phone.focus();
				return false;
			}
			if (!frm.birth.value) {
				alert("생년월일을 입력하세요.");
				frm.birth.focus();
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
	                        <li><a href="main.do">클래스</a></li>
	                        <li><a href="comBoard.do">공지사항</a></li>
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
						<a href="stuMyPage.do" class="a">수강신청 내역</a>
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
					<h2>개인정보 수정</h2>
					<form action="stuUpdatePro.do" name="frm" onsubmit="return chk()" method="post">
						<ul class="ul_wrapping">
							<li>
								<label for="stu_id" class="label">아이디</label>
								<input class="i-box_disable" type="text" name="stu_id" value="${student.getStu_id()}" readonly="readonly">
								<!-- disabled 로 설정하면 value값을 가져가지 못한다. -->
							</li>
							<li>
								<label for="name" class="label">이름</label>
								<input class="i-box_disable" type="text" name="name" value="${student.getName()}" readonly="readonly">
							</li>
							<li>
								<label for="phone" class="label">전화번호</label>
								<input class="i-box" type="text" name="phone" value="${student.getPhone()}"
									   placeholder="'-' 제외하고 입력" pattern="\d{11}" title="'-' 제외하고 입력">
									   <!-- required="required" 조건 없애야 얼럿 노출됨 -->	   
							</li>
							<li>
								<label for="birth" class="label">생년월일</label>
								<input class="i-box" type="text" name="birth" value="${student.getBirth()}"
									   placeholder="8자리 입력 (19970205)" pattern="\d{8}" title="생년월일 8자리 입력">
							</li>
							<li>
								<label for="gender" class="label">성별</label>
								<select class="i-box3" name="gender">
									<c:if test="${student.getGender() == 0 || student.getGender() == null}">
										<option value="${student.getGender()}" selected="selected">남</option>
										<option value="1">여</option>
									</c:if>
									<c:if test="${student.getGender() == 1}">
										<option value="0">남</option>
										<option value="${student.getGender()}" selected="selected">여</option>
									</c:if>
								</select>
							</li>
							<li>
								<input class="i-box-reset" type="reset" value="취소">
								<input class="i-box-submit" type="submit" value="수정 완료">
							</li>
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