/*
 * ConstantsHelper.java
 *
 * Created on 08. Oktober 2006, 09:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.httppost.util;

import org.openide.util.HelpCtx;

/**
 *
 * @author HuberB1
 */
public class ConstantsHelper {
    
    /** Creates a new instance of ConstantsHelper */
    private ConstantsHelper() {
    }
    //
    public static HelpCtx getHelpCtx() {
        HelpCtx retValue = new HelpCtx("org.huberb.httppost.about");
        return retValue;
    }
}
