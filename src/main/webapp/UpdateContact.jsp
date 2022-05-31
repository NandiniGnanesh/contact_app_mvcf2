<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Update Contact</h1>
	
	<form action="UpdateContactInt.jsp" method = "post">
	
		<input type="hidden" name="sl_no" value=${contactBean.sl_no} />
		Enter name : <input type = "text" name = "name" id="name" value=${contactBean.name}><br/>
		Enter comma separated email : <input type = "email" name = "email" multiple value=${contactBean.email}><br/>
		Enter comma separated phoneNumbers  : <input type = "text" name = "phoneNum" multiple value=${contactBean.phoneNum}><br/>
		Enter comma separated tags : <input type = "text" name = "tags" multiple value=${contactBean.tags}><br/>
		Enter date of birth : <input type = "date" name = "dob" value=${contactBean.dob}><br/> 
		<label>Gender : </label>
		male <input name = "gender" type = radio value = "M" ${isMChecked}>
		female <input name = "gender" type = "radio" value = "F" ${isFChecked}><br/>
		
		<input type = "submit" value = "UpdateContact">
	
	</form>
	<b>${errorMsg}</b>
</body>
</html>