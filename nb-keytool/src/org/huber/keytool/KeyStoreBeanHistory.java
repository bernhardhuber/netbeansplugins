/*
 * KeyStoreBeanHistory.java
 *
 * Created on 23. Februar 2006, 20:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.List;
import org.huber.keytool.model.HistoryBean;
import org.huber.keytool.model.KeyStoreBean;
import org.huber.keytool.model.LabelValueBean;

/**
 *
 * @author HuberB1
 */
public class KeyStoreBeanHistory implements PropertyChangeListener, Serializable {
    private static final long serialVersionUID = 20060223210400L;
    
    private final HistoryBean<LabelValueBean<KeyStoreBean>> ksbh;
    
    /** Creates a new instance of KeyStoreBeanHistory */
    public KeyStoreBeanHistory() {
        ksbh = new HistoryBean<LabelValueBean<KeyStoreBean>>();
    }
    
    public List<LabelValueBean<KeyStoreBean>> getHistory() {
        return ksbh.getHistory();
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
        final String propertyName = evt.getPropertyName();
        if (propertyName != null && KeyStoreTopComponent.KEY_STORE_BEAN_PROP_NAME.equals(propertyName)) {
            final KeyStoreBean ksbNewValue = (KeyStoreBean)evt.getNewValue();
            
            if (ksbNewValue != null && ksbNewValue.getName() != null && ksbNewValue.isKeyStoreLoaded()) {
                final KeyStoreBean ksbForHistory = new KeyStoreBean();
                ksbForHistory.setName( ksbNewValue.getName() );
                
                final LabelValueBean<KeyStoreBean> lvb = new LabelValueBean<KeyStoreBean>();
                final String label = ksbForHistory.getName();
                lvb.setLabel(label);
                lvb.setValue( ksbForHistory );
                
                this.ksbh.add( lvb );
            }
        }
    }
    
}
