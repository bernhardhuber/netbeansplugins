/*
 * AbstractActivatedNodeHandler.java
 *
 * Created on 19. Mai 2007, 22:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator;

import java.util.Enumeration;
import org.huberb.i18nvalidator.misc.ProgressHandleHelper;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;

/**
 *
 * @author HuberB1
 */
public abstract class AbstractActivatedNodeHandler {
    private boolean recursivly;
    private String displayNameProgressHandle;
    private ProgressHandleHelper phh;

    /**
     * Creates a new instance of AbstractActivatedNodeHandler
     */
    public AbstractActivatedNodeHandler() {
        this(false);
    }
    /**
     * Creates a new instance of AbstractActivatedNodeHandler
     */
    public AbstractActivatedNodeHandler(boolean recursivly) {
        this.recursivly = recursivly;
        this.displayNameProgressHandle = "NodeHandler";
    }

    public void setDisplayNameProgressHandle(String displayNameProgressHandle) {
        this.displayNameProgressHandle = displayNameProgressHandle;
    }
    public String getDisplayNameProgressHandle() {
        return displayNameProgressHandle;
    }


    public void handleActivatedNodes(Node[] activatedNodes) {
        final String displayName = this.displayNameProgressHandle;
        final int steps = activatedNodes.length;
        this.phh = new ProgressHandleHelper(steps, displayName );

        try {
            for (int i = 0; i < activatedNodes.length; i++ ) {
                final Node node = activatedNodes[i];
                handleActivatedNode( node );
                this.phh.progress();
            }
        } finally {
            phh.finish();
        }
    }

    public void handleActivatedNode( Node node ) {
        DataObject c = (DataObject)node.getCookie(DataObject.class);
        FileObject fo = c.getPrimaryFile();

        if (fo.isData()) {
            handleSingleFileObjectWrapper(fo);
        } else if (fo.isFolder()) {
            for (Enumeration children = fo.getData(recursivly); children.hasMoreElements(); ) {
                FileObject childFo = (FileObject)children.nextElement();
                if (childFo.isData()) {
                    handleSingleFileObjectWrapper(childFo);
                }
            }
        }
    }

    protected void handleSingleFileObjectWrapper(FileObject fo ) {
        try {
            this.phh.progress( fo.getPath() );
            handleSingleFileObject(fo);
        } finally {

        }
    }

    protected abstract void handleSingleFileObject(FileObject fo);
}
