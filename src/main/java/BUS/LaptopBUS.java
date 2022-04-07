/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.LaptopDAO;
import DTO.Laptop;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author WindZ
 */
public class LaptopBUS {
    private ArrayList<Laptop> dssp ;
    public LaptopBUS() {
        
    }
    public void listSP() {
        LaptopDAO spDAO = new LaptopDAO();
        dssp = new ArrayList<>();
        dssp = spDAO.list();
    }
    public int getSize(){
        return this.dssp.size();
    }
    public int getSizeOf(String maNsx) {
        int soluong = 0;
        for(Laptop sp : dssp) {
            if (sp.getMaNhaSanXuat().equals(maNsx)) {
                soluong++;
            }
        }
        return soluong;
    }
    public void addSP(Laptop sp) {
        dssp.add(sp);
        LaptopDAO spDAO = new LaptopDAO();
        spDAO.add(sp);
    }

    public void deleteSP(String idSP) {
        for(Laptop sp : dssp ) {
            if(sp.getMaLaptop().equals(idSP)) {
                dssp.remove(sp);
                LaptopDAO spDAO = new LaptopDAO();
                spDAO.delete(idSP);
                return;
            }
        }
    }
    public void setSP(Laptop s) {
        for(int i = 0 ; i < dssp.size() ; i++) {
            if(dssp.get(i).getMaLaptop().equals(s.getMaLaptop())) {
                dssp.set(i, s);
                LaptopDAO spDAO = new LaptopDAO();
                spDAO.set(s);
                return;
            }
        }
    }
    
    public boolean checkMasp(String masp){
        for(Laptop sp : dssp) {
            if(sp.getMaLaptop().equals(masp)) {
                return true;
            }
        }
        return false;
    }
    public Laptop getSP(String masp) {
        for(Laptop sp : dssp) {
            if(sp.getMaLaptop().equals(masp)) {
                return sp;
            }
        }
        return null;
    }
    public void updateSL(String masp, int sl) {
        for(Laptop sp : dssp) {
            if(sp.getMaLaptop().equals(masp)) {
                int old = sp.getSoluong();
                if(sl > old) {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return;
                }
                old -= sl;
                sp.setSoluong(old);
                LaptopDAO spDAO = new LaptopDAO();
                spDAO.set(sp);
                System.out.println(sp.getSoluong());
                return;
            }
        }
    }
    public void updateSLValue(String masp, int sl) {
        for(Laptop sp : dssp) {
            if(sp.getMaLaptop().equals(masp)) {
                sp.setSoluong(sl);
                LaptopDAO spDAO = new LaptopDAO();
                spDAO.set(sp);
                return;
            }
        }
    }
    public boolean checkSL(String masp , int sl) {
        for(Laptop sp : dssp) {
             if(sp.getMaLaptop().equals(masp)) {
                if(sl > sp.getSoluong()) {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return true;
                }
             }
         }
         return false;
    }
    public ArrayList<Laptop> searchSP(String masp, String mansx, int max, int min) {
        ArrayList<Laptop> search = new ArrayList<>();
        masp = masp.isEmpty()? "" : masp;
        mansx = mansx.isEmpty()? "" : mansx;
        for(Laptop sp : dssp) {
            if( sp.getMaLaptop().contains(masp) && 
                sp.getMaNhaSanXuat().contains(mansx) &&
                sp.getGia() >= min && 
                sp.getGia() <= max) {
                search.add(sp);
            }
        }
        return search;
    }
    public String remindMaLaptop() {
        int max = 0;
        StringBuilder s = new StringBuilder();
        for(Laptop kh : dssp) {
            int id = Integer.parseInt(kh.getMaLaptop());
            if(id > max) {
                max = id;
            }
        }
        s.append("0".repeat(Math.max(0, 3 - String.valueOf(max + 1).length())));
        return s.toString() +(max+1);
    }
    public ArrayList<Laptop> getList() {
        return dssp;
    }
    
    public void ExportExcelDatabase(){
        LaptopDAO spDAO = new LaptopDAO();
        spDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase(File file){
        LaptopDAO spDAO = new LaptopDAO();
        spDAO.ImportExcelDatabase(file);
    }
}
