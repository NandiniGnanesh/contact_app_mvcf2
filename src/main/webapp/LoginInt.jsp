 <jsp:useBean id="login" class = "com.uttara.project.LoginBean" scope = "request">
 	<jsp:setProperty name = "login" property = "*"/>
 </jsp:useBean>
 <jsp:forward page="login.do"/>