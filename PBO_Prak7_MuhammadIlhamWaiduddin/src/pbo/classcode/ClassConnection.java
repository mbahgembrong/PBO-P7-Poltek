/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo.classcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Birthday
 */
public class ClassConnection {
    
    private static Connection koneksi;
    
    public static Connection getKoneksi(){
        
        if(koneksi == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/pbo_17/9/20";
                String user = "root";
                String pass = "";
                koneksi = (Connection) DriverManager.getConnection(url,user,pass);
                System.out.println("Database Tersambung");
            } catch (ClassNotFoundException ex){
                Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex){
                System.out.println("Database tidak tersambung");
                Logger.getLogger(ClassConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return koneksi;
        
    }
}
