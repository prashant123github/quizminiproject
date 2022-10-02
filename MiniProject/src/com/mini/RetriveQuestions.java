
// Retrieve the data of Questions and answers with options



package com.mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetriveQuestions {

	public static void main (String [] args ) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			// Load the Driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Establish the connection
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "root");
			
			// Establish the prepare statement 
			 ps = con.prepareStatement("SELECT QuestionNo, Question, Option1, Option2, Option3, Option4, Answer FROM questions ORDER BY RAND() LIMIT 10");
			
			// Execute Query 
			 rs = ps.executeQuery();
			
			while(rs.next()) {
				
				System.out.println(" Question. No:  " +rs.getInt(1));
				System.out.println(" Question " +rs.getString(2));
				System.out.println(" Option   " +rs.getString(3));
				System.out.println(" Option   " +rs.getString(4));
				System.out.println(" Option   " +rs.getString(5));
				System.out.println(" Option   " +rs.getString(6));
				System.out.println();
				


			}
		}catch(Exception e ) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();
			rs.close();
		}
	}
}
