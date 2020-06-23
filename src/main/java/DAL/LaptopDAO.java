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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public LaptopDAO(){
    }
    public ArrayList<Laptop> list() {
        ArrayList<Laptop> dsct = new ArrayList<>();
        try {
            String sql = "SELECT * FROM laptop WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next()){
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
        String sql = "UPDATE laptop SET ";
        sql += "MANSX='"+sp.getMaNhaSanXuat()+"', ";
        sql += "MANCC='"+sp.getMaNhaCungCap()+"', ";
        sql += "TEN='"+sp.getTen()+"', ";
        sql += "SOLUONG='"+sp.getSoluong()+"', ";
        sql += "GIA='"+sp.getGia()+"', ";
        sql += "CPU='"+sp.getCPU()+"', ";
        sql += "RAM='"+sp.getRAM()+"', ";
        sql += "GPU='"+sp.getGPU()+"', ";
        sql += "MANHINH='"+sp.getManhinh()+"', ";
        sql += "OCUNG='"+sp.getOcung()+"', ";
        sql += "IMG='"+sp.getImg()+"' ";
        sql += "WHERE MALAPTOP='"+sp.getMaLaptop()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void ExportExcelDatabase(){
        try {
            String sql = "SELECT * FROM laptop WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Laptopdb");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("MALAPTOP");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("MANSX");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("MANCC");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("TEN");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("SOLUONG");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("GIA");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("CPU");
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue("RAM");
            cell.setCellStyle(style);
            cell = row.createCell(8);
            cell.setCellValue("GPU");
            cell.setCellStyle(style);
            cell = row.createCell(9);
            cell.setCellValue("MANHINH");
            cell.setCellStyle(style);
            cell = row.createCell(10);
            cell.setCellValue("OCUNG");
            cell.setCellStyle(style);
            cell = row.createCell(11);
            cell.setCellValue("IMG");
            cell.setCellStyle(style);
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("MALAPTOP"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("MANSX"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("MANCC"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getString("TEN"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getInt("SOLUONG"));
            cell = row.createCell(5);
            cell.setCellValue(rs.getInt("GIA"));
            cell = row.createCell(6);
            cell.setCellValue(rs.getString("CPU"));
            cell = row.createCell(7);
            cell.setCellValue(rs.getString("RAM"));
            cell = row.createCell(8);
            cell.setCellValue(rs.getString("GPU"));
            cell = row.createCell(9);
            cell.setCellValue(rs.getString("MANHINH"));
            cell = row.createCell(10);
            cell.setCellValue(rs.getString("OCUNG"));
            cell = row.createCell(11);
            cell.setCellValue(rs.getString("IMG"));
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./report/Laptopdb.xlsx"));
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
                String maNSX = row.getCell(1).getStringCellValue();
                String maNCC = row.getCell(2).getStringCellValue();
                String tenSP = row.getCell(3).getStringCellValue();
                int sl = (int) row.getCell(4).getNumericCellValue();
                int gia = (int) row.getCell(5).getNumericCellValue();
                String CPU = row.getCell(6).getStringCellValue();
                String RAM = row.getCell(7).getStringCellValue();
                String GPU = row.getCell(8).getStringCellValue();
                String MANHINH = row.getCell(9).getStringCellValue();
                String OCUNG = row.getCell(10).getStringCellValue();
                String IMG = row.getCell(11).getStringCellValue();
                
                String sql_check = "SELECT * FROM laptop WHERE MALAPTOP='"+maSP+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO laptop VALUES (";
                    sql += "'"+maSP+"',";
                    sql += "'"+maNSX+"',";
                    sql += "'"+maNCC+"',";
                    sql += "N'"+tenSP+"',";
                    sql += "'"+sl+"',";
                    sql += "'"+gia+"',";
                    sql += "N'"+CPU+"',";
                    sql += "'"+RAM+"',";
                    sql += "'"+GPU+"',";
                    sql += "'"+MANHINH+"',";
                    sql += "'"+OCUNG+"',";
                    sql += "'"+IMG+"')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE laptop SET ";
                    sql += "MANSX='"+maNSX+"', ";
                    sql += "MANCC='"+maNCC+"', ";
                    sql += "TEN='"+tenSP+"', ";
                    sql += "SOLUONG='"+sl+"', ";
                    sql += "GIA='"+gia+"', ";
                    sql += "CPU='"+CPU+"', ";
                    sql += "RAM='"+RAM+"', ";
                    sql += "GPU='"+GPU+"', ";
                    sql += "MANHINH='"+MANHINH+"', ";
                    sql += "OCUNG='"+OCUNG+"', ";
                    sql += "IMG='"+IMG+"' ";
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
