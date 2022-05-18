<jsp:useBean id="editAcc" class = "com.uttara.project.RegBean" scope = "request">
	<jsp:setProperty name = "editAcc" property = "*" />
</jsp:useBean>
<jsp:forward page="editAccount.do"></jsp:forward>