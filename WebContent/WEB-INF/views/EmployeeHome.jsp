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

#content{
float: left;
width: 60%;
height: 100%;
margin-top: 10px;
}
#options{
margin-top: 50px;
}
.showhide{
line-height:40px;


}
.wrapper {
        display: flex;
        float:left;
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
      <li ><a href="adminhome?username=${username}">Admin</a></li>
      <%} if(role.contains("edp")){%>
      <li><a  href="EDPHome?username=${username}">EDP</a></li>
      <%} %>
      <li class="active"><a  href="employee?username=${username}">Employee</a></li>

      
    </ul>
    <ul class="nav navbar-nav navbar-right">

      <li><a href="invalidate"><span class="glyphicon glyphicon-user"></span> Logout</a></li>
      
    </ul>
 
  </div>
 
 </nav>
  <div class="wrapper">

        <nav id="sidebar">

                
          
 <ul class="list-unstyled components">
 
  <li><a id="empassets?username=${username}" class="showhide">My Assets</a></li><br>    
     <li><a id="emprequest?username=${username}" class="showhide">My Requests</a></li><br>    
   
   
    <li><a id="postAssetRequests" href="#" class="showhide">Request Asset</a></li><br>
    <li><a id="assetrequest" href="#" class="showhide">Request New type of Asset</a></li><br>
       <li><a id="changePassword" href="#" class="showhide">Change Password</a></li><br>
  </ul>
  </nav>
  </div>
    <!-- the content is shown here -->
    
    
    <div id="content">
    <h5>${message}</h5>
    </div>
 


  </j:when>
  <j:otherwise>
  <a href="login">Click here to login</a>
</j:otherwise>
</j:choose></body>
</html>
