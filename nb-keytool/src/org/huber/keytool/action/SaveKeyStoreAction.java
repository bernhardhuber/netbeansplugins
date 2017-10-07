package org.huber.keytool.action;

import java.security.KeyStore;
import org.huber.keytool.KeyStoreTopComponent;
import org.huber.keytool.model.ConstantsHelper;
import org.huber.keytool.model.KeyStoreBean;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

/**
 * An action for saving a keystore file
 */
public final class SaveKeyStoreAction extends CallableSystemAction {
    
    public void performAction() {
        final KeyStoreTopComponent win = KeyStoreTopComponent.findInstance();
        if (win != null) {
            final KeyStoreBean ksb = win.getKeyStoreBean();
            final KeyStore ks = ksb.getKeyStore();
            
            final char[] password = ksb.getStorePassword();
            final String keyStoreFilename = ksb.getName();
            
            // ask the user
            final boolean askUserSaveKeyStore = askUserSaveKeyStore(keyStoreFilename);
            
            if (askUserSaveKeyStore && ks != null) {
                ksb.saveKeyStoreFile();
//                final File keyStoreFile = new File(keyStoreFilename);
//                saveKeyStoreFile( ks, keyStoreFile, password );
            }
        }
    }
    
//    private void saveKeyStoreFile(KeyStore ks, File keyStoreFile, char[] password) {
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(keyStoreFile);
//            ks.store( fos, password );
//        } catch (Exception ex) {
//            ErrorManager.getDefault().notify( ex );
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException ex) {
//                    ErrorManager.getDefault().notify( ErrorManager.INFORMATIONAL, ex );
//                }
//            }
//        }
//        
//    }
    
    public String getName() {
        return NbBundle.getMessage(SaveKeyStoreAction.class, "CTL_SaveKeyStoreAction");
    }
    public String iconResource() {
        return "org/huber/keytool/images/password-save16x16.png";
    }
    
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
    /**
     * Ask the user if he really wants to save the keystore file
     *
     * @param keyStoreFilename the name of the keystore file
     * @return true if the user really wants to save the keystore file.
     */
    private boolean askUserSaveKeyStore(String keyStoreFilename) {
        final String title = NbBundle.getMessage(SaveKeyStoreAction.class, "TTL_SaveKeyStoreActionAskUser");
        final String message = NbBundle.getMessage(SaveKeyStoreAction.class, "MSG_SaveKeyStoreActionAskUser", keyStoreFilename );
        
        final DialogDescriptor dd = new
                DialogDescriptor(message,
                title,
                true,
                DialogDescriptor.YES_NO_OPTION,
                DialogDescriptor.NO_OPTION,
                null);
        final DialogDisplayer dialogDisplayer = DialogDisplayer.getDefault();
        
        final Object answer = dialogDisplayer.notify(dd);
        final boolean userAnswersYes = answer == DialogDescriptor.YES_OPTION;
        return userAnswersYes;
    }
}
