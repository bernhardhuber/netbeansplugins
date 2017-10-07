package org.huber.keytool.action;

import java.security.KeyStore;
import java.security.KeyStoreException;
import org.huber.keytool.KeyStoreTopComponent;
import org.huber.keytool.model.ConstantsHelper;

import org.huber.keytool.model.KeyStoreBean;
import org.huber.keytool.ui.KeyStoreEntryTableModel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.ErrorManager;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

/**
 * An action deleting a key store entry
 */
public final class DeleteKeyAction extends CallableSystemAction {
    
    public void performAction() {
        final KeyStoreTopComponent win = KeyStoreTopComponent.findInstance();
        if (win != null) {
            final KeyStoreEntryTableModel ksetm = win.getKeyStoreEntryTableModel();
            final KeyStoreBean ksb = win.getKeyStoreBean();
            final KeyStore keyStore = ksb.getKeyStore();
            
            if (keyStore != null) {
                final int[] selectedRows = win.getSelectedAliases();
                for (int i = 0; i < selectedRows.length; i++ ) {
                    final int rowIndex = selectedRows[i];
                    final String alias = ksetm.getAlias( rowIndex );
                    try {
                        if (keyStore.containsAlias(alias)) {
                            final boolean askUserDeleteKey = askUserDeleteKey(alias);
                            if (askUserDeleteKey) {
                                keyStore.deleteEntry( alias );
                            }
                        }
                    } catch (KeyStoreException kse) {
                        ErrorManager.getDefault().notify( kse );
                    }
                }
                win.refreshKeyStoreView();
            }
        }
    }
    
    public String getName() {
        return NbBundle.getMessage(DeleteKeyAction.class, "CTL_DeleteKeyAction");
    }
    public String iconResource() {
        return "org/huber/keytool/images/password-delete16x16.png";
    }
    
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
    /**
     * Ask the user if he really wants to delete the keystore entry
     *
     * @param alias identifying the keystore entry uniquely.
     * @return true if the user really wants to delete the keystore entry.
     */
    private boolean askUserDeleteKey(String alias) {
        final String message = NbBundle.getMessage(DeleteKeyAction.class, "MSG_DeleteKeyActionAskUser", alias );
        final String title = NbBundle.getMessage(DeleteKeyAction.class, "TTL_DeleteKeyActionAskUser");
        
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
