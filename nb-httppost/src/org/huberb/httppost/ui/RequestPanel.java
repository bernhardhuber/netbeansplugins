/*
 * RequestPanel.java
 *
 * Created on 25. September 2006, 22:42
 */

package org.huberb.httppost.ui;

import org.huberb.httppost.model.HttpPostForm;

/**
 *
 * @author  HuberB1
 */
public class RequestPanel extends javax.swing.JPanel {
    
    /** Creates new form RequestPanel */
    public RequestPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        urlLabel = new javax.swing.JLabel();
        urlTextField = new javax.swing.JTextField();
        tapPanel = new org.huberb.httppost.ui.TapPanel();
        tabPanelLabel = new javax.swing.JLabel();
        tapSubPanel = new javax.swing.JPanel();
        methodLabel = new javax.swing.JLabel();
        methodComboBox = new javax.swing.JComboBox();
        contentTypeLabel = new javax.swing.JLabel();
        contentTypeComboBox = new javax.swing.JComboBox();
        useCacheCheckBox = new javax.swing.JCheckBox();
        followRedirectsCheckBox = new javax.swing.JCheckBox();
        requestDataLabel = new javax.swing.JLabel();
        requestDataScrollPane = new javax.swing.JScrollPane();
        requestDataTextArea = new javax.swing.JTextArea();

        urlLabel.setLabelFor(urlTextField);
        org.openide.awt.Mnemonics.setLocalizedText(urlLabel, org.openide.util.NbBundle.getMessage(RequestPanel.class, "RequestPanel.urlLabel.text")); // NOI18N

        tapPanel.setOrientation(1);
        org.openide.awt.Mnemonics.setLocalizedText(tabPanelLabel, org.openide.util.NbBundle.getMessage(RequestPanel.class, "RequestPanel.tabPanelLabel.text")); // NOI18N
        tabPanelLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        tapPanel.add(tabPanelLabel);

        methodLabel.setLabelFor(methodComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(methodLabel, org.openide.util.NbBundle.getMessage(RequestPanel.class, "RequestPanel.methodLabel.text")); // NOI18N

        methodComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "POST", "GET" }));
        methodComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                methodComboBoxItemStateChanged(evt);
            }
        });

        contentTypeLabel.setLabelFor(contentTypeComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(contentTypeLabel, org.openide.util.NbBundle.getMessage(RequestPanel.class, "RequestPanel.contentTypeLabel.text")); // NOI18N

        contentTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "text/xml", "application/x-www-form-urlencoded" }));

        org.openide.awt.Mnemonics.setLocalizedText(useCacheCheckBox, org.openide.util.NbBundle.getMessage(RequestPanel.class, "RequestPanel.useCacheCheckBox.text")); // NOI18N
        useCacheCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        useCacheCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.openide.awt.Mnemonics.setLocalizedText(followRedirectsCheckBox, org.openide.util.NbBundle.getMessage(RequestPanel.class, "RequestPanel.followRedirectsCheckBox.text")); // NOI18N
        followRedirectsCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        followRedirectsCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        requestDataLabel.setLabelFor(requestDataTextArea);
        org.openide.awt.Mnemonics.setLocalizedText(requestDataLabel, org.openide.util.NbBundle.getMessage(RequestPanel.class, "RequestPanel.requestDataLabel.text")); // NOI18N

        requestDataTextArea.setColumns(20);
        requestDataTextArea.setRows(5);
        requestDataTextArea.setMinimumSize(new java.awt.Dimension(0, 36));
        requestDataScrollPane.setViewportView(requestDataTextArea);

        org.jdesktop.layout.GroupLayout tapSubPanelLayout = new org.jdesktop.layout.GroupLayout(tapSubPanel);
        tapSubPanel.setLayout(tapSubPanelLayout);
        tapSubPanelLayout.setHorizontalGroup(
            tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tapSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(requestDataScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .add(tapSubPanelLayout.createSequentialGroup()
                        .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, requestDataLabel)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, contentTypeLabel)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, methodLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(methodComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(contentTypeComboBox, 0, 110, Short.MAX_VALUE))
                        .add(23, 23, 23)
                        .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(followRedirectsCheckBox)
                            .add(useCacheCheckBox))))
                .addContainerGap())
        );
        tapSubPanelLayout.setVerticalGroup(
            tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tapSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(methodLabel)
                    .add(methodComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(followRedirectsCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tapSubPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(contentTypeLabel)
                    .add(contentTypeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(useCacheCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(requestDataLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(requestDataScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        tapPanel.add(tapSubPanel);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(tapPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(urlLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(urlTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(urlLabel)
                    .add(urlTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tapPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void methodComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_methodComboBoxItemStateChanged
        boolean newState = false;
        String method = (String) evt.getItem();
        if ("POST".equalsIgnoreCase(method)) {
            newState = true;
        }
        requestDataTextArea.setEnabled(newState);
        contentTypeComboBox.setEnabled(newState);
    }//GEN-LAST:event_methodComboBoxItemStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox contentTypeComboBox;
    private javax.swing.JLabel contentTypeLabel;
    private javax.swing.JCheckBox followRedirectsCheckBox;
    private javax.swing.JComboBox methodComboBox;
    private javax.swing.JLabel methodLabel;
    private javax.swing.JLabel requestDataLabel;
    private javax.swing.JScrollPane requestDataScrollPane;
    private javax.swing.JTextArea requestDataTextArea;
    private javax.swing.JLabel tabPanelLabel;
    private org.huberb.httppost.ui.TapPanel tapPanel;
    private javax.swing.JPanel tapSubPanel;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JTextField urlTextField;
    private javax.swing.JCheckBox useCacheCheckBox;
    // End of variables declaration//GEN-END:variables

    public String getUrl() {
        final String url = this.urlTextField.getText();
        return url;
    }
    public String getMethod() {
        final String method = String.valueOf(this.methodComboBox.getSelectedItem());
        return method;
    }
    public String getRequestData() {
        return this.requestDataTextArea.getText();
    }
    public String getContentType() {
        final String contentType = String.valueOf(this.contentTypeComboBox.getSelectedItem());
        return contentType;
    }
    public Boolean getUseCache() {
        return Boolean.valueOf( this.useCacheCheckBox.isSelected());
    }
    public Boolean getFollowRedirects() {
        return Boolean.valueOf( this.followRedirectsCheckBox.isSelected() );
    }
    
    HttpPostForm getHttpPostForm( HttpPostForm httpPostForm ) {
        httpPostForm.setUrl( this.getUrl() );
        httpPostForm.setMethod( this.getMethod() );
        httpPostForm.setRequestData( this.getRequestData() );
        httpPostForm.setContentType( this.getContentType() );
        httpPostForm.setUseCache( this.getUseCache() );
        httpPostForm.setFollowRedirects( this.getFollowRedirects() );
        
        return httpPostForm;
    }

    void updateHttpPostForm(HttpPostForm httpPostForm) {
        this.urlTextField.setText( httpPostForm.getUrl() );
        this.methodComboBox.setSelectedItem( httpPostForm.getMethod() );
        this.requestDataTextArea.setText( httpPostForm.getRequestData() );
        this.contentTypeComboBox.setSelectedItem( httpPostForm.getContentType() );
        this.useCacheCheckBox.setSelected( httpPostForm.getUseCache() );
        this.followRedirectsCheckBox.setSelected( httpPostForm.getFollowRedirects() );
    }
}
