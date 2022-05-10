<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>회원가입 | CLASS 33</title>
	<link rel="stylesheet" type="text/css" href="assets/css/TchLogin.css">
	
	<script type="text/javascript"> 
		function chk(){
			if(frm.password.value != frm.password2.value){
				alert("암호가 다릅니다");
				frm.password2.focus();
				return false;
			}	
			return true;
		}
		function winop(){
			if(!frm.tch_id.value){
				alert("id를 입력하고 사용하세요")
				frm.tch_id.focus();
				return false;
			}
			 window.open("tchIdCheck.do?tch_id=" +frm.tch_id.value, "","width=300 height=300");
		}    
		function inputIdChk(){
			document.userInfo.idDuplication.value="inUncheck";
		}
</script> 
</head>
<body>
	<div class="authentication-inner">
        <div class="card">
            <div>
                <img src="images/admin_logo2.svg">
            </div>
            <div>
                <h3>Special edu starts here 🚀</h3>
                <p>특별한 교육 매칭을 시작하세요!</p>
            </div>
            <div>
                <form action="tchJoinForm2.do" name="frm" onsubmit="return chk()" method="post">
                <div>
                    <input class="input_id" type="text" name="tch_id" maxlength="20" title="20글자내외로 입력하시오" placeholder="아이디" required="required">
                    <input class="duplicateCheck" type="button" value="중복확인" onclick="winop()">
                    <input type="hidden" name="inDuplication" value="idUncheck">
                 </div>   
                    <input class="input" type="password" name="password" placeholder="비밀번호" required="required">
                    <input class="input" type="password" name="password2" placeholder="비밀번호 확인" required="required">
                    <input class="button_area" type="submit" value="회원가입">
                    <div class="joingo">
                        이미 회원이신가요? <a href="tchLoginForm.do">로그인하기</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>