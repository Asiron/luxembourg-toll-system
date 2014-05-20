/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author asiron
 */
public class RandomString {
    
    public static String generateAlphaNumbericString(Integer length) {
        return generateString(length, true);
    }
    
    public static String generateAlphaString(Integer length) {
        return generateString(length, false);
    }
    
    private static String generateString(Integer length, Boolean alphanumberic) {
        Random r = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        
        char letter;
        for (int i = 0; i < length; i++) {
            if (alphanumberic && r.nextFloat() < 0.5) {
                letter = (char)((int)'0' + r.nextInt(10));
            } else {
                letter = (char)((int)'A' + r.nextInt(26));
            }
            sb.append(letter);
        }
        return sb.toString();
    }
}
