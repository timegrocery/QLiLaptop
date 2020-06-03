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
public class Laptop {
    String maLaptop, maChitiet, maNhaSanXuat, maNhaCungCap;
    String ten, img;
    int soluong,gia;
    
    public Laptop() {
        
    }
    
    public Laptop(String maLaptop, String ten, int soluong, int gia, String maChitiet, String maNhaSanXuat, String maNhaCungCap, String img) {
        this.maLaptop = maLaptop;
        this.ten = ten;
        this.soluong = soluong;
        this.gia = gia;
        this.maChitiet = maChitiet;
        this.maNhaSanXuat = maNhaSanXuat;
        this.maNhaCungCap = maNhaCungCap;
        this.img = img;
    }
    
    public String getMaLaptop() {
        return this.maLaptop;
    }
    
    public void setMaLaptop(String maLaptop){
        this.maLaptop = maLaptop;
        
    }
    
    public String getTen() {
        return this.ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }
    
    public int getSoluong() {
        return this.soluong;
    }
    
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    public int getGia() {
        return this.gia;
    }
    
    public void setGia(int gia) {
        this.gia = gia;
    }
    
    public String getMaChitiet() {
        return this.maChitiet;
    }
    
    public void setMaChitiet(String maChitiet) {
        this.maChitiet = maChitiet;
    }
    
    public String getMaNhaSanXuat() {
        return this.maNhaSanXuat;
    }
    
    public void setMaNhaSanXuat(String maNhaSanXuat) {
        this.maNhaSanXuat = maNhaSanXuat;
    }
    
    public String getMaNhaCungCap() {
        return this.maNhaCungCap;
       
    }
    
    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }
    
    public String getImg() {
        return this.img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
    
}
