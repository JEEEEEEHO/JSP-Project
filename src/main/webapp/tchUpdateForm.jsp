<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html
  lang="utf-8"
  class="light-style layout-menu-fixed"
  dir="ltr"
  data-theme="theme-default"
  data-assets-path="assets/"
  data-template="vertical-menu-template-free"
>
  <head>
    <meta charset="utf-8" />
    
    <title>개인정보 수정 | Class 33</title>
	
    <jsp:include page="tch_head.jsp"></jsp:include>
    
    <script type="text/javascript">
    	function chk() {
    		if (!frm.phone.value) {
				alert("전화번호를 입력하세요.");
				frm.phone.focus();
				return false;
			}
			if (!frm.birth.value) {
				alert("생년월일을 입력하세요.");
				frm.birth.focus();
				return false;
			}
			return true;
		}
    </script>
    
    
  </head>

  <body>
    <!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <jsp:include page="tch_menu.jsp"></jsp:include>

        <!-- Layout container -->
        <div class="layout-page">

          <!-- Content wrapper -->
          <div class="content-wrapper">

            <!-- Content -->
            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4">개인정보 수정</h4>
                  <div class="card">
                    <!-- Account -->
                    <div class="card-body">
                      <form action="tchUpdatePro.do" name="frm" id="formAccountSettings" method="POST" onsubmit="return chk()">
						<div class="mb-3 col-md-6">
                          <label for="exampleFormControlReadOnlyInput1" class="form-label">아이디</label>
                       	  <input
                          	type="text"
                          	name="tch_id" 
                          	value="${teacher.getTch_id()}" 
                          	class="form-control"
                          	id="exampleFormControlReadOnlyInput1"
                          	readonly
                          />
                      	</div>
                      	  
						<div class="mb-3 col-md-6">
                          <label for="exampleFormControlReadOnlyInput1" class="form-label">이름</label>
                       	  <input
                          	type="text"
                          	name="name" 
                          	value="${teacher.getName()}" 
                          	class="form-control"
                          	id="exampleFormControlReadOnlyInput1"
                          	readonly
                          />
                      	</div>

                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="phone">전화번호</label>
                          <input
                          type="text"
                          id="phone"
                          name="phone"
                          value="${teacher.getPhone()}"
                          class="form-control"
                          placeholder="'-' 제외하고 입력"
                          pattern="\d{11}" 
                          title="'-' 제외하고 입력"
                          maxlength="11"
                          />
                        </div>

                        <div class="mb-3 col-md-6">
                          <label for="birth" class="form-label">생년월일</label>
                          <input
                          type="text"
                          id="birth"
                          name="birth"
                          value="${teacher.getBirth()}"
                          class="form-control"
                          placeholder="8자리 입력 (19970205)" 
                          pattern="\d{8}" 
                          title="생년월일 8자리 입력"
                          maxlength="8"
                          />
                        </div>

                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="gender">성별</label>
                          <select id="gender" class="select2 form-select">
                            <c:if test="${teacher.getGender() == 0 || teacher.getGender() == null}">
	                            <option value="${teacher.getGender()}" selected="selected">남</option>
	                            <option value="1">여</option>
                            	
                            </c:if>
                            <c:if test="${teacher.getGender() == 1}">
	                            <option value="0">남</option>
	                            <option value="${teacher.getGender()}" selected="selected">여</option>
                            	
                            </c:if>
                          </select>
                        </div>

                        <div class="mb-3 col-md-6">
                          <label class="form-label" for="formFileMultiple">이력서 <span class="text-muted">(PDF파일 형식으로 업로드해주세요)</span></label>
                            <input 
                            type="file" 
                            name="resume" 
                            <%-- src="${context}/fileSave/profile/${teacher.getResume()}"  --%>
                            class="form-control" 
                            id="formFileMultiple"
                            multiple /> <!-- 여기서 경로는 이클립스의 경로? sql의 경로?  context= /J20220403 /js/pdfjs/web/ pdf 업로드 하면 올라가는 이름은?  -->
                        </div>
                        <div class="mt-5">
                          <button type="reset" class="btn btn-outline-secondary">취소</button>
                          <button type="submit" class="btn btn-primary me-2">수정 완료</button>
                        </div>


                      </form>
                    </div>
                    <!-- /Account -->
                  </div>
            </div>
            <!-- / Content -->

            <div class="content-backdrop fade"></div>
          </div>
          <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
      </div>

      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->

    <jsp:include page="tch_footer.jsp"></jsp:include>
  </body>
</html>

