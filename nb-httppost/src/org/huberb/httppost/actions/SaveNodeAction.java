/*
 * EditNodeAction.java
 *
 * Created on 07. Oktober 2006, 14:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.httppost.actions;

import java.io.IOException;
import org.huberb.httppost.HttpPostTopComponent;
import org.huberb.httppost.model.Persistance;
import org.huberb.httppost.node.HttpPostFormFolderNode;
import org.huberb.httppost.node.HttpPostFormNode;
import org.huberb.httppost.util.ConstantsHelper;
import org.openide.ErrorManager;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;

/**
 *
 * @author HuberB1
 */
public class SaveNodeAction extends NodeAction {

    /**
     * Creates a new instance of EditNodeAction
     */
    public SaveNodeAction() {
    }

    @Override
    protected void performAction(Node[] node) {
        if (enable(node)) {
            HttpPostFormNode hpfn = (HttpPostFormNode) node[0];

            Node parentNode = hpfn.getParentNode();
            if (parentNode != null && parentNode instanceof HttpPostFormFolderNode) {
                HttpPostFormFolderNode hpffn = (HttpPostFormFolderNode) parentNode;
                try {
                    hpfn.destroy();
                    final HttpPostTopComponent win = HttpPostTopComponent.findInstance();
                    win.getSavedNodeFolder().getHttpPostFromChildren().add(new Node[]{hpfn});

                    Persistance persistance = new Persistance();
                    persistance.save(hpfn.getHttpPostForm());
                } catch (IOException ex) {
                    ErrorManager.getDefault().notify(ex);
                }

            }
        }
    }

    @Override
    protected boolean enable(Node[] node) {
        boolean enableNode = node != null && node.length >= 1;
        if (enableNode) {
            for (int i = 0; enableNode && i < node.length; i++) {
                enableNode = enableNode && node[i] instanceof HttpPostFormNode;
            }
        }
        return enableNode;
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(HttpPostAction.class, "CTL_SaveNodeAction");
    }

    @Override
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
    }

    @Override
    public HelpCtx getHelpCtx() {
        return ConstantsHelper.getHelpCtx();
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

}
