/*
 * FormatCommandInterface.java
 *
 * Created on 20. Mai 2005, 21:05
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.huberb.localenb.ui;

import java.util.Locale;

/**
 * This interface defines the contract for handling the execution of formatting.
 * <p>
 *  Implement this interface if a class shall format some user input.
 * </p>
 *
 * @author HuberB1
 */
public interface FormatCommandInterface {
    /**
     * Format some data for the specified locale
     *
     * @param selectedLocale the selected locale the
     * formatting implementation <strong>shall</strong> use.
     */
    void format(Locale selectedLocale);
    void setPatterns(String []patterns);

    /**
     * Get the internal state which shall get persisted
     */
    Object getState();
    /**
     * Update the internal state
     */
    void setState(Object newState);
 }
