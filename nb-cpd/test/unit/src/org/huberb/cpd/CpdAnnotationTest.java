/*
 * CpdAnnotationTest.java
 * JUnit based test
 *
 * Created on 22. April 2007, 18:31
 */

package org.huberb.cpd;

import junit.framework.*;

/**
 *
 * @author HuberB1
 */
public class CpdAnnotationTest extends TestCase {
    
    public CpdAnnotationTest(String testName) {
        super(testName);
    }

    /**
     * Test of getAnnotationType method, of class org.huberb.cpd.CpdAnnotation.
     * Test of getShortDescription method, of class org.huberb.cpd.CpdAnnotation.
     */
    public void testGet() {
        CpdAnnotation instance = new CpdAnnotation("Info1");
        
        assertEquals( "org-huberb-cpd-cpd-annotation", instance.getAnnotationType() );
        assertNotNull( instance.getShortDescription() );
    }

    /**
     * Test of equals method, of class org.huberb.cpd.CpdAnnotation.
     */
    public void testEquals() {
        CpdAnnotation instance = new CpdAnnotation("Info1");
        CpdAnnotation instance2 = new CpdAnnotation("Info1");
        CpdAnnotation instance3 = new CpdAnnotation("Info3");
        
        assertEquals( instance, instance );
        assertEquals( instance, instance2 );
        assertTrue( !instance.equals(instance3) );
    }

    /**
     * Test of hashCode method, of class org.huberb.cpd.CpdAnnotation.
     */
    public void testHashCode() {
        CpdAnnotation instance = new CpdAnnotation("Info1");
        CpdAnnotation instance2 = new CpdAnnotation("Info1");
        CpdAnnotation instance3 = new CpdAnnotation("Info3");
        
        assertTrue( instance.hashCode() == instance.hashCode() );
        assertTrue( instance.hashCode() == instance2.hashCode() );
        assertTrue( instance.hashCode() != instance3.hashCode() );
    }
    
}
