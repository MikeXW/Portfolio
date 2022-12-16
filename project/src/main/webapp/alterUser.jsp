<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <meta charset="UTF-8">
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/user.css">
     <title>Reset</title>
    </head>
    <style>
        body {
             background-image: url('images/background.jpg');
            }
    </style>
    <body>
    
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <form class="alterUserForm" action="alterUser" method="post">
        <div class="container">
            <div class="usernameInput">
                <label for="uname"><b>Username</b></label><br>
                <input class="usernameInput" type="text" placeholder="Enter Username" name="username" required><br>
            </div>  
            <div class="passwordInput">
                <label for="psw"><b>New Password</b></label><br>
                <input class="passwordInput" type="password" placeholder="Enter Password" name="password" required><br>
            </div>
            <div class="usernameInput">
                <label for="user"><b>Security Level</b></label><br>
                <input class="usernameInput" type="text" placeholder="Enter User Tier" name="secCheck" required><br>
            </div>
            <div class="secInput">
                <label for="questions"><b>Security Questions</b></label><br><br>
                What is your favorite color?<br>
                <input class="forget1" type="password" placeholder="Enter Answer" name="forgot1" required><br>
                What is your pets first name?<br>
                <input class="forget2" type="password" placeholder="Enter Answer" name="forgot2" required><br>
                What city is the city you were born in?<br>
                <input class="forget3" type="password" placeholder="Enter Answer" name="forgot3" required><br>
            </div>
            <button type="submit">Reset</button><br>
            <span class="psw"><a href="index2.jsp"> Go back to home</a></span> &nbsp;
            </form>
        </div>
        <script type="text/javascript">
var status = document.getElementById("status").value;
if(status == "success"){
	alert("User Updated");
}
if(status=="failed"){
	alert("Please Input All Fields!");
}
if(status=="takenemail"){
	alert("Email taken!");
}
if(status=="takenusername"){
	alert("No user with that username!");
}
if(status=="wrong secCheck"){
	alert("You must use only 1, 2 or 3 as security level");
}
</script>
    </body>
</html>