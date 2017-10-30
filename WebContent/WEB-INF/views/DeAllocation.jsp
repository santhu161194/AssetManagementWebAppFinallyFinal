<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="deallocateAsset" method="post">
		<table align="center">
			<tr>
				<td>AssetId</td>
				<td><input name=assetID value="${assetID}" readonly="true"></td>
				<td>
			<tr>
				<td>DeAssignedBy</td>
				<td><input name=deassignedBy value="${username}" readonly="true"></td>
			</tr>
			<tr></tr>
			<tr><td></td><td><input id="allocateAsset" class="showhide" type="submit" value="Confirm"></td></tr>
			</table>
		



	</form>
</body>
</html>