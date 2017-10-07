/*
 * EditNodeAction.java
 *
 * Created on 07. Oktober 2006, 14:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.httppost.actions;

import org.huberb.httppost.HttpPostTopComponent;
import org.huberb.httppost.model.HttpPostForm;
import org.huberb.httppost.node.HttpPostFormNode;
import org.huberb.httppost.util.ConstantsHelper;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;

/**
 * 
 * @author HuberB1
 */
public class EditNodeAction extends NodeAction {
    
    /** Creates a new instance of EditNodeAction */
    public EditNodeAction() {
    }
    
    @Override
    protected void performAction(Node[] node) {
        if (enable(node)) {
            HttpPostFormNode hpfn = (HttpPostFormNode)node[0];
            
            final HttpPostTopComponent win = HttpPostTopComponent.findInstance();
            final HttpPostForm hpf = hpfn.getHttpPostForm();
            win.updateHttpPostForm( hpf );
        }
    }
    
    @Override
    protected boolean enable(Node[] node) {
        boolean enableNode = node != null && node.length == 1;
        enableNode = enableNode && node[0] instanceof HttpPostFormNode;
        return enableNode;
    }
    
    @Override
    public String getName() {
        return NbBundle.getMessage(HttpPostAction.class, "CTL_EditNodeAction");
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
