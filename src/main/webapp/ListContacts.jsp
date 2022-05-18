<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>List contacts</h1>
	<form action="listContact.do">
 		 List contacts by : <select name="list">
 		 
   			 <option value="name">Name</option>
   			 <option value="dob">Date Of Birth</option>
    		 <option value="created_date">Created Date</option>
    		 
 		 </select>
  		<br><br>
 		 <input type="submit" value="Submit">
	</form>
</body>
</html>