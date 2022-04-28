<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>한데모아 - 커뮤니티</title>
<link rel='stylesheet' type='text/css' href='css/index.css'>
<link rel='stylesheet' type='text/css' href='css/communityboard.css'>
<link rel='stylesheet' type='text/css' href='css/community.css'>
<!-- 노토산스 폰트 시작-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<!-- 노토산스 폰트 종료-->
<script src="/jquery-3.6.0.min.js"></script>
<script src='/js/index.js'></script>
<script>
    $(document).ready(function () {

    let cate;
    let rankRefresh;
    
    start1();

    $('#postsearch_btn').click(function () {
        let postsearch = $('#postsearch').val();
        let code = $('#code').val();
        console.log("postsearch:"+postsearch);
        console.log("code:"+code);
        location.replace("/communitysearch?catedetailcode="+code+"&page=1&postsearch="+postsearch);        
    });

    function start1() {
        console.log("start1");
        $.ajax({
            type: "get",
            url: "/catename",
            data: {'catedetailcode': $('#rank_post_list_title').html()},
            dataType: "json",
            success: function (response) {
                $('#rank_post_list_title').html(response.catedetailname+' 커뮤니티 게시판');
            }
        });
        console.log($('#tt').html());
    }

    $(".category_name").click(function () {
        $(this).siblings().css("background-color", "rgb(214, 214, 214)");
        $(this).siblings().children().css("color", "black");
        $(this).css("background-color", "#12379e");
        $(this).children().css("color", "#ffffff");
        $.ajax({
            type: "get",
            url: "/catelist",
            data: {'catename': $(this).children().html()},
            dataType: "json",
            success: function (response) {
                $('.category_list').remove();
                for (var i in response) {
                    var div = $('<div class="category_list"><a href="/community?catedetailcode='+response[i].catedetailcode+'&page=1"><p>'+response[i].catename+'</p></a></div>');
                    $('#cate_detail').append(div);
                }
            }
        });
    });

    // function rankBoardView() {
    //     console.log("값:", cate);
    //     $.ajax({
    //         type: "get",
    //         url: "/rankboard",
    //         data: {'catedetailname': cate},
    //         dataType: "json",
    //         success: function (response) {
    //             console.log("rankboard 새로고침");
    //             $('.rankboard_list_box').remove();
    //             for (var i in response) {
    //                 console.log(response[i].classtitle);
    //                 var rankBoardList = $('<div class="rankboard_list_box"><div class="rankboard_list_number"><h1>'+(Number(i)+1)+'</h1></div><div class="rankboard_list_inner"><a href="/community?postnum='+response[i].postnum+'"><h3>'+response[i].posttitle+'</h3></a><p>'+response[i].author+' - '+response[i].classtitle+'</p></div></div>');
    //                 $('#rankboard_list').append(rankBoardList);
    //             } 
    //         }
    //     });                        
    // }
});
    </script>
<style>
.nav_list_area:nth-child(3) {
	background-color: #12379e;
	border-radius: 15px;
}

.nav_list_area:nth-child(3) h4 {
	color: white;
}
</style>
</head>
<body>
	<div class="container">
		<!-- 네비게이션 start -->
		<div id="nav_area">
			<div id="nav_space">
				<div id="logo">
					<a href="/handemoa"> <img src="css/images/logo.png" />
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
                    <button id="nav_login_btn" onclick="location.href='/logout'" style="color: white; background-color: #ce4764">로그아웃</button>
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
				</div>
			</div>
		</div>
		<!-- 네비게이션 end -->
		<div id="content_container">
			<div id="content_container_space">
				<div id="search_area">
					<div id="search_box">
						<input id="search" placeholder="게시글 통합 검색" type="search" />
					</div>
					<div id="search_icon_area">
						<a href="#"><img src="css/images/search_icon.png" /></a>
					</div>
					<div id="search_box_space"></div>
				</div>
				<div class="inner_space"></div>
				<div id="content">
					<!-- 여기서 내용 시작 -->
					<div id="category_area">
						<!-- 반복할 상위 카테고리 시작 -->
						<div class="category_name" style="cursor: pointer;">
							<p>고등</p>
						</div>
						<div class="category_name" style="cursor: pointer;">
							<p>개발</p>
						</div>
						<!-- 반복할 상위 카테고리 종료 -->
					</div>
					<div id="cate_detail">
						<!-- 반복할 하위 카테고리 시작 -->

						<!-- 반복할 하위 카테고리 종료 -->
					</div>
					<div class="inner_space"></div>
					<div id="rank_container">

						<div id="rank_post_list_area_box">
							<div id="rank_post_list_area">
								<div id="rank_post_list">
									<h2 id="rank_post_list_title">${catedetailcode}</h2>
									
									<div id="postsearch_area">
										<!-- 임시저장 버튼 숨김(로그인시 보임) -->
										<c:choose>
											<c:when test="${isLogOn == true && member!= null}">
												<button id="tempsavelist_btn">
													<a href="/communitytempsave?catedetailcode=${catedetailcode}&page=1&memberid=${member.id}"">임시저장목록</a>
												</button>
											</c:when>
											<c:otherwise>
			        	                &nbsp;
			        	                </c:otherwise>
										</c:choose>
										<button id="rankwrite_btn">
											<a href="/communitywrite?catedetailcode=${catedetailcode}">글쓰기</a>
										</button>
										<div id="postsearch_box">
											<input type="hidden" value="${catedetailcode}" id="code">
											<input id="postsearch" type="search" />
										</div>
										<div id="postsearch_icon_area">
											<img src="css/images/search_icon.png" id="postsearch_btn" />
										</div>

										<div id="postsearch_box_space"></div>
									</div>
									

									<div id="rank_post">
										<!-- 반복할 게시물 시작 -->
										<c:forEach items="${ postlist }" var="list">
											<div class="rank_post_box">
												<div class="rank_post_box2">

													<div class="rank_post_space"></div>
													<div class="rank_post_inner">
														<div class="rank_post_inner_1">
															<p>${list.regdate}</p>
															<a href="/profile?nickname=${list.nickname}"><p>${list.nickname}</p></a>
															
														</div>
														<div class="rank_post_inner_2">
															<a href="/communitypost?postnum=${list.postnum}">
																<h3>${list.posttitle}</h3>
															</a>
														</div>
													</div>
													<div class="like_view_count_box" >
													<p>좋아요:${list.likecount}</p>
													<p>조회수:${list.viewcount}</p>
													</div>
												</div>
											</div>
										</c:forEach>
										<!-- 반복할 게시물 종료 -->
									</div>
									
									
									<div class="inner_space"></div>
									

									
									<div id="paging">
                                    <c:if test="${paging.previousPageStart != -1}">
                                        <div class="paging_btn"><a href="/communitysearch?catedetailcode=${catedetailcode}&page=${paging.previousPageStart}&postsearch=${postsearch}">이전</a></div>
                                    </c:if>
                                    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="num">
                                        <div class="paging_btn"><a href="/communitysearch?catedetailcode=${catedetailcode}&page=${num}&postsearch=${postsearch}">${num}</a></div>
                                    </c:forEach>
                                    <c:if test="${paging.nextPageStart != -1}">
                                        <div class="paging_btn"><a href="/communitysearch?catedetailcode=${catedetailcode}&page=${paging.nextPageStart}&postsearch=${postsearch}">다음</a></div>
                                    </c:if>
                                </div>
                                <div class="inner_space"></div>
								</div>
							</div>
						</div>	


							<div id="rankboard_area_box">
								<div id="rankboard_area">
									<div id="rankboard_title">베스트 게시글</div>
									<div class="inner_space"></div>
									<div id="rankboard_list">
										<!-- 반복할 게시물 시작 -->
										<c:forEach items="${ rankingboard }" var="list">
											<c:set var="i" value="${i+1}" />
											<div class="rankboard_list_box">
												<div class="rankboard_list_number">
													<h1>${i}</h1>
												</div>
												<div id="rankboard_post_title">
													<a href="/communitypost?postnum=${list.postnum}">
														${list.posttitle} </a>
												</div>
											</div>
										</c:forEach>
										<!-- 반복할 게시물 종료 -->
									</div>
								</div>
							</div>


						</div>
						<!-- 여기서 내용 종료 -->
					</div>
				</div>
						<div class="inner_space"></div>
						<div class="post_space"></div>
			</div>
		</div>
</body>
</html>