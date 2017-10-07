/*
 * ProgressInputStreamTest.java
 * JUnit based test
 *
 * Created on 04. September 2006, 23:05
 */

package org.huberb.nbgzip.util;

import java.io.ByteArrayInputStream;
import junit.framework.*;

/**
 *
 * @author HuberB1
 */
public class ProgressInputStreamTest extends TestCase {
    
    public ProgressInputStreamTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(ProgressInputStreamTest.class);
        
        return suite;
    }
    
    /**
     * Test of read method, of class org.huberb.nbgzip.util.ProgressInputStream.
     */
    public void testRead() throws Exception {
        final ByteArrayInputStream bais = createByteArrayInputStream("ABC");
        final int len = bais.available();
        ProgressInputStream instance = new ProgressInputStream(bais, len );
        
        int expResult = 65;
        int result = instance.read();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of skip method, of class org.huberb.nbgzip.util.ProgressInputStream.
     */
    public void testSkip() throws Exception {
        final ByteArrayInputStream bais = createByteArrayInputStream("ABC");
        final int len = bais.available();
        ProgressInputStream instance = new ProgressInputStream(bais, len );
        
        long expResult = 1L;
        long result = instance.skip(1);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of reset method, of class org.huberb.nbgzip.util.ProgressInputStream.
     */
    public void testReset() throws Exception {
        final ByteArrayInputStream bais = createByteArrayInputStream("ABC");
        final int len = bais.available();
        ProgressInputStream instance = new ProgressInputStream(bais, len );
        
        instance.reset();
        
    }
    
    /**
     * Test of resetReadBytes method, of class org.huberb.nbgzip.util.ProgressInputStream.
     */
    public void testResetReadBytes() {
        final ByteArrayInputStream bais = createByteArrayInputStream("ABC");
        final int len = bais.available();
        ProgressInputStream instance = new ProgressInputStream(bais, len );
        
        instance.resetReadBytes();
    }
    
    /**
     * Test of incrementReadBytes method, of class org.huberb.nbgzip.util.ProgressInputStream.
     */
    public void testIncrementReadBytes() {
        final ByteArrayInputStream bais = createByteArrayInputStream("ABC");
        final int len = bais.available();
        ProgressInputStream instance = new ProgressInputStream(bais, len );
        
        instance.incrementReadBytes(1L);
    }
    
    /**
     * Test of getReadBytes method, of class org.huberb.nbgzip.util.ProgressInputStream.
     */
    public void testGetReadBytes() {
        final ByteArrayInputStream bais = createByteArrayInputStream("ABC");
        final int len = bais.available();
        ProgressInputStream instance = new ProgressInputStream(bais, len );
        
        long expResult = 0L;
        long result = instance.getReadBytes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTotalBytes method, of class org.huberb.nbgzip.util.ProgressInputStream.
     */
    public void testGetTotalBytes() {
        final ByteArrayInputStream bais = createByteArrayInputStream("ABC");
        final int len = bais.available();
        ProgressInputStream instance = new ProgressInputStream(bais, len );
        
        long expResult = 3L;
        long result = instance.getTotalBytes();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getReadPercentage method, of class org.huberb.nbgzip.util.ProgressInputStream.
     */
    public void testGetReadPercentage() {
        final ByteArrayInputStream bais = createByteArrayInputStream("ABC");
        final int len = bais.available();
        ProgressInputStream instance = new ProgressInputStream(bais, len );
        
        int expResult = 0;
        int result = instance.getReadPercentage();
        assertEquals(expResult, result);
        
    }
    
    private ByteArrayInputStream createByteArrayInputStream( String input ) {
        byte[] buf = input.getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        return bais;
    }
}
