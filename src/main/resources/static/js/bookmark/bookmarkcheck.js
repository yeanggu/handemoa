	/**
 * 
 */ 
//북마크 버튼 클릭 이벤트
$(document).on('click', '#bookmark_on', (function () {
	//북마크 off하기 - delete
	$.ajax({
		url: "/bookmarkdelete",
		data: { 'postnum': $("#getPostnum").val(),
                'memberid' : $("#userid").val()},
		type: 'post',
		dataType: 'json',
		success: function (result) {
			if(result == 1){						
				$("#bookmark").empty();
				$("#bookmark").html("<img class='bookmark_btn' id='bookmark_off' src='/css/images/bookmark_off.png'/>");
			}
			else{
				alert("북마크 off 실패");
			}
		}
	}); //ajax end
	
}));	

$(document).on('click', '#bookmark_off', (function () {
	//북마크하는 아이디와 글 작성자가 같으면 북마크 불가능
	if($("#post_memberid").val() == $("#userid").val()){
		alert("내 게시물은 북마크가 불가능 합니다.");
		return;	
	}	
			
	//북마크 on하기 - insert
	$.ajax({
		url: "/bookmarkinsert",
		data: { 'postnum': $("#getPostnum").val(),
                'memberid' : $("#userid").val()},
		type: 'post',
		dataType: 'json',
		success: function (result) {
			if(result == 1){
			$("#bookmark").empty();
			$("#bookmark").html("<img class='bookmark_btn' id='bookmark_on' src='/css/images/bookmark_on.png'/>");
			}
			else{
				alert("북마크 on 실패")
			}						
		}
	}); //ajax end		
	
}));

//로그인 안한 상태 북마크 버튼 클릭
$(document).on('click', '#bookmark_logout', (function () {
	$("#modal_logincheck").show();	
}));


