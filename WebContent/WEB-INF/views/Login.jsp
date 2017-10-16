<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

html{
margin:0px;
}

#id1 {
    height: 50px;
    width: 100%;
    background-color: silver;
    position:relative;
    bottom:20px;
    
}
#id2 {
    height: 60px;
    width: 100%;
    background-color:white;
}
th, td {
    text-align: left;
    padding: 8px;
}    
img{
float:left;
}
</style>

    

</head>
<body>
	<img src="/assetmanagementwebapp/WebContent/WEB-INF/views/logo.jpg" width="100px" height="30px">
	<h2 id="id1" align="center">EMPLOYEE DATA</h2>

	<div id="id2">
		<h3 align="center">Login Page</h3>
		</div>
	<div class="form" style="border: 1px double; padding: 10px; margin:auto;width:40%;">
		<h4><FONT COLOR="RED">${status}</FONT></h4>
		<br>
		<div class="container">
		<form action="login" method="post">
		<table>
		<tr><td>Enter code</td><td><input name="username" required="true"/></td></tr>
		<tr><td>Enter password</td><td><input name="password" type="password" required="true"/></td></tr>
		<tr><td></td><td><input type="submit" value="Login"></td></tr>
		</table>
			
</form>
	
		</div>
	</div>
</body>
</html>