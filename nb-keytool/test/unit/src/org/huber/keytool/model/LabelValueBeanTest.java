/*
 * LabelValueBeanTest.java
 * JUnit based test
 *
 * Created on 17. September 2006, 09:27
 */

package org.huber.keytool.model;

import junit.framework.*;
import java.io.Serializable;

/**
 *
 * @author HuberB1
 */
public class LabelValueBeanTest extends TestCase {
    
    private LabelValueBean<Long> instance;
    public LabelValueBeanTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        this.instance = new LabelValueBean<Long>("l", new Long(100) );
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(LabelValueBeanTest.class);
        
        return suite;
    }
    
    /**
     * Test of setLabel method, of class org.huber.keytool.model.LabelValueBean.
     * Test of getLabel method, of class org.huber.keytool.model.LabelValueBean.
     */
    public void testSetLabel() {
        String newLabel = "n";
        instance.setLabel(newLabel);
        
        assertEquals( newLabel, instance.getLabel() );
        assertEquals( "n", instance.getLabel() );
    }
    
    /**
     * Test of setValue method, of class org.huber.keytool.model.LabelValueBean.
     * Test of getValue method, of class org.huber.keytool.model.LabelValueBean.
     */
    public void testSetValue() {
        
        Long newValue = new Long(200L);
        instance.setValue(newValue);
        
        assertEquals( newValue, instance.getValue() );
        assertEquals( new Long(200L), instance.getValue() );
    }
    
    /**
     * Test of setLabelAndValue method, of class org.huber.keytool.model.LabelValueBean.
     */
    public void testSetLabelAndValue() {
        String newLabel = "N";
        Long newValue = new Long(1000);
        
        instance.setLabelAndValue(newLabel, newValue);
        
        assertEquals( "N", instance.getLabel() );
        assertEquals( new Long(1000), instance.getValue() );
    }
    
    /**
     * Test of toString method, of class org.huber.keytool.model.LabelValueBean.
     */
    public void testToString() {
        String result = instance.toString();
        assertNotNull( result );
    }
    
    /**
     * Test of hashCode method, of class org.huber.keytool.model.LabelValueBean.
     */
    public void testHashCode() {
        final LabelValueBean other = new LabelValueBean(
                instance.getLabel(), instance.getValue() );
        
        int expResult = other.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class org.huber.keytool.model.LabelValueBean.
     */
    public void testEquals() {
        final LabelValueBean other = new LabelValueBean(
                instance.getLabel(), instance.getValue() );
        
        boolean result;
        
        result = instance.equals(other);
        assertEquals(true, result);
        
        other.setLabel( "XXX" );
        result = instance.equals( other );
        assertEquals( true, result );
        
        other.setLabel( instance.getLabel() );
        other.setValue( new Long(-1000) );
        result = instance.equals( other );
        assertEquals( false, result );
    }
    
}
