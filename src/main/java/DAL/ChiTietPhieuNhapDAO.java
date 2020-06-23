/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.ChiTietPhieuNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class ChiTietPhieuNhapDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public ChiTietPhieuNhapDAO(){
    }
    public ArrayList<ChiTietPhieuNhap> list() {
        ArrayList<ChiTietPhieuNhap> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM chitietphieunhap WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()) {
                String maPN = rs.getString("MAPN");
                String maLaptop = rs.getString("MALAPTOP");
                int soluong = rs.getInt("SOLUONG");
                int dongia = rs.getInt("DONGIA");
                int thanhtien = rs.getInt("THANHTIEN");

                ChiTietPhieuNhap ct = new ChiTietPhieuNhap(maPN, maLaptop, soluong, dongia, thanhtien);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }

    public void add(ChiTietPhieuNhap ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chitietphieunhap VALUES (";
               sql += "'"+ct.getMaPN()+"',";
               sql += "'"+ct.getMaLaptop()+"',";
               sql += "'"+ct.getSoluong()+"',";
               sql += "'"+ct.getDongia()+"',";
               sql += "'"+ct.getThanhtien()+"')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chitietphieunhap WHERE MAPN='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
