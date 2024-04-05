<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <form id="userInfo" action="${ root }/user?action=findpw" method="POST">
        <div class="border shadow rounded mt-5 p-5 col-sm-6 col-md-6 col-lg-4 container centered">
            <div class="justify-content-center">
                <label for="findpwid" class="form-label">아이디로 비밀번호 찾기</label>
                <input type="id" class="form-control mb-2" id="findpwid" name="userid" placeholder="아이디를 입력하세요." required>
            </div>
        </div>
        <div class="rounded-top p-0 col-sm-6 col-md-6 col-lg-4 container centered mt-5">
            <div class="row justify-content-center ms-auto me-auto">
                <button type="submit" class="btn btn-primary" id="findbtn">비밀번호 찾기</button>
            </div>
        </div>
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>