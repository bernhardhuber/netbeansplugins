package org.huberb.nbgzip.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.netbeans.api.progress.ProgressHandle;
import org.openide.util.Cancellable;

/**
 * Encapsulate <code>ProgressHandle</code> tasks here.
 * <p>
 * This class supports a progress handle which tracks the amount of bytes
 * read from an <code>ProgressInputStream</code>.
 *
 * @see ProgressInputStream
 *
 * @author HuberB1
 */
public class ProgressHandleWrapper implements Cancellable {
    private ProgressHandle progressHandle;
    private ProgressInputStream pis;
    private boolean cancel;
    
    /**
     * Create a new instance.
     *
     * @param newPis watch this input stream, and use the amount of read bytes
     *   as progress indication.
     */
    public ProgressHandleWrapper(ProgressInputStream newPis) {
        this.cancel = false;
        this.pis = newPis;
    }
    
    /**
     * Set progress handle
     *
     * @param newProgressHandle
     */
    public void setProgressHandle(ProgressHandle newProgressHandle) {
        this.progressHandle = newProgressHandle;
        
        this.cancel = false;
        this.progressHandle.start( 100 );
        
        final PropertyChangeListener pcl = new ProgressInputStreamPropertyChangeListener(this.progressHandle);
        pis.addPropertyChangeListener( pcl );
    }
    
    public ProgressHandle getProgressHandle() {
        return this.progressHandle;
    }
    public ProgressInputStream getProgressInputStream() {
        return this.pis;
    }

    /**
     * Implement the <code>Cancellable</code> interface.
     */
    public boolean cancel() {
        this.cancel = true;
        return this.cancel;
    }
    public boolean isCancelled() {
        return this.cancel;
    }
    
    /**
     * Encapsulate updating the ProgressHandle progress.
     */
    public static class ProgressInputStreamPropertyChangeListener implements PropertyChangeListener {
        private ProgressHandle progressHandle;
        
        public ProgressInputStreamPropertyChangeListener( ProgressHandle progressHandle ) {
            this.progressHandle = progressHandle;
        }
        
        /**
         * This callback is invoked each time bytes are read from a 
         * <code>ProgressInputStream</code>.
         * This method updates the <code>ProgressHandle</code> progress
         * 
         * @param evt the property change event delivered from the 
         *   <code>ProgressInputStream</code>
         *
         * @see ProgressInputStream
         */
        public void propertyChange(final PropertyChangeEvent evt) {
            final ProgressInputStream pis = (ProgressInputStream)evt.getSource();
            final int progress = pis.getReadPercentage();
            progressHandle.progress( progress );
        }
    }
}