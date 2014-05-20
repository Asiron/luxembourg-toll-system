/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import uni.lu.lts.facility.Section;
import uni.lu.lts.users.Account;
import uni.lu.lts.users.Account.AccountType;
import uni.lu.lts.users.AccountFactory;
import uni.lu.lts.users.Permission;

/**
 *
 * @author asiron
 */
public class LuxembourgTollSystem {

    private Lock ltsLock;
    private Map<String, Section> sections;
    private Map<String, Account> accounts ;
    
    private Account loggedIn = null;
    
    
    public LuxembourgTollSystem(Lock ltsLock) {
        this.ltsLock = ltsLock;
        this.sections = new HashMap<>();
        this.accounts = new HashMap<>();
        this.accounts.put("root", AccountFactory.createAccount(Account.AccountType.ROOT, "root", "root"));
    }
    
    /**
     * Get the value of sections
     *
     * @return the value of sections
     */
    public Map<String, Section> getSections() {
        return sections;
    }

    /**
     * Set the value of sections
     *
     * @param sections new value of sections
     */
    public void setSections(Map<String, Section> sections) {
        this.sections = sections;
    }
    
    public Account login(String login, String password) {
        Account account = accounts.get(login);
        if (account != null && account.checkPassword(password)) {
            System.out.println("Successfully logged in");
            return account;
        }
        return null;
    }
    
    public void logout() {
        loggedIn = null;
        System.out.println("Successfully logged out");
    }
    
    public void createAccount(String type, String login, String password) {
        
        if (accounts.containsKey(login)) {
            System.out.println("Counldn't create account, there already exists one account with the same login");
            return;
        }
        
        AccountType accountType;
        try {  
            accountType = AccountType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException iae) {
            System.out.println("Wrong account type");
            return;
        }
        
        Account account = AccountFactory.createAccount(accountType, login, password);
        if (account == null) {
            System.out.println("Counldn't create account, there was some problem");
            return;
        }
        accounts.put(login, account);
        System.out.println("Successfully created account");
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String line;
        while(true) {
            System.out.println("");
            System.out.print(">>");
            line = scanner.nextLine();
            String[] tokens = line.trim().split("\\s+");
            
            if (tokens[0].equals("login") && loggedIn == null) {
                loggedIn = login(tokens[1], tokens[2]);
                continue;
            } else if (tokens[0].equals("login") && loggedIn != null) {
                System.out.println("You are already logged in!");
                continue;
            }
            
            if (tokens[0].equals("logout") && loggedIn != null) {
                logout();
                continue;
            } else if (tokens[0].equals("logout") && loggedIn == null) {
                System.out.println("You are not logged in!");
                continue;
            }
            
            if (tokens[0].equals("create") && loggedIn != null &&
                loggedIn.checkPermission(Permission.MODIFYACCOUNTS))
            {
                String accountType = tokens[1];
                String login       = tokens[2];
                String password    = tokens[3];
                
                createAccount(accountType, login, password);
                continue;
            } else if ((tokens[0].equals("create") && loggedIn == null) ||
                       (tokens[0].equals("create") && !loggedIn.checkPermission(Permission.MODIFYACCOUNTS)))
            {
                System.out.println("Cannot create accounts without permissions");
                continue;
            }
        }
    }
    
    private void printTokens(String[] tokens) {
        for (String string : tokens) {
            System.out.print("<" + string + ">");
        }
    }
}
