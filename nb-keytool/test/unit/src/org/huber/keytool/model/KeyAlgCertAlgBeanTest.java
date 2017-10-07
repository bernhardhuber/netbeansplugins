/*
 * KeyAlgCertAlgBeanTest.java
 * JUnit based test
 *
 * Created on 07. Juni 2007, 08:23
 */

package org.huber.keytool.model;

import junit.framework.*;

/**
 *
 * @author HuberB1
 */
public class KeyAlgCertAlgBeanTest extends TestCase {
    
    public KeyAlgCertAlgBeanTest(String testName) {
        super(testName);
    }

    /**
     * Test of getKeyAlg method, of class org.huber.keytool.model.KeyAlgCertAlgBean.
     */
    public void testGetKeyAlg() {
        KeyAlgCertAlgBean instance = new KeyAlgCertAlgBean("A", "B");
        
        String expResult = "A";
        String result = instance.getKeyAlg();
        assertEquals(expResult, result);
    }

    /**
     * Test of setKeyAlg method, of class org.huber.keytool.model.KeyAlgCertAlgBean.
     */
    public void testSetKeyAlg() {
        String keyAlg = "A";
        KeyAlgCertAlgBean instance = new KeyAlgCertAlgBean();
        
        instance.setKeyAlg(keyAlg);
        
        assertEquals( "A", instance.getKeyAlg() );
    }

    /**
     * Test of getCertAlg method, of class org.huber.keytool.model.KeyAlgCertAlgBean.
     */
    public void testGetCertAlg() {
        KeyAlgCertAlgBean instance = new KeyAlgCertAlgBean("A","B");
        
        String expResult = "B";
        String result = instance.getCertAlg();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of setCertAlg method, of class org.huber.keytool.model.KeyAlgCertAlgBean.
     */
    public void testSetCertAlg() {
        String certAlg = "B";
        KeyAlgCertAlgBean instance = new KeyAlgCertAlgBean();
        instance.setCertAlg(certAlg);
        
        assertEquals( "B", instance.getCertAlg() );
    }

    /**
     * Test of hashCode method, of class org.huber.keytool.model.KeyAlgCertAlgBean.
     */
    public void testHashCode() {
        KeyAlgCertAlgBean instance = new KeyAlgCertAlgBean("A","B");
        KeyAlgCertAlgBean instance2 = new KeyAlgCertAlgBean("X","Y");
        KeyAlgCertAlgBean instance3 = new KeyAlgCertAlgBean("A","B");
        
        assertEquals( instance.hashCode(), instance.hashCode() );
        assertEquals( instance.hashCode(), instance3.hashCode() );
        assertTrue( instance.hashCode() != instance2.hashCode() );
    }

    /**
     * Test of equals method, of class org.huber.keytool.model.KeyAlgCertAlgBean.
     */
    public void testEquals() {
        KeyAlgCertAlgBean instance = new KeyAlgCertAlgBean("A","B");
        KeyAlgCertAlgBean instance2 = new KeyAlgCertAlgBean("X","Y");
        KeyAlgCertAlgBean instance3 = new KeyAlgCertAlgBean("A","B");

        assertEquals( instance, instance );
        assertTrue( !instance.equals(instance2) );
        assertEquals( instance, instance3 );
    }
    
}
