<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>한데모아 - 북마크</title>
<script src="/jquery-3.6.0.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='/css/index.css'>
<link rel='stylesheet' type='text/css' href='/css/bookmark/bookmarkview.css'>
<script type="text/javascript" src='/js/bookmark/bookmarkview.js'></script>
<script src='/js/index.js'></script>
</head>
<body>

    <div class="container">
        <!-- 네비게이션 start -->
        <div id="nav_area">
            <div id="nav_space">
                <div id="logo">
                    <a href="/handemoa"> 
                        <img src="/css/images/logo.png" />
                    </a>
                </div>
                <div id="nav_profile">
               <div id="nav_profile_img">

                  <img src="css/images/${member.profileimg}"
                     style="border-radius: 80%;" width="100%" />


               </div>
               <c:choose>
                  <c:when test="${isLogOn == true && member!= null}">

                     <a href="/profile?nickname=${member.nickname}"><h3
                           style="text-align: center;">${member.nickname}님</h3></a>
                     <div style="display: flex;">
                        <button id="nav_login_btn" onclick="location.href='/logout'"
                           style="color: white; background-color: #ce4764;">로그아웃</button>
                        <button id="nav_login_btn" onclick="location.href='/memberedit'"
                           style="color: white; background-color: gray; margin-left: 10px; font-size: 5px;">회원정보수정</button>

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
						<a href="/ranking?catedetailcode=8&page=1"> <!-- 해당 링크 이동 -->
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
                        <a href="#"><img src="/css/images/search_icon.png"/></a>
                    </div>
                    <div id="search_box_space"></div>
                </div>
            	<div id="inner_space"></div>
                <div id="bookmark_content">
					<!-- 컨텐츠 넣어주세요! -->
				<div id="bookmark_content_box">
					<!-- 페이지 제목 -->
					<div class="content_title">
						<div class="title_bar"><h2>${member.nickname} 님의 북마크</h2></div>
					</div>
					<!-- 상세 검색 -->
					<div class="content_head">
						<div class="content_head_item">						
							글 제목 검색
						</div>						
						<div class="content_head_item">
							<div id="bookmark_search_box">
							<input type="text" id="bookmark_searchdetail">
							</div>
						</div>
						<div class="content_head_item">
							<img id="search_btn" src="/css/images/search_icon.png"/>
						</div>
					</div>

					<!-- 북마크 목록-->
					<div id="bookmark_list">
						<c:forEach var="bookmark" items="${bookMarkList}" varStatus="i">
							<div class="list_body">
								<div class="list_item">
									<div class="bookmark_thumbnail">
										<img class="thumbnail_img" src="/css/images/${bookmark.thumbnail}">
									</div>
								</div>
								<div class="list_item">
									<div class="bookmark_row">
										<div class="bookmark_item">${bookmark.regdate }</div>
										<div class="bookmark_item">${bookmark.classtitle }</div>
										<div class="bookmark_item">${bookmark.author }</div>
									</div>
									<div class="bookmark_row">
										<div class="bookmark_item_title"><a href="/rankingpost?postnum=${bookmark.postnum}">${bookmark.posttitle }</a></div>
									</div>	
									<div class="bookmark_row">
										<div class="bookmark_item_title"><a href="/profile?nickname=${bookmark.nickname}">${bookmark.nickname }</a></div>
									</div>	
								</div>						
							</div>
						</c:forEach>
					</div> <!-- report_list end -->
					<!-- 페이지 번호 -->
					<div id="bookmark_page">
						<div class="direction_prev">
							<div class="direction_first_page">
								<c:if test="${(pagedto.currentpage != 1) && (pagedto.totalPage != 0)}">
									<a class="true" href="/bookmarkview?currentpage=1&searchtxt=${searchdetail.searchtxt}">&lt;&lt;</a>
								</c:if>
								<c:if test="${(pagedto.currentpage == 1) || (pagedto.totalPage == 0)}">
									<a class="false">&lt;&lt;</a>
								</c:if>
							</div>
							<div class="direction_prev_page">
								<c:if test="${pagedto.hasPrevPage == true}">
									<a class="true" href="/bookmarkview?currentpage=${pagedto.currentpage -1}&searchtxt=${searchdetail.searchtxt}">&lt;</a>
								</c:if>
								<c:if test="${pagedto.hasPrevPage == false}">
									<a class="false">&lt;</a>
								</c:if>							
							</div>
						</div>

						<c:forEach var="i" begin="${pagedto.beginPage}" end="${pagedto.endPage }">
							<c:if test="${pagedto.beginPage != 0}">
								<div class="page">
									<a class="pagenum" href="/bookmarkview?currentpage=${i }&searchtxt=${searchdetail.searchtxt}">${i}</a>
								</div>
							</c:if>						
							<c:if test="${pagedto.beginPage == 0}">
								<a class="false"></a>
							</c:if>
						</c:forEach>
							
						<div class="direction_next">
							<div class="direction_next_page">
								<c:if test="${pagedto.hasNextPage == true}">
									<a class="true" href="/bookmarkview?currentpage=${pagedto.currentpage + 1}&searchtxt=${searchdetail.searchtxt}">&gt;</a>
								</c:if>
								<c:if test="${pagedto.hasNextPage == false}">
									<a class="false">&gt;</a>
								</c:if>
							</div>
							<div class="direction_last_page">
								<c:if test="${(pagedto.currentpage != pagedto.totalPage) && (pagedto.totalPage != 0)}">
									<a class="true" href="/bookmarkview?currentpage=${pagedto.totalPage}&searchtxt=${searchdetail.searchtxt}">&gt;&gt;</a>
								</c:if>
								<c:if test="${(pagedto.currentpage == pagedto.totalPage) || (pagedto.totalPage == 0)}">
									<a class="false">&gt;&gt;</a>
								</c:if>
							</div>
						</div>						
					</div> <!-- 페이지번호 end-->
					</div> <!-- div content box end -->
                </div> <!-- div bookmark_content end -->     

            </div><!--content_container_space end -->
            
        </div> <!-- content_container end -->
    </div>
</body>
</html>