<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 로그인 | CLASS 33</title>
    <link rel="stylesheet" type="text/css" href="assets/css/TchLogin.css">
</head>
<body>
    <div class="authentication-inner">
        <div class="card">
            <div>
                <img src="images/admin_logo2.svg">
            </div>
            <div>
                <h3>Welcome to Class 33👋</h3>
                <p>특별한 교육 매칭을 시작하세요!</p>
            </div>
            <div>
                <form action="tchLoginPro.do" method="post">
                    <input class="input" type="text" name="tch_id" placeholder="아이디" required="required">
                    <input class="input" type="password" name="password" placeholder="비밀번호" required="required">
                    <input class="button_area" type="submit" value="로그인">
                    <div class="joingo">
                        아직 회원이 아니신가요? <a href="tchJoinForm.do">회원가입하기</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>