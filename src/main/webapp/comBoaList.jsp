<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/comBoaList.css">
<title>Class 33</title>
<script type="text/javascript">
	function getSSubject(b_subjectno){
		var s_subj400 = document.getElementById("s_subject400");
		var s_subj500 = document.getElementById("s_subject500");
		
		if(b_subjectno == "%400") {
			s_subj400.style.display = "inline";
		} else {
			s_subj400.style.display = "none";
		}
		
		if(b_subjectno == "%500") {
			s_subj500.style.display = "inline";
		} else {
			s_subj500.style.display = "none";
		}
	}
	
	function getSLoc(upper_locno) {
		var s_loc100 = document.getElementById("s_loc100");
		var s_loc200 = document.getElementById("s_loc200");
		
		if(upper_locno == "%100") {
			s_loc100.style.display = "inline";
		} else {
			s_loc100.style.display = "none";
		}
		
		if(upper_locno == "%200") {
			s_loc200.style.display = "inline";
		} else {
			s_loc200.style.display = "none";
		}
		
	}
</script>	
</head>
	<div id="wrap">
		<div id="margin_wrap">
		    <jsp:include page="stu_header.jsp"/>
<!-- ------------------------header 끝 --------------------------------------------------------------------------->
<!--------------------------------------------------- section main ----------------------------------------------->
				<section class="section_com" >
					<main>
						<h1>공지사항</h1><br>
						<table class="tab" border="1" >
							<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>
							<c:forEach var="board" items="${list }">
								<c:if test="${board.re_level==0 }"> <!-- 원글만 불러옴 -->
										<tr align="center" >
											<td >${startNum}</td> <!-- 전체 수  -->
											<td align="left" > <!-- ref 가 있어야만 content 부분에서 댓글만 뽑아서 보여줄 수 있음, 원글에 대한 정보  -->
											<a id="ca" href="stuBoaContent.do?pageNum=${currentPage}&boardno=${board.boardno }&ref=${board.ref}">
											${board.subject}</a></td>
											<td>${board.name}</td>
											<td>${board.write_date }</td>
											<td>${board.readcount }</td>
										</tr>
								</c:if>
								<c:set var="startNum" value="${startNum-1 }" />	
							</c:forEach>
						</table>
						<div id="pn">
							<c:if test="${startPage>blockSize}">
								<a href="comBoard.do?pageNum=${startPage-blockSize}">[이전]</a>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<a id="pna" href="comBoard.do?pageNum=${i}">[${i}]</a>
							</c:forEach>
							<c:if test="${endPage<pageCnt}">
								<a href="comBoard.do?pageNum=${startPage+blockSize}">[다음]</a>
							</c:if>
						</div>
					</main>	
				</section>
<!--------------------------------------------------- section main 끝---------------------------------------------->
 			<jsp:include page="stu_footer.jsp"/>
    	</div>
    	<!-- margin_wrap 끝 -->
    </div>
    <!-- wrap 끝 -->
</body>
</html>