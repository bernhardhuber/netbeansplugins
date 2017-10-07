/*
 * KeyAlgCertAlgBean.java
 *
 * Created on 21. Februar 2006, 19:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.model;

import java.io.Serializable;

/**
 * This class encapsulates the key algorithm name, and the cert algorithm name.
 *
 * @author HuberB1
 */
public class KeyAlgCertAlgBean implements Serializable {
    private static final long serialVersionUID = 20060221194800L;
    
    
    /**
     * Creates a new instance of KeyAlgCertAlgBean
     */
    public KeyAlgCertAlgBean() {
    }
    /**
     * Creates a new instance of KeyAlgCertAlgBean
     */
    public KeyAlgCertAlgBean(String newKeyAlg, String newCertAlg) {
        this.keyAlg = newKeyAlg;
        this.certAlg = newCertAlg;
    }
    
    /**
     * Holds value of property keyAlg.
     */
    private String keyAlg;
    
    /**
     * Getter for property keyAlg.
     * @return Value of property keyAlg.
     */
    public String getKeyAlg() {
        return this.keyAlg;
    }
    
    /**
     * Setter for property keyAlg.
     * @param keyAlg New value of property keyAlg.
     */
    public void setKeyAlg(String keyAlg) {
        this.keyAlg = keyAlg;
    }
    
    /**
     * Holds value of property certAlg.
     */
    private String certAlg;
    
    /**
     * Getter for property certAlg.
     * @return Value of property certAlg.
     */
    public String getCertAlg() {
        return this.certAlg;
    }
    
    /**
     * Setter for property certAlg.
     * @param certAlg New value of property certAlg.
     */
    public void setCertAlg(String certAlg) {
        this.certAlg = certAlg;
    }
    
    public int hashCode() {
        return this.getKeyAlg().hashCode() ^
                this.getCertAlg().hashCode();
    }
    
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (obj instanceof KeyAlgCertAlgBean) {
            final KeyAlgCertAlgBean other = (KeyAlgCertAlgBean)obj;
            
            return this.getKeyAlg().equals(other.getKeyAlg()) &&
                    this.getCertAlg().equals(other.getCertAlg());
        }
        
        return false;
    }
}
