<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css" />
    <link rel="stylesheet" href="./css/mypage.css">
</head>
<body>
<%@ include file="/common/nav.jsp" %>
    <main>
        <div class="container centered pt-5 mt-5">
            <div class="row justify-content-center pt-5 mt-5 ms-3">
                <span class="h2 text-center text-dark fw-bolder">내 정보 수정</span>
                <form action="${root}/user" method="post" class="w-50">
                    <input type="hidden" name="action" value="modify">
                    <table class="rounded shadow table table-bordered border-dark" id="userinfo">
                        <tbody>
                          <tr>
                            <th class="w-25 table-primary" scope="row">ID</th>
                            <td><input type="text" name="userid" value="${userinfo.userId}" readonly class="form-control-plaintext"></td>
                          </tr>
                          <tr>
                            <th class="table-primary" scope="row">비밀번호</th>
                            <td><input type="text" name="username" value="${userinfo.userName}" class="form-control"></td>
                          </tr>
                          <tr>
                            <th class="table-primary" scope="row">이름</th>
                            <td><input type="password" name="userpwd" value="${userinfo.userName}" class="form-control"></td>
                          </tr>
                          <tr>
                            <th class="table-primary" scope="row">e-mail</th>
                            <td><input type="email" name="useremail" value="${userinfo.userEmail}" class="form-control"></td>
                          </tr>
                          <tr>
                            <th class="table-primary" scope="row">주소</th>
                            <td><input type="text" name="useraddr" value="${userinfo.userAddr}" class="form-control"></td>
                          </tr>
                          <tr>
                            <th class="table-primary" scope="row">성별</th>
                            <td>
                                <select name="usergender" class="form-select">
                                    <option value="M" ${userinfo.userGender == 'male' ? 'selected' : ''}>남자</option>
                                    <option value="F" ${userinfo.userGender == 'female' ? 'selected' : ''}>여자</option>
                                </select>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                      <div class="row justify-content-center p-0 ">
                          <button type="submit" class="w-25 bg-warning" id="updateuser">수정</button>
                      </div>
                </form>
            </div>
        </div>

    </main>
    <script type="text/javascript" src="./js/mypage.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
