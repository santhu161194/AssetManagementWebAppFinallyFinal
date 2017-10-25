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
    


    <div id="content">
        <h3 align="center">${viewdetails}</h3>
        </div>
    <h4><j:out value="${updatestatus}"></j:out></h4>
    <table border="2">
        <tr>
            <th> assetId
            <th>serialNumber
            <th>assetName
            <th>assetType
            <th>cost
            <th> status
            <!-- <th>createdDate
            <th> createdBy
            <th>modifiedBy -->
            
        </tr>
        
        <j:forEach var="asss" items="${assets}">
            <tr>
                <td><j:out value="${asss.assetId} "></j:out></td>
                <td><j:out value="${asss.serialNumber} "></j:out></td>
                <td><j:out value="${asss.assetName} "></j:out></td>
                <td><j:out value="${asss.assetType} "></j:out></td>
                <td><j:out value="${asss.cost} "></j:out></td>
                <td><j:out value="${asss.status} "></j:out></td>
                <%-- <td><j:out value="${asss.createdDate} "></j:out></td>
                <td><j:out value="${asss.createdBy} "></j:out></td>
                <td><j:out value="${asss.modifiedBy} "></j:out></td>
                <j:if test="${viewdetails eq 'Available Assets'}">
                <td><a class="showhide" id="allocateAsset?assetID=<j:out value="${asss.assetId}"></j:out>">Allocate Asset</a></td>
                </j:if>
                <j:if test="${viewdetails eq 'Allocated Assets'}">
                <td><a class="showhide" id="deallocateAsset?assetID=<j:out value="${asss.assetId}"></j:out>?deassignedBy=<j:out value="${username}"></j:out>">DeAllocate Asset</a></td>
                </j:if>
                <td><a href="UpdateAsset?code=<j:out value="${asss.assetId}"></j:out>">Update</a></td>
             --%>    </tr>
                </j:forEach>
                </table>
                
            
            
                </body>
                </html>