/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Bao Long
 */
public class KhachHangDTOTest {
    
    @Test
    public void testSetMaKH() {
        KhachHang idStart0 = new KhachHang();
        idStart0.setMaKH("1002");
        String actual = idStart0.getMaKH().substring(0, 1);
        String expected = "0";
        assertEquals(expected,actual);
    }

    @Test
    public void testSetHoKH_NoSpecialChar() {
        KhachHang noSpecialChar = new KhachHang();
        noSpecialChar.setHoKH("Dương%***#&*");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(noSpecialChar.getHoKH());
        assertFalse(hasSpecial.find());
    }

    @Test
    public void testSetHoKH_NoNumber(){
        KhachHang noNumber = new KhachHang();
        noNumber.setHoKH("Đỗ Đình Bảo232312");
        Pattern special = Pattern.compile ("[0-9]");
        Matcher hasSpecial = special.matcher(noNumber.getHoKH());
        assertFalse(hasSpecial.find());
    }
    
    @Test
    public void testSetHoKH_Size(){
        int maxSize = 25;
        KhachHang sizeLimit = new KhachHang();
        sizeLimit.setHoKH("Đỗ Đình Bảooooooooooooooossabeaaaaaaaaaa");
        boolean isOverMaxSize = (sizeLimit.getHoKH().length() > maxSize);
       assertFalse(isOverMaxSize);
    }
    
    @Test
    public void testSetTenKH_NoSpecialChar() {
        KhachHang noSpecialChar = new KhachHang();
        noSpecialChar.setTenKH("$(&$*#Long");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(noSpecialChar.getTenKH());
        assertFalse(hasSpecial.find()); 
    }
    
    @Test
    public void testSetTenKH_NoNumber(){
        KhachHang noNumber = new KhachHang();
        noNumber.setTenKH("Long32321");
        Pattern digit = Pattern.compile("[0-9]");
        Matcher hasDigit = digit.matcher(noNumber.getTenKH());
        assertFalse(hasDigit.find());
    }
    
   @Test
    public void testSetTenKH_Size(){
       int maxSize = 10;
       KhachHang sizeLimit = new KhachHang();
       sizeLimit.setTenKH("Longggggggggggggg");
       boolean isOverMaxSize = (sizeLimit.getTenKH().length() > maxSize);
       assertFalse(isOverMaxSize);
   }
    
   @Test
    public void testSetSDT_NoCharacter(){
       KhachHang noCharacter = new KhachHang();
       noCharacter.setSdt("094978asdsdx");
       Pattern special = Pattern.compile ("[0-9]");
       Matcher hasCharacter = special.matcher(noCharacter.getSdt());
       assertTrue(hasCharacter.find());
   }
    @Test
    public void testSetSDT_NoSpecialCharacter(){
       KhachHang noSpecialCharacter = new KhachHang();
       noSpecialCharacter.setSdt("0949786*(*&@#");
       Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
       Matcher hasSpecial = special.matcher(noSpecialCharacter.getSdt());
       assertFalse(hasSpecial.find());
   }
    
    @Test
    public void testSetSDT_Size(){
        int maxSize = 10;
       KhachHang sizeLimit = new KhachHang();
       sizeLimit.setSdt("094978681911111111");
       boolean isOverMaxSize = (sizeLimit.getSdt().length() > maxSize);
       assertFalse(isOverMaxSize);
    }
}
