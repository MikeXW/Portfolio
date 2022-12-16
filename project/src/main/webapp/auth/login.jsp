<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css"/>
		<title>Login</title>
	</head>
	<body>
		
		    <div class="container">
		    	<div class="userIcon">
	                <img alt="userIcon" src="${pageContext.request.contextPath}/images/user.png">
	            </div>
		      <label for="uname"><b>Username</b></label>
		      <input type="text" placeholder="Enter Username" name="uname" required>
		
		      <label for="psw"><b>Password</b></label>
		      <input type="password" placeholder="Enter Password" name="psw" required>
		        
		      <button type="submit">Login</button>
		      <label>
		        <input type="checkbox" checked="checked" name="remember"> Remember me
		      </label><br>
		      <span class="psw">Forgot <a href="#">password?</a></span>
		    </div>
	</body>
</html>


