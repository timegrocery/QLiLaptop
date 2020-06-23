/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.HoaDon;
import DTO.NhapHang;
import BUS.LaptopBUS;
//import com.sun.javafx.binding.StringFormatter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WindZ
 */
public class ThongKeDAO {
    LaptopBUS spBUS = new LaptopBUS();
    public ThongKeDAO(){
        spBUS.listSP();
    }
    public String StatisticSP( ArrayList<HoaDon> listHd, ArrayList<NhapHang> listNH,String MaSP){
        String s = "";
        int slOut=0,sumOut = 0;
        int slIn=0,sumIn = 0;
        try {
            MySQLConnect mySQL = new MySQLConnect();
            // BÁN
            if(!listHd.isEmpty()){
                String sql1 = "SELECT SUM(SOLUONG) AS SL,SUM(SOLUONG*DONGIA) AS TONGTIEN FROM chitiethoadon WHERE (";
                for(int i = 0 ; i < listHd.size() ; i++){
                    String mahd = listHd.get(i).getMaHD();
                    if(i == (listHd.size() - 1)){
                        sql1 += "MAHD ='"+ mahd +"') ";
                        break;
                    }
                    sql1 += "MAHD ='"+ mahd +"' OR ";
                }
                sql1+= "AND MALAPTOP = '"+MaSP+"' ";
                sql1 += "GROUP BY MAHD";
                System.out.println(sql1);
                ResultSet rs1 = mySQL.executeQuery(sql1);
                while(rs1.next()) {
                    slOut += rs1.getInt("SL");
                    sumOut += rs1.getInt("TONGTIEN");
                }
            }
            
            // NHẬP
            if(!listNH.isEmpty()){
                String sql2 = "SELECT SUM(SOLUONG) AS SL,SUM(TONGTIEN) AS TONGTIEN FROM phieunhap WHERE (";
                for(int i = 0 ; i < listNH.size() ; i++){
                    String idNhap = listNH.get(i).getMaPN();
                    if(i == (listNH.size() - 1)){
                        sql2 += "MAPN = '"+ idNhap +"') ";
                        break;
                    }
                    sql2 += "MAPN = '"+ idNhap +"' OR ";
                }
                sql2+= "AND MALAPTOP = '"+MaSP+"' ";
                sql2 += "GROUP BY MAPN";
                System.out.println(sql2);
                ResultSet rs2 = mySQL.executeQuery(sql2);
                while(rs2.next()){
                    slIn += rs2.getInt("SL");
                    sumIn += rs2.getInt("TONGTIEN");
                }

            }
            
            if(mySQL.isConnect()) mySQL.disConnect();
            
            s += String.format("Số lượng bán : %6d || Số lượng nhập  : %5d\n",slOut,slIn);
            s += String.format("Tổng tiền    : %5dđ || Tổng tiền nhập : %5dđ\n",sumOut,sumIn);
            s += "--------------------------------------------------- \n";
            s += "TỔNG THU NHẬP : "+(sumOut-sumIn)+"VNĐ"+"\n";      
            System.out.print(s);
            
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public String StatisticNV(ArrayList<HoaDon> listHd,String MaNV){
        String s ="";
        int sum = 0;
        String listItem = String.format("|%10s|%10s|\n","Mã SP","Số lượng");
        if(!listHd.isEmpty()){
            MySQLConnect mySQL = new MySQLConnect();
            try {
                // Tổng tiền 
                String sql1 = "SELECT SUM(TONGTIEN) AS TONGTIEN FROM hoadon WHERE (";
                for(int i = 0 ; i < listHd.size() ; i++){
                    String mahd = listHd.get(i).getMaHD();
                    if(i == (listHd.size() - 1)){
                        sql1 += "MAHD ='"+ mahd +"') ";
                        break;
                    }
                    sql1 += "MAHD ='"+ mahd +"' OR ";
                }
                sql1+= "AND MANV = '"+MaNV+"' ";
                sql1 += "GROUP BY MANV";
                System.out.println(sql1);
                ResultSet rs1 = mySQL.executeQuery(sql1);
                while(rs1.next()) {
                    sum += rs1.getInt("TONGTIEN");

                }

                // Mã SP || Số lượng 
                String sql2 = "SELECT MALAPTOP,SUM(chitiethoadon.SOLUONG) AS SOLUONG FROM chitiethoadon WHERE chitiethoadon.MAHD IN (SELECT MAHD FROM hoadon WHERE (";
                for(int i = 0 ; i < listHd.size() ; i++){
                    String mahd = listHd.get(i).getMaHD();
                    if(i == (listHd.size() - 1)){
                        sql2 += "MAHD ='"+ mahd +"') ";
                        break;
                    }
                    sql2 += "MAHD ='"+ mahd +"' OR ";
                }
                sql2+= "AND MANV = '"+MaNV+"' )";
                sql2 += "GROUP BY MALAPTOP";
                System.out.println(sql2);
                ResultSet rs2 = mySQL.executeQuery(sql2);
                while(rs2.next()){
                    listItem += String.format("|%10s|%10s|\n",rs2.getString("MALAPTOP"),rs2.getString("SOLUONG"));

                }

            } catch (SQLException ex) {
                Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        s += listItem;
        s += "--------------------------------------------------- \n";
        s += "TỔNG THU NHẬP : "+sum+"VNĐ"+"\n";      
        return s;
    }
    
    public String StatisticKH(ArrayList<HoaDon> listHd,String MaKH){
        String s ="";
        int sum = 0;
        String listItem = String.format("|%10s|%10s|\n","Mã SP","Số lượng");
        if(!listHd.isEmpty()){
            MySQLConnect mySQL = new MySQLConnect();
            try {
                // Tổng tiền 
                String sql1 = "SELECT SUM(TONGTIEN) AS TONGTIEN FROM hoadon WHERE (";
                for(int i = 0 ; i < listHd.size() ; i++){
                    String mahd = listHd.get(i).getMaHD();
                    if(i == (listHd.size() - 1)){
                        sql1 += "MAHD ='"+ mahd +"') ";
                        break;
                    }
                    sql1 += "MAHD ='"+ mahd +"' OR ";
                }
                sql1+= "AND MAKH = '"+MaKH+"' ";
                sql1 += "GROUP BY MAKH";
                System.out.println(sql1);
                ResultSet rs1 = mySQL.executeQuery(sql1);
                while(rs1.next()){
                    sum += rs1.getInt("TONGTIEN");

                }

                // Mã SP || Số lượng 
                String sql2 = "SELECT MALAPTOP,SUM(chitiethoadon.SOLUONG) AS SOLUONG FROM chitiethoadon WHERE chitiethoadon.MAHD IN (SELECT MAHD FROM hoadon WHERE (";
                for(int i = 0 ; i < listHd.size() ; i++){
                    String mahd = listHd.get(i).getMaHD();
                    if(i == (listHd.size() - 1)){
                        sql2 += "MAHD ='"+ mahd +"') ";
                        break;
                    }
                    sql2 += "MAHD ='"+ mahd +"' OR ";
                }
                sql2+= "AND MAKH = '"+MaKH+"' )";
                sql2 += "GROUP BY MALAPTOP";
                System.out.println(sql2);
                ResultSet rs2 = mySQL.executeQuery(sql2);
                while(rs2.next()){
                    listItem += String.format("|%10s|%10s|\n",rs2.getString("MALAPTOP"),rs2.getString("SOLUONG"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        s += listItem;
        s += "--------------------------------------------------- \n";
        s += "TỔNG TIỀN : "+sum+"VNĐ"+"\n";
        return s;
    }

    public ArrayList<String> StatisticTopSP(ArrayList<HoaDon> listHd) {   
        ArrayList<String> kq = new ArrayList<>();
        if(!listHd.isEmpty()){
            try {
                MySQLConnect mySQL = new MySQLConnect();
                String sql = "SELECT MALAPTOP,SUM(SOLUONG) AS SOLUONG FROM chitiethoadon WHERE ";
                for(int i = 0 ; i < listHd.size() ; i++) {
                    String mahd = listHd.get(i).getMaHD();
                    if(i == (listHd.size() - 1)){
                        sql += "MAHD ='"+ mahd +"' ";
                        break;
                    }
                    sql += "MAHD ='"+ mahd +"' OR ";
                }
                sql += "GROUP BY MALAPTOP ";
                sql += "ORDER BY SUM(SOLUONG) DESC ";
                // only need 10 top-sale products
                sql += "LIMIT 5";
                System.out.println(sql);
                ResultSet rs = mySQL.executeQuery(sql);
                while(rs.next()){
                    String maSP = rs.getString("MALAPTOP");
                    int sl = rs.getInt("SOLUONG");
                    String tenSP = spBUS.getSP(maSP).getTen();
                    String s = String.format("%6s_%5d_%25s",maSP,sl,tenSP);
                    kq.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kq;
    }
    
    public ArrayList<String> StatisticTopNV(ArrayList<HoaDon> listHd)
    {   
        ArrayList<String> kq = new ArrayList<>();
        if(!listHd.isEmpty()){
            try {
                MySQLConnect mySQL = new MySQLConnect();
                String sql = "SELECT nhanvien.MANV, CONCAT(nhanvien.HONV,' ',nhanvien.TENNV) AS HOTEN ,SUM(TONGTIEN) AS TONGTIEN ";
                       sql+= "FROM hoadon INNER JOIN nhanvien ON hoadon.MANV = nhanvien.MANV WHERE ";
                for(int i = 0 ; i < listHd.size() ; i++){
                    String mahd = listHd.get(i).getMaHD();
                    if(i == (listHd.size() - 1))
                    {
                        sql += "MAHD ='"+ mahd +"' ";
                        break;
                    }
                    sql += "MAHD ='"+ mahd +"' OR ";
                }
                sql += "GROUP BY MANV ";
                sql += "ORDER BY SUM(TONGTIEN) DESC ";
                sql += "LIMIT 5";
                System.out.println(sql);
                ResultSet rs = mySQL.executeQuery(sql);
                while(rs.next()){
                    String maNV = rs.getString("MANV");
                    String tenNV = rs.getString("HOTEN");
                    int tt = rs.getInt("TONGTIEN");
                    String s = String.format("%6s_%20s_%5d",maNV,tenNV,tt);
                    kq.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kq;
    }
    
    public ArrayList<String> StatisticTopKH(ArrayList<HoaDon> listHd){   
        ArrayList<String> kq = new ArrayList<>();
        if(!listHd.isEmpty()){
            try {
                MySQLConnect mySQL = new MySQLConnect();
                String sql = "SELECT khachhang.MAKH, CONCAT(khachhang.HOKH,' ',khachhang.TENKH) AS HOTEN ,SUM(TONGTIEN) AS TONGTIEN ";
                       sql+= "FROM hoadon INNER JOIN khachhang ON hoadon.MAKH = khachhang.MAKH WHERE ";
                for(int i = 0 ; i < listHd.size() ; i++){
                    String mahd = listHd.get(i).getMaHD();
                    if(i == (listHd.size() - 1)){
                        sql += "MAHD ='"+ mahd +"' ";
                        break;
                    }
                    sql += "MAHD ='"+ mahd +"' OR ";
                }
                sql += "GROUP BY MAKH ";
                sql += "ORDER BY SUM(TONGTIEN) DESC ";
                sql += "LIMIT 5";
                System.out.println(sql);
                ResultSet rs = mySQL.executeQuery(sql);
                while(rs.next()){
                    String maNV = rs.getString("MAKH");
                    String tenNV = rs.getString("HOTEN");
                    int tt = rs.getInt("TONGTIEN");
                    String s = String.format("%6s_%20s_%5d",maNV,tenNV,tt);
                    kq.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kq;
    }
}
