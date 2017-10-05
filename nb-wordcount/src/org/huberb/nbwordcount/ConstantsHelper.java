/*
 * ConstantsHelper.java
 *
 * Created on 18. September 2006, 19:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbwordcount;

import org.openide.util.HelpCtx;

/**
 *
 * @author HuberB1
 */
public class ConstantsHelper {
    
    /** Creates a new instance of ConstantsHelper */
    private ConstantsHelper() {
    }
    
    public static HelpCtx getHelpCtx() {
        final HelpCtx retValue = new HelpCtx("about_nbwordcount");
        return retValue;
    }
}
