package org.huberb.jdepend.misc;

import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;

/**
 * Encapsulate progress handle operations.
 * <p>
 * This class helps to manage a progress handle.
 * <ul>
 * <li>Define the maximum number of steps in the constructor
 * <li>Invoke progress for each step
 * <li>Call finish at the end
 * </ul>
 */
public class ProgressHandleHelper {
    private ProgressHandle ph;
    private int progressCount;
    private int totalNumberOfProgressSteps;
    
    /**
     * Create a new instance
     *
     * @param totalNumberOfProgressSteps the total number of steps
     * @param displayName the progress display name
     */
    public ProgressHandleHelper(int totalNumberOfProgressSteps, String displayName) {
        this.totalNumberOfProgressSteps = totalNumberOfProgressSteps;
        this.ph = ProgressHandleFactory.createHandle(displayName);
        this.ph.start(this.totalNumberOfProgressSteps);
        this.progressCount = 1;
    }
    
    /**
     * Indicate a single progress step.
     */
    public void progress() {
        this.progressCount += 1;
        if (this.progressCount > totalNumberOfProgressSteps) {
            this.progressCount = totalNumberOfProgressSteps;
        }
        
        final int theProgressCount = this.progressCount;
        ph.progress( theProgressCount );
    }
    
    /**
     * Change the progress label. This method does not change
     * the progress step value
     *
     * @param newProgressString the new progress label
     */
    public void progress( final String newProgressString ) {
        ph.progress( newProgressString );
    }
    
    /**
     * Indicate that progress has finished
     */
    public void finish() {
        this.ph.finish();
    }
}
