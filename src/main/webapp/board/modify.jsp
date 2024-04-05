<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="${root}/css/board_write.css">
    <title>저기요 EXCUSE ME</title>
</head>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css" />
<style>
    /* 폰트 설정 start */
    /* 만약 폰트 크기를 키웠는데 적용이 안된다면 ? font-weight 크기 100단위로 조절해주기 */
    *{
        font-family: 'Pretendard-Regular';
        margin:0;
        padding:0;
    }
    main{
        min-height: 100vh;
    }
    .container-fluid{
        max-width:1280px;
        display: flex;
        
    }
    
    /* 폰트 설정 end */
    
</style>
<body>
	<%@ include file="/common/nav.jsp" %>
	<h1>글 수정하기</h1>
	
	<form method="POST" id="form-modify" action="">
		<input type="hidden" name="action" value="modify"/>
		<input type="hidden" name="id" value="${board.board_id}"/>
		
		<input type="text" id="title" name="title" value="${ board.title }" plaeholder="제목을 입력하세요"/>
		<textarea id="contents" name="contents">${board.contents}</textarea>
		<button type="button" id="btn-form">수정하기</button>
	</form>
	    <footer class="navbar bg-dark nav justify-content-center pt-4 pb-4" data-bs-theme="dark">
        <p class = "text-light">© SSAFY 광주_4반 박지훈 이지표</p>
    </footer>
</body>

<script>
	document.querySelector("#btn-form").addEventListener("click", function(){
		if(!document.querySelector("#title").value){
			alert("제목 입력!!!");
			return;
		} else if(!document.querySelector("#contents").value){
			alert("내용 입력!!!");
			return;
		} else{
			let form = document.querySelector("#form-modify");
			form.setAttribute("action", "${root}/board");
			form.submit();
		}
	});
</script>
</html>