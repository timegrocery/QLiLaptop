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
public class ChiTietKhuyenMai {
    int maCtkm, maLaptop, cachtinh;
    
    public ChiTietKhuyenMai(){
        
    }
    
    public ChiTietKhuyenMai(int maCtkm, int maLaptop, int cachtinh) {
        this.maCtkm = maCtkm;
        this.maLaptop = maLaptop;
        this.cachtinh = cachtinh;
    }
    
    public int getMaCtkm() {
        return this.maCtkm;
    }
    
    public void setMaCtkm(int maCtkm) {
        this.maCtkm = maCtkm;
    }
    
    public int getMaLaptop() {
        return this.maLaptop;
    }
    
    public void setMaLaptop(int maLaptop) {
        this.maLaptop = maLaptop;
    }
    
    public int getCachtinh() {
        return this.cachtinh;
    }
    
    public void setCachtinh(int cachtinh) {
        this.cachtinh = cachtinh;
    }
}
