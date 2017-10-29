<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
  $(function() { // when DOM is ready
	    $(".showhide").click(function(){ 
	    	var toLoad=$(this).attr('id');// when #showhidecomment is clicked
	    	
	        $("#content").load(toLoad); // load the sample.jsp page in the #chkcomments element
	    }); 
	});
  </script>
</head>
<body>
<div id="content">
	<form action="allocateAsset" method="post" enctype="multipart/form-data">
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
			<tr>
					<td>Portrait Photo: </td>
					<td><input id="photo" type="file" name="file" size="50" required/></td>
			</tr>
			</table>
			<input id="allocateAsset" class="showhide" type="submit">
		



	</form>
	</div>
</body>
</html>