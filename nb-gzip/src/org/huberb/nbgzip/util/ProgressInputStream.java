package org.huberb.nbgzip.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.event.SwingPropertyChangeSupport;

/**
 * This InputStream counts the read bytes.
 * <p>
 * Moreover the count of read bytes are provided to property change listeners.
 * The count of read bytes are provided as Long property.
 *
 * @see #getReadBytes
 * @see #getReadPercentage
 * @see #PROPERTY_READ_BYTES
 */
public class ProgressInputStream extends FilterInputStream {
    private long readBytes;
    private long totalBytes;
    
    public final static String PROPERTY_READ_BYTES = "readBytes";
    private final PropertyChangeSupport propertyChangeSupport = new SwingPropertyChangeSupport(this);
    
    /**
     * Create a new instance
     *
     * @param is the source input stream
     * @param totalBytes the number of total bytes which will be read
     */
    public ProgressInputStream(InputStream is, long totalBytes) {
        super(is);
        this.totalBytes = totalBytes;
        this.resetReadBytes();
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener( listener );
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener( listener );
    }
    
    public int read() throws IOException {
        final int retValue = super.read();
        incrementReadBytes(1);
        
        return retValue;
    }
    public int read(byte[] b) throws IOException {
        final int retValue = super.read(b);
        incrementReadBytes(retValue);
        
        return retValue;
    }
    public int read(byte[] b, int off, int len) throws IOException {
        final int retValue = super.read(b, off, len);
        incrementReadBytes(retValue);
        
        return retValue;
    }
    
    public long skip(long n) throws IOException {
        final long retValue = super.skip(n);
        incrementReadBytes(retValue);
        
        return retValue;
    }
    
    public void reset() throws IOException {
        super.reset();
        this.resetReadBytes();
    }
    
    protected void resetReadBytes() {
        this.readBytes = 0;
    }
    
    protected void incrementReadBytes(long increment) {
        if (increment <= 0) {
            return;
        }
        final long oldReadBytes = this.readBytes;
        
        this.readBytes += increment;        
        this.propertyChangeSupport.firePropertyChange(PROPERTY_READ_BYTES, new Long(oldReadBytes), new Long(this.readBytes));
    }
    
    /**
     * Return the count of bytes read.
     *
     * @return long the count of bytes read
     */
    public long getReadBytes() {
        return Math.min(this.readBytes, this.totalBytes);
    }
    /**
     * Return the max count of bytes read.
     *
     * @return long the max counter of bytes read
     */
    public long getTotalBytes() {
        return this.totalBytes;
    }
    
    /**
     * Return the count of bytes read as percentage value ranging from 0 to 99
     *
     * @return int the count of bytes read as percentage value ranging from 0 to 99
     */
    public int getReadPercentage() {
        long percentage = 0;
        if (this.readBytes > 0 && this.totalBytes > 0) {
            float percentageAsFloat = ((float)this.readBytes * (100.0f / (float)this.totalBytes));
            percentage = (long)percentageAsFloat;
        }
        
        percentage = Math.max(0, percentage );
        percentage = Math.min(99, percentage);
        
        return (int)percentage;
    }
}