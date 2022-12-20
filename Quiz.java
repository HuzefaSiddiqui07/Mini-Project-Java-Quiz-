package com.miniproject.quizapplication.corejava.jdbc.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Quiz implements Quiz_InterfaceClass{
	
	Connection con = null;
	PreparedStatement pStmnt = null;
	
	
	@Override
	public void retrieveQuiz() throws SQLException {
		
		int score = 0;
        int correctAns = 0;
        int inCorrectAns = 0;
        
        int a = 1;
        

  try {
	
	// Create Scanner Object
	 Scanner sc = new Scanner(System.in); 
	    		
	ConnectionTest cTest = new ConnectionTest();
	con = cTest.getConnectionDetails();
	
	// Create the prepare statement
	pStmnt = con.prepareStatement("select * from quiz_questions ORDER BY RAND()");
	
	// Submit the SQL statement to Database
	ResultSet rSet = pStmnt.executeQuery();
	
	System.out.println("Enter Username");
	String userName = sc.next();
	
	System.out.println("\n");
	System.out.println("==================================== Quiz Started ====================================");
	
	while(rSet.next()) {
		
       System.out.println("\n-------------------------------------------------------------------------------------------------------");
		
		System.out.println("Question " + a + " : " + rSet.getString(1));
		System.out.println("1." + rSet.getString(2));
		System.out.println("2." + rSet.getString(3));
		System.out.println("3." + rSet.getString(4));
		System.out.println("4." + rSet.getString(5));
		System.out.println("\n----------------------------------------------------------------------------------------------------------");
		
      System.out.println("Enter Correct Answer");
	    int userAnswer = sc.nextInt();
	    
	   if (userAnswer == rSet.getInt(6)) {
	    	
	    	System.out.println("Correct Answer");
	    	
	    	             score++;
	    	             a++;
	    	           correctAns++;
	            }
	   
	               else {
	            	   
	    	System.out.println("Incorrect Answer!...Correct Answer is >>> " + rSet.getInt(6));
	    	
	    	              a++;     
	    	        inCorrectAns++;
	    	           
	    }
	  
	}
	
	System.out.println("\n-------------------------------------------------------------------------------------------------------------");
	System.out.println("Display the score out of 10 with remark & count correct & incorrect answers");
	System.out.println("\n");
	System.out.println("Score of Candidate >>> " + score + " Out of 10 ");
	System.out.println("Correct Answers > " + correctAns);
	System.out.println("Incorrect Answers > " + inCorrectAns);
	
	// Store UserName & Score into Database
  pStmnt = con.prepareStatement("insert into quiz_user(user_name , user_score)values(?,?)");
   pStmnt.setString(1, userName);
   pStmnt.setInt(2, score);
   
   int r = pStmnt.executeUpdate();
   System.out.println("Record is inserted >>> " + r);
   
   rSet.close();
   
   // Show Grade Bases on User Score 
   
    if (score < 5) {
   	
   	System.out.println("Grade : Class-D > Fail");
   }
   else {
   	
   	if (score == 5) {
   		
   		System.out.println("Grade : Class-C");
   		
   	}
   	else if (score >= 6 && score < 8) {
   		
	    	System.out.println("Grade : Class-B");
	    }
   	else {
   		
   		   System.out.println("Grade : Class-A");
   	  }
  }
    System.out.println("\n*************************************************************************************************************");

       } catch(Exception e) {
	
	         System.out.println(e);
	
       } finally {
	
	            con.close();
	           pStmnt.close();
         }
		
      }
		
   }
	         
	
	
	


