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
<s:form commandName="employee" action="addRoleToEmp" method="post">
<h2 id="id1" align="center">ADD ROLE TO EMP</h2>

	<div id="id2">
		<h3 align="center">Add Role To Emp</h3>
		</div>

<table align="center">
<%-- <tr><td>roleId</td><td><s:input path="roleId" cssClass="form" required="true"/></td><td>
<tr><td>roleName</td><td><s:input path="roleName" cssClass="form" required="true"/></td><td>
<tr><td>addedBy</td><td><s:input path="addedBy" cssClass="form" required="true"/></td><td>
<tr><td>addedDate</td><td><s:input path="addedDate" cssClass="form" required="true"/></td><td>
 --%>
<tr><td>employeeId</td><td><input type="text" name="employeeId"></td></tr>
<tr><td>roleId</td><td><input type="text" name="roleId"></td></tr>
<tr><td>addedBy</td><td><input type="text" name="addedBy"></td></tr>

<tr><td></td><td><input type="submit"></td><td></td></tr>
</table>
<a href="home">Return to home</a>
</s:form>
</body>
</html>