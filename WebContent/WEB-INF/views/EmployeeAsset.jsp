<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
#id1{
margin-left: 10%;

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
    <h2 id="id1" align="">Allocated Asset</h2>
      <input class="form-control" id="myInput" type="text" placeholder="Search Asset here....">
    <table border="2" class="table table-striped">
        <tr>
            <th> assetId
            <th>serialNumber
            <th>assetName
            <th>assetType
            <th>cost
            
        </tr>
        <tbody id="myTable">
        <j:forEach var="asss" items="${assets}">
            <tr>
               <td><a data-toggle="tooltip" data-placement="bottom" title="Asset Info!"  class="showhide" id="assetInfo?assetId=<j:out value="${asss.assetId}"></j:out>"><j:out value="${asss.assetId}"></j:out></a></td>
               <td><j:out value="${asss.serialNumber} "></j:out></td>
                <td><j:out value="${asss.assetName} "></j:out></td>
                <td><j:out value="${asss.assetType} "></j:out></td>
                <td><j:out value="${asss.cost} "></j:out></td>
                </tr>
                </j:forEach>
                
         </tbody>       
                </table>
                
          
            
                </body>
                </html>