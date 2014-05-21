/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.core;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author asiron
 */
public class Application {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lock ltsLock = new ReentrantLock(true);

        LuxembourgTollSystem lts = new LuxembourgTollSystem(ltsLock);
        TollSimulator ts = new TollSimulator(ltsLock, lts, true);
        ts.start();
        lts.start();
    }
}
