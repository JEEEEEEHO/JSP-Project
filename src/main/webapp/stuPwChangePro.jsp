<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정 처리 결과</title>
</head>
<body>
	<!-- 1.수정 성공 시 -->
	<c:if test="${result == 1 }">
		<script type="text/javascript">
			alert("개인정보가 수정되었습니다");
			location.href="stuPwChangeForm.do";
			</script>
	</c:if>
	
	<!-- 2.수정 오류 시  -->
	<c:if test="${result < 1}">
		<script type="text/javascript">
			alert("개인정보가 수정되지 않았습니다.\n기존 비밀번호를 확인하고 다시 시도해주세요.");
			location.href="stuPwChangeForm.do";
		</script>		
	</c:if>
</body>
</html>