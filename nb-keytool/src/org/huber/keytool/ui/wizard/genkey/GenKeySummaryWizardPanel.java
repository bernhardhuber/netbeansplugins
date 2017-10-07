package org.huber.keytool.ui.wizard.genkey;

import java.awt.Component;
import org.huber.keytool.model.ConstantsHelper;
import org.huber.keytool.ui.wizard.AbstractWizardDescriptorPanel;
import org.huber.keytool.ui.wizard.ObserverablePanel;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class GenKeySummaryWizardPanel extends AbstractWizardDescriptorPanel {
    
    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private GenKeySummaryPanel component;
    
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public Component getComponent() {
        if (component == null) {
            component = new GenKeySummaryPanel();
            this.component.bind( this.getChangeObserver() );
        }
        return component;
    }
    
    public HelpCtx getHelp() {
        return ConstantsHelper.getHelpCtx();
    }
    
    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        // return true;
        // If it depends on some condition (form filled out...), then:
        // return someCondition();
        final ObserverablePanel.ValidUserEntryResult vuer = this.component.isValidUserEntry();
        updateErrorMessage( vuer );
        return vuer.isValid();
        
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
        
        final Object obj = wd.getProperty( GenKeyBasicPanelWizardPanel.GEN_KEY_FORM_WZ_PROP );
        final GenKeyForm genKeyForm = (GenKeyForm)obj;
        
        final String genKeyFormFormatted = genKeyForm.format();
        this.component.setText(genKeyFormFormatted);
    }
    public void storeSettings(Object settings) {}
    
}

