<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	$(function() { // when DOM is ready
		$(".showhide").click(function() {
			var toLoad = $(this).attr('id');// when #showhidecomment is clicked

			$("#content").load(toLoad); // load the sample.jsp page in the #chkcomments element
		});
	});
</script>
<script type="text/javascript">
	function onSubmit() {

		var assetType = document.getElementById("assetType").value;
		var assetName = document.getElementById("assetName").value;

		var formValid = true;

		if (IsEmpty(assetName) == true) {
			document.getElementById("assetName_error").innerHTML = "Please Enter Asset name";
			formValid = false;
		} else {
			document.getElementById("assetName_error").innerHTML = null;
			formValid = true;
		}
		if (IsEmpty(assetType) == true) {
			document.getElementById("assetType_error").innerHTML = "Please Enter Asset Type";
			formValid = false;
		} else {
			document.getElementById("assetType_error").innerHTML = null;
			formValid = true;
		}

		if (formValid) {
			return true;
		}
		if (!formValid) {
			return true;
		}
		
	}
	function IsEmpty(input) {
		if (input.replace(/^\s+|\s+$/g, "") === "") {
			return true;
		}

	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div id="content">
		<form action="assetrequest" method="post" >
			<h2 id="id1" align="center">Asset Request</h2>

			<div id="id2">
				<h3 align="center">${message}</h3>
			</div>

			<table align="center">

				<tr>
					<td>employeeId</td>
					<td><input type="text" name="userName"
						value=${username
						} readonly>
				</tr>

				<tr>
					<td>Asset Type</td>
					<td><input type="text" name="assetType" id="assetType" required
					><span id="assetType_error"></span></td>
				</tr>

				<tr>
					<td>Asset Name</td>
					<td><input type="text" name="assetName" id="assetName" required
						><span id="assetName_error"></span></td>
				</tr>

				<tr>
				
					
					<td>
					<td><input type="submit" value="submit" onclick="return onSubmit()"></td>
				</td>
				</tr>


			</table>

		</form>

	</div>

</body>
</html>