/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import DTO.NhapHang;
import java.util.ArrayList;
import java.util.Calendar;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
/**
 *
 * @author Bao Long
 */
public class NhapHangBUSTest {
    
    NhapHangBUS nhBUS;

    @Test
    public void testList() {
        nhBUS = new NhapHangBUS();
        nhBUS.list();
        assertThat(nhBUS.getList(), is(not(empty())));
    }

    @Test
    public void testAddNh() {
        NhapHang nh = new NhapHang("7034","3002","4002","2021-04-24","1016",5,2750,13750);
        nhBUS = new NhapHangBUS();
        nhBUS.list();
        nhBUS.add(nh);
        NhapHang expected = nhBUS.getList().get(nhBUS.getList().size() - 1);
        assertEquals(expected.getMaPN(),nh.getMaPN());
        nhBUS.delete(nh.getMaPN());
    }

    @Test
    public void testDelete() {
        NhapHang nh = new NhapHang("7035","3002","4002","2021-04-24","1016",5,2750,13750);
        nhBUS = new NhapHangBUS();
        nhBUS.list();
        nhBUS.add(nh);
        int expectedSize = nhBUS.getList().size() - 1;
        nhBUS.delete(nh.getMaPN());
        assertEquals(expectedSize,nhBUS.getList().size());
    }

    @Test
    public void testListTime() {
        
        NhapHang nh1 = new NhapHang("7036","3002","4002","2021-04-24","1016",5,2750,13750);
        NhapHang nh2 = new NhapHang("7037","3002","4002","2021-05-01","1016",5,2750,13750);
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.set(2021, 01, 01);
        to.set(2021, 12, 30);
        int expectedSize = 2;
        nhBUS = new NhapHangBUS();
        nhBUS.list();
        nhBUS.add(nh1);
        nhBUS.add(nh2);
        ArrayList<NhapHang> arr = nhBUS.ListTime(from, to);
        assertEquals(expectedSize,arr.size());
        
        nhBUS.delete(nh1.getMaPN());
        nhBUS.delete(nh2.getMaPN());

    }
   
}
