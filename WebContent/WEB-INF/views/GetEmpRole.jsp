<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
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

<table align="center">
			
		
<j:forEach var="role" items="${roles}">
			<tr>
				<td><j:out value="${role}"></j:out></td>
				</tr>
				</j:forEach>
</table>
<a href="home">Return to home</a>

</body>
</html>