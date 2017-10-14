/*
 * ManifestEditorKit.java
 *
 * Created on October 20, 2005, 5:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.manifestsupport;

import javax.swing.text.Document;

import org.netbeans.editor.Syntax;
import org.netbeans.modules.editor.NbEditorKit;
import org.openide.ErrorManager;

/**
 *
 * @author Administrator
 */
public class ManifestEditorKit extends NbEditorKit {
    
    private static final ErrorManager LOGGER = ErrorManager.getDefault().getInstance("org.netbeans.modules.manifestsupport.ManifestEditorKit");
    private static final boolean LOG = LOGGER.isLoggable(ErrorManager.INFORMATIONAL);
    
    public static final String MIME_TYPE = "text/x-java-jar-manifest"; // NOI18N
    
    /** 
     * Creates a new instance of ManifestEditorKit 
     */
    public ManifestEditorKit() { 
    }
    
    /**
     * Create a syntax object suitable for highlighting Manifest file syntax
     */
    public Syntax createSyntax(Document doc) {
        if (LOG) {
            LOGGER.log(ErrorManager.INFORMATIONAL, "createSyntax"); // NOI18N
        }
        return new ManifestSyntax();
    }
    
    
    /**
     * Retrieves the content type for this editor kit
     */
    public String getContentType() {
        return MIME_TYPE;
    }
}