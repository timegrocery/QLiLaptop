/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class HoaDonDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public HoaDonDAO(){}
    public ArrayList<HoaDon> list(){
        ArrayList<HoaDon> dshd = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hoadon WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
                String maHD = rs.getString("MAHD");
                String maKH = rs.getString("MAKH");
                String maNV = rs.getString("MANV");
                String ngayhd = rs.getTimestamp("NGAYHD").toString();
                String maCtkm = rs.getString("MAKM");
                int tongtien = rs.getInt("TONGTIEN");

                HoaDon ct = new HoaDon(maHD, maKH, maNV, ngayhd, maCtkm, tongtien);
                dshd.add(ct);
            }
            rs.close();
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dshd;
    }
    public void add(HoaDon hd) {
        MySQLConnect mySQL = new MySQLConnect();
        String maKH = hd.getMaKH().equals("")?null:"'"+hd.getMaKH()+"'";
        String maCtkm = (hd.getMaCtkm()==null||hd.getMaCtkm().equals(""))?null:"'"+hd.getMaCtkm()+"'";
        String sql = "INSERT INTO hoadon VALUES (";
               sql += "'"+hd.getMaHD()+"',";
               sql += ""+maKH+",";
               sql += "'"+hd.getMaNV()+"',";
               sql += "'"+hd.getNgaylap().toString()+"',";
               sql += ""+maCtkm+",";
               sql += "'"+hd.getTongtien()+"')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void set(HoaDon hd){
        MySQLConnect mySQL = new MySQLConnect();
        String maKH = hd.getMaKH().equals("")?null:"'"+hd.getMaKH()+"'";
        String sql = "UPDATE hoadon SET ";
            sql += "MAKH="+maKH+", ";
            sql += "MANV='"+hd.getMaNV()+"', ";
            sql += "NGAYHD='"+hd.getNgaylap()+"', ";
            sql += "TONGTIEN='"+hd.getTongtien()+"' ";
            sql += "WHERE MAHD='"+hd.getMaHD()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM hoadon WHERE MAHD='"+MaHD+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
