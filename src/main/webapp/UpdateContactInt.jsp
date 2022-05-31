<jsp:useBean id="updateContact" class = "com.uttara.project.ContactBean" scope = "request">
	<jsp:setProperty name = "updateContact" property = "*" />
</jsp:useBean>
<jsp:forward page="updateContact.do"></jsp:forward>