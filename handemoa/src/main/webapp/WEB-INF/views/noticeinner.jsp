<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>한데모아 - 공지사항</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/index.css'>
<link rel='stylesheet' type='text/css' href='css/notice.css'>
<link rel='stylesheet' type='text/css' href='css/noticeinner.css'>
<script src="/jquery-3.6.0.min.js"></script>
<script src='/js/index.js'></script>
<script type="text/javascript">
	$(document).ready(function(){
	//jquery code
		
	});
</script>
</head>

<body>
 <div class="container">
        <!-- 네비게이션 start -->
        <div id="nav_area">
            <div id="nav_space">
                <div id="logo">
                    <a href="/handemoa"> 
                        <img src="css/images/logo.png" />
                    </a>
                </div>
                <div id="nav_profile">
                   <div id="nav_profile_img">
                <c:choose>
                   <c:when test="${isLogOn == true && member!= null}">
                    
                    <img src="css/images/${member.profileimg}" style=border-radius:80%; width="100%"/>
                    </c:when>
                    <c:otherwise>
                     <img src="css/images/login_before.png" />
                    </c:otherwise>
                    </c:choose>
               </div>
               
                   <c:choose>
                   <c:when test="${isLogOn == true && member!= null}">
                    
                    <a href="/profile?nickname=${member.nickname}"><h3 style="text-align: center; color">${member.nickname} 님</h3></a>
                    <div style="display: flex;">
                    <button id="nav_login_btn" onclick="location.href='/logout'" style="color: white; background-color: #E02C1B">로그아웃</button>
                    <button id="nav_login_btn" onclick="location.href='/memberedit'" style="color: white; background-color: gray; margin-left: 10px; font-size: 5px;">회원정보수정</button>
                    
                    </div>
                    </c:when>
                    <c:otherwise>
                    <button id="nav_login_btn" onclick="location.href='/login'">로그인</button>
                    </c:otherwise>
                    </c:choose>
                </div>
                <div id="nav_list">
                    <div class="nav_list_area">
                        <a href="/bookmark"> <!-- 해당 링크 이동 -->
                            <h4>북마크</h4></a>
                    </div>
                    <div class="nav_list_area">
                        <a href="/ranking?catedetailcode=10&page=1"> <!-- 해당 링크 이동 -->
                            <h4>강의랭킹</h4></a>
                    </div>
                    <div class="nav_list_area">
                        <a href="/community?catedetailcode=8&page=1"> <!-- 해당 링크 이동 -->
                            <h4>커뮤니티</h4></a>
                    </div>
                    <div class="nav_list_area">
                        <a href="/notice"> <!-- 해당 링크 이동 -->
                            <h4>공지사항</h4></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- 네비게이션 end -->
        <div id="content_container" />
            <div id="content_container_space" />
                <div id="search_area">
                    <div id="search_box">
                        <input id="search" placeholder="게시글 통합 검색" type="search"/>
                    </div>
                    <div id="search_icon_area">
                        <a href="#"><img src="css/images/search_icon.png"/></a>
                    </div>
                    <div id="search_box_space"></div>
                </div>
                <div id="inner_space"></div>
                
       <div id="content">
						
				<div class="notice_name">
                      <h1>공지사항</h1>
                </div>
                    	
               <div id="content_box">	
					<!-- 글제목 -->
					<div class="noticeinner_container">
						<div class="title">${noticedto.title}</div>
					</div>
					<!-- 글내용 -->
					<div class="noticeinner_container">
						<div class="content">${noticedto.content}</div>
					</div>
					
					<div id="notice_back_btn_area">
	                    <div id="notice_back_btn">
	                        <input type="button" id="notice_btn" onclick="history.back(-1);" style="cursor:pointer;" value="뒤로가기"> 
	                    </div>
	                </div>
					
					
				</div>
					
                	
   	    </div>
</body>
</html>