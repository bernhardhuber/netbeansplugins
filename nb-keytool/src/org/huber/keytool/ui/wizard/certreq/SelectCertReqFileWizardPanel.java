package org.huber.keytool.ui.wizard.certreq;

import java.awt.Component;
import java.io.File;
import org.huber.keytool.model.ConstantsHelper;
import org.huber.keytool.ui.wizard.AbstractWizardDescriptorPanel;
import org.huber.keytool.ui.wizard.ObserverablePanel;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class SelectCertReqFileWizardPanel extends AbstractWizardDescriptorPanel {
    
    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private SelectCertReqFilePanel component;
    
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public Component getComponent() {
        if (component == null) {
            component = new SelectCertReqFilePanel();
            this.component.bind( this.getChangeObserver() );
        }
        return component;
    }
    
    public HelpCtx getHelp() {
        return ConstantsHelper.getHelpCtx();
    }
    
    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        ObserverablePanel.ValidUserEntryResult vuer = this.component.isValidUserEntry();
        updateErrorMessage( vuer );
        return vuer.isValid();
        
        // If it depends on some condition (form filled out...), then:
        // return someCondition();
        // and when this condition changes (last form field filled in...) then:
        // fireChangeEvent();
        // and uncomment the complicated stuff below.
    }
    
    
    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    public void readSettings(Object settings) {
        if (this.component != null) {
            this.component.reset();
        }
        
        this.wd = (WizardDescriptor)settings;
        final String currentDirectoryOrFilecurrentDirectoryOrFile = (String)wd.getProperty( "currentDirectoryOrFile" );
        
        this.component.setCurrentDirectoryOrFilecurrentDirectoryOrFile( currentDirectoryOrFilecurrentDirectoryOrFile );
    }
    public void storeSettings(Object settings) {
        final WizardDescriptor wd = (WizardDescriptor)settings;
        File selectedFile = this.component.getSelectedFile();
        wd.putProperty( "selectedFile", selectedFile );
    }
    
}

