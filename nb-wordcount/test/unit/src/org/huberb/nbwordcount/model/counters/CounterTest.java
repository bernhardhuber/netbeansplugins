/*
 * CounterTest.java
 * JUnit based test
 *
 * Created on 23. Jänner 2006, 23:00
 */

package org.huberb.nbwordcount.model.counters;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 *
 * @author HuberB1
 */
public class CounterTest extends TestCase {
    
    public CounterTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(CounterTest.class);
        
        return suite;
    }
    
    /**
     * Test of reset method, of class org.huberb.nbwordcount.model.Counter.
     * Test of getCounterValue method, of class org.huberb.nbwordcount.model.Counter.
     * Test of intValue method, of class org.huberb.nbwordcount.model.Counter.
     * Test of longValue method, of class org.huberb.nbwordcount.model.Counter.
     * Test of floatValue method, of class org.huberb.nbwordcount.model.Counter.
     * Test of doubleValue method, of class org.huberb.nbwordcount.model.Counter.
     */
    public void testReset() {
        Counter instance = new Counter();
        instance.increment(100L);
        instance.reset(0L);
        
        assertEquals( 0L, instance.getCounterValue() );
        assertEquals( 0, instance.intValue() );
        assertEquals( 0L, instance.longValue() );
        assertEquals( 0.0f, instance.floatValue(), 0.001f );
        assertEquals( 0.0, instance.doubleValue(), 0.001 );
    }
    
    /**
     * Test of increment method, of class org.huberb.nbwordcount.model.Counter.
     */
    public void testIncrement() {
        long delta = 1L;
        Counter instance = new Counter();
        
        instance.increment(delta);
        assertEquals( 1L, instance.getCounterValue() );
        
        instance.increment( delta );
        assertEquals( 2L, instance.getCounterValue() );
        
        instance.increment( 100L );
        assertEquals( 102L, instance.getCounterValue() );
    }
    
    /**
     * Test of toString method, of class org.huberb.nbwordcount.model.Counter.
     */
    public void testToString() {
        Counter instance = new Counter();
        
        String expResult = "0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hashCode method, of class org.huberb.nbwordcount.model.Counter.
     */
    public void testHashCode() {
        Counter instance = new Counter();
        Counter instance2 = new Counter();
        
        assertEquals( instance.hashCode(), instance2.hashCode() );
        instance.increment( 1L );
        instance2.increment( 1L );
        assertEquals( instance.hashCode(), instance2.hashCode() );
    }
    /**
     * Test of equals method, of class org.huberb.nbwordcount.model.Counter.
     */
    public void testEquals() {
        Counter instance = new Counter();
        Counter instance2 = new Counter();
        
        assertTrue( instance.equals( instance ) );
        assertTrue( instance.equals( instance2 ) );
        assertTrue( instance2.equals( instance ) );
        assertTrue( instance2.equals( instance2 ) );
        
        instance.increment( 1L );
        instance2.increment( 1L );
        assertTrue( instance.equals( instance2 ) );
        assertTrue( instance2.equals( instance ) );
        
        instance.increment( 1L );
        assertTrue( !instance.equals( instance2 ) );
        assertTrue( !instance2.equals( instance ) );
        
        instance.increment( 99L );
        instance2.increment( 100L );
        assertTrue( instance.equals( instance2 ) );
        assertTrue( instance2.equals( instance ) );
    }
    
    public static void main(String[] argList) {
        TestRunner.run(suite());
    }
    
}
