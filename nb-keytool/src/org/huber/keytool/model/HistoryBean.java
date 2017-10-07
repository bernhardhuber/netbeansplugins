/*
 * HistoryBean.java
 *
 * Created on 23. Februar 2006, 18:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A generic bean for storing a history.
 *
 * @author HuberB1
 */
public class HistoryBean<T> implements Serializable {
    private static final long serialVersionUID = 20060223195000L;
    
    private final int MAX_SIZE = 10;
    private int maxSize;
    private List<T> history;
    
    /**
     * Creates a new instance of HistoryBean
     */
    public HistoryBean() {
        history = new ArrayList<T>();
        this.maxSize = MAX_SIZE;
    }
    
    public int getMaxSize() {
        return this.maxSize;
    }
    public void setMaxSize( int newMaxSize ) {
        this.maxSize = newMaxSize;
    }
    
    public void add(T bean) {
        // remove elements
        for (Iterator<T> i = this.history.iterator(); i.hasNext(); ) {
            final T e = i.next();
            if (e.equals( bean )) {
                i.remove();
            }
        }
        
        history.add( 0, bean );
        
        if (history.size() > this.maxSize) {
            assertHistorySize();
        }
    }
    
    /**
     * Assert that history list contains not more 
     * than <code>maxSize</code> elements.
     * <p>
     * It may contain less elements.
     */
    protected void assertHistorySize() {
        for (int i = history.size(); i > this.maxSize; i-- ) {
            history.remove( i- 1 );
        }
    }
    
    public List<T> getHistory() {
        return Collections.unmodifiableList( history );
    }
    
}
