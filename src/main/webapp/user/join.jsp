<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
      <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <p class="text-end small">이미 계정이 있으신가요?<a href="${root}/user?action=login" class="text-decoration-none text-primary"> 로그인</a></p>
    </div>
    <div class="rounded-top p-0 col-sm-6 col-md-6 col-lg-4 container centered mt-5">
        <a class="text-decoration-none  fw-bolder fs-3 text-start mt-0" href="#">저기요</a>
    </div>
    <form id="userInfo" action="${root}/user?action=join" method="post">
      <div class="border shadow rounded p-3 col-sm-6 col-md-6 col-lg-4 container centered mt-3 mb-3">
          <div class="justify-content-center">
              <label for="inputUsername" class="form-label">아이디</label> <span class="text-warning">*</span>
              <input type="id" class="form-control mb-2" id="inputUsername" name="userid" placeholder="아이디 입력" required>
            </div>
            <div class="justify-content-center">
              <label for="inputPassword" class="form-label">비밀번호</label> <span class="text-warning">*</span>
              <input type="password" class="form-control mb-2" id="inputPassword" name="userpwd" placeholder="비밀번호 입력" required>
            </div>
            <div class="justify-content-center">
              <label for="inputPassword2" class="form-label">비밀번호 확인</label> <span class="text-warning">*</span>
              <input type="password" class="form-control" id="inputPassword2" placeholder="비밀번호 재입력" required>
            </div>
      </div>
      <div class="border shadow rounded p-3 col-sm-6 col-md-6 col-lg-4 container centered">
          <div class="justify-content-center">
              <label for="name" class="form-label">이름</label> <span class="text-warning">*</span>
              <input type="text" class="form-control mb-2" id="name" name="username" placeholder="성 이름" required>
          </div>
          <div class="justify-content-center">
            <label for="inputEmail" class="form-label">이메일</label> <span class="text-warning">*</span>
            <input type="text" class="form-control mb-2" id="inputEmail" name="useremail" placeholder="user@domain.com" required>
          </div>
          <div class="justify-content-center">
            <label for="inputCity" class="form-label">주소</label> <span class="text-warning">*</span>
            <input type="text" class="form-control mb-2" id="inputCity" name="userAddr" placeholder="시도 / 군구" required>
          </div>
          <div class="justify-content-center">
            <label for="inputGender" class="form-label" name="userAddr" required>성별</label> <span class="text-warning">*</span>
            <select id="inputGender" class="form-select">
              <option selected>선택해주세요</option>
              <option value="male">남자</option>
              <option id="woman" value="female">여자</option>
            </select>
          </div>
          <div class="justify-content-center">
            <!-- <div class="form-check">
              <input class="form-check-input" type="checkbox" id="gridCheck">
              <label class="form-check-label" for="gridCheck">
                Check me out
              </label>
            </div> -->
          </div>
      </div>
    <div class="rounded-top p-0 col-sm-6 col-md-6 col-lg-4 container centered mt-5">
        <div class="row justify-content-center ms-auto me-auto">
            <button type="submit" class="btn btn-primary">가입하기</button>
        </div>
    </div>
  </form>
    <div class="navbar fixed-bottom navbar-expand navbar-dark bg-white container-fluid justify-content-center p-3">
        <div class="row">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link text-primary fw-bolder fs-4 text-center mt-0" href="#">Excuse Me</a>
            </li>
            <li class="nav-item ">
              <a class="nav-link text-dark text-center mt-2" href="#">개인정보처리방침</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-dark text-center mt-2" href="#">이용약관</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-muted text-center mt-2" href="#">&copy;SSAFY 광주 4반 박지훈 이지표</a>
            </li>
          </ul>
        </div>
    <script type="text/javascript" src="./js/signup.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>