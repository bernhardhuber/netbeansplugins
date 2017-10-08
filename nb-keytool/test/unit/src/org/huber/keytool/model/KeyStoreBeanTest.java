/*
 * KeyStoreBeanTest.java
 * JUnit based test
 *
 * Created on 17. September 2006, 11:56
 */
package org.huber.keytool.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.KeyStore;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author HuberB1
 */
public class KeyStoreBeanTest {

    private KeyStoreBean instance;

    @Before
    public void setUp() throws Exception {

        this.instance = new KeyStoreBean();
    }

    /**
     * Test of setKeyStoreValues method, of class
     * org.huber.keytool.model.KeyStoreBean. Test of getName method, of class
     * org.huber.keytool.model.KeyStoreBean.
     */
    @Test
    public void testSetKeyStoreValues() {
        String newName = "newName";
        KeyStore newKeyStore = null;
        char[] newPassword = {'a', 'b', 'c'};

        instance.setKeyStoreValues(newName, newKeyStore, newPassword);

        assertEquals("newName", instance.getName());
        assertEquals(null, instance.getKeyStore());
        assertEquals(newPassword, instance.getStorePassword());
    }

    /**
     * Test of isKeyStoreLoaded method, of class
     * org.huber.keytool.model.KeyStoreBean.
     */
    @Test
    public void testIsKeyStoreLoaded() {
        boolean expResult = false;
        boolean result = instance.isKeyStoreLoaded();
        assertEquals(expResult, result);
    }

    /**
     * Test of addPropertyChangeListener method, of class
     * org.huber.keytool.model.KeyStoreBean.
     */
    @Test
    public void testAddPropertyChangeListener() {
        java.beans.PropertyChangeListener l = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                Object source = evt.getSource();
                assertEquals(instance, source);
                assertEquals(KeyStoreBean.NAME_PROPERTY, evt.getPropertyName());
            }
        };
        instance.addPropertyChangeListener(l);

        instance.setName("newName3");
    }

    /**
     * Test of removePropertyChangeListener method, of class
     * org.huber.keytool.model.KeyStoreBean.
     */
    @Test
    public void testRemovePropertyChangeListener() {
        final Integer[] counts = new Integer[1];
        counts[0] = new Integer(0);
        java.beans.PropertyChangeListener l = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                int newCount = counts[0].intValue() + 1;
                counts[0] = new Integer(newCount);
            }
        };
        instance.addPropertyChangeListener(l);
        instance.removePropertyChangeListener(l);

        instance.setName("newName3");

        assertEquals(0, counts[0].intValue());
    }

    /**
     * Test of hashCode method, of class org.huber.keytool.model.KeyStoreBean.
     */
    @Test
    public void testHashCode() {
        instance.setName("nameA");
        KeyStoreBean instance2 = new KeyStoreBean();
        instance2.setName("nameA");

        int result = instance.hashCode();
        int result2 = instance2.hashCode();
        assertTrue(result == result2);

        instance2.setName("nameB");
        result = instance.hashCode();
        result2 = instance2.hashCode();
        assertTrue(result != result2);

    }

    /**
     * Test of equals method, of class org.huber.keytool.model.KeyStoreBean.
     */
    @Test
    public void testEquals() {
        instance.setName("nameA");
        KeyStoreBean instance2 = new KeyStoreBean();
        instance2.setName("nameA");

        boolean result = instance.equals(instance2);
        assertEquals(true, result);

        instance2.setName("B");
        result = instance.equals(instance2);
        assertEquals(false, result);
    }

    /**
     * Test of compareTo method, of class org.huber.keytool.model.KeyStoreBean.
     */
    @Test
    public void testCompareTo() {
        instance.setName("A");
        KeyStoreBean instance2 = new KeyStoreBean();
        instance2.setName("B");

        int result = instance.compareTo(instance2);
        assertEquals(-1, result);

    }

    /**
     * Test of saveKeyStoreFile method, of class
     * org.huber.keytool.model.KeyStoreBean.
     */
    @Test
    public void testSaveKeyStoreFile() {
        // TODO implement this test case
        //instance.saveKeyStoreFile();
    }

}
