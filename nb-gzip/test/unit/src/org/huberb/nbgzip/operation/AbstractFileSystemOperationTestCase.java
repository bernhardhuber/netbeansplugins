/*
 * AbstractFileSystemOperationTestCase.java
 *
 * Created on 12. September 2006, 20:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbgzip.operation;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import junit.framework.TestCase;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.LocalFileSystem;

/**
 *
 * @author HuberB1
 */
public class AbstractFileSystemOperationTestCase extends TestCase {
    private final LocalFileSystem lfs;
    
    /** Creates a new instance of AbstractFileSystemOperationTestCase */
    public AbstractFileSystemOperationTestCase(String testName) throws PropertyVetoException, IOException {
                super(testName);

        // Create a file in the java.io.tempdir directory
        final String tempDir = System.getProperty( "java.io.tmpdir" );
        final File tempDirAsFile = new File(tempDir);
        this.lfs = new LocalFileSystem();

        this.lfs.setRootDirectory( tempDirAsFile );
    }
    
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    protected FileSystem getFileSystem() {
        return this.lfs;
    }
}
