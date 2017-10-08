/*
 * PolicyOptionsBeanInfo.java
 *
 * Created on October 21, 2005, 12:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.policysupport.options;

import java.util.MissingResourceException;
import org.netbeans.modules.editor.options.BaseOptionsBeanInfo;
import org.netbeans.modules.editor.options.OptionSupport;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class PolicyOptionsBeanInfo extends BaseOptionsBeanInfo {
    
    /**
     * Constructor. The parameter in the superclass constructor is the
     * icon prefix.
     */
    public PolicyOptionsBeanInfo() {
        super("/org/netbeans/modules/policysupport/options/policyOptions"); // NOI18N
    }
    
    /*
     * Gets the property names after merging it with the set of properties
     * available from the BaseOptions from the editor module
     */
    protected String[] getPropNames() {
        return OptionSupport.mergeStringArrays(
                super.getPropNames(),
                PolicyOptions.POLICY_PROP_NAMES);
    }
    
    /**
     * Get the class described by this bean info
     */
    protected Class getBeanClass() {
        return PolicyOptions.class;
    }
    
    /**
     * Look up a resource bundle message, if it is not found locally defer to
     * the super implementation
     */
    protected String getString(String key) {
        String message = null;
        try {
            message = NbBundle.getMessage(PolicyOptionsBeanInfo.class, key);
        } catch (MissingResourceException e) {
            message = super.getString(key);
        }
        return message;
    }
}