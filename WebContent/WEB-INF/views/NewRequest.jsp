<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script>
  $(function() { // when DOM is ready
	    $(".showhide").click(function(){ 
	    	var toLoad=$(this).attr('id');// when #showhidecomment is clicked
	    	
	        $("#content").load(toLoad); // load the sample.jsp page in the #chkcomments element
	    }); 
	});
  </script>
<title>Insert title here</title>
</head>
<body>
<div id="content">
<form  action="assetrequest" method="post">
<h2 id="id1" align="center">Asset Request</h2>

	<div id="id2">
		<h3 align="center">${msg}</h3>
		</div>

<table align="center">

<tr><td>employeeId</td><td><input type="text" name="userName" value=${username} readonly></td></tr>
<tr><td>Asset Type</td><td><input type="text" name="assetType" required></td></tr>
<tr><td>Asset Name</td><td><input type="text" name="assetName" required></td></tr>

<tr><td></td><td><input id="assetrequest" type="submit" value="Submit" class="showhide"></td><td></td></tr>
</table>
<a href="home" >Return to home</a>
</form>

</div>

</body>
</html>