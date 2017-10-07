package org.huber.keytool.action;

import org.huber.keytool.KeyStoreTopComponent;
import org.huber.keytool.model.ConstantsHelper;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class OpenKeyStoreTopComponentAction extends CallableSystemAction {
    
    public void performAction() {
        final KeyStoreTopComponent win = KeyStoreTopComponent.findInstance();
        if (win != null) {
            win.open();
            win.requestActive();
        }
    }
    
    public String getName() {
        return NbBundle.getMessage(OpenKeyStoreTopComponentAction.class, "CTL_OpenKeyStoreTopComponentAction");
    }
        
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }
    
    protected boolean asynchronous() {
        return false;
    }
    
    private final static String ICON_RESOURCE = "org/huber/keytool/images/password-16x16.png";
    protected String iconResource() {
        return ICON_RESOURCE;
    }
    
}
