<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="UTF-8">
<title>클래스 상세</title>
<!-- -----------------------------------------style------------------------------------------ -->
<style type="text/css">
	.m_img {
		width:228px;
		height:200px;
	}
	.m_img img{
		width:228px;
		height:200px;
	}
	.table pre {
		font-size:15px;
	}
</style>
<!-- -----------------------------------------style------------------------------------------ -->

<jsp:include page="tch_head.jsp"></jsp:include>

</head>
<body>
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
								<h4 class="fw-bold py-3 mb-4">클래스 상세보기</h4>
							</li>
						</ul>
						
						
						<div class="card">
							<div class="table-responsive text-nowrap">
								<table class="table">
									<tbody class="table-border-bottom-0">
										<tr><td>제목</td><td>${tchclass.class_name}</td></tr>
										<tr><td>수강대상</td><td>${tchclass.grade}학년</td></tr>
										<tr><td>과목</td><td>${tchclass.subject_name}</td></tr>	
										<tr><td>키워드</td><td>${tchclass.keyword}</td></tr>	
										<tr>
											<td>수강가능한 지역</td>
											<td>
												<c:forEach var="loc" items="${s_locList }">
													<pre>${loc.upper_locname } ${loc.loc_name }</pre><br>
												</c:forEach>
											</td>
										</tr>	
										<tr><td>수업시간</td><td>${tchclass.lec_time}</td></tr>	
										<tr><td>수업기간</td><td>${tchclass.lec_duration}</td></tr>	
										<tr><td>강사소개</td><td><pre>${tchclass.teacher_intro}</pre></td></tr>		
										<tr><td>강의소개</td><td><pre>${tchclass.lec_intro}</pre></td></tr>		
										<tr><td>커리큘럼</td><td><pre>${tchclass.curriculum}</pre></td></tr>		
										<tr><td>본문이미지</td>
											<td>
												<c:if test="${tchclass.main_img != null }">
													<div class="m_img"><img alt="본문이미지" src="${context }/${tchclass.main_img}"></div> 
												</c:if>
												
												<c:if test="${tchclass.main_img == null }">
													이미지가 없습니다
												</c:if>
											</td>
										</tr>
										
										<tr><td colspan="2">	
										
										<input type="button" value="수정" class="btn btn-outline-secondary"
								            onclick="location.href='tchRegUpdateForm.do?classno=${tchclass.classno}&pageNum=${pageNum}'">
										<input type="button" value="삭제" class="btn btn-outline-secondary"
								            onclick="location.href='tchRegDeleteForm.do?classno=${tchclass.classno}&pageNum=${pageNum}'">
										<input type="button" value="목록" class="btn btn-outline-secondary"
										    onclick="location.href='tchlist.do?pageNum=${pageNum}'"></td></tr>
									</tbody>
								</table>
							</div>
						</div>
						<!--/ Basic Bootstrap Table -->
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
</body>
</html>