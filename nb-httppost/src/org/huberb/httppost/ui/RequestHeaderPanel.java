/*
 * RequestHeaderPanel.java
 *
 * Created on 25. September 2006, 20:06
 */

package org.huberb.httppost.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import org.huberb.httppost.model.HttpPostForm;

/**
 *
 * @author  HuberB1
 */
public class RequestHeaderPanel extends javax.swing.JPanel {
    
    private final DefaultComboBoxModel<String[]> dcbm;
    private final HeadersTableModel htm;
    
    /** Creates new form RequestHeaderPanel */
    public RequestHeaderPanel() {
        initComponents();
        
        this.dcbm = new DefaultComboBoxModel(new String[] {
            "User-Agent", 
            "Accept",
            // TOOD more headers
        });
        this.headerNameComboBox.setModel( dcbm );
        
        this.htm = new HeadersTableModel();
        this.headersTable.setModel( htm );
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tapPanel = new org.huberb.httppost.ui.TapPanel();
        tapPanelLabel = new javax.swing.JLabel();
        tapSubPanel = new javax.swing.JPanel();
        headerNameLabel = new javax.swing.JLabel();
        headerNameComboBox = new javax.swing.JComboBox();
        headerValueLabel = new javax.swing.JLabel();
        headerValueTextField = new javax.swing.JTextField();
        headersScrollPane = new javax.swing.JScrollPane();
        headersTable = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        headersListTitlePanel = new javax.swing.JPanel();
        headerListLabel = new javax.swing.JLabel();
        headersListTitleSeparator = new javax.swing.JSeparator();

        setFocusCycleRoot(true);

        tapPanel.setOrientation(1);
        org.openide.awt.Mnemonics.setLocalizedText(tapPanelLabel, org.openide.util.NbBundle.getMessage(RequestHeaderPanel.class, "RequestHeaderPanel.tapPanelLabel.text")); // NOI18N
        tapPanel.add(tapPanelLabel);

        headerNameLabel.setLabelFor(headerNameComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(headerNameLabel, org.openide.util.NbBundle.getMessage(RequestHeaderPanel.class, "RequestHeaderPanel.headerNameLabel.text")); // NOI18N

        headerNameComboBox.setEditable(true);

        headerValueLabel.setLabelFor(headerValueTextField);
        org.openide.awt.Mnemonics.setLocalizedText(headerValueLabel, org.openide.util.NbBundle.getMessage(RequestHeaderPanel.class, "RequestHeaderPanel.headerValueLabel.text")); // NOI18N

        headerValueTextField.setText(org.openide.util.NbBundle.getMessage(RequestHeaderPanel.class, "RequestHeaderPanel.headerValueTextField.text")); // NOI18N

        headersScrollPane.setViewportView(headersTable);

        buttonPanel.setFocusCycleRoot(true);
        org.openide.awt.Mnemonics.setLocalizedText(addButton, org.openide.util.NbBundle.getMessage(RequestHeaderPanel.class, "RequestHeaderPanel.addButton.text")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(editButton, org.openide.util.NbBundle.getMessage(RequestHeaderPanel.class, "RequestHeaderPanel.editButton.text")); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(removeButton, org.openide.util.NbBundle.getMessage(RequestHeaderPanel.class, "RequestHeaderPanel.removeButton.text")); // NOI18N
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        org.jdesktop.layout.GroupLayout buttonPanelLayout = new org.jdesktop.layout.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonPanelLayout.createSequentialGroup()
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addButton)
                    .add(removeButton)
                    .add(editButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap())
        );

        buttonPanelLayout.linkSize(new java.awt.Component[] {addButton, removeButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonPanelLayout.createSequentialGroup()
                .add(addButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(editButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(removeButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );

        headersListTitlePanel.setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(headerListLabel, org.openide.util.NbBundle.getMessage(RequestHeaderPanel.class, "RequestHeaderPanel.headerListLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        headersListTitlePanel.add(headerListLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        headersListTitlePanel.add(headersListTitleSeparator, gridBagConstraints);

        org.jdesktop.layout.GroupLayout tapSubPanelLayout = new org.jdesktop.layout.GroupLayout(tapSubPanel);
        tapSubPanel.setLayout(tapSubPanelLayout);
        tapSubPanelLayout.setHorizontalGroup(
            tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tapSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(headersListTitlePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, tapSubPanelLayout.createSequentialGroup()
                        .add(headersScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(buttonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(tapSubPanelLayout.createSequentialGroup()
                        .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(tapSubPanelLayout.createSequentialGroup()
                                .add(headerValueLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(headerValueTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                            .add(tapSubPanelLayout.createSequentialGroup()
                                .add(headerNameLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(headerNameComboBox, 0, 302, Short.MAX_VALUE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .addContainerGap())
        );
        tapSubPanelLayout.setVerticalGroup(
            tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tapSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tapSubPanelLayout.createSequentialGroup()
                        .add(28, 28, 28)
                        .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(headerValueLabel)
                            .add(headerValueTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(headerNameLabel)
                        .add(headerNameComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(headersListTitlePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(headersScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .add(buttonPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tapPanel.add(tapSubPanel);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(tapPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tapPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        
        final int minSelectionIndex = this.headersTable.getSelectionModel().getMinSelectionIndex();
        final HeaderBean hb = this.htm.getHeaderBeanAt( minSelectionIndex );
        if (hb != null) {
            this.htm.removeHeaderBean( hb );
        }
        
    }//GEN-LAST:event_removeButtonActionPerformed
    
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        final int minSelectionIndex = this.headersTable.getSelectionModel().getMinSelectionIndex();
        final HeaderBean hb = this.htm.getHeaderBeanAt( minSelectionIndex );
        if (hb != null) {
            this.headerNameComboBox.setSelectedItem( hb.getHeaderName() );
            this.headerValueTextField.setText( hb.getHeaderValuesAsCSV() );
        }
    }//GEN-LAST:event_editButtonActionPerformed
    
    
    
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        final String headerName = (String)this.dcbm.getSelectedItem();
        final String headerValue = this.headerValueTextField.getText();
        HeaderBean hb = new HeaderBean(headerName, headerValue);
        this.htm.addHeaderBean( hb );
        
    }//GEN-LAST:event_addButtonActionPerformed
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel headerListLabel;
    private javax.swing.JComboBox headerNameComboBox;
    private javax.swing.JLabel headerNameLabel;
    private javax.swing.JLabel headerValueLabel;
    private javax.swing.JTextField headerValueTextField;
    private javax.swing.JPanel headersListTitlePanel;
    private javax.swing.JSeparator headersListTitleSeparator;
    private javax.swing.JScrollPane headersScrollPane;
    private javax.swing.JTable headersTable;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton removeButton;
    private org.huberb.httppost.ui.TapPanel tapPanel;
    private javax.swing.JLabel tapPanelLabel;
    private javax.swing.JPanel tapSubPanel;
    // End of variables declaration//GEN-END:variables
    
    //---
    HttpPostForm getHttpPostForm(HttpPostForm httpPostForm) {
        Map<String, List<String>> requestHeaders = new HashMap<String, List<String>>();
        for (int i = 0; i < this.htm.getRowCount(); i++ ) {
            HeaderBean hb = this.htm.getHeaderBeanAt(i);
            final String key = hb.getHeaderName();
            final List<String> valuesAsList = hb.getHeaderValueAsList();
            requestHeaders.put( key, valuesAsList );
        }
        httpPostForm.setRequestHeaders(requestHeaders);
        
        return httpPostForm;
    }
    
    void updateHttpPostForm(HttpPostForm httpPostForm) {
        this.htm.clean();
        
        final Map<String, List<String>> requestHeaders = httpPostForm.getRequestHeaders();
        if (requestHeaders != null) {
            for (Map.Entry<String, List<String>> me : requestHeaders.entrySet()) {
                final String name = me.getKey();
                final List<String> valuesAsList = me.getValue();
                final String[] values = valuesAsList.toArray(new String[valuesAsList.size()]);
                
                final HeaderBean hb = new HeaderBean(name,values);
                this.htm.addHeaderBean( hb );
            }
        }
    }
    
}
