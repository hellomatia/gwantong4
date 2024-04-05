<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

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
    <script src="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.css" />
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css" />
    <link rel="stylesheet" href="${root}/css/main.css">
    <link rel="stylesheet" href="${root}/mytour/css/mytour.css" />

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
    <title>저기요 EXCUSE ME</title>
</head>
<body>
	<%@ include file="/common/nav.jsp" %>
    <main class="map_wrap">
        <div id="map" style="width:100%;height:100%;margin:auto;position:relative;overflow:hidden;"></div>

        <div id="menu_wrap" class="bg_white">
            <div class="option">
                <div>
                    <form onsubmit="searchPlaces(); return false;">
                        키워드 : <input type="text" value="이태원 맛집" id="keyword" size="15"> 
                        <button type="submit">검색하기</button> 
                    </form>
                </div>
            </div>
            <hr>
            <ul id="placesList">
            </ul>
            <div id="pagination"></div>
        </div>

        <div id="placesListContainer" class="bg_white">
            <ul id="clickedPlacesList"></ul>
        </div>    
    </main>

    <footer class="navbar bg-dark nav justify-content-center pt-4 pb-4" data-bs-theme="dark">
        <p class = "text-light">© SSAFY 광주_4반 박지훈 이지표</p>
    </footer>
</body>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=acd23fc0b913a085a3ad102649e3b5dc&libraries=services"></script>
<script type="text/javascript" src="${root}/mytour/js/mytour.js"></script>
<script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F    7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous">
</script>
<script type = "module" src = "${root}/js/main.js"></script>
<script>
    AOS.init();
</script>
</html>
