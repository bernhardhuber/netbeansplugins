/*
 * AskOption.java
 *
 * Created on 21. Dezember 2005, 09:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbgzip.option;

import org.openide.options.SystemOption;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 * Encapsulate options in this class.
 *
 * @author HuberB1
 */
public class AskOption extends SystemOption {
    protected final static long serialVersionUID = 20060116221600L;
        
    /** Creates a new instance of PatternBeanSettings */
    public AskOption() {
    }
    
    public String displayName() {
        return NbBundle.getMessage(this.getClass(), "CTL_AskOption");
    }
    
    public HelpCtx getHelpCtx() {
        //return HelpCtx.DEFAULT_HELP;
        return new HelpCtx( "about_nbgzip" );
    }
    
    //----
    private final static String ASK_COMPRESS = "AskCompress";
    public Boolean getAskCompress() {
        final Boolean value = (Boolean)getProperty( ASK_COMPRESS );
        return value;
    }
    public void setAskCompress( Boolean newMatchesResultMessage ) {
        putProperty(ASK_COMPRESS, newMatchesResultMessage, true );
    }

    //----
    private final static String ASK_UNCOMPRESS = "AskUncompress";
    public Boolean getAskUncompress() {
        final Boolean value = (Boolean)getProperty( ASK_UNCOMPRESS );
        return value;
    }
    public void setAskUncompress( Boolean newMatchesResultMessage ) {
        putProperty(ASK_UNCOMPRESS, newMatchesResultMessage, true );
    }
    
    //----
    private final static String ASK_DELETE = "AskDelete";
    public Boolean getAskDelete() {
        final Boolean value = (Boolean)getProperty( ASK_DELETE );
        return value;
    }
    public void setAskDelete( Boolean newMatchesResultMessage ) {
        putProperty(ASK_DELETE, newMatchesResultMessage, true );
    }
    
    //----
    /**
     * Get a default instance <code>AskOption</code>.
     *
     * @return AskOption the default locale's option
     */
    public static final AskOption getDefault() {
        return (AskOption)findObject(AskOption.class, true);
    }
    
    private final static Boolean ASK_COMPRESS_DEFAULT = Boolean.TRUE;
    private final static Boolean ASK_UNCOMPRESS_DEFAULT = Boolean.TRUE;
    private final static Boolean ASK_DELETE_DEFAULT = Boolean.TRUE;
    
    /**
     * initialize this option
     */
    protected void initialize() {
        super.initialize();
        
        putProperty( ASK_COMPRESS, ASK_COMPRESS_DEFAULT, false );
        putProperty( ASK_UNCOMPRESS, ASK_UNCOMPRESS_DEFAULT, false );
        putProperty( ASK_DELETE, ASK_DELETE_DEFAULT, false );
    }
}
