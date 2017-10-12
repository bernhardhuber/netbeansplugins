/*
 * LocaleOption.java
 *
 * Created on 09. Oktober 2005, 20:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.localenb.options;

import java.util.prefs.Preferences;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 * The locale options.
 * <p>
 * Configurable locale options.
 *
 * @author HuberB1
 */
public class LocaleOption {

    protected final static long serialVersionUID = 20051009215500L;

    enum OptionEnum {
        datePatternList(PROPERTY_DATE_PATTERN_LIST, DEFAULT_DATE_PATTERNS),
        numberPatternList(PROPERTY_NUMBER_PATTERN_LIST, DEFAULT_NUMBER_PATTERNS),
        messagePatternList(PROPERTY_MESSAGE_PATTERN_LIST, DEFAULT_MESSAGE_PATTERNS),
        messageArgPattern(PROPERTY_MESSAGE_ARG_DATE_PATTERN, DEFAULT_MESSAGE_ARG_DATE_PATTERN),
        messageArgNumberPattern(PROPERTY_MESSAGE_ARG_NUMBER_PATTERN, DEFAULT_MESSAGE_ARG_NUMBER_PATTERN);

        private final String propertyName;
        private final String defaultValueAsString;
        private final String[] defaultValueAsArray;

        OptionEnum(String propertyName, String defaultV) {
            this.propertyName = propertyName;
            this.defaultValueAsString = defaultV;
            this.defaultValueAsArray = null;
        }

        OptionEnum(String propertyName, String[] defaultV) {
            this.propertyName = propertyName;
            this.defaultValueAsString = null;
            this.defaultValueAsArray = defaultV;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public String getDefaultValueAsString() {
            return defaultValueAsString;
        }

        public String[] getDefaultValueAsArray() {
            return defaultValueAsArray;
        }

    }

    public static final String PROPERTY_DATE_PATTERN_LIST = "datePatternList";
    public static final String PROPERTY_NUMBER_PATTERN_LIST = "numberPatternList";
    public static final String PROPERTY_MESSAGE_PATTERN_LIST = "messagePatternList";

    public static final String PROPERTY_MESSAGE_ARG_DATE_PATTERN = "messageArgDatePattern";
    public static final String PROPERTY_MESSAGE_ARG_NUMBER_PATTERN = "messageArgNumberPattern";
    /**
     * Get a default instance <code>LocaleOption</code>.
     *
     * @return LocaleOption the default locale's option
     */
    private static final LocaleOption INSTANCE = new LocaleOption();

    private final PreferencesAccess preferencesAccess;

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
        "The disk \"{1}\" contains {0} file(s).",};
    private final static String[] DEFAULT_NUMBER_PATTERNS = {
        "0.00",
        "#,##0.00",
        "#,##0.0#",
        "#,##0.00;(#,##0.00)",
        "0.###E0 m/s",};
    private final static String DEFAULT_MESSAGE_ARG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static String DEFAULT_MESSAGE_ARG_NUMBER_PATTERN = "#";

    public static final LocaleOption getDefault() {
        return INSTANCE;
    }

    /**
     * Creates a new instance of LocaleOption
     */
    private LocaleOption() {
        this.preferencesAccess = new PreferencesAccess();
    }

    /**
     * Get the option's display name
     *
     * @return String the display name
     */
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
        return preferencesAccess.getPropertyAsStrings(PROPERTY_DATE_PATTERN_LIST, DEFAULT_DATE_PATTERNS);
    }

    public void setDatePatternList(String[] newDatePatternList) {
        preferencesAccess.putPropertyAsStrings(PROPERTY_DATE_PATTERN_LIST, newDatePatternList, true);
    }

    //-------------------------------------------------------------------------
    /**
     * Get the message pattern list
     *
     * @return String[] the message patterns
     */
    public String[] getMessagePatternList() {
        return preferencesAccess.getPropertyAsStrings(PROPERTY_MESSAGE_PATTERN_LIST, DEFAULT_MESSAGE_PATTERNS);
    }

    public void setMessagePatternList(String[] newMessagePatternList) {
        preferencesAccess.putPropertyAsStrings(PROPERTY_MESSAGE_PATTERN_LIST, newMessagePatternList, true);
    }

    //-------------------------------------------------------------------------
    /**
     * Get the number pattern list
     *
     * @return String[] the number patterns
     */
    public String[] getNumberPatternList() {
        return preferencesAccess.getPropertyAsStrings(PROPERTY_NUMBER_PATTERN_LIST, DEFAULT_NUMBER_PATTERNS);
    }

    public void setNumberPatternList(String[] newNumberPatternList) {
        preferencesAccess.putPropertyAsStrings(PROPERTY_NUMBER_PATTERN_LIST, newNumberPatternList, true);
    }

    //-------------------------------------------------------------------------
    public String getMessageArgDatePattern() {
        return preferencesAccess.getPropertyAsString(PROPERTY_MESSAGE_ARG_DATE_PATTERN, DEFAULT_MESSAGE_ARG_DATE_PATTERN);
    }

    public void setMessageArgDatePattern(String newValue) {
        preferencesAccess.putPropertyAsString(PROPERTY_MESSAGE_ARG_DATE_PATTERN, newValue, true);
    }

    //-------------------------------------------------------------------------
    public String getMessageArgNumberPattern() {
        return preferencesAccess.getPropertyAsString(PROPERTY_MESSAGE_ARG_NUMBER_PATTERN, DEFAULT_MESSAGE_ARG_NUMBER_PATTERN);
    }

    public void setMessageArgNumberPattern(String newValue) {
        preferencesAccess.putPropertyAsString(PROPERTY_MESSAGE_ARG_NUMBER_PATTERN, newValue, true);
    }

    //-------------------------------------------------------------------------
    static class PreferencesAccess {

        private final Preferences preferences;

        public PreferencesAccess() {
            this.preferences = NbPreferences.forModule(LocaleOption.class);
        }

        protected String[] getPropertyAsStrings(String key, String[] def) {
            String encoded = preferences.get(key, null);
            final String[] result;
            if (encoded != null) {
                result = new StringsStringEncoderDecoder().decodeToArrayString(encoded);
            } else {
                result = def;
            }
            return result;
        }

        protected void putPropertyAsStrings(String key, String[] value, boolean ignore) {
            String encoded = new StringsStringEncoderDecoder().encodeArrayString(value);
            preferences.put(key, encoded);
        }

        protected String getPropertyAsString(String key, String def) {
            String result = preferences.get(key, def);
            return result;
        }

        protected void putPropertyAsString(String key, String value, boolean ignore) {
            preferences.put(key, value);
        }
    }

    public HelpCtx getHelpCtx() {
        HelpCtx retValue = new HelpCtx("org.huberb.localenb.about");
        return retValue;
    }

}
