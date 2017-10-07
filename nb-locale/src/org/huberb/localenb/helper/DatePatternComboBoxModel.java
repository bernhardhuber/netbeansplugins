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
 * Encapsulate the default selectable date patterns here.
 *
 * @author HuberB1
 * @deprecated
 */
public class DatePatternComboBoxModel extends DefaultComboBoxModel {
    protected final static long serialVersionUID = 20070518L;
    
    private final static String[] DEFAULT_DATE_PATTERNS = {
        "yyyy-MM-dd HH:mm:ss", // 2005.07.30 23:50:20
        "yyyy.MM.dd G 'at' HH:mm:ss z", //2001.07.04 AD at 12:08:56 PDT
        "EEE, MMM d, ''yy", //Wed, Jul 4, '01
        "h:mm a", //12:08 PM
        "hh 'o''clock' a, zzzz", //12 o'clock PM, Pacific Daylight Time
        "K:mm a, z", //0:08 PM, PDT
        "yyyyy.MMMMM.dd GGG hh:mm aaa", //02001.July.04 AD 12:08 PM
        "EEE, d MMM yyyy HH:mm:ss Z", //Wed, 4 Jul 2001 12:08:56 -0700
        "yyMMddHHmmssZ", //010704120856-0700
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ", //2001-07-04T12:08:56.235-0700
    };
    /** Creates a new instance of DatePatternComboBoxModel */
    public DatePatternComboBoxModel() {
        super(DEFAULT_DATE_PATTERNS);
    }
    
}
