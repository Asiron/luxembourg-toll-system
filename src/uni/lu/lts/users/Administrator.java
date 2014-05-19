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

    
    
    public Administrator(String username, String password) {
        super(username, password);
        permissions.add(Permission.READALL);
        permissions.add(Permission.MODIFYACCOUNTS);
    }
    
    
    
}
