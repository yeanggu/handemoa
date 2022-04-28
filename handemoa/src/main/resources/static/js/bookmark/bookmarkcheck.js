	/**
 * 
 */ 

 	//북마크 버튼 클릭 이벤트
	$(document).on('click', '.bookmark_btn', (function () {
		let check = $(this).attr('id');
		if(check == 'bookmark_on'){
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
			});
		}
		else if(check == 'bookmark_off'){
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
					//console.log(result);
					if(result == 1){
					$("#bookmark").empty();
					$("#bookmark").html("<img class='bookmark_btn' id='bookmark_on' src='/css/images/bookmark_on.png'/>");
					}
					else{
						alert("북마크 on 실패")
					}						
				}
			}); //ajax end
			
		}
	}));

	//로그인 안한 상태 북마크 버튼 클릭
	$(document).on('click', '#bookmark_logout', (function () {
		$("#modal_bookmarkcheck").show();	
	}));
	//모달창 네 버튼 ->로그인 화면으로 이동
	$(document).on('click', '#modal_bookmarkcheck_ok_btn', (function () {
		$("#modal_bookmarkcheck").hide();
		location.href = '/login';	
	}));
	//모달창 아니요 버튼
	$(document).on('click', '#modal_bookmarkcheck_close_btn', (function () {
		$("#modal_bookmarkcheck").hide();
	}));
