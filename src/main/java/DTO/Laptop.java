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
    String maLaptop;
    String maNhaSanXuat;
    String maNhaCungCap;
    String ten;
    int soluong;
    int gia;
    String CPU;
    String RAM;
    String GPU;
    String manhinh;
    String ocung;    
    String img;

    
    public Laptop() {
        
    }
    
    public Laptop(String maLaptop, String maNhaSanXuat, String maNhaCungCap, String ten, int soluong, int gia,
            String CPU, String RAM, String GPU, String manhinh, String ocung, String img) {
        this.maLaptop = maLaptop;
        this.maNhaSanXuat = maNhaSanXuat;
        this.maNhaCungCap = maNhaCungCap;
        this.ten = ten;
        this.soluong = soluong;
        this.gia = gia;
        this.CPU = CPU;
        this.RAM = RAM;
        this.GPU = GPU;
        this.manhinh = manhinh;
        this.ocung = ocung;
        this.img = img;
    }
    
    public String getCPU() {
        return this.CPU;
    }
    
    public void setCPU(String CPU) {
        this.CPU = CPU;
    }
    
    public String getRAM() {
        return this.RAM;
    }
    
    public void setRAM(String RAM) {
        this.RAM = RAM;
    }
    
    public String getGPU() {
        return this.GPU;
    }
    
    public void setGPU(String GPU) {
        this.GPU = GPU;
    }
    
    public String getManhinh() {
        return this.manhinh;
    }
    
    public void setManhinh(String manhinh) {
        this.manhinh = manhinh;
    }
    
    public String getOcung() {
        return this.ocung;
    }
    
    public void setOcung(String ocung) {
        this.ocung = ocung;
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
