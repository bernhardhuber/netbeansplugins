package org.huberb.nbwordcount;

import javax.swing.SwingUtilities;
import org.huberb.nbwordcount.model.WordCountBean;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.Cancellable;
import org.openide.util.NbBundle;

/**
 * This class implements the task of examing the
 * counter information asynchrounsly.
 */
class WordCountingActionHelper implements Cancellable {
    private boolean isCancelled = false;
    private final WordCountBean wcb;
    private final ProgressHandle ph;
    
    /** Creates a new instance of WordCountBean */
    public WordCountingActionHelper() {
        this.isCancelled = false;
        // create the bean holding the counting info
        this.wcb = new WordCountBean();
        
        // create the ProgessHandle
        final String phDisplayName = NbBundle.getMessage(WordCountingAction.class, "PRG_WordCountingAction");
        this.ph = ProgressHandleFactory.createHandle( phDisplayName, this );
    }
    
    // Implementation of Cancellable
    public boolean cancel() {
        this.isCancelled  = true;
        return this.isCancelled;
    }

    /**
     * Use this method for creating a <code>Runnable</code> used for
     * gathering counter information, asynchronously.
     *
     * @return Runnable exmining the counter information, and
     *   updating the UI
     */
    public Runnable createTask(final Node[] activatedNodes) {
        // reset cancel, the previous action may have been cancelled
        this.isCancelled = false;
        
        // create a Runnable presenting the result in the Swing's EventDispatchThread
        final Runnable viewResult = new Runnable() {
            public void run() {
                // get name, and values of the counters
                final Long[] counters = wcb.getCounters();
                final String namesOfCounters = wcb.getNamesOfFiles();
                final int countOfFiles = wcb.getCountOfFiles();
                
                // Update the WordCount TopComponent window
                final WordCountTopComponent win = WordCountTopComponent.findInstance();
                if (win != null) {
                    win.setNamesOfCounters( countOfFiles, namesOfCounters );
                    win.setCounters( counters );
                    win.requestActive();
                }
            }
        };
        
        // create a Runnable not running in the Swing EventDispatchThread
        final Runnable task = new Runnable() {
            public void run() {
                try {
                    // start the progress
                    ph.start();
                    // calculate the count info
                    countAllNodes( activatedNodes );
                } finally {
                    // finish the progress
                    ph.finish();
                    // present the result in Swing's EventDispathThread
                    SwingUtilities.invokeLater( viewResult );
                }
            }
        };
        
        return task;
    }
    
    /**
     * Iterate over all activedNodes object.
     *
     * @param activatedNodes the nodes for which the count info is calculated
     */
    private void countAllNodes(Node[] activatedNodes) {
        // loop over all activated nodes
        for (int i = 0; !this.isCancelled && i < activatedNodes.length; i++) {
            final DataObject dataObject = (DataObject) activatedNodes[i].getCookie(DataObject.class);
            final FileObject fileObject = dataObject.getPrimaryFile();
            
            count( fileObject );
        }
    }
    
    /**
     * Calculate counts for a FileObject.
     * <p>
     * Either calculate the counts if the FileObject is real file, or
     * if the FileObject is a directory, recursivly calculate the counts for
     * all children od this directory.
     *
     * @param fo the FileObject for which the count info is calculated
     */
    private void count(FileObject fo) {
        if (fo.isFolder()) {
            final boolean folderIsOkay = isFolderOkay(fo);
            if (!folderIsOkay) {
                return ;
            }
            final FileObject[] foChildren = fo.getChildren();
            for (int i = 0; !this.isCancelled && i < foChildren.length; i++) {
                count( foChildren[i] );
            }
        }  else if (fo.isData()) {
            final boolean fileIsOkay = isFileOkay(fo);
            if (!fileIsOkay) {
                return ;
            }
            
            // update the progressHandle
            final String foProgress = fo.getNameExt();
            ph.progress( foProgress );
            
            wcb.setFileObject(fo);
            wcb.count();
        }
    }
    
    /**
     * Check if folder is ok for counting
     * @param foFolder the folder file object
     * @return boolean true if we shall count files of this folder
     */
    protected boolean isFolderOkay(FileObject foFolder) {
        boolean folderIsOkay = foFolder.canRead();
        folderIsOkay = folderIsOkay  && !foFolder.getName().toLowerCase().equals("cvs");
        return folderIsOkay;
    }
    /**
     * Check if file is ok for counting
     * @param foFile the file object
     * @return boolean true if we shall examine this file for counting
     */
    protected boolean isFileOkay(FileObject foFile) {
        boolean fileIsOkay = foFile.canRead();
        fileIsOkay = fileIsOkay && foFile.getExt().endsWith("java");
        return fileIsOkay;
    }
}