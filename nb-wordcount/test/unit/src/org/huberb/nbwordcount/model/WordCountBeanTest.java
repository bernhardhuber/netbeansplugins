/*
 * WordCountBeanTest.java
 * JUnit based test
 *
 * Created on 23. Jänner 2006, 23:13
 */

package org.huberb.nbwordcount.model;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import junit.framework.*;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.LocalFileSystem;

/**
 *
 * @author HuberB1
 */
public class WordCountBeanTest extends TestCase {
    
    public WordCountBeanTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(WordCountBeanTest.class);
        
        return suite;
    }
    
    /**
     * Test of count method, of class org.huberb.nbwordcount.model.WordCountBean.
     * Test of setFileObject method, of class org.huberb.nbwordcount.model.WordCountBean.
     * Test of getNamesOfCounters method, of class org.huberb.nbwordcount.model.WordCountBean.
     */
    // TODO fix fo = null
//    public void testCount() throws URISyntaxException {
//        WordCountBean instance = new WordCountBean();
//
//        URL url = this.getClass().getResource("SampleText.txt");
//        File file = new File(url.toURI());
//        File normalizedFile = FileUtil.normalizeFile( file );
//        FileObject fo = FileUtil.toFileObject( normalizedFile );
//
//        instance.setFileObject( fo );
//        instance.count();
//
//        assertTrue( instance.getCountOfCharacters() > 1 );
//        assertEquals( 3L, instance.getCountOfLines() );
//        assertEquals( 14L, instance.getCountOfWords() );
//
//        assertEquals( "", instance.getNamesOfCounters() );
//    }
    
    /**
     * Test of getCounters method, of class org.huberb.nbwordcount.model.WordCountBean.
     */
    public void testGetCounters() {
        WordCountBean instance = new WordCountBean();
        
        Long[] result = instance.getCounters();
        
        final Long long0L = new Long(0);
        assertEquals(long0L, result[0]);
        assertEquals(long0L, result[1]);
        assertEquals(long0L, result[2]);
    }
    
    
    public static void main(java.lang.String[] argList) {
        junit.textui.TestRunner.run(suite());
    }
    
}
