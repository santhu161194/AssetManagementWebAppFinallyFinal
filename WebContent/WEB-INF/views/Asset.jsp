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
th, td {
    text-align: left;
    padding: 8px;
}    


</style>
</head>
<body>
<s:form commandName="asset" action="addAsset" method="post">
<h2 id="id1" align="center">ASSET DATA</h2>

	<div id="id2">
		<h3 align="center">Add Asset Form</h3>
		</div>

<table align="center">
<tr><td>assetId</td><td><s:input path="assetId" cssClass="form" required="true"/></td><td>
<tr><td>serialNumber</td><td><s:input path="serialNumber" cssClass="form" required="true"/></td><td>
<tr><td>assetName</td><td><s:input path="assetName" cssClass="form" required="true"/></td><td>

<tr><td>Enter  AssetTypeEnum{Laptop/Deskto/Monitor//Mouse)</td><td><s:input path="assetType" cssClass="form" required="true"/></td><td>
<tr><td>Enter cost</td><td><s:input path="cost" cssClass="form" required="true"/></td><td>
<tr><td>Enter AssetStatusEnum{Available/Notavailable}</td><td><s:input path="status" cssClass="form" /></td><td>


</table>
	
 
 	<input type="submit" value="Submit">
	


<a href="home">Return to home</a>
</s:form>


</body>
</html>