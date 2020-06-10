/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.NhaCungCapDAO;
import DTO.NhaCungCap;
import java.util.ArrayList;

/**
 *
 * @author WindZ
 */
public class NhaCungCapBUS {
    public ArrayList<NhaCungCap> dsncc;
    public NhaCungCapBUS(){}
    
    public void listNCC(){
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        dsncc = new ArrayList<>();
        dsncc = nccDAO.list();
    }
    
    public void addNCC(NhaCungCap ncc) {
        dsncc.add(ncc);
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        nccDAO.add(ncc);
    }

    public void deleteNCC(String MaNCC) {
        for(NhaCungCap ncc : dsncc ) {
            if(ncc.getMaNCC().equals(MaNCC)) {
                dsncc.remove(ncc);
                NhaCungCapDAO nccDAO = new NhaCungCapDAO();
                nccDAO.delete(MaNCC);
                return;
            }
        }
    }
    public void setNCC(NhaCungCap s) {
        for(int i = 0 ; i < dsncc.size() ; i++) {
            if(dsncc.get(i).getMaNCC().equals(s.getMaNCC())) {
                dsncc.set(i, s);
                NhaCungCapDAO nccDAO = new NhaCungCapDAO();
                nccDAO.set(s);
                return;
            }
        }
    }
    public NhaCungCap searchMaNcc(String mancc) {
        for(NhaCungCap ncc : dsncc) {
            if( ncc.getMaNCC().equals(mancc)) {
                return ncc;
            }
        }
        return null;
    }
    public ArrayList<NhaCungCap> searchNcc(String mancc,String tenncc) {
        ArrayList<NhaCungCap> search = new ArrayList<>();
        mancc = mancc.isEmpty()?mancc = "": mancc;
        tenncc = tenncc.isEmpty()?tenncc = "": tenncc;
        for(NhaCungCap ncc : dsncc)
            if( ncc.getMaNCC().contains(mancc) && 
                ncc.getTenNCC().contains(tenncc)) {
                search.add(ncc);
            }
        return search;
    }
    public boolean checkMancc(String MaNCC) {
        for(NhaCungCap ncc : dsncc) {
            if(ncc.getMaNCC().equals(MaNCC)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<NhaCungCap> getList() {
        return dsncc;
    }
}
