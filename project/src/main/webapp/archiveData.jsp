<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

<%@page import="dataScrapper.*"%>
<%@page import= "dataObject.*"%>
<%@page import= "dbController.*"%>
<%@page import= "java.sql.SQLException"%> 


<%
DataObject data = new DataObject();
ContentDataController controller = new ContentDataController();
try {
	data = controller.getData();
} catch (Exception e) {
	e.printStackTrace();
}

Table sourceTable = data.table(0);
Table targetTable = data.table(1);
%>

<!DOCTYPE html>
<html>
    <head>
   	 <meta charset="UTF-8">
   	 <link rel="stylesheet" href="css/table.css">
   	 <title>Login</title>
    </head>
    
	<style>
		body {
 			 background-image: url('images/background.jpg');
			}
			
		.title{
				color:white;
				text-align:center;
				font-size: 40px;
			}
	</style>
    <body>
    	<h2 class="title"><p>Attacks Data</h2>
	   	 	<div class="container">
	            <h2>Source Attacks Data</h2>
	            <TABLE>
	            	<tr>
	                    <th>Country</th>
	                    <th>Num of Attacks</th>
	                    <th>% of Attacks</th>
	                </tr>
	                <% for(Row row: sourceTable.values) { %>
					    <tr>
					    	<td><%= row.values.get(0) %></td>
		                    <td><%= row.values.get(1) %></td>
		                    <td><%= row.values.get(2) %></td>
	                	</tr>
					<% } %>	     
				</TABLE>   
	   	 	</div>
	   	 	<div class="container">
	            <h2>Target Attacks Data</h2>
	            <TABLE>
	            	<tr>
	                    <th>Country</th>
	                    <th>Num of Attacks</th>
	                    <th>% of Attacks</th>
	                </tr>
	                <% for(Row row: targetTable.values) { %>
					    <tr>
					    	<td><%= row.values.get(0) %></td>
		                    <td><%= row.values.get(1) %></td>
		                    <td><%= row.values.get(2) %></td>
	                	</tr>
					<% } %>	     
				</TABLE>   
	   	 	</div>
    </body>
</html>
