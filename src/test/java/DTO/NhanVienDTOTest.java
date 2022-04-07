/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 *
 * @author ADMIN
 */
public class NhanVienDTOTest {

    @Test
    public void testSetMaNV() {
        NhanVien idOnly4 = new NhanVien();
        idOnly4.setMaNV("5002");
        String actual = idOnly4.getMaNV().substring(0, 1);
        String expected = "4";
        assertEquals(expected,actual);
    }

    @Test
    public void testSetHoNV_NoSpecialChar() {
        NhanVien noSpecialChar = new NhanVien();
        noSpecialChar.setHoNV("Đỗ Đình Bảo%***#&*");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(noSpecialChar.getHoNV());
        assertFalse(hasSpecial.find());
    }
    
    @Test
    public void testSetHoNV_NoNumber(){
        NhanVien noNumber = new NhanVien();
        noNumber.setHoNV("Đỗ Đình Bảo232312");
        Pattern digit = Pattern.compile("[0-9]");
        Matcher hasDigit = digit.matcher(noNumber.getHoNV());
        assertFalse(hasDigit.find());
    }
    
     @Test
    public void testSetHoNV_Size(){
       int maxSize = 25;
       NhanVien sizeLimit = new NhanVien();
       sizeLimit.setHoNV("Đỗ Đình Bảooooooooooooooossabeaaaaaaaaaa");
       boolean isOverMaxSize = (sizeLimit.getHoNV().length() > maxSize);
       assertFalse(isOverMaxSize);
    }
    
    @Test
    public void testSetTenNV_NoSpecialChar() {
        NhanVien noSpecialChar = new NhanVien();
        noSpecialChar.setTenNV("$(&$*#Long");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(noSpecialChar.getTenNV());
        assertFalse(hasSpecial.find()); 
    }
    
     @Test
    public void testSetTenNV_NoNumber() {
        NhanVien noNumber = new NhanVien();
        noNumber.setTenNV("Long32321");
        Pattern digit = Pattern.compile("[0-9]");
        Matcher hasDigit = digit.matcher(noNumber.getTenNV());
        assertFalse(hasDigit.find());
    }
    
     @Test
    public void testSetTenNV_Size(){
         int maxSize = 10;
         NhanVien sizeLimit = new NhanVien();
         sizeLimit.setTenNV("Longggggggggggggg");
         boolean isOverMaxSize = (sizeLimit.getTenNV().length() > maxSize);
         assertFalse(isOverMaxSize);
    }
    
    @Test
    public void testSetDiaChi() {
        NhanVien noSpecialCharExceptLine = new NhanVien();
        noSpecialCharExceptLine.setDiaChi("xxxxd&*%&%");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~]");
        Matcher hasSpecial = special.matcher(noSpecialCharExceptLine.getDiaChi());
        assertFalse(hasSpecial.find());
    }
    
}
