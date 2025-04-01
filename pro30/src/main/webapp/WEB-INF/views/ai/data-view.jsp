<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />  
<%
  request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flask API 데이터 출력</title>
</head>
<body>
	<h2>Flask API에서 가져온 데이터 (리스트 형식)</h2>
    
    <c:if test="${not empty dataList}">
        <table align="center" border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Score</th>
            </tr>
            <c:forEach var="item" items="${dataList}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.score}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty dataList}">
        <p>데이터가 없습니다.</p>
    </c:if>
</body>
</html>