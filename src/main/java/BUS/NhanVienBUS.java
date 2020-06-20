/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.NhanVienDAO;
import DTO.NhanVien;
import java.util.ArrayList;

/**
 *
 * @author WindZ
 */
public class NhanVienBUS {
    private ArrayList<NhanVien> dsnv ;
    public NhanVienBUS(int i1) {
        listNV();
    }
    
    public NhanVienBUS() {
        
    }
    public NhanVien get(String MaNV) {
        for(NhanVien nv : dsnv ) {
            if(nv.getMaNV().equals(MaNV)) {
                return nv;
            }
        }
        return null;
    }
    public void listNV() {
        NhanVienDAO nvDAO = new NhanVienDAO();
        dsnv = new ArrayList<>();
        dsnv = nvDAO.list();
    }
    public void addNV(NhanVien sp) {
        dsnv.add(sp);
        NhanVienDAO nvDAO = new NhanVienDAO();
        nvDAO.add(sp);
    }

    public void deleteNV(String MaNV) {
        for(NhanVien nv : dsnv ) {
            if(nv.getMaNV().equals(MaNV)) {
                dsnv.remove(nv);
                NhanVienDAO nvDAO = new NhanVienDAO();
                nvDAO.delete(MaNV);
                return;
            }
        }
    }
    public void setNV(NhanVien s) {
        for(int i = 0 ; i < dsnv.size() ; i++) {
            if(dsnv.get(i).getMaNV().equals(s.getMaNV())) {
                dsnv.set(i, s);
                NhanVienDAO nvDAO = new NhanVienDAO();
                nvDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String manv) {
        for(NhanVien nv : dsnv) {
            if(nv.getMaNV().equals(manv)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<NhanVien> search(String manv,String ho,String ten,String phai) {
        ArrayList<NhanVien> search = new ArrayList<>();
        manv = manv.isEmpty()?manv = "": manv;
        ho = ho.isEmpty()?ho = "": ho;
        ten = ten.isEmpty()?ten = "": ten;
        for(NhanVien nv : dsnv) {
            if( nv.getMaNV().contains(manv) && 
                nv.getHoNV().contains(ho) &&
                nv.getTenNV().contains(ten) &&
                nv.getPhai().contains(phai)) {
                search.add(nv);
            }
        }
        return search;
    }
    public String remindMaNV() {
        int max = 0;
        String s ="";
        for(NhanVien nv : dsnv) {
            int id = Integer.parseInt(nv.getMaNV());
            if(id > max) {
                max = id;
            }
        }
        for(int i = 0 ; i < 3-String.valueOf(max+1).length(); i++) {
            s+="0";
        }
        return s+(max+1);
    }
    public ArrayList<NhanVien> getList() {
        return dsnv;
    }
}
