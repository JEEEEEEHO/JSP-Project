<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
</head>
			<header>
				<div id="logo">
					<a href="main.do"><img src="images/logo.svg" class="logo"></a>
				</div>
				<div id="search_box">
					<form>
						<fieldset>
							<legend class="visually-hidden">검색</legend>
							<img alt="검색아이콘" src="images/icons/front_search.svg"> 
							<input type="text" maxlength="340" tabindex="1" placeholder="찾으시는 클래스가 있나요?" />
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
								<li class="userName">${stuName}님</li>
								<li><a href="stuLogoutPro.do">로그아웃</a></li>
							</c:if>
						</ul>
					</div>
				</nav>
			</header>

			<div id="gnb">
				<ul class="clearfix">
					<li><a href="main.do?grade=1"><h2>고1<h2></h2></a></li>
					<li><a href="main.do?grade=2"><h2>고2<h2></a></li>
					<li><a href="main.do?grade=3"><h2>고3<h2></a></li>
				</ul>
			</div>
			<hr>


</html>