<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
table{
width:100%;
}
th, td {
    border-bottom: 1px solid #ddd;
    padding: 8px;
    text-align: center;
    vertical-align: center;
}
tr:nth-child(even) {background-color: #f2f2f2}
th {
    background-color: black;
    color: white;
}
</style>
</head>
<body>
<j:choose>
<j:when test="${not empty sessionScope.username}">
	

	<div id="id2">
		
		</div>
	<h4><j:out value="${updatestatus}"></j:out></h4>
	<table border="2">
		<tr>
			<th>EMPCODE
			<th>FirstNAME
			<th>LastNAME
			
		</tr>	
		
			<tr>
				<td><j:out value="${empl.employeeId} "></j:out></td>
				<td><j:out value="${empl.firstName} "></j:out></td>
				<td><j:out value="${empl.lastName} "></j:out></td>
				
				
				</tr>
				
	</table>

	<a href="home">Return to home</a>
	</j:when>
	<j:otherwise>
<a href="#">Click here to login</a>
</j:otherwise>
</j:choose>
</body>
</html>