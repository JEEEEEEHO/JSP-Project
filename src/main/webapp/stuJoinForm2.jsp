<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>회원가입 | CLASS 33</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link rel="stylesheet" type="text/css" href="css/joinMain2.css">

	
</head>
<body>
	<div id="wrap">
		<div id="margin_wrap">
		    <header>
		        <div id="logo">
		            <a href="main.do"><img src="images/logo.svg" class="logo"></a>
		        </div>
		        <div id="search_box">
		            <form>
		                <fieldset>
		                    <legend class="visually-hidden">검색</legend>
		                    <img alt="검색아이콘" src="images/icons/front_search.svg">
		                    <input type="text" maxlength="340" tabindex="1" placeholder="찾으시는 클래스가 있나요?"/>
		                </fieldset>
		            </form>
		        </div>
		        
	            <nav>
	                <div id="nav_items1">
	                    <ul class="clearfix">
	                        <li><a href="clslist.do">클래스</a></li>
	                        <li><a href="comboard.do">공지사항</a></li>
	                        <li><a href="stumypage.do">마이클래스</a></li>
	                        <c:if test="${stu_id == null}">
		                        <li><a href="stuJoinForm.do">회원가입</a></li>
		                        <li><a href="stuLoginForm.do">로그인</a></li>
	                        </c:if>
	                        <c:if test="${stu_id != null}">
		                        <li class="userName">${name} 님</li>
		                        <li><a href="stuLogoutPro.do">로그아웃</a></li>
	                        </c:if>
	                    </ul>
	                </div>
	                
	            </nav>
		        
		    </header>
		    
		    <div id="gnb">
	            <ul class="clearfix">
	                <li><a href="#"><h2>고1<h2></h2></a></li>
	                <li><a href="#"><h2>고2<h2></a></li>
	                <li><a href="#"><h2>고3<h2></a></li>
	            </ul>
		    </div>
		    
		    <hr>	
		    <!-- ------------------------header 끝 --------------------------- -->
		    
		        	
<!-- ------------------------------------------------------	Main 시작 ---------------------------------------------------------->
	<main>
		<form action="stuJoinPro2.do">
		<div class="condition1">
		

			<div id="parent">
				<h3 class="main-h1">■ 필수 회원 정보 입력 </h3><br>
				<input type="hidden" name="stu_id" value="${stu_id}">
				<input type="hidden" name="password" value="${password}">
				
				<div class="child c1">이름</div>
					<input class="input_name" type="text" name="name" required="required">	
				
				<div class="child">생년월일 </div>
					<input class="input_birth" type="text" name="birth" required="required" 
				      	 placeholder="ex_19990130" title="8자리로 입력하세요">
 				<p><br>
	 			<div class="child c1">전화번호</div>
		 			<input class="input_ph" type="text" name="phone"  required="required"	 
		 			       placeholder="'-'를 제외하고 입력" pattern="\d{11}">
	 			<div class="child">성별</div>
		 			<select name="gender" id="gender">
		 				<option value="0" selected="selected"> 남 </option>
		 				<option value="1" > 여 </option>
		 			</select> 
		 	</div><br>
 			<div class="condition">
 			<h3 class="main-h2"> ■ 이용약관 (필수) </h3>
 			<textarea rows="10" cols="100">
제1조 (목적 등)
1. 이 약관은 CLASS33에서 제공하는 인터넷서비스(이하 CLASS33)를 이용함에 있어 이용자”와 CLASS33”의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.
2. 회원이 되고자 하는 자가 “CLASS33”이 정한 소정의 절차를 거쳐서 "등록하기" 단추를 누르면 본 약관에 동의하는 것으로 간주합니다. 본 약관에 정하는 이외의 이용자와 CLASS33의 권리, 의무 및 책임사항에 관해서는 전기통신사업법 기타 대한민국의 관련 법령과 상관습에 의합니다.
			
제2조 (이용자의 정의)
1. "이용자"란 “CLASS33”에 접속하여 본 약관에 따라 “과외가” 회원으로 가입하여 “CLASS33”이 제공하는 서비스를 받는 자를 말합니다.
			
제3조 (회원 가입)
1. 이용자가 되고자 하는 자는 “CLASS33”이 정한 가입 양식에 따라 회원정보를 기입하고 등록하기 버튼을 누르는 방법으로 회원 가입을 신청합니다.
2. “CLASS33”은 제1항과 같이 회원으로 가입할것을 신청한 자가 다음 각 호에 해당하지 않는 한 신청한 자를 회원으로 등록합니다.
1) 가입신청자가 본 약관 제6조 2항에 의하여 이전에 회원자격을 상실한 적이 있는 경우.
다만, 6조 1항에 의한 회원자격 상실 후 6개월이 미 경과한 자로서 “CLASS33”의 회원 재가입 승낙을 얻은 경우에는 예외로 합니다.
2) 등록 내용에 허위, 기재누락, 오기가 있는 경우
3) 기타 회원으로 등록하는 것이 “CLASS33”의 기술상 현저히 지장이 있다고 판단되는 경우
3. 회원가입계약의 성립시기는 “CLASS33”의 승낙이 가입신청자에게 도달한 시점으로 합니다.
4. 회원은 제1항의 회원정보 기재 내용에 변경이 발생한 경우, 즉시 변경사항을 정정하여 기재하여야 합니다.

제4조 (서비스의 제공 및 변경)
1. “CLASS33”은 이용자에게 아래와 같은 서비스를 제공합니다.
1)“CLASS33”내에서 제공 하는 모든 서비스
2) 기타 “CLASS33”이 자체 개발하거나 다른 회사와의 협력계약 등을 통해 회원들에게 제공할 일체의 서비스
2. “CLASS33”은 그 변경될 서비스의 내용 및 제공일자를 본 약관 제7조에서 정한 방법으로 이용자에게 통지하여 서비스를 변경하여 제공할 수 있습니다.

제5조 (서비스의 중단)
1. “CLASS33”은 컴퓨터 등 정보통신설비의 보수점검·교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있고, 새로운 서비스로의 교체 기타 “CLASS33”이 적절하다고 판단하는 사유에 기하여 현재 제공되는 서비스를 완전히 중단할 수 있습니다.
2. 제1항에 의한 서비스 중단의 경우에는 “CLASS33”은 제7조1항에서 정한 방법으로 이용자에게 통지합니다. 다만, “CLASS33”이 통제할 수 없는 사유로 인한 서비스의 중단(천재지변, 시스템 관리자의 고의과실이 없는 디스크 장애, 시스템 다운 등)으로 인하여 사전 통지가 불가능한 경우에는 그러하지 아니합니다.

제6조 (이용자 탈퇴 및 자격 상실 등)

1. 이용자는 “CLASS33”에 언제든지 자신의 회원 탈퇴를 요청할 수 있으며 “CLASS33”은 위 요청을 받은 즉시 해당 이용자의 탈퇴 신청을 받아드리며 지식포인트는 포기하는 것으로 간주하고 개인의 등록자료는 삭제되며 6개월 내 재가입시 기본정보를 보관하며 6개월 경과 후 회원의 모든정보가 소멸됩니다.
2. 이용자가 다음 각호의 사유에 해당하는 경우, “CLASS33”은 이용자의 회원자격을 적절한 방법으로 제한 및 정지, 상실시킬 수 있습니다.
1) 가입 신청 시에 허위 내용을 등록한 경우
2) 다른 사람의 "서비스"이용을 방해하거나 그 정보를 도용하는 등 전자거래질서를 위협하는경우
3) 서비스를 이용하여 법령과 본 약관이 금지하거나 공서양속에 반하는 행위를 하는 경우
3. “CLASS33”이 이용자의 회원자격을 상실시키기로 결정한 경우에는 회원등록을 말소합니다. 이 경우 이용자인 회원에게 회원등록 말소 전에 이를 통지하고, 소명할 기회를 부여합니다.

제7조 (이용자에 대한 통지)
1. “CLASS33”이 특정 이용자에 대한 통지를 하는 경우 “CLASS33”에 가입시 기재된 메일로 할 수 있습니다.
2. “CLASS33”이 불특정다수 이용자에 대한 통지를 하는경우 칠일이상 “CLASS33” 공지사항에 게시함으로써 개별 통지에 갈음할 수 있습니다.

제8조 (이용자의 개인정보보호)
“CLASS33”은 관련법령이 정하는 바에 따라서 이용자 등록정보를 포함한 이용자의 개인정보를 보호하기 위하여 노력합니다. 이용자의 개인정보보호에 관해서는 관련법령 및 “CLASS33”이 정하는 개인정보보호정책에 정한 바에 의합니다.

제9조 (“CLASS33”의 의무)
1. “CLASS33”은 법령과 본 약관이 금지하거나 공서양속에 반하는 행위를 하지않으며 본 약관이 정하는 바에 따라 지속적이고, 안정적으로 서비스를 제공하기 위해서 노력합니다.
2. “CLASS33”은 이용자가 안전하게 인터넷 서비스를 이용할 수 있도록 이용자의 개인정보(신용정보 포함)보호를 위한 보안 시스템을 구축합니다.
3. “CLASS33”은 이용자가 원하지 않는 영리목적의 광고성 전자우편을 발송하지 않습니다.

제10조 (“CLASS33”의 면책)
1. “CLASS33”은 천재지변 또는 이에 준 하는 불가항력으로 인하여 서비스를 제공할수 없는 경우에는 서비스 제공에 관한 책임이 면제됩니다.
2. “CLASS33”은 “이용자”의 귀책사유로인한 서비스 이용의 장애에대하여 책임을 지지 않습니다.
3. “CLASS33”은 “이용자”가 서비스를 이용하여 기대하는 수익을 상실한 것에 대하여 책임을 지지 않으며 그밖에 서비스를 통하여 얻은 자료로 인한 손해에 관하여 책임을 지지 않습니다.
4. “CLASS33”은 “이용자”가 서비스에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등 내용에 관하여는 책임을 지지 않습니다.
5. “CLASS33”은 과외제자와 선생님의 독립적이고 자발적인 의사에 따라 과외를신청할 수 있도록 사전에 동의된 정보와 방식에 의거하여 중개업무를 하는 것이므로 회원의 의도적인 조작 또는 허위정보에 대해 책임을 지지 않는다.
6.  “CLASS33”은 과외선생님이 과외비를 선불로 지급 받은 후 과외수업을 이행하지 않거나 일방적으로 계약을 파기하는 경우, 나머지 기간에 대한 과외비를 회사가 선생님회원 대신 지급하지 않는다. 또한 과외연결 후에 발생하는 학생과 선생님의 분쟁에 관하여 책임지지 않는다.
7. “CLASS33”은 회원이 등록한 모든 자료의 경우 일정기간동안 저작권을 갖지 않으며, 이 기간 동안의 저작권은 당연히 등록한 회원 본인의 것이므로 회사는 저작권에 관한 어떠한 책임도 지지 아니한다.
8. “CLASS33”은 회사자간의 분쟁 해결에 적극적으로 참여하여, 필요한 경우 본 약관을 일방적으로 위배한 회원에 대하여 법률적인 고소,고발조치를 취하거나, 피해자가 고소,고발조치를 취하는데 모든 도움을 제공한다

제11조 (이용자의 ID 및 비밀번호에 대한 의무)
1. “CLASS33”은 관계법령, "개인정보보호정책"에 의해서 그 책임을 지는 경우를 제외하고, 자신의 ID와 비밀번호에 관한 관리책임은 각 이용자에게 있습니다.
2. 이용자는 자신의 ID 및 비밀번호를 제3자에게 이용하게 해서는 안됩니다.
3. 이용자는 자신의 ID 및 비밀번호를 도난당하거나 제3자가 사용하고 있음을 인지한 경우에는 바로 “CLASS33”에 통보하고 “CLASS33”의 안내가 있는 경우에는 그에 따라야 합니다.


본 약관은 2022. 04. 29. 부터 적용합니다.
			</textarea>
			<div class= "checkbox">
			<input type="checkbox" id="checkbox" name="checkbox1" required="required" > 약관을 충분히 이해하였으며 이에 동의합니다</p>
			</div><br>
			<h3 class="main-h3"> ■ 개인정보 수집 및 이용에 대한 안내(필수) </h3>
			<table border="1">
				<tr>
					<th>개인정보 수정</th>
					<th>수집항목</th>
					<th>보유기간</th>
				</tr>
				<tr>
					<td>이용자 식별 및 본인 여부 확인</td>
					<td>이름, 이메일, 전화번호</td>
					<td>회원탈퇴시 까지</td>
				</tr>	
			</table>
		<div class= "checkbox">
		<input type="checkbox" id="checkbox" name="checkbox2" required="required"> 개인정보 수집 및 이용(필수)에 대한 안내를 이해하였으며 동의합니다.</p> 
		</div>
		<input class="button_area" type="submit" value="확인" >
		</div></div>
	</form>
	</main>
		<!-- ------------------------------------------------------	Main 끝 ---------------------------------------------------------->		    
		    
		    
<!-- ------------------------------------------------------	Main 끝 ---------------------------------------------------------->		    
		    
		    <footer>
		    	<div id="footerContent">
		    		CLASS 33(주)<br>
		    		대표자 : 이대 3조<br>
		    		사업자등록번호 : 333-33-33333<br>
		    		주소 : 서울특별시 마포구 대흥동 12-20 301호<br>
		    		E-Mail : class33@class33.co.kr<br>
		    		Copyrights © Class 33.<br>
		    	</div>
		    	
		    	<div id="btnTeacherLogin">
		    		<a href="#">교사 센터 바로가기</a>
		    		<img src="images/icons/front_chevron-right.svg">
		    	</div>
		    
		    </footer>
    	</div>
    	<!-- margin_wrap 끝 -->
    </div>
    <!-- wrap 끝 -->
</body>
</html>	
