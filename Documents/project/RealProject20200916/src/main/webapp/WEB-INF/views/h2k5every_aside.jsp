<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<style>
   
 a:link {
      text-decoration: none;
      }
 a:visited { 
     color: black;
      text-decoration: none;
     }
 a:hover { 
    color: black; 
    text-decoration: underline;
    }
.asideMenu{
  height:auto;  
}    
input[id*="my"]{
    /*  */
    display:none;
}
input[id*="my"] + label {
    display:block;
    padding:20px;
    border:1px solid black;
    border-bottom:0px;
    color:#fff;
    font-weight: 900;
    background:black;
    cursor:pointer;
}
input[id*='my'] + label + div{
    max-height:0;
    transition: all .35s;
    overflow:hidden; 
    background:#ebf8ff;
    font-size:11px;
}
input[id*='my'] + label + div p{
    display:inline-block;
}
input[id*='my']:checked + label + div  {
    max-height:800px;
}
input[class*='myClassCheckbox']+label{
    background:rgb(66, 65, 65);
}
</style>

</head>

<body>
    <div class='asideMenu'>
        <input type='radio' name='asideMenu' id='myMenu'>
        <label for='myMenu'>메뉴</label>
        <div>
            <div><p><a href='#'>공지사항</a></p></div>
            <div><p><a href='#'>상담게시판</a></p></div>
            <div><p><a href='#'>내 정보 관리</a></p></div>
        </div>
      
        <input type='radio' name='asideMenu' id='myGradeManagement'>
        <label for='myGradeManagement'>성적관리</label>
        <div>
            <div><p><a href='#'>시험 보기</a></p></div>
            <div><p><a href='#'>시험 성적 분석</a></p></div>
            <div><p><a href='#'>퀴즈 성적 분석</a></p></div>
            <div><p><a href='#'>성적 통계</a></p></div>
            <div><p><a href='#'>오답노트</a></p></div>
            <div><p><a href='#'>문제 관리</a></p></div>
        </div>

       <input type='radio' name='asideMenu' id='myClass'>
       <label for='myClass'>강의관리</label>
       <div>
         <input type='checkbox' class='myClassCheckbox' name='asideMenu' id='myClassManagement'
         onclick = "checkboxChecked(1)">
         <label for='myClassManagement'>강의 관리</label>
         <div>
              <div><p><a href='#'>수강 관리</a></p></div>
             <div><p><a href='#'>강의 진도 확인</a></p></div> 
            <div><p><a href='#'>목표 강의 진도</a></p></div>
            <div><p><a href='#'>메모장 관리</a></p></div>
            <div><p><a href='#'>나의 Q&A</a></p></div>
            <div><p><a href='#'>내 강의 후기</a></p></div>

         </div>
         <input type='checkbox' class='myClassCheckbox'name='asideMenu' id='mySuggestClass'
         onclick = "checkboxChecked(2)">
         <label for='mySuggestClass'>추천 강의</label>
            <div>
             <div><p><a href='#'>성적에 맞는 강의 추천</a></p></div>
             <div><p><a href='#'>취향에 맞는 강의 추천</a></p></div>
             <div><p><a href='#'>재수강 관리</a></p></div>     
             <div><p><a href='#'>심화학습 관리</a></p></div>
             <div><p><a href='#'>level check</a></p></div>
            </div>
         <input type='checkbox' class='myClassCheckbox' name='asideMenu' id='myTestAndAssign'
         onclick = "checkboxChecked(3)">
         <label for='myTestAndAssign'>과제 및 시험</label>
          <div>
                <div><p><a href='#'>과제 관리</a></p></div>
                <div><p><a href='#'>222</a></p></div>
         </div>  
        <!-- 강의관리 마무리 div id='myClass' -->
       </div>   
       <!-- 총괄 div id='asideMenu' -->
    </div>
    
    <div id="goTeacherPage" style="background-color: black; ">
        <!-- <p style="margin-top: auto;"><b><a href="goTeacherLoginFrm" style="color: white; ">강사페이지이동</a></b></p> -->
    </div>
<script>
var classManage = document.getElementById("myClassManagement");
var suggestClass = document.getElementById("mySuggestClass");
var myTest = document.getElementById("myTestAndAssign");
function checkboxChecked(i) {

    if(i==1){
        suggestClass.checked = false;
         myTest.checked = false;
    }else if(i==2){
        classManage.checked = false;
        myTest.checked = false;
    }else if (i ==3){
        classManage.checked=false;
        suggestClass.checked = false;
    }
}
</script>
</body>
</html>





