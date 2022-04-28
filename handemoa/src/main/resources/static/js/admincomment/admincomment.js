	/**
 * 
 */ 
 $(window).on('load', (function(){
	//페이지 새로 고침 후 선택했던 카테고리 유지
	const searchParams = new URLSearchParams(location.search);
	var divisioncode = searchParams.get("divisioncode");
	if(divisioncode == null) divisioncode = 0;
	$('#divisioncode').val(divisioncode).prop('selected', true);
	
	var search = searchParams.get("search");
	if(search == null) search = 0;
	$('#comment_search').val(search).prop('selected', true);
	
	var searchtxt = searchParams.get("searchtxt");
	$('#comment_searchdetail').val(searchtxt);
	
})); //on ready

 	//분류별 검색 버튼 클릭
	$(document).on('click', '#search_btn', (function () {
		//console.log($('#divisioncode option:selected').val());
		let division = $('#divisioncode option:selected').val();
		let search = $('#comment_search option:selected').val();
		let searchtxt = $('#comment_searchdetail').val();
		location.href = '/admincomment/search?divisioncode=' + division + '&currentpage=1' + '&search=' + search + '&searchtxt=' + searchtxt;	
	}));
	
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

	//삭제 창 확인 버튼
	$(document).on('click', '#modal_confirm_ok_btn', (function () {
		$("#modal_confirm").hide();

		//선택된 신고기록번호
		var commentnums = [];

		$("input[name='ck_report']:checked").each(function () {
			var num = $(this).val();
			commentnums.push(num);
		});
		//console.log(postnums);
		$.ajax({
		url: "/admincomment/delete",
		data: { 'commentnums': commentnums },
		type: 'post',
		dataType: 'json',
		success: function (result) {
			console.log(result);
			//location.reload();
			location.replace("/admincomment");
			}
		});
	})); //삭제 확인 end
