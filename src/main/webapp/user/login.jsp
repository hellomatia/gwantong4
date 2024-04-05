<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<c:forEach items="${cookie}">
    <c:if test="${cookie.name eq 'tripid'}">
        <c:set var="saveid" value="${cookie.value}"></c:set>
        <c:set var="checked" value="checked"></c:set>
    </c:if>
</c:forEach>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <div class="container centered">
    <div class="row justify-content-center">
      <div class="col-sm-10 col-md-8 col-lg-6 col-xl-5">
        <a href="">
        <div class="row justify-content-center mt-5">
          <img src="${root}/img/nav_logo.png" class="img-thumnail" alt="로고" style="width: 200px; height: auto;">
        </div>
        </a>
        <div class="card shadow">
          <div class="card-body">
            <form id="loginForm" action="${root}/user?action=login" method="post">

              <div class="form-group">
                <label for="username"></label>
                <input type="text" class="form-control" name="userid" id="username" placeholder="아이디 입력" required value="${saveid}">
              </div>

              <div class="form-group mb-3">
                <label for="password"></label>
                <input type="password" class="form-control" name="userpwd" id="password" placeholder="비밀번호 입력" required>
              </div>

              <div class="form-check mb-3">
                <label class="form-check-label">
                  <input class="form-check-input" type="checkbox" name="saveid" ${checked}/> 아이디 저장
                </label>
              </div>

              <div class="row justify-content-center ms-auto me-auto">
                <button type="submit" class="btn btn-primary" id="login">여행 떠나기!</button>
              </div>
            </form>
          </div>
        </div>
        <div class="row justify-content-center mt-4">
          <ul class="list-group list-group-horizontal justify-content-center">
            <li class="list-group-item border-0 p-0 me-3">
              <a href="${root}/user?action=mvfindpw" class="text-decoration-none text-muted small" onclick="javascript:findpw()">비밀번호 찾기</a>
            </li>
            <div class="vr no-gutters"></div>
            <div class="vr"></div>
            <li class="list-group-item border-0 p-0 ms-3">
              <a href="${root}/user?action=mvjoin" class="text-decoration-none text-muted small">회원가입</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="navbar fixed-bottom navbar-expand-sm navbar-dark bg-white container-fluid justify-content-center p-5">
    <div class="row">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link text-primary fw-bolder fs-4 text-center mt-0" href="${root}/index.jsp">Excuse Me</a>
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
  <script type="text/javascript" src="${root}/js/login.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
