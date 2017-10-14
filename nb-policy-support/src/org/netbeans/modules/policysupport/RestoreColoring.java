/*
 * RestoreColoring.java
 *
 * Created on October 20, 2005, 5:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.policysupport;

import org.netbeans.editor.LocaleSupport;
import org.netbeans.editor.Settings;
import org.openide.modules.ModuleInstall;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class RestoreColoring extends ModuleInstall {
    
    /**
     * <code>Localizer</code> passed to editor.
     */
    private static LocaleSupport.Localizer localizer;
    
    /**
     * Registers properties editor, installs options and copies settings.
     * Overrides superclass method.
     */
    public void restored() {
        addInitializer();
        installOptions();
    }
    
    /**
     * Uninstall properties options.
     * And cleans up editor settings copy.
     * Overrides superclass method.
     */
    public void uninstalled() {
        uninstallOptions();
    }
    
    /**
     * Adds initializer and registers editor kit.
     */
    public void addInitializer() {
        Settings.addInitializer(new PolicySettingsInitializer());
    }
    
    /**
     * Installs properties editor and print options.
     */
    public void installOptions() {
        // Adds localizer.
        localizer = new LocalSupportLocalizer();
        LocaleSupport.addLocalizer( localizer );
//        LocaleSupport.addLocalizer(localizer = new LocaleSupport.Localizer() {
//            public String getString(String key) {
//                return NbBundle.getMessage(RestoreColoring.class, key);
//            }
//        });
        
    }
    
    static class LocalSupportLocalizer implements LocaleSupport.Localizer {
        public String getString(String key) {
            return NbBundle.getMessage(RestoreColoring.class, key);
        }
    }
    
    /** Uninstalls properties editor and print options. */
    public void uninstallOptions() {
        // remove localizer
        LocaleSupport.removeLocalizer(localizer);
    }
    
}