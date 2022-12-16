<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css"/>
</head>
<style>
	body {
			background-image: url('images/background.jpg');
		}
</style>
    <body>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
   	 	<div class="container">
   	 	<form class="loginForm" action="login" method="post">
            <img alt="userIcon" src="images/user.png">

			<div class="usernameInput">
				<label for="uname"><b>Username</b></label>
				<input class="usernameInput" type="text" placeholder="Enter Username" name="username" required><br>
			</div>	 
			
			<div class="passwordInput">
				<label for="psw"><b>Password</b></label>
				<input class="passwordInput" type="password" placeholder="Enter Password" name="password" required><br>
			</div>
						
			<button type="submit">Login</button><br>
			<label>
				<input type="checkbox" checked="checked" name="remember"> Remember me
			</label><br><br>
			
			<span class="psw"><a href="changepass.jsp"> Reset password?</a></span> &nbsp;
			<span class="registration"><a href="register.jsp">Create account?</a></span>
			</form>
   	 	</div>
   	 	
<script type="text/javascript">
var status = document.getElementById("status").value;
if(status == "loginfail"){
	alert("Wrong Username or Password!");
}
if(status == "failed"){
	alert("Please Input Both Fields");
}

</script>
    </body>
</html>