/*
 * LoadKeyStoreDropTargetListener.java
 *
 * Created on 06. Juni 2007, 10:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.action;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;

/**
 * A DropTarget listener accepting key store files.
 *
 * @author HuberB1
 */
public class LoadKeyStoreDropTargetListener extends DropTargetAdapter {
    private static final Logger logger = Logger.getLogger(LoadKeyStoreDropTargetListener.class.getName());
    
    private LoadKeyStoreWizardLauncher lkswl;
    private DataFlavor dataObjectFlavor;
    /** Creates a new instance of LoadKeyStoreDropTargetListener */
    public LoadKeyStoreDropTargetListener() {
        this.lkswl = new LoadKeyStoreWizardLauncher();
        try {
            dataObjectFlavor = new DataFlavor("application/x-java-openide-dataobjectdnd; class=org.openide.loaders.DataObject");
        } catch (ClassNotFoundException ex) {
            logger.log(Level.WARNING,"Cannot setup DataObject DataFlavour", ex );
        } catch (IllegalArgumentException ex) {
            logger.log(Level.WARNING,"Cannot setup DataObject DataFlavour", ex );
        }
    }
    
    /**
     * handle drop
     */
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
        
        final Transferable trans = dtde.getTransferable();
        boolean gotData = false;
        
        dumpDataFlavors(trans);
        
        File theFile = null;
        try {
            if (trans.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                List list = (List)trans.getTransferData(DataFlavor.javaFileListFlavor);
                if (list.size() == 1) {
                    theFile = (File)list.get(0);
                    gotData = this.lkswl.isValidKeyStore(theFile);
                }
            } else if (trans.isDataFlavorSupported(dataObjectFlavor)) {
            // handle : "java.awt.datatransfer.DataFlavor[mimetype=application/x-java-openide-dataobjectdnd;representationclass=org.openide.loaders.DataObject]"
                DataObject dataObject = (DataObject)trans.getTransferData(dataObjectFlavor);
                FileObject fo = dataObject.getPrimaryFile();
                if (fo != null) {
                    gotData = this.lkswl.isValidKeyStore(fo);
                    if (gotData) {
                        theFile = FileUtil.toFile(fo);
                    }
                }
            }
        } catch (Exception e) {
            ErrorManager.getDefault().notify(ErrorManager.WARNING, e);
        } finally {
            dtde.dropComplete(gotData);
        }
        
        //
        if (gotData && theFile != null) {
            this.lkswl.launchWizard(theFile);
        }
    }
    
    private void dumpDataFlavors(Transferable trans) {
        logger.info("Flavors:");
        DataFlavor[] flavors = trans.getTransferDataFlavors();
        for (int i=0; i<flavors.length; i++) {
            logger.info("*** " + i + ": " + flavors[i]);
        }
    }
}
