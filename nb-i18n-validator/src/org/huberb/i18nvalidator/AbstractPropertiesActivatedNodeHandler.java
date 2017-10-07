/*
 * AbstractActivatedNodeHandler.java
 *
 * Created on 19. Mai 2007, 22:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator;

import org.netbeans.modules.properties.BundleStructure;
import org.netbeans.modules.properties.PropertiesDataObject;
import org.netbeans.modules.properties.PropertiesFileEntry;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;

/**
 *
 * @author HuberB1
 */
public abstract class AbstractPropertiesActivatedNodeHandler extends AbstractActivatedNodeHandler {

    /**
     * Creates a new instance of AbstractPropertiesActivatedNodeHandler
     */
    public AbstractPropertiesActivatedNodeHandler () {
        this(false);
    }
    /**
     * Creates a new instance of AbstractPropertiesActivatedNodeHandler
     */
    public AbstractPropertiesActivatedNodeHandler (boolean recursivly) {
        super(recursivly);
    }

    public void handleActivatedNode( Node node ) {
        final DataObject c = (DataObject)node.getCookie(DataObject.class);
        if (c instanceof PropertiesDataObject) {
            PropertiesDataObject pdo = (PropertiesDataObject)c;
            BundleStructure bs = pdo.getBundleStructure();
            int iMax = bs.getEntryCount();
            for (int i = 0; i < iMax; i++ ) {
                PropertiesFileEntry pfe = bs.getNthEntry(i);
                FileObject fo = pfe.getFile();
                
                handleSingleFileObjectWrapper(fo);
            }
        } else {
            super.handleActivatedNode(node);
        }
    }

    protected abstract void handleSingleFileObject(FileObject fo);
}
