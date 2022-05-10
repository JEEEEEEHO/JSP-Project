<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ----------------------입력완료시 리스트페이지로 돌아가기---------------------------- -->
<c:if test="${result > 0 && insertLoc_result > 0}">
	<script type="text/javascript">
		alert("입력 완료");  
		location.href="TchList.do";
	</script>
</c:if>
<!-- ----------------------오류일 경우 다시 등록페이지로 돌아가기---------------------------- -->
<c:if test="${result == 0 || insertLoc_result == 0}">  
	<script type="text/javascript">
		alert("클래스 등록 실패");  
		location.href="tchReg.do";
	</script>
</c:if>
</body>
</html>