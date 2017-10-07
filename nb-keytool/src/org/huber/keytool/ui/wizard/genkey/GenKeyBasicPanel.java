/*
 * GenKeyPanel.java
 *
 * Created on 14. Februar 2006, 00:53
 */

package org.huber.keytool.ui.wizard.genkey;

import java.beans.PropertyVetoException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.ChangeEvent;

import org.huber.keytool.model.KeyAlgCertAlgBean;
import org.huber.keytool.settings.KeytoolSettings;
import org.huber.keytool.ui.wizard.ChangeObserverOfWizardPanel;
import org.huber.keytool.ui.wizard.ObserverablePanel;
import org.openide.util.NbBundle;

/**
 *
 * @author  HuberB1
 */
public class GenKeyBasicPanel extends JPanel implements ObserverablePanel {
    
    /** Creates new form GenKeyPanel */
    public GenKeyBasicPanel() {
        initComponents();
        
        this.aliasFormattedTextField.setText( "mykey" );
        initKeySize();
        initValidity();
        
        final DefaultComboBoxModel dcbm = new DefaultComboBoxModel( LabelKeyAlgCertAlgBeanFactory.createLabelKeyAlgCertAlgBeans() );
        this.keyAlgComboBox.setModel( dcbm );
        
        this.reset();
    }
    
    
    private void initKeySize() {
        final SpinnerNumberModel snm = (SpinnerNumberModel) this.keySizeSpinner.getModel();
        snm.setMinimum( 512 );
        snm.setMaximum( 1024 );
        snm.setValue( 512 );
    }
    
    private void initValidity() {
        final SpinnerNumberModel snm = (SpinnerNumberModel) this.validitySpinner.getModel();
        snm.setMinimum( 1 );
        snm.setMaximum( Integer.MAX_VALUE );
        snm.setValue( 90 );
    }
    
    public void bind( ChangeObserverOfWizardPanel co ) {
        co.bindDocumentListener( this.aliasFormattedTextField.getDocument() );
        co.bindChangeListener( this.validitySpinner.getModel() );
        co.bindChangeListener( this.keySizeSpinner.getModel() );
        co.bindDocumentListener( this.keyPasswordField.getDocument() );
        co.bindDocumentListener( this.keyPasswordReTypedField.getDocument() );
        co.bindChangeListener( this.keyPasswordCheckBox );
    }
    
    public ObserverablePanel.ValidUserEntryResult isValidUserEntry() {
        final ObserverablePanel.ValidUserEntryResult vuer = new ObserverablePanel.ValidUserEntryResult();
        
        //final StringBuffer errorLabelText = new StringBuffer();
        
        final String alias = this.getAlias();
        final int keySize = this.getKeySize();
        final long validity = this.getValidityInDays();
        final char[] keyPassword = this.getKeyPassword();
        boolean isValid = true;
        
        if (isValid) {
            isValid = isValid && alias != null && alias.length() > 0;
            if (!isValid) {
                final String msg = NbBundle.getMessage( GenKeyBasicPanel.class, "ERR_ALIAS" );
                vuer.setInvalidMessage( msg );
                return vuer;
            }
        }
        if (isValid) {
            isValid = isValid && keySize >= 512;
            isValid = isValid && keySize <= 1024;
            isValid = isValid && ((keySize % 64) == 0);
            if (!isValid) {
                final String msg = NbBundle.getMessage( GenKeyBasicPanel.class, "ERR_KEYSIZE" );
                vuer.setInvalidMessage( msg );
                return vuer;
            }
        }
        if (isValid) {
            isValid = isValid && validity > 0;
            if (!isValid) {
                final String msg = NbBundle.getMessage( GenKeyBasicPanel.class, "ERR_VALIDITY" );
                vuer.setInvalidMessage( msg );
                return vuer;
            }
        }
        
        if (isValid) {
            KeytoolSettings keytoolSettings = KeytoolSettings.getDefault();
            final int MIN_LENGTH = keytoolSettings.getMinPasswordLength();
            
            if (!this.keyPasswordCheckBox.isSelected()) {
                isValid = isValid && keyPassword != null;
                isValid = isValid && keyPassword.length >= MIN_LENGTH;
                if (!isValid) {
                    final String msg = NbBundle.getMessage( GenKeyBasicPanel.class, "ERR_KEYPASSWORD", MIN_LENGTH );
                    vuer.setInvalidMessage( msg );
                    return vuer;
                }
                
                if (isValid) {
                    char[] keyPasswordReTyped = this.keyPasswordReTypedField.getPassword();
                    isValid = isValid && equalChars( keyPassword, keyPasswordReTyped);
                    if (!isValid) {
                        
                        final String msg = NbBundle.getMessage( GenKeyBasicPanel.class, "ERR_KEYPASSWORD_RETYPED" );
                        vuer.setInvalidMessage( msg );
                        return vuer;
                    }
                }
            }
        }
        
        return vuer;
    }
    
    private boolean equalChars( char[] c1, char[] c2 ) {
        boolean equalChars = true;
        equalChars = equalChars && c1 != null && c2 != null;
        equalChars = equalChars && c1.length == c2.length;
        for (int i = 0; equalChars && i < c1.length; i++ ) {
            equalChars = equalChars && c1[i] == c2[i];
        }
        return equalChars;
    }
    public String getName() {
        return NbBundle.getMessage( GenKeyBasicPanel.class, "NAME_GenKeyBasicPanel" );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        aliasLabel = new javax.swing.JLabel();
        validityLabel = new javax.swing.JLabel();
        keyAlgLabel = new javax.swing.JLabel();
        keySizeLabel = new javax.swing.JLabel();
        aliasFormattedTextField = new javax.swing.JFormattedTextField();
        validitySpinner = new javax.swing.JSpinner();
        keySizeSpinner = new javax.swing.JSpinner();
        keyPasswordLabel = new javax.swing.JLabel();
        keyPasswordField = new javax.swing.JPasswordField();
        keyPasswordCheckBox = new javax.swing.JCheckBox();
        keyAlgComboBox = new javax.swing.JComboBox();
        keyPasswordLength = new javax.swing.JLabel();
        keyPasswordReTypedField = new javax.swing.JPasswordField();

        aliasLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "IDX_Alias")));
        aliasLabel.setLabelFor(aliasFormattedTextField);
        aliasLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "LBL_Alias"));

        validityLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "IDX_ValidityInDays")));
        validityLabel.setLabelFor(validitySpinner);
        validityLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "LBL_ValidityInDays"));

        keyAlgLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "IDX_KeyAlg")));
        keyAlgLabel.setLabelFor(keyAlgComboBox);
        keyAlgLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "LBL_KeyAlg"));

        keySizeLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "IDX_KeySize")));
        keySizeLabel.setLabelFor(keySizeSpinner);
        keySizeLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "LBL_KeySize"));

        keyPasswordLabel.setDisplayedMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "IDX_KeyPassword")));
        keyPasswordLabel.setLabelFor(keyPasswordField);
        keyPasswordLabel.setText(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "LBL_KeyPassword"));

        keyPasswordField.setEnabled(false);
        keyPasswordField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                keyPasswordFieldCaretUpdate(evt);
            }
        });

        keyPasswordCheckBox.setMnemonic(Integer.parseInt(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "IDX_UseStorePassword")));
        keyPasswordCheckBox.setSelected(true);
        keyPasswordCheckBox.setText(org.openide.util.NbBundle.getMessage(GenKeyBasicPanel.class, "LBL_UseStorePassword"));
        keyPasswordCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        keyPasswordCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        keyPasswordCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                keyPasswordCheckBoxStateChangedHandler(evt);
            }
        });

        keyAlgComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        keyPasswordLength.setText("0");

        keyPasswordReTypedField.setEnabled(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(keyPasswordLabel)
                    .add(keySizeLabel)
                    .add(validityLabel)
                    .add(aliasLabel)
                    .add(keyAlgLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, keyPasswordCheckBox, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, keySizeSpinner, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, validitySpinner, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, aliasFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, keyAlgComboBox, 0, 113, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, keyPasswordReTypedField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .add(keyPasswordField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(keyPasswordLength, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(aliasFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(aliasLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(validityLabel)
                    .add(validitySpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(keySizeLabel)
                    .add(keySizeSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(keyPasswordLabel)
                    .add(keyPasswordCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(keyPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(keyPasswordLength))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(keyPasswordReTypedField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(keyAlgLabel)
                    .add(keyAlgComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void keyPasswordFieldCaretUpdate(CaretEvent evt) {//GEN-FIRST:event_keyPasswordFieldCaretUpdate
        
        final int theLengthOfKeyPassPassword = this.keyPasswordField.getPassword().length;
        
        final String newKeyPasswordLengthText = String.valueOf(theLengthOfKeyPassPassword);
        keyPasswordLength.setText(newKeyPasswordLengthText);
    }//GEN-LAST:event_keyPasswordFieldCaretUpdate
    
    private void keyPasswordCheckBoxStateChangedHandler(ChangeEvent evt) {//GEN-FIRST:event_keyPasswordCheckBoxStateChangedHandler
        final Object source = evt.getSource();
        if (source instanceof JToggleButton) {
            final JToggleButton jtb = (JToggleButton)source;
            boolean passwordFieldEnabled = !jtb.isSelected();
            this.keyPasswordField.setEnabled(passwordFieldEnabled);
            this.keyPasswordReTypedField.setEnabled(passwordFieldEnabled);
        }
        
    }//GEN-LAST:event_keyPasswordCheckBoxStateChangedHandler
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField aliasFormattedTextField;
    private javax.swing.JLabel aliasLabel;
    private javax.swing.JComboBox keyAlgComboBox;
    private javax.swing.JLabel keyAlgLabel;
    private javax.swing.JCheckBox keyPasswordCheckBox;
    private javax.swing.JPasswordField keyPasswordField;
    private javax.swing.JLabel keyPasswordLabel;
    private javax.swing.JLabel keyPasswordLength;
    private javax.swing.JPasswordField keyPasswordReTypedField;
    private javax.swing.JLabel keySizeLabel;
    private javax.swing.JSpinner keySizeSpinner;
    private javax.swing.JLabel validityLabel;
    private javax.swing.JSpinner validitySpinner;
    // End of variables declaration//GEN-END:variables
    
    public void reset() {
        final String EMPTY_STRING = "";
        this.aliasFormattedTextField.setText(EMPTY_STRING);

        KeytoolSettings keytoolSettings = KeytoolSettings.getDefault();
        final LabelKeyAlgCertAlgBean defaultValue = keytoolSettings.getDefaultGenKeyAlgorithm();
        this.keyAlgComboBox.setSelectedItem( defaultValue );
        
        this.keyPasswordCheckBox.setSelected(true);
        this.keyPasswordField.setText(EMPTY_STRING);
        this.keyPasswordReTypedField.setText(EMPTY_STRING);
        this.keySizeSpinner.setValue( 1024 );
        this.validitySpinner.setValue( 90 );
    }
    
    String getAlias() {
        final String alias = this.aliasFormattedTextField.getText();
        return alias;
    }
    void setAlias( String newAlias ) {
        this.aliasFormattedTextField.setText( newAlias );
    }
    
    int getKeySize() {
        final int keySize = ((Number)this.keySizeSpinner.getValue()).intValue();
        return keySize;
    }
    void setKeySize( int newKeySize ) {
        this.keySizeSpinner.setValue( newKeySize );
    }
    
    long getValidityInDays() {
        final long validity = ((Number)this.validitySpinner.getValue()).longValue();
        return validity;
    }
    long getValidityInSecs() {
        final long validityInDays = ((Number)this.validitySpinner.getValue()).longValue();
        final long daysToMillsFactor = 24L * 60L * 60L;
        final long validityInSec = validityInDays * daysToMillsFactor;
        
        return validityInSec;
    }
    void setValidityInSecs( long newValidityInSecs ) {
        final long millisToDaysFactor = 24L * 60L * 60L;
        final long validityInDays = newValidityInSecs / millisToDaysFactor;
        this.validitySpinner.setValue( Long.valueOf(validityInDays) );
    }
    char[] getKeyPassword() {
        char []keyPassword = null;
        
        if (!this.keyPasswordCheckBox.isSelected()) {
            keyPassword = this.keyPasswordField.getPassword();
        }
        return keyPassword;
    }
    void setKeyPassword( char[] newKeyPassword ) {
        if (newKeyPassword != null) {
            this.keyPasswordField.setText( new String( newKeyPassword ));
        } else {
            this.keyPasswordField.setText( "" );
        }
        final int keyPasswordFieldLength = this.keyPasswordField.getPassword().length;
        this.keyPasswordCheckBox.setSelected( keyPasswordFieldLength > 0);
    }
    
    /**
     * Get the key algorithm, and cert algorithm bean
     *
     * @return KeyAlgCertAlgBean
     */
    KeyAlgCertAlgBean getKeyAlgCertAlg() {
        LabelKeyAlgCertAlgBean lkacab = (LabelKeyAlgCertAlgBean)this.keyAlgComboBox.getSelectedItem();
        KeyAlgCertAlgBean kacab = lkacab.getValue();
        return kacab;
    }
    
    void setKeyAlgCertAlg( KeyAlgCertAlgBean newKeyAlgCertAlg ) {
        final LabelKeyAlgCertAlgBean lkacab = new LabelKeyAlgCertAlgBean( "", newKeyAlgCertAlg );
        this.keyAlgComboBox.setSelectedItem( lkacab );
    }
    
    public void saveTo(GenKeyForm genKeyForm) throws PropertyVetoException {
        genKeyForm.setAlias( this.getAlias() );
        genKeyForm.setValidityInSecs( this.getValidityInSecs() );
        genKeyForm.setKeySize( this.getKeySize() );
        genKeyForm.setKeyPassword( this.getKeyPassword() );
        genKeyForm.setKeyAlgCertAlg( this.getKeyAlgCertAlg() );
    }
    public void loadFrom( GenKeyForm genKeyForm ) {
        this.setAlias( genKeyForm.getAlias() );
        this.setValidityInSecs( genKeyForm.getValidityInSecs() );
        this.setKeySize( genKeyForm.getKeySize() );
        this.setKeyPassword( genKeyForm.getKeyPassword() );
        this.setKeyAlgCertAlg( genKeyForm.getKeyAlgCertAlg() );
    }
}
