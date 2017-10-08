/*
 * HistoryBeanTest.java
 * JUnit based test
 *
 * Created on 17. September 2006, 11:26
 */
package org.huber.keytool.model;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author HuberB1
 */
public class HistoryBeanTest {

    private HistoryBean<String> instance;

    @Before
    public void setUp() throws Exception {

        this.instance = new HistoryBean<String>();
    }

    /**
     * Test of getMaxSize method, of class org.huber.keytool.model.HistoryBean.
     * Test of setMaxSize method, of class org.huber.keytool.model.HistoryBean.
     */
    @Test
    public void testGetMaxSize() {
        int newMaxSize = 100;
        instance.setMaxSize(newMaxSize);

        int result = instance.getMaxSize();
        assertEquals(newMaxSize, result);
    }

    /**
     * Test of add method, of class org.huber.keytool.model.HistoryBean.
     */
    @Test
    public void testAdd() {
        String s1 = "s1";

        instance.setMaxSize(2);

        instance.add(s1);
        assertEquals(s1, instance.getHistory().get(0));

        String s2 = "s2";
        instance.add(s2);
        assertEquals(s2, instance.getHistory().get(0));

        String s3 = "s3";
        instance.add(s3);
        assertEquals(s3, instance.getHistory().get(0));
        assertEquals(s2, instance.getHistory().get(1));
        assertEquals(2, instance.getMaxSize());
        assertEquals(2, instance.getHistory().size());
    }

    /**
     * Test of getHistory method, of class org.huber.keytool.model.HistoryBean.
     */
    @Test
    public void testGetHistory() {

        instance.add("A1");
        instance.add("A0");
        List<String> result = instance.getHistory();

        assertEquals("A0", result.get(0));
        assertEquals("A1", result.get(1));
    }

}
