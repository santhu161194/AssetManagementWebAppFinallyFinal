<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style type="text/css">
    .container {
        width: 300px;
        clear: both;
    }
    .container input {
        width: 100%;
        clear: both;
    }

    </style>
<title>Insert title here</title>
</head>
<body>
<h2 align="center">EMPLOYEE INFORMATION</h2>
<h3 align="center">Department Form</h3>	
<s:form action="dept" commandName="department" method="post">
<div class=container>
<table align="center">
<tr><td>department number</td><td><s:input path="deptno" ></s:input></td></tr>
<tr><td>Department name</td><td><s:input path="deptname" ></s:input></td></tr>
<tr><td>Location</td><td><s:input path="location" ></s:input></td></tr>
</table>
</div>
<input type="submit">

<a href="home">Go to home</a>
</s:form>

</body>
</html>