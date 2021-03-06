/*
 * CryptographicServicesTest.java
 * JUnit based test
 *
 * Created on 07. Juni 2007, 08:09
 */
package org.huber.keytool.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HuberB1
 */
public class CryptographicServicesTest {

    /**
     * Test of getServiceTypes method, of class
     * org.huber.keytool.model.CryptographicServices.
     */
    @Test
    public void testGetServiceTypes() {
        String[] result = CryptographicServices.getServiceTypes();
    }

    /**
     * Test of getCryptoImpls method, of class
     * org.huber.keytool.model.CryptographicServices.
     */
    @Test
    public void testGetCryptoImplsKeyPairGenerator() {
        String serviceType = "KeyPairGenerator";
        String[] result;

        result = CryptographicServices.getCryptoImpls(serviceType, true);
        assertCryptoImplsResult(result);

        result = CryptographicServices.getCryptoImpls(serviceType, false);
        assertCryptoImplsResult(result);
    }

    /**
     * Test of getCryptoImpls method, of class
     * org.huber.keytool.model.CryptographicServices.
     */
    @Test
    public void testGetCryptoImplsSignatur() {
        String serviceType = "Signature";
        String[] result;

        result = CryptographicServices.getCryptoImpls(serviceType, true);
        assertCryptoImplsResult(result);

        result = CryptographicServices.getCryptoImpls(serviceType, false);
        assertCryptoImplsResult(result);
    }

    protected void assertCryptoImplsResult(String[] result) {
        assertNotNull(result);
        assertTrue(result.length > 0);
        for (int i = 0; i < result.length; i++) {
            assertNotNull(result[i]);
        }
    }
}
