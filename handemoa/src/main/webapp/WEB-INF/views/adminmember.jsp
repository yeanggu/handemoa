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
<title>한데모아 - 회원관리</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/index.css'>
<link rel='stylesheet' type='text/css' href='css/adminmember.css'>
<link rel='stylesheet' type='text/css' href='css/paging.css'>
<script src='/js/index.js'></script>
<script src='/js/adminmember.js'></script>
<script src="/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	//검색
	$(document).on('click', '#btnSearch', function(e){
		e.preventDefault();
		var url = "/adminmember";
		url = url + "?searchType=" + $('#searchType').val();
		url = url + "&keyword=" + $('#keyword').val();
		location.href = url;
	});
	
	//탈퇴 회원 정렬
	$(document).on('change', '#quit_type', function(e){
		e.preventDefault();
		var url = "/adminmember";
		url = url + "?quitType=" + $('#quit_type').val();
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
						
						<div class="adminmember_name">
                            <h1>회원관리</h1>
                    	</div>
                    	
                    
                    <!-- 주요 기능 -->	
                    <div id="adminmember_btn_area">
	                    <div id="adminmember_btn_box">
	                        <input type="button" id="adminmember_btn" style="cursor:pointer;" onclick="deleteValue();" value="삭 제">
	                        <input type="button" id="adminmember_btn" style="cursor:pointer;" onclick="quitValue();" value="탈 퇴">
	                    </div>
	                    
	                    
	                	<!-- 회원 검색기능 -->
	                	<div id="adminsearch_area">
		                		<!-- 카테고리 선택 -->
		                	<div id="adminmember_search_type">
		                		<select name="searchType" id="searchType" class="search_type">
					                <option value="nickname">닉네임</option>
					                <option value="id">ID</option>                 
				                </select>
		                	</div> 
		                	
			                	<!-- 회원 검색기능 -->
			           		<div id="adminmember_search_area">
			                    <div id="adminmember_search_box">
			                       	 <input  type="text" name="keyword" id="keyword" value="${pagedto.keyword }"/>
			                    </div>
			                    <div id="adminmember_search_icon_area" >
			                        <div name="btnSearch" id="btnSearch" style="cursor:pointer;"><img src="css/images/search_icon.png"/></div>
			                    </div>
			                </div>
	                	</div>
	                </div>
	                
	                
	                <!-- 전체회원 리스트 -->
                    <div id="admin_member_content_box">
                    
							<div class="adminmember_title_container">
                    		<input type="checkbox" id="adminmember_select_all" name="adminmember_select_all" onclick="selectAll(this)" value='selectall'>
		                    	<div class="title_item" >회원가입날짜</div>
								<div class="title_item" >ID</div>
								<div class="title_item" >NICKNAME</div>
								<div class="title_item" >EMAIL</div>
								<div class="title_item" >PHONE</div>
								<div class="title_item" >생년월일</div>
								<div class="title_item" >
									<select name="quit_type" id="quit_type" class="quit_type">
						                <option value="all_select">탈퇴여부</option>
						                <option value="all_select">모든회원</option>
						                <option value="member_select">회원</option>
						                <option value="quit_member_select">탈퇴회원</option>                 
				               		</select>
				               	</div>	
								<div class="title_item" >회원탈퇴날짜</div>
							</div>
							
						<c:forEach items="${adminmemberlist }" var="dto">
							<div class="adminmember_check_container">
							<input type="checkbox" id="adminmember_select" name="adminmember_select" value="${dto.id }">
								<div class="adminmember_container">
									<div class="admin_item" >${dto.regdate}</div>
									<div class="admin_item" >${dto.id}</div>
									<div class="admin_item" ><a href="/profile?nickname=${dto.nickname}" target="_blank">${dto.nickname}</a></div>
									<div class="admin_item" >${dto.email}</div>
									<div class="admin_item" >${dto.phone}</div>
									<div class="admin_item" >${dto.birth}</div>
									<div class="admin_item" >
										<c:if test="${dto.status == 1}">탈퇴회원</c:if>
									</div>
									<div class="admin_item" >${dto.quitdate}</div>
								</div>
							</div>
						</c:forEach>
					</div>
					
                	<!-- 페이지 번호 넣을 공간입니다.-->
                    <div class="paginate">
				        <div class="paging">
				            <a class="direction_prev" href="adminmember?currentPage=1&quitType=${pagedto.quitType }${pagedto.searchTypeKeyword}"> &lt;&lt; </a> 
				            <c:if test="${pagedto.hasPreviousPage == true}">
				            	<a class="direction_prev" href="adminmember?currentPage=${pagedto.currentPage-1}&quitType=${pagedto.quitType }${pagedto.searchTypeKeyword}"> &lt;</a>
				            </c:if>
				            <c:if test="${pagedto.hasPreviousPage == false}">
				            	<a class="direction_prev" href="adminmember?currentPage=${pagedto.currentPage}&quitType=${pagedto.quitType }${pagedto.searchTypeKeyword}"> &lt;</a>
				            </c:if>
				            
				            <c:forEach begin="${pagedto.firstPage}" end="${pagedto.lastPage}" var="idx">
				                <a href="adminmember?currentPage=${idx}&quitType=${pagedto.quitType }${pagedto.searchTypeKeyword}" id="idx"><c:out value="${idx}" /></a>
				            </c:forEach>
				            
				            <c:if test="${pagedto.hasNextPage == true}">
				            	<a class="direction_next" href="adminmember?currentPage=${pagedto.currentPage+1}&quitType=${pagedto.quitType }${pagedto.searchTypeKeyword}"> &gt;</a>
				            </c:if>
				            <c:if test="${pagedto.hasNextPage == false}">
				            	<a class="direction_next" href="adminmember?currentPage=${pagedto.currentPage}&quitType=${pagedto.quitType }${pagedto.searchTypeKeyword}"> &gt;</a>
				            </c:if>
				            
				            <a class="direction_next" href="adminmember?currentPage=${pagedto.lastPage}&quitType=${pagedto.quitType }${pagedto.searchTypeKeyword}"> &gt;&gt; </a>
				        </div>
				    </div>
	                    

	               </div>
	            </div>  
	        </div>   
    </div>
</body>
</html>