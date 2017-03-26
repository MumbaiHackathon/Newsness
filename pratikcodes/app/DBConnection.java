/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pratik1
 */
public class DBConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/newsness";
    static final String USER = "root";
    static final String PASS = "pratik";
    static Connection conn=null;
    private DBConnection()
    { 
    }
    static Connection getInstance()
    {
        if(conn == null)
        {
             try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connected to database!");
            } catch (Exception ex) {
                System.out.println("Something went wrong in Connecting to DB!"+ex);
        }
        }
        return conn;
    }
}
