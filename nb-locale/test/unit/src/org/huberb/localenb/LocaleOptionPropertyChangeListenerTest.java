/*
 * LocaleOptionPropertyChangeListenerTest.java
 * JUnit based test
 *
 * Created on 19. Mai 2007, 10:30
 */
package org.huberb.localenb;

import java.beans.PropertyChangeEvent;
import java.util.Locale;
import org.huberb.localenb.options.LocaleOption;
import org.huberb.localenb.ui.FormatCommandInterface;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class LocaleOptionPropertyChangeListenerTest {

    private MyFormatCommandInterfaceImpl[] fci;
    private LocaleOptionPropertyChangeListener instance;

    @Before
    public void setUp() throws Exception {
        fci = new MyFormatCommandInterfaceImpl[]{
            new MyFormatCommandInterfaceImpl(),
            new MyFormatCommandInterfaceImpl(),
            new MyFormatCommandInterfaceImpl()
        };
        instance = new LocaleOptionPropertyChangeListener(fci);
    }

    @After
    public void tearDown() throws Exception {
        fci = null;
        instance = null;
    }

    /**
     * Test of propertyChange method, of class
     * org.huberb.localenb.LocaleOptionPropertyChangeListener.
     * <p>
     * Test intersting property change
     */
    @Test
    public void testPropertyChange() {
        Object src = this;
        String[] names = {
            LocaleOption.PROPERTY_DATE_PATTERN_LIST,
            LocaleOption.PROPERTY_NUMBER_PATTERN_LIST,
            LocaleOption.PROPERTY_MESSAGE_PATTERN_LIST,};
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            String[] oldValue = null;
            String[] newValue = {
                "A", "B", "C",};
            PropertyChangeEvent evt = new PropertyChangeEvent(src, name, oldValue, newValue);
            instance.propertyChange(evt);
        }

        for (int i = 0; i < this.fci.length; i++) {
            MyFormatCommandInterfaceImpl mfcii = this.fci[i];
            String[] result = mfcii.getPatterns();
            assertEquals("A", result[0]);
            assertEquals("B", result[1]);
            assertEquals("C", result[2]);
        }
    }

    /**
     * Test of propertyChange method, of class
     * org.huberb.localenb.LocaleOptionPropertyChangeListener.
     * <p>
     * Test non-intersting property change
     */
    @Test
    public void testPropertyChange2() {
        Object src = this;
        String[] names = {
            "NoInterstingPropertyName",};
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            String oldValue = null;
            Integer newValue = new Integer(10);

            PropertyChangeEvent evt = new PropertyChangeEvent(src, name, oldValue, newValue);
            instance.propertyChange(evt);
        }

        for (int i = 0; i < this.fci.length; i++) {
            MyFormatCommandInterfaceImpl mfcii = this.fci[i];
            String[] result = mfcii.getPatterns();
            assertNull(result);
        }
    }

    static class MyFormatCommandInterfaceImpl implements FormatCommandInterface {

        private String[] patterns;

        public void format(Locale selectedLocale) {
        }

        public void setPatterns(String[] patterns) {
            this.patterns = patterns;
        }

        public String[] getPatterns() {
            return this.patterns;
        }

        public Object getState() {
            return null;
        }

        public void setState(Object newState) {
        }

    }
}
