	/**
 * 
 */ 
 
 	//분류별 검색 버튼 클릭
	$(document).on('click', '#search_btn', (function () {
		//console.log($('#divisioncode option:selected').val());
		let division = $('#divisioncode option:selected').val();
		let search = $('#search_select option:selected').val();
		let searchtxt = $('#searchdetail').val();
		
		location.href = '/admincommentreport/search?divisioncode=' + division + '&currentpage=1' + '&search=' + search + '&searchtxt=' + searchtxt;;	
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
		$.ajax({
		url: "/admincommentreport/delete",
		data: { 'commentnums': commentnums },
		type: 'post',
		dataType: 'json',
		success: function (result) {
			console.log(result);
			//location.reload();
			location.replace("/admincommentreport/division?divisioncode=0&currentpage=1");
		}
		});
	})); //삭제 확인 end	
	
	// 클릭시 상세 신고 내역 확인
	$(document).on('click', '.toggle', (function () {

		$("#modal_report").show();

		var commentnum = $(this).children('input').attr('value');
		$('.reportdetail_body').remove();

		$.ajax({
			url: "/admincommentreport/detail",
			data: { 'commentnum': commentnum },
			type: 'post',
			dataType: 'json',
			success: function (data) {

				for (var i = 0; i < data.length; i++) {
					$('.modal_content_body').append("<div class='reportdetail_body'>");
					$('.modal_content_body').children('.reportdetail_body').eq(i).append("<div class='report'>" + (i + 1) + "</div>");
					$('.modal_content_body').children('.reportdetail_body').eq(i).append("<div class='report'>" + data[i].reportid + "</div>");
					$('.modal_content_body').children('.reportdetail_body').eq(i).append("<div class='report'>" + data[i].reason + "</div>");
					$('.modal_content_body').append("</div>");
				}
			}
		});
	})); //.toggle click end 
