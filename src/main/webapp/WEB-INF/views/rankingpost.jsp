<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>한데모아 - Ranking</title>
    <link rel='stylesheet' type='text/css' href='css/index.css'>
    <link rel='stylesheet' type='text/css' href='css/report.css'>
    <link rel='stylesheet' type='text/css' href='css/ranking.css'>
    <link rel='stylesheet' type='text/css' href='css/bookmark/bookmarkcheck.css'>
    <link rel='stylesheet' type='text/css' href='css/bookmark/loginmodal.css'>
    <!-- 노토산스 폰트 시작-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <!-- 노토산스 폰트 종료-->
    <script src="/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src='/js/report.js'></script>
    <script type="text/javascript" src='/js/bookmark/bookmarkcheck.js'></script>
    <script type="text/javascript" src='/js/bookmark/loginmodal.js'></script>
    <script src='/js/index.js'></script>
    <link rel='stylesheet' type='text/css' href='css/alarm.css'>
    <script src='/js/alarm.js'></script>
    <script>
    $(document).ready(function () {

    let cate;
    let rankRefresh;
    strCut();

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
                $('#rank_post_list_title').html(response.catedetailname+' 게시판');
                catecode = response.catedetailcode;
                console.log(catecode);
            }
        });
        console.log($('#tt').html());
    }

    function strCut() {
        let linkstr = $('#rank_post_link').html();
        if (linkstr.length > 45) {
            let cutlinkstr = linkstr.substr(0, 45) + '...';
            $('#rank_post_link').html(cutlinkstr);
        }

        let titlestr = $('#rank_post_title').children().html();
        if (titlestr.length > 20) {
            let cuttitlestr = titlestr.substr(0, 20) + '...';
            $('#rank_post_link').children().html(cuttitlestr);
        }
    }

    $('#comment_insert_btn').click(function () {
        console.log("댓글작성 ajax 작동");
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/commentinsert",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            data: { 'postnum': $('#comment_postnum').val(),
                    'memberid': $('#comment_memberid').val(),
                    'commentcontent': $('#commentcontent').val(),
                    'postmemberid': $('#post_memberid').val()},
            success: function (a) {
                if (a == 1) {
                    alert("댓글이 작성되었습니다.");
                    location.reload();
                }
                else if (a == -1) {
                    alert("로그인이 필요합니다.");
                }
            }
        });
    });
    

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
                    var div = $('<div class="category_list"><a href="/ranking?catedetailcode='+response[i].catedetailcode+'&page=1"><p>'+response[i].catename+'</p></a></div>');
                    $('#cate_detail').append(div);
                }
            }
        });
    });

    $("#deletepost_btn").click(function () {
        console.log($("#getPostnum").val());
        if (confirm("정말 삭제하시겠습니까?")) {
            $.ajax({
                type: "get",
                url: "/deletepost",
                data: {'postnum': $("#getPostnum").val(),
                        'postid' : $("#post_memberid").val(),
                        'userid' : $("#userid").val()},
                dataType: "json",
                success: function (a) {
                    if (a == 1) {
                        alert("삭제되었습니다.");
                        location.replace("/ranking?catedetailcode="+catecode+"&page=1");
                    }
                    else if (a == -1) {
                        alert("로그인이 필요합니다.");
                    }
                    else if (a == -2) {
                        alert("권한이 없습니다.");
                    }
                }
            });
        }
    });

    $('#updatepost_btn').click(function (e) {
        console.log("수정실행");
        let postid = $('#post_memberid').val();
        let userid = $('#userid').val();
        let postnum = $('#getPostnum').val();
        console.log($('#post_memberid').val());
        console.log($('#userid').val());

        if ( postid == userid ) {
            location.replace("/rankingedit?postnum="+postnum);
        }else {
            alert("실패");
        }

        
    });

    $('#like_btn').click(function () { 
        likepost(1);
        console.log("좋아요 버튼 클릭");     
    });
    $('#dislike_btn').click(function () { 
        likepost(-1);
        console.log("싫어요 버튼 클릭");
    });

    function likepost(likestatus) {
        let likeCountArray = [];
        console.log("postnum:"+$("#getPostnum").val());
        console.log("userid:"+$("#userid").val());
        console.log("likestatus:"+likestatus);
        $.ajax({
            type: "GET",
            url: "/likepost",
            data: {'postnum': $("#getPostnum").val(),
                    'userid': $("#userid").val(),
                    'likestatus': likestatus},
            dataType: "json",
            success: function (result) {
                for (var i in result) {
                    if (result[i] === -1) {
                        alert("로그인이 필요합니다.");
                        return;
                    } else {
                        likeCountArray.push(result[i]);
                        console.log(result[i]);

                        $('#likecount_result').html(likeCountArray[0]);
                        $('#dislikecount_result').html(likeCountArray[1]);
                    }
                }
            }
        });
    }



});
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
                    <button id="nav_login_btn" onclick="location.href='/logout'" style="color: white; background-color: #ce4764;">로그아웃</button>
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
                        <a href="/search"><img src="css/images/search_icon.png"/></a>
                    </div>
                    <div id="search_box_space"></div>
                </div>
                <div class="inner_space"></div>
                <div id="content">
                <!-- 여기서 내용 시작 -->
                    <div id="category_area">
                        <!-- 반복할 상위 카테고리 시작 -->
                        <div class="category_name" style="cursor:pointer;">
                            <p>고등</p>
                        </div>
                        <div class="category_name" style="cursor:pointer;">
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
                                <div class="post_space"></div>
                                <div id="rank_post">
                                    <!-- 반복할 게시물 시작 -->
                                    <img class="post_writer_profile" src="css/images/${vo.profileimg}">
                                    <div id="rank_post_profile_area">
                                        <input type="hidden" value="${postnum}" id="getPostnum">
                                        <div id="rank_post_profile">
                                            <p><a href="/profile?nickname=${vo.nickname}" target="_blank">${vo.nickname}</a></p>
                                            <input type="hidden" value="${memberid}" id="post_memberid">
                                            <input type="hidden" value="${member.id}" id="userid">
                                        </div>
                                        <div id="rank_post_update">
                                            <button class="btn" id="deletepost_btn">삭제</button>
                                            <button class="btn" id="updatepost_btn">수정</button>
                                            <div class="report_btn">
                                                <button class="reportpostbtn" id="2post${postnum}">신고하기</button>
                                            </div>
                                            <div id="bookmark">
							                  <!-- 로그인 / 비로그인-->
							                  <c:choose>
							                     <c:when test="${isLogOn == true && member!= null}">
							                        <!-- 로그인 되어 있으면 북마크 있는지 체크하고 on/off-->
							                        <c:choose>
							                                  <c:when test="${(checkBookMark == 1) && (memberid != member.id)}">
							                              <img class="bookmark_btn" id="bookmark_on" src="/css/images/bookmark_on.png"/>
							                                  </c:when>
							                                <c:otherwise>
							                              <img class="bookmark_btn" id="bookmark_off" src="/css/images/bookmark_off.png"/>
							                                </c:otherwise>
							                             </c:choose>
							                     </c:when>
							                     <c:otherwise>
							                             <img class="bookmark_btn" id="bookmark_logout" src="/css/images/bookmark_off.png"/>
							                          </c:otherwise>
							                  </c:choose>
							               </div>
                                        </div>
                                    </div>
                                    <div id="rank_post_date">
                                        <p>${vo.regdate}</p>
                                        <p>조회수 ${vo.viewcount}</p>
                                    </div>
                                    <div id="rank_post_class">
                                        <h4>${vo.author}　-</h4>
                                        <h4>　${vo.classtitle}</h4>
                                    </div>
                                    <div id="rank_post_link_box">
                                        <a href="${vo.link}" target="blank"><p id="rank_post_link">링크 : ${vo.link}</p></a>
                                    </div>
                                    <div id="rank_post_title">
                                        <h1>${vo.posttitle}</h1>
                                    </div>
                                    <div id="rank_post_content_box">
                                        <textarea id="rank_post_content" readonly="readonly">${vo.content}</textarea>
                                    </div>
                                    <div id="rank_post_likecount">
                                        <button id="like_btn">좋아요<h3 id="likecount_result">${likecount}</h3></button>
                                        <button id="dislike_btn">싫어요<h3 id="dislikecount_result">${dislikecount}</h3></button>
                                    </div>
                                    <!-- 반복할 게시물 종료 -->
                                </div>
                                <div class="post_space"></div>
                                <div>
                                    <!-- 댓글 작성폼 시작 -->
                                    <div id="comment_insert_box">
                                        <input type="hidden" name="memberid" id="comment_memberid" value="${member.id}"/>
                                        <input type="hidden" name="postnum" id="comment_postnum" value="${postnum}"/>
                                            <textarea name="commentcontent" id="commentcontent"></textarea>
                                        <button id="comment_insert_btn">작성</button>
                                    </div>
                                    <!-- 댓글 작성폼 종료 -->
                                </div>
                                <div class="post_space"></div>
                                <div>
                                    <!-- 반복할 댓글 시작 -->
                                    <c:forEach items="${ comment }" var="comment" >
                                        <div class="comment_box">
                                            <div class="comment_inner1">
                                                <div class="comment_inner1_box1">
                                                    <p class="comment_date">${comment.regdate}</p>
                                                    <h4 class="comment_id">${comment.nickname}</h4>
                                                </div>
                                                <div class="comment_inner1_box2">
                                                    <div class="report_btn">
                                                        <button class="reportcommentbtn" id="2comment${comment.commentnum}">신고하기</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <textarea class="comment_textarea" readonly="readonly">${comment.commentcontent}</textarea>
                                        </div>                                        
                                    </c:forEach>
                                    <!-- 반복할 댓글 종료 -->
                                </div>
                                <div class="post_space"></div>
                            </div>
                        </div>
                        </div>
                        
                       <div id="rankboard_area_box">
                        <div id="rankboard_area">
                            <div id="rankboard_title">실시간 강의랭킹</div>
                            <div class="inner_space"></div>
                            <div id="rankboard_list">
                                <!-- 반복할 게시물 시작 -->
                                <c:forEach items="${ rankingboard }" var="list" >
                                <c:set var="i" value="${i+1}"/>
                                <div class="rankboard_list_box">
                                    <div class="rankboard_list_number">
                                        <h1>${i}</h1>
                                    </div>
                                    <div class="rankboard_list_inner">
                                        <div id="rankboard_post_title">
                                        	<a href="/rankingpost?postnum=${list.postnum}" >
                                           		 ${list.posttitle}
                                            </a>
                                        </div>
                                        <div id="rankboard_post_detail">
                                        	${list.author} - ${list.classtitle}
                                        </div>	
                                    </div>
                                </div>
                                </c:forEach>
                                <!-- 반복할 게시물 종료 -->
                            </div>
                        </div>
                        </div>
                        
                    </div>
                <!-- 여기서 내용 종료 -->
                	<div id="modal_report">
						<div class="modal_layer">
							<div class="modal_content">
								<div class="modal_content_title">
									<h1>신고하기</h1>
								</div>
								<div class="modal_content_radio">
									<input type="radio" name="radioTxt" value="1" id="radio1" checked><label for="radio1">헌법 및 사회질서, 미풍양속에 반함</label><br>
									<input type="radio" name="radioTxt" value="2" id="radio2"><label for="radio2">음란성</label><br>
									<input type="radio" name="radioTxt" value="3" id="radio3"><label for="radio3">개인정보 침해 및 명예훼손</label><br>
									<input type="radio" name="radioTxt" value="4" id="radio4"><label for="radio4">불법 촬영</label><br>
									<input type="radio" name="radioTxt" value="5" id="radio5" ><label for="radio5">불법 광고</label><br>
									<input type="radio" name="radioTxt" value="6" id="radio6"><label for="radio6">도배성</label><br>
									<input type="radio" name="radioTxt" value="7" id="radio7"><label for="radio7">저작권 침해</label><br>
									<input type="radio" name="radioTxt" value="8" id="radio8"><label for="radio8">폭력/잔혹/혐오</label><br>
									<input type="radio" name="radioTxt" value="9" >기타(직접 입력 - 100자 이내) <span id="reportTxt_count">(0 / 100)</span><br>
								</div>
								<div class="modal_content_text">
									<textarea id="reportTxt" maxlength="100" readonly></textarea>
								</div>
                                <div class="modal_content_footer">
                                허위신고를 할 경우 신고자의 활동에 제한을 받을 수 있습니다.<br>
                                이 점 유의해 주시기 바랍니다.
                                </div>
								<div class="modal_report_btn">
									<button type="button" id="modal_report_close_btn">취소</button>
									<button type="button" id="modal_report_ok_btn">확인</button>
								</div>
							</div>
						</div>
					</div>
					<%@ include file="/WEB-INF/views/bookmark/loginmodal.jsp" %>   		               
		               
                </div>
					<div class="post_space"></div>
            </div>
        </div>
    </div>
</body>
</html>