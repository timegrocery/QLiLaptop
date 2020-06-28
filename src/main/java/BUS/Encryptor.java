/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

/**
 *
 * @author WindZ
 */
/*
    Creates a standard password-based bytes encryptor using 256 bit AES encryption with Galois Counter Mode (GCM)
*/
public class Encryptor {
    protected boolean has_special_privileges = false;
    private static final String PUBLIC_KEY = "LMAO"; // don't change these value unless you know what you are doing
    private static final String ADMIN_SALT = "d8e9ff17363472eb";
    private static final String USER_SALT = "ca76605932b1c183";
    
    /**
     * Registration system (experimental) - not gonna show this to teacher.
     * 
     * @param account
     * @param password
     */
    public String writePasswordToFile(String account, String password) {
        TextEncryptor encryptor;
        
        if (account.equals("admin")) {
            encryptor = Encryptors.text(PUBLIC_KEY, ADMIN_SALT);
        } else {
            encryptor = Encryptors.text(PUBLIC_KEY, USER_SALT);
        }
        String encryptedPassword = encryptor.encrypt(password);
        return encryptedPassword;
    }
    /**
     * Checks if input account is valid
     * 
     * @param account user account
     * @param password user password
     * @return false if account is not valid
     * @return true if account is valid
     */
    private boolean verifyLogin(String account, final String password) {
        String tempPath = Handler.getFullPath(String.format("accounts/admin"));
        tempPath = tempPath.replace("admin","");
        tempPath = String.format("%s%s", tempPath, account);
        if (tempPath == null) {
            return false;
        }
        TextEncryptor decryptor;
        
        if (account.equals("admin")) {
            decryptor = Encryptors.text(PUBLIC_KEY, ADMIN_SALT);
        } else {
            decryptor = Encryptors.text(PUBLIC_KEY, USER_SALT);
        }

        String lineJustFetched = null;
        try (BufferedReader buffer = new BufferedReader(new FileReader(tempPath));) {
            lineJustFetched = null;
            lineJustFetched = buffer.readLine(); // reads the first line
        } catch (final Exception e) {
            System.out.println("Wrong login credential");
            return false;
        }
        if (lineJustFetched == null) {
            return false;
        }
        if (!password.equals(decryptor.decrypt(lineJustFetched))) {
            return false;
        }
        if (account.equals("admin")) {
            this.has_special_privileges = true;
        }
        return true;
    }
    protected void inputLogin() {
        final Console console = System.console();
        String account = new String();
        System.out.println("\t\t\tLOGIN");
        System.out.println("----------------------------------------------------------\n");
        try {
            System.out.print("Username: ");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        final char[] password = console.readPassword("Password: "); // invisible password, for security issues

        if (verifyLogin(account, String.valueOf(password))) {
            if (this.has_special_privileges) {
                System.out.println("Logged in successfully as an administrator!");
            } else {
                System.out.println("Logged in successfully as an employee");
            }
        } else {
            System.out.println("Username or password is wrong!");
            inputLogin();
        }
    }
}
