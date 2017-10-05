/*
 * XsltFileObjectComboBoxModel.java
 *
 * Created on 14. September 2006, 08:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbjaranalyzer.options;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.huberb.nbjaranalyzer.misc.ConstantsHelper;
import org.huberb.nbjaranalyzer.options.LabelFileObjectBean;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.Repository;

/**
 * A combo box model of the xslt file objects.
 *
 * @author HuberB1
 */
public class XsltFileObjectComboBoxModel extends DefaultComboBoxModel {
    
    /**
     * Creates a new instance of XsltFileObjectComboBoxModel
     */
    public XsltFileObjectComboBoxModel() {
        refresh();
    }
    
    public final void refresh() {
        
        this.removeAllElements();
        
        final LabelFileObjectBean[] foArray = populateModel();
        for ( LabelFileObjectBean lfob : foArray ) {
            this.addElement( lfob );
        }
    }
    
    protected LabelFileObjectBean[] populateModel() {
        final List<LabelFileObjectBean> foList = new ArrayList<LabelFileObjectBean>();
        final String FOLDER_NAME = ConstantsHelper.getXsltFolderName();
        
        final Repository repo = Repository.getDefault();
        final FileSystem fs = repo.getDefaultFileSystem();
        
        final FileObject xsltFolder = fs.findResource( FOLDER_NAME );
        for (Enumeration children = xsltFolder.getData(false); children.hasMoreElements() ; ) {
            final FileObject foChild = (FileObject)children.nextElement();
            final LabelFileObjectBean lfob = new LabelFileObjectBean(foChild);
            foList.add( lfob );
        }
        return foList.toArray(new LabelFileObjectBean[foList.size()]);
    }

}
