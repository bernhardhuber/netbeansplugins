/*
 * AskOption.java
 *
 * Created on 21. Dezember 2005, 09:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.nbgzip.option;

import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 * Encapsulate options in this class.
 *
 * @author HuberB1
 */
public class AskOption {

    protected final static long serialVersionUID = 20060116221600L;
    private static final AskOption INSTANCE = new AskOption();

    //----
    private final static String ASK_COMPRESS = "AskCompress";
    private final static String ASK_UNCOMPRESS = "AskUncompress";
    private final static String ASK_DELETE = "AskDelete";

    private final static Boolean ASK_COMPRESS_DEFAULT = Boolean.TRUE;
    private final static Boolean ASK_UNCOMPRESS_DEFAULT = Boolean.TRUE;
    private final static Boolean ASK_DELETE_DEFAULT = Boolean.TRUE;

    //----
    /**
     * Get a default instance <code>AskOption</code>.
     *
     * @return AskOption the default locale's option
     */
    public static final AskOption getDefault() {
        return INSTANCE;
    }

    /**
     * Creates a new instance of PatternBeanSettings
     */
    private AskOption() {
    }

    public String displayName() {
        return NbBundle.getMessage(this.getClass(), "CTL_AskOption");
    }

    public HelpCtx getHelpCtx() {
        //return HelpCtx.DEFAULT_HELP;
        return new HelpCtx("about_nbgzip");
    }

    public Boolean getAskCompress() {
        final Boolean value = getPropertyBoolean(ASK_COMPRESS, ASK_COMPRESS_DEFAULT);
        return value;
    }

    public void setAskCompress(Boolean value) {
        setPropertyBoolean(ASK_COMPRESS, value);
    }

    public Boolean getAskUncompress() {
        final Boolean value = getPropertyBoolean(ASK_UNCOMPRESS, ASK_UNCOMPRESS_DEFAULT);
        return value;
    }

    public void setAskUncompress(Boolean value) {
        setPropertyBoolean(ASK_UNCOMPRESS, value);
    }

    public Boolean getAskDelete() {
        final Boolean value = getPropertyBoolean(ASK_DELETE, ASK_DELETE_DEFAULT);
        return value;
    }

    public void setAskDelete(Boolean value) {
        setPropertyBoolean(ASK_DELETE, value);
    }

    protected final Boolean getPropertyBoolean(String key, Boolean def) {
        return NbPreferences.forModule(AskOption.class).getBoolean(key, def);
    }

    protected final void setPropertyBoolean(String key, Boolean value) {
        NbPreferences.forModule(AskOption.class).putBoolean(key, value);
    }
}
