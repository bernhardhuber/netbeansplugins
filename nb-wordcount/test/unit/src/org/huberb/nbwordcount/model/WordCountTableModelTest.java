/*
 * WordCountTableModelTest.java
 * JUnit based test
 *
 * Created on 25. Jänner 2006, 20:59
 */
package org.huberb.nbwordcount.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HuberB1
 */
public class WordCountTableModelTest {

    /**
     * Test of setCounters method, of class
     * org.huberb.nbwordcount.model.WordCountTableModel. Test of getCounters
     * method, of class org.huberb.nbwordcount.model.WordCountTableModel.
     */
    @Test
    public void testSetCounters() {
        Long[] newCounters = new Long[]{1L, 2L, 3L, 4L, 5L, 6L,};
        WordCountTableModel instance = new WordCountTableModel();

        instance.setCounters(newCounters);

        Number[] counters = instance.getCounters();
        assertEquals(1L, counters[0].longValue());
        assertEquals(2L, counters[1].longValue());
        assertEquals(3L, counters[2].longValue());
    }

    /**
     * Test of getRowCount method, of class
     * org.huberb.nbwordcount.model.WordCountTableModel.
     */
    @Test
    public void testGetRowCount() {
        WordCountTableModel instance = new WordCountTableModel();

        int expResult = 7;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnCount method, of class
     * org.huberb.nbwordcount.model.WordCountTableModel.
     */
    @Test
    public void testGetColumnCount() {
        WordCountTableModel instance = new WordCountTableModel();

        int expResult = 2;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValueAt method, of class
     * org.huberb.nbwordcount.model.WordCountTableModel.
     */
    @Test
    public void testGetValueAt() {
        int rowIndex = 0;
        int columnIndex = 0;
        WordCountTableModel instance = new WordCountTableModel();

        Object expResult = "Characters";
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnName method, of class
     * org.huberb.nbwordcount.model.WordCountTableModel.
     */
    @Test
    public void testGetColumnName() {
        int columnIndex = 0;
        WordCountTableModel instance = new WordCountTableModel();

        String expResult = "Name";
        String result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnClass method, of class
     * org.huberb.nbwordcount.model.WordCountTableModel.
     */
    @Test
    public void testGetColumnClass() {
        WordCountTableModel instance = new WordCountTableModel();

        int columnIndex = 0;

        Class expResult = String.class;
        Class result = instance.getColumnClass(columnIndex);
        assertEquals(expResult, result);

        columnIndex = 1;
        expResult = Number.class;
        result = instance.getColumnClass(columnIndex);
        assertEquals(expResult, result);
    }
}
