<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>EditAccount</h1>
	
	
	<form action="EditAccountInt.jsp" method = "post">
	
		Enter name to edit: <input type = "text" name = "name"><br/>
		Enter email that you already have : <input type = "text" name = "email"><br/>
		Enter password to edit : <input type = "password" name = "pwd"><br/>
		Repeat your edited password : <input type = "password" name = "rpwd"><br/>
		<input type = "submit" value = "Submit">
	
	</form>
	<b>${errorMsg}</b>
</body>
</html>