/*
 * PolicyTokenContext.java
 *
 * Created on July 20, 2005, 11:41 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.netbeans.modules.policysupport;

import org.netbeans.editor.BaseTokenID;
import org.netbeans.editor.TokenContext;
import org.netbeans.editor.TokenContextPath;
import org.netbeans.editor.Utilities;

/**
 *
 * @author HuberB1
 */
public class PolicyTokenContext extends TokenContext {
    
    // Numeric-ids for token categories
//    public static final int NAME_ID = 1;
//    public static final int COLON_ID = 2;
//    public static final int VALUE_ID = 3;
//    public static final int END_OF_LINE_ID = 4;
    //--
    public static final int COMMENT_ID = 10;
    public static final int KEYWORD_ID = COMMENT_ID +1;
    public static final int STRING_ID = KEYWORD_ID +1;
    public static final int PERMISSION_ID = STRING_ID +1;
    public static final int SPECIALCHAR_ID = PERMISSION_ID +1;
    public static final int WHITESPACE_ID = SPECIALCHAR_ID +1;
    
    // Token-ids
//    public static final BaseTokenID NAME = new BaseTokenID("name", NAME_ID);
//    public static final BaseTokenID COLON = new BaseTokenID("colon", COLON_ID);
//    public static final BaseTokenID VALUE = new BaseTokenID("value", VALUE_ID);
//    public static final BaseTokenID END_OF_LINE = new BaseTokenID("end-of-line", END_OF_LINE_ID);
    //--
    public static final BaseTokenID COMMENT = new BaseTokenID( "comment", COMMENT_ID );
    public static final BaseTokenID KEYWORD = new BaseTokenID( "keyword", KEYWORD_ID );
    public static final BaseTokenID STRING = new BaseTokenID( "string", STRING_ID );
    public static final BaseTokenID PERMISSION = new BaseTokenID( "permission", PERMISSION_ID );
    public static final BaseTokenID SPECIALCHAR = new BaseTokenID( "specialchar", SPECIALCHAR_ID );
    public static final BaseTokenID WHITESPACE = new BaseTokenID( "whitespace", WHITESPACE_ID );
    
    // Context instance declaration
    public static final PolicyTokenContext context = new PolicyTokenContext();
    public static final TokenContextPath contextPath = context.getContextPath();
    
    /**
     * Construct a new PolicyTokenContext
     */
    public PolicyTokenContext() {
        super("policy-");
        
        try {
            addDeclaredTokenIDs();
        } catch (Exception e) {
            Utilities.annotateLoggable(e);
        }
    }
}
