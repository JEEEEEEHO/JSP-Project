<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- ------------------------------------------입력창에 명령을 주는 자바 스크립트 시작--------------------------- -->
<script type="text/javascript">
	function chk() {
		if(!frm.b_subject.value){
			alert("과목을 선택해주세요");
			frm.b_subject.focus();
			return false;
		}
		if(!frm.class_name.value){
			alert("제목을 입력해주세요");
			frm.class_name.focus();
			return false;
		}
		if(!frm.grade.value){
			alert("제목을 입력해주세요");
			frm.grade.focus();
			return false;
		}
		if(!frm.keyword.value){
			alert("제목을 입력해주세요");
			frm.keyword.focus();
			return false;
		}
		if(!frm.b_loc.value){
			alert("지역을 선택해주세요");
			frm.b_loc.focus();
			return false;
		}
		if(!frm.s_loc100.value && !frm.s_loc200.value){
			alert("상세 지역을 선택해주세요");
			frm.b_loc.focus();
			return false;
		}
		if(!frm.lec_time.value){
			alert("수업 시간을 입력해주세요");
			frm.lec_time.focus();
			return false;
		}
		if(!frm.lec_duration.value){
			alert("수업 기간을 입력해주세요");
			frm.lec_duration.focus();
			return false;
		}
		if(!frm.teacher_intro.value){
			alert("강사 소개를 입력해주세요");
			frm.teacher_intro.focus();
			return false;
		}
		if(!frm.lec_intro.value){
			alert("강의 소개를 입력해주세요")
			frm.lec_intro.focus()	
			return false;
		}
		if(!frm.curriculum.value){
			alert("커리큘럼을 입력해주세요")
			frm.curriculum.focus();
			return false;
		}
		return true;
	}
<!-- ------------------------------------------입력창에 명령을 주는 자바 스크립트 끝 ---------------------------- -->

<!-- ------------------------------------------선택창을 띄워주는 자바 스크립트 시작------------------------------- -->
	function getSLoc(upper_locno) { /* 첫번쨰 지역창 */ 
		var s_loc100=document.getElementById("s_loc100");
		var s_loc200=document.getElementById("s_loc200");
		
		if(upper_locno=="100"){  /* 서울일 때 */ 
			s_loc100.style.display="inline";
		} else{
			s_loc100.style.display="none";			
		}
		
		if(upper_locno="200"){ /* 경기도일 때 */
			s_loc200.style.display="inline";
		} else{
			s_loc200.style.display="none";
		}
	}
	function getSLoc2(upper_locno){ /* 두번쨰 지역창 */
		var s_loc100_2=document.getElementById("s_loc100_2");
		var s_loc200_2=document.getElementById("s_loc200_2");
		
		if(upper_locno=="100"){ /* 서울일 때 */
			s_loc100_2.style.display="inline";
		} else{
			s_loc100_2.style.display="none";			
		}
		
		if(upper_locno="200"){ /* 경기도일 때 */
			s_loc200_2.style.display="inline";
		} else{
			s_loc200_2.style.display="none";
		}
	}
	function getSLoc3(upper_locno){ /* 세번쨰 지역창 */
		var s_loc100_3=document.getElementById("s_loc100_3");
		var s_loc200_3=document.getElementById("s_loc200_3");
		
		if(upper_locno=="100"){ /* 서울일 때 */
			s_loc100_3.style.display="inline";
		} else{
			s_loc100_3.style.display="none";			
		}
		
		if(upper_locno="200"){ /* 경기도일 때 */
			s_loc200_3.style.display="inline";
		} else{
			s_loc200_3.style.display="none";
		}
	}
	<!-- -----------------과목------------------ -->
	function getSSubject(b_subjectno){ /* 상위 과목 번호 100,200,300,400,500 */
		var s_subj400=document.getElementById("s_subject400"); /* 사탐번호 때 */
		var s_subj500=document.getElementById("s_subject500")  /* 과탐번호 때 */
		
		if(b_subjectno=="400"){
			s_subj400.style.display="inline";
		} else{
			s_subj400.style.display="none";
		}
		
		if(b_subjectno=="500"){
			s_subj500.style.display="inline";
		} else{
			s_subj500.style.display="none";
		}
	}
	<!-- -----------------지역추가------------------->
	function addSelect() {
		var b_loc2=document.getElementById("b_loc2");
		var b_loc3=document.getElementById("b_loc3");
		
		if(b_loc2.style.display=="none"){ /* 2번째 추가인 경우  */
			b_loc2.style.display="inline"; /* 2번째를 보이고 */
		} else if(b_loc2.style.disply="inline"){ /* 3번째 추가인 경우 = 2번째가 떠 있는 경우  */
			b_loc3.style.display="inline"; /* 3번째를 보여라 */
		}
	}
	
	function goBack() {
		history.go(-1);
	}
</script>
<!-- ------------------------------------------선택창을 띄워주는 자바 스크립트 끝------------------------------- -->
</head>
<!-- ------------------------------------------수정할 클래스 목록 ------------------------------------------ -->
<body>
	<form action="tchRegUpdatePro.do" name="frm" enctype="multiplart/form-data" method="post" onsubmit="return chk()">
	<!-- ---페이지넘버, 클래스 넘버, 이미지 가져감---------- -->
		<input type="hidden" name="pageNum" value="${pageNum }">
		<input type="hidden" name="classno" value="${classno }">
		<input type="hidden" name="org_main_img" value="${tchclass.main_img}">
		
		<table class="card">
			<tr><td>제목</td>
			<td><input type="text" name="class_name" required="required" value="${tchclass.class_name }" maxlength="40"> </td>
			</tr>
			
			<tr>
			<td>
				<select name="grade">
					<option value="0" <c:if test="${tchclass.grade==0 }"> selected="selected"</c:if>>모든학년</option>
					<!-- 원래 가지고 있는 grade가 selected 되어서 정해짐  -->
					<option value="1" <c:if test="${tchclass.grade==1 }"> selected="selected"</c:if>>1학년</option>
					<option value="2" <c:if test="${tchclass.grade==2 }"> selected="selected"</c:if>>2학년</option>
					<option value="3" <c:if test="${tchclass.grade==3 }"> selected="selected"</c:if>>3학년</option>
				</select>
			</td>
			</tr>
			
			<tr>
				<td>과목</td>
				<td>
					<select id="b_subject" name="b_subject" onchange="getSSubject(this.value)"> <!--s_subj 보일지 말지 결정-->
						<option value="" selected="selected">과목</option> <!-- 아무것도 안들고 가는 값 -->
						<c:forEach var="b_subj" items="${b_subj_list }"> <!-- 대분류 --> <!-- 100, 200, 300, 400, 500 -->
							<option value="${b_subj.subjectno }" <c:if test="${b_subj.subjectno==b_tchSubj }">selected="selected"</c:if>>${b_subj.subject_name }</option>
						</c:forEach>
					</select>
					
					<select id="s_subject400" name="s_subject400" <c:if test="${b_tchSubj!=400 }"> style="display:none" </c:if>> <!-- 사탐 b_tchSubj! -->
						<c:forEach var="s_subj" items="${s_subj_list400}"> <!-- 사탐 세부 과목 선택 401 402.. -->
							<option value="${s_subj.subjectno}" <c:if test="${s_subj.subjectno==tchclass.subjectno }">selected="selected"</c:if>>${s_subj.subject_name }</option>
						</c:forEach>
					</select>
					
					<select id="s_subject500" name="s_subject500" <c:if test="${b_tchSubj!=500 }"> style="display:none" </c:if>> <!-- 과탐 b_tchSubj! -->
						<c:forEach var="s_subj" items="${s_subj_list500}"> <!-- 과탐 세부 과목 선택 501 502 ... -->
							<option value="${s_subj.subjectno}" <c:if test="${s_subj.subjectno==tchclass.subjectno }">selected="selected"</c:if>>${s_subj.subject_name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>키워드</td>
				<td><input type="text" name="keyword" required="required" value="${tchclass.keyword }" maxlength="20"></td>
			</tr>

			<tr>
				<td>수강가능한 지역</td>
				<td>
					<div>
						<select id="b_loc" name="b_loc" onchange="getSLoc(this.value)" >  <!--s_loc들이 보일지 말지 결정-->
							<option value="" selected="selected">지역</option> <!-- 디폴트-->
							<c:forEach var="b_loc" items="${b_loc_list }" > <!-- 서울시/경기도 선택-->
								<option value="${b_loc.locno }">${b_loc.loc_name }</option> 
							</c:forEach>
						</select>
						
						<select id="s_loc100" name="s_loc100" style="display: none;"> <!-- 서울시 선택-->
							<option value="100">전체</option> <!-- 서울시 전체 선택-->
							<c:forEach var="s_loc" items="${s_loc_list100 }"> <!-- 서울시의 구 선택-->
								<option value="${s_loc.locno }">${s_loc.loc_name }</option> 
							</c:forEach>							
						</select>
						
						<select id="s_loc200" name="s_loc200" style="display: none;"> <!-- 경기도 선택-->
							<option value="200">전체</option> <!-- 경기도 전체 선택-->
							<c:forEach var="s_loc" items="${s_loc_list200 }"> <!-- 경기도의 시 선택-->
								<option value="${s_loc.locno }">${s_loc.loc_name }</option> 
							</c:forEach>							
						</select>
						<input id="add_loc" type="button" value="추가" onclick="addSelect()"> (지역을 반드시 선택해주세요) <!-- 지역추가 -->					
					</div>
					
					<div>
						<select id="b_loc2" name="b_loc2" onchange="getSLoc2(this.value)" >  <!--s_loc들이 보일지 말지 결정-->
							<option value="" selected="selected">지역</option> <!-- 디폴트-->
							<c:forEach var="b_loc" items="${b_loc_list }" > <!-- 서울시/경기도 선택-->
								<option value="${b_loc.locno }">${b_loc.loc_name }</option> 
							</c:forEach>
						</select>
						<select id="s_loc100_2" name="s_loc100_2" style="display: none;"> <!-- 서울시 선택-->
							<option value="100">전체</option> <!-- 서울시 전체 선택-->
							<c:forEach var="s_loc" items="${s_loc_list100 }"> <!-- 서울시의 구 선택-->
								<option value="${s_loc.locno }">${s_loc.loc_name }</option> 
							</c:forEach>							
						</select>
						<select id="s_loc200_2" name="s_loc200_2" style="display: none;"> <!-- 경기도 선택-->
							<option value="200">전체</option> <!-- 경기도 전체 선택-->
							<c:forEach var="s_loc" items="${s_loc_list200 }"> <!-- 경기도의 시 선택-->
								<option value="${s_loc.locno }">${s_loc.loc_name }</option> 
							</c:forEach>							
						</select>
						<input id="add_loc" type="button" value="추가" onclick="addSelect()"> (지역을 반드시 선택해주세요) <!-- 지역추가 -->					
					</div>
					
					<div>
						<select id="b_loc3" name="b_loc3" onchange="getSLoc3(this.value)" >  <!--아래 것들이 보일지 말지 결정-->
							<option value="" selected="selected">지역</option> <!-- 디폴트-->
							<c:forEach var="b_loc" items="${b_loc_list }" >
								<option value="${b_loc.locno }">${b_loc.loc_name }</option> <!-- 서울시/경기도 선택-->
							</c:forEach>
						</select>
						<select id="s_loc100_3" name="s_loc100_3" style="display: none;"> <!-- 서울시 선택-->
							<option value="100">전체</option> <!-- 서울시 전체 선택-->
							<c:forEach var="s_loc" items="${s_loc_list100 }">
								<option value="${s_loc.locno }">${s_loc.loc_name }</option> <!-- 서울시의 구 선택-->
							</c:forEach>							
						</select>
						<select id="s_loc200_3" name="s_loc200_3" style="display: none;"> <!-- 경기도 선택-->
							<option value="200">전체</option> <!-- 경기도 전체 선택-->
							<c:forEach var="s_loc" items="${s_loc_list200 }">
								<option value="${s_loc.locno }">${s_loc.loc_name }</option> <!-- 경기도의 시 선택-->
							</c:forEach>							
						</select>
					</div>									
				</td>
			</tr>
			
			<tr>
				<td>수업시간</td>
				<td><input type="text" name="lec_time" required="required" value="${tchclass.lec_time }" maxlength="40"></td>
			</tr>
			
			<tr>
				<td>본문이미지</td>
				<td>
					<c:if test="${tchclass.main_img!=null }">
						<div class="m_img">
							<img alt="본문이미지" src="${context }/${tchclass.main_img}">
							<input type="file" name="main_img" accept="image/*">
						</div>
					</c:if>
					<c:if test="${tchclass.main_img == null }"> 이미지가 없습니다
						<input type="file" name="main_img" accept="image/*">
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="reset" value="취소" onclick="goBack()">
					<input type="submit" value="수정완료">
				</td>
			</tr>		
		</table>
	</form>
</body>
</html>