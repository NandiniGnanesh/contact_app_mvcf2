<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h1>AddContact</h1>
	
	<form action="AddContactInt.jsp" method = "post">
	
		Enter name : <input type = "text" name = "name" id="name" value=${name}><br/>
		Enter comma separated email : <input type = "email" name = "email" multiple value=${email}><br/>
		Enter comma separated phoneNumbers  : <input type = "text" name = "phoneNum" multiple value=${phoneNum}><br/>
		Enter comma separated tags : <input type = "text" name = "tags" multiple value=${tags}><br/>
		Enter date of birth : <input type = "date" name = "dob" value=${dob}><br/> 
		<label>Gender : </label>
		male <input name = "gender" type = radio value = "M" ${isMChecked}>
		female <input name = "gender" type = "radio" value = "F" ${isFChecked}><br/>
		
		<input type = "submit" value = "AddContact">
	
	</form>
	
	<b>${errorMsg}</b><br><br/>
	
	<a href = "Menu.jsp">Click here to go back to Menu</a>
	
</body>
</html>
