/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uni.lu.lts.core.LuxembourgTollSystem;

/**
 *
 * @author asiron
 */
public class LTSTests {
    
    public LTSTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testLogin() {
        
        Lock ltsLock = new ReentrantLock(true);
        LuxembourgTollSystem lts = new LuxembourgTollSystem(ltsLock);
        
        Account actual   = lts.login("root", "root");
                
        Assert.assertTrue(actual instanceof Root);
        
        actual   = lts.login("root", "lol");
        
        Assert.assertTrue(!(actual instanceof Root));
    }
}
