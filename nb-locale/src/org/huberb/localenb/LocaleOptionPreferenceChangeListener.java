/*
 * DataPatternComboboxHelper.java
 *
 * Created on 09. Oktober 2005, 22:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.localenb;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import org.huberb.localenb.options.LocaleOption;
import org.huberb.localenb.options.StringsStringEncoderDecoder;
import org.huberb.localenb.ui.FormatCommandInterface;

/**
 * Encapsulate propagating option property changes.
 *
 * @author HuberB1
 */
public class LocaleOptionPreferenceChangeListener implements PreferenceChangeListener {

    private FormatCommandInterface[] fci;

    /**
     * Creates a new instance of DataPatternComboboxHelper
     */
    public LocaleOptionPreferenceChangeListener(FormatCommandInterface[] fci) {
        this.fci = fci;
    }

    /**
     * Handle a property change of type date pattern list, number pattern list,
     * and message pattern list, depending on the property name provided in the
     * constructor.
     *
     * @evt the property change event sent if the user changes a pattern list
     */
    @Override
    public void preferenceChange(PreferenceChangeEvent evt) {
        if (evt != null && evt.getKey() != null && evt.getNewValue() != null) {
            final String evtPropertyName = evt.getKey();
            final String newValue = evt.getNewValue();
            final String[] newValueDecoded = new StringsStringEncoderDecoder().decodeToArrayString(newValue);
            
            if (LocaleOption.PROPERTY_DATE_PATTERN_LIST.equals(evtPropertyName)) {
                this.fci[0].setPatterns(newValueDecoded);
            } else if (LocaleOption.PROPERTY_NUMBER_PATTERN_LIST.equals(evtPropertyName)) {
                this.fci[1].setPatterns(newValueDecoded);
            } else if (LocaleOption.PROPERTY_MESSAGE_PATTERN_LIST.equals(evtPropertyName)) {
                this.fci[2].setPatterns(newValueDecoded);
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
