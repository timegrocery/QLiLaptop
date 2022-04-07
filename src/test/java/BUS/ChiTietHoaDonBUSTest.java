/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.ChiTietHoaDon;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *
 * @author Bao Long
 */
public class ChiTietHoaDonBUSTest {
    
    ChiTietHoaDonBUS cthdBUS;
    
    @Test
    public void testList() {
        cthdBUS = new ChiTietHoaDonBUS();
        cthdBUS.list();
        assertThat(cthdBUS.getList(), is(not(empty())));
    }

    @Test
    public void testAdd() {
        ChiTietHoaDon cthd = new ChiTietHoaDon("6030","1003",12,1200);
        cthdBUS = new ChiTietHoaDonBUS();
        cthdBUS.list();
        cthdBUS.add(cthd);
        ChiTietHoaDon expected = cthdBUS.getList().get(cthdBUS.getList().size() - 1);
        assertEquals(expected.getMaHD(),cthd.getMaHD());
        cthdBUS.delete(cthd.getMaHD());
    }

    @Test
    public void testDelete() {
        ChiTietHoaDon cthd = new ChiTietHoaDon("6031","1003",12,1200);
        cthdBUS = new ChiTietHoaDonBUS();
        cthdBUS.list();
        cthdBUS.add(cthd);
        int expectedSize = cthdBUS.getList().size() - 1;
        cthdBUS.delete(cthd.getMaHD());
        assertEquals(expectedSize,cthdBUS.getList().size());
    }

    @Test
    public void testSearch() {
        ChiTietHoaDon cthd = new ChiTietHoaDon("6032","1003",12,1200);
        cthdBUS = new ChiTietHoaDonBUS();
        cthdBUS.list();
        cthdBUS.add(cthd);
        ChiTietHoaDon expected = cthdBUS.search(cthd.getMaHD());
        assertEquals(expected.getMaHD(),cthd.getMaHD());
        cthdBUS.delete(cthd.getMaHD());
    }
}
