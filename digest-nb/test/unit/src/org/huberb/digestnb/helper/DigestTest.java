/*
 * DigestTest.java
 * JUnit based test
 *
 * Created on 07. Oktober 2005, 00:03
 */

package org.huberb.digestnb.helper;

import junit.framework.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * TestCase for DigestTest
 *
 * @see DigestTest
 * @author HuberB1
 */
public class DigestTest extends TestCase {
    
    public DigestTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(DigestTest.class);
        
        return suite;
    }
    
    /**
     * Test of getHexDigest method, of class org.huberb.digestnb.helper.Digest.
     */
    public void testGetHexDigest() {
        String credentials = "secret";
        String algorithm = "MD5";
        
        String expResult = "5ebe2294ecd0e0f08eab7690d2a6ee69";
        String result = Digest.getHexDigest(credentials, algorithm);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getBase64Digest method, of class org.huberb.digestnb.helper.Digest.
     */
    public void testGetBase64Digest() {
        String credentials = "secret";
        String algorithm = "MD5";
        
        String expResult = "Xr4ilOzQ4PCOq3aQ0qbuaQ==";
        String result = Digest.getBase64Digest(credentials, algorithm);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of getDigest method, of class org.huberb.digestnb.helper.Digest.
     */
    public void testGetDigest() {
        String credentials = "secret";
        String algorithm = "MD5";
        
        byte[] expResult = new byte[] {
            (byte)0x5e, (byte)0xbe, (byte)0x22, (byte)0x94,
                    (byte)0xec, (byte)0xd0, (byte)0xe0, (byte)0xf0,
                    (byte)0x8e, (byte)0xab, (byte)0x76, (byte)0x90,
                    (byte)0xd2, (byte)0xa6, (byte)0xee, (byte)0x69,
        };
        byte[] result = Digest.getDigest(credentials, algorithm);
        assertEquals(expResult.length, result.length);
        for (int i = 0; i < expResult.length; i++) {
            assertEquals(expResult[i], result[i]);
        }
    }
    
    public static void main(java.lang.String[] argList) {
        junit.textui.TestRunner.run(suite());
    }
    
}
