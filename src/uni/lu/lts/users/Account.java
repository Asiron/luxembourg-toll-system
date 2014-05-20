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
	
    private AccountType type;
    private String username;	
    private String passwordHash;		
    private final byte[] salt;

    protected Set<Permission> permissions;
    
    public Account(AccountType type, String username, String password) {
    
        this.type     = type;
        this.username = username;
        this.salt     = new byte[32];
        final Random r = new SecureRandom();
        r.nextBytes(this.salt);
        
        this.passwordHash = HashUtil.computeHash(password) + HashUtil.convertBytesToString(salt);
        
        this.permissions = new HashSet<>();
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
    
    public boolean checkPermission(Permission permission) {
        return permissions.contains(permission);
    }
    
    public String seePermissions() {
        String retValue= "";
        for (Permission per : permissions) {
            retValue = retValue + per.name() + "\n";
        }
        return retValue;
    }
    
    public AccountType getType() {
        return type;
    }
    
    public static enum AccountType {
        
        ROOT ("Root"),
        ADMIN ("Administrator"),
        REGULAR ("Regular User"),
        PRIVILEGED ("Privileged User");
        
        private final String fullname;

        private AccountType(String fullname) {
            this.fullname = fullname;
        }

        @Override
        public String toString() {
            return fullname;
        }
    }
}