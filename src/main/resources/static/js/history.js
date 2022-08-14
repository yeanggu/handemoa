	//페이지 이동
	function movePage(currentPage){
	  
	  var url = "/history";
	  url = url + "?currentPage="+currentPage;
	  
	  location.href=url;
	}

	//체크박스 전체 선택
	function selectAll(selectAll)  {
	      if( $("#postmanage_select_all").is(':checked') ){
	        $("input[name=postmanage_select]").prop("checked", true);
	      }else{
	        $("input[name=postmanage_select]").prop("checked", false);
	      }
	}
	
	//회원 선택 삭제
	function restoreValue(){
		var url = "restorepost";
     	var valueArr = new Array();
     	var list = $("input[name='postmanage_select']");
     	for(var i=0; i<list.length;i++){
     		if(list[i].checked){
     			valueArr.push(list[i].value);
     		}
     	}
     	if(valueArr.length == 0){
     		alert("복구할 게시물을 선택하세요");
     	}else{
     		var chk = confirm("정말 복구하시겠습니까?");
	     		if(chk){
		//alert(JSON.stringify({ "valueArr" : valueArr }));
		     		$.ajax({
		    		    url : url,
		    		    type : "POST",
		    		  // traditional:true,
		    		   //data : JSON.stringify({ "valueArr" : valueArr }),
		    		   //contentType:"application/json; charset=UTF-8",
		    		   data:{"valueArr" : valueArr},
		    		    success : function(data){ 
		    		    	if(data >= 1) {
		    		    		alert("복구가 완료되었습니다.");
		    		    		location.replace("history");
		    		    	} else {
		    		    		alert("복구가 실패되었습니다.");
		    		    		location.replace("history");
		    		    	}
		    		    }
		     		});    
	     		}
     	}
	}
	
	//회원 선택 탈퇴
	function deleteValue(){
		var url = "deletepost";
     	var valueArr = new Array();
     	var list = $("input[name='postmanage_select']");
     	for(var i=0; i<list.length;i++){
     		if(list[i].checked){
     			valueArr.push(list[i].value);
     		}
     	}
     	if(valueArr.length == 0){
     		alert("삭제할 게시물을 선택하세요");
     	}else{
     		var chk = confirm("정말 삭제하시겠습니까?");
	     		if(chk){
		     		$.ajax({
		    		    url : url,
		    		    type : "POST",
		    		   data:{"valueArr" : valueArr},
		    		    success : function(data){ 
		    		    	if(data >= 1) {
		    		    		alert("삭제가 완료되었습니다.");
		    		    		location.replace("history");
		    		    	} else {
		    		    		alert("삭제가 실패되었습니다.");
		    		    		location.replace("history");
		    		    	}
		    		    }
		     		});    
	     		}
     	}
	}
	