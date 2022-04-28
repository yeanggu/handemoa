const navList = ['북마크', '강의랭킹', '커뮤니티', '공지사항'];

 	//통합 검색 버튼 클릭
	$(document).on('click', '#search_icon_area', (function () {
		let searchtxt = $('#search').val();
		//console.log(searchtxt);
		location.href = '/search?searchtxt=' + searchtxt;	
	}));