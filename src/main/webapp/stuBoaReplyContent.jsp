<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Class 33</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/stuBoaReplyContent.css">
<script type="text/javascript" src="js/jquery.js"></script>	
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
	
	
	/* function goList() {
		alert("grade="+${param.grade});
		
		location.href="clsList.do?grade=${param.grade}";
		
	} */

</script>	
</head>
<body>
	<div id="wrap">
		<div id="margin_wrap">
		    <jsp:include page="stu_header.jsp"/>	

<!-- ----------------------------------------------------header 끝 -------------------------------------------------------------------------------------------------->

<!--------------------------------------------------- section main 시작 ---------------------------------------------------------------------------------------------->
<!---------------------------------------------------원글 상세 내용--------------------------------------------------------------------------------------------------->
<section class="section_com">
			<h1>공지사항</h1><br><br>
				<div>
					<table id="ort"> <!-- 원글에 대한 내용을 보여줌  -->
						<th id="bs">${board.subject}</th><th id="bw">${board.write_date }</th><th id="bn">${board.name }</th>
						<tr><td id="bc" colspan="3">${board.content }</td></tr>
					</table>
				</div>
			<br>
			<br>
<!--------------------------------------------------- 댓글들 보기---------------------------------------------------------------------------------------------------->
			<c:forEach var="board2" items="${list }" >
				<c:if test="${board2.re_level>0 }">
					<table class="ret" >
						<tr>
							<td id="reh" colspan="2" >
								${board2.name2 } ${board2.write_date } 
							</td>
						</tr>
							<c:choose>
								<c:when test="${studno==board2.studno }">
									<tr>
										<td id="reh">
											<form action="stuBoaReplyUpdatePro.do?" method="post">
												<input type="hidden" name="pageNum" value="${pageNum }">
												<input type="hidden" name="boardno" value="${board2.boardno }">
												<input type="hidden" name="ref" value="${board.ref}">
												
												<input id="reply" type="text" name="content" placeholder="${board2.content }" required="required">
												<input id="btnu" type="submit" value="수정">
											</form>	
										</td>
										<td id="reb">
											<script type="text/javascript">
												function deletechk() {
													if(confirm("정말로 삭제하시겠습니까")==true){
													location.href="stuBoaReplyDeletePro.do?pageNum=${pageNum}&boardno=${board2.boardno}&ref=${ref}";
													} else{
													return false;
														}
													}
											</script>
											<input id="btnd" type="button" value="삭제" onclick="return deletechk()"> <!-- 글쓴이가 같은 경우 -->
										</td>
									</tr>	
								</c:when>
								<c:otherwise>
									<tr>
										<td id="rec" colspan="4">
										${board2.content } 
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
					</table>
				</c:if>
			</c:forEach>
<!--------------------------------------------------- 댓글등록------------------------------------------------------------------------------------------------------------------->
			<br>
			<br>
			<form action="stuBoaReplyWritePro.do" method="post">
				<div id="rer">
					<input type="hidden" name="pageNum" value="${pageNum }"> 
					<!-- 1 -->
					<input type="hidden" name="boardno" value="${boardno }"> 
					<!-- 원글 boardno, 대댓글은 이 부분을 board2에서 가져오면 됨 -->
					<input type="hidden" name="studno" value="${studno }"> 
					<!-- 어짜피 있을 때만 가져감 -->
					<input type="hidden" name="tchno" value="${board.tchno }">
					<!-- 원글의 선생님 -->
					<input type="hidden" name="subject" value="${board.subject }">
					<!-- 원글의 제목 (댓글은 제목 없음) -->
					<input type="hidden" name="readcount" value="${board.readcount }">
					<!-- 원글의 조회수, 사실 0이 되어도 됨 -->
					<input type="hidden" name="write_date" value="${board.write_date }">
					
					<!-- 원글 boardno, 대댓글에서는 댓글의 것들 -->
					<input type="hidden" name="ref" value="${ref}">
					<input type="hidden" name="re_step" value="${board.re_step}"> <!-- 0 -->
					<input type="hidden" name="re_level" value="${board.re_level}"> <!-- 0 -->
					
					<input id="inp" type="text" name="content" required="required">
					<input id="btn" type="submit" value="등록">
				</div>
			</form>
		</main>
	</section>	
<!--------------------------------------------------- section main 끝------------------------------------------------------------------------------------>
    	<jsp:include page="stu_footer.jsp"/>
   	</div>
    	<!-- margin_wrap 끝 -->
    </div>
    <!-- wrap 끝 -->
</body>
</html>