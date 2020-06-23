/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAL.ThongKeDAO;
import DTO.HoaDon;
import DTO.NhapHang;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author WindZ
 */
public class ThongKeBUS {
    private HoaDonBUS hdBUS = new HoaDonBUS();
    private NhapHangBUS nhBUS = new NhapHangBUS();
    public ThongKeBUS(){
        hdBUS.list();
        nhBUS.list();
    }
    public String StatisticSP(String Id,Calendar from,Calendar to){
        ArrayList<HoaDon> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ArrayList<NhapHang> dsNhap = new ArrayList<> ();
        dsNhap = nhBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticSP(dsHD,dsNhap, Id);
    }
    public String StatisticNV(String Id,Calendar from,Calendar to){
        ArrayList<HoaDon> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticNV(dsHD, Id);
    }
    public String StatisticKH(String Id,Calendar from,Calendar to){
        ArrayList<HoaDon> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticKH(dsHD, Id);
    }
    
    public ArrayList<String> StatisticTopSP(Calendar from,Calendar to){
        ArrayList<HoaDon> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticTopSP(dsHD);
    }
    
    public ArrayList<String> StatisticTopNV(Calendar from,Calendar to){
        ArrayList<HoaDon> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticTopNV(dsHD);
    }
    
    public ArrayList<String> StatisticTopKH(Calendar from,Calendar to){
        ArrayList<HoaDon> dsHD = new ArrayList<> ();
        dsHD = hdBUS.ListTime(from, to);
        
        ThongKeDAO tkDAO = new ThongKeDAO();
        return tkDAO.StatisticTopKH(dsHD);
    }
}
