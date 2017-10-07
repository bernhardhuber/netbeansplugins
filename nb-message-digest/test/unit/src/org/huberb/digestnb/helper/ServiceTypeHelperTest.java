/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.digestnb.helper;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berni3
 */
public class ServiceTypeHelperTest {

    public ServiceTypeHelperTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getServiceTypes method, of class ServiceTypeHelper.
     */
    @Test
    public void testGetServiceTypes() {
        String[] result = ServiceTypeHelper.getServiceTypes();
        assertNotNull(result);
        String m = "testGetServiceTypes " + Arrays.toString(result);
        assertTrue(m, result.length > 0);
        System.out.println("" + m);
    }

    /**
     * Test of getCryptoImpls method, of class ServiceTypeHelper.
     */
    @Test
    public void testGetCryptoImpls() {
        String[] result = ServiceTypeHelper.getCryptoImpls("MessageDigest");
        assertNotNull(result);
        String m = "testGetCryptoImpls " + Arrays.toString(result);
        assertTrue(m, result.length > 0);
        System.out.println("" + m);
    }

    /**
     * Test of getMessageDigestImpls method, of class ServiceTypeHelper.
     */
    @Test
    public void testGetMessageDigestImpls() {

        String[] result = ServiceTypeHelper.getMessageDigestImpls();
        assertNotNull(result);
        String m = "testGetMessageDigestImpls " + Arrays.toString(result);
        assertTrue(m, result.length > 0);
        System.out.println("" + m);
    }

}
