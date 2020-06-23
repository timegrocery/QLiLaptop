/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DAL.MySQLConnect;
import DAL.NhanVienDAO;
import DTO.ChiTietKhuyenMai;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class ChiTietKhuyenMaiDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public ChiTietKhuyenMaiDAO(){
    }
    public ArrayList<ChiTietKhuyenMai> list() {
        ArrayList<ChiTietKhuyenMai> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM chitietkhuyenmai WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()) {
                String maCtkm = rs.getString("MAKM");
                String maLaptop = rs.getString("MALAPTOP");
                float cachtinh = rs.getFloat("CACHTINH");

                ChiTietKhuyenMai ct = new ChiTietKhuyenMai(maCtkm, maLaptop, cachtinh);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }

    public void add(ChiTietKhuyenMai ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chitietkhuyenmai VALUES (";
               sql += "'"+ct.getMaCtkm()+"',";
               sql += "'"+ct.getMaLaptop()+"',";
               sql += "'"+ct.getCachtinh()+"',";
               sql += "'1')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chitietkhuyenmai WHERE MAKM='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
