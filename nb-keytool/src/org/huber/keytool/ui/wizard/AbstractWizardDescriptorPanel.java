/*
 * WizardPanelChangeListener.java
 *
 * Created on 17. Februar 2006, 20:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.wizard;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.openide.WizardDescriptor;

/**
 * An abstract WizardDescriptor.Panel it supports
 * adding, removing of change listeners.
 * @author HuberB1
 */
public abstract class AbstractWizardDescriptorPanel implements WizardDescriptor.Panel  {
    
    protected static final String ERROR_MESSAGE = "WizardPanel_errorMessage";
    protected WizardDescriptor wd;
    
    private ChangeObserverOfWizardPanel changeObserver;
    
    /** Creates a new instance of WizardPanelChangeListener */
    public AbstractWizardDescriptorPanel() {
        this.changeObserver = new ChangeObserverOfWizardPanel(this);
    }
    
    /**
     * Get the ChangeObserver for this wizard panel.
     *
     * @return ChangeObserverOfWizardPanel object of this wizard panel
     */
    protected ChangeObserverOfWizardPanel getChangeObserver() {
        return this.changeObserver;
    }
    
    private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);
    public final void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }
    
    public final void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }
    
    protected final void fireChangeEvent() {
        Iterator<ChangeListener> it;
        synchronized (listeners) {
            it = new HashSet<ChangeListener>(listeners).iterator();
        }
        final ChangeEvent ev = new ChangeEvent(this);
        while (it.hasNext()) {
            it.next().stateChanged(ev);
        }
    }
    
    protected void updateErrorMessage( ObserverablePanel.ValidUserEntryResult vuer ) {
        if (!vuer.isValid()) {
            this.setErrorMessage( vuer.getInvalidMessage() );
        } else {
            this.setErrorMessage( "" );
        }
    }
    protected void setErrorMessage( String message ) {
        if (wd != null && message != null) {
            this.wd.putProperty( AbstractWizardDescriptorPanel.ERROR_MESSAGE, message );
        }
    }
}
