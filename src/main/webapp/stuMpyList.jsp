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
<!-- -------------------- 김지호 담당부분 css1 시작-------------------------------------------------------------- -->
<style type="text/css">
	.section_com {
		width: 940px;
		height: auto;
		display: inline;
		float: right;
    	padding: 20px;
		background-color:  white; /* section 영역 확인용 */
	}
	main{
		width: 100%;
    	min-height: 524px;
    	margin-top: 30px;
	}
	main::after{
		clear: both;
		content: '';
		display: block;
	}
	
 	#lii:nth-child(3n+1){
		margin-left:0px;
	} 
</style>
<!-- -------------------- 김지호 담당부분 css1 끝-------------------------------------------------------------- -->
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
</head>
<body>
	<div id="wrap">
		<div id="margin_wrap">
		    <jsp:include page="stu_header.jsp"/>
<!-- ------------------------header 끝 ------------------------------------------------------------------>
<!-- ------------------------------------- main 시작- ---------------------------------------------------->
	<main>
		<jsp:include page="stu_myPageSNB.jsp"/>
<!-- -------------------- 김지호 담당부분2 시작-------------------------------------------------------------- -->
			<section class="section_com">
				<h1>수강 신청 내역</h1>
				<br>
				<ul class="clearfix" style="padding-left: 30px;">
					<div id="class_box">
						<c:choose>
							<c:when test="${result>0}"> <!--  -->
								<c:forEach var="classe" items="${list }">
										<li id="lii">
											<a href="clsMtch.do?classno=${classe.classno}">
												<div id="img"><img src="${classe.main_img }" alt="profile"></div>
											</a>
											
											<a href="clsMtch.do?classno=${classe.classno}">
												<span class="text_box">
													<h4>${classe.class_name }</h4>
													<p>${classe.keyword }</p>
												</span>
											</a>
										</li>
								</c:forEach>
							</c:when>
							<c:when test="${result==0}">
								수강신청 내역이 없습니다
							</c:when>
						</c:choose>
					</div>	 		
		 		</ul>
 			</section>
	</main>
<!-- -------------------- 김지호 담당부분2 끝-------------------------------------------------------------- -->
<!-- ------------------------------------- main 끝- ---------------------------------------------------->
    		<jsp:include page="stu_footer.jsp"></jsp:include>
		</div>
    	<!-- margin_wrap 끝 -->
    </div>
    <!-- wrap 끝 -->
</body>
</html>