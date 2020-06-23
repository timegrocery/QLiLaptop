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

    public Laptop(String maLaptop, String maNhaSanXuat, String maNhaCungCap, String ten, int soluong, int gia, String CPU, String RAM, String GPU, String manhinh, String ocung, String img) {
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

    public String getMaLaptop() {
        return maLaptop;
    }

    public void setMaLaptop(String maLaptop) {
        this.maLaptop = maLaptop;
    }

    public String getMaNhaSanXuat() {
        return maNhaSanXuat;
    }

    public void setMaNhaSanXuat(String maNhaSanXuat) {
        this.maNhaSanXuat = maNhaSanXuat;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getManhinh() {
        return manhinh;
    }

    public void setManhinh(String manhinh) {
        this.manhinh = manhinh;
    }

    public String getOcung() {
        return ocung;
    }

    public void setOcung(String ocung) {
        this.ocung = ocung;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    
    
}
