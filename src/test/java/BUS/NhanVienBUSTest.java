/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.NhanVien;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;


/**
 *
 * @author Bao Long
 */
public class NhanVienBUSTest {
    
    NhanVienBUS nvBUS;
    
    public NhanVienBUSTest() {
    }

    @Test
    public void testListNV() {
        nvBUS = new NhanVienBUS();
        nvBUS.listNV();
        assertThat(nvBUS.getList(), is(not(empty())));
    }

    @Test
    public void testAddNV() {
        NhanVien nv = new NhanVien("4012","Hoàng","Ngọc Long","Nam","abcs",2000,"");
        nvBUS = new NhanVienBUS();
        nvBUS.listNV();
        nvBUS.addNV(nv);
        NhanVien expected = nvBUS.getList().get(nvBUS.getList().size() - 1);
        assertEquals(expected.getMaNV(),nv.getMaNV());
        nvBUS.deleteNV(nv.getMaNV());
    }

    @Test
    public void testDeleteNV() {
        NhanVien nv = new NhanVien("4013","Hoàng","Ngọc Long","Nam","abcs",2000,"");
        nvBUS = new NhanVienBUS();
        nvBUS.listNV();
        nvBUS.addNV(nv);
        int expectedSize = nvBUS.getList().size() - 1;
        nvBUS.deleteNV(nv.getMaNV());
        assertEquals(expectedSize,nvBUS.getList().size());
    }

    @Test
    public void testSearch() {
        NhanVien nv = new NhanVien("4014","Hoàng","Ngọc Long","Nam","abcs",2000,"");
        nvBUS = new NhanVienBUS();
        nvBUS.listNV();
        nvBUS.addNV(nv);
        ArrayList<NhanVien> arr = nvBUS.search(nv.getMaNV(), nv.getHoNV(),nv.getTenNV(), nv.getPhai());
        for(NhanVien staff : arr){
          assertEquals(nv.getMaNV(),staff.getMaNV());
        }
        nvBUS.deleteNV(nv.getMaNV());
    }   
}