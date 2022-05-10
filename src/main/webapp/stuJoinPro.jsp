<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 회원가입 page1</title>
</head>
<body>
<c:if test="${result>0 }">
	<script type="text/javascript">
	alert("다음페이지로 이동");
	location.href= "stuJoinForm2.do";
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
	alert("입력실패, 다시시도해 주세요");
	location.href="stuJoinForm.do";
	</script>
</c:if>
</body>
</html>