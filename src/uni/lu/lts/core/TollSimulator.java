package uni.lu.lts.core;
    
import java.util.concurrent.locks.Lock;


/**
 *
 * @author asiron
 */
public class TollSimulator extends Thread {
    
    private Lock ltsLock;
    private LuxembourgTollSystem database;
    
    public TollSimulator(Lock ltsLock, LuxembourgTollSystem database) {
        this.database = database;
        this.ltsLock = ltsLock;
    }
    
    @Override
    public void run() {
        while (true) {
            
        }
    }
    
}
