<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
<%@include file="css/pages.css"%>
</style>
<script>
  $(function() { // when DOM is ready
	    $(".showhide").click(function(){ 
	    	var toLoad=$(this).attr('id');// when #showhidecomment is clicked
	    	
	        $("#content").load(toLoad); // load the sample.jsp page in the #chkcomments element
	    }); 
	});
  </script>
</head>
<body>
<div id="content">
		<%
HttpSession session1=request.getSession(false);
if(session1==null||session1.getAttribute("username")==null)
{	%>
<a href="login">Click here to login</a>
<%} else{ %>
	<h2 id="id1" align="center">EMPLOYEE DATA</h2>

	<div id="id2">
		<h3 align="center">${viewdetails}</h3>
		</div>
	<h4><j:out value="${updatestatus}"></j:out></h4>
	<table border="2">
		<tr>
			<th>EMPCODE
			<th>FirstNAME
			<th>LastNAME
			<th>gender
			<th>mobileNumber
			<th>Address
			
		</tr>
		
		<j:forEach var="emp" items="${empl}">
			<tr>
				<td><j:out value="${emp.employeeId} "></j:out></td>
				<td><j:out value="${emp.firstName} "></j:out></td>
				<td><j:out value="${emp.lastName} "></j:out></td>
				<td><j:out value="${emp.gender} "></j:out></td>
				<td><j:out value="${emp.mobileNumber} "></j:out></td>
				<td><j:out value="${emp.address} "></j:out></td>
				<j:if test="${viewdetails eq 'View All Employees' }">
				<td><a class="showhide" id="UpdateEmployee?code=<j:out value="${emp.employeeId}"></j:out>">Update</a></td>
				<td><a class="showhide" id="getRole?code=<j:out value="${emp.employeeId}"></j:out>">Show Employee Roles</a></td>
				</j:if>
				</tr>
				</j:forEach>
	</table>

	<%} %>
	</div>
</body>
</html>