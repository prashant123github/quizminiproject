


		package com.mini;

		import java.sql.Connection;
	
		import java.sql.DriverManager;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.util.Scanner;

		public class RetriveQueAndAns {
	
		public Connection driverConnection() {
		Connection con = null;
		
		try { 
			// load the Driver class
						Class.forName("com.mysql.jdbc.Driver");
						
						// Establish the connection
					 con =	DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "root");
						
			
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return con;
		}

		private void getGrade(int x) {
		if(x>=8 && x<=10) {
			System.out.println("Grade A");
		}else if(x>=6 && x<8){
			System.out.println("Grade B");
		}else if( x==5) {
			System.out.println("Grade C");
		}else  {
			System.out.println("Fail");

			
		}
		}
	
	
		private void getStudentData(Connection con) throws Exception {
		Scanner sc2 = new Scanner(System.in);
		System.out.println(" Enter the number of student: ");
		int num = sc2.nextInt();
		for(int a = 1; a<=num; a++) {
			int count = 0;
		try {
			
		PreparedStatement ps1 = con.prepareStatement(" SELECT   Question,Option1,Option2,Option3,Option4,Answer FROM questions ORDER BY RAND() LIMIT 10");
		 ResultSet rs1 = ps1.executeQuery();
		PreparedStatement ps5 = con.prepareStatement("select QuestionNo from questions");
		 ResultSet rs5 = ps5.executeQuery();
		 
		 Scanner sc = new Scanner(System.in);
			System.out.println(" Enter Student id of student serial number: ");
			int id = sc.nextInt();
			System.out.println(" Enter Student Name: ");
			String studentName = sc2.next();
			while(rs1.next() ) {
				if(rs5.next())
				System.out.println("QuestionNo: "  +rs5.getInt(1));
				System.out.println(" Question " +rs1.getString(1));
				System.out.println(" " +rs1.getString(2));
				System.out.println(" " +rs1.getString(3));
				System.out.println(" "+rs1.getString(4));
				System.out.println(" "+rs1.getString(5));

				System.out.println( " ");
				
				System.out.println(" Enter the  option(in UPPERCASE)");
				Scanner scanner = new Scanner(System.in);
				String option = scanner.next();
				String ans =rs1.getString(6);
				if(option.equals(ans) ){
					  System.out.println("Correct Answer");
						 count++;	

					  System.out.println( " ");
					
				}else {
					System.out.println(" Wrong Answer");
				}
				}
				System.out.println(count);
				int x = count;

				storeStudentData(con, id, studentName,  x);
			
				}catch (Exception e) {
			}
			}
			}
				public void storeStudentData(Connection con, int id, String fullname, int x)throws Exception {
				
				PreparedStatement ps2  = con.prepareStatement("insert into studentdata (StudentID, studentName, score) values( ?,?,?);");

			
				ps2.setInt(1,id);
				ps2.setString(2, fullname);
				ps2.setInt(3,x);
				 ps2.executeUpdate();
				System.out.println(" Students record successfully inserted " );	
			
		}
			
				public void displayStortedStudentData(Connection con) throws Exception {
				PreparedStatement ps3 = con.prepareStatement("select * from studentdata order by score desc");
				ResultSet rs3 =	ps3.executeQuery();
				while(rs3.next()) {

				System.out.println(" Student ID:" +rs3.getInt(1));
	            System.out.println(" Student Name :" +rs3.getString(2));
	            System.out.println(" Score is :" +rs3.getInt(3));
	            System.out.println(" ");
			}
			}

				private void displayFinalResult(Connection con) throws Exception {
		
				Scanner sc2 = new Scanner(System.in);
				System.out.println(" Enter student id: ");
				int id = sc2.nextInt();
				PreparedStatement ps4 = con.prepareStatement("select * from studentdata where studentID= ?");
				ps4.setInt(1, id);
				ResultSet rs4 = ps4.executeQuery();
				while ( rs4.next()) {
				System.out.println(" Student id is: " +rs4.getInt(1));
				System.out.println(" Student name is: " +rs4.getString(2));
				System.out.println(" Student score  is: " +rs4.getInt(3));
				System.out.println(" ");
		}
			}

				public static void main (String [] args ) throws Exception {
	
				RetriveQueAndAns retriveQueAndAns = new RetriveQueAndAns();
				Connection con = 	retriveQueAndAns.driverConnection();
				retriveQueAndAns.getStudentData(con);
				retriveQueAndAns.displayStortedStudentData(con);
				retriveQueAndAns.displayFinalResult(con);
		}
		
	}


