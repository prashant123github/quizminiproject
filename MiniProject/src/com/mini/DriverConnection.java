
// This class is only for create the connection

package com.mini;

import java.sql.Connection;
import java.sql.DriverManager;

public class DriverConnection {
	Connection con = null;
	public Connection getConnection() {
	
		try {
			// load the Driver class
			Class.forName("com.mysql.jdbc.Driver");
			
			// Establish the connection
		 con =	DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "root");
			
		}catch(Exception e) {
		}
		return con;
	}
}


