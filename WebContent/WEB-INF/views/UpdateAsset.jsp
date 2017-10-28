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

		var serialNumber = document.getElementById("serialNumber").value;
		var assetName = document.getElementById("assetName").value;
		var cost = document.getElementById("cost").value;

		var formValid = true;
		var formValid1 = false;

		if (IsEmpty(serialNumber) == true) {
			document.getElementById("name_error").innerHTML = "Please Enter serial number";
			formValid = false;
		} else {
			document.getElementById("name_error").innerHTML = null;
			formValid = true;
		}
		if (IsEmpty(assetName) == true) {
			document.getElementById("lname_error").innerHTML = "Please Enter last name";
			formValid = false;
		} else if (assetName.length >= 40) {
			document.getElementById("lname_error").innerHTML = "Value should not exceed 40";
			formValid1 = true;
		} else if (assetName.length <= 1) {
			document.getElementById("lname_error").innerHTML = "Value should be greater than 5";
			formValid1 = true;
		} else {
			document.getElementById("lname_error").innerHTML = null;
			formValid = true;
		}

		if (IsEmpty(cost) == true) {
			document.getElementById("mobile_error").innerHTML = "Please Enter mobile number";
			formValid = false;
		} else if (cost.length > 10) {
			document.getElementById("mobile_error").innerHTML = "Mobile number should not be more than 10 digits";
			formValid1 = true;
		} else if (cost.length < 1) {
			document.getElementById("mobile_error").innerHTML = "Mobile number should contain 10 digits";
			formValid1 = true;
		} else {
			document.getElementById("mobile_error").innerHTML = null;
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

	<s:form commandName="asset" action="UpdateAsset" method="post"
		onsubmit="return onSubmit()">
		<div id="id2">
			<h3 align="center">${viewdetails}</h3>

		</div>

		<table align="center">
			<tr>
				<td>AssetId</td>
				<td><s:input path="assetId" cssClass="form" readonly="true" required="true"/></td>
			</tr>
			<tr>
				<td>SerialNumber</td>
				<td><s:input path="serialNumber" cssClass="form"
						id="serialNumber" required="true"/><span id="name_error" ></span></td>
			</tr>
			<tr>
				<td>AssetName</td>
				<td><s:input path="assetName" cssClass="form" id="assetName" required="true"/><span
					id="lname_error"></span></td>
			</tr>
			<tr>
				<td>AssetType</td>
				<td><s:input path="assetType" cssClass="form" id="assetType" required="true"/><span
					id="mobile_error"></span></td>
			</tr>

			<%-- <tr>
				<td>Select Status</td>
				<td><input type="radio" name="status" value="Available"
					${asset.status eq "Available"?'checked="checked"':''} />Available <input
					type="radio" name="status" value="NotAvailable"
					${asset.status eq "NotAvailable"?'checked="checked"':''} />NotAvailable</td>
			</tr>
 --%>
			<tr>
				<td>cost</td>
				<td><s:input path="cost" cssClass="form" id="cost" required="true"/><span
					id="mobile_error"></span></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="update" onclick="return onSubmit()"></td>
				<td></td>
			</tr>

		</table>
	</s:form>

</body>
</html>