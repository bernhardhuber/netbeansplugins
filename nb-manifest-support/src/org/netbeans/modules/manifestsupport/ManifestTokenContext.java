/*
 * ManifestTokenContext.java
 *
 * Created on July 20, 2005, 11:41 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.netbeans.modules.manifestsupport;

import org.netbeans.editor.BaseTokenID;
import org.netbeans.editor.TokenContext;
import org.netbeans.editor.TokenContextPath;
import org.netbeans.editor.Utilities;

/**
 *
 * @author Administrator
 */
public class ManifestTokenContext extends TokenContext {
       
      // Numeric-ids for token categories
    public static final int NAME_ID = 1;
    public static final int COLON_ID = 2;
    public static final int VALUE_ID = 3;
    public static final int END_OF_LINE_ID = 4;
    
    // Token-ids
    public static final BaseTokenID NAME = new BaseTokenID("name", NAME_ID);
    public static final BaseTokenID COLON = new BaseTokenID("colon", COLON_ID);
    public static final BaseTokenID VALUE = new BaseTokenID("value", VALUE_ID);
    public static final BaseTokenID END_OF_LINE = new BaseTokenID("end-of-line", END_OF_LINE_ID);
   
    // Context instance declaration
    public static final ManifestTokenContext context = new ManifestTokenContext();
    public static final TokenContextPath contextPath = context.getContextPath();
    
    /**
     * Construct a new ManifestTokenContext
     */
    public ManifestTokenContext() {
        super("mf-");
        
        try {
            addDeclaredTokenIDs();
        } catch (Exception e) {
            Utilities.annotateLoggable(e);
        }
    }
}
