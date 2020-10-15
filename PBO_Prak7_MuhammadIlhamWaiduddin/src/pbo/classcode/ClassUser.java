/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo.classcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ClassUser {
    
    private String username;
    private String password;
    private String level;
    private int ndata = 0;
    private Object[][] data;
    
    private Connection conn;
    private Statement st;
    
    public ClassUser(){
    
        
    }
    
    void TampilUser(){
        try {
            conn = ClassConnection.getKoneksi();        //hubungkan kelas
            st = conn.createStatement();
            String sql = "Select * from user";          //query sql
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){                          //tangkap hasil query
                Object[] o = new Object[3];
                o[0] = rs.getString("username");
                o[1] = rs.getString("password");
                o[2] = rs.getString("level");
                System.out.println(o[0]+"\t"+o[1]+"\t"+o[2]); //tampilkan
            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            Logger.getLogger(ClassUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InsertUser(String username, String password, String level){
        try{
            conn = ClassConnection.getKoneksi();   //menghubungkan kelas
            st = conn.createStatement();
            String sql = "INSERT INTO user Values (?, ?, ?)";  //Query
            try(PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, username);       //argument1
                p.setString(2, password);       //argument2
                p.setString(3, level);          //argument3
                p.execute();
                JOptionPane.showMessageDialog(null, "Sukses menambahkan data...");
            }
        }catch (SQLException ex){
            Logger.getLogger(ClassUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void UpdateUser(String username, String password, String level) {
        try{
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "UPDATE user SET password=?, level=? WHERE username=?";
            try (PreparedStatement p = conn.prepareStatement(sql)){
                p.setString(1, password);
                p.setString(2, level);
                p.setString(3, username);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Sukses Update data...");
            }}
         catch (SQLException ex) {
            Logger.getLogger(ClassUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void DeleteUser(String username){
        try{
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "DELETE FROM user WHERE username=?";
            try (PreparedStatement p = conn.prepareStatement(sql)){
                p.setString(1, username);
                p.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sukses menghapus data...");
            }
        }catch (SQLException ex){
            Logger.getLogger(ClassUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getDataUser(){
        try{
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "select count(*) from user";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "select * from user";
            rs = st.executeQuery(sql);
            data = new Object[ndata][3];
            int idx=0;
            while(rs.next()){
                data[idx][0] = rs.getString("username");
                data[idx][1] = rs.getString("password");
                data[idx][2] = rs.getString("level");
                idx++;
            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            Logger.getLogger(ClassUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getDataUser(String key){
        try{
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "select count(*) from user where username like '%"+key+"%'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "select * from user where username like '%"+key+"%'";
            rs = st.executeQuery(sql);
            data = new Object[ndata][3];
            int idx=0;
            while(rs.next()){
                data[idx][0] = rs.getString("username");
                data[idx][1] = rs.getString("password");
                data[idx][2] = rs.getString("level");
                idx++;
            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            Logger.getLogger(ClassUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getNumberDataUser(){
        return ndata;
    }
    
    public Object[][] getAllDataUser(){
        return data;
    }
}
