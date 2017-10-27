<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Asset Request </title>

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
<div id="content">
<form  action="postAssetRequests" method="post">
<h2 id="id1" align="center"> Asset-Request</h2>

    <div id="id2">
        <h3 align="center">${message}</h3>
    </div>

 <table align="center">

    <tr>
    <td>employeeId</td>
    <td><input type="text" name="EmployeeId" readonly value=${username} ></td>
    </tr>
    <tr>
    <td>Select Asset Type:</td>
    <td>
         <select name="type" >
         <option value="Laptop" name="type">Laptop</option>
         <option value="Desktop" name="type">Desktop</option>
         <option value="Mouse" name="type">Mouse</option>
         <option value="Keyboard" name="type">Keyboard</option>
       </select>
   </td>

   </tr>

<tr>
<td>
</td>
<td><input type="submit" value="Submit" class="showhide"></td>

</tr>
</table>

</form>


</div>
</body>
</html>
