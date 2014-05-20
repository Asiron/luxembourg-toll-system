/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.users;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uni.lu.lts.util.CountryCode;

/**
 *
 * @author asiron
 */
public class UtilTests {
    
    public UtilTests() {
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
    public void testCountryCode() {
        for (CountryCode cc : CountryCode.values()) {
            System.out.print(cc + " ");
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.print(CountryCode.randomCountry() + " ");
        }
    }
}
