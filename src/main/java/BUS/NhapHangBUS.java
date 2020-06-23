/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.NhapHangDAO;
import DTO.NhapHang;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author WindZ
 */
public class NhapHangBUS {
    private ArrayList<NhapHang> dsNH;
    public NhapHangBUS() {
    }
    
    public NhapHangBUS(int i) {
        list();
    }
    
    public void list() {
        NhapHangDAO nhDAO = new NhapHangDAO();
        dsNH= new ArrayList<>();
        dsNH= nhDAO.list();
    }
    
    public void add(NhapHang nh) {
        int id = 0;
        if(!dsNH.isEmpty()) {
            id = Integer.parseInt(dsNH.get(dsNH.size()-1).getMaPN());
        }
        nh.setMaPN(String.valueOf(id+1));
        dsNH.add(nh);
        NhapHangDAO nhDAO = new NhapHangDAO();
        nhDAO.add(nh);
    }

    public void delete(String idNH){
        for(NhapHang nh : dsNH) {
            if(nh.getMaPN().equals(idNH)) {
                dsNH.remove(nh);
                NhapHangDAO nhDAO = new NhapHangDAO();
                nhDAO.delete(idNH);
                return;
            }
        }
    }
    public int set(NhapHang s) {
        for(int i = 0 ; i < dsNH.size() ; i++) {
            if(dsNH.get(i).getMaPN().equals(s.getMaPN())) {
                dsNH.set(i, s);
                NhapHangDAO nhDAO = new NhapHangDAO();
                nhDAO.set(s);
                return i;
            }
        }
        return -1;
    }
    public boolean checkTime(Calendar from,Calendar to,Calendar time) {
        if(time.after(from) && time.before(to)) {
            return true;
        }
        return false;
    }
    public ArrayList<NhapHang> ListTime(Calendar from,Calendar to) {
        ArrayList<NhapHang> list = new ArrayList<>();
        for(NhapHang nh : dsNH) {
            Timestamp date = Timestamp.valueOf(nh.getNgayNhap());
            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(date.getTime());
            if(checkTime(from, to, time)) {
                list.add(nh);
            }
        }
        return list;
    }
    public String remindMaNH() {
        int max = 0;
        String s ="";
        for(NhapHang nh : dsNH) {
            int id = Integer.parseInt(nh.getMaPN());
            if(id > max) {
                max = id;
            }
        }
        for(int i = 0 ; i < 3-String.valueOf(max+1).length(); i++) {
            s+="0";
        }
        return s+(max+1);
    }
    public ArrayList<NhapHang> getList() {
        return dsNH;
    }
}
