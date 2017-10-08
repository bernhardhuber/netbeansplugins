/*
 * PolicyIndentEngine.java
 *
 * Created on 03. September 2006, 18:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.policysupport;

import org.netbeans.editor.ext.ExtFormatter;
import org.netbeans.modules.editor.FormatterIndentEngine;

/**
 * The policy indent engine.
 *
 * @author HuberB1
 */
public class PolicyIndentEngine extends FormatterIndentEngine {

    protected final static long serialVersionUID = 20060903221900L;
    
    /** Creates a new instance of PolicyIndentEngine */
    public PolicyIndentEngine() {
        setAcceptedMimeTypes( new String[] {PolicyEditorKit.MIME_TYPE} );
    }
    
    /**
     * Creates a suitable formatter for handling SQL
     */
    protected ExtFormatter createFormatter() {
        return new PolicyFormatter(PolicyEditorKit.class);
    }
}
