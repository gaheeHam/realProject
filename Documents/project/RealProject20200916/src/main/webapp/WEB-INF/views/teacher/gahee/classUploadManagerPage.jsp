<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>Insert title here</title>
<script> 

$(function(){     
	//모달박스 해제
	   var $layerWindow=$('#articleView_layer');
	   $layerWindow.find("#bg_layer").on('mousedown',function(evt){
	      console.log(evt);
	      $layerWindow.removeClass('open');
	   }); //on End
	   $(document).keydown(function(evt){
	      console.log(evt);
	      if(evt.keyCode!=27) return;
	      else if($layerWindow.hasClass('open')){
	         $layerWindow.removeClass('open');
	      }
	   }); //keydown End
	   
	});// End
	
</script>

<style>
table,th,td{
border: 1px solid black;
border-collapse: collapse;
}

html, body {
   height: 100%;
   margin: 0
}

#articleView_layer {
   display: none;
   position: fixed;
   position: absolute;
   top: 0;
   left: 0;
   width: 100%;
   height: 100%
}

#articleView_layer.open {
   display: block;
   color: red;
}

#articleView_layer #bg_layer {
   position: absolute;
   top: 0;
   left: 0;
   width: 100%;
   height: 100%;
   background: #000;
   opacity: .5;
   filter: alpha(opacity = 50);
   z-index: 100
}

#contents_layer {
   position: absolute;
   top: 40%;
   left: 40%;
   width: 400px;
   height: 400px;
   margin: -150px 0 0 -194px;
   padding: 28px 28px 0 28px;
   border: 2px solid #555;
   background: #fff;
   font-size: 12px;
   z-index: 200;
   color: #767676;
   line-height: normal;
   white-space: normal;
   overflow: scroll
}
  #bg_layer{padding: 50px;} 
        #graph{
            height: 40px;
            background: #ccc; 
            border-radius: 40px;
            width: 300px;
        }
        #graph span{
        display: block;
        padding: 0 10px;
       /*  width: 70%; */
        height: 40px;
        line-height: 40px;
        text-align: right;
        background-color: blueviolet; 
        border-radius: 40px;
        box-sizing: border-box;
        color: #fff;
        animation: stack 2s 1;
        }
        
</style>
</head>

<body>
<h1>나의 강의 관리</h1>
<div id='comeClass'></div>
<br><br><hr><br><br>
<div id='comeAa'>
<input type="hidden" name = "${_csrf.parameterName}" value="${_csrf.token}" />
<input type="hidden" id='token' data-token-name='${_csrf.headerName }' value='${_csrf.token }'/>
</div>

 <div id="articleView_layer">
     				 <div id="bg_layer"></div>
      				<div id="contents_layer"></div>
 			  </div>

<script>
var managerClassList =${manager_classList};
var sumnum;

if(managerClassList.length !=0){
	console.dir(managerClassList);
	$('#comeClass').append("<table><thead><tr><th>강의명</th><th>난이도</th><th>시작날짜</th><th>종료날짜</th><th>강의계획서</th><th>강좌및과제관리</th><th>수강자관리</th></tr></thead><tbody>");
	for(var i=0; i<managerClassList.length; i++){
		sumnum=managerClassList[i].cl_lcnum;
		var stday =  managerClassList[i].cl_stday.split(" ");
		var fnday = managerClassList[i].cl_fnday.split(" ");
		$('tbody').append("<tr><td>"+managerClassList[i].cl_clname+"</td><td>"+managerClassList[i].cl_lv+"</td><td>"+stday[0]+"</td><td>"+fnday[0]+"</td><td><a onclick=\"openClassPlan('"+managerClassList[i].pc_title+"','"+managerClassList[i].pc_cont+"','"+managerClassList[i].cl_cc+"','"+managerClassList[i].cl_lcnum+"')\">상세보기</a></td><td><a onclick=\"openCoursePage('"+managerClassList[i].cl_lv+"','"+managerClassList[i].cl_idnum+"')\">상세보기</a></td><td><a onclick=\"ajaxaa('"+managerClassList[i].cl_lv+"','"+managerClassList[i].cl_idnum+"')\">상세보기</a></td></tr>");
	}
	$('#comeClass').prepend("</tbody></table>");

}else{
	$('#comeClass').append("<p>현재 진행 중인 강의가 없습니다.</p>");
}

function openClassPlan(pc_title,pc_cont,cl_cc,cl_lcnum) {
	 $('#articleView_layer').addClass('open');
	//console.log(pc_title+pc_cont+cl_cc+cl_lcnum);
	//var cont = pc_cont.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');
	$('#contents_layer').append("<center><h3>강의계획서</h3><table><tr><th>계획서 개요</th><th>"+pc_title+"</th></tr><tr><th>계획서 내용</th><th>"+pc_cont+"</th></tr><tr><th>관심사</th><th>"+cl_cc+"</th></tr><tr><th>총강좌회차</th><th>"+cl_lcnum+"</th></tr></table></center>");
	
	
}
function openCoursePage(cl_lv,cl_idnum) {
	//레벨값,일련번호
	console.log(cl_lv+cl_idnum);
	$('body').append("<form action='selectmanagercoursehomeworkpage' method='post'><input type='hidden' name = '${_csrf.parameterName}' value='${_csrf.token}' />");
	$('form').append("<input type='hidden' name='co_idnum' value='"+cl_idnum+"'></form>");
	$('form').submit();

}


function ajaxaa(cl_lv,cl_idnum) {
	$.ajax({
		url:'rest/aaList?cl_lv='+cl_lv+'&cl_idnum='+cl_idnum,
		type:'GET',
		//async: false,
		dataType: 'json',
		beforeSend : function(xhr)
		{
			//이거 안하면 403 error
			//데이터를 전송하기 전에 헤더에 csrf값을 설정한다
			var $token = $("#token");
			xhr.setRequestHeader($token.data("token-name"), $token.val());
		},
		success: function(aaList) {
		console.log(aaList);
		//$('#comeAa').empty();
		$('#comeAa').append("<table><thead><tr><th>학생ID</th><th>진도율</th></tr></thead><tbody id='aa'>");
		for(var i=0; i<aaList.length; i++){
			$('#aa').append("<tr><td>"+aaList[i].aa_id+"</td><td><a onclick=\"openProgress('"+aaList[i].aa_id+"','"+cl_idnum+"','"+cl_lv+"')\">상세보기</a></td></tr>");
		}
		$('#comeAa').prepend("</tbody></table>");
		},
		error: function(err) {
			console.log(err)
			
		}
	});
	
}


function openProgress(id, idnum, lv) {
	$.ajax({
		url:'rest/countAt?at_id='+id+'&at_idnum='+idnum+'&at_lv='+lv,
		type:'GET',
		//async: false,
		dataType: 'json',
		beforeSend : function(xhr)
		{
			//이거 안하면 403 error
			//데이터를 전송하기 전에 헤더에 csrf값을 설정한다
			var $token = $("#token");
			xhr.setRequestHeader($token.data("token-name"), $token.val());
		},
		success: function(countAt) {
			var sum = parseInt(sumnum);
			var count =parseInt(countAt);
		console.log(countAt);
		 $('#articleView_layer').addClass('open');
		 var persent =(count/sum)*100;
		 console.log("count:  "+count);
		 console.log("sum:  "+sum);
		 console.log("persent:  "+persent);
		 $('#contents_layer').empty();
		 $('#contents_layer').append("<table><tr><td>"+id+"</td><td></div><div id='graph'><span id='sp'>"+persent+"</span></div></tr></table>");
		 $('#sp').css("width",persent+"%");
		 $('style').append(" @keyframes stack{0%{ width: 0; color: rgba(255, 255, 255, 0);} 50%{ color:  rgba(255, 255, 255, 1); } 100%{  width: "+persent+"%;}}");
		},
		error: function(err) {
			console.log(err)
			
		}
	});
}



</script>

</body>
</html>