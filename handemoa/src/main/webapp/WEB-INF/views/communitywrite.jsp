<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>한데모아 - 커뮤니티</title>
    <link rel='stylesheet' type='text/css' href='css/community.css'>
    <link rel='stylesheet' type='text/css' href='css/index.css'>
    <!-- 노토산스 폰트 시작-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <!-- 노토산스 폰트 종료-->
    <script src="/jquery-3.6.0.min.js"></script>
    <script src='/js/index.js'></script>
    <script>
    $(document).ready(function () {

    let cate;
    let rankRefresh;
    let catecode;
    
    start1();

    function start1() {
        console.log("start1");
        $.ajax({
            type: "get",
            url: "/catename",
            data: {'catedetailcode': $('#rank_post_list_title').html()},
            dataType: "json",
            success: function (response) {
                $('#rank_post_list_title').html(response.catedetailname+' 커뮤니티 게시판 글쓰기');
                catecode = response.catedetailcode;
                console.log(catecode);
            }
        });
        console.log($('#tt').html());
    }

    $('#write_btn').click(function () {
        console.log("저장");
        write(1);
    });
    $('#writetemp_btn').click(function () {
        console.log("임시");
        write(0);
    });

    function write(tempsave) {
        console.log($('#post_content').val());
        console.log($('#memberid').val());
        $.ajax({
        type: "POST",
        dataType: "json",
        url: "/commuinsert",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: { 'catedetailcode': catecode,
	        	 'memberid': $('#memberid').val(),
	             'author': $('#author').val(),
	             'classtitle': $('#classtitle').val(),
	             'posttitle': $('#posttitle').val(),
	             'content': $('#post_content').val(),
	             'link': $('#link').val(),
	             'thumbnail': $('#thumbnail_add').val(),
	             'tempsave': tempsave},
        success: function (a) {
            alert("글이 등록되었습니다.");
            if (a == 1) {
                console.log("새글작성 성공");
                location.replace("/community?catedetailcode="+catecode+"&page=1");
                }
            }        
        });
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
});
    </script>
    <style>
    
    .btn_container{
margin-top: 20px;
display: flex;
justify-content: flex-end;
}
    
    .btn_item{
}

.btn_item:nth-child(1) { width:100px; }
.btn_item:nth-child(2) { width:100px; margin-left: 10px;}
.btn_item:nth-child(3) { width:100px; margin-left: 10px;}
    
    #rank_post {
    width: 100%;
    height: 450px;
}
    
        .nav_list_area:nth-child(3){
   background-color: #12379e;
   border-radius: 15px;
}

.nav_list_area:nth-child(3) h4{
   color:white;
}

#post_content{
   resize: none;
}
    
    </style>
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
                        <a href="/community?catedetailcode=10&page=1"> <!-- 해당 링크 이동 -->
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
                <div class="inner_space"></div>
                <div id="content">
                <!-- 여기서 내용 시작 -->
                    <div id="category_area">
                        <!-- 반복할 상위 카테고리 시작 -->
                        <div class="category_name">
                            <p>고등</p>
                        </div>
                        <div class="category_name">
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
                        <div id="rank_post_list_area">
                            <div id="rank_post_list">
                                <h2 id="rank_post_list_title">${catedetailcode}</h2>
                                <div class="inner_space"></div>
                                <div id="rank_post">
                                    <!-- 반복할 게시물 시작 -->
                                        <input type="hidden" name="divisioncode" value="1" id="divisioncode">
                                        <input type="hidden" name="memberid" value="${member.id}" id="memberid">
                                        <input type="hidden" name="author" id="author">
                                        <input type="hidden" name="classtitle" id="classtitle"><br>
                                        <input type="text" maxlength='30' name="posttitle" id="posttitle" placeholder="제목을 입력하세요." style="width: 300px;"><br>
                                        <textarea name="content" id="post_content" placeholder="내용을 입력하세요." style="margin-top:20px ;"></textarea><br>
                                        <input type="hidden" name="link" id="link"><br> 
                                        <input type="hidden" name="thumbnail" id="thumbnail"><br>
                                        <input type="hidden" name="tempsave" value="1" id="tempsave">         
                                    <!-- 반복할 게시물 종료 -->
                                </div>
                                <div class="inner_space"></div>
                                <div>
                                    <!-- <button id="writetemp_btn">임시작성</button> -->
                                    <!-- <button id="write_btn">작성</button> -->
                                </div>
                                <div class="btn_container">
	                    <div class="btn_item" ><button type="button" class="btn_item" onclick="javascript:history.back()" style='cursor:pointer;'>취소</button></div>
	                    <div class="btn_item" ><button type="button" class="btn_item" id="writetemp_btn" style='cursor:pointer;'>임시저장</button></div>
	                    <div class="btn_item" ><button type="button" class="btn_item" id="write_btn" style='cursor:pointer;'>저장</button></div>
	                    </div>
                                <div>
                                    <%-- <button><a href="/community?catedetailcode=${catedetailcode}&page=1">목록</a></button> --%>
                                </div>
                                <div class="inner_space"></div>
                            </div>
                        </div>
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
                <!-- 여기서 내용 종료 -->
                </div>
            </div>
        </div>
    </div>
</body>
</html>