<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Class 33</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/clsMtch.css">
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
</script>	
<title>Insert title here</title>
</head>
<!---------------------------------------- header 시작 ---------------------------------------------------->
<body>
	<div id="wrap">
		<div id="margin_wrap">
		    <jsp:include page="stu_header.jsp"/>
<!---------------------------------------- header 끝 ---------------------------------------------------->
<!--------------------------------------	Main 시작 ---------------------------------------------------->
				<section class="section_com">
					<main>
						<script>
							function login() {
						 		alert("회원가입이 필요한 페이지 입니다");
						 		location.href="stuLoginForm.do";
								}
						</script>
						<script type="text/javascript">
							function resume() {
								window.open("fileSave/profile/${resume }")
							}			
						</script>
						<div id="container">
							<div id="sidebar_img">
								<img id="pimg" alt="프로필 이미지" src="${classe.main_img }">
							</div>
						
							<div id="intro">
								<div>
									<h2>${classe.name } 선생님</h2>
								</div>
									<br>
									<br>
								<div>
									<h1 id="cn" >${classe.class_name }</h1>
								</div>
									<br>
									<br>
								<div>
									<h4>${classe.keyword }</h4>
								</div>	
									<br>
									<br>		
								<div style="padding-left: 150px;">
							 		<button id="btn" onclick="resume()">이력서보기</button>
									<c:choose>
										<c:when test="${stu_id==null }">
											<button id="btn" type="button" onclick="login()">수강신청</button>
										</c:when>
										<c:otherwise>
											<c:if test="${classe.status==0}">
												<button id="btn" type="button" onclick="location.href='clsMtchSuc.do?classno=${classno}'">수강신청</button>
											</c:if>
											<c:if test="${classe.status==1 }">
												<button id="btnd" type="button" disabled="disabled">수강마감</button>
													<script type="text/javascript">
															function deletechk() {
																if(confirm("정말로 취소하시겠습니까")==true){
																	location.href="clsMtchCnl.do?classno=${classno}";
																} else{
																	return false;
																	}
																}
													</script>
												<button id="btn" type="button" onclick="deletechk()">수강취소</button>
											</c:if>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<cot>
								<div id="contents">
									<h2>수업 가능한 지역</h2>
									<br>
									<br>
									<div id="region" >
										<c:forEach var="loclist" items="${list }">
											${loclist.upper_locname }  ${loclist.loc_name }<br>
										</c:forEach>
									</div>
								</div>
								<div id="contents">
									<h2>수업 가능한 시간</h2>
									<br>
									<br>
									<p>${classe.lec_time }</p>
								</div>
								<div id="contents">
									<h2>커리큘럼</h2>
										<br>
										<br>
									<p id="curri">${classe.curriculum }</p>
								</div>	
							</cot>
						</div>
					</main>
				</section>
<!--------------------------------------	Main 끝 ---------------------------------------------------->
<!--------------------------------------	footer ---------------------------------------------------->
			<jsp:include page="stu_footer.jsp"/>
		</div>
    	<!-- margin_wrap 끝 -->
	</div>
    <!-- wrap 끝 -->
</body>
</html>
