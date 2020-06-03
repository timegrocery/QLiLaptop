/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDon;
import java.util.ArrayList;

/**
 *
 * @author WindZ
 */
public class ChiTietHoaDonBUS {
    private ArrayList<ChiTietHoaDon> dsChiTietHD ;
    public ChiTietHoaDonBUS() {
        
    }
    public ChiTietHoaDonBUS(int i) {
        list();
    }
    public void list() {
        ChiTietHoaDonDAO loaiDAO = new ChiTietHoaDonDAO();
        dsChiTietHD = new ArrayList<>();
        dsChiTietHD = loaiDAO.list();
    }
    public void add(ChiTietHoaDon loai) {
        dsChiTietHD.add(loai);
        ChiTietHoaDonDAO loaiDAO = new ChiTietHoaDonDAO();
        loaiDAO.add(loai);
    }

    public void delete(String idChiTietHD) {
        for(ChiTietHoaDon ct : dsChiTietHD ) {
            if(ct.getMaHD().equals(idChiTietHD))
            {
                dsChiTietHD.remove(ct);
                ChiTietHoaDonDAO loaiDAO = new ChiTietHoaDonDAO();
                loaiDAO.delete(idChiTietHD);
                return;
            }
        }
    }
    
    public void set(ChiTietHoaDon s) {
        for(int i = 0 ; i < dsChiTietHD.size() ; i++) {
            if(dsChiTietHD.get(i).getMaHD().equals(s.getMaHD())) {
                dsChiTietHD.set(i, s);
                return;
            }
        }
    }
    public ChiTietHoaDon search(String maHD) {
        for(ChiTietHoaDon ct : dsChiTietHD) {
            if( ct.getMaHD().equals(maHD)) {
                return ct;
            }
        }
        return null;
    }
    public ArrayList<String> getHD(String maLaptop) {
        ArrayList<String> s = new ArrayList<>();
        if(maLaptop.isEmpty()) return null;
        for(ChiTietHoaDon ct : dsChiTietHD) {
            if(ct.getMaLaptop().equals(maLaptop)) {
                s.add(ct.getMaHD());
            }
        }
        return s;
    }
    public ArrayList<ChiTietHoaDon> getListHD(String maHD) {
        ArrayList<ChiTietHoaDon> ds = new ArrayList<>();
        for(ChiTietHoaDon ct : dsChiTietHD) {
            if( ct.getMaHD().equals(maHD)) {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<ChiTietHoaDon> getList() {
        return dsChiTietHD;
    }
}
