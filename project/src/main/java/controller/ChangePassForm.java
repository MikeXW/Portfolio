package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class ChangePassForm
 */
public class ChangePassForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String change1 = request.getParameter("forgot1");
		String change2 = request.getParameter("forgot2");
		String change3 = request.getParameter("forgot3");
		String password = request.getParameter("password");
		Connection con = null;
		RequestDispatcher dispatcher = null;
		if(username.isBlank() || password.isBlank() || change1.isBlank() || change2.isBlank() || change3.isBlank()){
			dispatcher = request.getRequestDispatcher("changepass.jsp");
			request.setAttribute("status", "failed");
		}else {
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projdb?autoReconnect=true&useSSL=false&serverTimezone=US/Eastern","root","theoneandonly");
			PreparedStatement ps = con.prepareStatement("SELECT users.forgot1,users.forgot2,users.forgot3 FROM users WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()) {
				dispatcher =request.getRequestDispatcher("login.jsp");
				request.setAttribute("status", "loginfail");
			}else {
				while (rs.next()) {
					String fchange2 = rs.getString("forgot2");
					String fchange1 = rs.getString("forgot1");
					String fchange3 = rs.getString("forgot3");
					if(fchange1.equals(change1) && fchange2.equals(change2) && fchange3.equals(change3)) {
						PreparedStatement ps2=con.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
						ps2.setString(1, HashPassword.hash(password));
						ps2.setString(2, username);
						ps2.executeUpdate();
						dispatcher = request.getRequestDispatcher("changepass.jsp");
						request.setAttribute("status", "success");
					}else {
						dispatcher =request.getRequestDispatcher("changepass.jsp");
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
