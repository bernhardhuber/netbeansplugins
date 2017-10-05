/*
 * WordCountTableModelTest.java
 * JUnit based test
 *
 * Created on 25. Jänner 2006, 20:59
 */

package org.huberb.nbwordcount.model;

import junit.framework.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class WordCountTableModelTest extends TestCase {
    
    public WordCountTableModelTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(WordCountTableModelTest.class);
        
        return suite;
    }

    /**
     * Test of setCounters method, of class org.huberb.nbwordcount.model.WordCountTableModel.
     * Test of getCounters method, of class org.huberb.nbwordcount.model.WordCountTableModel.
     */
    public void testSetCounters() {
        Long[] newCounters = new Long[] {
            new Long(1),
            new Long(2),
            new Long(3),
            
            new Long(4),
            new Long(5),
            new Long(6),
        };
        WordCountTableModel instance = new WordCountTableModel();
        
        instance.setCounters(newCounters);
        
        Number[] counters = instance.getCounters();
        assertEquals( 1L, counters[0].longValue() );
        assertEquals( 2L, counters[1].longValue() );
        assertEquals( 3L, counters[2].longValue() );
    }

    /**
     * Test of getRowCount method, of class org.huberb.nbwordcount.model.WordCountTableModel.
     */
    public void testGetRowCount() {        
        WordCountTableModel instance = new WordCountTableModel();
        
        int expResult = 7;
        int result = instance.getRowCount();
        assertEquals(expResult, result);        
    }

    /**
     * Test of getColumnCount method, of class org.huberb.nbwordcount.model.WordCountTableModel.
     */
    public void testGetColumnCount() {
        WordCountTableModel instance = new WordCountTableModel();
        
        int expResult = 2;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValueAt method, of class org.huberb.nbwordcount.model.WordCountTableModel.
     */
    public void testGetValueAt() {
        int rowIndex = 0;
        int columnIndex = 0;
        WordCountTableModel instance = new WordCountTableModel();
        
        Object expResult = "Characters";
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);        
    }

    /**
     * Test of getColumnName method, of class org.huberb.nbwordcount.model.WordCountTableModel.
     */
    public void testGetColumnName() {
        int columnIndex = 0;
        WordCountTableModel instance = new WordCountTableModel();
        
        String expResult = "Name";
        String result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnClass method, of class org.huberb.nbwordcount.model.WordCountTableModel.
     */
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

    public static void main(java.lang.String[] argList) {
        junit.textui.TestRunner.run(suite());
    }
    
}
