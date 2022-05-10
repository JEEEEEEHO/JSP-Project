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
<!-- ----------------------수정 완료시 리스트 페이지로 다시 돌아가기---------------------------- -->
<c:if test="${(result > 0) && (deleteLoc_result > 0) && (insertLoc_result > 0)}">
	<script type="text/javascript">
		alert("수정 완료 되었습니다");
		location.href="tchContent.do?pageNum=${pageNum}&classno=${classno}";
	</script>
</c:if>	
<!-- ----------------------수정 오류시 다시 업데이트 등록 페이지로 돌아가기---------------------------- -->
<c:if test="${result == 0 || deleteLoc_result == 0 || insertLoc_result == 0}">
	<script type="text/javascript">
		alert("수정되지 않았습니다");
		location.href="tchRegUpdateForm.do?classno=${num}&pageNum=${pageNum}";
	</script>
</c:if>
</body>
</html>