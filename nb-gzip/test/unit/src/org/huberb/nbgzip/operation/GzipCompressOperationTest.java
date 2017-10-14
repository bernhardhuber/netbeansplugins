/*
 * GzipCompressOperationTest.java
 * JUnit based test
 *
 * Created on 12. September 2006, 20:36
 */
package org.huberb.nbgzip.operation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author HuberB1
 */
public class GzipCompressOperationTest {

    private FileObject aFileObject;
    private String sampleFileName;
    private AbstractFileSystemOperationUtil util;

    @Before
    public void setUp() throws Exception {
        util = new AbstractFileSystemOperationUtil();

        sampleFileName = "sampleFileForGzipCompressOperationTest";

        final FileObject root = util.getFileSystem().getRoot();
        aFileObject = FileUtil.createData(root, sampleFileName);

        // at last on exit remove this file
        final File f = FileUtil.toFile(aFileObject);
        f.deleteOnExit();

        // write some data info aFileObject
        FileLock lock = aFileObject.lock();
        try (OutputStream os = aFileObject.getOutputStream(lock)) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 10; i++) {
                sb.append("SampleDataSampleDataSampleDataSampleDataSampleData");
            }
            final String sampleData = sb.toString();
            
            os.write(sampleData.getBytes());
            os.flush();
        }
        lock.releaseLock();

    }

    /**
     * Test of compress method, of class
     * org.huberb.nbgzip.operation.GzipCompressOperation.
     */
    @Test
    public void testCompress() throws IOException {
        FileObject sourceFO = this.aFileObject;
        assertTrue(sourceFO.canWrite());

        //--------------------------------------------------------------------
        // (1) compress the sourceFO
        GzipCompressOperation instance = new GzipCompressOperation();
        boolean expResult = true;
        boolean result = instance.compress(sourceFO);
        assertEquals(expResult, result);
        assertTrue(sourceFO.canRead());

        final FileObject compressedFO = util.getFileSystem().getRoot().getFileObject(sampleFileName + ".gz");
        assertTrue(compressedFO.canRead());
        assertTrue(sourceFO.getSize() > compressedFO.getSize());
        // rename the source file
        FileLock lock = sourceFO.lock();
        sourceFO.rename(lock, this.sampleFileName, "orig");
        lock.releaseLock();

        //--------------------------------------------------------------------
        // (2) uncompress the sourceFO
        final GzipUncompressOperation uncompressInstance = new GzipUncompressOperation();
        uncompressInstance.uncompress(compressedFO);
        final FileObject uncompressedFO = util.getFileSystem().getRoot().getFileObject(sampleFileName);

        assertTrue(uncompressedFO.canRead());
        assertEquals(sourceFO.getSize(), uncompressedFO.getSize());

        // Compare soureceFO & uncompressedFO
        assertTrue(sourceFO != uncompressedFO);
        assertTrue(!sourceFO.getNameExt().equals(uncompressedFO.getNameExt()));
        assertFileObjectContentEquals(sourceFO, uncompressedFO);

        // delete all FileObject instances
        compressedFO.delete();
        uncompressedFO.delete();
        sourceFO.delete();
    }

    private void assertFileObjectContentEquals(FileObject sourceFO, FileObject uncompressedFO) throws IOException {
        final InputStream is1 = sourceFO.getInputStream();
        final InputStream is2 = uncompressedFO.getInputStream();

        boolean contentIsEqual = false;
        try {
            while (is1.available() >= 0 && is2.available() >= 0) {
                int i1 = is1.read();
                int i2 = is2.read();

                assertEquals(i1, i2);
                if (i1 == -1 && i2 == -1) {
                    contentIsEqual = true;
                    break;
                }
            }
        } finally {
            is1.close();
            is2.close();
        }

        assertTrue(contentIsEqual);
    }
}
