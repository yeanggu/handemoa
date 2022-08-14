<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>한데모아 - 검색</title>
<script src="/jquery-3.6.0.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='/css/index.css'>
<link rel='stylesheet' type='text/css' href='/css/search/search.css'>
<link rel='stylesheet' type='text/css' href='css/alarm.css'>
<script src='/js/alarm.js'></script>
<script src='/js/index.js'></script>
<script type="text/javascript" src='/js/search/search.js'></script>
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
                        <a href="#"><img src="/css/images/search_icon.png"/></a>
                    </div>
                    <div id="search_box_space"></div>
                </div>
            	<div id="inner_space"></div>
                <div id="search_content">
					<!-- 컨텐츠 넣어주세요! -->
					<div id="search_content_box">
					<!-- 페이지 제목 -->
					<div class="content_title">
						<h2>전체 게시글 통합 검색</h2>
					</div>
					<!-- 상세 검색 버튼-->
					<div class="content_head">
						<div class="content_head_item">						
							상세 검색
						</div>
						<div class="content_head_item">
							<select id="divisioncode" name="divisioncode">
								<option value="0">전체검색</option>
								<option value="1">커뮤니티</option>
								<option value="2">강의랭킹</option>
							</select>						
						</div>
						<div class="content_head_item">부분카테고리
							<select id="search_catename" name="search_catename">
								<option value="전체검색">전체검색</option>
								<c:forEach var="name" items="${catename}" varStatus="i">
									<option value="${name}">${name}</option>
								</c:forEach>
							</select>						
						</div>
						<div class="content_head_item">세부카테고리
							<select id="search_catedetailname" name="search_catedetailname">
								<option value="0">전체검색</option>
								<c:forEach var="cate" items="${cateAll}" varStatus="i">
									<option value="${cate.catedetailcode}">${cate.catedetailname}</option>
								</c:forEach>
							</select>						
						</div>
						<div class="content_head_item">
							<div id="searchtxt_box">
							<input type="text" id="searchtxt">
							</div>
						</div>
						<div class="content_head_item">
							<img id="search_btn" src="/css/images/search_icon.png"/>
						</div>
					</div>

					<!-- 게시글 목록-->
					<div id="post_list">
						<c:if test="${fn:length(searchPostList) == 0}">
							<p>'${searchdetail.searchtxt}'에 대한 검색 결과가 없습니다.</p>
						</c:if>
						<c:forEach var="post" items="${searchPostList}" varStatus="i">
							<c:if test="${post.divisioncode == 1}">
							<!--커뮤니티 게시글-->
								<div class="community_list_body">
									<div class="list_item">
										<div class="post_row">
											<div class="post_item">${post.regdate }</div>
										</div>
										<div class="post_row">
											<div class="post_item_title"><a href="/communitypost?postnum=${post.postnum}">${post.posttitle }</a></div>
										</div>	
										<div class="post_row">
											<div class="post_item"><a href="/profile?nickname=${post.nickname}" target="_blank">${post.nickname }</a></div>
										</div>	
									</div>						
								</div>

							</c:if>
							<c:if test="${post.divisioncode == 2}">
							<!--강의랭킹 게시글-->
								<div class="ranking_list_body">
									<div class="list_item">
										<div class="ranking_thumbnail"><img class="thumbnail_img" src="/css/images/${post.thumbnail}"></div>
									</div>
									<div class="list_item">
										<div class="post_row">
											<div class="post_item">${post.regdate }</div>
											<div class="post_item">${post.classtitle }</div>
											<div class="post_item">${post.author }</div>
										</div>
										<div class="post_row">
											<div class="post_item_title"><a href="/rankingpost?postnum=${post.postnum}">${post.posttitle }</a></div>
										</div>	
										<div class="post_row">
											<div class="post_item"><a href="/profile?nickname=${post.nickname}" target="_blank">${post.nickname }</a></div>
										</div>	
									</div>						
								</div>
							</c:if>
						</c:forEach>
					</div> <!-- report_list end -->
					<!-- 페이지 번호 -->
					<div id="search_page">
						<div class="direction_prev">
							<div class="direction_first_page">
								<c:if test="${(pagedto.currentpage != 1) && (pagedto.totalPage != 0)}">
									<a class="true" href='/searchdetail?divisioncode=${divisioncode}&currentpage=1&catename=${searchdetail.catename}&catedetailcode=${searchdetail.catedetailcode}&searchtxt=${searchdetail.searchtxt}'>&lt;&lt;</a>
								</c:if>
								<c:if test="${(pagedto.currentpage == 1) || (pagedto.totalPage == 0)}">
									<a class="false">&lt;&lt;</a>
								</c:if>
							</div>
							<div class="direction_prev_page">
								<c:if test="${pagedto.hasPrevPage == true}">
									<a class="true" href='/searchdetail?divisioncode=${divisioncode}&currentpage=${pagedto.currentpage -1}&catename=${searchdetail.catename}&catedetailcode=${searchdetail.catedetailcode}&searchtxt=${searchdetail.searchtxt}'>&lt;</a>
								</c:if>
								<c:if test="${pagedto.hasPrevPage == false}">
									<a class="false">&lt;</a>
								</c:if>							
							</div>
						</div>

						<c:forEach var="i" begin="${pagedto.beginPage}" end="${pagedto.endPage }">
							<c:if test="${pagedto.beginPage != 0}">
								<div class="page">
									<a class="pagenum" href='/searchdetail?divisioncode=${divisioncode}&currentpage=${i}&catename=${searchdetail.catename}&catedetailcode=${searchdetail.catedetailcode}&searchtxt=${searchdetail.searchtxt}'>${i}</a>
								</div>
							</c:if>						
							<c:if test="${pagedto.beginPage == 0}">	
								<div class="page">
									<a class="false">0</a>
								</div>
							</c:if>					
						</c:forEach>
							
						<div class="direction_next">
							<div class="direction_next_page">
								<c:if test="${pagedto.hasNextPage == true}">
									<a class="true" href='/searchdetail?divisioncode=${divisioncode}&currentpage=${pagedto.currentpage +1}&catename=${searchdetail.catename}&catedetailcode=${searchdetail.catedetailcode}&searchtxt=${searchdetail.searchtxt}'>&gt;</a>
								</c:if>
								<c:if test="${pagedto.hasNextPage == false}">
									<a class="false">&gt;</a>
								</c:if>
							</div>
							<div class="direction_last_page">
								<c:if test="${(pagedto.currentpage != pagedto.totalPage) && (pagedto.totalPage != 0)}">
									<a class="true" href='/searchdetail?divisioncode=${divisioncode}&currentpage=${pagedto.totalPage}&catename=${searchdetail.catename}&catedetailcode=${searchdetail.catedetailcode}&searchtxt=${searchdetail.searchtxt}'>&gt;&gt;</a>
								</c:if>
								<c:if test="${(pagedto.currentpage == pagedto.totalPage) || (pagedto.totalPage == 0)}">
									<a class="false">&gt;&gt;</a>
								</c:if>
							</div>
						</div>						
					</div> <!-- 페이지번호 end-->
					</div> <!-- search_content_box end -->
                </div> <!-- div content end -->     

            </div><!--content_container_space end -->
            
        </div> <!-- content_container end -->
    </div>
</body>
</html>