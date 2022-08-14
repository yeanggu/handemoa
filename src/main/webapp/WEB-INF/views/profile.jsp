<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>한데모아 - 프로필</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/index.css'>
<link rel='stylesheet' type='text/css' href='css/paging.css'>
<link rel='stylesheet' type='text/css' href='css/profile.css'>
<script src='/js/profile.js'></script>
<script src="/jquery-3.6.0.min.js"></script>
<link rel='stylesheet' type='text/css' href='css/alarm.css'>
<script src='/js/alarm.js'></script>
<script src='/js/index.js'></script>
<script type="text/javascript">

	//검색
	$(document).on('click', '#btnSearch', function(e){
		e.preventDefault();
		var url = "/postmanage";
		url = url + "?searchType=" + $('#searchType').val();
		url = url + "&keyword=" + $('#keyword').val();
		location.href = url;
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
                    <button id="nav_login_btn" onclick="location.href='/logout'" style="color: white; background-color: #ce4764;">로그아웃</button>
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
                    
                    
                    
                    <c:choose>
						<c:when test="${isLogOn == true && member!= null}">

							<div class="nav_list_area">
								<div class="handemore_button">
									<a href="http://localhost:3000/note" id="handemore_font">
										HANDEMORE > </a>
								</div>
							</div>

						</c:when>
						<c:otherwise>
							<div class="nav_list_area">
								<div class="handemore_button">
									<a href="/login" id="handemore_font"> HANDEMORE > </a>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
                </div>
            </div>
        </div>
        <!-- 네비게이션 end -->
        <div id="content_container">
            <div id="content_container_space">
                <div id="search_area">
                    <div id="search_box">
                        <input id="search" placeholder="게시글 통합 검색" type="search"/>
                    </div>
                    <div id="search_icon_area">
                        <a href="#"><img src="css/images/search_icon.png"/></a>
                    </div>
                    <div id="search_box_space"></div>
                </div>
                
                <div id="content">
                
	                <div id="introduce_content_area">
	                	<div id="introduce_content_box">
			                    <c:choose>
			                    <c:when test="${isLogOn == true && member!= null}">
			                    <img src="css/images/${memberlist.profileimg}" style="border-radius: 80%;" width=110px;/>
			                    </c:when>
			                    <c:otherwise>
			                     <img src="css/images/login_before.png" />
			                    </c:otherwise>
			                    </c:choose>
			                <div id="introduce_nickname">${pagedto.nickname}</div>
						</div>    	
						
		                <div id="profile_content">
							<hr>
							<div id="introduce_content">${memberlist.intro}</div>	
						</div>
	                </div>  
	                
	                
	                <div id="history_area">
		                <div id="raking_post_box">
		                	<div id="raking_post">
			                	<div id="title">
			                		${pagedto.nickname}님의 최근 강의랭킹 작성 게시물
			                	</div>
			                	
			                    <div id="content_box">	
								<c:forEach items="${profilelist }" var="dto">
									<div class="post_container">
									<div class="post_box">
										<div class="item">${dto.regdate}</div>
										<div class="detail">
											<div class="classtitle">${dto.classtitle}</div>
											<div class="item">${dto.author}</div>
										</div>
										<div class="detail_two">
										<div class="posttitle">${dto.posttitle}</div>
										<div class="viewcount">조회수:${dto.viewcount}</div>
										</div>
									</div>	
									</div>
								</c:forEach>
								</div>	
							</div>	                     	
		                </div>  
		                
		                <div id="raking_comment_box">
		                	<div id="raking_comment">
			                	<div id="title">
			                		${pagedto.nickname}님의 최근 강의랭킹 작성 댓글
			                	</div>
			                	
			                	<div id="content_box">			                
								<c:forEach items="${commentlist }" var="dto">
									<div class="comment_container">
									<div class="comment_box">
										<div class="item">${dto.regdate}</div>
										<div class="detail">
											<div class="classtitle">${dto.classtitle}</div>
											<div class="item">${dto.author}</div>
										</div>
										<div class="commentcontent">${dto.commentcontent}</div>
									</div>	
									</div>
								</c:forEach>
								</div>
							</div>
		                </div>  
	                	
	                </div>
                
                
                </div>
                
                <div id="profile_inner_space"></div>
                	
			</div>	
   		 </div>
   </div>		 
</body>
</html>