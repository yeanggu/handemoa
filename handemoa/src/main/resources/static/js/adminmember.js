	//페이지 이동
	function movePage(currentPage){
	  
	  var url = "/adminmember";
	  url = url + "?currentPage="+currentPage;
	  
	  location.href=url;
	}

	//체크박스 전체 선택
	function selectAll(selectAll)  {
	      if( $("#adminmember_select_all").is(':checked') ){
	        $("input[name=adminmember_select]").prop("checked", true);
	      }else{
	        $("input[name=adminmember_select]").prop("checked", false);
	      }
	}
	
	//회원 선택 삭제
	function deleteValue(){
		var url = "memberdeleteselect";
     	var valueArr = new Array();
     	var list = $("input[name='adminmember_select']");
     	for(var i=0; i<list.length;i++){
     		if(list[i].checked){
     			valueArr.push(list[i].value);
     		}
     	}
     	if(valueArr.length == 0){
     		alert("삭제할 회원을 선택하세요");
     	}else{
     		var chk = confirm("정말 삭제하시겠습니까?");
	     		if(chk){
		     		$.ajax({
		    		    url : url,
		    		    type : "POST",
		    		    traditional:true,
		    		    data : { valueArr : valueArr },
		    		    success : function(jdata){
		    		    	if(jdata = 1) {
		    		    		alert("삭제가 완료되었습니다.");
		    		    		location.replace("adminmember");
		    		    	} else {
		    		    		alert("삭제가 실패되었습니다.");
		    		    		location.replace("adminmember");
		    		    	}
		    		    }
		     		});    
	     		}
     	}
	}
	
	//회원 선택 탈퇴
	function quitValue(){
		var url = "quitOX";
     	var valueArr = new Array();
     	var list = $("input[name='adminmember_select']");
     	for(var i=0; i<list.length;i++){
     		if(list[i].checked){
     			valueArr.push(list[i].value);
     		}
     	}
     	if(valueArr.length == 0){
     		alert("탈퇴할 회원을 선택하세요.");
     	}else{
     		$.ajax({
    		    url : url,
    		    type : "POST",
    		    traditional:true,
    		    data : JSON.stringify({ "valueArr" : valueArr }),
    		    contentType:"application/json; charset=UTF-8",
    		    success : function(data){
    		    	var sum=0;
    		    	for (var i=0;i<data.length;i++){
    		    		sum += data[i];
    		    	}	
    		    	if(sum>=1){
    		    		alert("이미 탈퇴한 회원이 존재합니다.");
        		    	location.replace("adminmember");
        		    	return;
    		    	}else{
    		    		var chk = confirm("선택한 회원을 정말 탈퇴시키겠습니까?");
    		    		if(chk){
        			     	$.ajax({
        			    		url : "/memberquitselect",
        			    		type : "POST",
        			    		traditional:true,
        			    		data : { valueArr : valueArr },
        			    		success : function(jdata){
        			    		    if(jdata = 1) {
        			    		    	alert("탈퇴가 완료되었습니다.");
        			    		    	location.replace("adminmember");
        			    		    } else {
        			    		    	alert("탈퇴가 실패되었습니다.");
        			    		    	location.replace("adminmember");
        			    		    }
        			    		}
        			     	});//ajax2 end  
        		     	}
    		    	}
    		    	
    		    }
     		});//ajax1 end    
     	}
	}
	