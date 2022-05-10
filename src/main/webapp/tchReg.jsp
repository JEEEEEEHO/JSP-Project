<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="UTF-8">

<title>클래스 등록</title>

<jsp:include page="tch_head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/tchRegUpdate.css">


<script type="text/javascript">
	function chk(){
		if(!frm.b_subject.value) {
			alert("과목을 선택해주세요");
			frm.b_subject.focus();
			return false;
		}
	
		if(!frm.class_name.value) {
			alert("제목을 입력해주세요");
			frm.class_name.focus();
			return false;
		} 
	
		if(!frm.grade.value) {
			alert("수강대상을 입력해주세요");
			frm.grade.focus();
			return false;
		} 
	
		if(!frm.keyword.value) {
			alert("키워드를 입력해주세요");
			frm.keyword.focus();
			return false;
		} 
	
		if(!frm.b_loc.value) {
			alert("지역을 선택해주세요");
			frm.b_loc.focus();
			return false;
		}
		
		if(!frm.s_loc100.value && !frm.s_loc200.value) {
			alert("상세 지역을 선택해주세요");
			frm.b_loc.focus();
			return false;
		} 
	
		if(!frm.lec_time.value) {
			alert("수업시간을 입력해주세요");
			frm.lec_time.focus();
			return false;
		} 
	
		if(!frm.lec_duration.value) {
			alert("수업기간을 입력해주세요");
			frm.lec_duration.focus();
			return false;
		} 
	
		if(!frm.teacher_intro.value) {
			alert("강사소개를 입력해주세요");
			frm.teacher_intro.focus();
			return false;
		} 
	
		if(!frm.lec_intro.value) {
			alert("강의소개를 입력해주세요");
			frm.lec_intro.focus();
			return false;
		} 
	
		if(!frm.curriculum.value) {
			alert("커리큘럼을 입력해주세요");
			frm.curriculum.focus();
			return false;
		} 
	
		return true;
	}
	
	function getSLoc(upper_locno) {
		var s_loc100 = document.getElementById("s_loc100");
		var s_loc200 = document.getElementById("s_loc200");
		
		if(upper_locno == "100") {
			s_loc100.style.display = "inline";
		} else {
			s_loc100.style.display = "none";
		}
		
		if(upper_locno == "200") {
			s_loc200.style.display = "inline";
		} else {
			s_loc200.style.display = "none";
		}
		
	}
	
	function getSLoc2(upper_locno) {
		
 		var s_loc100_2 = document.getElementById("s_loc100_2");
		var s_loc200_2 = document.getElementById("s_loc200_2");
		
		if(upper_locno == "100") {
			s_loc100_2.style.display = "inline";
		} else {
			s_loc100_2.style.display = "none";
		}
		
		if(upper_locno == "200") {
			s_loc200_2.style.display = "inline";
		} else {
			s_loc200_2.style.display = "none";
		}
		
	}
	
	function getSLoc3(upper_locno) {
		
 		var s_loc100_3 = document.getElementById("s_loc100_3");
		var s_loc200_3 = document.getElementById("s_loc200_3");
		
		if(upper_locno == "100") {
			s_loc100_3.style.display = "inline";
		} else {
			s_loc100_3.style.display = "none";
		}
		
		if(upper_locno == "200") {
			s_loc200_3.style.display = "inline";
		} else {
			s_loc200_3.style.display = "none";
		}
		
	}
	
	function getSSubject(b_subjectno){
		var s_subj400 = document.getElementById("s_subject400");
		var s_subj500 = document.getElementById("s_subject500");
		
		if(b_subjectno == "400") {
			s_subj400.style.display = "inline";
		} else {
			s_subj400.style.display = "none";
		}
		
		if(b_subjectno == "500") {
			s_subj500.style.display = "inline";
		} else {
			s_subj500.style.display = "none";
		}
	}
	
	function addSelect() {
		var b_loc2 = document.getElementById("b_loc2");
		var b_loc3 = document.getElementById("b_loc3");
		
		if(b_loc2.style.display == "none") {
			b_loc2.style.display = "inline";
		} else if(b_loc2.style.display == "inline"){
			b_loc3.style.display = "inline";
		}
		
		
	}
	
	function goBack() {
		history.go(-1);
	}
</script>


</head>
<!-- ----------------------등록 표 생성---------------------------- -->
<body>

	<form action="tchRegPro.do" name="frm" enctype="multipart/form-data" method="post" onsubmit="return chk()">
		
		<!-- Layout wrapper -->
	<div class="layout-wrapper layout-content-navbar">
		<div class="layout-container">
			<jsp:include page="tch_menu.jsp"></jsp:include>
			
			
			<!-- Layout container -->
			<div class="layout-page">

				<!-- Content wrapper -->
				<div class="content-wrapper">
					
					<!-- Content -->
					<div class="container-xxl flex-grow-1 container-p-y">
						<ul class="nav nav-pills flex-column flex-md-row">
							<li class="navbar-nav-right d-flex align-items-center">
								<h4 class="fw-bold py-3 mb-4">클래스 등록</h4>
							</li>
						</ul>
						
						<div class="card">
							<div class="table-responsive text-nowrap">
								<table class="table">
									<tbody class="table-border-bottom-0">
		
										<tr><td>제목</td>
										<td><input type="text" name="class_name" required="required" maxlength="40"></td></tr>
										
										<tr><td>수강대상</td>
											<td>
												<select name="grade">
													<option value="0" selected="selected">모든 학년</option>
													<option value="1">1학년</option>
													<option value="2">2학년</option>
													<option value="3">3학년</option>
												</select>									
											</td>
										</tr>
										
										<tr>
											<td>과목</td>
											<td>
												<select id="b_subject" name="b_subject" onchange="getSSubject(this.value)">
													<option value="" selected="selected">과목</option>
													<c:forEach var="b_subj" items="${b_subj_list }">
														<option value="${b_subj.subjectno }">${b_subj.subject_name }</option>
													</c:forEach>										
												</select>
												
												<select id="s_subject400" name="s_subject400" style="display:none">
													<c:forEach var="s_subj" items="${s_subj_list400 }">
														<option value="${s_subj.subjectno }">${s_subj.subject_name }</option>
													</c:forEach>
												</select>
													
												<select id="s_subject500" name="s_subject500" style="display:none">
													<c:forEach var="s_subj" items="${s_subj_list500 }">
														<option value="${s_subj.subjectno }">${s_subj.subject_name }</option>
													</c:forEach>
												</select>
											</td>
										</tr>
												
										<tr><td>키워드</td>
										<td><input type="text" name="keyword" required="required" maxlength="20"></td></tr>
											
										<tr>
											<td>수강가능한 지역</td>
											<td>
												<div>
													<select id="b_loc" name="b_loc" onchange="getSLoc(this.value)">
														<option value="" selected="selected">지역</option>
														<c:forEach var="b_loc" items="${b_loc_list }">
															<option value="${b_loc.locno }">${b_loc.loc_name }</option>
														</c:forEach>										
													</select>
													
													<select id="s_loc100" name="s_loc100" style="display:none">
														<option value="100">전체</option>
														<c:forEach var="s_loc" items="${s_loc_list100 }">
															<option value="${s_loc.locno }">${s_loc.loc_name }</option>
														</c:forEach>
													</select>
													
													<select id="s_loc200" name="s_loc200" style="display:none">
														<option value="200">전체</option>
														<c:forEach var="s_loc" items="${s_loc_list200 }">
															<option value="${s_loc.locno }">${s_loc.loc_name }</option>
														</c:forEach>
													</select>
													<input id="add_loc" type="button" value="추가" onclick="addSelect()">
												</div>
												
												<div>
													<select id="b_loc2" name="b_loc2" onchange="getSLoc2(this.value)" style="display:none">
														<option value="" selected="selected">지역</option>
														<c:forEach var="b_loc" items="${b_loc_list }">
															<option value="${b_loc.locno }">${b_loc.loc_name }</option>
														</c:forEach>										
													</select>
													
													<select id="s_loc100_2" name="s_loc100_2" style="display:none">
														<option value="100">전체</option>
														<c:forEach var="s_loc" items="${s_loc_list100 }">
															<option value="${s_loc.locno }">${s_loc.loc_name }</option>
														</c:forEach>
													</select>
													
													<select id="s_loc200_2" name="s_loc200_2" style="display:none">
														<option value="200">전체</option>
														<c:forEach var="s_loc" items="${s_loc_list200 }">
															<option value="${s_loc.locno }">${s_loc.loc_name }</option>
														</c:forEach>
													</select>
												</div>
												
												<div>
													<select id="b_loc3" name="b_loc3" onchange="getSLoc3(this.value)" style="display:none">
														<option value="" selected="selected">지역</option>
														<c:forEach var="b_loc" items="${b_loc_list }">
															<option value="${b_loc.locno }">${b_loc.loc_name }</option>
														</c:forEach>										
													</select>
													
													<select id="s_loc100_3" name="s_loc100_3" style="display:none">
														<option value="100">전체</option>
														<c:forEach var="s_loc" items="${s_loc_list100 }">
															<option value="${s_loc.locno }">${s_loc.loc_name }</option>
														</c:forEach>
													</select>
													
													<select id="s_loc200_3" name="s_loc200_3" style="display:none">
														<option value="200">전체</option>
														<c:forEach var="s_loc" items="${s_loc_list200 }">
															<option value="${s_loc.locno }">${s_loc.loc_name }</option>
														</c:forEach>
													</select>
												</div>
												
												
											</td>
										</tr>	
										
										<tr><td>수업시간</td>
										<td><input type="text" name="lec_time" required="required" maxlength="40"></td></tr>	
										
										<tr><td>수업기간</td>
										<td><input type="text" name="lec_duration" required="required" maxlength="7"></td></tr>	
										
										<tr><td>강사소개</td>
										<td><textarea name="teacher_intro" rows="10" cols="130"></textarea></td></tr>	
											
										<tr><td>강의소개</td>
										<td><textarea name="lec_intro" rows="15" cols="130"></textarea></td></tr>	
											
										<tr><td>커리큘럼</td>
										<td><textarea name="curriculum" rows="20" cols="130"></textarea></td></tr>	
											
										<tr><td>본문이미지</td>
										<td><input type="file" name="main_img" accept="image/*" required="required"></td></tr>
										
										<tr>
											<td colspan="2">
												<input type="reset" value="취소" onclick="goBack()" class="btn btn-outline-secondary">
												<input type="submit" value="등록" class="btn btn-primary me-2">
											</td></tr>
											
										</tbody>
										
									</table>
								 </div>
							</div>
							<!-- / Basic Bootstrap Table -->
						</div>
						<!-- / Content -->
							
						<div class="content-backdrop fade"></div>	
					</div>
					<!-- Content wrapper -->
				</div>
				<!-- / Layout page -->
			</div>
		
		<!-- Overlay -->
		<div class="layout-overlay layout-menu-toggle"></div>
	</div>	
	<!-- / Layout wrapper -->
	
	<jsp:include page="tch_footer.jsp"></jsp:include>	
			
	</form>
</body>
</html>