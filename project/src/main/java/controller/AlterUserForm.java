package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class AlterUserForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] securityLevles = new String[]{"1","2","3"};
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String secCheck = request.getParameter("secCheck");
		String email = request.getParameter("email");
		String forgot1 = request.getParameter("forgot1");
		String forgot2 = request.getParameter("forgot2");
		String forgot3 = request.getParameter("forgot3");
		Connection con = null;
		RequestDispatcher dispatcher = null;

		if(username.isEmpty()) {
			dispatcher = request.getRequestDispatcher("alterUser.jsp");
			request.setAttribute("status", "failed");
		}
		else if(!Arrays.asList(securityLevles).contains(secCheck)) {
			dispatcher = request.getRequestDispatcher("alterUser.jsp");
			request.setAttribute("status", "wrong secCheck");
		}
		else {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projdb?autoReconnect=true&useSSL=false&serverTimezone=US/Eastern","root","theoneandonly");
			
			
			PreparedStatement ps1 = con.prepareStatement("SELECT users.username FROM users WHERE username = ?");
			ps1.setString(1, username);
			ResultSet rs = ps1.executeQuery();
			
			if(!rs.isBeforeFirst()) {
				dispatcher = request.getRequestDispatcher("alterUser.jsp");
				request.setAttribute("status", "takenusername");
			}
			else {
			PreparedStatement ps = con.prepareStatement("UPDATE users SET password = ?, secCheck = ?, forgot1 = ?, forgot2 = ?, forgot3 = ? WHERE username = ?" );
			ps.setString(1, HashPassword.hash(password));
			ps.setString(2, secCheck);
			ps.setString(3, forgot1);
			ps.setString(4, forgot2);
			ps.setString(5, forgot3);
			ps.setString(6, username);
			int rowCount = ps.executeUpdate();
			dispatcher = request.getRequestDispatcher("alterUser.jsp");
			if (rowCount > 0) {
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("status", "failed");
			}
		}}catch (Exception e) {
			e.printStackTrace();				
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
		}}}
		dispatcher.forward(request, response);
	}}