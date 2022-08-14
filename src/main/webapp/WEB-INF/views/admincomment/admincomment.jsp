<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
    if (session.getAttribute("signedUser") == null) {
        response.sendRedirect("adminlogin");
    }
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>한데모아 - 관리자</title>
<script src="/jquery-3.6.0.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='/css/index.css'>
<link rel='stylesheet' type='text/css' href='/css/admincomment/admincomment.css'>
<script src='/js/index.js'></script>
<script type="text/javascript" src='/js/admincomment/admincomment.js'></script>
</head>
<body>

    <div class="container">
        <!-- 네비게이션 start -->
        <div id="nav_area">
            <div id="nav_space">
                <div id="logo">
                    <a href="/adminindex"> 
                        <img src="/css/images/logo.png" />
                    </a>
                </div>
                <div id="nav_profile">
                    <div id="nav_profile_img">
                        <img src="/css/images/login_before.png"/>
                    </div>
                    <h3>관리자</h3>
                    <button id="nav_login_btn"	onclick="location.href='/adminlogout'">로그아웃</button>
                </div>
                <div id="nav_list">
                    <div class="nav_list_area">
                        <a href="/adminmember"> <!-- 해당 링크 이동 -->
                            <h4>회원관리</h4></a>
                    </div>
                    <div class="nav_list_area">
                        <a href="/adminpostreport"> <!-- 해당 링크 이동 -->
                            <h4>신고관리</h4></a>
                    </div>
                    <div class="nav_list_area">
                        <a href="/postmanage"> <!-- 해당 링크 이동 -->
                            <h4>게시글/댓글관리</h4></a>
                    </div>
                    <div class="nav_list_area">
                        <a href="/adminnotice"> <!-- 해당 링크 이동 -->
                            <h4>공지사항 관리</h4></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- 네비게이션 end -->

        <div id="content_container">
            <div id="content_container_space">
            	<div id="inner_space"></div>
                <div id="admincomment_content">
					<!-- 컨텐츠 넣어주세요! -->

					<!-- 관리 탭 -->
					<div class="adminmanage_bar">
                       	<div class="title_bar"><a href="/postmanage" id="postbar">게시글관리</a></div>
                       	<div class="title_bar" ><a href="/admincomment" id="commentbar">댓글관리</a></div>
                    	<div class="title_bar"><a href="/history" id="historybar">HISTORY</a></div>
                    </div>
					<!-- 상세 검색 / 삭제 버튼-->
					<div class="content_head">
						<div class="content_head_delete">
							<button id="delete_btn">삭제</button>
						</div>
						<div class="content_head_search">
							<div class="content_search_item">						
								상세 검색
							</div>
							<div class="content_search_select">
								<select id="divisioncode" name="divisioncode" class="search_select">
									<option value="0">전체검색</option>
									<option value="1">커뮤니티</option>
									<option value="2">강의랭킹</option>
								</select>						
							</div>
							<div class="content_search_select">
								<select id="comment_search" name="comment_search" class="search_select">
									<option value="0">전체검색</option>
									<option value="1">댓글번호</option>
									<option value="2">댓글내용</option>
									<option value="3">닉네임</option>
								</select>						
							</div>
							<div id="comment_search_box">
								<input type="text" id="comment_searchdetail">
							</div>							
							<div class="comment_search_btn">
								<img id="search_btn" src="/css/images/search_icon.png"/>
							</div>
						</div>
					</div>

					<!-- 댓글 목록-->
					<div id="comment_list">
						<div class="list_head">
							<div class="report">
								<input type="checkbox" id="allCheck">
							</div>
							<div class="report">댓글 분류</div>
							<div class="report">댓글 번호</div>
							<div class="report">작성일</div>
							<div class="report">닉네임</div>
							<div class="report">댓글</div>
						</div>
						<c:forEach var="comment" items="${commentList}" varStatus="i">
							<div class="list_body">
								<div class="list_row">
									<div class="report">
										<input type="checkbox" name="ck_report" value="${comment.commentnum }">
									</div>
									<div class="report">${comment.divisionname }</div>
									<div class="report">${comment.commentnum }</div>
									<div class="report">${comment.regdate }</div>
									<div class="report"><a href="/profile?nickname=${comment.nickname }" target="_blank">${comment.nickname }</a></div>
									<c:if test="${comment.divisioncode == 1}">
										<div class="report"><a href="/communitypost?postnum=${comment.postnum }" target="_blank">${comment.commentcontent }</a></div>
									</c:if>
									<c:if test="${comment.divisioncode == 2}">
										<div class="report"><a href="/rankingpost?postnum=${comment.postnum }" target="_blank">${comment.commentcontent }</a></div>
									</c:if>
								</div>								
							</div>
						</c:forEach>
					</div> <!-- report_list end -->
					<!-- 페이지 번호 -->
					<div id="admincomment_page">
						<div class="direction_prev">
							<div class="direction_first_page">
								<c:if test="${(pagedto.currentpage != 1) && (pagedto.totalPage != 0)}">
									<a class="true" href="/admincomment/search?divisioncode=${divisioncode}&currentpage=1&search=${searchdetail.search}&searchtxt=${searchdetail.searchtxt}">&lt;&lt;</a>
								</c:if>
								<c:if test="${(pagedto.currentpage == 1) || (pagedto.totalPage == 0)}">
									<a class="false">&lt;&lt;</a>
								</c:if>
							</div>
							<div class="direction_prev_page">
								<c:if test="${pagedto.hasPrevPage == true}">
									<a class="true" href='/admincomment/search?divisioncode=${divisioncode}&currentpage=${pagedto.currentpage -1}&search=${searchdetail.search}&searchtxt=${searchdetail.searchtxt}'>&lt;</a>
								</c:if>
								<c:if test="${pagedto.hasPrevPage == false}">
									<a class="false">&lt;</a>
								</c:if>							
							</div>
						</div>

						<c:forEach var="i" begin="${pagedto.beginPage}" end="${pagedto.endPage }">
							<c:if test="${pagedto.beginPage != 0}">
								<div class="page">
									<a class="pagenum" href='/admincomment/search?divisioncode=${divisioncode}&currentpage=${i }&search=${searchdetail.search}&searchtxt=${searchdetail.searchtxt}'>${i}</a>
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
									<a class="true" href='/admincomment/search?divisioncode=${divisioncode}&currentpage=${pagedto.currentpage + 1}&search=${searchdetail.search}&searchtxt=${searchdetail.searchtxt}'>&gt;</a>
								</c:if>
								<c:if test="${pagedto.hasNextPage == false}">
									<a class="false">&gt;</a>
								</c:if>
							</div>
							<div class="direction_last_page">
								<c:if test="${(pagedto.currentpage != pagedto.totalPage) && (pagedto.totalPage != 0)}">
									<a class="true" href="/admincomment/search?divisioncode=${divisioncode}&currentpage=${pagedto.totalPage}&search=${searchdetail.search}&searchtxt=${searchdetail.searchtxt}">&gt;&gt;</a>
								</c:if>
								<c:if test="${(pagedto.currentpage == pagedto.totalPage) || (pagedto.totalPage == 0)}">
									<a class="false">&gt;&gt;</a>
								</c:if>
							</div>
						</div>						
					</div> <!-- 페이지번호 end-->
					
					<!-- 선택된 내역 없음 모달창 -->					
					<div id="modal_check">
						<div class="modal_layer">
							<div class="modal_content">
								<div class="modal_content_title">
									<h2>선택된 신고 내역이 없습니다.</h2>
									<h2>다시 선택해주세요.</h2>
								</div>
								<div class="modal_check_btn">
									<button type="button" id="modal_check_close_btn">확인</button>
								</div>
							</div>
						</div>
					</div>
					
					
					<!-- 삭제 확인 모달창 -->
					<div id="modal_confirm">
						<div class="modal_layer">
							<div class="modal_content">
								<div class="modal_content_title">
									<h2>정말로 삭제하시겠습니까?</h2>
								</div>
								<div class="modal_confirm_btn">
									<button type="button" id="modal_confirm_close_btn">아니요</button>
									<button type="button" id="modal_confirm_ok_btn">삭제</button>
								</div>
							</div>
						</div>
					</div>

                </div> <!-- div content end -->     

            </div><!--content_container_space end -->
            
        </div> <!-- content_container end -->
    </div>
</body>
</html>