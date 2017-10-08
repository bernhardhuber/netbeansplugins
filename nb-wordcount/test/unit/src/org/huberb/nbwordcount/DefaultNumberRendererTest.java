/*
 * DefaultNumberRendererTest.java
 * JUnit based test
 *
 * Created on 25. Jänner 2006, 21:42
 */
package org.huberb.nbwordcount;

import java.util.Locale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HuberB1
 */
public class DefaultNumberRendererTest {

    private Locale theJVMDefaultLocale;

    @Before
    public void setUp() throws Exception {
        this.theJVMDefaultLocale = Locale.getDefault();
    }

    @After
    public void tearDown() throws Exception {
        Locale.setDefault(this.theJVMDefaultLocale);
    }

    /**
     * Test of setValue method, of class
     * org.huberb.nbwordcount.DefaultNumberRenderer.
     */
    @Test
    public void testSetValue() {
        Locale.setDefault(Locale.GERMAN);
        DefaultNumberRenderer instance = new DefaultNumberRenderer();

        Object value = new Double(1.12);
        instance.setValue(value);
        assertEquals("1,12", instance.getText());

        value = new Double(1.4);
        instance.setValue(value);
        assertEquals("1,4", instance.getText());

        value = new Double(1.1234567890123);
        instance.setValue(value);
        assertEquals("1,12", instance.getText());
    }

    /**
     * Test of setValue method, of class
     * org.huberb.nbwordcount.DefaultNumberRenderer.
     */
    @Test
    public void testSetValueLong() {
        Locale.setDefault(Locale.GERMAN);
        DefaultNumberRenderer instance = new DefaultNumberRenderer();

        Object value;

        value = new Long(12);
        instance.setValue(value);
        assertEquals("12", instance.getText());
    }

    /**
     * Test of setPattern method, of class
     * org.huberb.nbwordcount.DefaultNumberRenderer. Test of getPattern method,
     * of class org.huberb.nbwordcount.DefaultNumberRenderer.
     */
    @Test
    public void testSetPattern() {
        String newPattern = "0.000";

        Locale.setDefault(Locale.GERMAN);
        DefaultNumberRenderer instance = new DefaultNumberRenderer();

        instance.setPattern(newPattern);

        assertEquals(newPattern, instance.getPattern());

        instance.setValue(new Double(1.1));
        assertEquals("1,100", instance.getText());
    }
}
