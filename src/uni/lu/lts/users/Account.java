package uni.lu.lts.users;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import uni.lu.lts.util.HashUtil;

/**
 *
 * @author asiron
 */
public class Account {
	
    private String username;	
    private String passwordHash;		
    private final byte[] salt;

    protected Set<Permission> permissions;
    
    public Account(String username, String password) {
       
        this.username = username;
        this.salt     = new byte[32];
        final Random r = new SecureRandom();
        r.nextBytes(this.salt);
        
        this.passwordHash = HashUtil.computeHash(password) + HashUtil.convertBytesToString(salt);
        
        this.permissions = new HashSet<Permission>();
    }
	
    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    
    public void changePassword(String newPassword) {
        this.passwordHash = HashUtil.computeHash(newPassword) + HashUtil.convertBytesToString(salt);
    }
    
    public boolean checkPassword(String password) {
        return passwordHash.equals(HashUtil.computeHash(password) + HashUtil.convertBytesToString(salt));
    }
}