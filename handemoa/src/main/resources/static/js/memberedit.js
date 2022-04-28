var nicknamecheck = 2;
var pwcheck = 2;
var phonecheck = 2;

$(document).ready(function(){

//<!-- 닉네임 중복 체크 -->
$('#nickname_change_btn').click(function(){
	let id = $('#nickname_nickname').val();
	if(id==""){
		alert("닉네임을 입력해주세요");
	}
	else{
	console.log(id);
	$.ajax({
		url : "/membercheck_nickname",
		type : "post",
		data :{'nickname': id},
		dataType : 'json',
		success : function(result){
			console.log(result);
			if(result == 0){
				$("#nicknamecheck_font").html('사용할 수 있는 닉네임 입니다.');
				$("#nicknamecheck_font").css('color', 'green');
				$("#nicknamecheck_font").css('font-size', '11px');
				nicknamecheck = 1;
			} else{
				$("#nicknamecheck_font").html('사용할 수 없는 닉네임 입니다.');
				$("#nicknamecheck_font").css('color', 'red');
				$("#nicknamecheck_font").css('font-size', '11px');
				nicknamecheck = 2;
			}
		},
		error : function(){
			alert("서버요청실패");
		}
	});//ajax
	}
})
//<!-- 닉네임 중복 체크 END -->



//<!-- 휴대폰 번호 중복 체크 -->
$('#phone_change_btn').click(function(){
	let id = $('#phone_phone').val();
	if(id==""){
		alert("휴대폰 번호를 입력해주세요");
	}
	else{
	console.log(id);
	$.ajax({
		url : "/membercheck_phone",
		type : "post",
		data :{'phone': id},
		dataType : 'json',
		success : function(result){
			console.log(result);
			if(result == 0){
				$("#phonecheck_font").html('사용할 수 있는 휴대폰 번호 입니다.');
				$("#phonecheck_font").css('color', 'green');
				$("#phonecheck_font").css('font-size', '11px');
				phonecheck = 1;
				
			} else{
				$("#phonecheck_font").html('사용할 수 없는 휴대폰 번호 입니다.');
				$("#phonecheck_font").css('color', 'red');
				$("#phonecheck_font").css('font-size', '11px');
				phonecheck = 2;
			}
		},
		error : function(){
			alert("서버요청실패");
		}
	});//ajax
	}
})
//<!-- 휴대폰 번호 중복 체크 END -->



//회원정보수정 버튼 function
$("#editcomple").on('click', function(ev){

if(nicknamecheck==2){
	alert("닉네임 중복체크를 확인해주세요.");
	ev.preventDefault();
}

if(pwcheck==2){
	alert("비밀번호를 확인해주세요.");
	ev.preventDefault();
}

if(phonecheck==2){
	alert("휴대폰 번호 중복체크를 확인해주세요.");
	ev.preventDefault();
}

if (nicknamecheck==1 && pwcheck==1 && phonecheck==1) {
	alert("회원정보가 수정되었습니다.");
}
}); //회원가입 버튼 function END


/*썸네일*/
    let profileimgadd; /*한개*/

    $('#profileimg_btn').click(function () {
        alert("프로필 사진이 등록되었습니다");
        var formData = new FormData($('#profileimg_form')[0]);
        $.ajax({
            url: "/profileimg",
            type: "POST",
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (profileimgName) {
                profileimgadd = profileimgName; /*두개*/
                $('#profileimg').val(profileimgName);
            }
        });
    });

    var date = new Date();

    $("#profileimg_preview_btn").click(function () { 
        $('.profileimg_preview').remove();
        var th = $('<img class="profileimg_preview" src="css/images/'+profileimgadd+'?'+date.getTime()+'"/>'); /*세개*/
        $('#profileimg_preview_area').append(th);
    });
/*썸네일 END*/




}) //ready function END

//<!-- 비밀번호 중복체크 -->
$(function(){
	$("#pw_success").hide();
	$("#pw_fail").hide();
	$("input").keyup(function(){
		var pw_text=$("#pw_text").val();
		var pw_check_text=$("#pw_check_text").val();
		if(pw_text != "" || pw_check_text != ""){
			if(pw_text == pw_check_text){
				$("#pw_success_id").show();
				$("#pw_fail_id").hide();
				pwcheck=1;
				
			}else{
				$("#pw_success_id").hide();
				$("#pw_fail_id").show();
				pwcheck=2;
				
			}
		}
	});
})
//<!-- 비밀번호 중복체크 END -->

//<!-- 휴대폰 번호 - 자동생성 -->
function inputPhoneNumber(obj) {

    var number = obj.value.replace(/[^0-9]/g, "");
    var phone = "";

    if(number.length < 4) {
        return number;
    } else if(number.length < 7) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3);
    } else if(number.length < 11) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 3);
        phone += "-";
        phone += number.substr(6);
    } else {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 4);
        phone += "-";
        phone += number.substr(7);
    }
    obj.value = phone;
}
// 휴대폰 번호 - 자동생성 END


function memberquit() {
		var memberquit_id = $("input[name='id']").val(); /*id가 들어간상태*/
	if (confirm("정말로 회원탈퇴 하시겠습니까?")) {
		$.ajax({
			url: "/memberquit",
			type: "POST",
			data: { "memberquit_id": memberquit_id },
			success: function(data) {
				if(data==1){
					alert("회원탈퇴가 완료되었습니다")
					location.href = "/logout";
				}
				}
			})
		};
}



