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
	
	<h2 id="id1" align="center">EMPLOYEE DATA</h2>

	<div id="id2">
		<h3 align="center">Departments</h3>
		</div>
	<h4><j:out value="${deptstatus}"></j:out></h4>
	<table border="2">
		<tr>
			<th>Deptno
			<th>Deptname
			<th>Location
			</tr>
		
		<j:forEach var="dep" items="${dept}">
			<tr>
				<td><j:out value="${dep.deptno} "></j:out></td>
				<td><j:out value="${dep.deptname} "></j:out></td>
				<td><j:out value="${dep.location} "></j:out></td>
				<td><a href="UpdateDepartment?code=<j:out value="${dep.deptno}"></j:out>">Update</a></td>
				
				</tr>
				</j:forEach>
	</table>

	<a href="home">Return to home</a>
</body>
</html>