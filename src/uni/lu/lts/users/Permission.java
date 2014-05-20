/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

/**
 *
 * @author asiron
 */
public enum Permission {
    READALL ("User can read all from the toll databse"),
    READONLYSELF ("User can only read tolls from his own car"),
    MODIFYACCOUNTS ("User can modify accounts");
    
    private final String fullname;

    private Permission(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return fullname;
    }
}
