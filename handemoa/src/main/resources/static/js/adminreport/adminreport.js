	/**
 * 
 */ 
$(window).on('load', (function(){
	//페이지 새로 고침 후 선택했던 카테고리 유지
	const searchParams = new URLSearchParams(location.search);
	let divisioncode = searchParams.get("divisioncode");
	if(divisioncode == null) divisioncode = 0;
	$('#divisioncode').val(divisioncode).prop('selected', true);
	
	//현재 페이지 번호에 색상 변경
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

	//checkbox 클릭 이벤트
	$(document).on('click', '#allCheck', (function () {
		if ($('#allCheck').is(':checked')) {
			$('input:checkbox').prop('checked', true);
		}
		else {
			$('input:checkbox').prop('checked', false);
		}

	})); //checkbox click end
	
	//삭제 버튼 클릭 이벤트
	$(document).on('click', '#delete_btn', (function () {
		if (!$('input:checkbox').is(':checked')) {
			$("#modal_check").show();	
			return;
		}
		$("#modal_confirm").show();
	})); 
	//선택 확인 창 닫기 버튼
	$(document).on('click', '#modal_check_close_btn', (function () {
		$("#modal_check").hide();
	}));
	// 삭제 창 닫기 버튼
	$(document).on('click', '#modal_confirm_close_btn', (function () {
		$("#modal_confirm").hide();
	}));
	
	//상세 신고 내역 창 닫기 버튼
	$(document).on('click', '#modal_close_btn', (function () {
		$("#modal_report").hide();
	}));
