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
public class NhapHang {
    private String idNH,maNCC,maSP,ngayNhap;
    private int donGiaNhap,soLuong,tongTien;

    public NhapHang() {
    }

    public NhapHang(String idNH, String maNCC, String maSP, String ngayNhap, int donGiaNhap, int soLuong, int tongTien) {
        this.idNH = idNH;
        this.maNCC = maNCC;
        this.maSP = maSP;
        this.ngayNhap = ngayNhap;
        this.donGiaNhap = donGiaNhap;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public String getIdNH() {
        return idNH;
    }

    public void setIdNH(String idNH) {
        this.idNH = idNH;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(int donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    
    
    
}
