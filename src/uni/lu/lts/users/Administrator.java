/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

/**
 *
 * @author asiron
 */
public class Administrator extends Account{

    public Administrator(AccountType type, String username, String password) {
        super(type, username, password);
        permissions.add(Permission.MODIFYACCOUNTS);
        permissions.add(Permission.READALL);
    }
    
}
