<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script  type="text/javascript">
function onSubmit() {

	var employeeId = document.getElementById("employeeId").value;
		var oldPassword = document.getElementById("oldPassword").value;
		var newPassword = document.getElementById("newPassword").value;
		//var changedBy = document.getElementById("changedBy").value;
		var flag = true;
		var flag2 = false;
		
		if (IsEmpty(employeeId) == true) {
			document.getElementById("id_error").innerHTML = "Please Enter employee id name";	
			flag = false;
		}
		else
			{
			document.getElementById("id_error").innerHTML = "";	
			flag=true;
			}
		if (IsEmpty(oldPassword) == true) {
			document.getElementById("oldPassword_error").innerHTML = "Please Enter old password";
			flag = false;
		}
		else 
		{
		document.getElementById("oldPassword_error").innerHTML = "";	
		flag=true;
		}
		 if (newPassword.length >= 40) {
			document.getElementById("newPassword_error").innerHTML = "Password should not exceed 40";
			flag2 = true;
		}
		else if (newPassword.length <= 5) {
			document.getElementById("newPassword_error").innerHTML = "Password should be greater than 5";
			flag2 = true;
		}else
		{
			document.getElementById("newPassword_error").innerHTML = "";	
			flag=true;
			}
		
		 if (document.getElementById('newPassword').value !=
		            document.getElementById('confirmPassword').value){
			    document.getElementById("confirmPassword_error").innerHTML = "Password and Confirm Password Not Match";		    
			    flag2 = true;
		}
		
		if (IsEmpty(newPassword) == true) {
			document.getElementById("newPassword_error").innerHTML = "Please Enter new password";
			flag = false;		
		}
		
		
		if (flag2) {
			return false;
		}
		if (!flag) {
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
</head>
<body>
	<form action="changePassword" method="post" onsubmit = "return onSubmit()">
		
     <h2 id="id1" align="center">CHANGE PASSWORD</h2>

	     <div id="id2">
		     <h3 align="center"></h3>
		  </div>

<table align="center">

 <tr><td>employeeId</td><td><input type="text" name="employeeID" id="employeeId" readonly value=${username} ><span id = "id_error"></span></td></tr>
 <tr><td>oldPassword</td><td><input type="password" name="oldPassword" id="oldPassword"  value=""><span id = "oldPassword_error"></span></td></tr>
 <tr><td>newPassword</td><td><input type="password" name="newPassword" id="newPassword"><span id = "newPassword_error"></span></td></tr> 
 <tr><td>ReEnter Password</td><td><input type="password"  id="confirmPassword"><span id = "confirmPassword_error"></span></td></tr> 

 <tr><td></td><td><input type="submit" value="SUBMIT"></td><td></td></tr>
  </table>

	</form>
</body>
</html>
