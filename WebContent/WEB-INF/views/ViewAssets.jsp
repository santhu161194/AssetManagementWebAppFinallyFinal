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
</style>


<script>
  $(function() { // when DOM is ready
	    $(".showhide").click(function(){ 
	    	var toLoad=$(this).attr('id');// when #showhidecomment is clicked
	    	
	        $("#content").load(toLoad); // load the sample.jsp page in the #chkcomments element
	    }); 
	});
  
  
 


  </script>
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
	


	<div id="content">
		<h3 align="center">${viewdetails}</h3>
		</div>
	<h4><j:out value="${updatestatus}"></j:out></h4>
	<input class="form-control" id="myInput" type="text" placeholder="Search Request here....">
	<div style="overflow:scroll;height:400px;width:100%;overflow:auto">
	<table border="2">
		<tr>
			<th> assetId
			<th>serialNumber
			<th>assetName
			<th>assetType
			<th>cost
			<th> status
		
			
		</tr>
		<tbody id="myTable">
		<j:forEach var="asset" items="${assets}">
			<tr>
				<td><j:out value="${asset.assetId} "></j:out></td>
				<td><j:out value="${asset.serialNumber} "></j:out></td>
				<td><j:out value="${asset.assetName} "></j:out></td>
				<td><j:out value="${asset.assetType} "></j:out></td>
				<td><j:out value="${asset.cost} "></j:out></td>
				<td><j:out value="${asset.status} "></j:out></td>
								
				<j:if test="${viewdetails eq 'Available Assets'}">
				<td><a class="showhide" id="allocateAsset?assetID=<j:out value="${asset.assetId}"></j:out>">Allocate Asset</a></td>
				</j:if>
				<j:if test="${viewdetails eq 'Allocated Assets'}">
				<td><a class="showhide" id="deallocateAsset?assetID=<j:out value="${asset.assetId}"></j:out>">DeAllocate Asset</a></td>
				</j:if>
				<j:if test="${viewdetails eq 'All Assets'}">
				<j:if test="${requestrole eq 'admin'}">
				<td><a class="showhide" id="UpdateAsset?assetID=<j:out value="${asset.assetId}"></j:out>">Update</a>
				</j:if>
				</j:if>
				</tr>
				</j:forEach>
				</tbody>
				</table>
				</div>
			
			
				</body>
				</html>
				
	