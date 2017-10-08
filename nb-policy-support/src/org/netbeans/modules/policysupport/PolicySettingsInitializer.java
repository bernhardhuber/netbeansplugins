/*
 * PolicySettingsInitializer.java
 *
 * Created on October 20, 2005, 5:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.policysupport;

import java.awt.Color;
import java.awt.Font;
import java.util.Map;

import org.netbeans.editor.BaseKit;
import org.netbeans.editor.Coloring;
import org.netbeans.editor.Settings;
import org.netbeans.editor.SettingsDefaults;
import org.netbeans.editor.SettingsNames;
import org.netbeans.editor.SettingsUtil;
import org.netbeans.editor.TokenCategory;
import org.netbeans.editor.TokenContext;
import org.netbeans.editor.TokenContextPath;

/**
 *
 * @author HuberB1
 */
public class PolicySettingsInitializer extends Settings.AbstractInitializer {
    
    public static final String NAME = "policy-settings-initializer"; // NOI18N
    
    /**
     * Constructor
     */
    public PolicySettingsInitializer() {
        super(NAME);
    }
    
    /**
     * Update map filled with the settings.
     *
     * @param kitClass kit class for which the settings are being updated.
     *   It is always non-null value.
     * @param settingsMap map holding [setting-name, setting-value] pairs.
     *   The map can be empty if this is the first initializer
     *   that updates it or if no previous initializers updated it.
     */
    public void updateSettingsMap(Class kitClass, Map settingsMap) {
        if (kitClass == BaseKit.class) {
            new PolicyTokenColoringInitializer().updateSettingsMap(kitClass, settingsMap);
        }
        
        if (kitClass == PolicyEditorKit.class) {
            SettingsUtil.updateListSetting(
                    settingsMap,
                    SettingsNames.TOKEN_CONTEXT_LIST,
                    new TokenContext[] { PolicyTokenContext.context }
            );
        }
    }
    
    /**
     * Class for adding syntax coloring to the editor
     * <p>
     * Properties token coloring initializer.
     */
    private static class PolicyTokenColoringInitializer extends SettingsUtil.TokenColoringInitializer {
        /** Bold font. */
        private static final Font boldFont = SettingsDefaults.defaultFont.deriveFont(Font.BOLD);
        /** Italic font. */
        private static final Font italicFont = SettingsDefaults.defaultFont.deriveFont(Font.ITALIC);
        
        /** Empty coloring. */
        private static final Coloring emptyColoring = new Coloring(null, null, null);
        
        private static final Coloring commentColoring = new Coloring(null, Color.LIGHT_GRAY, null );
        private static final Coloring keywordColoring = new Coloring( boldFont, Color.BLUE, null );
        private static final Coloring stringColoring = new Coloring( null, Color.MAGENTA, null );
        
        private static final Coloring specialCharColoring  = new Coloring( null, Color.orange, null );
        private static final Coloring permissionColoring = new Coloring( null, Color.red, null );
        
        /** Constructs <code>PropertiesTokenColoringInitializer</code>. */
        public PolicyTokenColoringInitializer() {
            super(PolicyTokenContext.context);
        }
        
        
        /** Gets token coloring. */
        public Object getTokenColoring(TokenContextPath tokenContextPath,
                TokenCategory tokenIDOrCategory, boolean printingSet) {
            
            if(!printingSet) {
                final int tokenID = tokenIDOrCategory.getNumericID();
                
                switch (tokenID) {
                    case PolicyTokenContext.COMMENT_ID:
                        return commentColoring;
                    case PolicyTokenContext.KEYWORD_ID:
                        return keywordColoring;
                    case PolicyTokenContext.STRING_ID:
                        return stringColoring;
                    case PolicyTokenContext.SPECIALCHAR_ID:
                        return specialCharColoring;
                    case PolicyTokenContext.PERMISSION_ID:
                        return permissionColoring;
                }
            } else { // printing set
                return SettingsUtil.defaultPrintColoringEvaluator;
            }
            return null;
        }
        
    } // End of class PolicyTokenColoringInitializer.
    
}