/*
 * CertificateRequestFileFilterFactoryTest.java
 * JUnit based test
 *
 * Created on 07. Juni 2007, 08:35
 */
package org.huber.keytool.ui.filefilter;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HuberB1
 */
public class CertificateRequestFileFilterFactoryTest {

    /**
     * Test of addChoosableFileFilter method, of class
     * org.huber.keytool.ui.filefilter.CertificateRequestFileFilterFactory.
     */
    @Test
    public void testAddChoosableFileFilter() {
        JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        CertificateRequestFileFilterFactory.addChoosableFileFilter(fc);

        FileFilter[] ffs = fc.getChoosableFileFilters();

        assertTrue(ffs.length == 2);
        for (int i = 0; i < ffs.length; i++) {
            FileFilter ff = ffs[i];

            assertNotNull(ff.getDescription());

            File file = new File("abc");
            assertTrue(!ff.accept(file));
        }
    }

}
