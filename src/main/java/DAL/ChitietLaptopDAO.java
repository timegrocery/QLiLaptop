/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DAL.MySQLConnect;
import DAL.NhanVienDAO;
import DTO.ChitietLaptop;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class ChitietLaptopDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public ChitietLaptopDAO()
    {
        
    }
    public ArrayList<ChitietLaptop> list() {
        ArrayList<ChitietLaptop> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM chitietlaptop WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()) {
                String maChitiet = rs.getString("MACT");
                String cpu = rs.getString("CPU");
                String ram = rs.getString("RAM");
                String gpu = rs.getString("GPU");
                String manhinh = rs.getString("MANHINH");
                String ocung = rs.getString("OCUNG");

                ChitietLaptop ct = new ChitietLaptop(maChitiet, cpu, ram, gpu, manhinh, ocung);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }

    public void add(ChitietLaptop ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chitietlaptop VALUES (";
               sql += "'"+ct.getMaChitiet()+"',";
               sql += "'"+ct.getCpu()+"',";
               sql += "'"+ct.getRam()+"',";
               sql += "'"+ct.getGpu()+"',";
               sql += "'"+ct.getManhinh()+"',";
               sql += "'"+ct.getOcung()+"',";
               sql += "'1')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chitietlaptop WHERE MACT='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
