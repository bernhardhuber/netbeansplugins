/*
 * GenKeyForm.java
 *
 * Created on 15. Februar 2006, 20:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huber.keytool.ui.wizard.genkey;

import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.Locale;
import javax.security.auth.x500.X500Principal;
import org.huber.keytool.model.KeyAlgCertAlgBean;
import org.huber.keytool.settings.KeytoolSettings;
import org.openide.util.NbBundle;

/**
 * A form holding all user input entries
 *
 * @author HuberB1
 */
public class GenKeyForm implements Serializable {
    private static final long serialVersionUID = 20060215205700L;
    
    /** Creates a new instance of GenKeyForm */
    public GenKeyForm() {
        final String EMPTY_STRING = "";
        KeytoolSettings keytoolSettings = KeytoolSettings.getDefault();
        
        this.alias = EMPTY_STRING;
        this.city = EMPTY_STRING;
        this.countryCode = Locale.getDefault().getCountry();
        this.firstName = EMPTY_STRING;
        this.kaca = LabelKeyAlgCertAlgBeanFactory.RSA;
        this.keyPassword = new char[0];
        this.keySize = 1024;
        this.lastName = EMPTY_STRING;
        this.organization = EMPTY_STRING;
        this.organizationalUnit = EMPTY_STRING;
        this.state = EMPTY_STRING;
        this.validity = keytoolSettings.getDefaultKeyValidityInSecs();
    }
    
    /**
     * Holds value of property alias.
     */
    private String alias;
    
    /**
     * Utility field used by bound properties.
     */
    private PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
    
    /**
     * Utility field used by constrained properties.
     */
    private java.beans.VetoableChangeSupport vetoableChangeSupport =  new VetoableChangeSupport(this);
    
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
    
    /**
     * Adds a VetoableChangeListener to the listener list.
     * @param l The listener to add.
     */
    public void addVetoableChangeListener(java.beans.VetoableChangeListener l) {
        vetoableChangeSupport.addVetoableChangeListener(l);
    }
    
    /**
     * Removes a VetoableChangeListener from the listener list.
     * @param l The listener to remove.
     */
    public void removeVetoableChangeListener(java.beans.VetoableChangeListener l) {
        vetoableChangeSupport.removeVetoableChangeListener(l);
    }
    
    /**
     * Getter for property alias.
     * @return Value of property alias.
     */
    public String getAlias() {
        return this.alias;
    }
    
    /**
     * Setter for property alias.
     * @param alias New value of property alias.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public void setAlias(String alias) throws java.beans.PropertyVetoException {
        String oldAlias = this.alias;
        vetoableChangeSupport.fireVetoableChange("alias", oldAlias, alias);
        this.alias = alias;
        propertyChangeSupport.firePropertyChange("alias", oldAlias, alias);
    }
    
    /**
     * Holds value of property keySize.
     */
    private int keySize;
    
    /**
     * Getter for property keySize.
     * @return Value of property keySize.
     */
    public int getKeySize() {
        return this.keySize;
    }
    
    /**
     * Setter for property keySize.
     * @param keySize New value of property keySize.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public void setKeySize(int keySize) throws java.beans.PropertyVetoException {
        int oldKeySize = this.keySize;
        vetoableChangeSupport.fireVetoableChange("keySize", Integer.valueOf(oldKeySize), Integer.valueOf(keySize));
        this.keySize = keySize;
        propertyChangeSupport.firePropertyChange("keySize", Integer.valueOf(oldKeySize), Integer.valueOf(keySize));
    }
    
    /**
     * Holds value of property validity.
     */
    private long validity;
    
    /**
     * Getter for property validity.
     * @return Value of property validity.
     */
    public long getValidityInSecs() {
        return this.validity;
    }
    
    /**
     * Setter for property validity.
     * @param validity New value of property validity.
     */
    public void setValidityInSecs(long validity) throws java.beans.PropertyVetoException {
        long oldValidity = this.validity;
        vetoableChangeSupport.fireVetoableChange("validity", Long.valueOf(oldValidity), Long.valueOf(validity));
        this.validity = validity;
        propertyChangeSupport.firePropertyChange("validity", Long.valueOf(oldValidity), Long.valueOf(validity));
    }
    
    /**
     * Holds value of property firstName.
     */
    private String firstName;
    
    /**
     * Getter for property firstName.
     * @return Value of property firstName.
     */
    public String getFirstName() {
        return this.firstName;
    }
    
    /**
     * Setter for property firstName.
     * @param firstName New value of property firstName.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public void setFirstName(String firstName) throws java.beans.PropertyVetoException {
        String oldFirstName = this.firstName;
        vetoableChangeSupport.fireVetoableChange("firstName", oldFirstName, firstName);
        this.firstName = firstName;
        propertyChangeSupport.firePropertyChange("firstName", oldFirstName, firstName);
    }
    
    /**
     * Holds value of property lastName.
     */
    private String lastName;
    
    /**
     * Getter for property lastName.
     * @return Value of property lastName.
     */
    public String getLastName() {
        return this.lastName;
    }
    
    /**
     * Setter for property lastName.
     * @param lastName New value of property lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Holds value of property organizationalUnit.
     */
    private String organizationalUnit;
    
    /**
     * Getter for property organizationalUnit.
     * @return Value of property organizationalUnit.
     */
    public String getOrganizationalUnit() {
        return this.organizationalUnit;
    }
    
    /**
     * Setter for property organizationalUnit.
     * @param organizationalUnit New value of property organizationalUnit.
     */
    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }
    
    /**
     * Holds value of property organization.
     */
    private String organization;
    
    /**
     * Getter for property organization.
     * @return Value of property organization.
     */
    public String getOrganization() {
        return this.organization;
    }
    
    /**
     * Setter for property organization.
     * @param organization New value of property organization.
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    /**
     * Holds value of property city.
     */
    private String city;
    
    /**
     * Getter for property city.
     * @return Value of property city.
     */
    public String getCity() {
        return this.city;
    }
    
    /**
     * Setter for property city.
     * @param city New value of property city.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public void setCity(String city) throws java.beans.PropertyVetoException {
        String oldCity = this.city;
        vetoableChangeSupport.fireVetoableChange("city", oldCity, city);
        this.city = city;
        propertyChangeSupport.firePropertyChange("city", oldCity, city);
    }
    
    /**
     * Holds value of property state.
     */
    private String state;
    
    /**
     * Getter for property state.
     * @return Value of property state.
     */
    public String getState() {
        return this.state;
    }
    
    /**
     * Setter for property state.
     * @param state New value of property state.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public void setState(String state) throws java.beans.PropertyVetoException {
        String oldState = this.state;
        vetoableChangeSupport.fireVetoableChange("state", oldState, state);
        this.state = state;
        propertyChangeSupport.firePropertyChange("state", oldState, state);
    }
    
    /**
     * Holds value of property countryCode.
     */
    private String countryCode;
    
    /**
     * Getter for property countryCode.
     * @return Value of property countryCode.
     */
    public String getCountryCode() {
        return this.countryCode;
    }
    
    /**
     * Setter for property countryCode.
     * @param countryCode New value of property countryCode.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public void setCountryCode(String countryCode) throws java.beans.PropertyVetoException {
        String oldCountryCode = this.countryCode;
        vetoableChangeSupport.fireVetoableChange("countryCode", oldCountryCode, countryCode);
        this.countryCode = countryCode;
        propertyChangeSupport.firePropertyChange("countryCode", oldCountryCode, countryCode);
    }
    
    /**
     * Holds value of property keyPassword.
     */
    private char[] keyPassword;
    
    /**
     * Getter for property keyPassword.
     *
     * @return Value of property keyPassword.
     */
    public char[] getKeyPassword() {
        return this.keyPassword;
    }
    
    /**
     * Setter for property newKeyPassword.
     *
     * @param newKeyPassword New value of property newKeyPassword.
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public void setKeyPassword(char[] newKeyPassword) throws java.beans.PropertyVetoException {
        char[] oldPassword = this.keyPassword;
        vetoableChangeSupport.fireVetoableChange("keyPassword", oldPassword, newKeyPassword);
        this.keyPassword = newKeyPassword;
        propertyChangeSupport.firePropertyChange("keyPassword", oldPassword, newKeyPassword);
    }
    
    public String format() {
        final StringBuilder sb = new StringBuilder();
        
        sb.append(
                NbBundle.getMessage(GenKeyForm.class, "GenKeyFormFormat1",
                new Object[] { this.alias, this.validity, this.keySize } )
                );
        
        sb.append( getX500PrincipalAsString() );
        
        return sb.toString();
    }
    
    public String getX500PrincipalAsString() {
        final String x500PrincipalAsString =
                NbBundle.getMessage(GenKeyForm.class, "GenKeyFormFormat2",
                new Object[] {
            this.firstName, this.lastName,
            this.organizationalUnit,
            this.organization,
            this.city,
            this.state,
            this.countryCode,
        } );
        
        return x500PrincipalAsString;
    }
    
    public X500Principal getX500Principal() {
        final String name = getX500PrincipalAsString();
        X500Principal x500Principal = new X500Principal(name);
        return x500Principal;
    }
    
    /**
     * Holds value of property kaca.
     */
    private KeyAlgCertAlgBean kaca;
    
    /**
     * Getter for property kaca.
     * @return Value of property kaca.
     */
    public KeyAlgCertAlgBean getKeyAlgCertAlg() {
        return this.kaca;
    }
    
    /**
     * Setter for property kaca.
     * @param kaca New value of property kaca.
     *
     * @throws PropertyVetoException if some vetoable listeners reject the new value
     */
    public void setKeyAlgCertAlg(KeyAlgCertAlgBean kaca) throws java.beans.PropertyVetoException {
        KeyAlgCertAlgBean oldKaca = this.kaca;
        vetoableChangeSupport.fireVetoableChange("kaca", oldKaca, kaca);
        this.kaca = kaca;
        propertyChangeSupport.firePropertyChange("kaca", oldKaca, kaca);
    }
    
}
