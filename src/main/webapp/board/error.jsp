<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>잘못된 접근입니다.</title>
</head>
<body>
	<h1>잘못된 접근입니다.</h1>
	<a href="${root}/board?actioin=list">목록으로 돌아가기</a>
</body>
</html>