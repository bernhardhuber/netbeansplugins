/*
 * SimpleRenderVisitor.java
 *
 * Created on 02. Juni 2007, 12:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.spi.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.huberb.i18nvalidator.api.FindItem;
import org.huberb.i18nvalidator.api.ComponentCounter;
import org.huberb.i18nvalidator.api.SearchItem;
import org.huberb.i18nvalidator.spi.*;

/**
 *
 * @author HuberB1
 */
public class CountOfVisitor extends AbstractVisitor {
    private Map<String, ComponentCounter> counterFindItem = new HashMap<String, ComponentCounter>();
        
    /** Creates a new instance of SimpleRenderVisitor */
    public CountOfVisitor() {
    }
    
    public List<ComponentCounter> getComponentCounterList() {
        List<ComponentCounter> list = new ArrayList<ComponentCounter>();
        
        for (Iterator<ComponentCounter> i = this.counterFindItem.values().iterator(); i.hasNext(); ) {
            ComponentCounter ic = i.next();
            list.add( ic );
        }
        return list;
    }
    
    //-------------------------------------------------------------------------
    public void _visit( SearchItem fi ) {
        // ignore
    }
    
    public void _visit( FindItem fi ) {
        String pattern = fi.getPattern();
        ComponentCounter ic = counterFindItem.get(pattern);
        if (ic == null) {
            ic = new ComponentCounter(fi);
        }
        ic.increment();
        
        counterFindItem.put( pattern, ic );
    }
    
}
