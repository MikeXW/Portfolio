<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="css/nav.css">
<link rel ="stylesheet" href="css/home.css">
<link rel="icon" type="images/x-icon" href="images/usb.png">
</head>
<body>
	<!--Navigation bar START-->
		<nav class ="nav">
			<ul>
					<li><a href="login.jsp">Log Out</a></li>
					<li><a href="https://www.google.com/" target="_blank"> Search </a></li>
					<li><a href="contact.jsp"> Contact </a></li>
					<li><a href="about.jsp">About Us</a></li>
					
					<li class="dropdown">
						<a href="javascript:void(0)" class="#cases">Ddos Attacks</a>
						<div class="dropdown-content">
							<a href="tableData.jsp"> Data</a>
						</div>
				<li class="navlogo"><img src="images/usb.png" alt="logo" width="50" height="50" id="logo"></li>
			</ul>
		</nav>
		<!--Navigation bar END-->
			<div class="middle_page">
					<!--Title-->
					<header id="title">
						<h1>Barry University</h1>
						<h2>Department of Mathamatics and Computer Science</h2>
						<h3>Software Engineering</h3>
					</header>
					<br>
				<div class="container">
					<div class="card">
						<iframe id="map" src="https://livethreatmap.radware.com/" width=100% height=700 title="Live Cyber attacks"></iframe>
					</div>			
				</div>
			</div>
</body>
</html>