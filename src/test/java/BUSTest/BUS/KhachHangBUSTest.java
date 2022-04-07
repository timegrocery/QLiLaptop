/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSTest.BUS;

import BUS.KhachHangBUS;
import DTO.KhachHang;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *
 * @author Bao Long
 */
public class KhachHangBUSTest {
    KhachHangBUS khBUS;
    @Test
    public void testList() {
        khBUS = new KhachHangBUS();
        khBUS.list();
        assertThat(khBUS.getList(), is(not(empty())));
    }

    @Test
    public void testAdd() {
        KhachHang kh = new KhachHang("0031","Đỗ Đình","Bảo Long","0949786819");
        khBUS = new KhachHangBUS();
        khBUS.list();
        khBUS.add(kh);
        KhachHang expected = khBUS.getList().get(khBUS.getList().size() - 1);
        assertEquals(expected.getMaKH(),kh.getMaKH());
        khBUS.delete(kh.getMaKH());
    }

    @Test
    public void testDelete() {
        KhachHang kh = new KhachHang("0032","Đỗ Đình","Bảo Long","0949786819");
        khBUS = new KhachHangBUS();
        khBUS.list();
        khBUS.add(kh);
        int expectedSize = khBUS.getList().size() - 1;
        khBUS.delete(kh.getMaKH());
        assertEquals(expectedSize,khBUS.getList().size());
    }
    
    @Test
    public void testSearch() {
        KhachHang kh = new KhachHang("0033","Đỗ Đình","Bảo Long","0949786819");
        khBUS = new KhachHangBUS();
        khBUS.list();
        khBUS.add(kh);
        ArrayList<KhachHang> arr = khBUS.search(kh.getMaKH(), kh.getHoKH(), kh.getTenKH());
        for(KhachHang cus : arr){
            assertEquals(kh.getMaKH(),cus.getMaKH());
        }
        khBUS.delete(kh.getMaKH());
    }
}
