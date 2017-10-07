/*
 * ManifestOptionsBeanInfo.java
 *
 * Created on October 21, 2005, 12:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.manifestsupport.options;

import java.util.MissingResourceException;
import org.netbeans.modules.editor.options.BaseOptionsBeanInfo;
import org.netbeans.modules.editor.options.OptionSupport;
import org.openide.util.NbBundle;

/**
 *
 * @author Administrator
 */
public class ManifestOptionsBeanInfo extends BaseOptionsBeanInfo {
    
    /**
     * Constructor. The parameter in the superclass constructor is the
     * icon prefix.
     */
    public ManifestOptionsBeanInfo() {
        super("/org/netbeans/modules/manifestsupport/options/mfOptions"); // NOI18N
    }
    
    /*
     * Gets the property names after merging it with the set of properties
     * available from the BaseOptions from the editor module
     */
    protected String[] getPropNames() {
        return OptionSupport.mergeStringArrays(
                super.getPropNames(),
                ManifestOptions.MF_PROP_NAMES);
    }
    
    /**
     * Get the class described by this bean info
     */
    protected Class getBeanClass() {
        return ManifestOptions.class;
    }
    
    /**
     * Look up a resource bundle message, if it is not found locally defer to
     * the super implementation
     */
    protected String getString(String key) {
        try {
            return NbBundle.getMessage(ManifestOptionsBeanInfo.class, key);
        } catch (MissingResourceException e) {
            return super.getString(key);
        }
    }
}