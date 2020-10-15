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
public class ClassBarang {
    
    private String kode;
    private String nama;
    private int stok;
    private int harga;
    private int ndata = 0;
    private Object[][] data;
    
    private Connection conn;
    private Statement st;
    
    public ClassBarang(){
    
    }
    
    void TampilBarang(){
        try {
            conn = ClassConnection.getKoneksi();        //hubungkan kelas
            st = conn.createStatement();
            String sql = "Select * from barang";          //query sql
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){                          //tangkap hasil query
                Object[] o = new Object[4];
                o[0] = rs.getString("kode");
                o[1] = rs.getString("nama");
                o[2] = rs.getString("stok");
                o[3] = rs.getString("harga");
                System.out.println(o[0]+"\t"+o[1]+"\t"+o[2]+"\t"+o[3]); //tampilkan
            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            Logger.getLogger(ClassBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InsertBarang(String kode, String nama, int stok, int harga){
        try{
            conn = ClassConnection.getKoneksi();   //menghubungkan kelas
            st = conn.createStatement();
            String sql = "INSERT INTO barang Values (?, ?, ?, ?)";  //Query
            try(PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, kode);       //argument1
                p.setString(2, nama);       //argument2
                p.setInt(3, stok);          //argument3
                p.setInt(4, harga);         //argument4
                p.execute();
                JOptionPane.showMessageDialog(null, "Sukses menambahkan data...");
            }
        }catch (SQLException ex){
            Logger.getLogger(ClassBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void UpdateBarang(String kode, String nama, int stok, int harga) {
        try{
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "UPDATE barang SET nama=?, stok=?, harga=? WHERE kode=?";
            try (PreparedStatement p = conn.prepareStatement(sql)){
                p.setString(4, kode);       
                p.setString(1, nama);       
                p.setInt(2, stok);          
                p.setInt(3, harga);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Sukses Update Data...");
            }}
         catch (SQLException ex) {
            Logger.getLogger(ClassBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void DeleteBarang(String kode){
        try{
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "DELETE FROM barang WHERE kode=?";
            try (PreparedStatement p = conn.prepareStatement(sql)){
                p.setString(1, kode);
                p.executeUpdate();
                System.out.println("Sukses menghapus data...");
            }
        }catch (SQLException ex){
            Logger.getLogger(ClassBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getDataBarang(){
        try{
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "select count(*) from barang";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "select * from barang order by kode asc";
            rs = st.executeQuery(sql);
            data = new Object[ndata][4];
            int idx=0;
            while(rs.next()){
                data[idx][0] = rs.getString("kode");
                data[idx][1] = rs.getString("nama");
                data[idx][2] = rs.getInt("stok");
                data[idx][3] = rs.getInt("harga");
                idx++;
            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            Logger.getLogger(ClassBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getDataBarang(String key){
        try{
            conn = ClassConnection.getKoneksi();
            st = conn.createStatement();
            String sql = "select count(*) from barang where kode like '%"+key+"%'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ndata = rs.getInt("COUNT(*)");
            }
            sql = "select * from barang where kode like '%"+key+"%'";
            rs = st.executeQuery(sql);
            data = new Object[ndata][4];
            int idx=0;
            while(rs.next()){
                data[idx][0] = rs.getString("kode");
                data[idx][1] = rs.getString("nama");
                data[idx][2] = rs.getInt("stok");
                data[idx][3] = rs.getInt("harga");
                idx++;
            }
            rs.close();
            st.close();
        }catch (SQLException ex){
            Logger.getLogger(ClassBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getNumberDataBarang(){
        return ndata;
    }
    
    public Object[][] getAllDataBarang(){
        return data;
    }
}
