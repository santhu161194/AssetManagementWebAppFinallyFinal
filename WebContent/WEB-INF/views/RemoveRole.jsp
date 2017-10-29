<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function onSubmit() {

		var employeeId = document.getElementById("employeeId").value;
		var roleName = document.getElementById("roleName").value;

		var validForm = true;
		var validForm1 = false;

		if (IsEmpty(roleId) == true) {
			document.getElementById("id_error").innerHTML = "Please Enter role id";

			validForm = false;
		} else {
			document.getElementById("id_error").innerHTML = null;
			validForm = true;
		}
		if (IsEmpty(roleName) == true) {
			document.getElementById("name_error").innerHTML = "Please Enter role name";
			validForm = false;
		} else if (roleName.length >= 10) {
			document.getElementById("name_error").innerHTML = "Value should not exceed 40";
			validForm1 = true;
		} else if (roleName.length <= 2) {
			document.getElementById("name_error").innerHTML = "Value should be greater than 2";
			validForm1 = true;
		} else {
			document.getElementById("name_error").innerHTML = null;
			validForm1 = false;
			validForm = true;
		}

		if (validForm1) {
			return false;
		}
		if (!validForm) {
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
.red {
	color: red;
}

.body .form {
	float: left;
	clear: both;
}

span {
	color: red;
}
</style>
</head>
<body>
	<s:form commandName="employee" action="removeRole" method="post"
		onsubmit="return onSubmit()">


		<div id="id2">
			<h3 align="center">${viewdetails}</h3>
		</div>

		<table align="center">
			<%-- <tr><td>roleId</td><td><s:input path="roleId" cssClass="form" required="true"/></td><td>
<tr><td>roleName</td><td><s:input path="roleName" cssClass="form" required="true"/></td><td>
<tr><td>addedBy</td><td><s:input path="addedBy" cssClass="form" required="true"/></td><td>
<tr><td>addedDate</td><td><s:input path="addedDate" cssClass="form" required="true"/></td><td>
 --%>
			<tr>
				<td>employeeId</td>
				<td><input type="text" name="employeeId" id="employeeId" required="true"><span
					id="id_error"></span></td>
			</tr>
			<%-- <tr>
				<td>roleName</td>
				<td><input type="text" name="roleName" id="roleName" required="true"><span
					id="name_error"></span></td>
			</tr> --%>
			<td>Select Role:</td>
    <td>
         <select name="roleName" >
         <option value="admin" name="roleName" >Admin</option>
         <option value="edp" name="roleName">EDP</option>
         <option value="employee" name="roleName" checked>Employee</option>
        
       </select>
   </td>
			<tr>
				<td>removedBy</td>
				<td><input type="text" name="removedBy" id="removedBy"
					value="${username}"><span id="removedBy_error" required="true"></span></td>
			</tr>


			<tr>
				<td></td>
				<td><input type="submit" value="submit" onclick="return onSubmit()"></td>
				<td></td>
			</tr>
		</table>
	</s:form>
</body>
</html>