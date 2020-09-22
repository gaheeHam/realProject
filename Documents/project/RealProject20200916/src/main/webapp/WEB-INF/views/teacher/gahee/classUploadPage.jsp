<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
    #errP{
    color: red;
    }
    </style>
    </head>
    <body>
 <h1>강의 업로드 폼</h1>
        <form action="insertclassupload">
        <table>
            <tr>
                <th>강의명을 입력하시오</th>
                <td><input type="text" placeholder="강의명" name='cl_clname'></td>
            </tr>
            <tr>
                <th>시작날짜를 입력하시오(ex.2020/08/22)</th>
                <td><input type="date" name='cl_stday'></td>
            </tr>
            <tr>
                <th>종료날짜를 입력하시오(ex.2020/08/22)</th>
                <td><input type="date" name='cl_fnday'></td>
            </tr>
            <tr>
                <th>강의 가격을 입력하시오(ex.100point=>100)</th>
                <td><input type="number" placeholder="강의가격" name='cl_pt'>POINT</td>
            </tr>
            <tr>
                <th>강좌 회차를 입력하시오</th>
                <td><input type="number" placeholder="강의회차" name='cl_lcnum'>회차</td>
            </tr>
            <tr>
                <th>레벨을 설정하시오(1~5)</th>
                <td><input type="number" placeholder="레벨" name='cl_lv'></td>
            </tr>
            <tr>
                <th>관심사를 선택하시오</th>
                <td><input type="radio" value="1" name='cl_cc'>컴퓨터<input type="radio" value="2" name='cl_cc'>댄스</td>
            </tr>
        </table>
        <p id='errP'></p>
        <button type="button" id='insertClassBnt'>등록하기</button> ||  <button type="reset">취소</button>
        <input type="hidden" name = "${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" id='token' data-token-name='${_csrf.headerName }' value='${_csrf.token }'/>
    </form>
    <script>
    var num;
    $('#insertClassBnt').click(function() {
    	num = 0;
    	
    	var $input = $('input');
    	console.log($input[0].value);

    	for(var i=0; i<$input.length-2; i++){
    		console.log("num: "+num)
    		if($input[i].value==""||$input[i].value==" "||$input[i].value==null){
    			num=1;
    			 
    		}else if($input[6].checked==false && $input[7].checked==false){
    			num=2;
    		}
    	}
    	
    	var lv = parseInt($input[5].value);
    	console.log("$input[5].value: " + $input[5].value)
    	if(lv<1 || lv>5 ){
    		num=3;
   		
    	}
    	
    	daycal();
    	
    	console.log("num         :"+num);
    	
    	switch (num) {
		case 0:
			console.log("num: "+num)
    		$('#insertClassBnt').attr("type","submit");
    		$('#insertClassBnt').submit();	
			break;
		case 1:
			$('#errP').text('');
            $('#errP').text('빈칸이 있으면 안됩니다.');	
			break;
		case 2:
			 $('#errP').text('');
	            $('#errP').text('관심사를 선택해 주세요');
			break;
		case 3:
			 $('#errP').text('');
	            $('#errP').text('1~5까지의 레벨만 설정가능합니다.');
			break;
		case 4:
			 $('#errP').text('');
	            $('#errP').text('현재날짜보다 시작일이 빠를 수 없습니다. ');  
			break;
		case 5:
			 $('#errP').text('');
	            $('#errP').text('시작일과 종료일이 같을 수 없습니다.');
			break;
		case 6:
			$('#errP').text('');
       	 $('#errP').text("종료일이 시작일보다 빠를 수 없습니다.");
       
			break;

		default:
			break;
		}
    	
        
    })


    
    
    
    function daycal(){
    	
        var stdayArr =document.getElementsByName('cl_stday')[0].value.split('-');
        var stday="";
        for(var i=0; i<stdayArr.length; i++){
            stday += stdayArr[i];
        }
        var intSt =parseInt(stday);

        var fndayArr =document.getElementsByName('cl_fnday')[0].value.split('-');
        var fnday="";
        for(var i=0; i<fndayArr.length; i++){
            fnday += fndayArr[i];
        }
        var intFn =parseInt(fnday);

        var now = new Date(); 
        var month = now.getMonth()+1; 
        var day = now.getDate();
        var year = now.getFullYear();
        if(month<10){
            month ="0"+month;
        }
        if(day<10){
            day = "0"+day;
        }
       
       var nowdate =""; 
       nowdate = parseInt(""+year+month+day);
       
       if(nowdate > intSt){
    	   num=4;
            console.log("nowdate > intSt");
        }else if(intSt==intFn){
        	num=5;
            console.log("intSt=intFn");
           
        }else if(intSt>intFn){
        	num=6;
        	
    }
    }


        
    </script>
</body>
</html>