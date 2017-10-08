/*
 * PolicyEditorKit.java
 *
 * Created on October 20, 2005, 5:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.policysupport;

import javax.swing.text.Document;
import org.netbeans.editor.Syntax;
import org.netbeans.modules.editor.NbEditorKit;
import org.openide.ErrorManager;

/**
 *
 * @author HuberB1
 */
public class PolicyEditorKit extends NbEditorKit {
    protected final static long serialVersionUID = 20060730114300L;
    
    private static final ErrorManager LOGGER = ErrorManager.getDefault().getInstance("org.netbeans.modules.policysupport.PolicyEditorKit");
    private static final boolean LOG = LOGGER.isLoggable(ErrorManager.INFORMATIONAL);
    
    public static final String MIME_TYPE = "text/x-java-policy"; // NOI18N
    
    /**
     * 
     * Creates a new instance of PolicyEditorKit
     */
    public PolicyEditorKit() { 
    }
    
    /**
     * Create a syntax object suitable for highlighting Policy file syntax
     */
    public Syntax createSyntax(Document doc) {
        if (LOG) {
            LOGGER.log(ErrorManager.INFORMATIONAL, "createSyntax"); // NOI18N
        }
        return new PolicySyntax();
    }
    
    
    /**
     * Retrieves the content type for this editor kit
     */
    public String getContentType() {
        return MIME_TYPE;
    }
}