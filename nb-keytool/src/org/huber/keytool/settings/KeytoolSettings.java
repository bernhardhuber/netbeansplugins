/*
 * KeytoolSettings.java
 *
 * Created on 11. November 2006, 10:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.settings;

import org.huber.keytool.ui.wizard.genkey.LabelKeyAlgCertAlgBean;
import org.huber.keytool.ui.wizard.genkey.LabelKeyAlgCertAlgBeanFactory;
import org.openide.options.SystemOption;
import org.openide.util.NbBundle;

/**
 * Persistant Keytool settings
 *
 * @author HuberB1
 */
public class KeytoolSettings extends SystemOption {
    protected final static long serialVersionUID = 200611111056L;
    
    /** Creates a new instance of KeytoolSettings */
    public KeytoolSettings() {
    }
    
    public String displayName() {
        return NbBundle.getMessage( KeytoolSettings.class, "OptionsCategory_Title");
    }
    
    //---
    // settings
    
    // min password length
    private static final String MIN_PASSWORD_LENGTH = "MIN_PASSWORD_LENGTH";
    public Integer getMinPasswordLength() {
        return (Integer)this.getProperty( MIN_PASSWORD_LENGTH );
    }
    public void setMinPasswordLength( Integer newMinPasswordLength ) {
        this.putProperty( MIN_PASSWORD_LENGTH, newMinPasswordLength, true );
    }
    
    private static final String DEFAULT_KEY_VALIDITY = "DEFAULT_KEY_VALIDITY";
    public Integer getDefaultKeyValidityInDays() {
        return (Integer)this.getProperty( DEFAULT_KEY_VALIDITY );
    }
    public void setDefaultKeyValidityInDays( Integer newDefaultKeyValidity ) {
        this.putProperty( DEFAULT_KEY_VALIDITY, newDefaultKeyValidity, true );
    }
    public Long getDefaultKeyValidityInSecs() {
        Integer defaultValidityInDays = getDefaultKeyValidityInDays();
        Long defaultKeyValidityInSecs = defaultValidityInDays * 24L * 60L * 60L;
        return defaultKeyValidityInSecs;
    }
    
    private static final String DEFAULT_GENKEY_ALGORITHM = "DEFAULT_GENKEY_ALGORITHM";
    public LabelKeyAlgCertAlgBean getDefaultGenKeyAlgorithm() {
        return (LabelKeyAlgCertAlgBean)this.getProperty( DEFAULT_GENKEY_ALGORITHM );
    }
    public void setDefaultGenKeyAlgorithm(LabelKeyAlgCertAlgBean v) {
        this.putProperty( DEFAULT_GENKEY_ALGORITHM, v, true );
    }
    
    private static final String DEFAULT_CSR_ALGORITHM = "DEFAULT_CSR_ALGORITHM";
    public LabelKeyAlgCertAlgBean getDefaultCSRAlgorithm() {
        return (LabelKeyAlgCertAlgBean)this.getProperty( DEFAULT_CSR_ALGORITHM );
    }
    public void setDefaultCSRAlgorithm(LabelKeyAlgCertAlgBean v) {
        this.putProperty( DEFAULT_CSR_ALGORITHM, v, true );
    }
    
    //----
    // static members
    public static KeytoolSettings getDefault() {
        return (KeytoolSettings)SystemOption.findObject( KeytoolSettings.class, true );
    }
    
    /** Initialize shared state.
     * Should use {@link #putProperty} to set up variables.
     * Subclasses should always call the super method.
     * <p>This method need <em>not</em> be called explicitly; it will be called once
     * the first time a given shared class is used (not for each instance!).
     */
    protected void initialize() {
        super.initialize();
        
        setMinPasswordLength( 6 );
        setDefaultKeyValidityInDays( 90 );
        
        LabelKeyAlgCertAlgBean l1 = LabelKeyAlgCertAlgBeanFactory.createLabelKeyAlgCertAlgBean(LabelKeyAlgCertAlgBeanFactory.DSA);
        setDefaultCSRAlgorithm(l1);
        
        LabelKeyAlgCertAlgBean l2 = LabelKeyAlgCertAlgBeanFactory.createLabelKeyAlgCertAlgBean(LabelKeyAlgCertAlgBeanFactory.RSA);
        setDefaultGenKeyAlgorithm(l2);
    }
    
}
