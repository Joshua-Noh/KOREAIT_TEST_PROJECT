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
 <style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
  </style>
  <meta charset="UTF-8">
  <title>글목록창</title>
  <!-- CSS -->
 <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
 <link href="https://fonts.googleapis.com/css?family=Philosopher" rel="stylesheet">
 <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<h1>갤러리 게시판</h1>
<c:choose>
  <c:when test="${imageList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${imageList !=null }" >
    <div class="wrapper grid">
    <c:forEach  var="img" items="${imageList }" varStatus="articleNum" >
     
            <div class="item">
                <img width="150" height="150" src="${contextPath}/download.do?articleNO=${img.articleNO}&imageFileName=${img.imageFileName}" id="preview"  /><br>
                <p><c:out value="${img.title }"/></p>
            </div>
     
    </c:forEach>
    </div>
     </c:when>
    </c:choose>
<!-- <a  class="cls1"  href="#"><p class="cls2">글쓰기</p></a> -->
<a  class="cls1"  href="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/articleForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><p class="cls2">글쓰기</p></a>
</body>
</html>