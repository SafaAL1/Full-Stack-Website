/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.borrowbike.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import ryerson.ca.borrowbike.helper.BikeBorrow;

/**
 *
 * @author student
 */
public class BIKE_Hold_CRUD {
    public static Connection getCon() throws ClassNotFoundException, SQLException{
       Connection con=null;
     try{
         Class.forName("com.mysql.jdbc.Driver");
        String connection=System.getenv("DB_URL");
        //String connection ="localhost:3306";
         con=DriverManager.getConnection("jdbc:mysql://"+connection+"/borrow_LBS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
        
         
         System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
     
    }
    
    public static boolean isOnHold(String isbn){
       boolean result;
        try{
            Connection con= getCon();
            
        
            
            String q = "select * from Bike_Hold "
                    + " WHERE isbn LIKE '"+isbn+"'"+";";

			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){

			result=true;
                                
                                }
                        else
                            result=false;
			
			con.close();

		}catch(Exception e){return false;}
            return result;
    }
    
    
    public static Set<BikeBorrow>  getHolds(){
        Set<BikeBorrow> bikes= new HashSet<BikeBorrow>();
        

        try{
            Connection con= getCon();
            
        
            
            String q = "select * from Bike_Hold "
                    +";";
                        System.out.println(q);
			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){

				String isbn=rs.getString("isbn");
			
                                 
                                
                                BikeBorrow bike = new BikeBorrow(isbn,null, null);
                                bikes.add(bike);
                                
                                }
			
			
			con.close();

		}catch(Exception e){return bikes;}
            return bikes;
    }
    
    public static void addHold(String isbn) throws ClassNotFoundException, SQLException{
      
        
            Connection con= getCon();
          
            String q = "insert into Bike_Hold "
                    + "(isbn) values "
                    + "('"+isbn+"');";
            Statement stmt = con.createStatement(); 
           
            stmt.execute(q);
			con.close();
                        

		 
 
        
    }
    
    public static void addHold(String isbn, String username, String date) throws ClassNotFoundException, SQLException{
      
        
            Connection con= getCon();
          
            String q = "insert into Bike_Hold "
                    + "(isbn, username, date) values "
                    + "("+
                    "'" +isbn+"'"+ ","
                    +"'"+username+"'" + ","
                    +"'"+date+"'"
                    +"');";
            Statement stmt = con.createStatement(); 
           
            stmt.execute(q);
			con.close();
                        

		 
 
        
    }
    
}
