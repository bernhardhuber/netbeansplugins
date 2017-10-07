/*
 * WizardChangeListener.java
 *
 * Created on 17. Februar 2006, 20:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.wizard;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A change listener implements firing a change event to the 
 * WizardDescriptor.Panel.
 *
 * @author HuberB1
 */
public class WizardPanelChangeListener implements ChangeListener {
    private AbstractWizardDescriptorPanel awdp;
    
    /** Creates a new instance of WizardChangeListener */
    public WizardPanelChangeListener(AbstractWizardDescriptorPanel awdp) {
        this.awdp = awdp;
    }
    
    public void stateChanged( ChangeEvent ce ) {
        this.awdp.fireChangeEvent();
    }
    
}
