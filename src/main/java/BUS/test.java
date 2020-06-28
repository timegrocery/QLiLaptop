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
        String password = "123";
        System.out.println("Password: " + password);
        Encryptor enc = new Encryptor();
        String encPassword = enc.encryptPassword(password);
        System.out.println("Ecnrypted password: " + encPassword);
        Decryptor dnc = new Decryptor();
        try {
            String dncPassword = dnc.decryptPassword(encPassword);
            System.out.println("Decrypted password: " + dncPassword);
        } catch (Exception e){ 
            System.err.println("Public key doesn't match!");
        }
    }
}
