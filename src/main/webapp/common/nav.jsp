<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
    <header class="navbar navbar-expand-lg bg-body-tertiary shadow-sm ps-5 pe-5" >
        <div class = "container-fluid">
            <a href="${root }"><img class = 'col-4 ms-4' src = '${ root }/img/nav_logo.png'></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <nav class="collapse navbar-collapse mr-1" id="navbarNav">
                <ul class="navbar-nav ms-auto me-2 grid gap-4">
                    <li class = "navbar-item">
                        <a href = "${root}/mytour/mytour_map.jsp" class="nav-link active fs-5" aria-current="page" href="#">나만의 여행계획</a>
                    </li>
                    <li class = "navbar-item">
                        <a href = "${root}/board?action=list" class="nav-link active fs-5" aria-current="page" href="#">게시판</a>
                    </li>
                    <c:if test="${empty sessionScope.userinfo}">
                    <li id = 'nav-login' class = "navbar-item">
                        <a class="nav-link active fs-5" aria-current="page" href="${root}/user/login.jsp">로그인</a>
                    </li>
                    <li id = 'nav-signup' class = "navbar-item">
                        <a  href="${root}/user?action=mvjoin" class="nav-link active fs-5" aria-current="page" href="#">회원가입</a>
                    </li>
                    </c:if>
                    <!--로그인 하면 display 변경해주기-->
					<c:if test="${not empty sessionScope.userinfo}">
                    <li id = 'nav-logout' class = "navbar-item">
                        <a class="nav-link active fs-5" aria-current="page" href="${root}/user?action=logout" >로그아웃</a>
                    </li>
                    <li id = 'nav-mypage' class = "navbar-item">
                        <a href="${root}/user/myPage.jsp" class="nav-link active fs-5" aria-current="page">마이페이지</a>
                    </li>
                    </c:if>
                </ul> 
            </nav>
        </div>
    </header>