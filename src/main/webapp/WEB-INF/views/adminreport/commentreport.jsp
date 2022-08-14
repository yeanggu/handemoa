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
<link rel='stylesheet' type='text/css' href='/css/adminreport/adminreport.css'>
<link rel='stylesheet' type='text/css' href='/css/adminreport/commentreport.css'>
<script type="text/javascript" src='/js/adminreport/adminreport.js'></script>
<script type="text/javascript" src='/js/adminreport/commentreport.js'></script>
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
                <div id="report_content">
					<!-- 컨텐츠 넣어주세요! -->

					<!-- 게시글 신고관리 / 댓글 신과관리 탭 -->
					<div class="content_report_tab">
						<div class="content_postreport_tab"><a href="/adminpostreport">게시글 신고관리</a></div>
						<div class="content_commentreport_tab"><a href="/admincommentreport">댓글 신고관리</a></div>
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
								<select id="search_select" name="search" class="search_select">
									<option value="0">전체검색</option>
									<option value="1">댓글내용</option>
									<option value="2">닉네임</option>
								</select>						
							</div>
							<div id="content_search_box">
								<input type="text" id="searchdetail">
							</div>							
							<div class="content_search_btn">
								<img id="search_btn" src="/css/images/search_icon.png"/>
							</div>
						</div>
					</div>

					<!-- 댓글 신고 목록 -->
					<div id="report_list">
						<div class="list_head">
							<div class="report">
								<input type="checkbox" id="allCheck">
							</div>
							<div class="report">글 분류</div>
							<div class="report">닉네임</div>
							<div class="report">댓글 번호</div>
							<div class="report">댓글 내용</div>
							<div class="report">신고 횟수</div>
						</div>
						<c:forEach var="comment" items="${commentReportList}" varStatus="i">
							<div class="list_body">
								<div class="list_row">
									<div class="report">
										<input type="checkbox" name="ck_report" value="${comment.commentnum }">
									</div>
									<div class="report">${comment.divisionname }</div>
									<div class="report"><a href="/profile?nickname=${comment.nickname}" target="_blank">${comment.nickname}</a></div>
									<div class="report">${comment.commentnum }</div>
									<div class="report">
										<span class="toggle" id="${i.count }">${comment.commentcontent }
											<input type="hidden" value="${comment.commentnum }">
										</span>
									</div>
									<div class="report">${comment.count }</div>
								</div>								
							</div>
						</c:forEach>
					</div> <!-- report_list end -->

					<!-- 페이지 번호 -->
					<div id="report_page">
						<div class="direction_prev">
							<div class="direction_first_page">
								<c:if test="${(reportpagedto.currentpage != 1) && (reportpagedto.totalPage != 0)}">
									<a class="true" href="/admincommentreport/division?divisioncode=${divisioncode}&currentpage=1">&lt;&lt;</a>
								</c:if>
								<c:if test="${(reportpagedto.currentpage == 1) || (reportpagedto.totalPage == 0)}">
									<a class="false">&lt;&lt;</a>
								</c:if>
							</div>
							<div class="direction_prev_page">
								<c:if test="${reportpagedto.hasPrevPage == true}">
									<a class="true" href='/admincommentreport/division?divisioncode=${divisioncode}&currentpage=${reportpagedto.currentpage -1}'>&lt;</a>
								</c:if>
								<c:if test="${reportpagedto.hasPrevPage == false}">
									<a class="false">&lt;</a>
								</c:if>							
							</div>
						</div>

						<c:forEach var="i" begin="${reportpagedto.beginPage}" end="${reportpagedto.endPage }">
							<c:if test="${reportpagedto.beginPage != 0}">
								<div class="page">
									<a class="pagenum" href='/admincommentreport/division?divisioncode=${divisioncode}&currentpage=${i }'>${i}</a>
								</div>
							</c:if>						
							<c:if test="${reportpagedto.beginPage == 0}">
								<div class="page">
									<a class="false">0</a>
								</div>
							</c:if>						
						</c:forEach>
							
						<div class="direction_next">
							<div class="direction_next_page">
								<c:if test="${reportpagedto.hasNextPage == true}">
									<a class="true" href='/admincommentreport/division?divisioncode=${divisioncode}&currentpage=${reportpagedto.currentpage + 1}'>&gt;</a>
								</c:if>
								<c:if test="${reportpagedto.hasNextPage == false}">
									<a class="false">&gt;</a>
								</c:if>
							</div>
							<div class="direction_last_page">
								<c:if test="${(reportpagedto.currentpage != reportpagedto.totalPage) && (reportpagedto.totalPage != 0)}">
									<a class="true" href="/admincommentreport/division?divisioncode=${divisioncode}&currentpage=${reportpagedto.totalPage}">&gt;&gt;</a>
								</c:if>
								<c:if test="${(reportpagedto.currentpage == reportpagedto.totalPage) || (reportpagedto.totalPage == 0)}">
									<a class="false">&gt;&gt;</a>
								</c:if>
							</div>
						</div>						
					</div> <!-- 페이지번호 end-->

					<!-- 신고 상세 내역 모달창 -->
					<div id="modal_report">
						<div class="modal_layer">
							<div class="modal_content">
								<div class="modal_content_title">
									<h2>신고 상세 내역</h2>
									<hr>
								</div>
								<div class="modal_content_body">
									<div class="reportdetail">
										<div class="reportdetail_head">
											<div class="report">번호</div>
											<div class="report">신고 회원 아이디</div>
											<div class="report">신고 사유</div>
										</div>
									</div>
								</div>
								<button type="button" id="modal_close_btn">창 닫기</button>
							</div>
						</div>
					</div>
					
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