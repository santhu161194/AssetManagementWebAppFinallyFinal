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
<h2 align="center">EMPLOYEE INFORMATION</h2>
<h3 align="center">Employee Updation Form</h3>
<s:form commandName="employee" action="UpdateEmployee" method="post">
<table align="center">
<tr><td>Enter code</td><td><s:input path="code" cssClass="form" readonly="true"/></td><td><s:errors path="code" cssClass="red" ></s:errors></td></tr>
<tr><td>Enter Firstname</td><td><s:input path="fname" cssClass="form"/></td><td><s:errors path="fname" cssClass="red"></s:errors></td></tr>
<tr><td>Enter LastName</td><td><s:input path="lname" cssClass="form"/></td><td><s:errors path="lname" cssClass="red"></s:errors></td></tr>
<tr><td>Enter Job</td><td><s:input path="job" cssClass="form"/></td><td><s:errors path="job" cssClass="red"></s:errors></td></tr>
<tr><td>Enter Salary</td><td><s:input path="salary" cssClass="form"/></td><td><s:errors path="salary" cssClass="red"></s:errors></td></tr>
<tr><td>Enter Deptno</td><td><s:input path="deptno" cssClass="form" readonly="true"/></td><td><s:errors path="deptno" cssClass="red"></s:errors></td></tr>
<tr><td></td><td><input type="submit"></td><td></td></tr>
</table>
<a href="home">Return to home</a>
</s:form>
</body>
</html>