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
/*
    
*/
public class Encryptor {
    private final String PUBLIC_KEY = "Ngọc Long hehe"; // don't change these value unless you know what you are doing
    private final String SALT = "d8e9ff17363472eb";
    TextEncryptor encryptor;
    
    public Encryptor() {
        encryptor = Encryptors.text(PUBLIC_KEY, SALT);
    }

    /**
     * Registration system (experimental) - not gonna show this.
     * Creates a standard password-based bytes encryptor using 256 bit AES encryption with Galois Counter Mode (GCM)
     * The "standard" encryption method is 256-bit AES using PKCS #5's PBKDF2 (Password-Based Key Derivation Function #2)
     * The password used to generate the SecretKey should be kept in a secure place and not be shared.
     * The salt is used to prevent dictionary attacks against the key in the event your encrypted data is compromised.
     * A 16-byte random initialization vector is also applied so each encrypted message is unique.
     * 
     * @param password
     */
    public String encryptPassword(final String password) {
        String encryptedPassword = encryptor.encrypt(password);
        return encryptedPassword;
    }
}
