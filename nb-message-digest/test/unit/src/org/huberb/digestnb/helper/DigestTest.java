/*
 * DigestTest.java
 * JUnit based test
 *
 * Created on 07. Oktober 2005, 00:03
 */
package org.huberb.digestnb.helper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TestCase for DigestTest
 *
 * @see DigestTest
 * @author HuberB1
 */
public class DigestTest {

    /**
     * Test of getHexDigest method, of class org.huberb.digestnb.helper.Digest.
     */
    @Test
    public void testGetHexDigest() {
        String credentials = "secret";
        String algorithm = "MD5";

        String expResult = "5ebe2294ecd0e0f08eab7690d2a6ee69";
        String result = Digest.getHexDigest(credentials, algorithm);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBase64Digest method, of class
     * org.huberb.digestnb.helper.Digest.
     */
    @Test
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
    @Test
    public void testGetDigest() {
        String credentials = "secret";
        String algorithm = "MD5";

        byte[] expResult = new byte[]{
            (byte) 0x5e, (byte) 0xbe, (byte) 0x22, (byte) 0x94,
            (byte) 0xec, (byte) 0xd0, (byte) 0xe0, (byte) 0xf0,
            (byte) 0x8e, (byte) 0xab, (byte) 0x76, (byte) 0x90,
            (byte) 0xd2, (byte) 0xa6, (byte) 0xee, (byte) 0x69,};
        byte[] result = Digest.getDigest(credentials, algorithm);
        assertEquals(expResult.length, result.length);
        for (int i = 0; i < expResult.length; i++) {
            assertEquals(expResult[i], result[i]);
        }
    }

}
