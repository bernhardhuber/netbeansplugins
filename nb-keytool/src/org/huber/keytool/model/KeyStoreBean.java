/*
 * KeyStoreBean.java
 *
 * Created on 18. Februar 2006, 10:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.model;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.KeyStore;
import org.openide.ErrorManager;

/**
 * Encapsulate the attributes of the KeyStore application.
 *
 * @author HuberB1
 */
public class KeyStoreBean implements Serializable, Comparable {
    private final static long serialVersionUID = 200602181038L;
    
    public final static String NAME_PROPERTY = "name";
    public final static String KEY_STORE_PROPERTY = "keyStore";
    public final static String STORE_PASSWORD_PROPERTY = "storePassword";
    
    /**
     * Holds value of property name.
     */
    private String name;
    /**
     * Holds value of property keyStore.
     */
    private transient KeyStore keyStore;
    /**
     * Holds value of property storePassword.
     */
    private char[] storePassword;
    /**
     * Utility field used by bound properties.
     */
    private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
    
    
    /** Creates a new instance of KeyStoreBean */
    public KeyStoreBean() {
    }
    
    /** Creates a new instance of KeyStoreBean */
    public KeyStoreBean(String newName, KeyStore newKeyStore, char[] newPassword ) {
        this.setKeyStoreValues( newName, newKeyStore, newPassword );
    }
    
    public void setKeyStoreValues(String newName, KeyStore newKeyStore, char[] newPassword ) {
        this.setName( newName );
        this.setKeyStore( newKeyStore );
        this.setStorePassword( newPassword );
    }
    
    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange(NAME_PROPERTY, oldName, name);
    }
    /**
     * Getter for property keyStore.
     * @return Value of property keyStore.
     */
    public KeyStore getKeyStore() {
        return this.keyStore;
    }
    /**
     * Setter for property keyStore.
     * @param keyStore New value of property keyStore.
     */
    public void setKeyStore(KeyStore keyStore) {
        KeyStore oldKeyStore = this.keyStore;
        this.keyStore = keyStore;
        propertyChangeSupport.firePropertyChange(KEY_STORE_PROPERTY, oldKeyStore, keyStore);
    }
    
    public boolean isKeyStoreLoaded() {
        return this.keyStore != null;
    }
    
    /**
     * Getter for property storePassword.
     *
     * @return Value of property storePassword.
     */
    public char[] getStorePassword() {
        return this.storePassword;
    }
    /**
     * Setter for property newStorePassword.
     *
     * @param newStorePassword New value of property newStorePassword.
     */
    public void setStorePassword(char[] newStorePassword) {
        char[] oldPassword = this.storePassword;
        this.storePassword = newStorePassword;
        propertyChangeSupport.firePropertyChange(STORE_PASSWORD_PROPERTY, oldPassword, newStorePassword);
    }
    
    //---- property change listener support
    
    /**
     * Adds a PropertyChangeListener to the listener list.
     * @param l The listener to add.
     */
    public void addPropertyChangeListener(java.beans.PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }
    
    /**
     * Removes a PropertyChangeListener from the listener list.
     * @param l The listener to remove.
     */
    public void removePropertyChangeListener(java.beans.PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    
    public int hashCode() {
        int hashCode = 0;
        
        if (this.name != null) {
            hashCode = this.name.hashCode();
        }
        return hashCode;
    }
    public boolean equals( Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof KeyStoreBean) {
            final KeyStoreBean other = (KeyStoreBean)obj;
            final String name = this.name;
            final String otherName = other.name;
            if (name != null && other != null) {
                boolean equals = true;
                equals = equals && name.equals( otherName );
                equals = equals && this.isKeyStoreLoaded() == other.isKeyStoreLoaded();
                return equals;
            }
            
        }
        return false;
    }
    public int compareTo(Object o) {
        if (o == this) {
            return 0;
        }
        if (o != null && o instanceof KeyStoreBean) {
            KeyStoreBean otherKsb = (KeyStoreBean)o;
            final String otherKsbName = otherKsb.getName();
            final String name = this.getName();
            
            if (name != null && otherKsbName != null) {
                return name.compareTo( otherKsbName );
            } else if (name != null && otherKsbName == null) {
                return 1;
            } else {
                return -1;
            }
        }
        return 1;
    }
    
    /**
     * Save the key store file
     */
    public void saveKeyStoreFile() {
        final KeyStore ks = this.getKeyStore();
        
        final File keyStoreFile = new File( this.getName() );
        final char[] password = this.getStorePassword();
        
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(keyStoreFile);
            ks.store( fos, password );
        } catch (Exception ex) {
            ErrorManager.getDefault().notify( ex );
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    ErrorManager.getDefault().notify( ErrorManager.INFORMATIONAL, ex );
                }
            }
        }
    }
}
