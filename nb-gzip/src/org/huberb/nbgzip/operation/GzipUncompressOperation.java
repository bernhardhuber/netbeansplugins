/*
 * GzipUncompressOperation.java
 *
 * Created on 27. Jänner 2006, 22:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.nbgzip.operation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
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
 * Encapsulate the uncompressing operation here.
 *
 * @author HuberB1
 */
public class GzipUncompressOperation extends StreamOperation {

    /**
     * Creates a new instance of GzipUncompressOperation
     */
    public GzipUncompressOperation() {
    }

    /**
     * Uncompress the FileObject
     *
     * @param sourceFO uncompress this source FileObject
     */
    public boolean uncompress(FileObject sourceFO) {
        boolean success = false;

        FileLock fileLock = null;
        ProgressHandle progressHandle = null;

        try {
            // create target
            final String targetFilename = sourceFO.getName();
            FileObject targetFO = FileUtil.createData(sourceFO.getParent(), targetFilename);
            fileLock = targetFO.lock();
            try (OutputStream os = targetFO.getOutputStream(fileLock)) {
                // get input
                ProgressHandleWrapper phw = createProgressHandleWrapper(sourceFO);
                try (InputStream is = new GZIPInputStream(phw.getProgressInputStream())) {

                    // process: read from input and write compressed to target
                    final Cancellable allowToCancel = phw;
                    final String progressLabel = NbBundle.getMessage(StreamOperation.class, "LBL_Uncompress");
                    progressHandle = ProgressHandleFactory.createHandle(progressLabel, allowToCancel);
                    phw.setProgressHandle(progressHandle);
                    success = process(phw, is, os);
                }
            }
        } catch (IOException ioe) {
            ErrorManager.getDefault().notify(ioe);
            success = false;
        } finally {
            releaseLock(fileLock);

            if (progressHandle != null) {
                progressHandle.finish();
            }
        }
        return success;
    }
}
