package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginForm
 */
public class LoginForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Connection con = null;
		RequestDispatcher dispatcher = null;
		if(username.isBlank() || password.isBlank()){
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("status", "failed");
		}else {
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projdb?autoReconnect=true&useSSL=false&serverTimezone=US/Eastern","root","theoneandonly");
			PreparedStatement ps = con.prepareStatement("SELECT users.password, users.secCheck FROM users WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()) {
				dispatcher =request.getRequestDispatcher("login.jsp");
				request.setAttribute("status", "loginfail");
			}else {
				while (rs.next()) {
					String fpassword = rs.getString("password");
					if(fpassword.equals(HashPassword.hash(password))) {
						HttpSession session = request.getSession();
						session.setAttribute("secCheck", rs.getString("secCheck"));
						
						//CHANGE THIS PART
						//Session management, depending on the access level
						switch(rs.getString("secCheck")) {
							case "1" : 	 dispatcher = request.getRequestDispatcher("index2.jsp"); break;
							case "2" : 	 dispatcher = request.getRequestDispatcher("index.jsp"); break;
							case "3" : 	 dispatcher = request.getRequestDispatcher("index.jsp"); break;
							default : dispatcher = request.getRequestDispatcher("index.jsp");
						}


					}else {
						dispatcher =request.getRequestDispatcher("login.jsp");
						request.setAttribute("status", "loginfail");
					}
				}
			}

			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		dispatcher.forward(request, response);
	}

}
