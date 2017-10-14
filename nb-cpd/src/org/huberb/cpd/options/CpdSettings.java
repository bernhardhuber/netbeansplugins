/*
 * CpdSettings.java
 *
 * Created on 13. September 2006, 21:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.cpd.options;

import net.sourceforge.pmd.cpd.CSVRenderer;
import net.sourceforge.pmd.cpd.LanguageFactory;
import net.sourceforge.pmd.cpd.Renderer;
import net.sourceforge.pmd.cpd.SimpleRenderer;
import net.sourceforge.pmd.cpd.XMLRenderer;
import org.huberb.cpd.cpdlib.CpdInternalRenderer;
import org.openide.options.SystemOption;
import org.openide.util.NbBundle;

/**
 * Options of the CPD module.
 *
 * @author HuberB1
 */
public class CpdSettings extends SystemOption {
    private static final long serialVersionUID = 20060913215300L;
    
    private final static String IGNORE_IDENTIFIERS_PROP = "ignoreIdentifiers";
    private final static String IGNORE_LITERALS_PROP = "ignoreLiterals";
    private final static String LANGUAGE_PROP = "language";
    private final static String LANGUAGE_EXT_PROP = "languagExt";
    private final static String MINIMAL_TOKEN_COUNT_PROP = "minimalTokenCount";
    private final static String RECURSIVLY_PROP = "recursivly";
    private final static String RENDERER_ENUM_PROP = "rendererEnum";
    
    /**
     * Creates a new instance of CpdSettings
     */
    public CpdSettings() {
    }
    
    @Override
    public String displayName() {
        return NbBundle.getMessage( CpdSettings.class, "AdvancedOption_DisplayName");
    }
    
    /** Initialize shared state.
     * Should use {@link #putProperty} to set up variables.
     * Subclasses should always call the super method.
     * <p>This method need <em>not</em> be called explicitly; it will be called once
     * the first time a given shared class is used (not for each instance!).
     */
    @Override
    protected void initialize() {
        super.initialize();
        
        setIgnoreIdentifiers( Boolean.FALSE );
        setIgnoreLiterals( Boolean.FALSE );
        setLanguage(LanguageFactory.JAVA_KEY);
        setLanguageExt( "text" );
        setMinimalTokenCount(75);
        setRecursivly(Boolean.TRUE);
        
        setRendererEnum(RendererEnum.Text);
    }
    
    
    public static CpdSettings getDefault() {
        return (CpdSettings)SystemOption.findObject( CpdSettings.class, true );
    }
    
    //----
    public Boolean getIgnoreIdentifiers() {
        Boolean value = (Boolean)this.getProperty( IGNORE_IDENTIFIERS_PROP );
        return value;
    }
    public void setIgnoreIdentifiers( Boolean newValue ) {
        this.putProperty( IGNORE_IDENTIFIERS_PROP, newValue, true );
    }
    
    //----
    public Boolean getIgnoreLiterals() {
        Boolean value = (Boolean)this.getProperty( IGNORE_LITERALS_PROP );
        return value;
    }
    public void setIgnoreLiterals( Boolean newValue ) {
        this.putProperty( IGNORE_LITERALS_PROP, newValue, true );
    }
    
    //----
    public String getLanguage() {
        String value = (String)this.getProperty( LANGUAGE_PROP );
        return value;
    }
    public void setLanguage( String newValue ) {
        this.putProperty( LANGUAGE_PROP, newValue, true );
    }
    
    //----
    public String getLanguageExt() {
        String value = (String)this.getProperty( LANGUAGE_EXT_PROP );
        return value;
    }
    public void setLanguageExt( String newValue ) {
        this.putProperty( LANGUAGE_EXT_PROP, newValue, true );
    }
    
    //----
    public Integer getMinimalTokenCount() {
        Integer value = (Integer)this.getProperty( MINIMAL_TOKEN_COUNT_PROP );
        return value;
    }
    public void setMinimalTokenCount( Integer newValue ) {
        this.putProperty( MINIMAL_TOKEN_COUNT_PROP, newValue, true );
    }
    
    //----
    public Boolean getRecursivly() {
        Boolean value = (Boolean)this.getProperty( RECURSIVLY_PROP );
        return value;
    }
    public void setRecursivly( Boolean newValue ) {
        this.putProperty( RECURSIVLY_PROP, newValue, true );
    }
    
    public static enum RendererEnum {
        Text, Xml, Csv, Internal;
        
        private final static long serialVersionUID = 2007042100L;
        
        public Renderer createRenderer() {
            Renderer renderer = null;
            switch (this) {
                case Text:
                    renderer = new SimpleRenderer();
                    break;
                case Csv:
                    renderer = new CSVRenderer();
                    break;
                case Xml:
                    renderer = new XMLRenderer();
                    break;
                case Internal:
                default:
                    renderer = new CpdInternalRenderer();
                    break;
            }
            return renderer;
        }
    }
    //----
    public RendererEnum getRendererEnum() {
        RendererEnum value = (RendererEnum )this.getProperty( RENDERER_ENUM_PROP );
        return value;
    }
    public void setRendererEnum( RendererEnum newValue ) {
        this.putProperty( RENDERER_ENUM_PROP, newValue, true );
    }
}

