/*
 * CounterListener.java
 *
 * Created on 05. Juni 2007, 16:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.spi.implementation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.huberb.i18nvalidator.api.ComponentCounter;
import org.huberb.i18nvalidator.api.FindItem;
import org.huberb.i18nvalidator.api.SearchItem;
import org.huberb.i18nvalidator.spi.AbstractVisitor;

/**
 *
 * @author HuberB1
 */
public class CounterListener implements PropertyChangeListener {
    private Map<String, ComponentCounter<FindItem>> counterFindItem = new HashMap<String, ComponentCounter<FindItem>>();
    private ComponentCounter<FindItem> lastCcfi;
    
    /** Creates a new instance of CounterListener */
    public CounterListener() {
    }

    public Map<String, ComponentCounter<FindItem>> getCounterFindItemMap() {
        return this.counterFindItem;
    }
    public void setCounterFindItemMap(Map<String, ComponentCounter<FindItem>> cfi) {
        this.counterFindItem = cfi;
    }
    
    public List<ComponentCounter<FindItem>> getCounterFindItemList() {
        List<ComponentCounter<FindItem>> list = new ArrayList<ComponentCounter<FindItem>>();
        
        for (Iterator<ComponentCounter<FindItem>> i = this.counterFindItem.values().iterator(); i.hasNext(); ) {
            ComponentCounter<FindItem> ic = i.next();
            list.add( ic );
        }
        return list;
    }
        
    public void propertyChange(PropertyChangeEvent evt) {
        String evtPropName = evt.getPropertyName();
        Object evtNewValue = evt.getNewValue();
        if (AbstractVisitor.VISTING_PROP.equals(evtPropName)) {
            if (evtNewValue instanceof FindItem) {
                FindItem fi = (FindItem)evtNewValue;
                handleFindItem(fi);
            }
        } else if ("searchItem".equals(evtPropName)) {
            if (evtNewValue instanceof SearchItem) {
                SearchItem si = (SearchItem)evtNewValue;
                handleSearchItem(si);
            }
            
        }
    }
    
    protected void handleFindItem( FindItem fi ) {
        // callback FindItem visit
        ComponentCounter<FindItem> ccfi = getComponentCounterFindItem(fi.getPattern(), fi);
        this.lastCcfi = ccfi;
    }
    
    protected void handleSearchItem(SearchItem si) {
        if (this.lastCcfi != null) {
            this.lastCcfi.increment();
        }
    }
    
    protected ComponentCounter<FindItem> getComponentCounterFindItem( String name, FindItem fi ) {
        ComponentCounter<FindItem> ccfi = this.counterFindItem.get(name);
        if (ccfi == null) {
            ccfi = new ComponentCounter<FindItem>( fi );
            this.counterFindItem.put(name, ccfi );
        }
        return ccfi;
    }
    
}
