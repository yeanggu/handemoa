	/**
 * 
 */ 
 //세부 카테고리 등록 함수
 function setcatedetail(){
	let catename = $('#search_catename option:selected').text();
//	console.log(catename);
	//세부카테고리초기화
	$('#search_catedetailname').html('<option value="0">전체검색</option>');
	//부분 카테고리에 해당하는 세부카테고리 조회
	$.ajax({
		url: "/selectcate",
		data: { 'catename': catename },
		type: 'post',
		dataType: 'json',
		success: function (result) {
			for(var i in result){
				//console.log(" i " + result[i].catedetailcode + "result[i]" + result[i].catedetailname);
				$('#search_catedetailname').append('<option value="' + result[i].catedetailcode + '">' + result[i].catedetailname +'</option>')
			}			
		}
	});	
}

 $(window).on('load', (function(){
	
	//페이지 새로 고침 후 선택했던 카테고리 유지
	const searchParams = new URLSearchParams(location.search);
	let divisioncode = searchParams.get("divisioncode");
	if(divisioncode == null) divisioncode = 0;
	$('#divisioncode').val(divisioncode).prop('selected', true);
	
	let catename = searchParams.get("catename");
	if(catename == null) {catename = '전체검색';}
	$('#search_catename').val(catename).prop('selected', true);

	setcatedetail();	

	let searchtxt = searchParams.get("searchtxt");
	$('#searchtxt').val(searchtxt);	
	
	//현재 페이지 번호에 색상 변경
	let currentpage = searchParams.get("currentpage");
	//console.log(currentpage);
	let page = 0;
	if(currentpage == null) {
		currentpage = 0;
	}
	else{
		page = (currentpage-1)%5;
	}
	$('.pagenum').eq(page).css("color", "rgb(77, 77, 255)");
	//페이지 새로 고침 후 선택했던 카테고리 유지

})); //on ready
	
	$(document).on('change', '#search_catename',(setcatedetail));

 	//분류별 검색 버튼 클릭
	$(document).on('click', '#search_btn', (function () {
		let division = $('#divisioncode option:selected').val();
		let catename = $('#search_catename option:selected').val();
		let catedetailcode = $('#search_catedetailname option:selected').val();
		let searchtxt = $('#searchtxt').val();
		location.href = '/searchdetail?divisioncode=' + division + '&currentpage=1' + '&catename=' + catename + '&catedetailcode=' + catedetailcode + '&searchtxt=' + searchtxt;	
	}));