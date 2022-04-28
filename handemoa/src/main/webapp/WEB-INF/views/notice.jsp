<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel='stylesheet' type='text/css' href='css/paging.css'>
<script src="/jquery-3.6.0.min.js"></script>
<script src='/js/index.js'></script>
<script type="text/javascript">

	$(document).on('click', '#btnSearch', function(e){
		e.preventDefault();
		var url = "/notice";
		url = url + "?keyword=" + $('#keyword').val();
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
                <div id="inner_space"></div>
                
                <div id="content">
						
						<div class="notice_name">
                            <h1>공지사항</h1>
                    	</div>
                    	
                    <div id="content_box">	
					<c:forEach items="${Alllist }" var="dto">
						<div class="notice_container">
							<div class="item">${dto.regdate}</div>
							<div class="item"><a href="/noticenum?noticenum=${ dto.noticenum }">${dto.title}</a></div>
							<div class="item">운영자</div>
						</div>
					</c:forEach>
					</div>
					
					<!-- 공지사항 검색기능 -->
	                <div id="notice_search_area">
	                    <div id="notice_search_box">
	                       	 <input  type="text" name="keyword" id="keyword" value="${pagedto.keyword}"/>
	                    </div>
	                    <div id="notice_search_icon_area" >
	                        <div name="btnSearch" id="btnSearch" style="cursor:pointer;"><img src="css/images/search_icon.png"/></div>
	                    </div>
	                </div>
	                
	                <!-- 페이지 번호 넣을 공간입니다.-->
                    <div class="paginate">
				        <div class="paging">
				            <a class="direction_prev" href="notice?currentPage=1${pagedto.searchTypeKeyword}"> &lt;&lt; </a> 
				            <c:if test="${pagedto.hasPreviousPage == true}">
				            	<a class="direction_prev" href="notice?currentPage=${pagedto.currentPage-1}${pagedto.searchTypeKeyword}"> &lt;</a>
				            </c:if>
				            <c:if test="${pagedto.hasPreviousPage == false}">
				            	<a class="direction_prev" href="notice?currentPage=${pagedto.currentPage}${pagedto.searchTypeKeyword}"> &lt;</a>
				            </c:if>
				            
				            <c:forEach begin="${pagedto.firstPage}" end="${pagedto.lastPage}" var="idx">
				                <a href="notice?currentPage=${idx}${pagedto.searchTypeKeyword}" id="idx"><c:out value="${idx}" /></a>
				            </c:forEach>
				            
				            <c:if test="${pagedto.hasNextPage == true}">
				            	<a class="direction_next" href="notice?currentPage=${pagedto.currentPage+1}${pagedto.searchTypeKeyword}"> &gt;</a>
				            </c:if>
				            <c:if test="${pagedto.hasNextPage == false}">
				            	<a class="direction_next" href="notice?currentPage=${pagedto.currentPage}${pagedto.searchTypeKeyword}"> &gt;</a>
				            </c:if>
				            
				            <a class="direction_next" href="notice?currentPage=${pagedto.lastPage}${pagedto.searchTypeKeyword}"> &gt;&gt; </a>
				        </div>
				    </div>
	                    

	               </div>
	            </div>  
	        </div>   
    </div>

</body>
</html>