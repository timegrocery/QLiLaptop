/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

/**
 *
 * @author WindZ
 */
public class test {
    public static void main(String args[]) {
        String file;
        file = Handler.getFullPath("report/bill"+"1234"+".pdf");
        System.out.println(file);
    }
}
