/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSTest.BUS;

import BUS.HoaDonBUS;
import DTO.HoaDon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *
 * @author Bao Long
 */
public class HoaDonBUSTest {
    HoaDonBUS hdBUS;

    @Test
    public void testList() {
        hdBUS = new HoaDonBUS();
        hdBUS.list();
        assertThat(hdBUS.getList(), is(not(empty())));
    }

    @Test
    public void testAdd() {
        HoaDon hd = new HoaDon("6042","0013","4001","2021-05-01 11:49:51","5010",100000);
        hdBUS = new HoaDonBUS();
        hdBUS.list();
        hdBUS.add(hd);
        HoaDon expected = hdBUS.getList().get(hdBUS.getList().size() - 1);
        
        assertEquals(expected.getMaHD(),hd.getMaHD());
        hdBUS.delete(hd.getMaHD());
    }

    @Test
    public void testDelete() {
        HoaDon hd = new HoaDon("6043","0013","4001","2021-05-01 11:49:51","5010",100000);
        hdBUS = new HoaDonBUS();
        hdBUS.list();
        hdBUS.add(hd);
        int expectedSize = hdBUS.getList().size() - 1;
        hdBUS.delete(hd.getMaHD());
        assertEquals(expectedSize,hdBUS.getList().size());
    }

    @Test
    public void testListTime() {
        HoaDon hd1 = new HoaDon("6044","0013","4001","2021-05-01 11:49:51","5010",100000);
        HoaDon hd2 = new HoaDon("6045","0014","4002","2021-06-30 7:35:52","5002",300000);
        hdBUS = new HoaDonBUS();
        hdBUS.list();
        hdBUS.add(hd1);
        hdBUS.add(hd2);
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        int expectedSize = 2;
        
        from.set(2021, 01, 01);
        to.set(2021, 12, 30);
        
        ArrayList<HoaDon> arr = hdBUS.ListTime(from, to);
        
        assertEquals(expectedSize,arr.size());
        
        hdBUS.delete(hd1.getMaHD());
        hdBUS.delete(hd2.getMaHD());
    }

    @Test
    public void testSearch() {
        HoaDon hd = new HoaDon("6045","0013","4001","2021-05-01 11:49:51","5010",100000);
        hdBUS = new HoaDonBUS();
        hdBUS.list();
        hdBUS.add(hd);
        
        ArrayList <String> mahd = new ArrayList<>();
        mahd.add(hd.getMaHD());
        ArrayList<HoaDon> arr = hdBUS.search(5, 2021, 0, 200000,mahd);
        
        for(HoaDon bill : arr){
            assertEquals(hd.getMaHD(),bill.getMaHD());
        }
        hdBUS.delete(hd.getMaHD());
    }

    @Test
    public void testGetListWidthArray() {
    }
    
}
