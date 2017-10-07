/*
 * HttpPostFormFolderNode.java
 *
 * Created on 27. September 2006, 22:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.httppost.node;

import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class HttpPostFormFolderNode extends AbstractNode {
    private static final Image IMAGE_FOLDER = ImageUtilities.loadImage("org/huberb/httppost/images/folder.gif"); // NOI18N
    private static final Image IMAGE_OPENFOLDER = ImageUtilities.loadImage("org/huberb/httppost/images/folderOpen.gif"); // NOI18N
    
    private boolean canSave;
    
    /**
     * Creates a new instance of HttpPostFormFolderNode
     */
    public HttpPostFormFolderNode(String name, boolean canSave ) {
        super(new HttpPostFormChildren());
        
        this.canSave = canSave;
        final String displayName = NbBundle.getMessage(HttpPostFormFolderNode.class, name );
        this.setDisplayName( displayName );
    }
    
    public HttpPostFormFolderNode( String name, Children children ) {
        super(children);
        
        this.canSave = false;
        final String displayName = NbBundle.getMessage(HttpPostFormFolderNode.class, name );
        this.setDisplayName( displayName );
    }
    
    public HttpPostFormChildren getHttpPostFromChildren() {
        return (HttpPostFormChildren)this.getChildren();
    }
    
    @Override
    public Image getIcon(int i) {
        return IMAGE_FOLDER;
    }
    
    @Override
    public Image getOpenedIcon(int i) {
        return IMAGE_OPENFOLDER;
    }

    public boolean getCanSave() {
        return this.canSave;
    }
}
