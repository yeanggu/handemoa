var idcheck = 2;
var nicknamecheck = 2;
var pwcheck = 2;
var emailcheck = 2;
var phonecheck = 2;

$(document).ready(function(){

//아이디 중복 체크
$('#repeat_check').click(function(){
	let id = $('#id_id').val();
	if(id==""){
		alert("아이디를 입력해주세요");
	}
	else{
	console.log(id);
	$.ajax({
		url : "/membercheck",
		type : "post",
		data :{'id': id},
		dataType : 'json',
		success : function(result){
			console.log(result);
			if(result == 0){
				$("#idcheck_font").html('사용할 수 있는 아이디 입니다.');
				$("#idcheck_font").css('color', 'green');
				$("#idcheck_font").css('font-size', '11px');
				idcheck = 1;
			} else{
				$("#idcheck_font").html('사용할 수 없는 아이디 입니다.');
				$("#idcheck_font").css('color', 'red');
				$("#idcheck_font").css('font-size', '11px');
				idcheck = 2;
			}
		},
		error : function(){
			alert("서버요청실패");
		}
	});//ajax
	}
})
//아이디 중복 체크 END



//<!-- 닉네임 중복 체크 -->
$('#repeat_check_nickname').click(function(){
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



//<!-- 이메일 중복 체크 -->
$('#repeat_check_email').click(function(){
	let id = $('#email_email').val();
	if(id==""){
		alert("이메일을 입력해주세요");
	}
	else{
	console.log(id);
	$.ajax({
		url : "/membercheck_email",
		type : "post",
		data :{'email': id},
		dataType : 'json',
		success : function(result){
			console.log(result);
			if(result == 0){
				$("#emailcheck_font").html('사용할 수 있는 이메일 입니다.');
				$("#emailcheck_font").css('color', 'green');
				$("#emailcheck_font").css('font-size', '11px');
				emailcheck = 1;
				
			} else{
				$("#emailcheck_font").html('사용할 수 없는 이메일 입니다.');
				$("#emailcheck_font").css('color', 'red');
				$("#emailcheck_font").css('font-size', '11px');
				emailcheck = 2;
			}
		},
		error : function(){
			alert("서버요청실패");
		}
	});//ajax
	}
})
//<!-- 이메일 중복 체크 END -->



//<!-- 휴대폰 번호 중복 체크 -->
$('#repeat_check_phone').click(function(){
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



//회원가입 버튼 function
$("#member_btn").on('click', function(ev){
if(idcheck==2){
	alert("아이디를 중복체크를 확인해주세요.");
	ev.preventDefault();
}

if(nicknamecheck==2){
	alert("닉네임 중복체크를 확인해주세요.");
	ev.preventDefault();
}

if(pwcheck==2){
	alert("비밀번호를 확인해주세요.");
	ev.preventDefault();
}

if(emailcheck==2){
	alert("이메일 중복체크를 확인해주세요.");
	ev.preventDefault();
}

if(phonecheck==2){
	alert("휴대폰 번호 중복체크를 확인해주세요.");
	ev.preventDefault();
}
//회원가입 버튼 function END



// 최종 버튼 체크
if(idcheck == 1 && nicknamecheck == 1 && pwcheck == 1 && emailcheck == 1 && phonecheck == 1){
	//<!-- 생년월일 ymd ++ -->
	var birthString = $('input[name=birth_yy]').val() + "-" + $("select[name=birth_mm]").val() + "-" + $('input[name=birth_dd]').val();
	console.log(birthString);
	$('input[type=hidden]').val(birthString);
	//<!-- 생년월일 ymd ++ END -->
}// 최종 버튼 체크

}); //회원가입 버튼 function END
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
