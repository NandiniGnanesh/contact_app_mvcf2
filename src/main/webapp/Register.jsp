<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Register</h1>
	
	<form action="RegInt.jsp" method = "post">
	
		Enter name : <input type = "text" name = "name"><br/>
		Enter email : <input type = "text" name = "email"><br/>
		Enter password : <input type = "password" name = "pwd"><br/>
		Repeat your password : <input type = "password" name = "rpwd"><br/>
		<input type = "submit" value = "Register">
	
	</form>
	
	<b>${errorMsg}</b>
</body>
</html>