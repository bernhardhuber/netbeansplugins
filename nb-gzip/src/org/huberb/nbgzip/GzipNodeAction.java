/*
 * TestNodeAction.java
 *
 * Created on 16. Jänner 2006, 14:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbgzip;

import java.io.File;
import javax.swing.Action;
import org.huberb.nbgzip.operation.AskOperation;
import org.huberb.nbgzip.operation.DeleteOperation;
import org.huberb.nbgzip.operation.GzipCompressOperation;
import org.huberb.nbgzip.operation.GzipUncompressOperation;
import org.huberb.nbgzip.operation.ShallWeQuestions;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;

/**
 * An action for compressing, and uncompressing selected nodes.
 *
 * @author HuberB1
 */
public class GzipNodeAction extends NodeAction {
    protected final static long serialVersionUID = 20060116171900L;
    
    private static final String ICON_RESOURCE = "org/huberb/nbgzip/images/gzip.png";
    
    private static GzipNodeAction instance = new GzipNodeAction();
    
    private transient ShallWeQuestions shallWeQuestionsCompress;
    private transient ShallWeQuestions shallWeQuestionsUncompress;
    private transient ShallWeQuestions shallWeQuestionsDelete;
    
    public static Action getDefault() {
        return instance;
    }
    
    /** Creates a new instance of TestNodeAction */
    private GzipNodeAction() {
    }
    public String getName() {
        final String name = NbBundle.getMessage(GzipNodeAction.class, "CTL_GzipAction");
        return name;
    }
    protected String iconResource() {
        return ICON_RESOURCE;
    }
    
    public HelpCtx getHelpCtx() {
        //return HelpCtx.DEFAULT_HELP;
        return new HelpCtx( "about_nbgzip" );
    }
    
    public boolean asynchronous() {
        return true;
    }    
    
    /**
     * Compress, or uncompress the selected nodes.
     *
     * @param arr the selected nodes
     */
    protected void performAction(Node[] arr) {
        resetShallWeQuestion();
        
        // loop over all selected nodes
        for (int i = 0; i < arr.length; i++) {
            final DataObject dataObject = (DataObject) arr[i].getCookie(DataObject.class);
            final FileObject fo = dataObject.getPrimaryFile();
            
            // decide to compress, or uncompress
            if (fo.isData() && fo.canRead() && !fo.isVirtual()) {
                if (fo.hasExt( "gz" )) {
                    doUncompress( fo );
                    if (this.shallWeQuestionsUncompress.shallWeTerminateDoOperation()) {
                        break;
                    }
                } else {
                    doCompress( fo );
                    if (this.shallWeQuestionsCompress.shallWeTerminateDoOperation()) {
                        break;
                    }
                }
            }
            if (this.shallWeQuestionsDelete.shallWeTerminateDoOperation()) {
                break;
            }
        }
    }
    
    /**
     * Check if this action shall be enabled for the selected nodes
     * 
     * @param arr the selected nodes
     * @return boolean true if this action shall be enabled, else false
     */
    protected boolean enable(Node[] arr) {
        if ((arr == null) || (arr.length == 0)) {
            return false;
        }
        
        for (int i = 0; i < arr.length; i++) {
            DataObject dataObject = (DataObject) arr[i].getCookie(DataObject.class);
            //Action is disabled for root folder eg:"/" on Linux or "C:" on Win
            if (dataObject == null) {
                return false;
            }
            final FileObject fo = dataObject.getPrimaryFile();
            if (fo != null) {
                //Check if it is root.
                File file = FileUtil.toFile(fo);
                if (file != null) {
                    if (file.getParent() == null || file.isDirectory()) {
                        //It is root or a directory.
                        return false;
                    }
                }
            }
            
            // Fix #14740 disable action on SystemFileSystem.
            try {
                if(dataObject.getPrimaryFile().getFileSystem().isDefault()) {
                    return false;
                }
            } catch (FileStateInvalidException fsie) {
                return false;
            }
        }
        return true;
    }
    
    private void resetShallWeQuestion() {
        this.shallWeQuestionsCompress = new ShallWeQuestions();
        this.shallWeQuestionsUncompress = new ShallWeQuestions();
        this.shallWeQuestionsDelete = new ShallWeQuestions();
    }
    
    /**
     * Uncompress the file object
     *
     * @param srcFo the file object which will be uncompressed
     */
    private void doUncompress( FileObject srcFo ) {
        final AskOperation askOperation = new AskOperation();

        if (this.shallWeQuestionsUncompress.shallWeAskQuestion()) {
            this.shallWeQuestionsUncompress.setAskUserOption( askOperation.askUncompress( srcFo ) );
        }
        if (this.shallWeQuestionsUncompress.shallWeDoOperation()) {
            final GzipUncompressOperation gzipOperation = new GzipUncompressOperation();
            final boolean success = gzipOperation.uncompress(srcFo);
            if (success) {
                doDelete(srcFo);
            }
        }
    }
    
    /**
     * Compress the file object
     *
     * @param srcFo the file object which will be compressed
     */
    private void doCompress( FileObject srcFo ) {
        final AskOperation askOperation = new AskOperation();

        if (this.shallWeQuestionsCompress.shallWeAskQuestion()) {
            this.shallWeQuestionsCompress.setAskUserOption( askOperation.askCompress( srcFo ) );
        }
        
        if (this.shallWeQuestionsCompress.shallWeDoOperation()) {
            final GzipCompressOperation gzipOperation = new GzipCompressOperation();
            final boolean success = gzipOperation.compress(srcFo);
            if (success) {
                doDelete(srcFo);
            }
        }
    }
    
    /**
     * Delete the file object
     *
     * @param srcFo the file object which will be deleted
     */
    private void doDelete( FileObject srcFo ) {
        final AskOperation askOperation = new AskOperation();

        if (this.shallWeQuestionsDelete.shallWeAskQuestion()) {
            this.shallWeQuestionsDelete.setAskUserOption( askOperation.askDelete( srcFo ));
        }
        if (this.shallWeQuestionsDelete.shallWeDoOperation()) {
            final DeleteOperation deleteOperation = new DeleteOperation();
            deleteOperation.delete( srcFo );
        }
    }
    
}
