/*
 * ConstantsHelper.java
 *
 * Created on 13. September 2006, 23:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.cpd.misc;

import org.openide.util.HelpCtx;

/**
 * A simple helper class for encapsulating application wide constants.
 *
 * @author HuberB1
 */
public class ConstantsHelper {
    
    /**
     * Creates a new instance of ConstantsHelper
     */
    private ConstantsHelper() {
    }
    
    //---
    private static HelpCtx helpCtx = new HelpCtx("org.huberb.cpd.about");
    public static HelpCtx getHelpCtx() {
        return helpCtx;
    }

    //---
    private final static String XSLT_FOLDER_NAME = "cpd/xslt";
    private final static String STANDARD_XSLT_FILE_OBJECT_NAME = XSLT_FOLDER_NAME + "/standard.xsl";
    
    public static String getStandardXsltFileObjectName() {
        return STANDARD_XSLT_FILE_OBJECT_NAME;
    }
    public static String getXsltFolderName() {
        return XSLT_FOLDER_NAME ;
    }
}
