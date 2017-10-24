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
		<%
HttpSession session1=request.getSession(false);
if(session1==null||session1.getAttribute("username")==null)
{	%>
<a href="login">Click here to login</a>
<%} else{ %>
	<h2 id="id1" align="center">EMPLOYEE DATA</h2>

	<div id="id2">
		<h3 align="center">View Employees</h3>
		</div>
	<h4><j:out value="${updatestatus}"></j:out></h4>
	<table border="2">
		<tr>
			<th>EMPCODE
			<th>FirstNAME
			<th>LastNAME
			
		</tr>
		
		<j:forEach var="emp" items="${empl}">
			<tr>
				<td><j:out value="${emp.employeeId} "></j:out></td>
				<td><j:out value="${emp.firstName} "></j:out></td>
				<td><j:out value="${emp.lastName} "></j:out></td>
				
				<td><a href="UpdateEmployee?code=<j:out value="${emp.employeeId}"></j:out>">Update</a></td>
				<td><a href="removeEmployeeRole?code=<j:out value="${emp.employeeId}"></j:out>">Remove Role</a></td>
				<td><a href="getEmployeeRole?code=<j:out value="${emp.employeeId}"></j:out>">Emp Role</a></td>
				<td><a href="view?code=<j:out value="${emp.employeeId}"></j:out>">Emp Role</a></td>
				</tr>
				</j:forEach>
	</table>

	<a href="home">Return to home</a>
	<%} %>
</body>
</html>