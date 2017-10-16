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
<h3 align="center">Department Updation Form</h3>
<s:form commandName="department" action="UpdateDepartment" method="post">
<table align="center">
<tr><td>Deptno</td><td><s:input path="deptno" cssClass="form" readonly="true"/></td><td></td></tr>
<tr><td>Enter Deptname</td><td><s:input path="deptname" cssClass="form"/></td><td></td></tr>
<tr><td>Enter Location</td><td><s:input path="location" cssClass="form"/></td><td></td></tr>
<tr><td></td><td><input type="submit"></td><td></td></tr>
</table>
<a href="home">Return to home</a>
</s:form>
</body>
</html>