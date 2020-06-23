/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.HoaDonDAO;
import DTO.HoaDon;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author WindZ
 */
public class HoaDonBUS {
    private ArrayList<HoaDon> dsHD ;
    public HoaDonBUS() {
    }
    public HoaDonBUS(int i) {
        list();
    }
    public void list() {
        HoaDonDAO hdDAO = new HoaDonDAO();
        dsHD = new ArrayList<>();
        dsHD = hdDAO.list();
    }
    public void add(HoaDon hd) {
        dsHD.add(hd);
        HoaDonDAO hdDAO = new HoaDonDAO();
        hdDAO.add(hd);
    }

    public void delete(String idChiTietHD) {
        for(HoaDon hd : dsHD ) {
            if(hd.getMaHD().equals(idChiTietHD)) {
                dsHD.remove(hd);
                HoaDonDAO hdDAO = new HoaDonDAO();
                hdDAO.delete(idChiTietHD);
                return;
            }
        }
    }
    public int set(HoaDon s) {
        for(int i = 0 ; i < dsHD.size() ; i++) {
            if(dsHD.get(i).getMaHD().equals(s.getMaHD())) {
                dsHD.set(i, s);
                HoaDonDAO hdDAO = new HoaDonDAO();
                hdDAO.set(s);
                return i;
            }
        }
        return -1;
    }
    public String remindMaHD() {
        int max = 0;
        String s ="";
        for(HoaDon hd : dsHD) {
            int id = Integer.parseInt(hd.getMaHD());
            if(id > max) {
                max = id;
            }
        }
        for(int i = 0 ; i < 3-String.valueOf(max+1).length(); i++) {
            s+="0";
        }
        return s+(max+1);
    }
    public boolean checkTime(Calendar from,Calendar to,Calendar time){
        if(time.after(from) && time.before(to)) {
            return true;
        }
        return false;
    }
    public ArrayList<HoaDon> ListTime(Calendar from,Calendar to) {
        ArrayList<HoaDon> list = new ArrayList<>();
        for(HoaDon hd : dsHD) {
            Timestamp date = Timestamp.valueOf(hd.getNgaylap());
            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(date.getTime());
            if(checkTime(from, to, time)) {
                list.add(hd);
            }
        }
        return list;
    }
            
    public ArrayList<HoaDon> search( int mm, int yyy,double max, double min,ArrayList<String> mahd) {
        int mm1 = 0, mm2 = 12;
        int yyy1 = 0, yyy2 = Calendar.getInstance().get(Calendar.YEAR);
        
        if(mm != -1) {
            mm1 = mm;
            mm2 = mm;
        }
        if(yyy != 0) {
            yyy1 = yyy;
            yyy2 = yyy;
        }
        
        ArrayList<HoaDon> ds = getListWidthArray(mahd);
        ArrayList<HoaDon> search = new ArrayList<>();
        for(HoaDon hd : ds) {
            Timestamp time = Timestamp.valueOf(hd.getNgaylap());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time.getTime());;
            
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            
            if( hd.getTongtien() >= min && hd.getTongtien() <= max 
                && (month >= mm1 && month <= mm2)
                && (year >= yyy1 && year <= yyy2)) {
                search.add(hd);
            }
        }
        return search;
    }
    public boolean check(String maHD) {
        for(HoaDon hd : dsHD) {
            if( hd.getMaHD().equals(maHD)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<HoaDon> getListWidthArray(ArrayList<String> s) {
        ArrayList<HoaDon> ds = new ArrayList<>();
        if(s == null) return dsHD;
        for(HoaDon hd : dsHD) {
            String mahd = hd.getMaHD();
            for(String a: s){
                if(mahd.equals(a)){
                    ds.add(hd);
                }
            }
        }
        return ds;
    }
    public ArrayList<HoaDon> getList() {
        return dsHD;
    }
}
