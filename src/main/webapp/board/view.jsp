<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>게시글 상세보기</title>
</head>
<body>
<%@ include file="/common/nav.jsp" %>
    <div class="container mt-5">
        <h2 id="post-title">${ board.title }</h2>
        <p id="post-content">${ board.contents }</p>
        <p id="post-date">${ board.register_time }</p>
        <a href="${root}/board?action=list" class="btn btn-primary">목록으로 돌아가기</a>
        <a href="${root}/board?action=mvModify&id=${board.board_id}" class="btn btn-success">수정</a>      
        <a href="${root}/board?action=delete&id=${board.board_id}" class="btn btn-warning">삭제</a>
        
    </div>
        <footer class="navbar bg-dark nav justify-content-center pt-4 pb-4" data-bs-theme="dark">
        <p class = "text-light">© SSAFY 광주_4반 박지훈 이지표</p>
    </footer>
</body>
</html>