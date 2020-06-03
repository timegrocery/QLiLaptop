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
public class ChiTietPhieuNhap {
    String maPN, maLaptop;
    int soluong, dongia, thanhtien;
    
    public ChiTietPhieuNhap() {
        
    }
    
    public ChiTietPhieuNhap(String maPN, String maLaptop, int soluong, int dongia, int thanhtien) {
        this.maPN = maPN;
        this.maLaptop = maLaptop;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }
    
    public String getMaPN(){
        return this.maPN;
    }
    public String getMaLaptop(){
        return this.maLaptop;
    }
    public int getSoluong(){
        return this.soluong;
    }
    public int getDongia(){
        return this.dongia;
    }
    public int getThanhtien(){
        return this.thanhtien;
    }
    
    public void setMaPN(String maPN){
        this.maPN = maPN;
    }
    public void setMaLaptop(String maLaptop){
        this.maLaptop = maLaptop;
    }
    public void setSoluong(int soluong){
        this.soluong = soluong;
    }
    public void setDongia(int dongia){
        this.dongia = dongia;
    }
    public void setThanhtien(int thanhtien){
        this.thanhtien = thanhtien;
    }
    
}
