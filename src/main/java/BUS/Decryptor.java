/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

/**
 *
 * @author WindZ
 */
public class Decryptor {
    private final String PUBLIC_KEY = "Ngọc Long hehe"; // don't change these value unless you know what you are doing
    private final String SALT = "d8e9ff17363472eb";
    TextEncryptor decryptor;

    public Decryptor() {
        decryptor = Encryptors.text(PUBLIC_KEY, SALT);
    }
    
    /**
     * 
     * @param password user password
     * @return decrypted password
     */
    public String decryptPassword(final String password) {
        decryptor = Encryptors.text(PUBLIC_KEY, SALT);
        return decryptor.decrypt(password);
    }
}
