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

		var serialNo = document.getElementById("serialNumber").value;
		var assetName = document.getElementById("assetName").value;
		
		
		var cost = document.getElementById("cost").value;
		
		var formValid = true;
		var formValid1 = false;
		
		if (IsEmpty(serialNo) == true) {
			document.getElementById("serial_error").innerHTML = "Please Enter Serial No";	
			formValid = false;
		}else{
			document.getElementById("serial_error").innerHTML = null;	
			formValid = true;
		}
		
		if (IsEmpty(assetName) == true) {
			document.getElementById("assetName_error").innerHTML = "Please Enter Asset name";
			formValid = false;
		}
		else if (assetName.length >= 40) {
			document.getElementById("assetName_error").innerHTML = "AssetName should not exceed 40";
			formValid1 = true;
		}
		else if (assetName.length < 1) {
			document.getElementById("assetName_error").innerHTML = "AssetName should be greater than 1";
			formValid1 = true;
		}
		else{
			document.getElementById("assetName_error").innerHTML = null;	
			formValid = true;
		}
		if (IsEmpty(cost) == true) {
			document.getElementById("cost_error").innerHTML = "Please Enter cost";
			formValid = false;		
		}
		else if (cost.value <= 100) {
			document.getElementById("cost_error").innerHTML = "Cost should be greater 100";
			formValid1 = true;
		}
		else if (cost.length >= 1000000) {
			document.getElementById("cost_error").innerHTML = "Cost should not exceed 10000000";
			formValid1 = true;
		} 
		else{
			document.getElementById("cost_error").innerHTML = null;	
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
th, td {
    text-align: left;
    padding: 8px;
}    
span {
	color: red;
}

</style>
</head>
<body>
<s:form commandName="asset" action="addAsset" method="post" onsubmit="return onSubmit()">


	<div id="id2">
		<h3 align="center">Add Asset Form</h3>
		</div>

<table align="center">

<tr><td>serialNumber</td><td><s:input path="serialNumber" cssClass="form" id = "serialNumber"/><span id = "serial_error"></span></td></tr>
<tr><td>assetName</td><td><s:input path="assetName" cssClass="form" id = "assetName"/><span id = "assetName_error"></span></td></tr>


<%-- <tr><td>Asset Type</td><td><select>
	<option value = "Laptop" name="assetType">Laptop</option>
	<option value = "Desktop" name="assetType">Desktop</option>
	<option value = "Monitor" name="assetType">Monitor</option>
	<option value = "Mouse" name="assetType">Mouse</option>
	<option value = "Keyboard" name="assetType">Keyboard</option>	
</select></td></tr>  --%>
 
<tr><td>Select Asset Type:</td>
    <td>
         <select name="assetType" >
         <option value="Laptop" name="assetType">Laptop</option>
         <option value="Desktop" name="assetType">Desktop</option>
         <option value="Mouse" name="assetType">Mouse</option>
         <option value="Keyboard" name="assetType">Keyboard</option>
       </select>
   </td></tr>
<tr><td>Enter cost</td><td><s:input path="cost" cssClass="form" id = "cost"/><span id = "cost_error"></span></td></tr>
<tr><td>Select Status</td>
<td><input type = "radio" name = "status" value = "Available" id = "status" checked>Available
<input type = "radio" name = "status" value = "NotAvailable" id = "status">Not Available<span id = "status_error"></span>
</td></tr>
<tr><td>createdBy</td><td><s:input path="createdBy" cssClass="form" id = "createdBy" value="${username}"/><span id = "createdBy_error"></span></td></tr>
<tr><td></td><td><input type="submit" value="Submit" style="align:center" onclick="return onSubmit()"></td></tr>
</table>

</s:form>
</body>
</html>