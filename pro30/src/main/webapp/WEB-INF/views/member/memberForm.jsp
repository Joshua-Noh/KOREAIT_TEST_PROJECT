<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
   request.setCharacterEncoding("UTF-8");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입창</title>
<style>
   .text_center{
     text-align:center;
   }
</style>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
 function fn_process(){
    var _id=$("#t_id").val();
    if(_id==''){
   	 alert("ID를 입력하세요");
   	 return;
    }
    $.ajax({
       type:"post",
       async:true,  
       url:"${contextPath}/member/idCheck.do",
       dataType:"text",
       data: {id:_id},
       success:function (data,textStatus){
    	   //alert(data);
    	   
          if(data=='usable'){
       	   $('#message').text("사용할 수 있는 ID입니다.");
       	   $('#btnDuplicate').prop("disabled", true);
          }else{
       	   $('#message').text("사용할 수 없는 ID입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }		
</script>
</head>
<body>
	<form method="post"   action="${contextPath}/member/addMember.do">
	<h1  class="text_center">회원 가입창</h1>
	<table  align="center">
	   <tr>
	      <td width="200"><p align="right">아이디</td>
	      <td width="400"><input type="text" name="id" id="t_id">
	      <input type="button" id="btnDuplicate" value="ID 중복체크하기" onClick="fn_process()" />
	      <div id="message"></div></td>
	   </tr>
	   <tr>
	      <td width="200"><p align="right">비밀번호</td>
	      <td width="400"><input type="password" name="pwd"></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">이름</td>
	       <td width="400"><p><input type="text" name="name"></td>
	    </tr>
	    <tr>
	       <td width="200"><p align="right">이메일</td>
	       <td width="400"><p><input type="text" name="email"></td>
	    </tr>
	    <tr>
	       <td width="200"><p>&nbsp;</p></td>
	       <td width="400"><input type="submit" value="가입하기"><input type="reset" value="다시입력"></td>
	    </tr>
	</table>
	</form>
</body>
