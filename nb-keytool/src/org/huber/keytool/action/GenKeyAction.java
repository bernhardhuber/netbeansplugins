package org.huber.keytool.action;

import java.awt.Component;
import java.awt.Dialog;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;

import javax.swing.JComponent;

import org.huber.keytool.KeyStoreTopComponent;
import org.huber.keytool.model.ConstantsHelper;
import org.huber.keytool.model.KeyStoreBean;
import org.huber.keytool.ui.wizard.genkey.GenKeyBasicPanelWizardPanel;
import org.huber.keytool.ui.wizard.genkey.GenKeyDNamePanelWizardPanel;
import org.huber.keytool.ui.wizard.genkey.GenKeyForm;
import org.huber.keytool.ui.wizard.genkey.GenKeySummaryWizardPanel;
import org.huber.keytool.ui.wizard.genkey.GenerateCertificateAndKey;
import org.openide.DialogDisplayer;
import org.openide.ErrorManager;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

// An example action demonstrating how the wizard could be called from within
// your code. You can copy-paste the code below wherever you need.
/**
 * Create a new Key.
 * <p>
 * This action involves following steps
 * <ul>
 * <li>Gather key info, using a wizard
 * <li>Create a new key in the current keyStore
 * </ul>
 */
public final class GenKeyAction extends CallableSystemAction {
    
    private WizardDescriptor wizardDescriptor;
    private WizardDescriptor.Panel[] panels;
    
    public GenKeyAction() {
    }
    
    public String getName() {
        return NbBundle.getMessage( GenKeyAction.class, "ACT_GenKey" );
    }
    
    public String iconResource() {
        return "org/huber/keytool/images/password-create16x16.png";
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
            //final String prevKeyStoreName = win.getKeyStoreBean().getName();
            final boolean cancelled = showWizard();
            if (!cancelled) {
                final GenKeyForm genKeyForm = (GenKeyForm)this.wizardDescriptor.getProperty( GenKeyBasicPanelWizardPanel.GEN_KEY_FORM_WZ_PROP );
                final KeyStoreBean ksb = win.getKeyStoreBean();
                final boolean generatedCertificate = generateCertificate( genKeyForm, ksb );
                if (generatedCertificate) {
                    win.refreshKeyStoreView();
                }
            }
        }
    }
    
    private boolean generateCertificate( GenKeyForm genKeyForm, KeyStoreBean ksb ) {
        final KeyStore keyStore = ksb.getKeyStore();
        final String alias = genKeyForm.getAlias();
        boolean generatedCertificate = false;
        
        // create the certificate
        try {
            if (!keyStore.containsAlias( alias )) {
                final GenerateCertificateAndKey gck = new GenerateCertificateAndKey();
                gck.generateCertificate( genKeyForm );
                final PrivateKey privKey = gck.getPrivKey();
                final X509Certificate aX509Certificate = gck.getChain()[0];
                
                // define the password of the certificate
                // if no certificate password is defined
                // use the keyStore password
                final char[] keyStorePassword = ksb.getStorePassword();
                char[] password = genKeyForm.getKeyPassword();
                if (password == null || password.length == 0) {
                    password = keyStorePassword;
                }
                
                keyStore.setKeyEntry( alias,
                        privKey,
                        password,
                        new Certificate[] {aX509Certificate} );
                
                generatedCertificate = true;
            }
        } catch (Exception ex) {
            ErrorManager.getDefault().notify( ex );
        }
        return generatedCertificate;
    }
    
    /**
     * Show the generate certificate wizard
     *
     * @param boolean true if user wants to generate the certificate, else false
     */
    private boolean showWizard() {
        this.wizardDescriptor = new WizardDescriptor(getPanels());
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
        wizardDescriptor.setTitle( NbBundle.getMessage( GenKeyAction.class, "TTL_GenKey" ) );
        
        final GenKeyForm genKeyForm = new GenKeyForm();
        wizardDescriptor.putProperty( GenKeyBasicPanelWizardPanel.GEN_KEY_FORM_WZ_PROP, genKeyForm );
        
        final Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();
        final boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        return cancelled;
    }
    
    /**
     * Initialize panels representing individual wizard's steps and sets
     * various properties for them influencing wizard appearance.
     */
    private WizardDescriptor.Panel[] getPanels() {
        if (panels == null) {
            panels = new WizardDescriptor.Panel[] {
                new GenKeyBasicPanelWizardPanel(),
                new GenKeyDNamePanelWizardPanel(),
                new GenKeySummaryWizardPanel()
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

