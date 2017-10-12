/*
 * DigestOption.java
 *
 * Created on 10. Oktober 2005, 09:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.digestnb.option;

import org.openide.options.SystemOption;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 *
 * @author huberb1
 */
public class DigestOption extends SystemOption {

    protected final static long serialVersionUID = 20051010124300L;

    public static final String PROPERTY_DIGEST_FORMAT_MESSAGE = "digestFormatMessage";
    private final static String DEFAULT_DIGEST_MESSAGE_FORMAT = "secret {0} [{1} (algorigthm), {2} (encoding)] : {3}";

    /**
     * Get a default instance <code>LocaleOption</code>.
     *
     * @return LocaleOption the default locale's option
     */
    public static final DigestOption getDefault() {
        return (DigestOption) findObject(DigestOption.class, true);
    }

    /**
     * Creates a new instance of DigestOption
     */
    public DigestOption() {
    }

    @Override
    public String displayName() {
        return NbBundle.getMessage(DigestOption.class, "CTL_DigestOption");
    }

    /**
     * Get the digest format message
     */
    public String getDigestFormatMessage() {
        return (String) getProperty(PROPERTY_DIGEST_FORMAT_MESSAGE);
    }

    /**
     * Set a new digest format message
     */
    public void setDigestFormatMessage(String newDigestFormatMessage) {
        putProperty(PROPERTY_DIGEST_FORMAT_MESSAGE, newDigestFormatMessage, true);
    }

    @Override
    protected void initialize() {
        super.initialize();
        putProperty(PROPERTY_DIGEST_FORMAT_MESSAGE, DEFAULT_DIGEST_MESSAGE_FORMAT);
    }

    @Override
    public HelpCtx getHelpCtx() {
        final HelpCtx retValue = new HelpCtx("about_digestnb");
        return retValue;
    }
}
