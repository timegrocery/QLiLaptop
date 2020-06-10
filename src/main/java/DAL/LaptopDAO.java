/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.Laptop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author WindZ
 */
public class LaptopDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public LaptopDAO()
    {
        
    }
    public ArrayList<Laptop> list() {
        ArrayList<Laptop> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM laptop WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maLaptop = rs.getString("MALAPTOP");
                String maNhaSanXuat = rs.getString("MANSX");
                String maNhaCungCap = rs.getString("MANCC");
                String ten = rs.getString("TEN");
                int soluong = rs.getInt("SOLUONG");
                int gia = rs.getInt("GIA");
                String CPU = rs.getString("CPU");
                String RAM = rs.getString("RAM");
                String GPU = rs.getString("GPU");
                String manhinh = rs.getString("MANHINH");
                String ocung = rs.getString("OCUNG");
                
                String img = rs.getString("IMG");

                Laptop ct = new Laptop(maLaptop, maNhaSanXuat, maNhaCungCap, ten, soluong, gia, CPU, RAM, GPU, manhinh, ocung, img);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }

    public void add(Laptop ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO laptop VALUES (";
               sql += "'"+ct.getMaLaptop()+"',";
               sql += "'"+ct.getMaNhaSanXuat()+"',";
               sql += "'"+ct.getMaNhaCungCap()+"',";
               sql += "'"+ct.getTen()+"',";
               sql += "'"+ct.getSoluong()+"',";
               sql += "'"+ct.getGia()+"',";
               sql += "'"+ct.getCPU()+"',";
               sql += "'"+ct.getRAM()+"',";
               sql += "'"+ct.getGPU()+"',";
               sql += "'"+ct.getManhinh()+"',";
               sql += "'"+ct.getOcung()+"',";
               sql += "'"+ct.getImg()+"')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM laptop WHERE MALAPTOP='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    
    public void set(Laptop sp) {    
        String sql = "UPDATE sanpham SET ";
        sql += "'"+sp.getMaNhaSanXuat()+"',";
        sql += "'"+sp.getMaNhaCungCap()+"',";
        sql += "'"+sp.getTen()+"',";
        sql += "'"+sp.getSoluong()+"',";
        sql += "'"+sp.getGia()+"',";
        sql += "'"+sp.getCPU()+"',";
        sql += "'"+sp.getRAM()+"',";
        sql += "'"+sp.getGPU()+"',";
        sql += "'"+sp.getManhinh()+"',";
        sql += "'"+sp.getOcung()+"',";
        sql += "'"+sp.getImg()+"',";
        sql += "WHERE MASP='"+sp.getMaLaptop()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void ExportExcelDatabase(){
        try{
            String sql = "SELECT * FROM laptop WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sanphamdb");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("MASP");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("TENSP");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("SOLUONG");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("GIA");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("MACHITIET");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("MANSX");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("MANCC");
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue("IMG");
            cell.setCellStyle(style);
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("MASP"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("TENSP"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getInt("SOLUONG"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getInt("GIA"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getString("MACT"));
            cell = row.createCell(5);
            cell.setCellValue(rs.getString("MANSX"));
            cell = row.createCell(6);
            cell.setCellValue(rs.getString("MANCC"));
            cell = row.createCell(7);
            cell.setCellValue(rs.getString("IMG"));
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./report/sanphamdb.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ImportExcelDatabase(File file){
        try{
            FileInputStream in = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row;
            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                String maSP = row.getCell(0).getStringCellValue();
                String tenSP = row.getCell(1).getStringCellValue();
                int sl = (int) row.getCell(2).getNumericCellValue();
                int gia = (int) row.getCell(3).getNumericCellValue();
                String maCT = row.getCell(4).getStringCellValue();
                String maNhaSanXuat = row.getCell(5).getStringCellValue();
                String maNhaCungCap = row.getCell(6).getStringCellValue();
                String IMG = row.getCell(7).getStringCellValue();
                
                String sql_check = "SELECT * FROM sanpham WHERE MaSP='"+maSP+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO laptop VALUES (";
                    sql += "'"+maSP+"',";
                    sql += "N'"+tenSP+"',";
                    sql += "'"+sl+"',";
                    sql += "'"+gia+"',";
                    sql += "N'"+maCT+"',";
                    sql += "'"+maNhaSanXuat+"',";
                    sql += "'"+maNhaCungCap+"',";
                    sql += "'"+IMG+"',";
                    sql += "'1')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE laptop SET ";
                    sql += "TENSP='"+tenSP+"', ";
                    sql += "SOLUONG='"+sl+"', ";
                    sql += "GIA='"+gia+"', ";
                    sql += "MACT='"+maCT+"', ";
                    sql += "WHERE MALAPTOP='"+maSP+"'";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
