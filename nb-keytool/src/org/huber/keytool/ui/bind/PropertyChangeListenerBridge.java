/*
 * WizardChangeListener.java
 *
 * Created on 17. Februar 2006, 15:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.bind;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author HuberB1
 */
public class PropertyChangeListenerBridge implements PropertyChangeListener {
    private ChangeListener cl;
    private Set<String> propertyNames;
    
    /** Creates a new instance of WizardChangeListener */
    public PropertyChangeListenerBridge(ChangeListener cl) {
        this.cl = cl;
    }
    
    /** Creates a new instance of WizardChangeListener */
    public PropertyChangeListenerBridge(ChangeListener cl, String[] propertyNames) {
        this.cl = cl;
        this.propertyNames = new HashSet<String>();
        this.propertyNames.addAll( Arrays.asList( propertyNames ) );
    }
    
    public void propertyChange(PropertyChangeEvent evt) {
        boolean sendStateChanged = false;
        if (this.propertyNames != null) {
            final String propertyName = evt.getPropertyName();
            if (propertyName != null && this.propertyNames.contains(propertyName)) {
                sendStateChanged = true;
            }
        } else {
            sendStateChanged = true;
        }
        if (sendStateChanged) {
            ChangeEvent ce = new ChangeEvent( evt.getSource() );
            cl.stateChanged( ce );
        }
    }
}
