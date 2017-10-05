/*
 * SimpleLineCounterTest.java
 * JUnit based test
 *
 * Created on 10. August 2006, 20:08
 */

package org.huberb.nbwordcount.model.counters;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.huberb.nbwordcount.model.counters.LineCounterIF.CounterInfo;

/**
 *
 * @author HuberB1
 */
public class SimpleLineCounterTest extends TestCase {
    
    public SimpleLineCounterTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(SimpleLineCounterTest.class);
        
        return suite;
    }
    
    /**
     * Test of getCountOfCharacter method, of class org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    public void testGetCountOfCharacter() {
        SimpleLineCounter instance = new SimpleLineCounter();
        
        instance.count( "abc def" );
        Number expResult = new Long(7);
        Number result = instance.getCountOfCharacter();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCountOfWords method, of class org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    public void testGetCountOfWords() {
        SimpleLineCounter instance = new SimpleLineCounter();
        
        instance.count("abc def");
        Number expResult = new Long(2);
        Number result = instance.getCountOfWords();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of getCountOfLines method, of class org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    public void testGetCountOfLines() {
        
        SimpleLineCounter instance = new SimpleLineCounter();
        
        instance.count("abc def");
        instance.count("123 456");
        Number expResult = new Long(2);
        Number result = instance.getCountOfLines();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of count method, of class org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    public void testCount() {
        String line = "";
        SimpleLineCounter instance = new SimpleLineCounter();
        
        instance.count(line);
    }
    
    /**
     * Test of getCounterInfo method, of class org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    public void testGetCounterInfo() {
        SimpleLineCounter instance = new SimpleLineCounter();
        
        CounterInfo result = instance.getCounterInfo();
        assertNotNull(result);
        assertNotNull(result.getCounterValues());
        assertEquals(3, result.getCounterValues().length);
    }
    
    public static void main(String[] argList) {
        TestRunner.run(suite());
    }
}
