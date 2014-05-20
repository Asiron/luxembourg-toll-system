/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.core;

import java.util.HashMap;
import java.util.Map;
import uni.lu.lts.facility.Section;
import uni.lu.lts.users.Account;
import uni.lu.lts.users.Root;

/**
 *
 * @author asiron
 */
public class LuxembourgTollSystem {

    private Map<String, Section> sections;
    private Map<String, Account> accounts ;
    
    
    public LuxembourgTollSystem() {
        this.sections = new HashMap<>();
        this.accounts = new HashMap<>();
        this.accounts.put("root", new Root());
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
    
    public boolean login(String login, String password) {
        Account account = accounts.get(login);
        if (account != null) {
            return account.checkPassword(password);
        }
        return false;
    }
}
