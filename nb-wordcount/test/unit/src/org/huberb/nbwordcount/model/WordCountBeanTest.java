/*
 * WordCountBeanTest.java
 * JUnit based test
 *
 * Created on 23. Jänner 2006, 23:13
 */
package org.huberb.nbwordcount.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HuberB1
 */
public class WordCountBeanTest {

    /**
     * Test of count method, of class
     * org.huberb.nbwordcount.model.WordCountBean. Test of setFileObject method,
     * of class org.huberb.nbwordcount.model.WordCountBean. Test of
     * getNamesOfCounters method, of class
     * org.huberb.nbwordcount.model.WordCountBean.
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
     * Test of getCounters method, of class
     * org.huberb.nbwordcount.model.WordCountBean.
     */
    @Test
    public void testGetCounters() {
        WordCountBean instance = new WordCountBean();

        Long[] result = instance.getCounters();

        final Long long0L = new Long(0);
        assertEquals(long0L, result[0]);
        assertEquals(long0L, result[1]);
        assertEquals(long0L, result[2]);
    }
}
