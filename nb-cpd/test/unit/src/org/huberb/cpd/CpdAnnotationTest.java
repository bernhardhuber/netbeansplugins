/*
 * CpdAnnotationTest.java
 * JUnit based test
 *
 * Created on 22. April 2007, 18:31
 */
package org.huberb.cpd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class CpdAnnotationTest {

    /**
     * Test of getAnnotationType method, of class org.huberb.cpd.CpdAnnotation.
     * Test of getShortDescription method, of class
     * org.huberb.cpd.CpdAnnotation.
     */
    @Test
    public void testGet() {
        CpdAnnotation instance = new CpdAnnotation("Info1");

        assertEquals("org-huberb-cpd-cpd-annotation", instance.getAnnotationType());
        assertNotNull(instance.getShortDescription());
    }

    /**
     * Test of equals method, of class org.huberb.cpd.CpdAnnotation.
     */
    @Test
    public void testEquals() {
        CpdAnnotation instance = new CpdAnnotation("Info1");
        CpdAnnotation instance2 = new CpdAnnotation("Info1");
        CpdAnnotation instance3 = new CpdAnnotation("Info3");

        assertEquals(instance, instance);
        assertEquals(instance, instance2);
        assertTrue(!instance.equals(instance3));
    }

    /**
     * Test of hashCode method, of class org.huberb.cpd.CpdAnnotation.
     */
    @Test
    public void testHashCode() {
        CpdAnnotation instance = new CpdAnnotation("Info1");
        CpdAnnotation instance2 = new CpdAnnotation("Info1");
        CpdAnnotation instance3 = new CpdAnnotation("Info3");

        assertTrue(instance.hashCode() == instance.hashCode());
        assertTrue(instance.hashCode() == instance2.hashCode());
        assertTrue(instance.hashCode() != instance3.hashCode());
    }

}
