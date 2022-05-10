<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ----------------------삭제 확인 페이지---------------------------- -->
<h2>삭제하시겠습니까?</h2>
<form action="tchRegDeletePro.do">
	<input type="hidden" name="pageNum" value="${pageNum }">
	<input type="hidden" name="classno" value="${classno }">
	<input type="hidden" name="tchclass" value="${tchclass }">
	<input type="submit" value="확인">
</form>
</body>
</html>