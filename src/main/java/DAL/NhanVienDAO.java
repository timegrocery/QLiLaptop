/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class NhanVienDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public NhanVienDAO() {
    }
    public ArrayList<NhanVien> list()
    {
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nhanvien WHERE enable = 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()) {
                String maNV = rs.getString("MANV");
                String hoNV = rs.getString("HONV");
                String tenNV = rs.getString("TENNV");
                String phai = rs.getString("PHAI");
                String diaChi = rs.getString("DIACHI");
                int namsinh = rs.getInt("NAMSINH"); 
                String IMG = rs.getString("IMG");

                NhanVien nv = new NhanVien(maNV, hoNV, tenNV, phai, diaChi, namsinh, IMG);
                dsnv.add(nv);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnv;
    }

    public void set(NhanVien nv) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nhanvien SET ";
            sql += "HONV='"+nv.getHoNV()+"', ";
            sql += "TENNV='"+nv.getTenNV()+"', ";
            sql += "PHAI='"+nv.getPhai()+"', ";
            sql += "DIACHI='"+nv.getDiaChi()+"', ";
            sql += "NAMSINH='"+nv.getNamSinh()+"', "; 
            sql += "IMG='"+nv.getImg()+"' ";
            sql += " WHERE MANV='"+nv.getMaNV()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(NhanVien nv) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO nhanvien VALUES (";
                sql += "'"+nv.getMaNV()+"',";
                sql += "'"+nv.getHoNV()+"',";
                sql += "'"+nv.getTenNV()+"',";
                sql += "'"+nv.getPhai()+"',";
                sql += "'"+nv.getDiaChi()+"',";
                sql += "'"+nv.getNamSinh()+"',";  
                sql += "'"+nv.getImg()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaNV) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE nhanvien SET enable = 0 WHERE MANV='"+MaNV+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
