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
		
		
		var firstName = document.getElementById("firstName").value;
		var lastName = document.getElementById("lastName").value;
		
		var mobileNumber = document.getElementById("mobileNumber").value;
		var dateOfBirth = document.getElementById("dateOfBirth").value;
		var dateOfJoin = document.getElementById("dateOfJoin").value;
		var address = document.getElementById("address").value;
		
		var formValid = true;
		var formValid1 = false;
		
		
		if (IsEmpty(firstName) == true) {
			document.getElementById("name_error").innerHTML = "Please Enter first name";
			formValid = false;
		}
		else if (firstName.length >= 40) {
			document.getElementById("name_error").innerHTML = "Value should not exceed 40";
			formValid1 = true;
		}
		else if (firstName.length <= 5) {
			document.getElementById("name_error").innerHTML = "Value should be greater than 5";
			formValid1 = true;
		} 
		else
			{
			document.getElementById("name_error").innerHTML = null;
			formValid = true;
			}
		if (IsEmpty(lastName) == true) {
			document.getElementById("lname_error").innerHTML = "Please Enter last name";
			formValid = false;		
		}
		else if (lastName.length >= 40) {
			document.getElementById("lname_error").innerHTML = "Value should not exceed 40";
			formValid1 = true;
		}
		else if (lastName.length <= 5) {
			document.getElementById("lname_error").innerHTML = "Value should be greater than 5";
			formValid1 = true;
		} 
		else
			{
			document.getElementById("lname_error").innerHTML = null;
			formValid = true;
			}
		
		
		if (IsEmpty(mobileNumber) == true) {
			document.getElementById("mobile_error").innerHTML = "Please Enter mobile number";
			formValid = false;		
		}
		else if (mobileNumber.length > 10) {
			document.getElementById("mobile_error").innerHTML = "Mobile number should not be more than 10 digits";
			formValid1 = true;
		}
		else if (mobileNumber.length < 10) {
			document.getElementById("mobile_error").innerHTML = "Mobile number should contain 10 digits";
			formValid1 = true;
		}
		else
			{
			document.getElementById("mobile_error").innerHTML = null;
			formValid = true;
			}
		if (IsEmpty(dateOfBirth) == true) {
			document.getElementById("date_error").innerHTML = "Please Enter Date of birth";
			formValid = false;		
		}
		else
			{
			document.getElementById("date_error").innerHTML = null;
			formValid = true;
			}
		if (IsEmpty(dateOfJoin) == true) {
			document.getElementById("dateJoin_error").innerHTML = "Please Enter Joined date";
			formValid = false;		
		}
		else
			{
			document.getElementById("dateJoin_error").innerHTML = null;
			formValid = true;
			}
		if (IsEmpty(address) == true) {
			document.getElementById("address_error").innerHTML = "Please Enter address";
			formValid = false;		
		}
		else if (address.length >= 255) {
			document.getElementById("address_error").innerHTML = "Address should not exceed 255";
			formValid1 = true;
		}
		else if (address.length < 6) {
			document.getElementById("address_error").innerHTML = "Address should be greater than 6";
			formValid1 = true;
		} 
		else
			{
			document.getElementById("address_error").innerHTML = null;
			formValid = true;
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

<s:form commandName="employee" action="UpdateEmployee" method="post" onsubmit="return onSubmit()">
<h2 id="id1" align="center">EMPLOYEE DATA</h2>

	<div id="id2">
		<h3 align="center">${viewdetails}</h3>
		
		</div>

<table align="center">
<tr><td>EmployeeId</td><td><s:input path="employeeId" cssClass="form" readonly="true" required="true"/></td></tr>
<tr><td>Firstname</td><td><s:input path="firstName" cssClass="form" id = "firstName" required="true"/><span id = "name_error"></span></td></tr>
<tr><td>LastName</td><td><s:input path="lastName" cssClass="form" id = "lastName" required="true"/><span id = "lname_error"></span></td></tr>

  <tr><td>Select Gender</td><td><input type="radio" name="gender" value="MALE" ${employee.gender eq "MALE"?'checked="checked"':''}/>Male
		<input type="radio" name="gender"  value="FEMALE"  ${employee.gender eq "FEMALE"?'checked="checked"':''}/>female</td></tr>  
  
<%-- <tr><td>Enter Gender</td><td><s:input path="gender" cssClass="form"/></td></tr> 
  --%>
<tr><td>Enter MobileNumber</td><td><s:input path="mobileNumber" cssClass="form" id="mobileNumber" pattern="[789][0-9]{9}" title="Phone number with 7-9 and remaing 9 digit with 0-9" required="true"/><span id = "mobile_error"></span></td></tr>
<tr><td>Enter DateOfBirth</td><td><s:input path="dateOfBirth" cssClass="form" id="dateOfBirth" /><span id = "date_error" required="true"></span></td></tr>
<tr><td>Enter DateOfJoining</td><td><s:input path="dateOfJoin" cssClass="form"  id="dateOfJoin" /><span id = "dateJoin_error" required="true"></span></td></tr>
<tr><td>Enter Address</td><td><s:textarea path="address" cssClass="form" id="address" required="true"/><span id = "address_error"></span></td></tr>
<tr><td></td><td><input type="submit" value="update" onclick="return onSubmit()"></td><td></td></tr>

</table>
</s:form>

</body>
</html>