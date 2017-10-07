/*
 * ChooseCertReqOptionsPanel.java
 *
 * Created on 22. Februar 2006, 22:28
 */

package org.huber.keytool.ui.wizard.certreq;

import javax.swing.DefaultComboBoxModel;
import org.huber.keytool.settings.KeytoolSettings;
import org.huber.keytool.ui.wizard.ChangeObserverOfWizardPanel;
import org.huber.keytool.ui.wizard.ObserverablePanel;
import org.huber.keytool.ui.wizard.genkey.LabelKeyAlgCertAlgBean;
import org.huber.keytool.ui.wizard.genkey.LabelKeyAlgCertAlgBeanFactory;

/**
 *
 * @author  HuberB1
 */
public class ChooseCertReqOptionsPanel extends javax.swing.JPanel implements ObserverablePanel {
    
    /**
     * Creates new form ChooseCertReqOptionsPanel
     */
    public ChooseCertReqOptionsPanel() {
        initComponents();

        DefaultComboBoxModel dcbm = new DefaultComboBoxModel( LabelKeyAlgCertAlgBeanFactory.createLabelKeyAlgCertAlgBeans() );
        this.certReqSigAlgComboBox.setModel( dcbm );

        this.reset();
    }
    
    public String getName() {
        return org.openide.util.NbBundle.getMessage(ChooseCertReqOptionsPanel.class, "NAME_ChooseCertReqOptionsPanel");
    }
    
    public void bind(ChangeObserverOfWizardPanel co) {
    }
    
    public ValidUserEntryResult isValidUserEntry() {
        //boolean isValid = true;
        final ObserverablePanel.ValidUserEntryResult vuer = new ObserverablePanel.ValidUserEntryResult();
        
        return vuer;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        certReqFileLabel = new javax.swing.JLabel();
        certReqFileTextField = new javax.swing.JTextField();
        certReqSigAlgLabel = new javax.swing.JLabel();
        certReqSigAlgComboBox = new javax.swing.JComboBox();
        aliasLabel = new javax.swing.JLabel();
        aliasComboBox = new javax.swing.JComboBox();
        keyPasswordLabel = new javax.swing.JLabel();
        useStorePasswordCheckBox = new javax.swing.JCheckBox();
        keyPasswordField = new javax.swing.JPasswordField();

        certReqFileLabel.setLabelFor(certReqFileTextField);
        certReqFileLabel.setText(org.openide.util.NbBundle.getMessage(ChooseCertReqOptionsPanel.class, "LBL_CertReqFile")); // NOI18N

        certReqFileTextField.setEditable(false);

        certReqSigAlgLabel.setText(org.openide.util.NbBundle.getMessage(ChooseCertReqOptionsPanel.class, "LBL_CertReqSigAlg")); // NOI18N

        aliasLabel.setLabelFor(aliasComboBox);
        aliasLabel.setText(org.openide.util.NbBundle.getMessage(ChooseCertReqOptionsPanel.class, "LBL_Alias")); // NOI18N

        aliasComboBox.setModel(new DefaultComboBoxModel());

        keyPasswordLabel.setText(org.openide.util.NbBundle.getMessage(ChooseCertReqOptionsPanel.class, "LBL_KeyPassword")); // NOI18N

        useStorePasswordCheckBox.setText(org.openide.util.NbBundle.getMessage(ChooseCertReqOptionsPanel.class, "LBL_UseStorePassword")); // NOI18N
        useStorePasswordCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        useStorePasswordCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        useStorePasswordCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useStorePasswordCheckBoxActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, keyPasswordLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, certReqSigAlgLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, aliasLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, certReqFileLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(certReqSigAlgComboBox, 0, 201, Short.MAX_VALUE)
                    .add(certReqFileTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .add(aliasComboBox, 0, 201, Short.MAX_VALUE)
                    .add(useStorePasswordCheckBox)
                    .add(keyPasswordField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(certReqFileLabel)
                    .add(certReqFileTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(aliasLabel)
                    .add(aliasComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(certReqSigAlgLabel)
                    .add(certReqSigAlgComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(keyPasswordLabel)
                    .add(useStorePasswordCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(keyPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void useStorePasswordCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useStorePasswordCheckBoxActionPerformed

        final boolean useStorePasswordIsEnabled = this.useStorePasswordCheckBox.isSelected();
        this.keyPasswordField.setEnabled(!useStorePasswordIsEnabled);
        
    }//GEN-LAST:event_useStorePasswordCheckBoxActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox aliasComboBox;
    private javax.swing.JLabel aliasLabel;
    private javax.swing.JLabel certReqFileLabel;
    private javax.swing.JTextField certReqFileTextField;
    private javax.swing.JComboBox certReqSigAlgComboBox;
    private javax.swing.JLabel certReqSigAlgLabel;
    private javax.swing.JPasswordField keyPasswordField;
    private javax.swing.JLabel keyPasswordLabel;
    private javax.swing.JCheckBox useStorePasswordCheckBox;
    // End of variables declaration//GEN-END:variables
    
    private final static String EMPTY_STRING = "";
        
    public void reset() {
        this.certReqFileTextField.setText( EMPTY_STRING );
        
        KeytoolSettings keytoolSettings = KeytoolSettings.getDefault();
        LabelKeyAlgCertAlgBean defaultValue = keytoolSettings.getDefaultCSRAlgorithm();
        this.certReqSigAlgComboBox.setSelectedItem( defaultValue );
    }

    void setCertReqFile(String newCertReqFile) {
        this.certReqFileTextField.setText( newCertReqFile );
    }
    
    String getSigAlg() {
        final LabelKeyAlgCertAlgBean lkacab = (LabelKeyAlgCertAlgBean)this.certReqSigAlgComboBox.getSelectedItem();
        final String sigAlg = lkacab.getValue().getCertAlg();
        return sigAlg;
    }

    void setAliases(String[] aliases) {
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel)this.aliasComboBox.getModel();

        dcbm.removeAllElements();
        
        for (String alias : aliases) {
            dcbm.addElement( alias );
        }
    }
    String getAlias() {
        String alias = (String)this.aliasComboBox.getSelectedItem();
        return alias;
    }

    char[] getKeyPassword() {
        char[] keyPassword = null;
        if (!this.useStorePasswordCheckBox.isEnabled()) {
            keyPassword = this.keyPasswordField.getPassword();
        }
        return keyPassword;
    }
}
