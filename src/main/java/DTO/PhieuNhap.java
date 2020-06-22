package DTO;

public class PhieuNhap {

    String MAPN, MANCC, MANV, NGAYNHAP, MALAPTOP;
    int SOLUONG, DONGIA, TONGTIEN;

    public PhieuNhap(String MAPN, String MANCC, String MANV, String NGAYNHAP, String MALAPTOP, int SOLUONG, int DONGIA, int TONGTIEN) {
        this.MAPN = MAPN;
        this.MANCC = MANCC;
        this.MANV = MANV;
        this.NGAYNHAP = NGAYNHAP;
        this.MALAPTOP = MALAPTOP;
        this.SOLUONG = SOLUONG;
        this.DONGIA = DONGIA;
        this.TONGTIEN = TONGTIEN;
    }

    public String getMAPN() {
        return MAPN;
    }

    public void setMAPN(String MAPN) {
        this.MAPN = MAPN;
    }

    public String getMANCC() {
        return MANCC;
    }

    public void setMANCC(String MANCC) {
        this.MANCC = MANCC;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getNGAYNHAP() {
        return NGAYNHAP;
    }

    public void setNGAYNHAP(String NGAYNHAP) {
        this.NGAYNHAP = NGAYNHAP;
    }

    public String getMALAPTOP() {
        return MALAPTOP;
    }

    public void setMALAPTOP(String MALAPTOP) {
        this.MALAPTOP = MALAPTOP;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(int DONGIA) {
        this.DONGIA = DONGIA;
    }

    public int getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(int TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }

    

    
}
