<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>Page Title</title>

<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<link rel='stylesheet' type='text/css' href='css/alarm.css'>
<script src='/js/alarm.js'></script>
<link rel='stylesheet' type='text/css' href='/css/index.css'>
<link rel='stylesheet' type='text/css'
	href='/css/communitypostwrite.css'>
<script src='js/index.js'></script>

<script>
$(document).ready(function(){

		// jquery code
	});
	
    function title_content_check() { // 게시글 제목 공백 여부 확인
    	var title_check = document.commu_updateform.posttitle.value;
    	var content_check = document.commu_updateform.content.value;
    	var form = document.commu_updateform;
    	
    		if(title_check.trim()==""){ // 제목 미입력 시 알람, 제목 앞뒤 공백제거
    	    	alert("제목을 입력하세요.");
    	    	form.posttitle.focus();
    	    	return false;
    	    }
    		 
    		if(content_check.trim()==""){ // 내용 미입력 시 알람, 내용 앞뒤 공백제거
    		    alert("내용을 입력하세요."); // 데이터 전송 시에는 제거하지 않음.
    		    form.content.focus();
    		    return false;
    		}
    	
    		document.getElementById("posttitle").value = title_check.trim(); // 제목 앞뒤 공백 제거 
    		
   }
    
    function postsave(){ // 게시글 작성
    	title_content_check();
    	document.commu_updateform.submit();
    }

    </script>

<style>
#content {
	width: 855px;
	height: 800px;
	background-color: white;
	margin-bottom: 40px;
	border-radius: 20px;
}

/*해당 페이지 네비바*/
.nav_list_area:nth-child(3) {
	background-color: #12379e;
	border-radius: 15px;
}

.nav_list_area:nth-child(3) h4 {
	color: white;
}

.category_item {
	background-color: rgb(209, 209, 209);
	margin-top: 50px;
}

.category_item:nth-child(1) {
	width: 200px;
	text-align: center;
	margin-left: 50px;
}

.category_item:nth-child(2) {
	width: 400px;
	text-align: center;
	margin-left: 10px;
}

.writeform_container {
	margin-left: 50px;
	margin-top: 50px;
	width: 754px;
}

.commutextarea {
	margin-top: 20px;
	resize: none;
}

.btn_container {
	margin-top: 20px;
	display: flex;
	justify-content: flex-end;
}

.btn_item {
	
}

.btn_item:nth-child(1) {
	width: 100px;
}

.btn_item:nth-child(2) {
	width: 100px;
	margin-left: 10px;
}

.btn_item:nth-child(3) {
	width: 100px;
	margin-left: 10px;
}
</style>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<a href="#"><img src="css/images/search_icon.png" /></a>
					</div>
					<div id="search_box_space"></div>
				</div>
				<div id="inner_space"></div>
				<div id="content">
					<!-- 컨텐츠 넣어주세요! -->
					<!-- 글 작성 상단 카테고리 -->
					<div class="category_container" style="display: flex">
						<div class="category_item">
							<h2>커뮤니티</h2>
						</div>
						<div class="category_item">
							<h2>게시글 수정</h2>
						</div>
					</div>
					<div class="writeform_container">
						<form action="/update" method="post" name="commu_updateform">
							<input type="hidden" id="postnum" name="postnum"
								value="${communitydto.postnum}" /> <input type="hidden"
								id="tempsave" name="tempsave" value="${communitydto.tempsave}" />
							<%-- <input type="hidden" id="regdate" name="regdate" value="${communitydto.regdate}" /> --%>
							<div class="form-group">
								<!-- placeholder 속성 입력한 데이터가 없는 경우 배경으로 나타난다.실제적으로 입력을 100자까지로 지정 -->
								<textarea id="posttitle" name="posttitle" class="commutexttitle"
									placeholder="글 제목" rows="1" style="width: 750px; resize: none;"
									maxlength="30">${communitydto.posttitle}</textarea>
								<div class="form-group">
									<!--  여러줄의 데이터를 입력하고 하고자 할때 textarea 태그를 사용한다. -->
									<!--  textarea 안에 있는 모든 글자는 그대로 나타난다. 공백문자, tag, enter -->
									<textarea class="commutextarea" name="content"
										placeholder="글 내용" rows="20" cols="105" maxlength="2000">${communitydto.content }</textarea>
								</div>
								<div class="btn_container">
									<div class="btn_item">
										<button type="button" class="btn_item"
											onclick="history.back()" style='cursor: pointer;'>취소</button>
									</div>
									<div class="btn_item">
										<button type="button" class="btn_item"
											onclick="javascript:postsave()" style='cursor: pointer;'>등록</button>
									</div>
								</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="inner_space"></div>
		<div class="post_space"></div>
	</div>
</body>
</html>