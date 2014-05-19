/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author asiron
 */
public class HashUtil {
    
    public static String computeHash(String data) {

        byte[] hash = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            hash = md.digest(data.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.out.println("No such algorithm or no encoding");
        }
        return HashUtil.convertBytesToString(hash);
    }
    
    public static String convertBytesToString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        } 
        return sb.toString();
    }
}
