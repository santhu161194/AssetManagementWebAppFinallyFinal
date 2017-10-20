<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.red{
color:red;

}
.body .form{
float:left;
clear:both;
}

</style>
</head>
<body>
<s:form commandName="employee" action="getSingleEmployee" method="post">
<h2 id="id1" align="center">GET SINGLE EMPLOYEE</h2>

	<div id="id2">
		<h3 align="center">GET Single Employee</h3>
		</div>

<table align="center">


<tr><td>employeeId</td><td><input type="text" name="employeeId"></td></tr>


<tr><td></td><td><input type="submit"></td><td></td></tr>
</table>
<a href="home">Return to home</a>
</s:form>
</body>
</html>