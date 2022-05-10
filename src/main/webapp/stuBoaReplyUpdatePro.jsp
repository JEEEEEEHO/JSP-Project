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
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			alert("수정이 완료되었습니다");  
			location.href="stuBoaContent.do?&pageNum=${pageNum}&boardno=${boardno}&ref=${ref}";
			/* ref가 수정의 경우에는 같은 게시글이기 때문에 => content 로 가기는 하지만 그렇게 되면 게시글의 contentent도 화면상 변경 */ 
		</script>
	</c:if>
	<c:if test="${result <= 0 }">  
		<script type="text/javascript">
			alert("오류가 있습니다");  
			location.href="stuBoaContent.do?&pageNum=${pageNum}&boardno=${boardno}&ref=${ref}";
		</script>
	</c:if>
</body>
</html>