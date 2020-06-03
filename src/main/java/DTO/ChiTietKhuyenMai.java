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
    String maCtkm, maLaptop;
    float cachtinh;
    
    public ChiTietKhuyenMai(){
        
    }
    
    public ChiTietKhuyenMai(String maCtkm, String maLaptop, float cachtinh) {
        this.maCtkm = maCtkm;
        this.maLaptop = maLaptop;
        this.cachtinh = cachtinh;
    }
    
    public String getMaCtkm() {
        return this.maCtkm;
    }
    
    public void setMaCtkm(String maCtkm) {
        this.maCtkm = maCtkm;
    }
    
    public String getMaLaptop() {
        return this.maLaptop;
    }
    
    public void setMaLaptop(String maLaptop) {
        this.maLaptop = maLaptop;
    }
    
    public float getCachtinh() {
        return this.cachtinh;
    }
    
    public void setCachtinh(float cachtinh) {
        this.cachtinh = cachtinh;
    }
}
