/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author WindZ
 */
public class ChiTietHoaDon {
    private String maLaptop,maHD;
    private int gia,sl,thanhtien;

    public ChiTietHoaDon(String maHD, String maLaptop, int sl, int gia, int thanhtien) {
        this.maLaptop = maLaptop;
        this.maHD = maHD;
        this.gia = gia;
        this.sl = sl;
        this.thanhtien = thanhtien;
    }

    public String getMaLaptop() {
        return maLaptop;
    }

    public void setMaLaptop(String maSP) {
        this.maLaptop = maLaptop;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
    
    public int getThanhTien() {
        return this.thanhtien;
    }
    
    public int setThanhTien() {
        return this.thanhtien;
    }
}
