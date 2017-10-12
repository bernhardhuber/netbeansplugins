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
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

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
    @Test
    public void testCount() throws URISyntaxException {
        WordCountBean instance = new WordCountBean();

        URL url = this.getClass().getResource("SampleText.txt");
        File file = new File(url.toURI());
        File normalizedFile = FileUtil.normalizeFile(file);
        assertEquals(true, normalizedFile.canRead());
        FileObject fo = FileUtil.toFileObject(normalizedFile);
        assertNotNull(fo);
        assertEquals(true, fo.canRead());

        instance.setFileObject(fo);
        instance.count();

        assertEquals(true, instance.getCounters()[0].longValue() > 1L);
        assertEquals(65L, instance.getCounters()[0].longValue());
        assertEquals(14L, instance.getCounters()[1].longValue());
        assertEquals(2L, instance.getCounters()[2].longValue());

        assertEquals("SampleText.txt", instance.getNamesOfFiles());
    }

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
