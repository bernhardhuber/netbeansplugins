/*
 * WordCountBean.java
 *
 * Created on 22. Jänner 2006, 10:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbwordcount.model;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.huberb.nbwordcount.model.counters.JavaStatementLineCounter;
import org.huberb.nbwordcount.model.counters.LineCounterIF.CounterInfo;
import org.huberb.nbwordcount.model.counters.SimpleLineCounter;
import org.openide.filesystems.FileObject;

/**
 * Encapsulate the counting of characters, words, and lines.
 *
 * @author HuberB1
 */
public class WordCountBean implements Serializable {
    protected final static long serialVersionUID = 20060124151400L;

    private FileObject fo;
    private List<String> foNames;
    private SimpleLineCounter simpleLineCounter;
    private JavaStatementLineCounter javaStatementLineCounter;
    
    /** Creates a new instance of WordCountBean */
    public WordCountBean() {
        this.fo = null;
        this.foNames = new ArrayList<String>();
        this.simpleLineCounter = new SimpleLineCounter();
        this.javaStatementLineCounter = new JavaStatementLineCounter();
    }
    
    /**
     * Set the FileObject used for calculating the counter informations
     * @param fo the FileObject used for calculating the counter informations
     */
    public void setFileObject( FileObject fo ) {
        this.fo = fo;
    }
    
    /**
     * Calculate the counter information.
     * <p>
     * This is the main entry point of this class for gathering
     * count information of the currently set file
     * <p>
     * FileObject's InputStream is read to calculate the counting information
     */
    public void count() {
        InputStream is = null;
        try {
            is = this.fo.getInputStream();
            count(is);
            
            final String foName = fo.getNameExt();
            foNames.add( foName );
        } catch (IOException ioex) {
            // ignore it
        } finally {
            closeSilently(is);
        }
    }

    /**
     * Get the names of all FileObject's we have processed, as 
     * CSV String
     *
     * @return String a CSV String of the FileObject's names
     */
    public String getNamesOfFiles() {
        final StringBuilder sb = new StringBuilder();
        
        for (Iterator<String> i = this.foNames.iterator(); i.hasNext(); ) {
            String foName = i.next();
            sb.append( foName );
            if (i.hasNext()) {
                sb.append( ", " );
            }
        }
        return sb.toString();
    }
    
    /**
     * Get the count of files examined
     *
     * @return int the number of files examined
     */
    public int getCountOfFiles() {
        return this.foNames.size();
    }
    
    /**
     * Get the counters.
     * @return Long[] of counter values, 
     * in the following sequence
     * <ul>
     *   <li>Count of characters
     *   <li>Count of words
     *   <li>Count of lines
     * </ul>
     */
    public Long[] getCounters() {
        CounterInfo ci1 = this.simpleLineCounter.getCounterInfo();
        CounterInfo ci2 = this.javaStatementLineCounter.getCounterInfo();
        Number[] counters1 = ci1.getCounterValues();
        Number[] counters2 = ci2.getCounterValues();
        
        final Long[] counterValues = new Long[] {
            Long.valueOf(counters1[0].longValue() ),
            Long.valueOf( counters1[1].longValue() ),
            Long.valueOf( counters1[2].longValue() ),
            Long.valueOf( counters2[0].longValue() ),
            Long.valueOf( counters2[1].longValue() ),
            Long.valueOf( counters2[2].longValue() ),
        };
        return counterValues;
    }
    
    /**
     * Examine each line of this stream.
     *
     * @param is lines of this input stream are examined
     */
    private void count(InputStream is) throws IOException {
        BufferedReader in = null;
        
        try {
            String line;
            in = new BufferedReader(new InputStreamReader(is));
            
            while ((line = in.readLine()) != null) {
                this.simpleLineCounter.count( line );
                this.javaStatementLineCounter.count( line );
            }
        } finally {
            closeSilently(in);
        }
    }
    
    private void closeSilently(Closeable is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException ioex) {
                // ignore it
            }
        }
    }
    
}
