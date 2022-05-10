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
<!-- ----------------------삭제 완료 후 리스트로 돌아가기---------------------------- -->
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("삭제되었습니다");
		location.href="TchList.do?pageNum=${pageNum}";
		
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("삭제하지 못했습니다");
		location.href="tchContent.do?pageNum=${pageNum}&classno=${classno}";
	</script>
</c:if>

</body>
</html>