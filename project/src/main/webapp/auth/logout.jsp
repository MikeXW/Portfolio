<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/logout.css"/>
		<link href="https://fonts.googleapis.com/css2?family=Fredoka+One&family=Lato&family=Roboto+Slab:wght@400;500&display=swap" rel="stylesheet">
		<title>Logout</title>
	</head>
	<body>
		<div class="card">
	            <div class="logimage">
	                <img alt="waveGoodbye" src="${pageContext.request.contextPath}/images/goodbye.png">
	            </div>
	                <h1 id="logout"> Logout </h1>
	            <div class="lbutton">
	                <button type="button" id="button">Login</button>
	            </div>
	        </div>
	</body>
</html>
