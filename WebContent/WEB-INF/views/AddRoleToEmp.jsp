<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script  type="text/javascript">
function onSubmit() {
		
		var employeeId = document.getElementById("employeeId").value;
		var roleId = document.getElementById("roleId").value;
		
		var formValid = true;
		var formValid1 = false;
		
		if (IsEmpty(employeeId) == true) {
			
			document.getElementById("employeeId_error").innerHTML = "Please Enter employee id name";	
			formValid = false;
		}
		else if (employeeId.length >= 40) {
			document.getElementById("employeeId_error").innerHTML = "Value should not exceed 10";
			formValid1 = true;
		}
		else if (employeeId.length <= 1) {
			document.getElementById("employeeId_error").innerHTML = "Value should be greater than 1";
			formValid1 = true;
		} 
		else 
			{
			document.getElementById("employeeId_error").innerHTML =null;
			formValid=true;
			formValid1=false;
			}
		
		if (IsEmpty(roleId) == true) {
			document.getElementById("roleId_error").innerHTML = "Please Enter role id";
			formValid = false;
		}
		else
			{
			document.getElementById("roleId_error").innerHTML = null;
			formValid=true;
			}
		
		
		if (formValid1) {
			return false;
		}
		if (!formValid) {
			return false;
		}
		return true;
	}
	function IsEmpty(input) {
		if (input.replace(/^\s+|\s+$/g, "") === "") {
			return true;
		}
		
	}
</script>
<style type="text/css">
.red{
color:red;

}
.body .form{
float:left;
clear:both;
}
span {
	color: red;
}
</style>
</head>
<body>
<s:form commandName="employee" action="addRoleToEmp" method="post" onsubmit = "return onSubmit()">
<h2 id="id1" align="center">ADD ROLE TO EMP</h2>

	<div id="id2">
		<h3 align="center">${viewdetails}</h3>
		</div>

<table align="center">
<%-- <tr><td>roleId</td><td><s:input path="roleId" cssClass="form" required="true"/></td><td>
<tr><td>roleName</td><td><s:input path="roleName" cssClass="form" required="true"/></td><td>
<tr><td>addedBy</td><td><s:input path="addedBy" cssClass="form" required="true"/></td><td>
<tr><td>addedDate</td><td><s:input path="addedDate" cssClass="form" required="true"/></td><td>
 --%>
<tr><td>employeeId</td><td><input name="employeeId" id="employeeId" type="number"><span id = "employeeId_error"></span></td></tr>
<tr><td>roleId</td><td><input type="number" name="roleId" id="roleId"><span id="roleId_error"></span></td></tr>
<tr><td>addedBy</td><td><input type="text" name="addedBy" id="addedBy" value="${username}"><span id="addedBy_error"></span></td></tr>

<tr><td></td><td><input type="submit"></td><td></td></tr>
</table>
<a href="home">Return to home</a>
</s:form>
</body>
</html>