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

</style>
</head>
<body>
<s:form commandName="employee" action="UpdateEmployee" method="post">
<h2 id="id1" align="center">EMPLOYEE DATA</h2>

	<div id="id2">
		<h3 align="center">Update Employee Form</h3>
		</div>

<table align="center">
<tr><td>EmployeeId</td><td><s:input path="employeeId" cssClass="form" required="true"/></td><td>
<tr><td>Firstname</td><td><s:input path="firstName" cssClass="form" required="true"/></td><td>
<tr><td>LastName</td><td><s:input path="lastName" cssClass="form" required="true"/></td><td>
<tr><td>gender</td><td><s:input path="gender" cssClass="form" required="true"/></td><td>
<tr><td>mobileNumber</td><td><s:input path="mobileNumber" cssClass="form" required="true"/></td><td>
<tr><td>dateOfJoin</td><td><s:input path="dateOfJoin" cssClass="form" required="true"/></td><td>
<tr><td>dateOfBirth</td><td><s:input path="dateOfBirth" cssClass="form" required="true"/></td><td>
<tr><td>address</td><td><s:input path="address" cssClass="form" required="true"/></td><td>

<tr><td></td><td><input type="submit"></td><td></td></tr>
</table>
<a href="home">Return to home</a>
</s:form>
</body>
</html>