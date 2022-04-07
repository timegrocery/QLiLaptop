/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import DTO.NhaCungCap;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
/**
 *
 * @author Bao Long
 */
public class NhaCungCapBUSTest {
    NhaCungCapBUS nccBUS;

    @Test
    public void testListNCC() {
        nccBUS = new NhaCungCapBUS();
        nccBUS.listNCC();
        assertThat(nccBUS.getList(), is(not(empty())));
    }
    
    @Test
    public void testAddNCC() {
        NhaCungCap ncc = new NhaCungCap("3001","Hàng chính hiệu","396-398 Nguyễn Kiệm Phường 3 Quận Phú Nhuận","18008006");
        nccBUS = new NhaCungCapBUS();
        nccBUS.listNCC();
        nccBUS.addNCC(ncc);
        NhaCungCap expected = nccBUS.getList().get(nccBUS.getList().size() - 1);
        assertEquals(expected.getMaNCC(),ncc.getMaNCC());
        nccBUS.deleteNCC(ncc.getMaNCC());
    }

    @Test
    public void testDeleteNCC() {
      NhaCungCap ncc = new NhaCungCap("3002","Hàng chính hiệu","396-398 Nguyễn Kiệm Phường 3 Quận Phú Nhuận","18008006");
      nccBUS = new NhaCungCapBUS();
      nccBUS.listNCC();
      nccBUS.addNCC(ncc);
      int expectedSize = nccBUS.getList().size() - 1;
      nccBUS.deleteNCC(ncc.getMaNCC());
      assertEquals(expectedSize,nccBUS.getList().size());
    }
    
    @Test
    public void testSearchMaNcc() {
      NhaCungCap ncc = new NhaCungCap("3003","Hàng chính hiệu","396-398 Nguyễn Kiệm Phường 3 Quận Phú Nhuận","18008006");
      nccBUS = new NhaCungCapBUS();
      nccBUS.listNCC();
      nccBUS.addNCC(ncc);
      String expected = ncc.getMaNCC();
      NhaCungCap actual = nccBUS.searchMaNcc(ncc.getMaNCC());
      assertEquals(expected,actual.getMaNCC());
      nccBUS.deleteNCC(ncc.getMaNCC());
    }

    @Test
    public void testSearchNcc() {
      NhaCungCap ncc = new NhaCungCap("3004","Hàng chính hiệu","396-398 Nguyễn Kiệm Phường 3 Quận Phú Nhuận","18008006");
      nccBUS = new NhaCungCapBUS();
      nccBUS.listNCC();
      nccBUS.addNCC(ncc);
      ArrayList<NhaCungCap> arr = nccBUS.searchNcc(ncc.getMaNCC(), ncc.getTenNCC());
      for(NhaCungCap provider : arr){
          assertEquals(ncc.getMaNCC(),provider.getMaNCC());
      }
      nccBUS.deleteNCC(ncc.getMaNCC());
    }

}
