/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author asiron
 */
public class AccountTests {
    
    public AccountTests() {
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
    public void testHash() {
        Account user = new Account("asiron", "this is password");
        
        Boolean actual = user.checkPassword("this is password");
        Boolean expected = true;
        
        Assert.assertEquals("Passwords match", expected, actual);        
     
        
        user = new Account("asiron2", "this is password");
        
        actual = user.checkPassword("bullshit");
        expected = false;
        
        Assert.assertEquals("Passwords don't match", expected, actual);   
    }
}
