/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.ChiTietHoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */

public class ChiTietHoaDonDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public ChiTietHoaDonDAO(){
    }
    public ArrayList<ChiTietHoaDon> list() {
        ArrayList<ChiTietHoaDon> dsct = new ArrayList<>();
        try {
            String sql = "SELECT * FROM chitiethoadon WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
                String maHD = rs.getString("MAHD");
                String maSP = rs.getString("MALAPTOP");
                int soluong = rs.getInt("SOLUONG");
                int gia = rs.getInt("DONGIA");

                ChiTietHoaDon ct = new ChiTietHoaDon(maHD, maSP, soluong, gia);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsct;
    }

    public void add(ChiTietHoaDon ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chitiethoadon VALUES (";
               sql += "'"+ct.getMaHD()+"',";
               sql += "'"+ct.getMaLaptop()+"',";
               sql += "'"+ct.getSl()+"',";
               sql += "'"+ct.getGia()+"')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chitiethoadon WHERE MAHD='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
