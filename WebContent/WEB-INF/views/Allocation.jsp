<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="allocateAsset" method="post">
		<table align="center">
			<tr>
				<td>EmployeeId</td>
				<td><input name=employeeID /></td>
				<td>
			<tr>
				<td>AssetId</td>
				<td><input name=assetID value="${assetID}"></td>
				<td>
			<tr>
				<td>AssignedBy</td>
				<td><input name=assignedBy value=<%=session.getAttribute("username")%>></td>
			</tr>
			</table>
			<input type="submit">
		



	</form>
</body>
</html>