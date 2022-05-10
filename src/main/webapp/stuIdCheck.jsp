<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 Id 중복체크</title>
<link rel="stylesheet" type="text/css" href="css/IdCheck.css">
</head>
<body>
	<c:if test="${result != 0}">
		<div id="user" align="center"> 
			이미 사용중인 아이디입니다.
			<form action="stuIdCheck.do" >
			</form>
		</div>
	</c:if>

	<c:if test="${result == 0}">
		<div align="center"> 
			사용 가능한 아이디입니다.
		</div>
	</c:if>
	<div align="center">
		<a href="javascript:self.close();">닫기</a>
	</div>
	



</body>
</html>