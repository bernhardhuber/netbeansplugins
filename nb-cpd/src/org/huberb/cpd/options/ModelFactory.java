/*
 * ModelFactory.java
 *
 * Created on 21. April 2007, 20:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.cpd.options;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import net.sourceforge.pmd.cpd.LanguageFactory;

/**
 * A factory for creating ComboBoxModels.
 *
 * @author HuberB1
 */
public class ModelFactory {

    /**
     * Creates a new instance of ModelFactory
     */
    private ModelFactory() {
    }

    /**
     * Create a combobox model for a language accepted by CPD
     */
    public static ComboBoxModel<String> createLanguageComboBoxModel() {
        final String[] supportedLanguages = {
            LanguageFactory.JAVA_KEY,
            LanguageFactory.JSP_KEY,
            LanguageFactory.CPP_KEY,
            LanguageFactory.C_KEY,
            LanguageFactory.PHP_KEY,
            LanguageFactory.RUBY_KEY,
            LanguageFactory.BY_EXTENSION,};
        final DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<>(supportedLanguages);
        return dcbm;
    }

    /**
     * Crete a combobox model for a renderer
     */
    public static ComboBoxModel<CpdSettings.RendererEnum> createRendererComboBoxModel() {
        final CpdSettings.RendererEnum[] items = CpdSettings.RendererEnum.values();
        final DefaultComboBoxModel<CpdSettings.RendererEnum> dcbm = new DefaultComboBoxModel<>(items);
        return dcbm;
    }
}
