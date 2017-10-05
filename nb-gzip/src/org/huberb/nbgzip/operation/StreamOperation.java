/*
 * GzipOperation.java
 *
 * Created on 16. Jänner 2006, 15:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbgzip.operation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.huberb.nbgzip.util.ProgressHandleWrapper;
import org.huberb.nbgzip.util.ProgressInputStream;
import org.openide.ErrorManager;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;

/**
 * Encapsulate methods used for compressing, and uncompressing.
 * <p>
 * This class is extended by the concrete compressing, and uncompressing classes.
 *
 * @author HuberB1
 */
public abstract class StreamOperation {
    
    /** Creates a new instance of GzipOperation */
    public StreamOperation() {
    }

    protected ProgressHandleWrapper createProgressHandleWrapper(FileObject fo) throws IOException {
        final InputStream is = fo.getInputStream();
        final long foSize = fo.getSize();
        
        final ProgressInputStream pis = new ProgressInputStream( is, foSize );
        final ProgressHandleWrapper progressHandleWrapper = new ProgressHandleWrapper(pis);
        
        return progressHandleWrapper;
    }

    /**
     * Read from input stream, and write to output stream, check the 
     * progress handle wrapper if reading/writing shall be canceled
     *
     * @param progressHandleWrapper
     * @param is the input stream
     * @param os the output stream
     * @return boolean true if this method has completed, else false if this method
     * has been canceled
     */
    protected boolean process( ProgressHandleWrapper progressHandleWrapper, InputStream is, OutputStream os ) throws IOException {
        final int BUFFER_LEN = 16 * 1024;
        final byte []buffer = new byte[BUFFER_LEN];
        int readLen = 0;
        while (!progressHandleWrapper.isCancelled() && (readLen = is.read(buffer)) > -1) {
            os.write( buffer, 0, readLen );
        }
        return !progressHandleWrapper.isCancelled();
    }
    
    protected void closeInputStream( InputStream is ) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException ioe) {
            ErrorManager.getDefault().notify( ioe );
        }
    }
    protected void closeOutputStream( OutputStream os ) {
        try {
            if (os != null) {
                os.flush();
                os.close();
            }
        } catch (IOException ioe) {
            ErrorManager.getDefault().notify( ioe );
        }
    }
    
    protected void releaseLock( FileLock fileLock ) {
        if (fileLock != null) {
            fileLock.releaseLock();
        }
    }
    
}
