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
<title>한데모아 - 공지관리</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/index.css'>
<link rel='stylesheet' type='text/css' href='css/notice.css'>
<link rel='stylesheet' type='text/css' href='css/adminnotice.css'>
<link rel='stylesheet' type='text/css' href='css/paging.css'>
<script src='/js/index.js'></script>
<script src="/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

/*
		//페이지 이동
		function movePage(currentPage){
		  
		  var url = "/adminnotice";
		  url = url + "?currentPage="+currentPage;
		  
		  location.href=url;
		}
*/


		//공지 선택 삭제
		function deleteValue(){
			var url = "deleteselect";
	     	var valueArr = new Array();
	     	var list = $("input[name='notice_select']");
	     	for(var i=0; i<list.length;i++){
	     		if(list[i].checked){
	     			valueArr.push(list[i].value);
	     		}
	     	}
	     	if(valueArr.length == 0){
	     		alert("삭제할 항목을 선택하세요");
	     	}else{
	     		var chk = confirm("정말 삭제하시겠습니까?");
		     		if(chk==true){
			     		$.ajax({
			    		    url : url,
			    		    type : "POST",
			    		    traditional:true,
			    		    data : { valueArr : valueArr },
			    		    success : function(jdata){
			    		    	if(jdata = 1) {
			    		    		alert("삭제 완료");
			    		    		location.replace("adminnotice");
			    		    	} else {
			    		    		alert("삭제 실패");
			    		    		location.replace("adminnotice");
			    		    	}
			    		    }
			     		});    
		     		}
	     	}
		}
		 
		//공지 선택 수정
		function updateValue(){
			var valueupd = new Array();
			var list = $("input[name='notice_select']:checked");
		     	for(var i=0; i<list.length;i++){
		     		if(list[i].checked){
		     			valueupd.push(list[i].value);
		     		}
		     	}
	     	if(valueupd.length == 0){
	     		alert("수정할 항목을 선택하세요");
	     	}else if(valueupd.length > 1){
	     		alert("수정할 항목을 1개만 선택하세요");
	     	}else{
	     		location.href='updateview?noticenum='+list[0].value;
	     	}	
	     }
		
		
		$(document).on('click', '#btnSearch', function(e){
			e.preventDefault();
			var url = "/adminnotice";
			url = url + "?keyword=" + $('#keyword').val();
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
						
						<div class="notice_name">
                            <h1>공지사항 관리</h1>
                    	</div>
                    
                    <!-- 관리자 주요기능 -->
	                <div id="notice_btn_area">
	                    <div id="notice_btn_box">
	                        <input type="button" id="notice_btn" style="cursor:pointer;" onclick="deleteValue();" value="삭 제">
	                        <input type="button" id="notice_btn" style="cursor:pointer;" onclick="updateValue();" value="수 정">
	                        <a href="/insertnotice"><input type="button" id="notice_btn" style="cursor:pointer;" value="등 록"></a>
	                    </div>
	               
						<!-- 공지사항 검색기능 -->
		           		<div id="admin_notice_search_area">
		                    <div id="notice_search_box">
		                       	 <input  type="text" name="keyword" id="keyword" value="${pagedto.keyword}"/>
		                    </div>
		                    <div id="notice_search_icon_area" >
		                        <div name="btnSearch" id="btnSearch" style="cursor:pointer;"><img src="css/images/search_icon.png"/></div>
		                    </div>
		                </div>
                     </div>
                    
                    <!-- 내용 -->
                    <div id="admin_content_box">	
					<c:forEach items="${Alllist }" var="dto">
						<div class="admin_notice_check_container">
						<input type="checkbox" id="notice_select" name="notice_select" value="${dto.noticenum }">
							<div class="admin_notice_container">
								<div class="admin_item" >${dto.regdate}</div>
								<div class="admin_item" ><a href="/adminnoticenum?noticenum=${ dto.noticenum }">${dto.title}</a></div>
								<div class="admin_item" >운영자</div>
							</div>
						</div>
					</c:forEach>
					</div>
	            
	                
                	<!-- 페이지 번호 넣을 공간입니다.-->
                   <div class="paginate">
				        <div class="paging">
				            <a class="direction_prev" href="adminnotice?currentPage=1${pagedto.searchTypeKeyword}"> &lt;&lt; </a> 
				            <c:if test="${pagedto.hasPreviousPage == true}">
				            	<a class="direction_prev" href="adminnotice?currentPage=${pagedto.currentPage-1}${pagedto.searchTypeKeyword}"> &lt;</a>
				            </c:if>
				            <c:if test="${pagedto.hasPreviousPage == false}">
				            	<a class="direction_prev" href="adminnotice?currentPage=${pagedto.currentPage}${pagedto.searchTypeKeyword}"> &lt;</a>
				            </c:if>
				            
				            <c:forEach begin="${pagedto.firstPage}" end="${pagedto.lastPage}" var="idx">
				                <a href="adminnotice?currentPage=${idx}${pagedto.searchTypeKeyword}" id="idx"><c:out value="${idx}" /></a>
				            </c:forEach>
				            
				            <c:if test="${pagedto.hasNextPage == true}">
				            	<a class="direction_next" href="adminnotice?currentPage=${pagedto.currentPage+1}${pagedto.searchTypeKeyword}"> &gt;</a>
				            </c:if>
				            <c:if test="${pagedto.hasNextPage == false}">
				            	<a class="direction_next" href="adminnotice?currentPage=${pagedto.currentPage}${pagedto.searchTypeKeyword}"> &gt;</a>
				            </c:if>
				            
				            <a class="direction_next" href="adminnotice?currentPage=${pagedto.lastPage}${pagedto.searchTypeKeyword}"> &gt;&gt; </a>
				        </div>
				    </div>
	                    

	               </div>
	            </div>  
	        </div>   
    </div>
</body>
</html>