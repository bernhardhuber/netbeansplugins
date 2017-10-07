/*
 * JDependSettings.java
 *
 * Created on 13. September 2006, 21:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.jdepend.options;

import org.huberb.jdepend.misc.ConstantsHelper;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.Repository;
import org.openide.options.SystemOption;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class JDependSettings extends SystemOption {
    private static final long serialVersionUID = 20060913215300L;
    
    private final static String ANALYZE_INNER_CLASSES_PROP = "analyzeInnerClasses";
    private final static String FILTER_PACKAGES_PROP = "filterPackages";
    private final static String DEFAULT_FILTER_PACKAGES_PROP = "defaultFilterPackages";
    private final static String XSLT_FILE_OBJECT_PROP = "xsltFileObject";
    
    /** Creates a new instance of JDependSettings */
    public JDependSettings() {
    }
    
    public String displayName() {
        return NbBundle.getMessage( JDependSettings.class, "AdvancedOption_DisplayName");
    }
    /** Initialize shared state.
     * Should use {@link #putProperty} to set up variables.
     * Subclasses should always call the super method.
     * <p>This method need <em>not</em> be called explicitly; it will be called once
     * the first time a given shared class is used (not for each instance!).
     */
    protected void initialize() {
        super.initialize();
        
        setAnalyzerInnerClasses( Boolean.FALSE );
        
        final String[] filterPackages = new String[] { "java.*", "javax.*"};
        setFilterPackages( filterPackages );
        setDefaultFilterPackages( filterPackages );
        
        final String DEFAULT_XSLT_FILE_OBJECT_NAME = ConstantsHelper.getStandardXsltFileObjectName();
        final Repository repo = Repository.getDefault();
        final FileSystem fs = repo.getDefaultFileSystem();
        
        final FileObject fo = fs.findResource(  DEFAULT_XSLT_FILE_OBJECT_NAME );
        setXsltFileObject( fo );
    }
    
    
    public static JDependSettings getDefault() {
        return (JDependSettings)SystemOption.findObject( JDependSettings.class, true );
    }
    //----
    public Boolean getAnalyzerInnerClasses() {
        Boolean value = (Boolean)this.getProperty( ANALYZE_INNER_CLASSES_PROP );
        return value;
    }
    public void setAnalyzerInnerClasses( Boolean newValue ) {
        this.putProperty( ANALYZE_INNER_CLASSES_PROP, newValue, true );
    }
    
    //----
    public String[] getFilterPackages() {
        final String[] filterPackages = (String[])this.getProperty(FILTER_PACKAGES_PROP);
        return filterPackages;
    }
    public void setFilterPackages( String []newValue ) {
        this.putProperty( FILTER_PACKAGES_PROP, newValue.clone(), true );
    }
    
    //----
    public String[] getDefaultFilterPackages() {
        final String[] filterPackages = (String[])this.getProperty(DEFAULT_FILTER_PACKAGES_PROP);
        return filterPackages;
    }
    public void setDefaultFilterPackages( String []newValue ) {
        this.putProperty( DEFAULT_FILTER_PACKAGES_PROP, newValue.clone(), true );
    }
    
    
    //----
    public FileObject getXsltFileObject() {
        final FileObject xsltFileObject = (FileObject)this.getProperty(XSLT_FILE_OBJECT_PROP);
        return xsltFileObject;
    }
    public void setXsltFileObject( FileObject newValue ) {
        this.putProperty( XSLT_FILE_OBJECT_PROP, newValue, true );
    }
}
