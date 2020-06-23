/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import BUS.LaptopBUS;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
/**
 *
 * @author WindZ
 */
public class outBill {
    private String file;// = "./report/test.pdf";
    private ArrayList<ChiTietHoaDon> cthd = new ArrayList<ChiTietHoaDon>();
    private HoaDon hd;
    private BaseFont bf;
    private LaptopBUS spBUS;
    public outBill() {
            spBUS.listSP();
    }

    public outBill(HoaDon hd,ArrayList<ChiTietHoaDon> cthd)  {
        this.hd = hd;
        file = Handler.getFullPath("report/bill"+hd.getMaHD()+".pdf");
        System.out.println(file);
        this.cthd = cthd;
        spBUS.listSP();
    }
    public void print() {
        String uderline = "*";
        try {
            //Tạo Font
            bf = BaseFont.createFont("./font/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
            // Tạo tài liệu
            Document bill = new Document(PageSize.A4, 15, 15, 10, 10);
            
            String line = "";
            for(int i = 0 ; i < bill.getPageSize().getWidth()/5; i++){
                line += uderline;
            }
            //Tạo đối tượng writter
            PdfWriter.getInstance(bill, new FileOutputStream(file));
            
            //Mở document
            bill.open();
            
            Paragraph header = new Paragraph("Cửa hàng laptop",new Font(bf,35));
            header.setAlignment(1);
            bill.add(header);
            
            Paragraph info = new Paragraph("Hóa dơn : "+hd.getMaHD()+"          Ngày : "+hd.getNgaylap(),new Font(bf,15));
            bill.add(info);
            
            Paragraph l = new Paragraph(line);
            l.setAlignment(1);
            bill.add(l);
            
            String[] cellHeader = {"Mã SP","Tên SP","SL","Đơn Giá (VNĐ)"};
            
            PdfPTable t = new PdfPTable(cellHeader.length);
            t.setSpacingAfter(10);
            t.setSpacingBefore(10);
            int[] relativeWidths = {20,80,10,40};
            t.setWidths(relativeWidths);
            
            for(String s : cellHeader){
                t.addCell(createCell(s, new Font(bf,13)));
            }    
            for(ChiTietHoaDon ct : cthd){
                t.addCell( createCell(ct.getMaLaptop()) );
                t.addCell( createCell(spBUS.getSP(ct.getMaLaptop()).getTen()) );
                t.addCell( createCell(String.valueOf(ct.getSl())) );
                t.addCell( createCell(String.valueOf(ct.getGia())) );
            }
            bill.add(t);
            
            bill.add(l);
            
            Paragraph sum = new Paragraph("Tồng tiền : "+ hd.getTongtien()+"đ",new Font(bf,20));
            sum.setAlignment(Element.ALIGN_RIGHT);
            bill.add(sum);
            
            
            bill.close();
            
                    
            JOptionPane.showMessageDialog(null, "In hoàn tất");
            System.out.println("Done");
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(outBill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(outBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    public PdfPCell createCell(String s) {
        PdfPCell cell = new PdfPCell(new Phrase(s,new Font(bf,13)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(10);
        
        return cell;
    }
    public PdfPCell createCell(String s,Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(s,font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(10);
        return cell;
    }
}
