<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="en">
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
    <main>
        <div class="main">
            <div class = 'container-1'>
                <h3>관광지 후기 작성</h3>
            </div>
            <form method = "POST" id="form-board" action="">
            <input type="hidden" name="action" value="write" />
	            <div class = 'container-2'>
	                <div class= "title-container">
	                    <p class = 'field-name'>제목</p>
	                    <input id = 'title' class = 'title-field' name="title" type = 'text'>
	                </div>
	                <div class = "content-container">
	                    <p class = 'field-name'>내용</p>
	                    <textarea id = 'contents' name="contents" class = 'content-field'></textarea>
	                </div>
	            </div>
	            <div class = 'button-container'>
	                <a id = 'btn-enroll' class = 'button'>글 등록하기</a>
	            </div>
            </form>
        </div>
    </main>
    <footer class="navbar bg-dark nav justify-content-center pt-4 pb-4" data-bs-theme="dark">
        <p class = "text-light">© SSAFY 광주_4반 박지훈 이지표</p>
    </footer>
</body>

<!-- <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F    7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous">
</script> -->

<script>
document.querySelector("#btn-enroll").addEventListener("click", function () {
    if (!document.querySelector("#title").value) {
      alert("제목 입력!!");
      return;
    } else if (!document.querySelector("#contents").value) {
      alert("내용 입력!!");
      return;
    }
     let form = document.querySelector("#form-board");
     form.setAttribute("action", "${root}/board");
     form.submit();
  });
</script>

<!-- <script src = "../js/board_write.js"></script> -->
</html>