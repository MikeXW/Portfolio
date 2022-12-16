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
public class RegisterForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String forgot1 = request.getParameter("forgot1");
		String forgot2 = request.getParameter("forgot2");
		String forgot3 = request.getParameter("forgot3");
		Connection con = null;
		RequestDispatcher dispatcher = null;
		if(username.isEmpty() || password.isBlank() || email.isBlank() || forgot1.isBlank() || forgot2.isBlank() || forgot3.isBlank()) {
			dispatcher = request.getRequestDispatcher("register.jsp");
			request.setAttribute("status", "failed");
		}else {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projdb?autoReconnect=true&useSSL=false&serverTimezone=US/Eastern","root","theoneandonly");
			
			PreparedStatement ps3 = con.prepareStatement("SELECT * FROM users");
			ResultSet rs3 = ps3.executeQuery();
			if(!rs3.isBeforeFirst()) {
				PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, password, secCheck, email, forgot1, forgot2, forgot3) values(?,?,?,?,?,?,?)" );
				ps.setString(1, username);
				ps.setString(2, HashPassword.hash(password));
				ps.setString(3, "1");
				ps.setString(4, email);
				ps.setString(5, forgot1);
				ps.setString(6, forgot2);
				ps.setString(7, forgot3);
				ps.executeUpdate();
				dispatcher = request.getRequestDispatcher("login.jsp");
			}else {
			
			PreparedStatement ps1 = con.prepareStatement("SELECT users.username FROM users WHERE username = ?");
			ps1.setString(1, username);
			ResultSet rs = ps1.executeQuery();
			PreparedStatement ps2 = con.prepareStatement("SELECT users.email FROM users WHERE email = ?");
			ps2.setString(1, email);
			ResultSet rs2 = ps2.executeQuery();
			if(rs.isBeforeFirst()) {
				dispatcher = request.getRequestDispatcher("register.jsp");
				request.setAttribute("status", "takenusername");
			}else if(rs2.isBeforeFirst()){
				dispatcher = request.getRequestDispatcher("register.jsp");
				request.setAttribute("status", "takenemail");
			}
			else {
			PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, password, secCheck, email, forgot1, forgot2, forgot3) values(?,?,?,?,?,?,?)" );
			ps.setString(1, username);
			ps.setString(2, HashPassword.hash(password));
			ps.setString(3, "3");
			ps.setString(4, email);
			ps.setString(5, forgot1);
			ps.setString(6, forgot2);
			ps.setString(7, forgot3);
			int rowCount = ps.executeUpdate();
			dispatcher = request.getRequestDispatcher("login.jsp");
			if (rowCount > 0) {
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("status", "failed");
			}}
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