package org.huberb.nbpmdrulesets.options;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;

final class NbpmdrulesetsPanel extends javax.swing.JPanel {
    
    private final NbpmdrulesetsOptionsPanelController controller;
    
    NbpmdrulesetsPanel(NbpmdrulesetsOptionsPanelController controller) {
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
        pmdHomeLabel = new javax.swing.JLabel();
        pmdHomeTextField = new javax.swing.JTextField();
        pmdHomeButton = new javax.swing.JButton();

        setBackground(java.awt.Color.white);
        org.openide.awt.Mnemonics.setLocalizedText(pmdHomeLabel, "PMD Home");

        org.openide.awt.Mnemonics.setLocalizedText(pmdHomeButton, "...");
        pmdHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handlePmdHomeAction(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(pmdHomeLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pmdHomeTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pmdHomeButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(pmdHomeLabel)
                    .add(pmdHomeButton)
                    .add(pmdHomeTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void handlePmdHomeAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handlePmdHomeAction
        
        File currentDirectory = null;
        
        JFileChooser jfc = new JFileChooser(currentDirectory);
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        Component parent = null;
        int rc = jfc.showOpenDialog(parent);
        if (rc == JFileChooser.APPROVE_OPTION) {
            File theSelectedFile = jfc.getSelectedFile();
            this.pmdHomeTextField.setText( theSelectedFile.toString() );
        }
        //
        
    }//GEN-LAST:event_handlePmdHomeAction
    
    void load() {
        // TODO read settings and initialize GUI
        PmdRulesetSettings prs = PmdRulesetSettings.getDefault();
        
        this.pmdHomeTextField.setText( prs.getPmdHome() );
    }
    
    void store() {
        // TODO store modified settings
        PmdRulesetSettings prs = PmdRulesetSettings.getDefault();
        
        prs.setPmdHome( this.pmdHomeTextField.getText() );
    }
    
    boolean valid() {
        boolean isValid = true;
        // TODO check whether form is consistent and complete
        String pmdHome = this.pmdHomeTextField.getText();
        
        isValid = isValid && pmdHome != null;
        File pmdHomeFile = new File(pmdHome);
        isValid = isValid && pmdHomeFile.exists();
        isValid = isValid && pmdHomeFile.isDirectory();
        
        return isValid;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton pmdHomeButton;
    private javax.swing.JLabel pmdHomeLabel;
    private javax.swing.JTextField pmdHomeTextField;
    // End of variables declaration//GEN-END:variables
    
}