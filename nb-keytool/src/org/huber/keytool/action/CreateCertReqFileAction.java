package org.huber.keytool.action;

import java.awt.Component;
import java.awt.Dialog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;

import javax.swing.JComponent;

import org.huber.keytool.KeyStoreTopComponent;
import org.huber.keytool.model.ConstantsHelper;
import org.huber.keytool.model.KeyToolUtils;
import org.huber.keytool.ui.wizard.certreq.ChooseCertReqOptionsWizardPanel;
import org.huber.keytool.ui.wizard.certreq.SelectCertReqFileWizardPanel;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

/**
 * An action creating a certificate request file.
 *
 */
public final class CreateCertReqFileAction extends CallableSystemAction {
    
    private WizardDescriptor.Panel[] panels;
    private WizardDescriptor wizardDescriptor;
    
    public void performAction() {
        final KeyStoreTopComponent win = KeyStoreTopComponent.findInstance();
        if (win != null) {
            final String prevKeyStoreName = win.getKeyStoreBean().getName();
            final String[] aliases = win.getKeyStoreEntryTableModel().getAllAliases();
            
            final boolean cancelled = showWizard(prevKeyStoreName, aliases);
            
            if (!cancelled) {
                 File certReqFile =  (File)this.wizardDescriptor.getProperty( "selectedFile" );
                certReqFile = certReqFile.getAbsoluteFile();
                
                final String sigAlg = (String)this.wizardDescriptor.getProperty( "sigAlg" );
                final String alias = (String)this.wizardDescriptor.getProperty( "alias" );
                char[] keyPassword = (char[])this.wizardDescriptor.getProperty( "keyPassword" );
                
                final KeyStore keyStore = win.getKeyStoreBean().getKeyStore();
                final char[] storePass = win.getKeyStoreBean().getStorePassword();
                
                if (keyPassword == null) {
                    keyPassword = storePass;
                }
                
                try {
                    boolean createdCertReqFileSuccessfully = createCertReqFile(
                            certReqFile,
                            alias,
                            sigAlg,
                            keyPassword,
                            keyStore,
                            storePass
                            );
                    final String title = NbBundle.getMessage( CreateCertReqFileAction.class,
                            "TTL_SummaryCreateCertReqFile" );
                    final String message = NbBundle.getMessage( CreateCertReqFileAction.class,
                            "MSG_SummaryCreateCertReqFileSuccess",
                            new Object[] { certReqFile.toString() } );
                    displayUserFeedback( NotifyDescriptor.INFORMATION_MESSAGE, 
                            title, message );
                } catch (Exception ex) {
                    certReqFile.delete();
                    
                    final String exMsg = ex.getMessage();
                    final String title = NbBundle.getMessage( CreateCertReqFileAction.class,
                            "TTL_SummaryCreateCertReqFile" );
                    final String message = NbBundle.getMessage( CreateCertReqFileAction.class,
                            "MSG_SummaryCreateCertReqFileFailure",
                            new Object[] { certReqFile.toString(), exMsg } );
                    
                    displayUserFeedback( NotifyDescriptor.ERROR_MESSAGE, 
                            title, message );
                }
            }
        }
    }
    
    private boolean createCertReqFile(File certReqFile, 
            String alias, String sigAlg, 
            char[] keyPassword, KeyStore keyStore, char[] storePass) throws Exception {
        FileOutputStream fos = null;
        boolean createdCertReqFileSuccessfully = false;
        try {
            fos = new FileOutputStream(certReqFile);
            PrintStream ps = new PrintStream(fos);
            
            doCertReq( ps, 
                    keyStore, storePass, 
                    alias, keyPassword, sigAlg );
            ps.flush();
            createdCertReqFileSuccessfully = true;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                createdCertReqFileSuccessfully = false;
            }
        }
        return createdCertReqFileSuccessfully;
    }
    
    private void displayUserFeedback(int messageType, String title, String message ) {

        final NotifyDescriptor nd = new NotifyDescriptor(
                message, 
                title,
                NotifyDescriptor.OK_CANCEL_OPTION, 
                messageType,
                null, null
                );
        DialogDisplayer.getDefault().notify(nd);
    }

    /**
     * Create a certificate request.
     *
     * @param printstream write certificate request to this print stream
     * @param keyStore the key store
     * @param storePass the store password
     * @param alias the alias
     * @param keyPass the key password
     * @param sigAlg the signature algorithm
     */
    private void doCertReq( PrintStream printstream,
            KeyStore keyStore, char[] storePass,
            String alias, char[] keyPass, String sigAlg ) throws Exception {

        Object aobj[] = KeyToolUtils.recoverPrivateKey(keyStore, storePass, alias, keyPass);
        PrivateKey privatekey = (PrivateKey)aobj[0];
        if(keyPass == null) {
            keyPass = (char[])(char[])aobj[1];
        }
        final Certificate certificate = keyStore.getCertificate(alias);
        if(certificate == null) {
            final String message = NbBundle.getMessage( CreateCertReqFileAction.class,
                    "ERR_MISSING_PULIC_KEY", new Object[] { alias } );
            
            throw new Exception( message );
        }
        sun.security.pkcs.PKCS10 pkcs10 = new sun.security.pkcs.PKCS10(certificate.getPublicKey());
        if(sigAlg == null) {
            final String privateKeySigAlg = privatekey.getAlgorithm();
            if(privateKeySigAlg.equalsIgnoreCase("DSA") || privateKeySigAlg.equalsIgnoreCase("DSS")) {
                sigAlg = "SHA1WithDSA";
            } else {
                if(privateKeySigAlg.equalsIgnoreCase("RSA")) {
                    sigAlg = "MD5WithRSA";
                } else {
                    throw new Exception("Cannot derive signature algorithm");
                }
            }
        }
        Signature signature = Signature.getInstance(sigAlg);
        signature.initSign(privatekey);
        sun.security.x509.X500Name x500name = new sun.security.x509.X500Name(((X509Certificate)certificate).getSubjectDN().toString());
        sun.security.x509.X500Signer x500signer = new sun.security.x509.X500Signer(signature, x500name);
        
        pkcs10.encodeAndSign(x500signer);
        pkcs10.print(printstream);
    }

    
    private boolean showWizard(String prevKeyStoreName, String[] aliases) {
        wizardDescriptor = new WizardDescriptor(getPanels());
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
        wizardDescriptor.setTitle(NbBundle.getMessage( CreateCertReqFileAction.class, "TTL_CreateCertReqFile") );
        
        this.wizardDescriptor.putProperty( "currentDirectoryOrFile", prevKeyStoreName );
        this.wizardDescriptor.putProperty("aliases", aliases );
        
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
                new SelectCertReqFileWizardPanel(),
                new ChooseCertReqOptionsWizardPanel(),
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
        return NbBundle.getMessage( CreateCertReqFileAction.class, "ACT_CreateCertReqFile" );
    }
    public String iconResource() {
        return "org/huber/keytool/images/certreq-new16x16.png";
    }
        
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
}
