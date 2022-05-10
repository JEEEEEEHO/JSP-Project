<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<title>클래스 관리 | Class 33</title>

<jsp:include page="tch_head.jsp"></jsp:include>

</head>

<body>
	<!-- Layout wrapper -->
	<div class="layout-wrapper layout-content-navbar">
		<div class="layout-container">
		
<!-- --------------------------------------------------------------------------------------------------------------------------------------------- -->		
			<jsp:include page="tch_menu.jsp"></jsp:include>

<!-- --------------------------------------------------------------------------------------------------------------------------------------------- -->


			<!-- Layout container -->
			<div class="layout-page">

				<!-- Content wrapper -->
				<div class="content-wrapper">

					<!-- Content -->
					<div class="container-xxl flex-grow-1 container-p-y">
						<ul class="nav nav-pills flex-column flex-md-row">
							<li class="navbar-nav-right d-flex align-items-center">
								<h4 class="fw-bold py-3 mb-4">클래스 관리</h4>
							</li>
							<li class="navbar-nav flex-row align-items-center ms-auto">
								<button type="submit" class="btn btn-primary me-2 mb-3"
									onclick="location.href='tchReg.do?menu_num=1'">클래스 등록</button>
							</li>
						</ul>

						<!-- Basic Bootstrap Table -->
						<div class="card">
							<div class="table-responsive text-nowrap">
								<table class="table">
									<thead>
										<tr>
											<th>클래스 제목</th>
											<th>과목</th>
											<th>대상</th>
											<th>진행 상태</th>
										</tr>
									</thead>
									<tbody class="table-border-bottom-0">

										<c:forEach var="tchclass" items="${list }">

											<tr onclick="location.href='tchContent.do?classno=${tchclass.classno}&pageNum=${currentPage}&menu_num=1'"
												style="cursor: pointer;">
												<td><i class="fab fa-angular fa-lg text-danger me-3"></i>
													<strong>${tchclass.class_name}</strong></td>
												<td>${tchclass.subject_name}</td>
												<td>${tchclass.grade}</td>
												<td><c:if test="${tchclass.status == 0}">
														<span class="badge bg-label-primary me-1">모집중</span>
													</c:if> <c:if test="${tchclass.status == 1}"><span class="badge bg-label-secondary me-1">마감</span></c:if></td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
						<!--/ Basic Bootstrap Table -->

						<!-- Basic Pagination -->
						<nav aria-label="Page navigation" class="container-p-y">
							<ul class="pagination justify-content-center">

								<c:if test="${startPage > blockSize }">
									<li class="page-item prev"><a class="page-link"
										href='TchList.do?pageNum=${startPage-blockSize}'> <i
											class="tf-icon bx bx-chevrons-left"></i>
									</a></li>
								</c:if>

								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<li class="page-item"><a class="page-link"
										href='TchList.do?pageNum=${i}'>${i}</a></li>
								</c:forEach>

								<c:if test="${endPage < pageCnt }">
									<li class="page-item next"><a class="page-link"
										href='TchList.do?pageNum=${startPage+blockSize}'> <i
											class="tf-icon bx bx-chevrons-right"></i>
									</a></li>
								</c:if>

							</ul>
						</nav>
						<!--/ Basic Pagination -->
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


<!-- -----------------------------------------------------------------------------------------------------------------------footer -->
	<jsp:include page="tch_footer.jsp"></jsp:include>
</body>
</html>

