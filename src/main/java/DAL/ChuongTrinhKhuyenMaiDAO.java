/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DAL.MySQLConnect;
import DAL.NhanVienDAO;
import DTO.ChuongTrinhKhuyenMai;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class ChuongTrinhKhuyenMaiDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public ChuongTrinhKhuyenMaiDAO(){
        
    }
    public ArrayList<ChuongTrinhKhuyenMai> list() {
        ArrayList<ChuongTrinhKhuyenMai> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM chuongtrinhkhuyenmai WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()) {
                String maCtkm = rs.getString("MAKM");
                String tenCtkm = rs.getString("TENKM");
                String ngayBatdau = rs.getString("NGAYBATDAU");
                String ngayKetthuc = rs.getString("NGAYKETTHUC");

                ChuongTrinhKhuyenMai ct = new ChuongTrinhKhuyenMai(maCtkm, tenCtkm, ngayBatdau, ngayKetthuc);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }

    public void add(ChuongTrinhKhuyenMai ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chuongtrinhkhuyenmai VALUES (";
               sql += "'"+ct.getMaCtkm()+"',";
               sql += "'"+ct.getTenCtkm()+"',";
               sql += "'"+ct.getNgayBatdau()+"',";
               sql += "'"+ct.getNgayKetthuc()+"',";
               sql += "'1')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chuongtrinhkhuyenmai WHERE MAKM='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
