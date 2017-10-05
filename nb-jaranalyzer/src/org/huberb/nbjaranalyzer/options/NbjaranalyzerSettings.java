/*
 * NbjaranalyzerSettings.java
 *
 * Created on 13. September 2006, 21:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.nbjaranalyzer.options;

import java.util.Arrays;
import java.util.List;
import org.huberb.nbjaranalyzer.misc.ConstantsHelper;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.Repository;
import org.openide.options.SystemOption;
import org.openide.util.NbBundle;

/**
 *
 * @author HuberB1
 */
public class NbjaranalyzerSettings extends SystemOption {
    private static final long serialVersionUID = 20060913215300L;
    
    private final static String FILTER_PACKAGES_PROP = "filterPackages";
    private final static String DEFAULT_FILTER_PACKAGES_PROP = "defaultFilterPackages";
    
    private final static String FILTER_JARS_PROP = "filterJars";
    private final static String DEFAULT_FILTER_JARS_PROP = "defaultFilterJars";
    
    private final static String XSLT_FILE_OBJECT_PROP = "xsltFileObject";
    
    /** Creates a new instance of JDependSettings */
    public NbjaranalyzerSettings() {
    }
    
    public String displayName() {
        return NbBundle.getMessage( NbjaranalyzerSettings.class, "AdvancedOption_DisplayName");
    }
    /** Initialize shared state.
     * Should use {@link #putProperty} to set up variables.
     * Subclasses should always call the super method.
     * <p>This method need <em>not</em> be called explicitly; it will be called once
     * the first time a given shared class is used (not for each instance!).
     */
    protected void initialize() {
        super.initialize();
        
        final String[] filterPackages = new String[] { "java.", "javax."};
        setFilterPackages( filterPackages );
        setDefaultFilterPackages( filterPackages );
        
        final String[] filterJars = new String[] {};
        setFilterJars( filterJars );
        setDefaultFilterJars( filterJars );
        
        final String DEFAULT_XSLT_FILE_OBJECT_NAME = ConstantsHelper.getStandardXsltFileObjectName();
        final Repository repo = Repository.getDefault();
        final FileSystem fs = repo.getDefaultFileSystem();
        
        final FileObject fo = fs.findResource(  DEFAULT_XSLT_FILE_OBJECT_NAME );
        setXsltFileObject( fo );
    }
    
    
    public static NbjaranalyzerSettings getDefault() {
        return (NbjaranalyzerSettings)SystemOption.findObject( NbjaranalyzerSettings.class, true );
    }
    
    //----
    public String[] getFilterPackages() {
        final String[] filterPackages = (String[])this.getProperty(FILTER_PACKAGES_PROP);
        return filterPackages;
    }
    public List<String> getFilterPackagesAsList() {
        String[] filterPackages = this.getFilterPackages();
        List<String> asList = Arrays.asList( filterPackages );
        return asList;
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
    public String[] getFilterJars() {
        final String[] filterPackages = (String[])this.getProperty(FILTER_JARS_PROP);
        return filterPackages;
    }
    public List<String> getFilterJarsAsList() {
        String[] filterJars = this.getFilterJars();
        List<String> asList = Arrays.asList( filterJars );
        return asList;
    }
    public void setFilterJars( String []newValue ) {
        this.putProperty( FILTER_JARS_PROP, newValue.clone(), true );
    }
    
    //----
    public String[] getDefaultFilterJars() {
        final String[] filterPackages = (String[])this.getProperty(DEFAULT_FILTER_JARS_PROP);
        return filterPackages;
    }
    public void setDefaultFilterJars( String []newValue ) {
        this.putProperty( DEFAULT_FILTER_JARS_PROP, newValue.clone(), true );
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
