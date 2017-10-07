package org.huber.keytool.action;

import java.awt.Component;
import java.awt.Dialog;
import java.io.File;
import java.security.KeyStore;
import java.text.MessageFormat;

import javax.swing.JComponent;

import org.huber.keytool.KeyStoreTopComponent;
import org.huber.keytool.model.ConstantsHelper;
import org.huber.keytool.model.KeyStoreBean;
import org.huber.keytool.ui.wizard.newkeystore.EnterKeyStorePasswordWizardPanel;
import org.huber.keytool.ui.wizard.newkeystore.SelectKeyStoreWizardPanel;
import org.openide.DialogDisplayer;
import org.openide.ErrorManager;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

/**
 * Create a new KeyStore.
 * <p>
 * This action involves following steps
 * <ul>
 * <li>Gather keyStore info, using a wizard
 * <li>Create the keyStore in memory only
 * </ul>
 *
 */
public final class NewKeyStoreAction extends CallableSystemAction {
    
    private WizardDescriptor.Panel[] panels;
    private WizardDescriptor wizardDescriptor;
    
    public void performAction() {
        final KeyStoreTopComponent win = KeyStoreTopComponent.findInstance();
        if (win != null) {
            final String prevKeyStoreName = win.getKeyStoreBean().getName();
            final boolean cancelled = showWizard(prevKeyStoreName);
            if (!cancelled) {
                final File keyStoreFile =  (File)this.wizardDescriptor.getProperty( "selectedFile" );
                final char[] keyStorePassword = (char[])this.wizardDescriptor.getProperty("storePassword");
                final KeyStore ks = createEmptyKeyStore( keyStorePassword );
                if (ks != null) {
                    final KeyStoreBean ksb = new KeyStoreBean(keyStoreFile.getAbsolutePath(), ks, keyStorePassword );
                    win.setKeyStoreBean( ksb );
                }
            }
        }
    }
    
    /**
     * Create an empty KeyStore
     *
     * @param keyStorePassword the password of the keyStore
     * @return KeyStore or null if creation fails
     */
    private KeyStore createEmptyKeyStore(char[] keyStorePassword ) {
        KeyStore keyStore = null;
        final String keyStoreType = KeyStore.getDefaultType();
        try {
            keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load( null, keyStorePassword );
        } catch (Exception e) {
            ErrorManager.getDefault().notify( e );
            keyStore = null;
        }
        return keyStore;
    }
    
    private boolean showWizard(String prevKeyStoreName) {
        wizardDescriptor = new WizardDescriptor(getPanels());
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
        wizardDescriptor.setTitle(NbBundle.getMessage( NewKeyStoreAction.class, "TTL_CreateKeyStoreYour") );
        
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
                new SelectKeyStoreWizardPanel(),
                new EnterKeyStorePasswordWizardPanel(),
            };
            final String[] steps = new String[panels.length];
            for (int i = 0; i < panels.length; i++) {
                final Component c = panels[i].getComponent();
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
    
    public String getName() {
        return NbBundle.getMessage( NewKeyStoreAction.class, "ACT_CreateKeyStore" );
    }
    public String iconResource() {
        return "org/huber/keytool/images/password-new16x16.png";
    }
    
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}
