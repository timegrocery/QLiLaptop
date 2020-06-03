/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DAL.MySQLConnect;
import DAL.NhanVienDAO;
import DTO.NhapHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class NhapHangDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public NhapHangDAO()
    {
        
    }
    public ArrayList<NhapHang> list() {
        ArrayList<NhapHang> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM phieunhap WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maPN = rs.getString("MAPN");
                String maNCC = rs.getString("MANCC");
                String maNV = rs.getString("MANV");
                String ngayNhap = rs.getString("NGAYNHAP");
                int tongTien = rs.getInt("TONGTIEN");
                
                NhapHang ct = new NhapHang(maPN, maNCC, maNV, ngayNhap, tongTien);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }

    public void add(NhapHang ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO phieunhap VALUES (";
               sql += "'"+ct.getMaPN()+"',";
               sql += "'"+ct.getMaNCC()+"',";
               sql += "'"+ct.getMaNV()+"',";
               sql += "'"+ct.getTongTien()+"')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void set(NhapHang nh) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE phieunhap SET ";
            sql += "MANCC='"+nh.getMaNCC()+"', ";
            sql += "MANV='"+nh.getMaNV()+"', ";
            sql += "NGAYNHAP='"+nh.getNgayNhap()+"', ";
            sql += "TONGTIEN='"+nh.getTongTien()+"' ";
            sql += "WHERE MAPN ='"+nh.getMaPN()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM phieunhap WHERE MAPN='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
