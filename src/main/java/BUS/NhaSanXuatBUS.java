/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.NhaSanXuatDAO;
import DTO.NhaSanXuat;
import java.util.ArrayList;

/**
 *
 * @author WindZ
 */
public class NhaSanXuatBUS {
    private ArrayList<NhaSanXuat> dsNsx ;
    public NhaSanXuatBUS() {   
    }
    public void listNSX() {
        NhaSanXuatDAO nsxDAO = new NhaSanXuatDAO();
        dsNsx = new ArrayList<>();
        dsNsx = nsxDAO.list();
    }
    public void addNsx(NhaSanXuat nsx) {
        dsNsx.add(nsx);
        NhaSanXuatDAO nsxDAO = new NhaSanXuatDAO();
        nsxDAO.add(nsx);
    }

    public void deleteNsx(String idNsx) {
        for(NhaSanXuat nsx : dsNsx ) {
            if(nsx.getMaNSX().equals(idNsx)) {
                dsNsx.remove(nsx);
                NhaSanXuatDAO nsxDAO = new NhaSanXuatDAO();
                nsxDAO.delete(idNsx);
                return;
            }
        }
    }
    public void setNsx(NhaSanXuat s) {
        for(int i = 0 ; i < dsNsx.size() ; i++) {
            if(dsNsx.get(i).getMaNSX().equals(s.getMaNSX())) {
                dsNsx.set(i, s);
                NhaSanXuatDAO nsxDAO = new NhaSanXuatDAO();
                nsxDAO.set(s);
                return;
            }
        }
    }
    public NhaSanXuat searchMaNsx(String mansx) {
        for(NhaSanXuat nsx : dsNsx) {
            if( nsx.getMaNSX().equals(mansx)) {
                return nsx;
            }
        }
        return null;
    }
    public ArrayList<NhaSanXuat> searchNsx(String mansx,String tennsx) {
        ArrayList<NhaSanXuat> search = new ArrayList<>();
        mansx = mansx.isEmpty()?mansx = "": mansx;
        tennsx = tennsx.isEmpty()?tennsx = "": tennsx;
        for(NhaSanXuat nsx : dsNsx)
            if( nsx.getMaNSX().contains(mansx) && 
                nsx.getTenNSX().contains(tennsx)) {
                search.add(nsx);
            }
        return search;
    }
        
    public ArrayList<NhaSanXuat> getList() {
        return dsNsx;
    }
}
