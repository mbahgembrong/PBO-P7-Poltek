/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo.classcode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Birthday
 */
public class ClassValidasi {
    
    public String username;
    public String password;
    
    public Connection conn;
    public Statement st;
    
    public ClassValidasi(){
    
    }
    
    public boolean Login(String username, String password){
        boolean valid=false;
        String sql = "SELECT * FROM user WHERE username = '"+ username +"' "
                + "AND password = '"+ password +"'";
        try {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Selamat Datang "+username);
                valid = true;
            }else{
                JOptionPane.showMessageDialog(null, "Username atau Password Salah !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassValidasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }
    
    public String Login2(String username, String password){
        String level=null;
        String sql = "SELECT * FROM user WHERE username = '"+ username +"' "
                + "AND password = '"+ password +"'";
        try  {
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Selamat Datang "+username);
                level = rs.getString("level");//
            }else{
                JOptionPane.showMessageDialog(null, "Username atau Password Salah !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassValidasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return level;
    }
}
