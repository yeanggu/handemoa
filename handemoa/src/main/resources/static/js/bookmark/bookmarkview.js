	/**
 * 
 */ 
 $(window).on('load', (function(){	
	//현재 페이지 번호에 색상 변경
	const searchParams = new URLSearchParams(location.search);
	var currentpage = searchParams.get("currentpage");
	//console.log(currentpage);
	let page = 0;
	if(currentpage == null) {
		currentpage = 0;
	}
	else{
		page = (currentpage-1)%5;
	}
	$('.pagenum').eq(page).css("color", "rgb(77, 77, 255)");
	
})); //on ready

 	//분류별 검색 버튼 클릭
	$(document).on('click', '#search_btn', (function () {
		let searchtxt = $('#comment_searchdetail').val();
		location.href = '/bookmarkview?currentpage=1&searchtxt='+ searchtxt;
	}));
	
