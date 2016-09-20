package ejd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yglee
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JdbcHelper {
    
    ArrayList<Student> students = new ArrayList<>();
    
    Connection conn ;
    Statement stmt;
    ResultSet rs ;
    int updateCount = 0;
    
       public JdbcHelper() {
        conn = null;
        stmt = null;
        rs = null;
    }
    
    public boolean connect(String url, String user, String pass) {
        
        boolean result = false;
        try {
            this.conn = DriverManager.getConnection(url, user, pass);
            this.stmt = conn.createStatement();
            result = true;            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           // Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);           
        }   
        
        return result;

//        this.conn = DriverManager.getConnection(url, user, pass);
//        this.stmt = conn.createStatement();
//       return conn.isValid(0);
    }
    
    public void diconnect() {
        if(rs != null){
            try {
                rs.close();
                 } catch (Exception ex) {  }
        } 
        if(stmt != null) {
            try{   
                stmt.close();
            }catch (Exception ex) {  }             
            
        }    
         if(conn != null) {
            try{   
                conn.close();
            }catch (Exception ex) {  }             
            
        }    
    }
    
    public ResultSet query(String sql){
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
           // Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return rs;
    }
    
    public int update(String sql) {       
         try {
            updateCount = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
           // Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }        
        return updateCount;
    }
    
    public void printResult ( ResultSet resultSet) {   
         //Student std = new Student(); 
        try {
            while(resultSet.next()) {   
//              std.setId(resultSet.getString(1));
//              std.setFirstName(resultSet.getString(2));
//              std.setLastName(resultSet.getString(3));
              students.add(new Student(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));      
            //  System.out.printf("%10s %10s %s\n", std.getId(), std.firstName, std.lastName);         
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     for(Student stdArray: students) {
         System.out.println(stdArray.getId() + "\t"+ stdArray.getFirstName() + "\t"+ stdArray.getLastName());  // Will invoke overrided `toString()` method
     }
        
        //students.clear();
    }
    
    
}
