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
</head>
<body>
    <h2>Flask API에 요청(리스트 타입)</h2>
    <table>
        <tr><td>
            (직접 입력)<br>
        	<form action="${contextPath}/aiConn/testList.do" method="post">
	        	<input type="text" id="cityInput" value="서울,대구" size="40" />
		    	<button type="submit">전송</button>
		    </form>
	    </td></tr>
	    <tr><td>&nbsp;</td></tr>
    	<tr><td>
    		(선택 입력)<br>
    		<form action="${contextPath}/aiConn/testList.do" method="post">
		        <label><input type="checkbox" name="cities" value="서울" /> 서울</label><br>
		        <label><input type="checkbox" name="cities" value="대구" /> 대구</label><br>
		        <label><input type="checkbox" name="cities" value="부산" /> 부산</label><br>
		        <label><input type="checkbox" name="cities" value="광주" /> 광주</label><br>
		        <button type="submit">전송</button>
		    </form>
		    
    	</td></tr>
    	<c:if test="${not empty responseList}">
    	<tr>
    	 <td>
		        <table align="center" border="1">
		            <tr>
		                <th>ID</th>
		                <th>Name</th>
		                <th>Population</th>
		            </tr>
		            <c:forEach var="item" items="${responseList}">
		                <tr>
		                    <td>${item.id}</td>
		                    <td>${item.name}</td>
		                    <td>${item.population}</td>
		                </tr>
		            </c:forEach>
		        </table>
    	 <td>
    	</tr>
    	</c:if>
    </table>
    
    
	<h2>Flask API에서 가져온 데이터 (JSON 형식)</h2>
    
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