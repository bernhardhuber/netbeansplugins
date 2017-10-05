/*
 * DeleteOperation.java
 *
 * Created on 16. Jänner 2006, 21:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbgzip.operation;

import java.io.IOException;
import org.openide.ErrorManager;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;

/**
 * Encapsulate the delete operation here.
 *
 * @author HuberB1
 */
public class DeleteOperation {
    
    /** Creates a new instance of DeleteOperation */
    public DeleteOperation() {
    }
    
    public boolean delete( FileObject sourceFO ) {
        boolean success = false;
        FileLock lock = null;
        try {
            lock = sourceFO.lock();
            sourceFO.delete(lock);
            success = true;
        } catch (IOException ioe) {
            ErrorManager.getDefault().notify( ioe );
        } finally {
            if (lock != null) {
                lock.releaseLock();
            }
        }
        return success;
    }
}
