/*
 * CpdSettings.java
 *
 * Created on 13. September 2006, 21:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbpmdrulesets.options;

import java.io.File;
import org.openide.options.SystemOption;
import org.openide.util.NbBundle;

/**
 * Options of the PMD ruleset module.
 *
 * @author HuberB1
 */
public class PmdRulesetSettings extends SystemOption {
    private static final long serialVersionUID = 20070603L;
    
    private final static String PMD_HOME_PROP = "pmdHome";
    
    /**
     * Creates a new instance of CpdSettings
     */
    public PmdRulesetSettings() {
    }
    
    public String displayName() {
        return NbBundle.getMessage( PmdRulesetSettings.class, "AdvancedOption_DisplayName");
    }
    
    /** Initialize shared state.
     * Should use {@link #putProperty} to set up variables.
     * Subclasses should always call the super method.
     * <p>This method need <em>not</em> be called explicitly; it will be called once
     * the first time a given shared class is used (not for each instance!).
     */
    protected void initialize() {
        super.initialize();
        
        setPmdHome("");
    }
    
    
    public static PmdRulesetSettings getDefault() {
        return (PmdRulesetSettings)SystemOption.findObject( PmdRulesetSettings.class, true );
    }
    
    //----
    public String getPmdHome() {
        String value = (String)this.getProperty( PMD_HOME_PROP );
        return value;
    }
    public void setPmdHome( String newValue ) {
        this.putProperty( PMD_HOME_PROP, newValue, true );
    }
    public boolean validatePmdHome() {
        String pmdHome = getPmdHome();
        
        boolean isValid = true;
        isValid = isValid && pmdHome != null;
        isValid = isValid && pmdHome.length() > 0;
        if (isValid) {
            File pmdHomeFile = new File(pmdHome);
            isValid = isValid && pmdHomeFile.exists();
            isValid = isValid && pmdHomeFile.isDirectory();
        }
        
        return isValid;
    }
}

