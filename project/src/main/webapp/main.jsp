<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("secCheck")%>">

<script type="text/javascript">
var status = document.getElementById("secCheck").value;
if(status == "yes"){
	alert("It is working");
}
else{
	window.location.replace("http://localhost:8080/project/login.jsp");
}

</script>

<h1>Test Main Page</h1>
<a href="index.jsp">Logout</a>
</body>
</html>