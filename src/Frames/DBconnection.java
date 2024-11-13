/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.Connection;
import java.sql.DriverManager;


//when ever we need to connect to data base we can just simply call this method no need to write this again...
public class DBconnection {
  static Connection con = null;

  public static Connection getConnection(){
    try{
  //Class.forName("com.mysql.jdbc.Driver");
   Class.forName("com.mysql.cj.jdbc.Driver");
   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","43593");
   
   }catch(Exception e){
       e.printStackTrace();
       
   }
    return con;
      
  }}
  /* 
try{
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","43593");    
          }catch(Exception e){
              e.printStackTrace();
          }
*/

