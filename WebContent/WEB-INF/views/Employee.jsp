<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.red{
color:red;

}
.body .form{
float:left;
clear:both;
}
th, td {
    text-align: left;
    padding: 8px;
}    


</style>
</head>
<body>
<s:form commandName="employee" action="empl" method="post">
<h2 id="id1" align="center">EMPLOYEE DATA</h2>

	<div id="id2">
		<h3 align="center">Add Employee Form</h3>
		</div>

<table align="center">
<tr><td>EmployeeId</td><td><s:input path="employeeId" cssClass="form" required="true"/></td><td>
<tr><td>Firstname</td><td><s:input path="firstName" cssClass="form" required="true"/></td><td>
<tr><td>LastName</td><td><s:input path="lastName" cssClass="form" required="true"/></td><td>
<tr><td>Enter Password</td><td><s:input path="password" cssClass="form" required="true"/></td><td>
<tr><td>Enter Gender(Male/Female)</td><td><s:input path="gender" cssClass="form" required="true"/></td><td>
<tr><td>Enter MobileNumber</td><td><s:input path="mobileNumber" cssClass="form" required="true"/></td><td>
<tr><td>Enter DateOfBirth</td><td><s:input path="dateOfBirth" cssClass="form" /></td><td>
<tr><td>Enter DateOfJoining</td><td><s:input path="dateOfJoin" cssClass="form" /></td><td>
<tr><td>Enter Address</td><td><s:input path="address" cssClass="form" required="true"/></td><td>
<%
session.setAttribute("userid", (String)pageContext.getAttribute("userid"));
%>
</table>
	<div align="center">
<!-- 	Select Role 
		<INPUT TYPE="radio" NAME="radios" VALUE="user" CHECKED>User
		<INPUT TYPE="radio" NAME="radios" VALUE="admin">Admin
		<br>
 -->
  	<label  class="role">
   	<input type="radio" name="radio" id="admin"  value="admin" checked="checked"/>Admin
 	</label>
 	<label  class="role">
   	<input  type="radio" name="radio"  id="user" value="user"/>User
 	</label>
 
 	<input type="submit" value="Submit">
	</div>


<a href="home">Return to home</a>
</s:form>


</body>
</html>