package org.huber.keytool.action;

import java.io.File;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

public final class LoadKeyStoreCookieAction extends CookieAction {
    private LoadKeyStoreWizardLauncher lkswl;

    protected void performAction(Node[] activatedNodes) {

        final DataObject dataObject = (DataObject) activatedNodes[0].getCookie(DataObject.class);
        final FileObject fo = dataObject.getPrimaryFile();
        final File keyStoreFile = FileUtil.toFile(fo);

        lkswl.launchWizard( keyStoreFile );
    }

    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    public String getName() {
        return NbBundle.getMessage(LoadKeyStoreCookieAction.class, "CTL_LoadKeyStoreCookieAction");
    }

    protected Class[] cookieClasses() {
        return new Class[] {
            DataObject.class
        };
    }

    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);

        this.lkswl = new LoadKeyStoreWizardLauncher();
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    protected boolean asynchronous() {
        return false;
    }

    protected boolean enable(Node[] nodes) {
        if (nodes == null || nodes.length == 0 || nodes.length > 1) {
            return false;
        }
        DataObject dataObject = (DataObject) nodes[0].getCookie(DataObject.class);
        //Action is disabled for root folder eg:"/" on Linux or "C:" on Win
        if (dataObject == null) {
            return false;
        }
        final FileObject fo = dataObject.getPrimaryFile();
        if (fo == null) {
            return false;
        }
        return lkswl.isValidKeyStore(fo);
    }

}

