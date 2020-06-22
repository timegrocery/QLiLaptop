package BUS;

import DAL.PhieuNhapDAO;
import DTO.PhieuNhap;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PhieuNhapBUS {
    private ArrayList <PhieuNhap> dspn = new ArrayList<>();

    public PhieuNhapBUS() {
        
    }

    public void listpn() throws SQLException {
        PhieuNhapDAO temp = new PhieuNhapDAO();
        dspn = temp.list();
    }

    public void addPN(PhieuNhap pn){
        dspn.add(pn);
        PhieuNhapDAO temp = new PhieuNhapDAO();
        temp.add(pn);
    }

    public void deletePN(String MAPN){
        for (PhieuNhap pn : dspn){
            if(pn.getMAPN().equals(MAPN)){
                dspn.remove(pn);
                PhieuNhapDAO temp = new PhieuNhapDAO();
                temp.delete(MAPN);
                return;
            }
        }
    }

    public void setPN(PhieuNhap pn) {
        for(int i = 0 ; i < dspn.size() ; ++i) {
            if(dspn.get(i).getMAPN().equals(pn.getMAPN())) {
                dspn.set(i, pn);
                PhieuNhapDAO spDAO = new PhieuNhapDAO();
                spDAO.set(pn);
                return;
            }
        }
    }
    
    public boolean checkMAPN (String MAPN){
        for (PhieuNhap pn : dspn){
            if (pn.getMAPN().equals(MAPN))
                return true;
        }
        return false;
    }
    
    public PhieuNhap getpn(String MAPN){
        for (PhieuNhap pn : dspn){
            if(pn.getMAPN().equals(MAPN)){
                return pn;
            }
        }
        return null;
    }
    public String remindMaPN() {
        int max = 0;
        String s ="";
        for(PhieuNhap pn : dspn) {
            int id = Integer.parseInt(pn.getMAPN());
            if(id > max) {
                max = id;
            }
        }
        for(int i = 0 ; i < 3-String.valueOf(max+1).length(); i++) {
            s+="0";
        }
        return s+(max+1);
    }
    public ArrayList<PhieuNhap> getList() {
        return dspn;
    }
}