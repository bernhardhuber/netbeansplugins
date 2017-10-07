/*
 * WizardChangeListener.java
 *
 * Created on 17. Februar 2006, 15:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.bind;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author HuberB1
 */
public class ActionChangeListenerBridge implements ActionListener {
    private ChangeListener cl;
    
    /** Creates a new instance of WizardChangeListener */
    public ActionChangeListenerBridge(ChangeListener cl) {
        this.cl = cl;
    }
    public void actionPerformed(ActionEvent e) {
        ChangeEvent ce = new ChangeEvent( e.getSource() );
        cl.stateChanged( ce );
    }
}
