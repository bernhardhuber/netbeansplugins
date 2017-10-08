/*
 * PolicyOptions.java
 *
 * Created on October 20, 2005, 5:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.policysupport.options;

import java.util.MissingResourceException;

import org.netbeans.modules.editor.options.BaseOptions;
import org.netbeans.modules.policysupport.PolicyEditorKit;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class PolicyOptions extends BaseOptions {
    
    public static final String POLICY = "Policy"; // NOI18N
    
    /** Name of property. */
    private static final String HELP_ID = "org.netbeans.modules.policysupport.about"; // NOI18N
    
    //no policy specific options at this time
    static final String[] POLICY_PROP_NAMES = new String[] {};
    
    
    public PolicyOptions() {
        super(PolicyEditorKit.class, POLICY);
    }
    
    /**
     * Determines the class of the default indentation engine, in this case
     * PolicyIndentEngine.class
     */
//    protected Class getDefaultIndentEngineClass() {
//        return PolicyIndentEngine.class;
//    }
    
    /**
     * Gets the help ID
     */
    public HelpCtx getHelpCtx() {
        return new HelpCtx(HELP_ID);
    }
    
    /**
     * Look up a resource bundle message, if it is not found locally defer to
     * the super implementation
     */
    protected String getString(String key) {
        String message = null;
        try {
            message = NbBundle.getMessage(PolicyOptions.class, key);
        } catch (MissingResourceException e) {
            message = super.getString(key);
        }
        return message;
    }
    
}