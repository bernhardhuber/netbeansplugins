/*
 * SimpleLineCounterTest.java
 * JUnit based test
 *
 * Created on 10. August 2006, 20:08
 */
package org.huberb.nbwordcount.model.counters;

import org.huberb.nbwordcount.model.counters.LineCounterIF.CounterInfo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HuberB1
 */
public class SimpleLineCounterTest {

    /**
     * Test of getCountOfCharacter method, of class
     * org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    @Test
    public void testGetCountOfCharacter() {
        SimpleLineCounter instance = new SimpleLineCounter();

        instance.count("abc def");
        Number expResult = new Long(7);
        Number result = instance.getCountOfCharacter();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountOfWords method, of class
     * org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    @Test
    public void testGetCountOfWords() {
        SimpleLineCounter instance = new SimpleLineCounter();

        instance.count("abc def");
        Number expResult = new Long(2);
        Number result = instance.getCountOfWords();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCountOfLines method, of class
     * org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    @Test
    public void testGetCountOfLines() {

        SimpleLineCounter instance = new SimpleLineCounter();

        instance.count("abc def");
        instance.count("123 456");
        Number expResult = new Long(2);
        Number result = instance.getCountOfLines();
        assertEquals(expResult, result);
    }

    /**
     * Test of count method, of class
     * org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    @Test
    public void testCount() {
        String line = "";
        SimpleLineCounter instance = new SimpleLineCounter();

        instance.count(line);
    }

    /**
     * Test of getCounterInfo method, of class
     * org.huberb.nbwordcount.model.counters.SimpleLineCounter.
     */
    @Test
    public void testGetCounterInfo() {
        SimpleLineCounter instance = new SimpleLineCounter();

        CounterInfo result = instance.getCounterInfo();
        assertNotNull(result);
        assertNotNull(result.getCounterValues());
        assertEquals(3, result.getCounterValues().length);
    }
}
