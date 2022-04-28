<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
    if (session.getAttribute("signedUser") == null) {
        response.sendRedirect("adminlogin");
    }
%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>한데모아 - 히스토리</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/index.css'>
<link rel='stylesheet' type='text/css' href='css/paging.css'>
<link rel='stylesheet' type='text/css' href='css/postmanage.css'>
<link rel='stylesheet' type='text/css' href='css/history.css'>
<script src='/js/index.js'></script>
<script src='/js/history.js'></script>
<script src="/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	//검색
	$(document).on('click', '#btnSearch', function(e){
		e.preventDefault();
		var url = "/history";
		url = url + "?searchType=" + $('#searchType').val();
		url = url + "&keyword=" + $('#keyword').val();
		location.href = url;
	});
	
	//탈퇴 회원 정렬
	$(document).on('change', '#post_type', function(e){
		e.preventDefault();
		var url = "/history";
		url = url + "?postType=" + $('#post_type').val();
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
                    <a href="/adminindex"> 
                        <img src="css/images/logo.png" />
                    </a>
                </div>
                <div id="nav_profile">
                    <div id="nav_profile_img">
                        <img src="css/images/login_before.png"/>
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
                <div id="admin_inner_space"></div>
                
                <div id="admin_content">
						<div class="adminmanage_bar">
                            	<div class="title_bar"><a href="/postmanage" id="postbar">게시글관리</a></div>
                            	<div class="title_bar" ><a href="/admincomment" id="commentbar">댓글관리</a></div>
                            	<div class="title_bar"><a href="/history" id="historybar">HISTORY</a></div>
                    	</div>
						
						
                    <!-- 주요 기능 -->	
                    <div id="postmanage_btn_area">
	                    <div id="postmanage_btn_box">
	                        <input type="button" id="postmanage_btn" style="cursor:pointer;" onclick="restoreValue();" value="복 구">
	                        <input type="button" id="postmanage_btn" style="cursor:pointer;" onclick="deleteValue();" value="삭 제">
	                    </div>
	                    
	                    
	                	<!-- 회원 검색기능 -->
	                	<div id="adminsearch_area">
		                	<!-- 카테고리 선택 -->
		                	<div id="postmanage_search_type">
		                		<select name="searchType" id="searchType" class="searchType">
		                			<option value="all_select">전체(제목)</option>
					                <option value="community">커뮤니티</option>
					                <option value="ranking">강의랭킹</option> 
					                <option value="nickname">닉네임</option>
					                <option value="date">게시일</option>                
				                </select>
		                	</div> 
		                	
			                <!-- 회원 검색기능 -->
			           		<div id="postmanage_search_area">
			                    <div id="postmanage_search_box">
			                       	 <input  type="text" name="keyword" id="keyword" value="${pagedto.keyword }"/>
			                    </div>
			                    <div id="postmanage_search_icon_area" >
			                        <div name="btnSearch" id="btnSearch" style="cursor:pointer;"><img src="css/images/search_icon.png"/></div>
			                    </div>
			                </div>
	                	</div>
	                	
	                </div>
	                
	                
	                <!-- 전체회원 리스트 -->
                    <div id="admin_postmanage_content_box">
                    
							<div class="postmanage_title_container">
                    		<input type="checkbox" id="postmanage_select_all" name="postmanage_select_all" onclick="selectAll(this)" value='selecctall'>
								<div class="title_item" >
									<select name="post_type" id="post_type" class="post_type">
						                <option value="all_select">분류</option>
						                <option value="all_post_select">전체게시물</option>
						                <option value="community_select">커뮤니티</option>
						                <option value="ranking_select">강의랭킹</option>                 
				               		</select>
				               	</div>	
		                    	<div class="title_item" >게시일</div>
								<div class="title_item" >NICKNAME</div>
								<div class="title_item" >제목</div>
							</div>
							
						<c:forEach items="${adminpostlist }" var="dto">
							<div class="postmanage_check_container">
								<input type="checkbox" id="postmanage_select" name="postmanage_select" value="${dto.postnum }">
								<div class="postmanage_container">
									<div class="post_item" >
										<c:if test="${dto.divisioncode == 1}">커뮤니티</c:if>
										<c:if test="${dto.divisioncode == 2}">강의랭킹</c:if>
									</div>
									<div class="post_item" >${dto.regdate}</div>
									<div class="post_item" ><a href="/profile?nickname=${dto.nickname}" target="_blank">${dto.nickname}</a></div>
									<div class="post_item" >
										<c:if test="${dto.divisioncode == 1}"><a href="/communitypost?postnum=${ dto.postnum }" target="_blank">${dto.posttitle}</a></c:if>
										<c:if test="${dto.divisioncode == 2}"><a href="/rankingpost?postnum=${ dto.postnum }" target="_blank">${dto.posttitle}</a></c:if>										
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					
                	<!-- 페이지 번호 넣을 공간입니다.-->
                    <div class="paginate">
				        <div class="paging">
				            <a class="direction_prev" href="history?currentPage=1&postType=${pagedto.postType }${pagedto.searchTypeKeyword}"> &lt;&lt; </a> 
				            <c:if test="${pagedto.hasPreviousPage == true}">
				            	<a class="direction_prev" href="history?currentPage=${pagedto.currentPage-1}&postType=${pagedto.postType }${pagedto.searchTypeKeyword}"> &lt;</a>
				            </c:if>
				            <c:if test="${pagedto.hasPreviousPage == false}">
				            	<a class="direction_prev" href="history?currentPage=${pagedto.currentPage}&postType=${pagedto.postType }${pagedto.searchTypeKeyword}"> &lt;</a>
				            </c:if>
				            
				            <c:forEach begin="${pagedto.firstPage}" end="${pagedto.lastPage}" var="idx">
				                <a href="history?currentPage=${idx}&postType=${pagedto.postType }${pagedto.searchTypeKeyword}" id="idx"><c:out value="${idx}" /></a>
				            </c:forEach>
				            
				            <c:if test="${pagedto.hasNextPage == true}">
				            	<a class="direction_next" href="history?currentPage=${pagedto.currentPage+1}&postType=${pagedto.postType }${pagedto.searchTypeKeyword}"> &gt;</a>
				            </c:if>
				            <c:if test="${pagedto.hasNextPage == false}">
				            	<a class="direction_next" href="history?currentPage=${pagedto.currentPage}&postType=${pagedto.postType }${pagedto.searchTypeKeyword}"> &gt;</a>
				            </c:if>
				            
				            <a class="direction_next" href="history?currentPage=${pagedto.lastPage}&postType=${pagedto.postType }${pagedto.searchTypeKeyword}"> &gt;&gt; </a>
				        </div>
				    </div>
	                    

	               </div>
	            </div>  
	        </div>   
    </div>
</body>
</html>