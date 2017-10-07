/*
 * ConstantsHelper.java
 *
 * Created on 16. September 2006, 19:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.model;

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
        HelpCtx retValue = new HelpCtx("org.huber.keytool.about");
        return retValue;
    }
    public static HelpCtx getSettingsHelpCtx() {
        HelpCtx retValue = new HelpCtx("org.huber.keytool.settings");
        return retValue;
    }
    
}
