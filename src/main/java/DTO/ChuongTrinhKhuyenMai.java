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
public class ChuongTrinhKhuyenMai {
    String maCtkm,tenCtkm, ngayBatdau, ngayKetthuc;
    
    public ChuongTrinhKhuyenMai() {};
    
    public ChuongTrinhKhuyenMai(String maCtkm,String tenCtkm, String ngayBatdau, String ngayKetthuc) {
        this.maCtkm = maCtkm;
        this.tenCtkm = tenCtkm;
        this.ngayBatdau = ngayBatdau;
        this.ngayKetthuc = ngayKetthuc;
    }
    
    public String getMaCtkm() {
        return this.maCtkm;
    }
    
    public void setMaCtkm(String maCtkm) {
        this.maCtkm = maCtkm;
    }
    
    public String getTenCtkm() {
        return this.tenCtkm;
    }
    
    public void setTenCtkm(String tenCtkm) {
        this.tenCtkm = tenCtkm;
    }
    
    public String getNgayBatdau() {
        return this.ngayBatdau;
    }
    
    public void setNgayBatdau(String ngayBatdau) {
        this.ngayBatdau = ngayBatdau;
    }
    
    public String getNgayKetthuc() {
        return this.ngayKetthuc;
    }
    
    public void setNgayKetthuc(String ngayKetthuc) {
        this.ngayKetthuc = ngayKetthuc;
    }
    
}
