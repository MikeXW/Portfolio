<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
   	 <meta charset="UTF-8">
   	 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/registration.css"/>
   	 <title>Registration</title>
    </head>
	<style>
		body {
 			 background-image: url('images/background.jpg');
			}
	</style>
    <body>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    
   	 	<div class="container">
			<form class="registerForm" action="register" method="post">
			<div class="usernameInput">
				<label for="uname"><b>Username</b></label><br>
				<input class="usernameInput" type="text" placeholder="Enter Username" name="username" required><br>
			</div>	 
			
			<div class="passwordInput">
				<label for="psw"><b>Password</b></label><br>
				<input class="passwordInput" type="password" placeholder="Enter Password" name="password" required><br>
			</div>

            <div class="emailInput">
				<label for="email"><b>Email</b></label><br>
				<input class="emailInput" type="text" placeholder="Enter Email" name="email" required><br>
			</div>
			<br>
            <div class="secInput">
				<label for="questions"><b>Security Questions</b></label><br><br>
                What is your favorite color?<br>
				<input class="forget1" type="password" placeholder="Enter Answer" name="forgot1" required><br>
                What is your pets first name?<br>
			    <input class="forget2" type="password" placeholder="Enter Answer" name="forgot2" required><br>
                What city is the city you were born in?<br>
                <input class="forget3" type="password" placeholder="Enter Answer" name="forgot3" required><br>
            </div>	

            	<button type="submit">Register</button><br>
            	<span class="psw"><a href="login.jsp"> Login</span>
			
			</form>
   	 	</div>
   	 	
<script type="text/javascript">
var status = document.getElementById("status").value;
if(status == "success"){
	alert("User Created!");
}
if(status=="failed"){
	alert("Please Input All Fields!");
}
if(status=="takenemail"){
	alert("Email taken!");
}
if(status=="takenusername"){
	alert("Username Taken!");
}
</script>
    </body>
</html>