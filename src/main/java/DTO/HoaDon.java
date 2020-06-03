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
public class HoaDon {
    private String maHD,maKH,maNV,ngaylap;
    private String maCtkm = null;
    int tongtien;

    public HoaDon(String maHD, String maKH, String maNV, String ngaylap, String maCtkm, int tongtien) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngaylap = ngaylap;
        this.maCtkm = maCtkm;
        this.tongtien = tongtien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }
    
    public String getMaCtkm() {
        return this.maCtkm;
    }
    
    public void setCtkm(String maCtkm) {
        this.maCtkm = maCtkm;
    }
    
    public int getTongtien() {
        return this.tongtien;
    }
    
    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
    
}
