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
	<c:if test="${result >0 }">
		<script type="text/javascript">
		alert("회원가입을 축하합니다");
		location.href="lastJoin.jsp";
		</script>
	</c:if>

	<c:if test="${result ==0 }">
		<script type="text/javascript">
			alert("회원가입 실패, 다시시도해주세요");
			location.href="tchJoinForm.do";
		</script>
	</c:if>
</body>
</html>