/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DAL.MySQLConnect;
import DAL.NhanVienDAO;
import DTO.NhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class NhaCungCapDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public NhaCungCapDAO()
    {
        
    }
    public ArrayList<NhaCungCap> list() {
        ArrayList<NhaCungCap> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nhacungcap WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maNCC = rs.getString("MANCC");
                String tenNCC = rs.getString("TENNCC");
                String diaChi = rs.getString("DIACHINCC");
                String dienThoai = rs.getString("DIENTHOAI");
                

                NhaCungCap ct = new NhaCungCap(maNCC, tenNCC, diaChi, dienThoai);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }
    
    public void set(NhaCungCap ncc) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nhacungcap SET ";
            sql += "TENNCC='"+ncc.getTenNCC()+"', ";
            sql += "DIACHINCC='"+ncc.getDiaChi()+"', ";
            sql += "DIENTHOAI='"+ncc.getDienThoai()+"' ";
            sql += " WHERE MANCC='"+ncc.getMaNCC()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }
    
    public void add(NhaCungCap ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO nhacungcap VALUES (";
               sql += "'"+ct.getMaNCC()+"',";
               sql += "'"+ct.getTenNCC()+"',";
               sql += "'"+ct.getDiaChi()+"',";
               sql += "'"+ct.getDienThoai()+"',";
               sql += "'1')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM nhacungcap WHERE MANCC='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
