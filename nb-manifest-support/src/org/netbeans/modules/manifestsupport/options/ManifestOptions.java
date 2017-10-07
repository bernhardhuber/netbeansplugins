/*
 * ManifestOptions.java
 *
 * Created on October 20, 2005, 5:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.manifestsupport.options;

import java.util.MissingResourceException;
import org.netbeans.modules.editor.options.BaseOptions;
import org.netbeans.modules.manifestsupport.ManifestEditorKit;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 *
 * @author Administrator
 */
public class ManifestOptions extends BaseOptions {
    
    public static String MANIFEST = "Manifest"; // NOI18N
    
    /** Name of property. */
    private static final String HELP_ID = "editing.editor.mf"; // NOI18N
    
    //no manifest specific options at this time
    static final String[] MF_PROP_NAMES = new String[] {};
    
    
    public ManifestOptions() {
        super(ManifestEditorKit.class, MANIFEST);
    }
    
    /**
     * Determines the class of the default indentation engine, in this case
     * ManifestIndentEngine.class
     */
//    protected Class getDefaultIndentEngineClass() {
//        return ManifestIndentEngine.class;
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
        try {
            return NbBundle.getMessage(ManifestOptions.class, key);
        } catch (MissingResourceException e) {
            return super.getString(key);
        }
    }
    
}