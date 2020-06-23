package DAL;

import DTO.PhieuNhap;
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

public class PhieuNhapDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public ArrayList <PhieuNhap> list() throws SQLException {
        ArrayList <PhieuNhap> dsct = new ArrayList<>();
        String sql = "SELECT * FROM phieunhap";
        ResultSet rs = mySQL.executeQuery(sql);
        while (rs.next()){
            String MAPN = rs.getString("MAPN");
            String MANCC = rs.getString("MANCC");
            String MANV = rs.getString("MANV");
            String NGAYNHAP = rs.getString("NGAYNHAP");
            String MALAPTOP = rs.getString("MALAPTOP");
            int SOLUONG = rs.getInt("SOLUONG");
            int DONGIA = rs.getInt("DONGIA");
            int TONGTIEN = rs.getInt("TONGTIEN");
            PhieuNhap temp = new PhieuNhap(MAPN,MANCC,MANV,NGAYNHAP,MALAPTOP,SOLUONG,DONGIA,TONGTIEN);
            dsct.add(temp);
        }
        return dsct;
    }

    public void add (PhieuNhap ph) {

        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO phieunhap VALUES (";
        sql += "'" + ph.getMAPN() + ",";  
        sql += "'" + ph.getMANCC() + ",";
        sql += "'" + ph.getMANV() + ",";
        sql += "'" + ph.getNGAYNHAP()+ ",";
        sql += "'" + ph.getMALAPTOP() + ",";
        sql += "'" + ph.getSOLUONG() + ",";
        sql += "'" + ph.getDONGIA() + ",";
        sql += "'" + ph.getTONGTIEN() + "," + ");";

        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String MAPN){
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM phieunhap WHERE MAPN='"+MAPN+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public void set(PhieuNhap ph){
        String sql = "UPDATE phieunhap SET";
        sql += "MAPN='"+ph.getMAPN()+"', ";
        sql += "MANCC='"+ph.getMANCC()+"',";
        sql += "MANV='"+ph.getMANV()+"',";
        sql += "NGAYNHAP='"+ph.getNGAYNHAP()+"',";
        sql += "MALAPTOP='"+ph.getMALAPTOP()+"',";
        sql += "SOLUONG='"+ph.getSOLUONG()+"',";
        sql += "DONGIA='"+ph.getDONGIA()+"',";
        sql += "TONGTIEN='"+ph.getTONGTIEN()+"',";
        sql += "WHERE MAPN='"+ph.getMAPN()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void ExportExcelDatabase(){
        try{
            String sql = "SELECT * FROM phieunhap";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Phieunhapdb");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("MAPN");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("MANCC");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("MANV");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("NGAYNHAP");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("TONGTIEN");
            cell.setCellStyle(style);
            int i = 1;
        
        while(rs.next()){

            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("MAPN"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("MANCC"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getInt("MANV"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getInt("NGAYNHAP"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getInt("TONGTIEN"));
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./report/Phieunhapdb.xlsx"));
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
                String MAPN = row.getCell(0).getStringCellValue();
                String MANCC = row.getCell(1).getStringCellValue();
                String MANV = row.getCell(2).getStringCellValue();
                String NGAYLAP = row.getCell(3).getStringCellValue();
                int TONGTIEN = (int) row.getCell(4).getNumericCellValue();
                
                String sql_check = "SELECT * FROM phieunhap WHERE MAPN='"+MAPN+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO laptop VALUES (";
                    sql += "'" + MAPN + "',";
                    sql += "N'" + MANCC + "',";
                    sql += "'" + MANV + "',";
                    sql += "'" + NGAYLAP + "',";
                    sql += "N'" + TONGTIEN + "'," +"')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE laptop SET ";
                    sql += "MAPN='" + MAPN + "', ";
                    sql += "MANCC='" + MANCC + "', ";
                    sql += "MANV='" + MANV + "', ";
                    sql += "NGAYLAP='" + NGAYLAP + "', ";
                    sql += "WHERE MAPN='" + MAPN + "'";
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