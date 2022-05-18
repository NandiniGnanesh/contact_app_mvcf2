<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<style>

	.container{
		float:right;
	}
	
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align:center">Menu</h1>
	
	<a href = "openEditAccountView.do">EditAccount</a>
	
	<a class = "container" href = "logout.do">Logout</a><br><br/>
	 
	<p style="text-align:center">
	
		<a href = "openAddContactView.do">Click here to Add Contacts</a><br/>
		<a href = "openSearchContactView.do">Click here to Search Contacts</a><br/>
		<a href = "openListContactsView.do">Click here to list Contacts</a><br/>
		<a href = "openEditContactView.do">Click here to Edit Contacts</a><br/>
		<a href = "openBirthdayRemainderView.do">Click here for Birthday Remainders</a><br/>
		
	</p>
</body>
</html>