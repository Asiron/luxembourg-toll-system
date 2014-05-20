/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

import uni.lu.lts.users.Account.AccountType;

/**
 *
 * @author asiron
 */
public class AccountFactory {
    public static Account createAccount(AccountType type, String login, String password) {
        Account retValue = null;
        switch (type) {
            case ROOT:
                retValue = new Root(type, login, password);
                break;
            case ADMIN:
                retValue = new Administrator(type, login, password);
                break;
            case PRIVILEGED:
                retValue = new PrivilegedUser(type, login, password);
                break;
            case REGULAR:
                retValue = new RegularUser(type, login, password);
                break;
            default:
                System.out.println("AccountFactory, undefined type");
                break;
        }
        return retValue;
    }
}
