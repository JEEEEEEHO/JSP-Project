<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- Menu -->
			<aside id="layout-menu"
				class="layout-menu menu-vertical menu bg-menu-theme">
				<div class="app-brand demo">
					<a href="tchlist.do" class="app-brand-link"> <span
						class="app-brand-logo demo"> <img
							src="assets/img/favicon/admin_logo.svg">
					</span>
					</a> <a href="javascript:void(0);"
						class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
						<i class="bx bx-chevron-left bx-sm align-middle"></i>
					</a>
				</div>

				<div class="menu-inner-shadow"></div>

				<ul class="menu-inner py-1">
					<!-- 클래스 관리 -->
					<li class="menu-item" <c:if test="${menu_num == '1' }">style="border-right:5px solid #3B82F6"</c:if>>
						<a href="tchlist.do?menu_num=1" <c:if test="${menu_num == '1' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>
						class="menu-link" > 
						<i class="menu-icon tf-icons bx bx-home-circle"></i> 클래스 관리
					</a></li>

					<!-- 공지사항 관리 -->
					<li class="menu-item" <c:if test="${menu_num == '2' }">style="border-right:5px solid #3B82F6"</c:if>>
						<a href="tchBoaList.do?menu_num=2" <c:if test="${menu_num == '2' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>
						class="menu-link"> 
						<i class="menu-icon tf-icons bx bx-dock-top"></i> 공지사항 관리
					</a></li>

					<!-- 설정 -->
					<li class="menu-item">
						<a href="javascript:void(0);"
						class="menu-link menu-toggle"> 
						<i class="menu-icon tf-icons bx bx-cog me-2"></i> 설정
					</a>

						<ul class="menu-sub" <c:if test="${menu_num == '3' || menu_num == '4' || menu_num == '5'}">style="display:block"</c:if>>
							<li class="menu-item" <c:if test="${menu_num == '3'}">style="border-right:5px solid #3B82F6"</c:if>>
								<a href="tchUpdateForm.do?menu_num=3" <c:if test="${menu_num == '3' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>
								class="menu-link">개인정보 수정</a>
							</li>
							<li class="menu-item" <c:if test="${menu_num == '4'}">style="border-right:5px solid #3B82F6"</c:if>>
								<a href="tchPwChangeForm.do?menu_num=4" <c:if test="${menu_num == '4' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>
								class="menu-link">비밀번호 변경</a>
							</li>
							<li class="menu-item" <c:if test="${menu_num == '5'}">style="border-right:5px solid #3B82F6"</c:if>>
								<a href="tchDeleteForm.do?menu_num=5" <c:if test="${menu_num == '5' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>
								class="menu-link">회원탈퇴</a>
							</li>
						</ul></li>
				</ul>
				<div class="text-center mb-4">
					<a href="tchLogoutPro.do" class="bg-menu-theme mb-3"><p>로그아웃</p></a>
					<p>
						<a href="main.do" target="_blank" class="bg-menu-theme">서비스
							페이지 바로가기 ></a>
					</p>
				</div>
			</aside>
			<!-- / Menu -->