/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

/**
 *
 * @author asiron
 */
public class Root extends Account {

    public Root(AccountType type, String username, String password) {
        super(type, username, password);
        permissions.add(Permission.READALL);
        permissions.add(Permission.MODIFYACCOUNTS);
    }
    
}
