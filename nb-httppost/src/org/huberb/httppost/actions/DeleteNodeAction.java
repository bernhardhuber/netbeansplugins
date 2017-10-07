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
public class DeleteNodeAction extends NodeAction {

    /**
     * Creates a new instance of EditNodeAction
     */
    public DeleteNodeAction() {
    }

    @Override
    protected void performAction(Node[] nodes) {

        if (enable(nodes)) {
            for (int i = 0; i < nodes.length; i++) {
                try {
                    Node node = nodes[i];
                    Node parentNode = node.getParentNode();
                    if (parentNode != null && parentNode instanceof HttpPostFormFolderNode) {
                        HttpPostFormFolderNode hpffn = (HttpPostFormFolderNode) parentNode;
                        if (!hpffn.getCanSave()) {
                            Persistance persistance = new Persistance();
                            HttpPostFormNode hpfn = (HttpPostFormNode) node;
                            persistance.delete(hpfn.getHttpPostForm());
                        }
                    }
                    node.destroy();
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
        return NbBundle.getMessage(HttpPostAction.class, "CTL_DeleteNodeAction");
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
