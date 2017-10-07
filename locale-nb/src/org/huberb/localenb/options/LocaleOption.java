/*
 * LocaleOption.java
 *
 * Created on 09. Oktober 2005, 20:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.localenb.options;

import org.openide.options.SystemOption;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 * The locale options.
 * <p>
 * Configurable locale options.
 *
 * @author HuberB1
 */
public class LocaleOption extends SystemOption {
    protected final static long serialVersionUID = 20051009215500L;
    
    public static final String PROPERTY_DATE_PATTERN_LIST = "datePatternList";
    public static final String PROPERTY_NUMBER_PATTERN_LIST = "numberPatternList";
    public static final String PROPERTY_MESSAGE_PATTERN_LIST = "messagePatternList";
    
    public static final String PROPERTY_MESSAGE_ARG_DATE_PATTERN = "messageArgDatePattern";
    public static final String PROPERTY_MESSAGE_ARG_NUMBER_PATTERN = "messageArgNumberPattern";
    
    /**
     * Creates a new instance of LocaleOption
     */
    public LocaleOption() {
    }
    
    /**
     * Get the option's display name
     *
     * @return String the display name
     */
    @Override
    public String displayName() {
        return NbBundle.getMessage(LocaleOption.class, "CTL_LocaleOption");
    }
    
    //-------------------------------------------------------------------------
    /**
     * Get the date pattern list
     *
     * @return String[] the date patterns
     */
    public String[] getDatePatternList() {
        return (String[])getProperty(PROPERTY_DATE_PATTERN_LIST);
    }
    public void setDatePatternList(String[] newDatePatternList) {
        putProperty(PROPERTY_DATE_PATTERN_LIST, newDatePatternList, true );
    }
    
    //-------------------------------------------------------------------------
    /**
     * Get the message pattern list
     *
     * @return String[] the message patterns
     */
    public String[] getMessagePatternList() {
        return (String[])getProperty(PROPERTY_MESSAGE_PATTERN_LIST);
    }
    public void setMessagePatternList(String[] newMessagePatternList) {
        putProperty(PROPERTY_MESSAGE_PATTERN_LIST, newMessagePatternList, true );
    }
    
    //-------------------------------------------------------------------------
    /**
     * Get the number pattern list
     *
     * @return String[] the number patterns
     */
    public String[] getNumberPatternList() {
        return (String[])getProperty(PROPERTY_NUMBER_PATTERN_LIST);
    }
    public void setNumberPatternList(String[] newNumberPatternList) {
        putProperty(PROPERTY_NUMBER_PATTERN_LIST, newNumberPatternList, true );
    }
    
    //-------------------------------------------------------------------------
    public String getMessageArgDatePattern() {
        return (String)getProperty(PROPERTY_MESSAGE_ARG_DATE_PATTERN);
    }
    public void setMessageArgDatePattern(String newValue) {
        putProperty(PROPERTY_MESSAGE_ARG_DATE_PATTERN, newValue, true );
    }
    
    //-------------------------------------------------------------------------
    public String getMessageArgNumberPattern() {
        return (String)getProperty(PROPERTY_MESSAGE_ARG_NUMBER_PATTERN);        
    }
    public void setMessageArgNumberPattern(String newValue) {
        putProperty(PROPERTY_MESSAGE_ARG_NUMBER_PATTERN, newValue, true);
    }
    
    /**
     * Get a default instance <code>LocaleOption</code>.
     *
     * @return LocaleOption the default locale's option
     */
    public static final LocaleOption getDefault() {
        return (LocaleOption)findObject(LocaleOption.class, true);
    }
    
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
    private final static String[] DEFAULT_MESSAGE_PATTERNS = {
        "Hello ''{0}''!", // Hello 'world'!
        "<html>Hello <b>{0}</b>!</html>", // Hello world!
        "There {0,choice,0#are no files|1#is one file|1<are {0,number,integer} files}.",
        "The disk \"{1}\" contains {0} file(s).",
    };
    private final static String[] DEFAULT_NUMBER_PATTERNS = {
        "0.00",
        "#,##0.00",
        "#,##0.0#",
        "#,##0.00;(#,##0.00)",
        "0.###E0 m/s",
    };
    private final static String DEFAULT_MESSAGE_ARG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static String DEFAULT_MESSAGE_ARG_NUMBER_PATTERN = "#";
    
    /**
     * initialize this option
     */
    @Override
    protected void initialize() {
        super.initialize();
        putProperty( PROPERTY_DATE_PATTERN_LIST, DEFAULT_DATE_PATTERNS, false );
        putProperty( PROPERTY_MESSAGE_PATTERN_LIST, DEFAULT_MESSAGE_PATTERNS, false );
        putProperty( PROPERTY_NUMBER_PATTERN_LIST, DEFAULT_NUMBER_PATTERNS, false );
        
        putProperty( PROPERTY_MESSAGE_ARG_DATE_PATTERN, DEFAULT_MESSAGE_ARG_DATE_PATTERN, false );
        putProperty( PROPERTY_MESSAGE_ARG_NUMBER_PATTERN, DEFAULT_MESSAGE_ARG_NUMBER_PATTERN, false );
    }
    
    @Override
    public HelpCtx getHelpCtx() {
        HelpCtx retValue = new HelpCtx("org.huberb.localenb.about");
        return retValue;
    }
}
