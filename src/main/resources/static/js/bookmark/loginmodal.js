	/**
 * 
 */ 
//모달창 네 버튼 ->로그인 화면으로 이동
$(document).on('click', '#modal_logincheck_ok_btn', (function () {
	$("#modal_logincheck").hide();
	location.href = '/login';	
}));
//모달창 아니요 버튼
$(document).on('click', '#modal_logincheck_close_btn', (function () {
	$("#modal_logincheck").hide();
}));

