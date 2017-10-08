/*
 * KeyStoreFileFilterFactoryTest.java
 * JUnit based test
 *
 * Created on 07. Juni 2007, 08:37
 */
package org.huber.keytool.ui.filefilter;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HuberB1
 */
public class KeyStoreFileFilterFactoryTest {

    /**
     * Test of addChoosableFileFilter method, of class
     * org.huber.keytool.ui.filefilter.KeyStoreFileFilterFactory.
     */
    @Test
    public void testAddChoosableFileFilter() {
        JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        KeyStoreFileFilterFactory.addChoosableFileFilter(fc);

        FileFilter[] ffs = fc.getChoosableFileFilters();

        assertTrue(ffs.length == 2);

        for (int i = 0; i < ffs.length; i++) {
            FileFilter ff = ffs[i];

            assertNotNull(ff.getDescription());
            File file = new File("abc");
            ff.accept(file);
            assertTrue(!ff.accept(file));
        }
    }

    /**
     * Test of addChoosableFileFilter method, of class
     * org.huber.keytool.ui.filefilter.KeyStoreFileFilterFactory.
     */
    @Test
    public void testAccept() throws IOException {

        Object[] data = new Object[]{
            Boolean.TRUE, File.createTempFile("abc", ".jks"),
            Boolean.TRUE, File.createTempFile("abc", ".keystore"),};

        for (int i = 0; i < data.length; i += 2) {
            Boolean expected = (Boolean) data[i + 0];
            File theFile = (File) data[i + 1];
            theFile.deleteOnExit();

            Boolean accept = KeyStoreFileFilterFactory.accept(theFile);

            assertEquals("File " + i + ": " + theFile.toString(), expected, accept);
        }

    }
}
