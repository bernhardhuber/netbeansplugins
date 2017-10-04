/*
 * DatePatternComboBoxModel.java
 *
 * Created on 20. Mai 2005, 20:16
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package org.huberb.localenb.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;

/**
 * Encapsulate any combobox model creation here.
 *
 * @author HuberB1
 */
public class LocaleComboBoxModel extends DefaultComboBoxModel {

    protected final static long serialVersionUID = 20070518L;

    /**
     * Creates a new instance of ComboBoxModelFactory
     */
    public LocaleComboBoxModel() {
        super(sortedAvailableLocales());
    }

    static Locale[] sortedAvailableLocales() {
        final List<Locale> availableLocales = new ArrayList<>();
        availableLocales.addAll(Arrays.asList(Locale.getAvailableLocales()));
        availableLocales.sort((Locale locale1, Locale locale2) -> {
            String displayName1 = locale1.getDisplayName();
            String displayName2 = locale2.getDisplayName();
            return displayName1.compareTo(displayName2);
        });
        return availableLocales.toArray(new Locale[availableLocales.size()]);
    }
}
