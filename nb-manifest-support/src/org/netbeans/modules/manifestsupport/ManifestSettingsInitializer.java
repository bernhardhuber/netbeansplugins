/*
 * ManifestSettingsInitializer.java
 *
 * Created on October 20, 2005, 5:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.manifestsupport;

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
 * @author Administrator
 */
public class ManifestSettingsInitializer extends Settings.AbstractInitializer {
    
    public static final String NAME = "manifest-settings-initializer"; // NOI18N
    
    /**
     * Constructor
     */
    public ManifestSettingsInitializer() {
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
            new ManifestTokenColoringInitializer().updateSettingsMap(kitClass, settingsMap);
        }
        
        if (kitClass == ManifestEditorKit.class) {
            SettingsUtil.updateListSetting(
                    settingsMap,
                    SettingsNames.TOKEN_CONTEXT_LIST,
                    new TokenContext[] { ManifestTokenContext.context }
            );
        }
    }
    
    /**
     * Class for adding syntax coloring to the editor
     * <p>
     * Properties token coloring initializer. 
     */
    private static class ManifestTokenColoringInitializer extends SettingsUtil.TokenColoringInitializer {
        /** Bold font. */
        private static final Font boldFont = SettingsDefaults.defaultFont.deriveFont(Font.BOLD);
        /** Italic font. */
        private static final Font italicFont = SettingsDefaults.defaultFont.deriveFont(Font.ITALIC);
        
        /** Key coloring. */
        private static final Coloring keyColoring = new Coloring(boldFont, Coloring.FONT_MODE_APPLY_STYLE, Color.blue, null);
        /** Value coloring. */
        private static final Coloring valueColoring = new Coloring(null, Color.magenta, null);
        /** Colon coloring. */
        private static final Coloring colonColoring = new Coloring(null, Color.DARK_GRAY, null);
        /** Empty coloring. */
        private static final Coloring emptyColoring = new Coloring(null, null, null);
        
        
        /** Constructs <code>PropertiesTokenColoringInitializer</code>. */
        public ManifestTokenColoringInitializer() {
            super(ManifestTokenContext.context);
        }
        
        
        /** Gets token coloring. */
        public Object getTokenColoring(TokenContextPath tokenContextPath,
                TokenCategory tokenIDOrCategory, boolean printingSet) {
            
            if(!printingSet) {
                int tokenID = tokenIDOrCategory.getNumericID();
                
                if(tokenID == ManifestTokenContext.NAME_ID) {
                    return keyColoring;
                } else if(tokenID == ManifestTokenContext.VALUE_ID) {
                    return valueColoring;
                } else if(tokenID == ManifestTokenContext.COLON_ID) {
                    return colonColoring;                    
                }
            } else { // printing set
                return SettingsUtil.defaultPrintColoringEvaluator;
            }
            return null;
        }
        
    } // End of class ManifestTokenColoringInitializer.
    
}