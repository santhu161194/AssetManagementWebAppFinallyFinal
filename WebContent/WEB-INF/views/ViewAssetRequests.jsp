<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vi</title>
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
<div id="content" style="width:100%">
	<div id="id2">
		<h3 align="center">Asset Requests</h3>
		</div>
	<h4><j:out value="${message}"></j:out></h4>
		<input class="form-control" id="myInput" type="text" placeholder="Search Request here....">
	
	<div style="overflow:scroll;height:180px;width:100%;overflow:auto">
	<table border="2">
		<tr>
			<th> EmployeeId
			
			<th>assetType
			
			<th>requestDate
			
			
		</tr>
		  <tbody id="myTable">
		<j:forEach var="assetReq" items="${assetRequests}">
			<tr>
				<td><j:out value="${assetReq.employeeId} "></j:out></td>
				<td><j:out value="${assetReq.assetType} "></j:out></td>
				<td><j:out value="${assetReq.requestDate} "></j:out></td>
				<td><a  class="showhide" id="viewAssetsByType?type=<j:out value="${assetReq.assetType}"></j:out>">Allocate</a></td>
				<td><a   class="showhide" id="removeAssetRequest?employeeId=<j:out value="${assetReq.employeeId}"></j:out>&type=<j:out value="${assetReq.assetType} "></j:out>">Delete</a></td>
				</tr>
				</j:forEach>
				</tbody>
				</table>
				
			</div>
			
			<div id="id2">
			<hr>
		<h3 align="center">New Type Of Asset Requests</h3>
		</div><!--2 tbl  -->
			
			<div style="overflow:scroll;height:200px;width:100%;overflow:auto">
	<table border="2">
		<tr>
			<th> EmployeeId
			
			<th>assetType
			
			<th>assetName
			
			<th>Request Date
			
		</tr>
		  <tbody id="myTable">
		<j:forEach var="newAssetReq" items="${newAssetRequests}">
			<tr>
				<td><j:out value="${newAssetReq.employeeId} "></j:out></td>
				<td><j:out value="${newAssetReq.assetType} "></j:out></td>
				<td><j:out value="${newAssetReq.assetName} "></j:out></td>
				<td><j:out value="${newAssetReq.requestDate} "></j:out></td>
				
				</tr>
				</j:forEach>
				</tbody>
				</table>
				
			</div>
			
			</div>
	
				</body>
				</html>
				
	