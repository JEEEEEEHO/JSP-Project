<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>비밀번호 변경 | Class 33</title>

    <jsp:include page="tch_head.jsp"></jsp:include>

    <!-- Helpers -->
    <script src="assets/vendor/js/helpers.js"></script>
	    <script type="text/javascript">
    	function chk(){
    		if(!frm.oldPw.value){
    			alert("기존 비밀번호를 확인해주세요");
    			frm.oldPW.focus();
    			return false;
    		}
    		if(frm.newPw.value != frm.newPwCheck.value){
    			alert("변경할 비밀번호가 일치하지않습니다.");
    			frm.newPwCheck.focus();
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
              <h4 class="fw-bold py-3 mb-4">비밀번호 변경</h4>
                  <div class="card">
                    <!-- Account -->
                    <div class="card-body">
                      <form action="tchPwChangePro.do" id="formAccountSettings" name="frm" method="POST" onsubmit="return chk()">

                        <div class="form-password-toggle mb-3 col-md-6">
                          <label class="form-label" for="basic-default-password12">기존 비밀번호</label>
                          <div class="input-group">
                            <input
                              type="password"
                              name="oldPw"
                              class="form-control"
                              id="basic-default-password12"
                              placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;"
                              aria-describedby="basic-default-password2"
                            />
                            <span id="basic-default-password2" class="input-group-text cursor-pointer"
                              ><i class="bx bx-hide"></i
                            ></span>
                          </div>
                        </div>
                        <div class="form-password-toggle mb-3 col-md-6">
                          <label class="form-label" for="basic-default-password12">변경할 비밀번호</label>
                          <div class="input-group">
                            <input
                              type="password"
                              name="newPw"
                              class="form-control"
                              id="basic-default-password12"
                              placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;"
                              aria-describedby="basic-default-password2"
                            />
                            <span id="basic-default-password2" class="input-group-text cursor-pointer"
                              ><i class="bx bx-hide"></i
                            ></span>
                          </div>
                        </div>
                        <div class="form-password-toggle mb-3 col-md-6">
                          <label class="form-label" for="basic-default-password12">비밀번호 확인</label>
                          <div class="input-group">
                            <input
                              type="password"
                              name="newPwCheck"
                              class="form-control"
                              id="basic-default-password12"
                              placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;"
                              aria-describedby="basic-default-password2"
                            />
                            <span id="basic-default-password2" class="input-group-text cursor-pointer"
                              ><i class="bx bx-hide"></i
                            ></span>
                          </div>
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
