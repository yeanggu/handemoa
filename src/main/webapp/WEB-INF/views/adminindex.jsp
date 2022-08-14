<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
    if (session.getAttribute("signedUser") == null) {
        response.sendRedirect("adminlogin");
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>한데모아 - 관리자 페이지</title>
<link rel='stylesheet' type='text/css' href='/css/index.css'>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/index.css'>
<link rel='stylesheet' type='text/css' href='css/adminindex.css'>
    
<script src='/js/index.js'></script>
<script src="/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

//탈퇴 회원 정렬
$(document).on('change', '#quit_type', function(e){
	e.preventDefault();
	var url = "/adminindex";
	url = url + "?quitType=" + $('#quit_type').val();
	location.href = url;
});	

//신고 글분류
$(document).on('change', '#report_type', function(e){
	e.preventDefault();
	var url = "/adminindex";
	url = url + "?reportType=" + $('#report_type').val();
	location.href = url;
});	

//게시글 분류
$(document).on('change', '#post_type', function(e){
	e.preventDefault();
	var url = "/adminindex";
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
                <div id="index_inner_space">
                	<h1>관리자님 환영합니다.</h1>
                </div>
                <hr>
                <div id="all_content">
                
	                <div id="content">
							
							<div class="index_name">
	                            <h2>회원관리</h2>
	                    	</div>
		                
		                <!-- 전체회원 리스트 -->
	                    <div id="admin_member_content_box">
	                    
								<div class="adminmember_title_container">
			                    	<div class="title_item" >회원가입날짜</div>
									<div class="title_item" >ID</div>
									<div class="title_item" >NICKNAME</div>
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
								</div>
								
							<c:forEach items="${memberlist }" var="dto">
								<div class="adminmember_check_container">
									<div class="adminmember_container">
										<div class="admin_item" >${dto.regdate}</div>
										<div class="admin_item" >${dto.id}</div>
										<div class="admin_item" ><a href="/profile?nickname=${dto.nickname}" target="_blank">${dto.nickname}</a></div>
										<div class="admin_item" >${dto.phone}</div>
										<div class="admin_item" >${dto.birth}</div>
										<div class="admin_item" >
											<c:if test="${dto.status == 1}">탈퇴회원</c:if>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
							<div id="button_area">
								<a href="/adminmember">
								<div id="all_select">전체보기</div>
								</a>
							</div>
		         	</div>
	                
	                
	                
	                <div id="content">
							
							<div class="index_name">
	                            <h2>신고관리</h2>
	                    	</div>
		                
		                <!-- 전체회원 리스트 -->
	                    <div id="admin_member_content_box">
	                    
								<div class="adminmember_title_container">
									<div class="report_title_item" >
										<select name="report_type" id="report_type" class="report_type">
							                <option value="all_select">글분류</option>
							                <option value="all_select">전체게시물</option>
							                <option value="community">커뮤니티</option>
							                <option value="ranking">강의랭킹</option>
					               		</select>
					               	</div>	
									<div class="report_title_item" >NICKNAME</div>
									<div class="report_title_item" >제목</div>
									<div class="report_title_item" >신고횟수</div>
								</div>
								
							<c:forEach items="${reportlist }" var="dto">
								<div class="adminmember_check_container">
									<div class="adminmember_container">
										<div class="report_item" >
											<c:if test="${dto.divisioncode == 1}">커뮤니티</c:if>
											<c:if test="${dto.divisioncode == 2}">강의랭킹</c:if>
										</div>
										<div class="report_item" ><a href="/profile?nickname=${dto.nickname}" target="_blank">${dto.nickname}</a></div>
										<div class="report_item" >
											<c:if test="${dto.divisioncode == 1}"><a href="/community?postnum=${ dto.postnum }" target="_blank">${dto.posttitle}</a></c:if>
											<c:if test="${dto.divisioncode == 2}"><a href="/rankingpost?postnum=${ dto.postnum }" target="_blank">${dto.posttitle}</a></c:if>										
										</div>
										<div class="report_item" >${dto.postreportnum}</div>
									</div>
								</div>
							</c:forEach>
						</div>
							<div id="button_area">
								<a href="/adminpostreport">
								<div id="all_select">전체보기</div>
								</a>
							</div>
		         </div>
	         
	         
	      </div>   
	      <div id="all_content">   
	         
                <div id="content">
						
						<div class="index_name">
                            <h2>게시글/댓글관리</h2>
                    	</div>
	                
	                <!-- 전체회원 리스트 -->
                    <div id="admin_member_content_box">
                    
							<div class="adminmember_title_container">
		                    	<div class="post_title_item" >
		                    		<select name="post_type" id="post_type" class="post_type">
						                <option value="all_select">글분류</option>
						                <option value="all_select">전체게시물</option>
						                <option value="community">커뮤니티</option>
						                <option value="ranking">강의랭킹</option>
				               		</select>
				               	</div>	
								<div class="post_title_item" >게시일</div>
								<div class="post_title_item" >NICKNAME</div>
								<div class="post_title_item" >제목</div>
								<div class="post_title_item" >조회수</div>
							</div>
							
						<c:forEach items="${postlist }" var="dto">
							<div class="adminmember_check_container">
								<div class="adminmember_container">
									<div class="post_item" >
										<c:if test="${dto.divisioncode == 1}">커뮤니티</c:if>
										<c:if test="${dto.divisioncode == 2}">강의랭킹</c:if>
									</div>
									<div class="post_item" >${dto.regdate}</div>
									<div class="post_item" ><a href="/profile?nickname=${dto.nickname}" target="_blank">${dto.nickname}</a></div>
									<div class="post_item" >
										<c:if test="${dto.divisioncode == 1}"><a href="/community?postnum=${ dto.postnum }" target="_blank">${dto.posttitle}</a></c:if>
										<c:if test="${dto.divisioncode == 2}"><a href="/rankingpost?postnum=${ dto.postnum }" target="_blank">${dto.posttitle}</a></c:if>									
									</div>
									<div class="post_item" >${dto.viewcount}</div>
								</div>
							</div>
						</c:forEach>
					</div>
						<div id="button_area">
						<a href="/postmanage">
							<div id="all_select">전체보기</div>
						</a>	
						</div>
	         </div>
	         
	         
	         
                <div id="content">
						
						<div class="index_name">
                            <h2>공지사항 관리</h2>
                    	</div>
	                
	                <!-- 전체회원 리스트 -->
                    <div id="admin_member_content_box">
                    
							<div class="adminmember_title_container">
		                    	<div class="notice_title_item" >등록일</div>
								<div class="notice_title_item" >제목</div>
								<div class="notice_title_item" >운영자</div>
							</div>
							
						<c:forEach items="${noticelist }" var="dto">
							<div class="adminmember_check_container">
								<div class="adminmember_container">
									<div class="notice_item" >${dto.regdate}</div>
									<div class="notice_item" ><a href="/adminnoticenum?noticenum=${dto.noticenum}" target="_blank">${dto.title}</a></div>
									<div class="notice_item" >운영자</div>
								</div>
							</div>
						</c:forEach>
					</div>
						<div id="button_area">
						<a href="/adminnotice">
							<div id="all_select">전체보기</div>
						</a>	
						</div>
	         </div>	         	                         
                
         </div>       
                
            <div id="index_inner_space"></div>    
            </div>
        </div>
    </div>
</body>
</html>