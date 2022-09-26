package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root",
					"Amir@1999");
			String n = request.getParameter("txtName");
			String p = request.getParameter("txtPwd");
			PreparedStatement preparedStatement = connection
					.prepareStatement("select uname from login where uname=? and password=?");
			preparedStatement.setString(1, n);
			preparedStatement.setString(2, p);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome to ChitChat");
				dispatcher.forward(request, response);

			} else {
				out.println("<font color=red sixe = 18>Login Failed!!<br>");
				out.println("<a href=login.jsp>Try Again!!</a>");
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

}
