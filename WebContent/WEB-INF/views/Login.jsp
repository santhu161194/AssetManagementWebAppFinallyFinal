<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to MEDPLUS  Asset Management</title>





<script type="text/javascript">
	function onSubmit() {

		var uname = document.getElementById("username").value;
		var pass = document.getElementById("password").value;
		var flag = false;
		var flag2=true;
		
		
		if (IsEmpty(uname) == true) {
			document.getElementById("message").innerHTML = "* Please Enter Username";
			flag2=false;
		}
		else if (uname.length >= 40) {
			document.getElementById("message").innerHTML = "Username should not exceed 40 characters";
			flag = true;
		}
		else if (uname.length <= 5) {
			document.getElementById("message").innerHTML = "Username should be greater than 5 characters";
			flag = true;
		} 
		else
			{
			document.getElementById("message").innerHTML = "";
			flag=false;
			}
		if (IsEmpty(pass) == true) {
			document.getElementById("pass_error").innerHTML = "* Please Enter Password";
			flag2=false;
		}
		else if (uname.length <= 5) {
			document.getElementById("message").innerHTML = "Username should be greater than 5 characters";
			flag = true;
		} 
		else if (pass.length >= 15) {
			document.getElementById("pass_error").innerHTML = "Password should not exceed 15 characters";
			flag = true;
		}
		else if (pass.length < 6) {
			document.getElementById("pass_error").innerHTML = "Password should be greater than 5 characters";
			flag = true;
		} 

		if (!flag2) {
			return false;
		}
		
		if (flag) {
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
<style>
<%@include file="css/login.css"%>
</style>


</head>


<body>
	<div class="container">
		<div class="main">
			<h2>MEDPLUS <br>Asset Management</h2>





			<form action="login" id="form_id" method="post" name="myform"
				onsubmit="return onSubmit()">
				<font color="red"><div id="message">${message}</div></font> <font color="red"><div
						id="user_error"></div></font> <label>User Name :</label> <input
					type="text" name="username" id="username" /> <font color="red"><div
						id="pass_error"></div></font> <label>Password :</label> <input
					type="password" name="password" id="password" /> <input
					type="submit" value="Login" id="submit" name="submit" />
			</form>
		</div>
	</div>
</body>


</html>





























