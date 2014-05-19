/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.core;

/**
 *
 * @author asiron
 */
public class TollSimulator extends Thread {
    
    private LuxembourgTollSystem database;
    
    public TollSimulator(LuxembourgTollSystem database) {
        this.database = database;
    }
    
    @Override
    public void run() {
        
    }
    
}
