/*
 * WizardChangeListener.java
 *
 * Created on 17. Februar 2006, 15:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.bind;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 *
 * @author HuberB1
 */
public class DocumentChangeListenerBridge implements DocumentListener {
    private ChangeListener cl;
    
    /** Creates a new instance of WizardChangeListener */
    public DocumentChangeListenerBridge(ChangeListener cl) {
        this.cl = cl;
    }
    
    public void insertUpdate(DocumentEvent e) {
        firstStateChanged(e);
    }
    
    public void removeUpdate(DocumentEvent e) {
        firstStateChanged(e);
    }
    
    public void changedUpdate(DocumentEvent e) {
        firstStateChanged(e);
    }
    
    private void firstStateChanged( DocumentEvent e) {
        final Document d = e.getDocument();
        final ChangeEvent ce = new ChangeEvent(d);
        cl.stateChanged( ce );
        
    }
}
