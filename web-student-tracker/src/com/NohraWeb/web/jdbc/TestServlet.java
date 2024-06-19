package com.NohraWeb.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// Define datasource/connection pool for resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Step 1: Set up the PrintWriter'
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		// Step 2: Get a connection to the database
		Connection myConn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			
			//Step 3: Create a SQL Statement
			String sql = "select * from student";
			mySt = myConn.createStatement();
			
			// Step 4: Execute SQL Query
			myRs = mySt.executeQuery(sql);
			
			// Step 5: Process the resultSet
			while(myRs.next()) {
				String email = myRs.getString("email");
				out.println(email);
			}
		} 
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

}
