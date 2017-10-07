/*
 * AbstractComposite.java
 *
 * Created on 23. Mai 2007, 20:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuberB1
 */
public class AbstractComposite extends AbstractComponent implements Serializable {
    protected static final long serialVersionUID = 20070523L;

    private List<AbstractComponent> items;
    
    /** Creates a new instance of AbstractComposite */
    public AbstractComposite() {
        this.items = new ArrayList<AbstractComponent>(3);
    }
    
    public List<AbstractComponent> getItems() {
        return items;
    }
    public void setItems(List<AbstractComponent> items) {
        this.items = items;
    }

    public int getSize() {
        return items != null ? items.size() : 0;
    }
    
    public void addItem(AbstractComponent fi) {
        items.add( fi );
    }
    public void removeItem(AbstractComponent fi) {
        items.remove( fi );
    }
    
    public void removeItem(int index) {
        items.remove( index );
    }    
}
