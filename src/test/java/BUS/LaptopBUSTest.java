/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import DTO.Laptop;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

/**
 *
 * @author Bao Long
 */
public class LaptopBUSTest {
    LaptopBUS lpBUS;

    @Test
    public void testListSP() {
        lpBUS = new LaptopBUS();
        lpBUS.listSP();
        assertThat(lpBUS.getList(), is(not(empty())));
    }

    @Test
    public void testAddSP() {
        Laptop lp = new Laptop("1031","2002","3002","MSI GP66",100,2000,"Intel i7 10870h","16GB DDR4","Nvidia RTX 3060 6GB","15.6 inch","1TB SSD","");
        lpBUS = new LaptopBUS();
        lpBUS.listSP();
        lpBUS.addSP(lp);
        Laptop expected = lpBUS.getList().get(lpBUS.getList().size() - 1);
        assertEquals(expected.getMaLaptop(),lp.getMaLaptop());
        lpBUS.deleteSP(lp.getMaLaptop());
    }

    @Test
    public void testDeleteSP() {
        Laptop lp = new Laptop("1032","2002","3002","MSI GP66",100,2000,"Intel i7 10870h","16GB DDR4","Nvidia RTX 3060 6GB","15.6 inch","1TB SSD","");
        lpBUS = new LaptopBUS();
        lpBUS.listSP();
        lpBUS.addSP(lp);
        int expectedSize = lpBUS.getList().size() - 1;
        lpBUS.deleteSP(lp.getMaLaptop());
        assertEquals(expectedSize,lpBUS.getList().size());
        
    }

    @Test
    public void testUpdateSL() {
        Laptop lp = new Laptop("1033","2002","3002","MSI GP66",100,2000,"Intel i7 10870h","16GB DDR4","Nvidia RTX 3060 6GB","15.6 inch","1TB SSD","");
        lpBUS = new LaptopBUS();
        lpBUS.listSP();
        lpBUS.addSP(lp);
        int expectedSL = 50;
        lpBUS.updateSL(lp.getMaLaptop(),50);
        assertEquals(expectedSL,lp.getSoluong());
        lpBUS.deleteSP(lp.getMaLaptop());
    }

    @Test
    public void testUpdateSLValue() {
        Laptop lp = new Laptop("1034","2002","3002","MSI GP66",100,2000,"Intel i7 10870h","16GB DDR4","Nvidia RTX 3060 6GB","15.6 inch","1TB SSD","");
        lpBUS = new LaptopBUS();
        lpBUS.listSP();
        lpBUS.addSP(lp);
        int expectedSL = 25;
        lpBUS.updateSLValue(lp.getMaLaptop(), 25);
        assertEquals(expectedSL,lp.getSoluong());
        lpBUS.deleteSP(lp.getMaLaptop());
    }


    @Test
    public void testSearchSP() {
        Laptop lp = new Laptop("1035","2002","3002","MSI GP66",100,2000,"Intel i7 10870h","16GB DDR4","Nvidia RTX 3060 6GB","15.6 inch","1TB SSD","");
        lpBUS = new LaptopBUS();
        lpBUS.listSP();
        lpBUS.addSP(lp);
        ArrayList<Laptop> arr = lpBUS.searchSP("1035","2002", 0, 2000);
        for (Laptop l : arr){
            assertEquals(lp.getMaLaptop(),l.getMaLaptop());
        }
        lpBUS.deleteSP(lp.getMaLaptop());
    }

}
