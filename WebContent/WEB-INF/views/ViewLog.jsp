<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
table{
width:100%;
}
th, td {
    border-bottom: 1px solid #ddd;
    padding: 8px;
    text-align: center;
    vertical-align: center;
}
tr:nth-child(even) {background-color: #f2f2f2}
th {
    background-color: black;
    color: white;
}
tbody div{
    overflow:scroll;
    height:100px;
}
</style>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
</head>
<body>
	<s:form commandName="asset" action="viewAssetRequests" method="post">
	<h2 id="id1" align="center">Asset Mapping Log</h2>
	<h4><j:out value="${updatestatus}"></j:out></h4>
	<input class="form-control" id="myInput" type="text" placeholder="Search Request here....">
	<div style="overflow:scroll;height:400px;width:100%;overflow:auto">
	<table border="2">
		<tr>
			<th> AssignedTo
			
			<th>AssignedBy
			
			<th>AssetId
			<th>HandOverDate
			<th>Return Date
			<th>Status
			
			
		</tr>
	  <tbody id="myTable">
		
		<j:forEach var="assetLog" items="${assetMappingLog}">
			<tr>
				<td><j:out value="${assetLog.employeeId} "></j:out></td>
				<td><j:out value="${assetLog.assignedBy} "></j:out></td>
				<td><j:out value="${assetLog.assetId} "></j:out></td>
			    <td><j:out value="${assetLog.assignedDate} "></j:out></td>
			     <td><j:out value="${assetLog.returnDate} "></j:out></td>
				 <td><j:out value="${assetLog.status} "></j:out></td>
				</tr>
				</j:forEach>
		</tbody>
				</table>
				</div>
				
				</s:form>
				</body>
				</html>
				
	