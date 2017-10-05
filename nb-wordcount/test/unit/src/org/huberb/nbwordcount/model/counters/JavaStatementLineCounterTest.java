/*
 * JavaStatementLineCounterTest.java
 * JUnit based test
 *
 * Created on 11. August 2006, 16:23
 */

package org.huberb.nbwordcount.model.counters;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.huberb.nbwordcount.model.counters.JavaStatementLineCounter;
import org.huberb.nbwordcount.model.counters.LineCounterIF.CounterInfo;

/**
 *
 * @author HuberB1
 */
public class JavaStatementLineCounterTest extends TestCase {
    
    public JavaStatementLineCounterTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(JavaStatementLineCounterTest.class);
        
        return suite;
    }
    
    /**
     * Test of getCountOfLines method, of class org.huberb.nbwordcount.model.counters.JavaStatementLineCounter.
     */
    public void testGetCountOfLines() {
        JavaStatementLineCounter instance = new JavaStatementLineCounter();
        
        String s;
        
        s = "abc def";
        instance.count(s);
        s = "123 456";
        instance.count(s);
        
        assertEquals( 2, instance.getCountOfCodeLines().intValue() );
    }
    
    /**
     * Test of getCountOfWhitspaceLines method, of class org.huberb.nbwordcount.model.counters.JavaStatementLineCounter.
     */
    public void testGetCountOfWhitspaceLines() {
        JavaStatementLineCounter instance = new JavaStatementLineCounter();
        
        String s;
        
        s = "abc def";
        instance.count(s);
        s = "123 456";
        instance.count(s);
        
        assertEquals( 0, instance.getCountOfWhitspaceLines().intValue() );
    }
    
    /**
     * Test of getCountOfCodeLines method, of class org.huberb.nbwordcount.model.counters.JavaStatementLineCounter.
     */
    public void testGetCountOfCodeLines() {
        JavaStatementLineCounter instance = new JavaStatementLineCounter();
        
        String s;
        
        s = "abc def";
        instance.count(s);
        s = "123 456";
        instance.count(s);
        
        assertEquals( 2, instance.getCountOfCodeLines().intValue() );
        
    }
    /**
     * Test of getCountOfCodeLines method, of class org.huberb.nbwordcount.model.counters.JavaStatementLineCounter.
     */
    public void testGetCountOfCodeLines2() {
        JavaStatementLineCounter instance = new JavaStatementLineCounter();
        
        final Object[][]samples = new Object[][] {
            new Object[] { new Long(2), new String[] {"abc", "// abc", "cde"} },
        };
        CountOfCodeLinesTester coclt = new CountOfCodeLinesTester();
        coclt.doTest( instance, samples );
    }
    /**
     * Test of getCountOfWhitspaceLines method, of class org.huberb.nbwordcount.model.counters.JavaStatementLineCounter.
     */
    public void testGetCountOfWhitspaceLines2() {
        JavaStatementLineCounter instance = new JavaStatementLineCounter();
        
        final Object[][]samples = new Object[][] {
            new Object[] { new Long(0), new String[] {"abc", "// abc", "cde"} },
            new Object[] { new Long(1), new String[] {"abc", "", "cde"} },
        };
        AbstractLinesTester coclt = new CountOfWhitspaceLinesTester();
        coclt.doTest( instance, samples );
    }
    
    /**
     * Test of count method, of class org.huberb.nbwordcount.model.counters.JavaStatementLineCounter.
     */
    public void testCount() {
        String s = "";
        JavaStatementLineCounter instance = new JavaStatementLineCounter();
        
        instance.count(s);
        
        CounterInfo ci = instance.getCounterInfo();
        assertNotNull( ci );
        assertNotNull( ci.getCounterValues() );
        assertEquals( 3, ci.getCounterValues().length );
    }
    
    /**
     * Test of getCounterInfo method, of class org.huberb.nbwordcount.model.counters.JavaStatementLineCounter.
     */
    public void testGetCounterInfo() {
        JavaStatementLineCounter instance = new JavaStatementLineCounter();
        
        CounterInfo ci = instance.getCounterInfo();
        assertNotNull( ci );
        assertNotNull( ci.getCounterValues() );
        assertEquals( 3, ci.getCounterValues().length );
    }
    
    public static void main(String[] argList) {
        TestRunner.run(suite());
    }

    // Helper class to ease testing the getCountOfXXX methods
    static abstract class AbstractLinesTester {
        public void doTest(JavaStatementLineCounter instance, Object[][]samples) {
            for (int i = 0; i < samples.length; i++ ) {
                Object []sample = samples[i];
                final String[] lines = (String[])sample[1];
                final Long expected = (Long)sample[0];
                for (int j = 0; j < lines.length; j++ ) {
                    instance.count(lines[j]);
                }
                assertIt( i, expected, instance );
            }
        }
        protected abstract void assertIt(int i, Long expected, JavaStatementLineCounter instance);
    }
    static class CountOfCodeLinesTester extends AbstractLinesTester {
        protected void assertIt(int i, Long expected, JavaStatementLineCounter instance) {
            assertEquals( expected, instance.getCountOfCodeLines() );
        }
    }
    
    static class CountOfWhitspaceLinesTester extends AbstractLinesTester {
        protected void assertIt(int i, Long expected, JavaStatementLineCounter instance) {
            assertEquals( expected, instance.getCountOfWhitspaceLines() );
        }
    }
}
