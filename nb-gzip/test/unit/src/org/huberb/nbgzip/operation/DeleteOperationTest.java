/*
 * DeleteOperationTest.java
 * JUnit based test
 *
 * Created on 12. September 2006, 19:02
 */

package org.huberb.nbgzip.operation;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import junit.framework.*;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author HuberB1
 */
public class DeleteOperationTest extends AbstractFileSystemOperationTestCase {
    private FileObject aFileObject;
    
    public DeleteOperationTest(String testName) throws Exception {
        super(testName);
    }
        
    protected void setUp() throws Exception {
        super.setUp();
        
        final FileObject root = getFileSystem().getRoot();
        aFileObject = FileUtil.createData( root, "sampleFileForDeleteOperationTest" );
        
        // at last on exit remove this file
        final File f = FileUtil.toFile( aFileObject );
        f.deleteOnExit();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(DeleteOperationTest.class);
        
        return suite;
    }
    
    /**
     * Test of delete method, of class org.huberb.nbgzip.operation.DeleteOperation.
     */
    public void testDelete() {
        final FileObject sourceFO = this.aFileObject;
        assertTrue( sourceFO.isValid() );
        assertTrue( sourceFO.isData() );
        assertTrue( !sourceFO.isFolder() );

        assertTrue( sourceFO.canRead() );        
        assertTrue( sourceFO.canWrite() );        
        
        final DeleteOperation instance = new DeleteOperation();
        
        boolean expResult = true;
        boolean result = instance.delete(sourceFO);
        assertEquals(expResult, result);
        
        assertTrue( !sourceFO.canRead()  );        
    }
    
}
