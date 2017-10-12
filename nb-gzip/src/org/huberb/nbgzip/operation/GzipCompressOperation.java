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
import java.util.zip.GZIPOutputStream;
import org.huberb.nbgzip.util.ProgressHandleWrapper;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.ErrorManager;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Cancellable;
import org.openide.util.NbBundle;

/**
 * Encapsulate the compressing operation here.
 *
 * @author HuberB1
 */
public class GzipCompressOperation extends StreamOperation {

    /**
     * Creates a new instance of GzipOperation
     */
    public GzipCompressOperation() {
    }

    /**
     * Compress the FileObject
     *
     * @param sourceFO the source FileObject which gets compressed
     */
    public boolean compress(FileObject sourceFO) {
        boolean success = false;

        FileLock fileLock = null;
        ProgressHandle progressHandle = null;

        try {
            // create target
            final String targetFilename = sourceFO.getNameExt() + ".gz";
            FileObject targetFO = FileUtil.createData(sourceFO.getParent(), targetFilename);
            fileLock = targetFO.lock();
            try (OutputStream os = new GZIPOutputStream(targetFO.getOutputStream(fileLock))) {
                // get input
                final ProgressHandleWrapper phw = createProgressHandleWrapper(sourceFO);
                try (InputStream is = phw.getProgressInputStream()) {

                    // process: read from input and write compressed to target
                    final Cancellable allowToCancel = phw;
                    final String progressLabel = NbBundle.getMessage(StreamOperation.class, "LBL_Compress");
                    progressHandle = ProgressHandleFactory.createHandle(progressLabel, allowToCancel);
                    phw.setProgressHandle(progressHandle);

                    success = process(phw, is, os);
                }
            }
        } catch (IOException ioe) {
            success = false;
            ErrorManager.getDefault().notify(ioe);
        } finally {
            releaseLock(fileLock);

            if (progressHandle != null) {
                progressHandle.finish();
            }
        }
        return success;
    }

}
