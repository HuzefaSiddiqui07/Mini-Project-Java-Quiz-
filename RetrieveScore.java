package com.miniproject.quizapplication.corejava.jdbc.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class RetrieveScore extends Quiz_AbstractClass{
	
	Connection con = null;
	PreparedStatement pStmnt = null;
	
    // Create a Method 
	@Override
	void retrieveScore() throws SQLException {
	
	try {
		
    // Create ConnectionTest Class Object
	ConnectionTest cTest = new ConnectionTest();
	con = cTest.getConnectionDetails();
	
	// Create the prepare statement
	pStmnt = con.prepareStatement("select * from quiz_user");
	
	// Submit the SQL statement to Database
	ResultSet r = pStmnt.executeQuery();
	
	while(r.next()) {
		
		System.out.println("Display the list of userId, name with score into console");
		System.out.println("\n");
		System.out.println("User Id > " + r.getInt(1));
		System.out.println("User Name > " + r.getString(2));
		System.out.println("User Score > " + r.getInt(3));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	}
	
	// Retrieve Particular userId  Score from database
	
	// Create Scanner Object
	Scanner scan = new Scanner(System.in);
	
	System.out.println("Enter User Id");
	int userId = scan.nextInt();
	
	// Create the prepare statement
	pStmnt = con.prepareStatement("select * from quiz_user where user_id = ?");
	pStmnt.setInt(1, userId);
	
	// Submit the SQL statement to Database
	ResultSet rScore = pStmnt.executeQuery();
	
	while(rScore.next()) {
		
		System.out.println("To retrieve the particlar score using userId");
		System.out.println("\n");
		System.out.println("User Name > " + rScore.getString(2));
		System.out.println("User Score > " + rScore.getInt(3));
	}

	r.close();
	
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		
		con.close();
		pStmnt.close();
	}
  }
}
