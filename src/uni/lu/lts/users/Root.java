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

    public Root() {
        super("root", "root");
        permissions.add(Permission.MODIFYACCOUNTS);
        permissions.add(Permission.READALL);
    }
}
