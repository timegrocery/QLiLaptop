package DTO;

public class PhieuNhap {
    String MAPN,MANCC,MANV,NGAYNHAP;
    int TONGTIEN;
    public PhieuNhap(){

    }

    public PhieuNhap(String MAPN,String MANCC,String MANV,String NGAYNHAP,int TONGTIEN){
        this.MAPN = MAPN;
        this.MANCC = MANCC;
        this.MANV = MANV;
        this.NGAYNHAP = NGAYNHAP;
        this.TONGTIEN = TONGTIEN;
    }

    public String getMAPN() {
        return MAPN;
    }
    public void setMAPN(String MAPN){
        this.MAPN = MAPN;
    }
    public String getMANCC() {
        return MANCC;
    }
    public void setMANCC(String MANCC){
        this.MANCC = MANCC;
    }
    public String getMANV() {
        return this.MANV;
    }
    public void setMANV(String MANV) {
        this.MANV = MANV;
    }
    public String getNGAYNHAP() {
        return NGAYNHAP;
    }
    public void setNGAYNHAP(String NGAYNHAP){
        this.NGAYNHAP = NGAYNHAP;
    }
    public int getTONGTIEN() {
        return TONGTIEN;
    }
    public void setTONGTIEN(int TONGTIEN){
        this.TONGTIEN = TONGTIEN;
    }
}