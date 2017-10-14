/*
 * CpdSettingsTest.java
 * JUnit based test
 *
 * Created on 01. Juni 2007, 21:13
 */
package org.huberb.cpd.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class CpdSettingsTest {

    /**
     * Test of displayName method, of class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testDisplayName() {
        CpdSettings instance = new CpdSettings();

        String result = instance.displayName();
        assertTrue(result != null);
    }

    /**
     * Test of initialize method, of class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testInitialize() {
        CpdSettings instance = new CpdSettings();
        instance.initialize();
    }

    /**
     * Test of getDefault method, of class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testGetDefault() {
        CpdSettings expResult = null;
        CpdSettings result = CpdSettings.getDefault();
        assertNotNull(result);
    }

    /**
     * Test of getIgnoreIdentifiers method, of class
     * org.huberb.cpd.options.CpdSettings. Test of setIgnoreIdentifiers method,
     * of class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testGetIgnoreIdentifiers() {
        CpdSettings instance = new CpdSettings();

        Boolean newValue = true;
        instance.setIgnoreIdentifiers(newValue);
        assertEquals(newValue, instance.getIgnoreIdentifiers());

        newValue = false;
        instance.setIgnoreIdentifiers(newValue);
        assertEquals(newValue, instance.getIgnoreIdentifiers());
    }

    /**
     * Test of getIgnoreLiterals method, of class
     * org.huberb.cpd.options.CpdSettings. Test of setIgnoreLiterals method, of
     * class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testGetIgnoreLiterals() {
        CpdSettings instance = new CpdSettings();

        Boolean newValue = true;
        instance.setIgnoreLiterals(newValue);
        assertEquals(newValue, instance.getIgnoreLiterals());

        newValue = false;
        instance.setIgnoreLiterals(newValue);
        assertEquals(newValue, instance.getIgnoreLiterals());
    }

    /**
     * Test of getLanguage method, of class org.huberb.cpd.options.CpdSettings.
     * Test of setLanguage method, of class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testGetLanguage() {
        CpdSettings instance = new CpdSettings();

        String expResult = "XXX";
        instance.setLanguage(expResult);
        assertEquals(expResult, instance.getLanguage());
    }

    /**
     * Test of getLanguageExt method, of class
     * org.huberb.cpd.options.CpdSettings. Test of setLanguageExt method, of
     * class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testGetLanguageExt() {
        CpdSettings instance = new CpdSettings();

        String expResult = "AAA";
        instance.setLanguageExt(expResult);
        assertEquals(expResult, instance.getLanguageExt());
    }

    /**
     * Test of getMinimalTokenCount method, of class
     * org.huberb.cpd.options.CpdSettings. Test of setMinimalTokenCount method,
     * of class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testGetMinimalTokenCount() {
        CpdSettings instance = new CpdSettings();

        Integer expResult = 10;
        instance.setMinimalTokenCount(expResult);
        assertEquals(expResult, instance.getMinimalTokenCount());
    }

    /**
     * Test of getRecursivly method, of class
     * org.huberb.cpd.options.CpdSettings. Test of setRecursivly method, of
     * class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testGetRecursivly() {
        CpdSettings instance = new CpdSettings();

        Boolean expResult = true;
        instance.setRecursivly(expResult);
        assertEquals(expResult, instance.getRecursivly());

        expResult = false;
        instance.setRecursivly(expResult);
        assertEquals(expResult, instance.getRecursivly());
    }

    /**
     * Test of getRendererEnum method, of class
     * org.huberb.cpd.options.CpdSettings. Test of setRendererEnum method, of
     * class org.huberb.cpd.options.CpdSettings.
     */
    @Test
    public void testGetRendererEnum() {
        CpdSettings instance = new CpdSettings();

        CpdSettings.RendererEnum[] values = CpdSettings.RendererEnum.values();
        for (int i = 0; i < values.length; i++) {
            CpdSettings.RendererEnum expResult = values[i];
            instance.setRendererEnum(expResult);
            CpdSettings.RendererEnum result = instance.getRendererEnum();
            assertEquals(expResult, result);
        }
    }

}
