<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class 33</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

	function getSSubject(b_subjectno){
		var s_subj400 = document.getElementById("s_subject400");
		var s_subj500 = document.getElementById("s_subject500");
		
		if(b_subjectno == "%400") {
			s_subj400.style.display = "inline";
		} else {
			s_subj400.style.display = "none";
		}
		
		if(b_subjectno == "%500") {
			s_subj500.style.display = "inline";
		} else {
			s_subj500.style.display = "none";
		}
	}
	
	function getSLoc(upper_locno) {
		var s_loc100 = document.getElementById("s_loc100");
		var s_loc200 = document.getElementById("s_loc200");
		
		if(upper_locno == "%100") {
			s_loc100.style.display = "inline";
		} else {
			s_loc100.style.display = "none";
		}
		
		if(upper_locno == "%200") {
			s_loc200.style.display = "inline";
		} else {
			s_loc200.style.display = "none";
		}
		
	}
	/* function goList() {
		alert("grade="+${param.grade});
		
		location.href="clsList.do?grade=${param.grade}";
		
	} */
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
	                        <li><a href="stuMyPage.do">마이클래스</a></li>
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
	                <li <c:if test="${grade == 1 }">style="border-bottom:2px solid #3B82F6;"</c:if>><a href="main.do?grade=1"><h2 <c:if test="${grade == 1 }">style="color:#3B82F6;"</c:if>>고1</h2></a></li>
	                <li <c:if test="${grade == 2 }">style="border-bottom:2px solid #3B82F6;"</c:if>><a href="main.do?grade=2"><h2 <c:if test="${grade == 2 }">style="color:#3B82F6;"</c:if>>고2<h2></a></li>
	                <li <c:if test="${grade == 3 }">style="border-bottom:2px solid #3B82F6;"</c:if>><a href="main.do?grade=3"><h2 <c:if test="${grade == 3 }">style="color:#3B82F6;"</c:if>>고3<h2></a></li>
	            </ul>
		    </div>
		    
		    <hr>	
		    <!-- ------------------------header 끝 --------------------------- -->
<!-- ------------------------------------------------------	Main 시작 ---------------------------------------------------------->
		    <main>
				<div id="sorting_box">
					<form action="main.do?">
						<table>
							<input type="text" name="grade" value="${grade }" style="display:none">
							<tr>
								<th>과목</th>
								<td>
									<select id="b_subject" name="b_subject" onchange="getSSubject(this.value)">
										<option value="%" selected="selected">전체</option>
										<c:forEach var="b_subj" items="${b_subj_list }">
											<option value="%${b_subj.subjectno }">${b_subj.subject_name }</option>
										</c:forEach>										
									</select>
									
									<select id="s_subject400" name="s_subject400" style="display:none">
										<option value="4%" selected="selected">전체</option>
										<c:forEach var="s_subj" items="${s_subj_list400 }">
											<option value="%${s_subj.subjectno }">${s_subj.subject_name }</option>
										</c:forEach>
									</select>
										
									<select id="s_subject500" name="s_subject500" style="display:none">
										<option value="5%" selected="selected">전체</option>
										<c:forEach var="s_subj" items="${s_subj_list500 }">
											<option value="%${s_subj.subjectno }">${s_subj.subject_name }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							
							<tr>
								<th>지역</th>
								<td>
									<select id="b_loc" name="b_loc" onchange="getSLoc(this.value)">
										<option value="%" selected="selected">전체</option>
										<c:forEach var="b_loc" items="${b_loc_list }">
											<option value="%${b_loc.locno }">${b_loc.loc_name }</option>
										</c:forEach>										
									</select>
									
									<select id="s_loc100" name="s_loc100" style="display:none">
										<option value="1%" selected="selected">전체</option>
										<c:forEach var="s_loc" items="${s_loc_list100 }">
											<option value="%${s_loc.locno }">${s_loc.loc_name }</option>
										</c:forEach>
									</select>
									
									<select id="s_loc200" name="s_loc200" style="display:none">
										<option value="2%" selected="selected">전체</option>
										<c:forEach var="s_loc" items="${s_loc_list200 }">
											<option value="%${s_loc.locno }">${s_loc.loc_name }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							
							<tr id="btn_submit">
								<td colspan="2">
									<input type="submit" value="적용">
									<input type="reset"  value="취소">
								</td>
							</tr>
							
							
						</table>
					</form>
				</div> <!-- sorting_box 끝 -->
				
				
				
				<div id="class_box">
					
					<c:if test="${totCnt > 0 }">
						<ul class="clearfix">
							<c:forEach var="classe" items="${list }">
								<li>
									<a href="clsMtch.do?classno=${classe.classno}">
										<div id="img"></div>
									</a>
									
									<a href="clsMtch.do?classno=${classe.classno}">
										<span class="text_box">
											<h4>${classe.class_name }</h4>
											<p>${classe.keyword }</p>
										</span>
									</a>
								</li>
							</c:forEach>
				 		</ul>
				 	</c:if>
				 	<c:if test="${totCnt == 0 }">
			 			<p>클래스가 존재하지 않습니다.</p>
		 			</c:if>
				</div>
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
		    		<a href>교사 센터 바로가기</a>
		    		<img src="images/front_chevron-right.svg">
		    	</div>
		    
		    </footer>
    	</div>
    	<!-- margin_wrap 끝 -->
    </div>
    <!-- wrap 끝 -->
</body>
</html>