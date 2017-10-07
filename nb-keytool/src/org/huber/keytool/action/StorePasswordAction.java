package org.huber.keytool.action;

import java.awt.Component;
import java.awt.Dialog;
import java.io.File;
import java.text.MessageFormat;

import javax.swing.JComponent;

import org.huber.keytool.KeyStoreTopComponent;
import org.huber.keytool.model.ConstantsHelper;
import org.huber.keytool.model.KeyStoreBean;
import org.huber.keytool.ui.wizard.storepasswd.StorePasswordWizardPanel1;
import org.huber.keytool.ui.wizard.storepasswd.StorePasswordWizardPanel2;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

// An example action demonstrating how the wizard could be called from within
// your code. You can copy-paste the code below wherever you need.
public final class StorePasswordAction extends CallableSystemAction {
    
    private WizardDescriptor wizardDescriptor;
    private WizardDescriptor.Panel[] panels;
    
    public String getName() {
        return NbBundle.getMessage( StorePasswordAction.class, "ACT_StorePassword" );
    }
    
    public String iconResource() {
        return "org/huber/keytool/images/password-storepasswd16x16.png";
    }
    
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
    public void performAction() {
        final KeyStoreTopComponent win = KeyStoreTopComponent.findInstance();
        if (win != null) {
            final String prevKeyStoreName = win.getKeyStoreBean().getName();
            final boolean cancelled = showWizard(prevKeyStoreName);
            if (!cancelled) {
                final File keyStoreFile =  (File)this.wizardDescriptor.getProperty( "selectedFile" );
                final char[] keyStorePassword = (char[])this.wizardDescriptor.getProperty("storePassword");
                
                final KeyStoreBean ksb = win.getKeyStoreBean();
                ksb.setName( keyStoreFile.getAbsolutePath() );
                ksb.setStorePassword( keyStorePassword );
                
                ksb.saveKeyStoreFile();

            }
        }
    }
        
    /**
     * Show the generate certificate wizard
     *
     * @param boolean true if user wants to generate the certificate, else false
     */
    private boolean showWizard(String prevKeyStoreName) {
        
        this.wizardDescriptor = new WizardDescriptor(getPanels());
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
        wizardDescriptor.setTitle( NbBundle.getMessage( StorePasswordAction.class, "TTL_StorePassword" ));
        
        this.wizardDescriptor.putProperty( "currentDirectoryOrFile", prevKeyStoreName );
        
        final Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();
        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        return cancelled;
    }
    
    /**
     * Initialize panels representing individual wizard's steps and sets
     * various properties for them influencing wizard appearance.
     */
    private WizardDescriptor.Panel[] getPanels() {
        if (panels == null) {
            panels = new WizardDescriptor.Panel[] {
                new StorePasswordWizardPanel1(),
                new StorePasswordWizardPanel2()
            };
            String[] steps = new String[panels.length];
            for (int i = 0; i < panels.length; i++) {
                Component c = panels[i].getComponent();
                // Default step name to component name of panel. Mainly useful
                // for getting the name of the target chooser to appear in the
                // list of steps.
                steps[i] = c.getName();
                if (c instanceof JComponent) { // assume Swing components
                    JComponent jc = (JComponent) c;
                    // Sets step number of a component
                    jc.putClientProperty("WizardPanel_contentSelectedIndex", Integer.valueOf(i));
                    // Sets steps names for a panel
                    jc.putClientProperty("WizardPanel_contentData", steps);
                    // Turn on subtitle creation on each step
                    jc.putClientProperty("WizardPanel_autoWizardStyle", Boolean.TRUE);
                    // Show steps on the left side with the image on the background
                    jc.putClientProperty("WizardPanel_contentDisplayed", Boolean.TRUE);
                    // Turn on numbering of all steps
                    jc.putClientProperty("WizardPanel_contentNumbered", Boolean.TRUE);
                }
            }
        }
        return panels;
    }
    
}

