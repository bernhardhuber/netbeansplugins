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

import javax.swing.DefaultComboBoxModel;

/**
 * Encapsulate the default selectable message patterns here.
 *
 * @author HuberB1
 * @deprecated
 */
public class MessagePatternComboBoxModel extends DefaultComboBoxModel{
    protected final static long serialVersionUID = 20070518L;
    
    private final static String[] DEFAULT_MESSAGE_PATTERNS = {
        "Hello ''{0}''!", // Hello 'world'!
        "<html>Hello <b>{0}</b>!</html>", // Hello world!
    };
    /** Creates a new instance of DatePatternComboBoxModel */
    public MessagePatternComboBoxModel() {
        super(DEFAULT_MESSAGE_PATTERNS);
    }
    
}
