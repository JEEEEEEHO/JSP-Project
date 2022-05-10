<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원탈퇴 처리</title>
	<c:if test="${result == 1}">
		<script type="text/javascript">
			alert("탈퇴가 성공적으로 마무리되었습니다.\n그동안 저희 서비스를 이용해 주셔서 감사합니다.");
			location.href = "main.do";
		</script>
	</c:if>
	<c:if test="${result != 1}">
		<script type="text/javascript">
			alert("탈퇴 오류");
			location.href = "stuDeleteForm.do";
		</script>
	</c:if>
</head>
<body>

</body>
</html>