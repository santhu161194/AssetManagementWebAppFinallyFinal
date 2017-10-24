<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  function assetRequest(select){
	  var request=document.getElementById(select);
	  var sel=(request.options[request.selectedIndex].value);
	  window.location = '/Task2/postAssetRequests?type=' + sel;
  }
  
  </script>
  <script>
  $(function() { // when DOM is ready
	    $(".showhide").click(function(){ 
	    	var toLoad=$(this).attr('id');// when #showhidecomment is clicked
	    	
	        $("#content").load(toLoad); // load the sample.jsp page in the #chkcomments element
	    }); 
	});
  </script>
<title>Welcome Home</title>
<style type="text/css">
body {
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 14px;
    line-height: 1.42857143;
    color: #333;
    background-color: whitesmoke;
}
#upleft { 
width: 300px;
height: 100%;
background: grey;
float: left;
margin: 10px;
padding-left: 10px;
position: absolute;
left: -10px;
top: 42px;
}
#profile { 
   width:550px; 
				height: 200px;
				background:gray;
				float:left;
				 margin: 10px;
 
}

#upright { 
   width:600px;
				height:215px;
				background:gray;
				float:left;

}
#below { 
 height:215px;
				width:600px;
				background:gray;
				float:left;


}
#d4 { 
 height:50px;
				width:600px;
				background:white;
				float:left;


}
#table{
width:95%;
margin: 10px;
}
#th{
margin: 10px;
    border-bottom: 1px solid #ddd;
    padding: 8px;
    text-align: center;
    vertical-align: center;
}

#options{
margin-top: 50px;
}
.showhide{
line-height:40px;


}
#content{
float: left;
width: 60%;
height: 100%;
margin-top: 10px;
margin-left:20px;
}
.wrapper {
        display: flex;
}

#sidebar {
        min-width: 250px;
        max-width: 250px;
        height: 100vh;
        margin-top: -20px;
}
#sidebar {
    /* don't forget to add all the previously mentioned styles here too */
    background: #7386D5;
    color: #fff;
    transition: all 0.3s;
}

#sidebar .sidebar-header {
    padding: 20px;
    background: #6d7fcc;
}

#sidebar ul.components {
    padding: 20px 0;
    border-bottom: 1px solid #47748b;
}

#sidebar ul p {
    color: #fff;
    padding: 10px;
}

#sidebar ul li a {
    padding: 10px;
    font-size: 1.1em;
    display: block;
}
#sidebar ul li a:hover {
    color: #7386D5;
    background: #fff;
}
</style>
</head>
<body>
<j:choose>
<j:when test="${not empty sessionScope.username}">

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Asset Management</a>
    </div>
    <ul class="nav navbar-nav">
   <% List role = (List)session.getAttribute("role");
   if(role.contains("admin")){
%>
      <li class="active"><a href="emphome?username=${username}">Admin</a></li>
      <%} if(role.contains("edp")){%>
      <li><a  href="EDPHome?username=${username}">EDP</a></li>
      <%} %>
      <li ><a  href="employee?username=${username}">Employee</a></li>

      
    </ul>
    <ul class="nav navbar-nav navbar-right">

      <li><a href="invalidate"><span class="glyphicon glyphicon-user"></span> Logout</a></li>
      
    </ul>
  </div>
  
  
  <div id="upleft">
  <div id="profile">
  <h3 align="center" style="color: blue;">profile</h3>
   <div class="userdata">
 <table width="90%" height="90%" border="0" align="center" cellpadding="0" cellspacing="0" style="border-top: black;">
  <tr>
    <td width="41%" valign="top"><b>Employee Id</b></td>
    <td width="2%" valign="top">:</td>
    <td width="57%" valign="top"><j:out value="${emp.employeeId} "></j:out><td width="38%"></td>
  </tr>
 <tr>
    <td valign="top"><b>First Name</b></td>
    <td valign="top">:</td>
    <td ><j:out value="${emp.firstName} "></j:out></td>
  </tr>
  <tr>
    <td valign="top"><b>Last Name</b></td>
    <td valign="top">:</td>
    <td valign="top"><j:out value="${emp.lastName} "></j:out></td>
  </tr>
    <tr>
    <td valign="top"><b>Mobile No</b></td>
    <td valign="top">:</td>
    <td valign="top"><j:out value="${emp.mobileNumber} "></j:out> </td>
  </tr>
  <tr>
    <td valign="top"><b>Birth Date</b></td>
    <td valign="top">:</td>
    <td valign="top"><j:out value="${emp.dateOfBirth} "></j:out>
      </td>
  </tr>
  <tr>
    <td valign="top"><b>Joining Date</b></td>
    <td valign="top">:</td>
    <td valign="top"><j:out value="${emp.dateOfJoin} "></j:out>
      </td>
  </tr>
</table>

 </div>
  <br>
  </div>
  
  <br>
  <div id="upleft">
  <h4 align="center">Your Assets</h1>
  <table id="table" border="2" margin: 10px; >
		<tr id="th">
			<th id="th"> assetId
			<th id="th">serialNumber
			<th id="th">assetName
			<th id="th">assetType
			<th id="th">cost
			<th id="th"> status
			<!-- <th>createdDate
			<th> createdBy
			<th>modifiedBy -->
			
		</tr>
		
		<j:forEach var="asss" items="${assets}">
			<tr id="th">
				<td id="th"><j:out value="${asss.assetId} "></j:out></td>
				<td id="th"><j:out value="${asss.serialNumber} "></j:out></td>
				<td id="th"><j:out value="${asss.assetName} "></j:out></td>
				<td id="th"><j:out value="${asss.assetType} "></j:out></td>
				<td id="th"><j:out value="${asss.cost} "></j:out></td>
				<%-- <td id="th"><j:out value="${asss.status} "></j:out></td> --%>
				<%-- <td><j:out value="${asss.createdDate} "></j:out></td>
				<td><j:out value="${asss.createdBy} "></j:out></td>
				<td><j:out value="${asss.modifiedBy} "></j:out></td> 
				<td><a href=postAssetRequests?type=<j:out value="${asss.assetType}"></j:out>>Asset Request</a></td>
				<td><a href="UpdateAsset?code=<j:out value="${asss.assetId}"></j:out>">Update</a></td>--%>
				</tr>
				</j:forEach>
				</table>
				
				<a href="home">Return to home</a>
		
  </div>
  </div>
<div id="upright">
<table align="center" border="0" style="margin-top: 10%">
<tr>
<td>
<a href="#" onclick="assetRequest('mySelect')"  class="rightBottomTab"><h4>Request Assets from Avilable</h4></a>
</td>
<td>
<select id="mySelect">
  <option value="Laptop">Laptop</option>
  <option value="Desktop">Desktop</option>
  <option value="Mouse">Mouse</option>
  <option value="Keyboard">Keyboard</option>
</select>
</td>
</tr>
<tr style="background-color: gray;">
<td colspan=2>
<a href="#"  class="rightBottomTab"><h4>Request New type of Asset</h4></a>
</td>
</tr>
<tr>
<td colspan=2>
<a href="#"  class="rightBottomTab"><h4>change password</h4></a>
</td>
</tr>

</table>



</div>
<div id="d4">
<h4 align="center">My Requests</h1>
</div>
<div id="below">

  <table id="table" border="2" margin: 10px; >
		<tr id="th">
			<th id="th"> assetId
			<th id="th">serialNumber
			<th id="th">assetName
			
			<!-- <th>createdDate
			<th> createdBy
			<th>modifiedBy -->
			
		</tr>
		
		<j:forEach var="requestList" items="${requestList}">
			<tr id="th">
				<td id="th"><j:out value="${requestList.employeeId} "></j:out></td>
				<td id="th"><j:out value="${requestList.assetType} "></j:out></td>
				<td id="th"><j:out value="${requestList.requestDate} "></j:out></td>
				
				<%-- <td><j:out value="${asss.createdDate} "></j:out></td>
				<td><j:out value="${asss.createdBy} "></j:out></td>
				<td><j:out value="${asss.modifiedBy} "></j:out></td> 
				<td><a href=postAssetRequests?type=<j:out value="${asss.assetType}"></j:out>>Asset Request</a></td>
				<td><a href="UpdateAsset?code=<j:out value="${asss.assetId}"></j:out>">Update</a></td>--%>
				</tr>
				</j:forEach>
				</table>
				

</div>
  </j:when>
  <j:otherwise>
  <a href="login">Click here to login</a>
</j:otherwise>
</j:choose></body>
</html>