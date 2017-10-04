/*
 * DataPatternComboboxHelper.java
 *
 * Created on 09. Oktober 2005, 22:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.localenb;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.huberb.localenb.options.LocaleOption;
import org.huberb.localenb.ui.FormatCommandInterface;

/**
 * Encapsulate propagating option property changes.
 *
 * @author HuberB1
 */
public class LocaleOptionPropertyChangeListener implements PropertyChangeListener {
    private FormatCommandInterface[] fci;
    
    /** Creates a new instance of DataPatternComboboxHelper */
    public LocaleOptionPropertyChangeListener(FormatCommandInterface[] fci) {
        this.fci = fci;
    }
    
    
    /**
     * Handle a property change of type date pattern list, number pattern list,
     * and message pattern list, depending on the property name provided in the
     * constructor.
     *
     * @evt the property change event sent if the user changes a pattern list
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt != null && evt.getPropertyName() != null && evt.getNewValue() != null) {
            // the property name
            final String evtPropertyName = evt.getPropertyName();
            // the new property value
            
            if (LocaleOption.PROPERTY_DATE_PATTERN_LIST.equals( evtPropertyName )) {
                final String[] evtNewPatterns = (String[])evt.getNewValue();
                this.fci[0].setPatterns( evtNewPatterns );
            } else if (LocaleOption.PROPERTY_NUMBER_PATTERN_LIST.equals( evtPropertyName )) {
                final String[] evtNewPatterns = (String[])evt.getNewValue();
                this.fci[1].setPatterns( evtNewPatterns );
            } else if (LocaleOption.PROPERTY_MESSAGE_PATTERN_LIST.equals( evtPropertyName )) {
                final String[] evtNewPatterns = (String[])evt.getNewValue();
                this.fci[2].setPatterns( evtNewPatterns );
            }
        }
    }
    
}
