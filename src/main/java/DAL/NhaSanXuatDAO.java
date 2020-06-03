/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NhaSanXuat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class NhaSanXuatDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public NhaSanXuatDAO()
    {
        
    }
    public ArrayList<NhaSanXuat> list()
    {
        ArrayList<NhaSanXuat> dsnv = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nhasanxuat WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maNsx = rs.getString("MANSX");
                String tenNsx = rs.getString("TENNSX");

                NhaSanXuat nv = new NhaSanXuat(maNsx, tenNsx);
                dsnv.add(nv);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnv;
    }

    public void set(NhaSanXuat nv) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nhasanxuat SET ";
            sql += "MANSX='"+nv.getTenNSX()+"', ";
            sql += " WHERE MANSX='"+nv.getMaNSX()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(NhaSanXuat nv) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO nhasanxuat VALUES (";
               sql += "'"+nv.getMaNSX()+"',";
               sql += "'"+nv.getTenNSX()+"')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaNV)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM nhasanxuat WHERE MANSX='"+MaNV+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
