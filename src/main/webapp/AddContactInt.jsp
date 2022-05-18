<jsp:useBean id="addContact" class = "com.uttara.project.ContactBean" scope = "request">
	<jsp:setProperty name = "addContact" property = "*" />
</jsp:useBean>
<jsp:forward page="addContact.do"></jsp:forward>