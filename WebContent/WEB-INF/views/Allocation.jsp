<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
if(!$('input[type="file"]').val()) {
	   alert('please enter');
	   return false;
	}
</script>


<script type="text/javascript">
$(document).ready(function(){
    $("#photo").change(function(e){
var fd = new FormData();    
fd.append( 'file',$( '#photo' )[0].files[0] );
$.ajax({
  url: 'addImage',
  data: fd,
  processData: false,
  contentType: false,
  type: 'POST',
  success: function(data){
    alert(data);
  }
});
return false;
   
	 });
    
});	



</script>
</head>
<body>
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
					<td><input id="photo" type="file" name="photo" size="50" required/></td>
			</tr>
			</table>
			<input type="submit">
		



	</form>
</body>
</html>