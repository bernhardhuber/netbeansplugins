/*
 * DNamePanel.java
 *
 * Created on 14. Februar 2006, 09:56
 */

package org.huber.keytool.ui.wizard.genkey;

import java.beans.PropertyVetoException;
import java.util.Locale;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import org.huber.keytool.ui.wizard.ChangeObserverOfWizardPanel;
import org.huber.keytool.ui.wizard.ObserverablePanel;
import org.openide.util.NbBundle;

/**
 *
 * @author  HuberB1
 */
public class GenKeyDNamePanel extends javax.swing.JPanel implements ObserverablePanel {
    
    /** Creates new form DNamePanel */
    public GenKeyDNamePanel() {
        initComponents();
        //this.countryCodeComboBox.setModel( cbm );
    }
    
    public void bind(ChangeObserverOfWizardPanel changeObserver) {
        changeObserver.bindDocumentListener( this.firstNameFormattedTextField.getDocument() );
    }
    
    public ObserverablePanel.ValidUserEntryResult isValidUserEntry() {
        final ObserverablePanel.ValidUserEntryResult vuer = new ObserverablePanel.ValidUserEntryResult();
        
        final String firstName = this.firstNameFormattedTextField.getText();
        
        boolean isValid = true;
        
        isValid = isValid && firstName != null && firstName.length() > 0;
        if (!isValid) {
            final String msg = NbBundle.getMessage( GenKeyDNamePanel.class, "ERR_FIRSTNAME_MANDATORY");
            vuer.setInvalidMessage( msg );
            return vuer;
        }
        return vuer;
    }
    
    public String getName() {
        return NbBundle.getMessage( GenKeyDNamePanel.class, "NAME_GenKeyDNamePanel" );
    }
    
    private ComboBoxModel getCountryCodeComboBoxModel() {
        //final String []isoCountries = Locale.getISOCountries();
        final Locale[] locales = Locale.getAvailableLocales();
        final LocaleIsoCountry[] localeIsoCountries = LocaleIsoCountry.createIsoCountries( locales );
        
        final DefaultComboBoxModel dcbm = new DefaultComboBoxModel(localeIsoCountries);
        dcbm.setSelectedItem( new LocaleIsoCountry(Locale.getDefault()) );
        return dcbm;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        organizationalUnitLabel = new javax.swing.JLabel();
        organizationLabel = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        stateLabel = new javax.swing.JLabel();
        countryCodeLabel = new javax.swing.JLabel();
        firstNameFormattedTextField = new javax.swing.JFormattedTextField();
        lastNameFormattedTextField = new javax.swing.JFormattedTextField();
        cityFormattedTextField = new javax.swing.JFormattedTextField();
        organizationalUnitFormattedTextField = new javax.swing.JFormattedTextField();
        organizationFormattedTextField = new javax.swing.JFormattedTextField();
        stateFormattedTextField = new javax.swing.JFormattedTextField();
        countryCodeComboBox = new javax.swing.JComboBox();

        firstNameLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "IDX_Firstname")));
        firstNameLabel.setLabelFor(firstNameFormattedTextField);
        firstNameLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "LBL_Firstname"));

        lastNameLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "IDX_Lastname")));
        lastNameLabel.setLabelFor(lastNameFormattedTextField);
        lastNameLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "LBL_Lastname"));

        organizationalUnitLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "IDX_OrganizationalUnit")));
        organizationalUnitLabel.setLabelFor(organizationalUnitFormattedTextField);
        organizationalUnitLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "LBL_OrganizationalUnit"));

        organizationLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "IDX_Organization")));
        organizationLabel.setLabelFor(organizationFormattedTextField);
        organizationLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "LBL_Organization"));

        cityLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "IDX_City")));
        cityLabel.setLabelFor(cityFormattedTextField);
        cityLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "LBL_City"));

        stateLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "IDX_State")));
        stateLabel.setLabelFor(stateFormattedTextField);
        stateLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "LBL_State"));

        countryCodeLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "IDX_CountryCode")));
        countryCodeLabel.setLabelFor(countryCodeComboBox);
        countryCodeLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyDNamePanel.class, "LBL_CountryCode"));

        countryCodeComboBox.setModel(getCountryCodeComboBoxModel());

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, organizationLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, cityLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, organizationalUnitLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, lastNameLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, firstNameLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, stateLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, countryCodeLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(organizationalUnitFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(organizationFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(cityFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(lastNameFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(countryCodeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(firstNameFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(stateFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(firstNameLabel)
                    .add(firstNameFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lastNameLabel)
                    .add(lastNameFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(organizationalUnitLabel)
                    .add(organizationalUnitFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(organizationLabel)
                    .add(organizationFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cityLabel)
                    .add(cityFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(stateLabel)
                    .add(stateFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(countryCodeLabel)
                    .add(countryCodeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField cityFormattedTextField;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JComboBox countryCodeComboBox;
    private javax.swing.JLabel countryCodeLabel;
    private javax.swing.JFormattedTextField firstNameFormattedTextField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JFormattedTextField lastNameFormattedTextField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JFormattedTextField organizationFormattedTextField;
    private javax.swing.JLabel organizationLabel;
    private javax.swing.JFormattedTextField organizationalUnitFormattedTextField;
    private javax.swing.JLabel organizationalUnitLabel;
    private javax.swing.JFormattedTextField stateFormattedTextField;
    private javax.swing.JLabel stateLabel;
    // End of variables declaration//GEN-END:variables
    
    public void reset() {
        final String EMPTY_STR = "";
        this.cityFormattedTextField.setText(EMPTY_STR);
        this.countryCodeComboBox.setSelectedIndex(0);
        this.firstNameFormattedTextField.setText(EMPTY_STR);
        this.lastNameFormattedTextField.setText(EMPTY_STR);
        this.organizationFormattedTextField.setText(EMPTY_STR);
        this.organizationalUnitFormattedTextField.setText(EMPTY_STR);
        this.stateFormattedTextField.setText(EMPTY_STR);
    }
    
    public String getCity() {
        return this.cityFormattedTextField.getText();
    }
    public void setCity( String newCity ) {
        this.cityFormattedTextField.setText( newCity );
    }
    public String getCountryCode() {
        Object obj = this.countryCodeComboBox.getSelectedItem();
        LocaleIsoCountry lci = (LocaleIsoCountry)obj;
        String countryCode = lci.getIsoCountry();
        return countryCode;
    }
    public void setCountryCode(String newCountryCode) {
        final Locale newLocale = new Locale( "", newCountryCode);
        final LocaleIsoCountry lci = new LocaleIsoCountry(newLocale);
        this.countryCodeComboBox.setSelectedItem( lci );
    }
    
    public String getFirstName() {
        return this.firstNameFormattedTextField.getText();
    }
    public void setFirstName( String newFirstName ) {
        this.firstNameFormattedTextField.setText( newFirstName );
    }
    
    public String getLastName() {
        return this.lastNameFormattedTextField.getText();
    }
    public void setLastName( String newLastName ) {
        this.lastNameFormattedTextField.setText( newLastName );
    }
    public String getOrganization() {
        return this.organizationFormattedTextField.getText();
    }
    public void setOrganization( String newOrganization ) {
        this.organizationFormattedTextField.setText( newOrganization );
    }
    
    public String getOrganizationalUnit() {
        return this.organizationalUnitFormattedTextField.getText();
    }
    public void setOrganizationalUnit( String newOrganizationalUnit ) {
        this.organizationalUnitFormattedTextField.setText( newOrganizationalUnit );
    }
    
    public String getState() {
        return this.stateFormattedTextField.getText();
    }
    public void setState( String newState ) {
        this.stateFormattedTextField.setText( newState );
    }
    
    public void saveTo(GenKeyForm genKeyForm) throws PropertyVetoException {
        genKeyForm.setFirstName( this.getFirstName() );
        genKeyForm.setLastName( this.getLastName() );
        genKeyForm.setOrganization( this.getOrganization() );
        genKeyForm.setOrganizationalUnit( this.getOrganizationalUnit() );
        genKeyForm.setCity( this.getCity() );
        genKeyForm.setState( this.getState() );
        genKeyForm.setCountryCode( this.getCountryCode() );
    }
    public void loadFrom( GenKeyForm genKeyForm ) {
        this.setFirstName( genKeyForm.getFirstName() );
        this.setLastName( genKeyForm.getLastName() );
        this.setOrganization( genKeyForm.getOrganization() );
        this.setOrganizationalUnit( genKeyForm.getOrganizationalUnit() );
        this.setCity( genKeyForm.getCity() );
        this.setState( genKeyForm.getState() );
        this.setCountryCode( genKeyForm.getCountryCode() );
    }
}