/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSTest.BUS;

import BUS.NhaSanXuatBUS;
import DTO.NhaSanXuat;
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
public class NhaSanXuatBUSTest {
    NhaSanXuatBUS nsxBUS;

    @Test
    public void testListNSX() {
        nsxBUS = new NhaSanXuatBUS();
        nsxBUS.listNSX();
        assertThat(nsxBUS.getList(), is(not(empty())));
    }

    @Test
    public void testAddNsx() {
        NhaSanXuat nsx = new NhaSanXuat ("2004","MSI");
        nsxBUS = new NhaSanXuatBUS();
        nsxBUS.addNsx(nsx);
        NhaSanXuat expected = nsxBUS.getList().get(nsxBUS.getList().size() - 1);
        assertEquals(expected.getMaNSX(),nsx.getMaNSX());
        nsxBUS.deleteNsx(nsx.getMaNSX());
    }

    @Test
    public void testDeleteNsx() {
        NhaSanXuat nsx = new NhaSanXuat ("2005","MSI");
        nsxBUS = new NhaSanXuatBUS();
        nsxBUS.addNsx(nsx);
        int expectedSize = nsxBUS.getList().size() - 1;
        nsxBUS.deleteNsx(nsx.getMaNSX());
        assertEquals(expectedSize,nsxBUS.getList().size());
    }

    @Test
    public void testSearchMaNsx() {
        NhaSanXuat nsx = new NhaSanXuat ("2006","MSI");
        nsxBUS = new NhaSanXuatBUS();
        nsxBUS.addNsx(nsx);
        String expected = nsx.getMaNSX();
        NhaSanXuat actual = nsxBUS.searchMaNsx(nsx.getMaNSX());
        assertEquals(expected,actual.getMaNSX());
        nsxBUS.deleteNsx(nsx.getMaNSX());
    }

    @Test
    public void testSearchNsx() {
        NhaSanXuat nsx = new NhaSanXuat ("2005","MSI");
        nsxBUS = new NhaSanXuatBUS();
        nsxBUS.addNsx(nsx);
        ArrayList<NhaSanXuat> arr = nsxBUS.searchNsx(nsx.getMaNSX(), nsx.getTenNSX());
        for(NhaSanXuat producer : arr){
            assertEquals(nsx.getMaNSX(),producer.getMaNSX());
        }
        nsxBUS.deleteNsx(nsx.getMaNSX());
    }
  
}