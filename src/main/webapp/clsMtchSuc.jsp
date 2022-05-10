<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<c:if test="${result==1 }">
		<script type="text/javascript">
			alert("매칭이 완료되었습니다");
			location.href="clsMtch.do?classno=${classno}";
			/* clsMtch.do?classno=${classno} 이렇게 걸어줘야 수강신청 완료가 반영되므로 클래스 메인화면에서 이 값을 넘겨줘야함 */
		</script>
	</c:if> 
	
	<c:if test="${result!=1 }">
		<script type="text/javascript">
			alert("회원가입이 필요한 페이지 입니다");
			location.href="stuLoginForm.do";
		</script>
	</c:if>
</body>
</html>