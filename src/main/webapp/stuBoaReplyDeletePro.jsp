<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			alert("삭제가 완료되었습니다");  
			location.href="stuBoaContent.do?&pageNum=${pageNum}&boardno=${boardno}&ref=${ref}";
			/* 댓글의 ref 를 가는 바람에 contetn 로 가게 되면 내용이 보이지 않음 */ 
		</script>
	</c:if>
	<c:if test="${result <= 0 }">  
		<script type="text/javascript">
			alert("오류");  
			location.href="stuBoaContent.do?&pageNum=${pageNum}&boardno=${boardno}&ref=${ref}";
		</script>
	</c:if>
</body>
</html>