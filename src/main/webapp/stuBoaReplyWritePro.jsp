<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> <!-- 회원에 한해서 오는 페이지 이기 때문에 오류가 있다면 그건 어떤 입력값의 오류일 것 --> 
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			alert("등록 되었습니다");  
			location.href="stuBoaContent.do?pageNum=${pageNum}&boardno=${boardno}&ref=${ref}";
		</script>
	</c:if>
	<c:if test="${result == 0 }">  
		<script type="text/javascript">
			alert("입력값에 오류가 있습니다");  
			location.href="stuBoaContent.do?pageNum=${pageNum}&boardno=${boardno}&ref=${ref}";
		</script>
	</c:if>
</body>
</html>