<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="changePassword" method="post">
		<table align="center">
			<tr>
				<td>EmployeeId</td>

				<td><input name=employeeID  value=${username} readonly/></td>
				
			<tr>
				<td>Old Password</td>

				<td><input name=oldpassword required/></td>
				

			<tr>
			<tr>
				<td>Re enter Old Password</td>

				<td><input name=oldpassword1 required/></td>
			

			<tr>
			<tr>
				<td>New Password</td>
				<td><input name=newpassword required/></td>
			<tr>
		

		
			<tr>	

			</table>
			<div align="center">
			<input type="submit" value="Submit">
		    </div>



	</form>
</body>
</html>
