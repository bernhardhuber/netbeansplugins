/*
 * WizardChangeListener.java
 *
 * Created on 17. Februar 2006, 15:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.bind;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author HuberB1
 */
public class CaretChangeListenerBridge implements CaretListener {
    private ChangeListener cl;
    
    /** Creates a new instance of WizardChangeListener */
    public CaretChangeListenerBridge(ChangeListener cl) {
        this.cl = cl;
    }

    public void caretUpdate(CaretEvent e) {
        ChangeEvent ce = new ChangeEvent(e.getSource());
        cl.stateChanged( ce );
    }    
}
