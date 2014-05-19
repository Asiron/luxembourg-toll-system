package uni.lu.lts.users;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.SecureRandom;
import java.util.Random;
import uni.lu.lts.util.HashUtil;

/**
 *
 * @author asiron
 */
public class Account {
	
    private final String username;	
    private final String passwordHash;		
    private final byte[] salt;

    public Account(String username, String password) {
       
        this.username = username;
        this.salt     = new byte[32];
        final Random r = new SecureRandom();
        r.nextBytes(this.salt);
        
        this.passwordHash = HashUtil.computeHash(password) + HashUtil.convertBytesToString(salt);
    }
	
    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return passwordHash.equals(HashUtil.computeHash(password) + HashUtil.convertBytesToString(salt));
    }

}