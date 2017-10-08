/*
 * DeleteOperationTest.java
 * JUnit based test
 *
 * Created on 12. September 2006, 19:02
 */
package org.huberb.nbgzip.operation;

import java.io.File;
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
public class DeleteOperationTest {

    private FileObject aFileObject;

    @Before
    public void setUp() throws Exception {
        final AbstractFileSystemOperationUtil util = new AbstractFileSystemOperationUtil();
        final FileObject root = util.getFileSystem().getRoot();
        aFileObject = FileUtil.createData(root, "sampleFileForDeleteOperationTest");

        // at last on exit remove this file
        final File f = FileUtil.toFile(aFileObject);
        f.deleteOnExit();
    }

    /**
     * Test of delete method, of class
     * org.huberb.nbgzip.operation.DeleteOperation.
     */
    @Test
    public void testDelete() {
        final FileObject sourceFO = this.aFileObject;
        assertTrue(sourceFO.isValid());
        assertTrue(sourceFO.isData());
        assertTrue(!sourceFO.isFolder());

        assertTrue(sourceFO.canRead());
        assertTrue(sourceFO.canWrite());

        final DeleteOperation instance = new DeleteOperation();

        boolean expResult = true;
        boolean result = instance.delete(sourceFO);
        assertEquals(expResult, result);

        assertTrue(!sourceFO.canRead());
    }

}
