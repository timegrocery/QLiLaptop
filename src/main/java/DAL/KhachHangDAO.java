/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DAL.MySQLConnect;
import DAL.NhanVienDAO;
import DTO.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class KhachHangDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public KhachHangDAO()
    {
        
    }
    public ArrayList<KhachHang> list() {
        ArrayList<KhachHang> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM khachhang WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maKH = rs.getString("MAKH");
                String hoKH = rs.getString("HOKH");
                String tenKH = rs.getString("TENKH");
                String sdt = rs.getString("SDT");

                KhachHang ct = new KhachHang(maKH, hoKH, tenKH, sdt);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }

    public void set(KhachHang kh) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE khachhang SET ";
            sql += "HOKH='"+kh.getHoKH()+"', ";
            sql += "TENKH='"+kh.getTenKH()+"', ";
            sql += "SDT='"+kh.getSdt()+"' ";
            sql += " WHERE MAKH='"+kh.getMaKH()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }
    
    public void add(KhachHang ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO khachhang VALUES (";
               sql += "'"+ct.getMaKH()+"',";
               sql += "'"+ct.getHoKH()+"',";
               sql += "'"+ct.getTenKH()+"',";
               sql += "'"+ct.getSdt()+"',";
               sql += "'1')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM khachhang WHERE MAKH='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
