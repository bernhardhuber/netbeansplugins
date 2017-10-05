/*
 * LineCounterIF.java
 *
 * Created on 10. August 2006, 14:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbwordcount.model.counters;

/**
 * An interface for counting information.
 * <p>
 * An implementation shall expect that <code>count</code> is invoked
 * for each line of a file.
 * <p>
 * After all files has been examined the counter information is 
 * retrieved using <code>getCounterInfo</code>.
 *
 * @author HuberB1
 */
public interface LineCounterIF {
    /**
     * Examine this line
     *
     * @param line the line
     */
    void count(String line);
    
    /**
     * Return the counter information gather by examing the lines
     *
     * @return CounterInfo
     */
    CounterInfo getCounterInfo();
    
    public static abstract class CounterInfo {
        public abstract Number[] getCounterValues();
    }
}
