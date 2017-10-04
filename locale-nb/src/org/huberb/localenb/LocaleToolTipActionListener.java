/*
 * LocaleToolTipActionListener.java
 *
 * Created on 21. Mai 2005, 10:29
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.huberb.localenb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Locale;
import javax.swing.JComboBox;
import org.openide.util.NbBundle;


/**
 * An ActionListener for updating the ToolTip of the locale's combobox.
 *
 * @author HuberB1
 */
public class LocaleToolTipActionListener implements ActionListener {
    
    /**
     * Creates a new instance of LocaleToolTipActionListener
     */
    public LocaleToolTipActionListener() {
    }
    
    /**
     * update the tool tip text, as the selection has changed
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        Locale selectedLocale = (Locale)cb.getSelectedItem();
        
        // update the tool tip
        final String newToolTipMessage = formatLocale( selectedLocale );
        cb.setToolTipText( newToolTipMessage );
    }
    
    /**
     * Format a tool tip text for the specified locale
     *
     * @param selectedLocale the locale for which a tool tip message is formatted
     * @return String the formatted locale's tool tip message
     */
    public String formatLocale( Locale selectedLocale ) {
        final Object[] args = new Object[] {
            selectedLocale.getDisplayName(),
            
            selectedLocale.getCountry(),
            selectedLocale.getDisplayCountry(),
            selectedLocale.getLanguage(),
            selectedLocale.getDisplayLanguage(),
            selectedLocale.getVariant(),
            selectedLocale.getDisplayVariant(),
            selectedLocale.getISO3Country(),
            selectedLocale.getISO3Language(),
        };
        
        final String theToolTipMessagePattern = this.getToolTipMessagePattern();
        final String toolTipMessage = MessageFormat.format( theToolTipMessagePattern, args );
        return toolTipMessage;
    }
    
    /**
     * Getter for property toolTipMessagePattern.
     * @return Value of property toolTipMessagePattern.
     */
    protected String getToolTipMessagePattern() {
        String messagePattern = NbBundle.getMessage(LocaleToolTipActionListener.class, "CTL_LOCALE_TOOL_TIP");
        if (messagePattern == null) {
            messagePattern = "{0}";
        }
        return messagePattern;
    }
    
}
