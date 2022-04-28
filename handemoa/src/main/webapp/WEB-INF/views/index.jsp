<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>한데모아 - 메인페이지</title>
<link rel='stylesheet' type='text/css' href='/css/index.css'>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/index.css'>
<link rel='stylesheet' type='text/css' href='css/mainindex.css'>
    
<script src="/jquery-3.6.0.min.js"></script>
<script src='/js/index.js'></script>
<script type="text/javascript">

//세부 카테고리 등록 함수
function setcatedetail(){
	let catename = $('#search_catename option:selected').text();
	//세부카테고리초기화
	$('#search_catedetailname').html('<option value="0">검색</option>');
	//부분 카테고리에 해당하는 세부카테고리 조회
	$.ajax({
		url: "/maincategory",
		data: { 'catename': catename },
		type: 'post',
		dataType: 'json',
		success: function (result) {
			for(var i in result){
				$('#search_catedetailname').append('<option value="' + result[i].catedetailcode + '">' + result[i].catedetailname +'</option>')
			}			
		}
	});	
}

$(window).on('load', (function(){
	
	//페이지 새로 고침 후 선택했던 카테고리 유지
	const searchParams = new URLSearchParams(location.search);
	let catename = searchParams.get("catename");
	if(catename == null) {catename = '검색';}
	$('#search_catename').val(catename).prop('selected', true);

	setcatedetail();	
	
	let catedetailcode = searchParams.get("catedetailcode");
	if(catedetailcode == null) {catedetailcode = 0;}
	$('#search_catedetailname').val(catedetailcode).prop('selected', true);
	

})); //on ready
	
	$(document).on('change', '#search_catename',(setcatedetail));

	//분류별 검색 버튼 클릭
	$(document).on('click', '#search_btn', (function () {
		let catename = $('#search_catename option:selected').val();
		let catedetailcode = $('#search_catedetailname option:selected').val();
		
		$.ajax({
			type: "get",
			url: "/mainranking",
			data: { 'catedetailcode': catedetailcode },
			type: 'post',
			dataType: 'json',
			success: function (response) {
				console.log("rankboard 새로고침");
                $('.rankboard_list_box').remove();
                for (var i in response) {
                    console.log(response[i].classtitle);
                    var rankBoardList = $('<div class="rankboard_list_box"><div class="rankboard_list_number"><h1>'+(Number(i)+1)+'</h1></div><div class="rankboard_list_inner"><a href="/ranking?postnum='+response[i].postnum+'"><div id="rankboard_post_title" >'+response[i].posttitle+'</div></a><div id="rankboard_post_detail">'+response[i].author+' - '+response[i].classtitle+'</div></div></div>');
                    $('#rankboard_list').append(rankBoardList);
                } 
			}
		});	
		
	}));
	
	
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
	                <div id="intro_inner_space">
	                	<div id="intro_box">
		                	<div id="intro_main">흩어진 지식을 지금 여기,</div>
		                	<div id="intro_main">한데 모아!</div>
	                	</div>
	 	               	<div id="intro_box">한 눈에 볼 수 있는 카테고리별 자료추천 랭킹 서비스 커뮤니티</div>
	                </div>
	                
                    <div id="search_box">
                        <input id="search" placeholder="게시글 통합 검색" type="search"/>
                    </div>
                    <div id="search_icon_area">
                        <a href="#"><img src="css/images/search_icon.png"/></a>
                    </div>
                    <div id="search_box_space"></div>
                
                </div>
                <hr>
                
                <div id="all_content_area">
                <div id="all_content">
                
	                <div id="ranking_content">
							
							<div class="index_name">
	                            <h2>RANKING</h2>
	                    	</div>
	                    	
	                    	
							<div class= "select_area">
							<div class="content_head_item">
								TITLE <select id="search_catename" name="search_catename">
									<option value="검색">검색</option>
									<c:forEach var="name" items="${catename}" varStatus="i">
										<option value="${name}">${name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="content_head_item">
								DETAIL <select id="search_catedetailname"
									name="search_catedetailname">
									<option value="0">검색</option>
									<c:forEach var="cate" items="${cateAll}" varStatus="i">
										<option value="${cate.catedetailcode}">${cate.catedetailname}</option>
									</c:forEach>
								</select>
							</div>
							<div class="content_head_item">
								<img id="search_btn" src="/css/images/search_icon.png" style="cursor:pointer"/>
							</div>
							</div>



							<!-- 전체회원 리스트 -->
	                    <div id="rankboard_area_box">
                        <div id="rankboard_area">
                            <div id="rankboard_title">실시간 강의랭킹</div>
                            <div class="inner_space"></div>
                            <div id="rankboard_list">
                                <!-- 반복할 게시물 시작 -->
 
                                <!-- 반복할 게시물 종료 -->
                            </div>
                        </div>
                        </div>
								
							<div id="button_area">
								<a href="/ranking?catedetailcode=10&page=1" id="all_select">
									전체보기
								</a>
							</div>
		         	</div>
	                
	                
	                
	         
			         
			      </div>   
			      <div id="all_content">   
			         
		                <div id="community_content">
								
								<div class="index_name">
		                            <h2>COMMUNITY</h2>
		                    	</div>
			                
			                <!-- 전체회원 리스트 -->
		                    <div id="community_content_box">
		                    
									<div class="adminmember_title_container">
				                    	<div class="post_title_item" >분류</div>	
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
											<div class="post_item" >${dto.posttitle}</div>
											<div class="post_item" >${dto.viewcount}</div>
										</div>
									</div>
								</c:forEach>
							</div>
							
							<div id="button_area">
							<a href="/community?catedetailcode=8&page=1" id="all_select">
								전체보기
							</a>	
							</div>
			         </div>
			         
			         
			         
		                <div id="notice_content">
								
								<div class="index_name">
		                            <h2>공지사항</h2>
		                    	</div>
			                
			                <!-- 전체회원 리스트 -->
		                    <div id="notice_content_box">
		                    
									<div class="adminmember_title_container">
				                    	<div class="notice_title_item" >등록일</div>
										<div class="notice_title_item" >제목</div>
										<div class="notice_title_item" >운영자</div>
									</div>
									
								<c:forEach items="${noticelist }" var="dto">
									<div class="adminmember_check_container">
										<div class="adminmember_container">
											<div class="notice_item" >${dto.regdate}</div>
											<div class="notice_item" >${dto.title}</div>
											<div class="notice_item" >운영자</div>
										</div>
									</div>
								</c:forEach>
							</div>
								<div id="button_area">
								<a href="/notice" id="all_select">
									전체보기
								</a>	
								</div>
			         </div>	         	                         
       		 	 </div> 
       		 	 
       		 	 </div>
       		 	 <div id="index_inner_space"></div>
            </div>
        </div>
    </div>
</body>
</html>