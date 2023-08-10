/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author HaPi
 */
public class BCryptCoder {
    
    public static String encoder(String input) {
         String salt = "$2a$10$rBbHM187S9d2/pbh5zuu5e";
        String encoded = BCrypt.hashpw(input, salt);
        return encoded;
    }
    
    public static boolean isMatched(String password,String encryptedPassword) {
        return BCrypt.checkpw(password,encryptedPassword );
    }
    
    
    public static void main(String[] args) {
        String password = "fun123";
        String salt = BCrypt.gensalt();
        String encoded = BCrypt.hashpw(password, salt);
        boolean valuate = BCrypt.checkpw(password, encoded);
        System.out.println("salt: " + salt);
        System.out.println("encoded:" + encoded);
        System.out.println(valuate);
    }
}
