/*
 * LocaleIsoCountry.java
 *
 * Created on 17. Februar 2006, 22:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.wizard.genkey;

import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import org.huber.keytool.model.LabelValueBean;

/**
 * A simple bean for storing the ISO 2 letter code, and an human readable
 * country name.
 *
 * This class is used as bean in a DefaultComboBoxModel.
 */
public class LocaleIsoCountry extends LabelValueBean<String> implements Comparable {
    
    public LocaleIsoCountry(Locale locale) {
        final String  newLabel = locale.getCountry() + " - " + locale.getDisplayCountry();
        this.setLabelAndValue( newLabel, locale.getCountry() );
    }
    
    public String getIsoCountry() {
        return this.getValue();
    }
    public int hashCode() {
        return this.getLabel().hashCode();
    }
    
    public boolean equals( Object obj ) {
        boolean equals = (this == obj);
        if (!equals && obj instanceof LocaleIsoCountry) {
            LocaleIsoCountry other = (LocaleIsoCountry)obj;
            equals = this.getIsoCountry().equals( other.getIsoCountry() );
        }
        return equals;
    }
    
    /**
     * Provide an order using the ISO 2 letter as ordering key
     *
     * @param o the object to compare to.
     * @return neg., 0, or positive number
     */
    public int compareTo(Object o) {
        final LocaleIsoCountry lic = (LocaleIsoCountry)o;
        final int compareTo = this.getIsoCountry().compareTo( lic.getIsoCountry() );
        return compareTo;
    }

    /**
     * Create an LocaleIsoCountry[] array for the specified
     * array of locales.
     * <p>
     * Ignore locales having no two letter iso country name.
     *
     * @param locales the locales, for each Locale a LocaleIsoCountry
     *   is created
     * @return LocaleIsoCountry[] created
     */
    public static LocaleIsoCountry[] createIsoCountries(Locale[] locales) {
        final Set<LocaleIsoCountry> list = new TreeSet<LocaleIsoCountry>();
        for (int i = 0; i < locales.length; i++ ) {
            final Locale locale = locales[i];
            final String localeCountry = locale.getCountry();
            if (localeCountry != null && localeCountry.length() == 2) {
                list.add( new LocaleIsoCountry(locales[i] ) );
            }
        }
        final LocaleIsoCountry[] localeIsoCountries = (LocaleIsoCountry[])list.toArray( new LocaleIsoCountry[list.size()]);
        return localeIsoCountries;
    }
    
}
