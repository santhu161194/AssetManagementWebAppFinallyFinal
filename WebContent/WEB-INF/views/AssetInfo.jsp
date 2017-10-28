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
align:"center";
width:60%;
}
th, td {
    border-bottom: 1px solid #ddd;
    padding: 8px;
    text-align: center;
    vertical-align: center;
}
tr:nth-child(even) {background-color: #f2f2f2}
th {
    background-color: gray;
    color: black;
    text-align: center;
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
  
</head>
<body>
	


<!-- 	<div id="content" align="center">
		<h3 align="center" style="color: blue;">Asset Details</h3>
		</div> -->
	<h4><j:out value="${updatestatus}"></j:out></h4>
	<table border="0" align="center">
	       <tr>
	       <td colspan="2">
	          <h3 align="center" style="color: blue;">Asset Details</h3>
	       </td>
	        </tr>
			<tr>
			    <th> assetId</th>
				<td><j:out value="${asset.assetId} "></j:out></td>
				<br>
				</tr>
				<tr>
				<th>serialNumber
				<td><j:out value="${asset.serialNumber} "></j:out></td>
				<br>
				</tr>
				<tr>
				<th>assetName
				<td><j:out value="${asset.assetName} "></j:out></td>
				<br>
				</tr>
				<tr>
				<th>assetType
				<td><j:out value="${asset.assetType} "></j:out></td>
				<br>
				</tr>
				<tr>
				<th>cost
				<td><j:out value="${asset.cost} "></j:out></td>
				<br>
				</tr>
				 
				<tr>
				<td colspan="2" align="center">
				<a id="empassets?username=${username}" class="showhide">Back</a>
				</td>
				</tr>
				<%-- <tr>
				
				<td><j:out value="${asset.modifiedBy} "></j:out></td>
				</tr> --%>
			
				</table>
				
			
			
				</body>
				</html>
				
	