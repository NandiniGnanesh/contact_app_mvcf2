<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.* , com.uttara.project.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	 <div align="center">
        <table border="1" cellpadding="5">
     <caption><h2>List of contacts to edit</h2></caption>
            <thead>
            <tr>
                 <th scope="col">sl_no</th>
                 <th scope="col">name</th>
                 <th scope="col">email</th>
                 <th scope="col">phoneNum</th>
                 <th scope="col">tags</th>
                 <th scope="col">gender</th>
			     <th scope="col">dob</th>
			     <th scope="col">created_date</th>
			     <th scope="col"></th>
			     <th scope="col"></th>
            </tr>
            </thead>
   				<c:forEach items="${editBeanList}" var="contact">
   			<tbody>
   			
   				<tr>
   				<td scope="row""><c:out value="${contact.sl_no}" /></td>
   				<td><c:out value="${contact.name}" /></td>
   				<td><c:out value="${contact.email}" /></td>
	    		<td><c:out value="${contact.phoneNum}" /></td>
	   			<td><c:out value="${contact.tags}" /></td>
	   			<td><c:out value="${contact.gender}" /></td>
	    		<td><c:out value="${contact.dob}" /></td>
	    		<td><c:out value="${contact.created_date}" /></td>
	    		<td>
            		<form action="updateContact.do" method="post">
                		<input type="hidden" name="sl_no" value="${contact.sl_no}" />
               		 	<input type="submit"  value="edit">
            		</form>
            	</td>
            	<td>
            		<form action="deleteContact.do" method="post">
                		<input type="hidden" name="sl_no" value="${contact.sl_no}" />
               		 	<input type="submit"  value="delete">
            		</form>
        		</td>

   				</tr>
   				
   			</tbody>
   				 </c:forEach>
</table>
	</div>
</body>
</html>