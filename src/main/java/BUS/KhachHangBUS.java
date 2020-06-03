/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.KhachHangDAO;
import DTO.KhachHang;
import DTO.NhanVien;
import java.util.ArrayList;


/**
 *
 * @author WindZ
 */
public class KhachHangBUS {
    private ArrayList<KhachHang> dskh ;
    public KhachHangBUS(int i1) {
        list();
    }
    
    public KhachHangBUS() {
        
    }
    public KhachHang get(String MaKH)
    {
        for(KhachHang kh : dskh )
        {
            if(kh.getMaKH().equals(MaKH))
            {
                return kh;
            }
        }
        return null;
    }
    public void list() {
        KhachHangDAO khDAO = new KhachHangDAO();
        dskh = new ArrayList<>();
        dskh = khDAO.list();
    }
    public void add(KhachHang kh)
    {
        dskh.add(kh);
        KhachHangDAO khDAO = new KhachHangDAO();
        khDAO.add(kh);
    }

    public void delete(String MaKH) {
        for(KhachHang kh : dskh ) {
            if(kh.getMaKH().equals(MaKH)) {
                dskh.remove(kh);
                KhachHangDAO khDAO = new KhachHangDAO();
                khDAO.delete(MaKH);
                return;
            }
        }
    }
    public void set(KhachHang s) {
        for(int i = 0 ; i < dskh.size() ; i++) {
            if(dskh.get(i).getMaKH().equals(s.getMaKH())) {
                dskh.set(i, s);
                KhachHangDAO khDAO = new KhachHangDAO();
                khDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String makh) {
        for(KhachHang kh : dskh) {
            if(kh.getMaKH().equals(makh)) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<KhachHang> search(String makh,String ho,String ten) {
        ArrayList<KhachHang> search = new ArrayList<>();
        makh = makh.isEmpty()?makh = "": makh;
        ho = ho.isEmpty()?ho = "": ho;
        ten = ten.isEmpty()?ten = "": ten;
        for(KhachHang kh : dskh) {
            if( kh.getMaKH().contains(makh) && 
                kh.getHoKH().contains(ho) &&
                kh.getTenKH().contains(ten)) {
                search.add(kh);
            }
        }
        return search;
    }
    public ArrayList<KhachHang> getList() {
        return dskh;
    }
}
