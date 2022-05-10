<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인 처리 결과</title>
</head>
<body>
	<!-- 1.로그인 성공 시 -->
	<c:if test="${result == 1 }"> 
		<script type="text/javascript">
			location.href = "main.do";
		</script>
	</c:if>
	<!-- 2.비밀번호 틀릴 경우 -->
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("비밀번호가 다릅니다.");
			history.go(-1);
		</script>
	</c:if>
	<!-- 3.회원이 아닌 경우 -->
	<c:if test="${result == -1 }">
		<script type="text/javascript">
			alert("아직 회원이 아니시네요. 회원가입을 진행하시겠습니까?");
			location.href = "stuJoinForm.do";
		</script>
	</c:if>
</body>
</html>