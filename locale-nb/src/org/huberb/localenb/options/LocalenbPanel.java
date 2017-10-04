package org.huberb.localenb.options;

import java.awt.Component;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import javax.swing.JTextField;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;

final class LocalenbPanel extends javax.swing.JPanel {
    private PropertyEditor datePatternsPE;
    private PropertyEditor messagePatternsPE;
    private PropertyEditor numberPatternsPE;
    
    private final LocalenbOptionsPanelController controller;
    
    LocalenbPanel(LocalenbOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        messageArgsNumberParsePatternLabel = new javax.swing.JLabel();
        messageArgsDateParsePatternLabel = new javax.swing.JLabel();
        dateParseFormattedTextField = new javax.swing.JFormattedTextField();
        numberParseFormattedTextField = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        datePatternsLabel = new javax.swing.JLabel();
        messagePatternsLabel = new javax.swing.JLabel();
        numberPatternsLabel = new javax.swing.JLabel();
        datePatternsTextField = new javax.swing.JTextField();
        messagePatternsTextField = new javax.swing.JTextField();
        numberPatternsTextField = new javax.swing.JTextField();
        datePatternsButton = new javax.swing.JButton();
        messagePatternButton = new javax.swing.JButton();
        numberPatternsButton = new javax.swing.JButton();

        setBackground(java.awt.Color.white);
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.jPanel1.border.title"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(messageArgsNumberParsePatternLabel, org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.messageArgsNumberParsePatternLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(messageArgsDateParsePatternLabel, org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.messageArgsDateParsePatternLabel.text")); // NOI18N

        dateParseFormattedTextField.setText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.dateParseFormattedTextField.text")); // NOI18N
        dateParseFormattedTextField.setToolTipText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.dateParseFormattedTextField.toolTipText")); // NOI18N

        numberParseFormattedTextField.setText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.numberParseFormattedTextField.text")); // NOI18N
        numberParseFormattedTextField.setToolTipText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.numberParseFormattedTextField.toolTipText")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, messageArgsNumberParsePatternLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, messageArgsDateParsePatternLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dateParseFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .add(numberParseFormattedTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(messageArgsDateParsePatternLabel)
                    .add(dateParseFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(messageArgsNumberParsePatternLabel)
                    .add(numberParseFormattedTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.jPanel2.border.title"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(datePatternsLabel, org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.datePatternsLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(messagePatternsLabel, org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.messagePatternsLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(numberPatternsLabel, org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.numberPatternsLabel.text")); // NOI18N

        datePatternsTextField.setEditable(false);
        datePatternsTextField.setText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.datePatternsTextField.text")); // NOI18N

        messagePatternsTextField.setEditable(false);
        messagePatternsTextField.setText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.messagePatternsTextField.text")); // NOI18N

        numberPatternsTextField.setEditable(false);
        numberPatternsTextField.setText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.numberPatternsTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(datePatternsButton, org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.datePatternsButton.text")); // NOI18N
        datePatternsButton.setToolTipText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.datePatternsButton.toolTipText")); // NOI18N
        datePatternsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleDatePatternsAction(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(messagePatternButton, org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.messagePatternButton.text")); // NOI18N
        messagePatternButton.setToolTipText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.messagePatternButton.toolTipText")); // NOI18N
        messagePatternButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleMessagePatternsAction(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(numberPatternsButton, org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.numberPatternsButton.text")); // NOI18N
        numberPatternsButton.setToolTipText(org.openide.util.NbBundle.getMessage(LocalenbPanel.class, "LocalenbPanel.numberPatternsButton.toolTipText")); // NOI18N
        numberPatternsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleNumberPatternsAction(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, datePatternsLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, messagePatternsLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, numberPatternsLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(messagePatternsTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .add(datePatternsTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .add(numberPatternsTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(datePatternsButton)
                    .add(messagePatternButton)
                    .add(numberPatternsButton)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(datePatternsLabel)
                    .add(datePatternsButton)
                    .add(datePatternsTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(messagePatternsLabel)
                    .add(messagePatternButton)
                    .add(messagePatternsTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numberPatternsLabel)
                    .add(numberPatternsButton)
                    .add(numberPatternsTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void handleNumberPatternsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handleNumberPatternsAction

        handlePropertyEditorDialog( this.numberPatternsPE, this.numberPatternsTextField );
   
    }//GEN-LAST:event_handleNumberPatternsAction

    private void handleMessagePatternsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handleMessagePatternsAction

        handlePropertyEditorDialog( this.messagePatternsPE, this.messagePatternsTextField );
        
    }//GEN-LAST:event_handleMessagePatternsAction
    
    private void handleDatePatternsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handleDatePatternsAction

        handlePropertyEditorDialog( this.datePatternsPE, this.datePatternsTextField );
        
    }//GEN-LAST:event_handleDatePatternsAction
        
    /**
     * Display a dialog showing the property editor custom editor,
     * and store the property editor value as text in some target text field
     */
    private void handlePropertyEditorDialog( PropertyEditor pe, JTextField targetTextField ) {
        Component editorComponent = pe.getCustomEditor();
        
        DialogDisplayer dDisplayer = DialogDisplayer.getDefault();
        DialogDescriptor dDescriptor = new DialogDescriptor(editorComponent, "Number Patterns");
        
        Object rc = dDisplayer.notify( dDescriptor );
        if (rc == DialogDescriptor.OK_OPTION) {
            String asText = pe.getAsText();
            targetTextField.setText( asText );
        }
    }
    
    /**
     * Load values from the LocaleOption
     */
    void load() {
        final LocaleOption localeOption = LocaleOption.getDefault();
        
        final String[] datePatternsList = localeOption.getDatePatternList();
        this.datePatternsPE = PropertyEditorManager.findEditor(String[].class);
        this.datePatternsPE.setValue( datePatternsList );
        
        final String[] messagePatternsList = localeOption.getMessagePatternList();
        this.messagePatternsPE = PropertyEditorManager.findEditor(String[].class);
        this.messagePatternsPE.setValue( messagePatternsList );
        
        final String[] numberPatternsList = localeOption.getNumberPatternList();
        this.numberPatternsPE = PropertyEditorManager.findEditor(String[].class);
        this.numberPatternsPE.setValue( numberPatternsList );
        
        //---
        this.datePatternsTextField.setText( this.datePatternsPE.getAsText() );
        this.messagePatternsTextField.setText( this.messagePatternsPE.getAsText() );
        this.numberPatternsTextField.setText( this.numberPatternsPE.getAsText() );
        
        
        this.dateParseFormattedTextField.setValue( localeOption.getMessageArgDatePattern() );
        this.numberParseFormattedTextField.setValue( localeOption.getMessageArgNumberPattern() );
    }
    
    /**
     * Store value to the LocaleOption
     */
    void store() {
        final LocaleOption localeOption = LocaleOption.getDefault();
        
        localeOption.setDatePatternList( (String[])this.datePatternsPE.getValue() );
        localeOption.setMessagePatternList( (String[])this.messagePatternsPE.getValue() );
        localeOption.setNumberPatternList( (String[])this.numberPatternsPE.getValue() );
        
        localeOption.setMessageArgDatePattern( (String)this.dateParseFormattedTextField.getValue() );
        localeOption.setMessageArgNumberPattern( (String)this.numberParseFormattedTextField.getValue() );
    }
    
    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField dateParseFormattedTextField;
    private javax.swing.JButton datePatternsButton;
    private javax.swing.JLabel datePatternsLabel;
    private javax.swing.JTextField datePatternsTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel messageArgsDateParsePatternLabel;
    private javax.swing.JLabel messageArgsNumberParsePatternLabel;
    private javax.swing.JButton messagePatternButton;
    private javax.swing.JLabel messagePatternsLabel;
    private javax.swing.JTextField messagePatternsTextField;
    private javax.swing.JFormattedTextField numberParseFormattedTextField;
    private javax.swing.JButton numberPatternsButton;
    private javax.swing.JLabel numberPatternsLabel;
    private javax.swing.JTextField numberPatternsTextField;
    // End of variables declaration//GEN-END:variables
    
}
